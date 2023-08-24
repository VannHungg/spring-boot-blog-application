package com.example.springbootblogapplication.controllers;

import com.example.springbootblogapplication.model.Account;
import com.example.springbootblogapplication.model.Post;
import com.example.springbootblogapplication.services.AccountService;
import com.example.springbootblogapplication.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.Optional;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/posts/{id}")
    public String getPostById(@PathVariable("id") Long id, Model model) {
        Optional<Post> optionalPost = postService.getById(id);
        if(optionalPost.isPresent()) {
            Post post = optionalPost.get();
            model.addAttribute("post", post);
            return "post";
        }
        else {
            return "404";
        }
    }

    @GetMapping("/posts/new")
    public String createNewPost(Model model) {
        Optional<Account> optionalAccount = accountService.findOneByEmail("user.user@domain.com");
        if(optionalAccount.isPresent()) {
            Post post = new Post();
            post.setAccount(optionalAccount.get());
            model.addAttribute("post", post);
            return "post_new";
        }
        else {
            return "404";
        }

//        Account account1 = new Account();
//        account1.setFirstName("user");
//        account1.setLastName("user");
//        account1.setEmail("user.user@domain.com");
//        account1.setPassword("password");
//        System.out.println("account1: " + account1);
//        Post post = new Post();
//        post.setAccount(account1);
//        model.addAttribute("post", post);
//
//        Account account = accountService.findById(1L).orElseThrow(() -> new IllegalArgumentException("can not find by id"));
//        System.out.println("account: " + account);
//        return "post_new";
    }

    @PostMapping("/posts/new")
    public String saveNewPost(@ModelAttribute Post post) {
        Post newPost = postService.savePost(post);
        return "redirect:/posts/" + newPost.getId();
    }
}
