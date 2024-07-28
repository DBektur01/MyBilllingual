package javaB13.dto.responses;

import lombok.Builder;

@Builder
public record SimpleResponse(
        String message
) {
}
