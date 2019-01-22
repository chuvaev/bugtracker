package entities;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@Component
public class Project implements Serializable {

    @Nullable
    private static final Logger logger = LogManager.getLogger(Project.class);

    @NotNull
    private User user;

    @NotNull
    private Issue issue;

    @Autowired
    public void  setUser(@NotNull final User user){
        this.user = user;
    }

    @Autowired
    public void setIssue(@NotNull final Issue issue){
        this.issue = issue;
    }

    @NotNull
    private String id;

    @Nullable
    private String name;

    @Nullable
    private String description;

    @Nullable
    private String userId;

    @Nullable
    private String issueId;

    public Project(@NotNull final String name, @Nullable final String description){
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.userId = user.getId();
        this.issueId = issue.getId();
        logger.trace("New Project");
    }

    public String toString(){
        return "id: " + id + "\nname: " + name + "\ndescription: " + description + "\nuserId: " + userId + "\nissueId: " + issueId;

    }

}
