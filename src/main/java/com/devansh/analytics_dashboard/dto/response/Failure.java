package com.devansh.analytics_dashboard.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Data
@Builder
@Getter
public class Failure {
    private String field;
    private Object rejectedValue;
    private Object message;
}
