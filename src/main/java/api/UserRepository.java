package api;

import entities.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UserRepository {

    @NotNull
    Collection<User> findAll();

    @Nullable
    User findById(@Nullable final String id);

    @NotNull
    Collection<User> findByIds(@Nullable Collection<String> ids);

    @Nullable
    User merge(@Nullable final User user);

    @Nullable
    Collection<User> merge(@Nullable Collection<User> users);

    void removeById(@Nullable final String id);
    void removeByIds(@Nullable final Collection<String> ids);
    void remove (@Nullable final Collection<User> users);
    void removeAll();
}
