package com.example.demo.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.Post;
import com.example.demo.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 1) 전체 게시글 조회 (GET /api/posts)
    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        
        return ResponseEntity.ok(posts);
    }

    // 2) 게시글 단건 조회 (GET /api/posts/{id})
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Integer id) {
        Post post = postService.getPostById(id);
        if (post == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(post);
    }

    // 3) 게시글 작성 (POST /api/posts)
    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody Post post) {
        // title, kategorie, content가 JSON 바디에 담겨서 오리라고 기대
        postService.createPost(post);
        return ResponseEntity.ok().build();
    }

    // 4) 게시글 수정 (PUT /api/posts/{id})
    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePost(
            @PathVariable Integer id,
            @RequestBody Post updated) {

        Post existing = postService.getPostById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        // 클라이언트로부터 받은 JSON 객체(updated)에는 id 없이 title/kategorie/content가 담겨있다고 가정
        updated.setId(id);
        postService.updatePost(updated);
        return ResponseEntity.ok().build();
    }

    // 5) 게시글 삭제 (DELETE /api/posts/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Integer id) {
        Post existing = postService.getPostById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        postService.deletePost(id);
        return ResponseEntity.ok().build();
    }
}
