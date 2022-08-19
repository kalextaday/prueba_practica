package com.example.demo.application.dto.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeneralResponse <T> {

    private Integer code;

    private String message;

    private T data;

    public T getData() {
        return this.data;
    }

    public void setData(final T data) {
        this.data = data;
    }

}
