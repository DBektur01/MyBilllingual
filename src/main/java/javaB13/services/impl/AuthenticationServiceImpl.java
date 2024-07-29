package javaB13.services.impl;

import javaB13.config.jwt.JwtService;
import javaB13.dto.requests.auth.AuthenticationRequest;
import javaB13.dto.requests.auth.ForgotPassword;
import javaB13.dto.requests.auth.SignUpRequest;
import javaB13.dto.responses.SimpleResponse;
import javaB13.dto.responses.auth.AuthenticationResponse;
import javaB13.entity.User;
import javaB13.entity.UserInfo;
import javaB13.enums.Role;
import javaB13.exceptions.AlreadyExistException;
import javaB13.exceptions.NotFoundException;
import javaB13.repositories.UserInfoRepository;
import javaB13.repositories.UserRepository;
import javaB13.services.AuthenticationService;
import javaB13.services.EmailService;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserInfoRepository userInfoRepository;
    private final EmailService emailService;
    private final TemplateEngine templateEngine;

    @Override
    public AuthenticationResponse signUp(SignUpRequest signUpRequest) {
        log.info("");
        return Optional.of(signUpRequest)
                .map(SignUpRequest::email)
                .filter(email -> !userRepository.existsByUserInfoEmail(email))
                .map(email -> User.builder()
                        .firstName(signUpRequest.firstName())
                        .lastName(signUpRequest.lastName())
                        .email(email)
                        .password(passwordEncoder.encode(signUpRequest.password()))
                        .role(Role.USER)
                        .build())
                .map(user -> {
                    userRepository.save(user);
                    return user;
                })
                .map(user -> AuthenticationResponse.builder()
                        .token(jwtService.generateToken((UserDetails) user))
                        .email(user.getEmail())
                        .role(user.getRole())
                        .build())
                .orElseThrow(() -> new AlreadyExistException("User with email: " + signUpRequest.email() + " already exists"));
    }

    /**
     * Method SignIn
     * 1)-  Получение email из AuthenticationRequest...
     * Извлекаем email из запроса аутентификации....
     * <p>
     * 2) - Поиск пользователя по email:
     * Ищем пользователя по email в репозитории.
     * Используем flatMap, так как findUserInfoByEmail
     * возвращает Optional<User>
     * <p>
     * 3) -  Аутентифицируем пользователя с помощью authenticationManager.
     * Если аутентификация успешна, возвращаем пользователя..
     * <p>
     * 4) - Генерация JWT токена и формирование ответа:
     * Генерируем JWT токен для пользователя и формируем ответ с токеном, email и ролью.
     * <p>
     * 5) - Обработка отсутствия пользователя:
     * Если пользователь не найден, выбрасываем исключение NotFoundException.
     */
    @Override
    public AuthenticationResponse signIn(AuthenticationRequest authenticationRequest) {
        return Optional.of(authenticationRequest)
                .map(AuthenticationRequest::email)
                .flatMap(userRepository::findUserInfoByEmail)
                .map(user -> {
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    authenticationRequest.email(),
                                    authenticationRequest.password()
                            )
                    );
                    return user;
                })
                .map(user -> {
                    String jwtToken = jwtService.generateToken(user);
                    return AuthenticationResponse.builder()
                            .token(jwtToken)
                            .email(user.getUsername())
                            .role(user.getRole())
                            .build();
                })
                .orElseThrow(() -> new NotFoundException("User was not found."));
    }

    /**
     * 1) - Поиск пользователя по email или выброс исключения...
     * 2) - Генерация токена и обновление пользователя...
     * 3) - Формирование и отправка письма...
     */
    @Override
    public SimpleResponse forgotPassword(ForgotPassword forgotPassword) {
        log.info("Initiating password reset");

        UserInfo userInfo = userRepository.findUserInfoByEmail(forgotPassword.email())
                .orElseThrow(() -> new NotFoundException("User was not found"));

        String token = UUID.randomUUID().toString();
        userInfo.setResetPasswordToken(token);
        userInfoRepository.save(userInfo);

        String resetPasswordLink = "http://localhost:8080/api/auth/reset-password?token=" + token;
        Context context = new Context();
        context.setVariable("title", "Password Reset");
        context.setVariable("message", "Hello Nuriza");
        context.setVariable("token", resetPasswordLink);
        context.setVariable("tokenTitle", "Reset Password");

        String htmlContent = templateEngine.process("reset-password-template.html", context);

        try {
            emailService.sendEmail(forgotPassword.email(), "Password Reset Request", htmlContent);
            log.info("Password reset email sent");

            return SimpleResponse.builder()
                    .message("The password reset was sent to your email. Please check your email.")
                    .build();
        } catch (MessagingException e) {
            log.error("Error sending password reset email", e);
            return SimpleResponse.builder()
                    .message("Failed to send password reset email. Please try again.")
                    .build();
        }
    }


    @Override
    public SimpleResponse resetPassword(String token, String newPassword) {
        log.info("Resetting password");
        UserInfo userInfo = userInfoRepository.findByResetPasswordToken(token)
                .orElseThrow(() -> new NotFoundException("User was not found"));
        userInfo.setPassword(passwordEncoder.encode(newPassword));
        userInfo.setResetPasswordToken(null);
        userInfoRepository.save(userInfo);
        log.info("Password reset successful");
        return SimpleResponse.builder().message("User password changed successfully!").build();
    }


    @Override
    public AuthenticationResponse authWithGoogle(String tokenId) throws FirebaseAuthException {
        log.info("Authenticating with Google");
        FirebaseToken firebaseToken = FirebaseAuth.getInstance().verifyIdToken(tokenId);
        if (userRepository.findUserInfoByEmail(firebaseToken.getEmail()).isEmpty()) {
            User newUser = new User();
            String[] name = firebaseToken.getName().split(" ");
            newUser.setFirstName(name[0]);
            newUser.setLastName(name[1]);
            UserInfo userInfo = new UserInfo();
            userInfo.setEmail(firebaseToken.getEmail());
            userInfo.setPassword(firebaseToken.getEmail());
            userInfo.setRole(Role.USER);
            userRepository.save(newUser);
        }
        UserInfo userInfo = userRepository.findUserInfoByEmail(firebaseToken.getEmail()).orElseThrow(() -> {
            log.error(String.format("Пользователь с таким электронным адресом %s не найден!", firebaseToken.getEmail()));
            return new NotFoundException(String.format("Пользователь с таким электронным адресом %s не найден!", firebaseToken.getEmail()));
        });

        String token = jwtService.generateToken(userInfo);
        log.info("Authentication with Google successful");
        return AuthenticationResponse.builder()
                .email(firebaseToken.getEmail())
                .token(token)
                .role(userInfo.getRole())
                .build();
    }

    @PostConstruct
    public void init() {
        try {
            log.info("Initializing Firebase");
            GoogleCredentials googleCredentials = GoogleCredentials.fromStream(new ClassPathResource("bilingual.json").getInputStream());
            FirebaseOptions firebaseOptions = FirebaseOptions.builder()
                    .setCredentials(googleCredentials)
                    .build();
            FirebaseApp firebaseApp = FirebaseApp.initializeApp(firebaseOptions);
            log.info("Firebase initialized successfully");
        } catch (IOException e) {
            log.error("IOException occurred during initialization");
        }
    }
}
