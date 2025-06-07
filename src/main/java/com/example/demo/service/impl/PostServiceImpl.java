package com.example.demo.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.entity.Post;
import com.example.demo.mapper.PostMapper;
import com.example.demo.service.PostService;

@Service
@Transactional  // 트랜잭션 관리를 Spring이 자동으로 해줌
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;

    // 생성자 주입 방식 (Spring 4.3 이후 @Autowired 생략 가능)
    public PostServiceImpl(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    @Override
    public List<Post> getAllPosts() {
        return postMapper.selectAllPosts();
    }

    @Override
    public Post getPostById(Integer id) {
        return postMapper.selectPostById(id);
    }

    @Override
    public void createPost(Post post) {
        postMapper.insertPost(post);
    }

    @Override
    public void updatePost(Post post) {
        postMapper.updatePost(post);
    }

    @Override
    public void deletePost(Integer id) {
        postMapper.deletePost(id);
    }
}
