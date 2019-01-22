package service;

import api.IssueRepository;
import entities.Issue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IssueService implements IssueRepository {

    @NotNull
    private IssueRepository issueRepository;

    @Autowired
    public void setIssueRepository(@NotNull final IssueRepository issueRepository){
        this.issueRepository = issueRepository;
    }

    @Nullable
    private static final Logger logger = LogManager.getLogger(IssueService.class);

    @NotNull
    private Map<String, Issue> issues = new LinkedHashMap<>();

    public IssueService(){
    }

    @NotNull
    public Collection<Issue> findAll() {
        return issues.values();
    }

    public Issue findById(@Nullable final String id) {
        if (id == null || id.isEmpty()) return null;
        return issues.get(id);
    }

    @NotNull
    public Collection<Issue> findByIds(@Nullable final Collection<String> ids) {
        if (ids == null || ids.isEmpty()) return Collections.emptySet();
        @NotNull final Collection<Issue> result = new LinkedHashSet<>();
        for (@Nullable final String id : ids) {
            if (id == null || id.isEmpty()) continue;
            final Issue issue = findById(id);
            if (issue == null) continue;
            result.add(issue);
        }
        return result;
    }

    @Nullable
    public Issue merge(@Nullable final Issue issue) {
        if (issue == null) return null;
        @Nullable final String id = issue.getId();
        if (id == null || id.isEmpty()) return null;
        issues.put(id, issue);
        return issue;
    }

    @Nullable
    public Collection<Issue> merge(@Nullable final Collection<Issue> issues) {
        if (issues == null || issues.isEmpty()) Collections.emptySet();
        @NotNull final Collection<Issue> result = new LinkedHashSet<>();
        for (@Nullable final Issue issue : issues) {
            if (issue == null) continue;
            result.add(merge(issue));
        }
        return result;
    }

    public void removeById(@Nullable final String id){
        if (id == null || id.isEmpty()) return;
        if(!issues.containsKey(id)) return;
        issues.remove(id);
    }

    public void removeByIds(@Nullable Collection<String> ids){
        if (ids == null || ids.isEmpty()) return;
        for(@Nullable final String id: ids) removeById(id);
    }

    public void remove(@Nullable final Collection<Issue> issues){
        if (issues == null || issues.isEmpty()) return;
        for(@Nullable final Issue issue: issues){
            if(issue == null) continue;
            removeById(issue.getId());
        }
    }

    public void removeAll(){issues.clear();}
}
