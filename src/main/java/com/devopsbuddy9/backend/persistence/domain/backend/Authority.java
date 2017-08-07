package com.devopsbuddy9.backend.persistence.domain.backend;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by kb on 7/21/2017.
 */
public class Authority implements GrantedAuthority {

    private final String authority;

    public Authority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
