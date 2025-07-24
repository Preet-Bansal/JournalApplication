package com.preet.Journal.App.controller;

import com.preet.Journal.App.Service.JournalService;
import com.preet.Journal.App.Service.UserService;
import com.preet.Journal.App.entity.JournalEntity;
import com.preet.Journal.App.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

        @Autowired
        private UserService userService;

        @GetMapping
        public List<User> getAllUser(){
            return userService.getAll();
        }

        @PostMapping
        public void createUser(@RequestBody User user){
            userService.createUser(user);
        }

         @PutMapping("/{username}")
         public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable String username){
          User userInDb=  userService.findByusername(username);
          if(userInDb!=null){
              userInDb.setUsername(user.getUsername());
              userInDb.setPassword(user.getPassword());
              userService.createUser(userInDb);
          }
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }


}
