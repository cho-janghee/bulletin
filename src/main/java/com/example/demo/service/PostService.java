package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.Post;

public interface PostService {
    // 1) 전체 게시글 조회
    List<Post> getAllPosts();

    // 2) 게시글 단건 조회
    Post getPostById(Integer id);

    // 3) 게시글 작성
    void createPost(Post post);

    // 4) 게시글 수정
    void updatePost(Post post);

    // 5) 게시글 삭제
    void deletePost(Integer id);
}
