package com.daoxuanson.util;

import com.daoxuanson.model.request.UserRestquestApi;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

public class Encoder {

    public static void main(String[] args) {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String password = "123456";
//        System.out.println(passwordEncoder.encode("123456"));
//        System.out.println(passwordEncoder.matches("123456", "$2a$10$ZzgTtiC/E/LVCwolhkXDIOm3y0UxnY/jznXSThZfO890dp8r2p6sW"));

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8888/user?userName=";
        String userName = "sondx";

        UserRestquestApi restquestApi = restTemplate.getForObject(url + userName, UserRestquestApi.class);
        System.out.println(restquestApi.getUserName());
        System.out.println(restquestApi.getPassword());
        restquestApi.getRoles().forEach(System.out::println);
    }
}
