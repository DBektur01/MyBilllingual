package javaB13.dto.requests.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import javaB13.validations.PasswordValid;
import lombok.Builder;

@Builder
public record ResetPasswordRequest(
        @NotBlank(message = "The password must not be empty.")
        @NotNull(message = "The password must not be empty.")
        @PasswordValid
        String newPassword
) {
}
