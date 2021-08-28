package com.murilonerdx.restapispring.security;

import lombok.Data;

@Data
public class AccountCredentialDTO {
    private String username;
    private String password;
}
