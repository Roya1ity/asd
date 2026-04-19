package com.example.hello.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseUser {
    private String id;
    private String name;
    private String email;
}
