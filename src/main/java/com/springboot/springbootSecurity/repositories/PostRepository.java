package com.springboot.springbootSecurity.repositories;

import com.springboot.springbootSecurity.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

}
