package io.github.Yuurim98.digi_capsule.common.exception;

import io.github.Yuurim98.digi_capsule.common.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String ERROR_LOG_MESSAGE = "[ERROR] {} : {}";

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse<Void>> handleCustomException(CustomException exception) {
        log.error(ERROR_LOG_MESSAGE, exception.getClass().getSimpleName(), exception.getMessage());
        ErrorCode errorCode = exception.getErrorCode();
        return ResponseEntity
            .status(errorCode.getHttpStatus())
            .body(ApiResponse.error(errorCode));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationExceptions(
        MethodArgumentNotValidException exception) {

        FieldError firstError = exception.getFieldErrors().get(0);
        String message = String.format("{%s} %s", firstError.getField(), firstError.getDefaultMessage());

        log.error(ERROR_LOG_MESSAGE, exception.getClass().getName(), message);


        return ResponseEntity
            .status(ErrorCode.VALIDATION_FAILED.getHttpStatus())
            .body(ApiResponse.error(ErrorCode.VALIDATION_FAILED.getHttpStatus(), message));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Void>> handleHttpMessageNotReadableException(
        HttpMessageNotReadableException exception) {
        log.error(ERROR_LOG_MESSAGE, exception.getClass().getName(), exception.getMessage());
        String userFriendlyMessage = "요청 형식이 올바르지 않거나 요청 본문이 비어 있습니다.";
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ApiResponse.error(HttpStatus.BAD_REQUEST, userFriendlyMessage));
    }

}
