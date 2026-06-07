package com.devansh.analytics_dashboard.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.slf4j.MDC;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Result<T> implements Serializable {

    public enum Status {
        SUCCESS,
        FAIL,
        ERROR
    }

    public enum CustomStatus {

        NO_DATA_FOUND(250, "No Data Found"),

        BAD_REQUEST(400, "Bad Request"),
        UNAUTHORIZED(401, "Unauthorized"),
        FORBIDDEN(403, "Forbidden"),
        UNPROCESSABLE_ENTITY(422, "Unprocessable Entity"),

        EXTERNAL_API_DATA_FAILURE(424, "Client Dependency Failure"),
        SYSTEM_RESOURCE_NOT_FOUND(450, "System Resource Not Found"),

        EXTERNAL_DEPENDENCY_FAILURE(520, "External Dependency Failure"),
        BAD_DATA(521, "Bad Data"),
        EXTERNAL_DATA_FAILURE(522, "External Data Failure");

        private final int statusCode;
        private final String reasonPhrase;

        CustomStatus(int statusCode, String reasonPhrase) {
            this.statusCode = statusCode;
            this.reasonPhrase = reasonPhrase;
        }

        public int getStatusCode() {
            return statusCode;
        }

        public String getReasonPhrase() {
            return reasonPhrase;
        }
    }

    @Schema(name = "status", example = "SUCCESS", description = "Response status")
    private Status status;

    @Schema(name = "message", example = "Data fetched successfully", description = "Response message")
    private String message;

    @Schema(name = "data", description = "Response payload")
    private T data;

    @Schema(name = "errorCode", example = "ERR-001", description = "Custom error code")
    private String errorCode;

    @Builder.Default
    @Schema(name = "failures", description = "Validation failures")
    private List<Failure> failures = new ArrayList<>();

    @Schema(name = "type", description = "Custom status type")
    private CustomStatus type;

    @Builder.Default
    @Schema(name = "trace", description = "Request trace id")
    private String trace = MDC.get("traceId");

    @Schema(name = "errorDetail", description = "Additional error details")
    private Object errorDetail;

    public void enableTrace() {
        this.trace = MDC.get("traceId");
    }

    public boolean isSuccessful() {
        return status == Status.SUCCESS;
    }

    public static <T> Result<T> success() {
        return Result.<T>builder()
                .status(Status.SUCCESS)
                .build();
    }

    public static <T> Result<T> success(T data) {
        return Result.<T>builder()
                .status(Status.SUCCESS)
                .data(data)
                .build();
    }

    public static <T> Result<T> success(
            String message) {
        return Result.<T>builder()
                .status(Status.SUCCESS)
                .message(message)
                .build();
    }

    public static <T> Result<T> success(
            String message,
            T data) {
        return Result.<T>builder()
                .status(Status.SUCCESS)
                .message(message)
                .data(data)
                .build();
    }

    public static <T> Result<T> fail(
            String message) {
        return Result.<T>builder()
                .status(Status.FAIL)
                .message(message)
                .build();
    }

    public static <T> Result<T> fail(
            String message,
            CustomStatus type) {
        return Result.<T>builder()
                .status(Status.FAIL)
                .message(message)
                .type(type)
                .build();
    }

    public static <T> Result<T> fail(
            T data) {
        return Result.<T>builder()
                .status(Status.FAIL)
                .data(data)
                .build();
    }

    public static <T> Result<T> fail(
            List<Failure> failures) {
        return Result.<T>builder()
                .status(Status.FAIL)
                .failures(failures)
                .build();
    }

    public static <T> Result<T> fail(
            String field,
            String rejectedValue,
            String message) {
        return Result.<T>builder()
                .status(Status.FAIL)
                .message(message)
                .failures(
                        List.of(
                                new Failure(
                                        field,
                                        rejectedValue,
                                        message)))
                .build();
    }

    public static <T> Result<T> error(
            String message) {
        return Result.<T>builder()
                .status(Status.ERROR)
                .message(message)
                .build();
    }

    public static <T> Result<T> error(
            String message,
            CustomStatus type) {
        return Result.<T>builder()
                .status(Status.ERROR)
                .message(message)
                .type(type)
                .build();
    }

    public static <T> Result<T> error(
            String message,
            Object errorDetail) {
        return Result.<T>builder()
                .status(Status.ERROR)
                .message(message)
                .errorDetail(errorDetail)
                .build();
    }

    public static <T> Result<T> error(
            T data,
            CustomStatus type) {
        return Result.<T>builder()
                .status(Status.ERROR)
                .data(data)
                .type(type)
                .build();
    }

}