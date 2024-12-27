package com.microservice.AuthService.config;

public class PublicUrls {
    public static final String[] PUBLIC_URLS = {
            /*  "/v3/",
              "/authorize/swagger-ui/",
              "/v3/api-docs/",*/
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/swagger-resources",
            "/auth/login",
            "/auth/refresh-token"
            ,"/auth/validate-token"
            ,"/authUser/addUser"
//            ,"/authUser/addRole"
    };
}
