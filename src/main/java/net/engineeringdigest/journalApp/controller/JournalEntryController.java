package net.engineeringdigest.journalApp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import net.engineeringdigest.journalApp.entity.JournalEntity;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.services.JournalEntryService;
import net.engineeringdigest.journalApp.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
@Tag(name = "journal api")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<JournalEntity>> getAll() {
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         String username = authentication.getName();
         User user = userService.findByUsername(username);
         List<JournalEntity> all = user.getJournalEntries();

         if(all!=null && !all.isEmpty()){
             return new ResponseEntity<>(all, HttpStatus.OK);
         }
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<JournalEntity> createEntry(@RequestBody JournalEntity myEntry)
    {
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            journalEntryService.saveEntry(myEntry,username);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(myEntry, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("id/{myid}")
    public ResponseEntity<JournalEntity> getJournalEntryById(@PathVariable ObjectId myid)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        List<JournalEntity> journalEntries = user.getJournalEntries().stream().filter(j -> j.getId().equals(myid)).collect(Collectors.toList());
        if(journalEntries!=null && !journalEntries.isEmpty()){
            Optional<JournalEntity> journalEntry = journalEntryService.findById(myid);

            if(journalEntry.isPresent()){
                return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping ("id/{myid}")
    public ResponseEntity<?> deleteEntry(@PathVariable ObjectId myid)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        boolean removed = journalEntryService.deleteById(myid,username);
        if(removed)
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping ("id/{myid}")
    public ResponseEntity<?> deleteEntry(@PathVariable ObjectId myid,@RequestBody JournalEntity myEntry)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        List<JournalEntity> journalEntries = user.getJournalEntries().stream().filter(j -> j.getId().equals(myid)).collect(Collectors.toList());
        if(journalEntries!=null && !journalEntries.isEmpty()){
            Optional<JournalEntity> jEntry = journalEntryService.findById(myid);
            if(jEntry.isPresent()){
                JournalEntity old = jEntry.get();
                old.setTitle(myEntry.getTitle()!=null && !myEntry.getTitle().equals("") ?myEntry.getTitle():old.getTitle());
                old.setContent(myEntry.getContent()!=null && !myEntry.getContent().equals("") ?myEntry.getContent():old.getContent());
                journalEntryService.saveEntry(old);
                return new ResponseEntity<>(old, HttpStatus.OK);
            }

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
