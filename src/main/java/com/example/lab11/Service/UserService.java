package com.example.lab11.Service;

import com.example.lab11.ApiResponse.ApiException;
import com.example.lab11.Model.User;
import com.example.lab11.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public User getUserById(Integer id){
        return userRepository.findUserById(id);
    }
    public List<User> getUsersByDateRange(LocalDate from , LocalDate to){
        if (from.isAfter(to)) throw new ApiException("Error: date from is greater then date to");
        return userRepository.getUsersByDateRange(from,to);
    }

    public void addUser(User user){
        user.setRegistrationDate(LocalDate.now());
        userRepository.save(user);
    }
    public void updateUser(Integer id , User user){

        if (userRepository.findUserById(id) == null) throw new ApiException("Error: user not found");

        user.setId(id);
        userRepository.save(user);
    }
    public void deleteUser(Integer id ){

        if (userRepository.findUserById(id) == null) throw new ApiException("Error: user not found");

        userRepository.deleteById(id);
    }
}
