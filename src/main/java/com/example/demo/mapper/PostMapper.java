package com.example.demo.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.example.demo.entity.Post;

@Mapper
public interface PostMapper {
    List<Post> selectAllPosts();
    Post selectPostById(@Param("id") Integer id);
    int insertPost(Post post);
    int updatePost(Post post);
    int deletePost(@Param("id") Integer id);
}
