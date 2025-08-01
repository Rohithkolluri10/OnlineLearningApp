package com.onlineLearningPlatform.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorResponseDto {

    @Schema(
            description = "API path invoked by client"
    )
    private String apiPath;
    @Schema(
            description = "Error Message occured"
    )
    private String errorMessage;

    @Schema(
            description = "Error Code returned to the client"
    )
    private String errorCode;

    @Schema(
            description = "Errortime of the request"
    )
    private LocalDateTime errortime;
}
