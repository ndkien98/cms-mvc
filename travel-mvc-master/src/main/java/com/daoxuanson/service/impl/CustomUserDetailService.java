package com.daoxuanson.service.impl;

import com.daoxuanson.model.request.RoleRequest;
import com.daoxuanson.model.request.UserRestquestApi;
import com.daoxuanson.security.CustomUserDetail;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private RestTemplate restTemplate;

    public CustomUserDetailService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserRestquestApi user = restTemplate.getForObject("http://localhost:8888/user?userName="+s, UserRestquestApi.class);

        if (Objects.isNull(user)){
            throw new UsernameNotFoundException("Didn't found user by username " + s);
        }
        Set<GrantedAuthority> authoritySet = new HashSet<>();

        for (RoleRequest role: user.getRoles()) {
            authoritySet.add(new SimpleGrantedAuthority(role.getName()));
        }
        UserDetails userDetails = new CustomUserDetail(s,user.getPassword(),authoritySet);
        BeanUtils.copyProperties(user,userDetails);
        return userDetails;
    }
}
