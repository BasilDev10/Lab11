package com.example.lab11.Controller;

import com.example.lab11.ApiResponse.ApiResponse;
import com.example.lab11.Model.Post;
import com.example.lab11.Service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController {

    private final PostService postService;

    @GetMapping("/get")
    public ResponseEntity getAllPost(){
        return ResponseEntity.ok(postService.getAllPost());
    }
    @GetMapping("/get-by-title/{title}")
    public ResponseEntity getPostTitle(@PathVariable String title){
        return ResponseEntity.ok(postService.getPostTitle(title));
    }
    @GetMapping("/get-by-user-id/{userId}")
    public ResponseEntity getPostByUserId(@PathVariable Integer userId){
        return ResponseEntity.ok(postService.getPostByUserId(userId));
    }
    @GetMapping("/get-by-today-or-before/{date}")
    public ResponseEntity getAllPostByDateAndBefore(@PathVariable LocalDate date){
        return ResponseEntity.ok(postService.getAllPostByDateAndBefore(date));
    }
    @PostMapping("/add")
    public ResponseEntity addPost(@RequestBody @Valid Post post, Errors errors){
        if (errors.hasErrors())return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));

        postService.addPost(post);
        return ResponseEntity.status(201).body(new ApiResponse("Post is added"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updatePost(@PathVariable Integer id, @RequestBody @Valid Post post, Errors errors){
        if (errors.hasErrors())return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));

        postService.updatePost(id,post);
        return ResponseEntity.status(200).body(new ApiResponse("Post is updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePost(@PathVariable Integer id){

        postService.deletePost(id);
        return ResponseEntity.status(200).body(new ApiResponse("Post is deleted"));
    }
}
