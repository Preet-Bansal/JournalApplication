package com.preet.Journal.App.Service;

import com.preet.Journal.App.Repository.JournalRepo;
import com.preet.Journal.App.entity.JournalEntity;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalService {

    @Autowired
    private JournalRepo journalRepo;

    //saving entries
    public void createEntry(JournalEntity journalEntity){
        journalRepo.save(journalEntity);
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
    public void deleteEntryById(ObjectId id) {
        journalRepo.deleteById(id);
    }

    //updating entry
    public void updateEntry(JournalEntity journalEntity) {
        journalRepo.save(journalEntity);
    }
}
