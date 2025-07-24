package com.preet.Journal.App.Service;

import com.preet.Journal.App.Repository.JournalRepo;
import com.preet.Journal.App.entity.JournalEntity;
import com.preet.Journal.App.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalService {

    @Autowired
    private JournalRepo journalRepo;

    @Autowired
    private UserService userService;

    //saving entries
    @Transactional
    public void createEntry(JournalEntity journalEntity, String username){
        try {
            User user = userService.findByusername(username);
            journalEntity.setDate(LocalDateTime.now());
            JournalEntity saved = journalRepo.save(journalEntity);
            user.getJournalEntityList().add(saved);
            userService.createUser(user);
        }
        catch (Exception e)
        {
            System.out.println(e);
            throw new RuntimeException("something went wrong while saving entry", e);
        }
    }

    //getting all entries
    public List<JournalEntity> getAll() {
       return journalRepo.findAll();
    }

    //getting entry by id
    public Optional<JournalEntity> getEntryById(ObjectId id) {
        return journalRepo.findById(id);
    }

    //delete entry by id
    public void deleteEntryById(ObjectId id, String username) {
        User user=userService.findByusername(username);
        user.getJournalEntityList().removeIf(x-> x.getId().equals(id));
        userService.createUser(user);
        journalRepo.deleteById(id);
    }

    //updating entry
    public void updateEntry(JournalEntity journalEntity) {
        journalRepo.save(journalEntity);
    }
}
