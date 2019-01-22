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
public class User implements Serializable {

    @Nullable
    private static final Logger logger = LogManager.getLogger(User.class);

    @NotNull
    private Project project;

    @NotNull
    private Issue issue;

    @Autowired
    public void  setProject(@NotNull final Project project){
        this.project = project;
    }

    @Autowired
    public void setIssue(@NotNull final Issue issue){
        this.issue = issue;
    }

    @Nullable
    private String id;

    @Nullable
    private String login;

    @Nullable
    private String projectId;

    @Nullable
    private String issueId;

    private boolean isActive = true;

    public User(@NotNull final String login){
        this.id = UUID.randomUUID().toString();
        this.login = login;
        this.projectId = project.getId();
        this.issueId = issue.getId();
        logger.trace("New User");
    }

    public String toString(){
        return "id: " + id + "\nlogin: " + login  + "\nprojectId: " + projectId + "\nissueId: " + issueId;
    }
}
