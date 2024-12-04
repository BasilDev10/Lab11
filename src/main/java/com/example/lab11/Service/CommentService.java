package com.example.lab11.Service;

import com.example.lab11.ApiResponse.ApiException;
import com.example.lab11.Model.Comment;
import com.example.lab11.Repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostService postService;
    private final UserService userService;

    public List<Comment> getAllComment(){
        return commentRepository.findAll();
    }

    public Comment getCommentById(Integer id){
        return commentRepository.findCommentById(id);
    }

    public List<Comment> getCommentByTodayOrBefore(LocalDate date){
        return commentRepository.findCommentsByCommentDateIsLessThanEqual(date);
    }

    public List<Comment> getCommentByPostId(Integer id){
        return commentRepository.getCommentByPostId(id);
    }
    public void addComment(Comment comment){

        if (postService.getPostById(comment.getPostId()) == null) throw new ApiException("Error: post not found");
        if (userService.getUserById(comment.getUserId()) == null) throw new ApiException("Error: User not found");

        comment.setCommentDate(LocalDate.now());
        commentRepository.save(comment);
    }

    public void updateComment(Integer id,Comment comment){
        if (commentRepository.findCommentById(id) == null) throw new ApiException("Error: comment not found");
        if (postService.getPostById(comment.getPostId()) == null) throw new ApiException("Error: post not found");
        if (userService.getUserById(comment.getUserId()) == null) throw new ApiException("Error: User not found");

        comment.setId(id);
        commentRepository.save(comment);
    }

    public void deleteComment(Integer id){
        if (commentRepository.findCommentById(id) == null) throw new ApiException("Error: comment not found");

        commentRepository.deleteById(id);
    }
}
