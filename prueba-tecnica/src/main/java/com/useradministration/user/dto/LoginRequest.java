package com.useradministration.user.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String mobilePhone;
    private String password;
}