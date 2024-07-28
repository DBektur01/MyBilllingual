package javaB13.services;

import javaB13.dto.requests.auth.AuthenticationRequest;
import javaB13.dto.requests.auth.ForgotPassword;
import javaB13.dto.requests.auth.SignUpRequest;
import javaB13.dto.responses.SimpleResponse;
import javaB13.dto.responses.auth.AuthenticationResponse;
import javaB13.exceptions.FirebaseAuthException;
import org.springframework.stereotype.Service;

@Service

public interface AuthenticationService {
    AuthenticationResponse signUp(SignUpRequest signUpRequest);

    AuthenticationResponse signIn(AuthenticationRequest authenticationRequest);

    SimpleResponse forgotPassword(ForgotPassword forgotPassword);

    SimpleResponse resetPassword(String token, String newPassword);

    AuthenticationResponse authWithGoogle(String tokenId) throws FirebaseAuthException, com.google.firebase.auth.FirebaseAuthException;

}
