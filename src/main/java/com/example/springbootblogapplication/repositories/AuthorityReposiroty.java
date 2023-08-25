package com.example.springbootblogapplication.repositories;

import com.example.springbootblogapplication.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityReposiroty extends JpaRepository<Authority, String> {
}
