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
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@Component
public class Issue implements Serializable {

    @Nullable
    private static final Logger logger = LogManager.getLogger(Issue.class);

    @NotNull
    private Project project;

    @NotNull
    private User user;

    @Autowired
    public void  setProject(@NotNull final Project project){
        this.project = project;
    }

    @Autowired
    public void setUser(@NotNull final User user){
        this.user = user;
    }

    @Nullable
    private String id;

    @Nullable
    private Date date;

    @Nullable
    private String name;

    @Nullable
    private String projectId;

    @Nullable
    private String userId;


    public Issue(@Nullable final String name){
        this.id = UUID.randomUUID().toString();
        this.date = new Date();
        this.name = name;
        this.projectId = project.getId();
        this.userId = user.getId();
        logger.trace("New Issue");
    }

    public String toString(){
        return "id: " + id + "\ndata: " + date + "\nname: " + name  + "\nprojectId: " + projectId + "\nuserId: " + userId;
    }
}
