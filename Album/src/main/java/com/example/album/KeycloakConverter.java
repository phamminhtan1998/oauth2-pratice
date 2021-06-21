package com.example.album;


import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KeycloakConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        Map<String,Object> realmAccess = (Map<String, Object>) jwt.getClaims().get("realm_access");
        if(realmAccess==null||realmAccess.isEmpty()){
            return new ArrayList<>();
        }
        Collection<GrantedAuthority> roles = ((List<String>) realmAccess.get("roles")).stream()
                .map(roleName -> "ROLE_" + roleName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        roles.forEach(grantedAuthority -> System.out.println(grantedAuthority.getAuthority()));
        return roles;


    }
}
