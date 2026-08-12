package com.springboot.springbootSecurity.services;

import com.springboot.springbootSecurity.dto.PostDTO;
import com.springboot.springbootSecurity.entities.PostEntity;
import com.springboot.springbootSecurity.exceptions.ResourceNotFoundException;
import com.springboot.springbootSecurity.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PostDTO> getAllPosts() {
       return postRepository.findAll().
               stream()
                .map(postEntity -> modelMapper.map(postEntity, PostDTO.class))
                .toList();
    }

    @Override
    public PostDTO createPost(PostDTO inputPost) {

        PostEntity postEntity = modelMapper.map(inputPost, PostEntity.class);
        PostEntity savedPost = postRepository.save(postEntity);
        return modelMapper.map(savedPost, PostDTO.class);
    }

    @Override
    public PostDTO getPostById(Long postId) {
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));
        return modelMapper.map(postEntity, PostDTO.class);
    }

    @Override
    public PostDTO updatePost(Long postId, PostDTO updatedPost) {

        PostEntity olderPostEntity = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));

        updatedPost.setId(postId);
        modelMapper.map(updatedPost, olderPostEntity);
        PostEntity savedPost = postRepository.save(olderPostEntity);
        return modelMapper.map(savedPost, PostDTO.class);
    }
}
