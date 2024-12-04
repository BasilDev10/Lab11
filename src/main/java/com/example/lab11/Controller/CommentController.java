package com.example.lab11.Controller;


import com.example.lab11.ApiResponse.ApiResponse;
import com.example.lab11.Model.Comment;
import com.example.lab11.Service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comment")
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/get")
    public ResponseEntity getAllComment(){
        return ResponseEntity.ok(commentService.getAllComment());
    }

    @GetMapping("/get-by-today-or-before/{date}")
    public ResponseEntity getCommentByTodayOrBefore(@PathVariable LocalDate date){
        return ResponseEntity.ok(commentService.getCommentByTodayOrBefore(date));
    }
    @GetMapping("/get-by-post-id/{postId}")
    public ResponseEntity getCommentByPostId(@PathVariable Integer postId){
        return ResponseEntity.ok(commentService.getCommentByPostId(postId));
    }


    @PostMapping("/add")
    public ResponseEntity addComment(@RequestBody @Valid Comment comment , Errors errors){
        if (errors.hasErrors())return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));

        commentService.addComment(comment);
        return ResponseEntity.status(201).body(new ApiResponse("comment id added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateComment(@PathVariable Integer id , @RequestBody @Valid Comment comment , Errors errors){
        if (errors.hasErrors())return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));

        commentService.updateComment(id,comment);
        return ResponseEntity.status(200).body(new ApiResponse("comment id updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteComment(@PathVariable Integer id ){

        commentService.deleteComment(id);
        return ResponseEntity.status(200).body(new ApiResponse("comment id deleted"));
    }
}
