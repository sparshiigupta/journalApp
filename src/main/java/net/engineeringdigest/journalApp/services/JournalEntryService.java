package net.engineeringdigest.journalApp.services;

import net.engineeringdigest.journalApp.entity.JournalEntity;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntity journalEntity, String username) {
        User user = userRepository.findByUsername(username);
        journalEntity.setDate(LocalDateTime.now());
        JournalEntity saved = journalEntryRepository.save(journalEntity);
        user.getJournalEntries().add(saved);
        userRepository.save(user);
    }

    public void saveEntry(JournalEntity journalEntity) {
        journalEntryRepository.save(journalEntity);
    }

    public List<JournalEntity> getAll()
    {
        return journalEntryRepository.findAll();
    }
    
    public Optional<JournalEntity> findById(ObjectId journalId){
        return journalEntryRepository.findById(journalId);
    }

    @Transactional
    public boolean deleteById(ObjectId journalId, String userName){
        boolean removed = false;
        try{
            User user = userRepository.findByUsername(userName);
            removed = user.getJournalEntries().removeIf(j -> j.getId().equals(journalId));
            if(removed){
                userService.saveEntry(user);
                journalEntryRepository.deleteById(journalId);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException("Error deleting journal entry" + e.getMessage());
        }
        return removed;

    }
}
