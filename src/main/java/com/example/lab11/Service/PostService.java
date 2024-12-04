package com.example.lab11.Service;

import com.example.lab11.ApiResponse.ApiException;
import com.example.lab11.Model.Post;
import com.example.lab11.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final CategoryService categoryService;
    private final UserService userService;

    public List<Post> getAllPost(){
        return postRepository.findAll();
    }

    public Post getPostById(Integer id){
        return postRepository.findPostById(id);
    }
    public Post getPostTitle(String title){return postRepository.findPostByTitle(title);}
    public List<Post> getPostByUserId(Integer id){return postRepository.getPostByUserId(id);}
    public List<Post> getAllPostByDateAndBefore(LocalDate date){return postRepository.getAllPostByDateAndBefore(date);}

    public void addPost(Post post){
        if (categoryService.getCategoryById(post.getCategoryId()) == null) throw new ApiException("Error:Category not found");
        if (userService.getUserById(post.getUserId()) == null) throw new ApiException("Error: User not found");

        post.setPublishDate(LocalDate.now());
        postRepository.save(post);
    }
    public void updatePost(Integer id, Post post){
        if (postRepository.findPostById(id) == null) throw new ApiException("Error: post not found");
        if (categoryService.getCategoryById(post.getCategoryId()) == null) throw new ApiException("Error:Category not found");
        if (userService.getUserById(post.getUserId()) == null) throw new ApiException("Error: User not found");

        post.setId(id);
        postRepository.save(post);
    }
    public void deletePost(Integer id){
        if (postRepository.findPostById(id) == null) throw new ApiException("Error: post not found");

        postRepository.deleteById(id);
    }
}
