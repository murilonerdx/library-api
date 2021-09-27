package com.murilonerdx.restapispring.security;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AccountCredentialDTO {
    private String username;
    private String password;
}
