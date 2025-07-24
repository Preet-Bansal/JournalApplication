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
@RequestMapping("/journal")
public class JournalController {

    @Autowired
    private JournalService journalService;

    @Autowired
    private UserService userService;

    @GetMapping("{username}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String username){

        User user=userService.findByusername(username);
        List<JournalEntity> entries = user.getJournalEntityList();
        if(entries != null && !entries.isEmpty()){
            return new ResponseEntity<>(entries, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("{username}")
    public ResponseEntity<JournalEntity> createEntry(@RequestBody JournalEntity journalEntity, @PathVariable String username)
    {
        try {
             journalService.createEntry(journalEntity, username);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
       catch (Exception e){
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }

    @GetMapping("id/{id}")
    public ResponseEntity<JournalEntity> getEntryById(@PathVariable ObjectId id){
      Optional<JournalEntity> entry=  journalService.getEntryById(id);
      if(entry.isPresent()){
          return new ResponseEntity<>(entry.get(), HttpStatus.FOUND);
      }
      else
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{username},{id}")
    public ResponseEntity<JournalEntity> deleteEntryById(@PathVariable ObjectId id,
                                                         @PathVariable String username) {

        journalService.deleteEntryById(id, username);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("id/{username},{id}")
    public ResponseEntity<JournalEntity> updateEntry(@PathVariable ObjectId id,
                                                     @RequestBody JournalEntity journalEntity,
                                                     @PathVariable String username){
        JournalEntity old= journalService.getEntryById(id).orElse(null);
        if(old != null){
            old.setTitle(journalEntity.getTitle()!=null&&!journalEntity.getTitle().equals("")?journalEntity.getTitle():old.getTitle());
            old.setContent(journalEntity.getContent()!=null&&!journalEntity.getContent().equals("")?journalEntity.getContent():old.getContent());
            journalService.updateEntry(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
