package api;

import entities.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ProjectRepository {

    @NotNull
    Collection<Project>findAll();

    @Nullable
    Project findById(@Nullable final String id);

    @NotNull
    Collection<Project> findByIds(@Nullable Collection<String> ids);

    @Nullable
    Project merge(@Nullable final Project project);

    @Nullable
    Collection<Project> merge(@Nullable Collection<Project> projects);

    void removeById(@Nullable final String id);
    void removeByIds(@Nullable final Collection<String> ids);
    void remove (@Nullable final Collection<Project> projects);
    void removeAll();

}
