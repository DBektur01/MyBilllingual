package javaB13.dto.responses.auth;
import javaB13.enums.Role;
import lombok.Builder;

@Builder
public record AuthenticationResponse(
        String token,
        String email,
        Role role
) {
}
