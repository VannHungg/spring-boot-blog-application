package com.example.springbootblogapplication.services;

import com.example.springbootblogapplication.model.Account;
import com.example.springbootblogapplication.model.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service("userDetailsService")
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Account> optionalUserDetails = accountService.findByEmail(email);
        if(!optionalUserDetails.isPresent()) {
            throw new UsernameNotFoundException("Account not found");
        }
        Account account = optionalUserDetails.get();
        return new User(account.getEmail(), account.getPassword(), mapRolesToAuthorities(account.getAuthorities()));
    }

    public List<GrantedAuthority> mapRolesToAuthorities(Set<Authority> authorities) {
        return authorities.stream().map(authority -> new SimpleGrantedAuthority(authority.getName())).collect(Collectors.toList());
    }
}
