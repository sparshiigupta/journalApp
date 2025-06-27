package net.engineeringdigest.journalApp.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Document(collection = "users")
@Data
@NoArgsConstructor(force = true) // ✅ fix for Jackson + @NonNull
@AllArgsConstructor
public class User {

    @Id
    private ObjectId id;
    @Indexed(unique = true)
    @NonNull
    private String username;
    private String email;
    private boolean sentimentAnalysisEnabled;
    private String password;
    @DBRef
    private List<JournalEntity> journalEntries = new ArrayList<>();
    private List<String> roles = new ArrayList<>();
}
