package service;

import api.UserRepository;
import entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UserRepository {

    @NotNull
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(@NotNull final UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Nullable
    private static final Logger logger = LogManager.getLogger(UserService.class);

    @NotNull
    private Map<String, User> users = new LinkedHashMap<>();

    @NotNull
    public Collection<User> findAll() {
        return users.values();
    }

    @Nullable
    public User findById(@Nullable final String id) {
        if (id == null || id.isEmpty()) return null;
        return users.get(id);
    }

    @NotNull
    public Collection<User> findByIds(@Nullable final Collection<String> ids) {
        if (ids == null || ids.isEmpty()) return Collections.emptySet();
        @NotNull final Collection<User> result = new LinkedHashSet<>();
        for (@Nullable final String id : ids) {
            if (id == null || id.isEmpty()) continue;
            final User user = findById(id);
            if (user == null) continue;
            result.add(user);
        }
        return result;
    }

    @Nullable
    public User merge(@Nullable final User user) {
        if (user == null) return null;
        @Nullable final String id = user.getId();
        if (id == null || id.isEmpty()) return null;
        users.put(id, user);
        return user;
    }

    @Nullable
    public Collection<User> merge(@Nullable final Collection<User> users) {
        if (users == null || users.isEmpty()) Collections.emptySet();
        @NotNull final Collection<User> result = new LinkedHashSet<>();
        for (@Nullable final User user : users) {
            if (user == null) continue;
            result.add(merge(user));
        }
        return result;
    }

    public void removeById(@Nullable final String id){
        if (id == null || id.isEmpty()) return;
        if(!users.containsKey(id)) return;
        users.remove(id);
    }

    public void removeByIds(@Nullable Collection<String> ids){
        if (ids == null || ids.isEmpty()) return;
        for(@Nullable final String id: ids) removeById(id);
    }

    public void remove(@Nullable final Collection<User> users){
        if (users == null || users.isEmpty()) return;
        for(@Nullable final User user: users){
            if(user == null) continue;
            removeById(user.getId());
        }
    }

    public void removeAll(){users.clear();}
}
