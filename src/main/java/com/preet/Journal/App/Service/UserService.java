package com.preet.Journal.App.Service;

import com.preet.Journal.App.Repository.JournalRepo;
import com.preet.Journal.App.Repository.UserRepo;
import com.preet.Journal.App.entity.JournalEntity;
import com.preet.Journal.App.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    //saving entries
    public void createUser(User user){
        userRepo.save(user);
    }

    //getting all entries
    public List<User> getAll() {
       return userRepo.findAll();
    }

//    //getting entry by id
//    public Optional<User> getEntryById(ObjectId id) {
//        return userRepo.findById(id);
//    }
//
//    //delete entry by id
//    public void deleteEntryById(ObjectId id) {
//        userRepo.deleteById(id);
//    }

    //updating entry
    public User findByusername(String username) {
       return userRepo.findByusername(username);
    }
}
