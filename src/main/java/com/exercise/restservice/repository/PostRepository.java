package com.exercise.restservice.repository;

import com.exercise.restservice.entity.PostDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostDb, Long> {
    List<PostDb> findByUserId(Long userId);


}
