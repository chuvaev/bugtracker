package api;

import entities.Issue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface IssueRepository {

    @NotNull
    Collection<Issue> findAll();

    @Nullable
    Issue findById(@Nullable final String id);

    @NotNull
    Collection<Issue> findByIds(@Nullable Collection<String> ids);

    @Nullable
    Issue merge(@Nullable final Issue issue);

    @Nullable
    Collection<Issue> merge(@Nullable Collection<Issue> issues);

    void removeById(@Nullable final String id);
    void removeByIds(@Nullable final Collection<String> ids);
    void remove (@Nullable final Collection<Issue> issues);
    void removeAll();
}
