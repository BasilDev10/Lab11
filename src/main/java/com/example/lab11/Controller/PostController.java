package com.example.lab11.Controller;

import com.example.lab11.ApiResponse.ApiResponse;
import com.example.lab11.Model.Post;
import com.example.lab11.Service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController {

    private final PostService postService;

    @GetMapping("/get")
    public ResponseEntity getAllPost(){
        return ResponseEntity.ok(postService.getAllPost());
    }
    @PostMapping("/add")
    public ResponseEntity addPost(@RequestBody @Valid Post post, Errors errors){
        if (errors.hasErrors())return ResponseEntity.status(400).body((errors.getFieldError().getDefaultMessage()));

        postService.addPost(post);
        return ResponseEntity.status(201).body(("Post is added"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updatePost(@PathVariable Integer id, @RequestBody @Valid Post post, Errors errors){
        if (errors.hasErrors())return ResponseEntity.status(400).body((errors.getFieldError().getDefaultMessage()));

        postService.updatePost(id,post);
        return ResponseEntity.status(200).body(("Post is updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePost(@PathVariable Integer id){

        postService.deletePost(id);
        return ResponseEntity.status(200).body(("Post is deleted"));
    }
}
