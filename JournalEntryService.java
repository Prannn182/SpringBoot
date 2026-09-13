package com.example.Journal.App.services;

import com.example.Journal.App.Entity.JournalEntity;
import com.example.Journal.App.repository.JournalRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired //dependancy injection u can use it anywhere in the class
    private JournalRepo journalrepo;

    public void saveEntry(JournalEntity journalentity) {

    journalrepo.save(journalentity);
    }
    public List<JournalEntity> getAll(){
        return journalrepo.findAll();
    }
    public Optional<JournalEntity> findById(ObjectId id){
        return journalrepo.findById(id);
    }

    public void deleteById(ObjectId id) {
        journalrepo.deleteById(id);
    }
}
//controller-->service-->repo