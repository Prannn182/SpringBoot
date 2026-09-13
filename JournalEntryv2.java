package com.example.Journal.App.Controller;

import com.example.Journal.App.Entity.JournalEntity;
import com.example.Journal.App.repository.JournalRepo;
import com.example.Journal.App.services.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
import java.time.LocalDateTime;
import java.util.*;

@RestController //tells Spring this class will handle REST API requests and return data as a response.
@RequestMapping("/journal") //this will add mapping to the entire class
public class JournalEntryv2 {
    @Autowired
    private JournalEntryService journalEntryService;
    @GetMapping
    public List<JournalEntity> getAll(){
        return journalEntryService.getAll();
    };
    @PostMapping
    public ResponseEntity<JournalEntity> createEntries(@RequestBody JournalEntity myEntry){
       try{
           myEntry.setDate(LocalDateTime.now());
           journalEntryService.saveEntry(myEntry);
           return new ResponseEntity<>(HttpStatus.CREATED);
       } catch (Exception e) {
         return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }
    @GetMapping("id/{myid}")
    public ResponseEntity<JournalEntity> getJournalEntryById(@PathVariable ObjectId myid){

        Optional<JournalEntity> journalEntity=journalEntryService.findById(myid);
        if(journalEntity.isPresent()){
            return new ResponseEntity<>(journalEntity.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("id/{myid}")
    public ResponseEntity<?> deleteJournalEntry(@PathVariable ObjectId myid){
        journalEntryService.deleteById(myid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("id/{myid}")
    public ResponseEntity<JournalEntity> updateJournalEntry(@PathVariable ObjectId myid,@RequestBody JournalEntity newEntity){
        JournalEntity old=journalEntryService.findById(myid).orElse(null);
        if(old!=null){
            old.setTitle(newEntity.getTitle()!=null && !newEntity.getTitle().equals("")? newEntity.getTitle():old.getTitle());
            old.setContent(newEntity.getContent()!=null&& !newEntity.getContent().equals("")?newEntity.getContent(): old.getContent());
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    ;

}
