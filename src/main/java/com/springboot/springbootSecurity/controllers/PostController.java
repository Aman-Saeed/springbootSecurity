package com.springboot.springbootSecurity.controllers;

import com.springboot.springbootSecurity.dto.PostDTO;
import com.springboot.springbootSecurity.entities.User;
import com.springboot.springbootSecurity.services.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/posts")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<PostDTO> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{postId}")
    public PostDTO getPostById(@PathVariable Long postId) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("User {}", user);

        return postService. getPostById(postId);
    }

    @PostMapping
    public PostDTO createPost(@RequestBody PostDTO inputPost) {

        System.out.println("PostController.createPost() called with inputPost: " + inputPost);
        return postService.createPost(inputPost);
    }

    @PutMapping("/{postId}")
    public PostDTO updatePost(@PathVariable Long postId, @RequestBody PostDTO updatedPost) {
        return postService.updatePost(postId, updatedPost);
    }

}
