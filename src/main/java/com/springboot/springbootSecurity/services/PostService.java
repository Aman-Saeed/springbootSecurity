package com.springboot.springbootSecurity.services;

import com.springboot.springbootSecurity.dto.PostDTO;

import java.util.List;

public interface PostService {

    List<PostDTO> getAllPosts();

    PostDTO createPost(PostDTO inputPost);

    PostDTO getPostById(Long postId);

    PostDTO updatePost(Long postId, PostDTO updatedPost);
}
