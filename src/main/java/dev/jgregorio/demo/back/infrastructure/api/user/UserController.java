package dev.jgregorio.demo.back.infrastructure.api.user;

import dev.jgregorio.demo.back.application.out.user.UserService;
import dev.jgregorio.demo.back.domain.user.User;
import dev.jgregorio.demo.back.domain.user.UserSearch;
import dev.jgregorio.demo.back.infrastructure.api.user.search.UserSearchApiMapper;
import dev.jgregorio.demo.back.infrastructure.api.user.search.UserSearchRequest;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  private final UserSearchApiMapper userSearchApiMapper;

  @GetMapping("/search")
  public ResponseEntity<List<UserResponse>> search(
      @Valid final UserSearchRequest searchRequest, final Sort sort) {
    UserSearch criteria = userSearchApiMapper.fromRequest(searchRequest);
    List<User> users = userService.search(criteria, sort);
    List<UserResponse> responseUsers = users.stream().map(userSearchApiMapper::toResponse).toList();
    return ResponseEntity.ok(responseUsers);
  }
}
