package com.preet.Journal.App.controller;

import com.preet.Journal.App.Service.JournalService;
import com.preet.Journal.App.entity.JournalEntity;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalController {

    @Autowired
    private JournalService journalService;

    @GetMapping
    public List<JournalEntity> getAll(){
        return journalService.getAll();
    }

    @PostMapping
    public void createEntry(@RequestBody JournalEntity journalEntity)
    {
        journalEntity.setDate(LocalDateTime.now());
        journalService.createEntry(journalEntity);
    }

    @GetMapping("id/{id}")
    public JournalEntity getEntryById(@PathVariable ObjectId id){
       return journalService.getEntryById(id).orElse(null);
    }

    @DeleteMapping("id/{id}")
    public Boolean deleteEntryById(@PathVariable ObjectId id) {
        journalService.deleteEntryById(id);
        return true;
    }

    @PutMapping("id/{id}")
    public JournalEntity updateEntry(@PathVariable ObjectId id, @RequestBody JournalEntity journalEntity){
        JournalEntity old= journalService.getEntryById(id).orElse(null);
        if(old != null){
            old.setTitle(journalEntity.getTitle()!=null&&!journalEntity.getTitle().equals("")?journalEntity.getTitle():old.getTitle());
            old.setContent(journalEntity.getContent()!=null&&!journalEntity.getContent().equals("")?journalEntity.getContent():old.getContent());
        }
        journalService.updateEntry(old);
        return old;
    }
}
