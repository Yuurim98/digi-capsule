package io.github.Yuurim98.digi_capsule.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.github.Yuurim98.digi_capsule.common.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse<T> {

    private static final String SUCCESS_STATUS = "success";
    private static final String ERROR_STATUS = "error";

    private final String status;
    private final int code;
    private final String message;
    @JsonInclude(Include.NON_NULL)
    private final T data;


    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<T>(SUCCESS_STATUS, 200, message, data);
    }

    public static ApiResponse<String> success(String message) {
        return new ApiResponse<String>(SUCCESS_STATUS, 200, message, null);
    }

    public static ApiResponse<Void> error(ErrorCode errorCode) {
        return new ApiResponse<Void>(ERROR_STATUS, errorCode.getHttpStatus().value(), errorCode.getMessage(), null);
    }

    public static ApiResponse<Void> error(HttpStatus status, String message) {
        return new ApiResponse<Void>(ERROR_STATUS, status.value(), message, null);
    }

}
