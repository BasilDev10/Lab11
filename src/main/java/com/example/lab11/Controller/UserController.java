package com.example.lab11.Controller;

import com.example.lab11.ApiResponse.ApiResponse;
import com.example.lab11.Model.User;
import com.example.lab11.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getAllUser(){
        return ResponseEntity.ok(userService.getAllUser());
    }
    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user , Errors errors){
        if (errors.hasErrors())return ResponseEntity.status(400).body((errors.getFieldError().getDefaultMessage()));

        userService.addUser(user);
        return ResponseEntity.status(201).body(("User is added"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id ,@RequestBody @Valid User user , Errors errors){
        if (errors.hasErrors())return ResponseEntity.status(400).body((errors.getFieldError().getDefaultMessage()));

        userService.updateUser(id,user);
        return ResponseEntity.status(200).body(("User is updated"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id ){

        userService.deleteUser(id);
        return ResponseEntity.status(200).body(("User is deleted"));
    }
}
