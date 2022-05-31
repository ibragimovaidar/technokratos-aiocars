package com.technokratos.aiocars.security.userdetails;

import com.technokratos.aiocars.dto.response.PrivilegeResponse;
import com.technokratos.aiocars.dto.response.RoleResponse;
import com.technokratos.aiocars.dto.response.UserResponse;
import com.technokratos.aiocars.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TokenAuthenticationUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

    private final AccountService accountService;

    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) throws UsernameNotFoundException {
        UserResponse userResponse = (UserResponse) token.getPrincipal();

        return Optional.ofNullable(userResponse)
                .map(account -> UserAccount.builder()
                        .id(account.getId())
                        .username(account.getUsername())
                        .email(account.getEmail())
                        .authorities(getAuthorities(account.getRoles()))
                        .accessToken((String) token.getCredentials())
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("Unknown user by token " + token));
    }

    private List<SimpleGrantedAuthority> getAuthorities(Set<RoleResponse> roles) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> {
            authorities.addAll(role.getPrivileges().stream()
                    .map(PrivilegeResponse::getPrivilege)
                    .map(privilege -> "PRIVILEGE_" + privilege)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList()));

            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole()));
        });
        return authorities;
    }

}
