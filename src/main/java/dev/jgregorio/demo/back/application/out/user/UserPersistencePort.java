package dev.jgregorio.demo.back.application.out.user;

import dev.jgregorio.demo.back.domain.user.User;
import dev.jgregorio.demo.back.domain.user.UserSearch;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;

public interface UserPersistencePort {

  Optional<User> getByUsername(String username);

  List<User> search(UserSearch criteria, Sort sort);
}
