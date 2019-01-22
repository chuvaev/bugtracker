
import entities.Issue;
import entities.Project;
import entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.UUID;


public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(final String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        logger.trace("Entering application.");
        Project project = context.getBean("projectEntity", Project.class);
        User user = context.getBean("userEntity", User.class);
        Issue issue = context.getBean("issueEntity", Issue.class);
        project.setId(UUID.randomUUID().toString());
        user.setId(UUID.randomUUID().toString());
        issue.setId(UUID.randomUUID().toString());
        project.setName("Project");
        project.setDescription("First Project");
        project.setUserId(user.getId());
        project.setIssueId(issue.getId());
        user.setLogin("John");
        user.setProjectId(project.getId());
        user.setIssueId(issue.getId());
        issue.setName("Error");
        issue.setProjectId(project.getId());
        issue.setUserId(user.getId());
        System.out.println(project.toString());
        System.out.println(user.toString());
        System.out.println(issue.toString());
        logger.trace("Exiting application.");
        context.close();
    }
}
