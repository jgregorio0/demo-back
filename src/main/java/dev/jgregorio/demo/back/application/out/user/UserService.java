package dev.jgregorio.demo.back.application.out.user;

import dev.jgregorio.demo.back.domain.user.User;
import dev.jgregorio.demo.back.domain.user.UserSearch;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserPersistencePort userPersistence;

  // Starts a new transaction (REQUIRES_NEW) to break the circular dependency with JPA Auditing.
  @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
  public Optional<User> getByUsername(String username) {
    return userPersistence.getByUsername(username);
  }

  public List<User> search(UserSearch criteria, Sort sort) {
    return userPersistence.search(criteria, sort);
  }
}
