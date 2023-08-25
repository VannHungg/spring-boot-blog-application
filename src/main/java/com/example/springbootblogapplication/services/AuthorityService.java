package com.example.springbootblogapplication.services;

import com.example.springbootblogapplication.model.Authority;
import com.example.springbootblogapplication.repositories.AuthorityReposiroty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorityService {

    @Autowired
    private AuthorityReposiroty authorityReposiroty;

    public Optional<Authority> findAuthorityByName(String name) {
        return authorityReposiroty.findById(name);
    }

    public Authority save(Authority user) {
        return authorityReposiroty.save(user);
    }
}
