package javaB13.dto.requests.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import javaB13.validations.PasswordValid;
import lombok.Builder;

@Builder
public record AuthenticationRequest(
        @NotBlank(message = "The email must not be empty.")
        @NotNull(message = "The email must not be empty.")
        @Email(message = "Sorry, the email address you entered is invalid. Please check if it is correct")
        String email,

        @NotBlank(message = "The password must not be empty.")
        @NotNull(message = "The password must not be empty.")
        @PasswordValid
        String password
) {
}
