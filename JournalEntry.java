package com.example.Journal.App.Controller;

import com.example.Journal.App.Entity.JournalEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController //tells Spring this class will handle REST API requests and return data as a response.
@RequestMapping("/journal") //this will add mapping to the entire class
public class JournalEntry {
    private Map<Long, JournalEntity> journalentries=new HashMap<>();

    @GetMapping
    public List<JournalEntity> getAll(){
        return new ArrayList<>(journalentries.values());

    };
    @PostMapping
    public boolean createEntries(@RequestBody JournalEntity myEntry){
        journalentries.put(myEntry.getId(),myEntry);
        return true;
    }
    @GetMapping("id/{myid}")
    public JournalEntity getJournalEntryById(@PathVariable Long myid){
        return journalentries.get(myid);
    }
    @DeleteMapping("id/{myid}")
    public JournalEntity deleteJournalEntry(@PathVariable Long myid){
        return journalentries.remove(myid);
    }
    @PutMapping("id/{myid}")
    public JournalEntity updateJournalEntry(@PathVariable Long myid,@RequestBody JournalEntity myEntity){
        return journalentries.put(myid,myEntity);
    }

    ;

}
