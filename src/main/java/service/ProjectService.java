package service;

import api.ProjectRepository;
import entities.Project;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProjectService implements ProjectRepository {

    @NotNull
    private ProjectRepository projectRepository;

    @Autowired
    public void setProjectRepository(@NotNull final ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }

    @Nullable
    private static final Logger logger = LogManager.getLogger(Project.class);

    @NotNull
    private Map<String, Project> projects = new LinkedHashMap<>();


    @NotNull
    public Collection<Project> findAll() {
        return projects.values();
    }

    @Nullable
    public Project findById(@Nullable final String id) {
        if (id == null || id.isEmpty()) return null;
        return projects.get(id);
    }

    @NotNull
    public Collection<Project> findByIds(@Nullable final Collection<String> ids) {
        if (ids == null || ids.isEmpty()) return Collections.emptySet();
        @NotNull final Collection<Project> result = new LinkedHashSet<>();
        for (@Nullable final String id : ids) {
            if (id == null || id.isEmpty()) continue;
            final Project project = findById(id);
            if (project == null) continue;
            result.add(project);
        }
        return result;
    }

    @Nullable
    public Project merge(@Nullable final Project project) {
        if (project == null) return null;
        @Nullable final String id = project.getId();
        if (id == null || id.isEmpty()) return null;
        projects.put(id, project);
        return project;
    }

    @Nullable
    public Collection<Project> merge(@Nullable final Collection<Project> projects) {
        if (projects == null || projects.isEmpty()) Collections.emptySet();
        @NotNull final Collection<Project> result = new LinkedHashSet<>();
        for (@Nullable final Project project : projects) {
            if (project == null) continue;
            result.add(merge(project));
        }
        return result;
    }

    public void removeById(@Nullable final String id){
        if (id == null || id.isEmpty()) return;
        if(!projects.containsKey(id)) return;
        projects.remove(id);
    }

    public void removeByIds(@Nullable Collection<String> ids){
        if (ids == null || ids.isEmpty()) return;
        for(@Nullable final String id: ids) removeById(id);
    }

    public void remove(@Nullable final Collection<Project> projects){
        if (projects == null || projects.isEmpty()) return;
        for(@Nullable final Project project: projects){
            if(project == null) continue;
            removeById(project.getId());
        }
    }

    public void removeAll(){projects.clear();}
}
