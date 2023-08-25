package com.example.springbootblogapplication.config;

import com.example.springbootblogapplication.model.Account;
import com.example.springbootblogapplication.model.Authority;
import com.example.springbootblogapplication.model.Post;
import com.example.springbootblogapplication.repositories.PostRepository;
import com.example.springbootblogapplication.services.AccountService;
import com.example.springbootblogapplication.services.AuthorityService;
import com.example.springbootblogapplication.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class SeedData implements CommandLineRunner {

    @Autowired
    private PostService postService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private AuthorityService authorityService;

    @Override
    public void run(String... args) throws Exception {
        List<Post> posts = postService.getAllPost();

        if(posts.size() == 0) {
            Authority user = new Authority();
            user.setName("ROLE_USER");
            authorityService.save(user);

            Authority admin = new Authority();
            admin.setName("ROLE_ADMIN");
            authorityService.save(admin);

            Account account1 = new Account();
            account1.setFirstName("user");
            account1.setLastName("user");
            account1.setEmail("user.user@domain.com");
            account1.setPassword("password");
            Set<Authority> authorities1 = new HashSet<>();
            authorityService.findAuthorityByName("ROLE_USER").ifPresent(authorities1::add);
            account1.setAuthorities(authorities1);

            Account account2 = new Account();
            account2.setFirstName("admin");
            account2.setLastName("admin");
            account2.setEmail("admin.admin@domain.com");
            account2.setPassword("password");
            Set<Authority> authorities2 = new HashSet<>();
            authorityService.findAuthorityByName("ROLE_USER").ifPresent(authorities2::add);
            authorityService.findAuthorityByName("ROLE_ADMIN").ifPresent(authorities2::add);
            account2.setAuthorities(authorities2);

            accountService.save(account1);
            accountService.save(account2);

            Post post1 = new Post();
            post1.setTitle("title of post 1");
            post1.setBody("body of post 1");
            post1.setAccount(account1);

            Post post2 = new Post();
            post2.setTitle("title of post 2");
            post2.setBody("body of post 2");
            post2.setAccount(account2);

            postService.savePost(post1);
            postService.savePost(post2);
        }

    }
}
