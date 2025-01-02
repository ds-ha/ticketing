package com.concert.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResponse<T> {
    private String timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private T data;
}