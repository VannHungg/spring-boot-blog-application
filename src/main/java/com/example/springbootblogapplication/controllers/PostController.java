package com.example.springbootblogapplication.controllers;

import com.example.springbootblogapplication.model.Account;
import com.example.springbootblogapplication.model.Post;
import com.example.springbootblogapplication.services.AccountService;
import com.example.springbootblogapplication.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.time.LocalTime;
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

    @PostMapping("/posts/{id}")
    @PreAuthorize("isAuthenticated()")
    public String updatePost(@PathVariable("id") Long id, Post post, BindingResult bindingResult, Model model) {
        Optional<Post> optionalPost = postService.getById(id);
        if(optionalPost.isPresent()) {
            Post newPost = optionalPost.get();
            newPost.setTitle(post.getTitle());
            newPost.setBody(post.getBody());
            newPost.setUpdateAt(LocalDateTime.now());
            postService.savePost(newPost);
            return "redirect:/posts/" + post.getId();
        }
        else {
            return "404";
        }
    }

    @GetMapping("/posts/new")
    public String createNewPost(Model model) {
        Optional<Account> optionalAccount = accountService.findByEmail("user.user@domain.com");
        if(optionalAccount.isPresent()) {
            Post post = new Post();
            post.setAccount(optionalAccount.get());
            model.addAttribute("post", post);
            return "post_new";
        }
        else {
            return "404";
        }
    }

    @PostMapping("/posts/new")
    public String saveNewPost(@ModelAttribute Post post) {
        postService.savePost(post);
        return "redirect:/posts/" + post.getId();
    }

    @GetMapping("/posts/{id}/edit")
    @PreAuthorize("isAuthenticated()")
    public String getPostForEdit(@PathVariable("id") Long id, Model model) {
        Optional<Post> optionalPost = postService.getById(id);
        if(optionalPost.isPresent()) {
            Post post = optionalPost.get();
            model.addAttribute("post", post);
            return "post_edit";
        }
        else {
            return "404";
        }
    }

    @GetMapping("/posts/{id}/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deletePost(@PathVariable("id") Long id, Model model) {
        Optional<Post> optionalPost = postService.getById(id);
        if(optionalPost.isPresent()) {
            Post post = optionalPost.get();
            postService.deletePost(post);
            return "redirect:/";
        }
        else {
            return "404";
        }
    }
}
