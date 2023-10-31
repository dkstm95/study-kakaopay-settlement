package com.kakaopay.task.user.service;

import com.kakaopay.task.user.domain.User;
import com.kakaopay.task.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserManager {

    private final UserRepository userRepository;

    public List<User> findAllByIdIn(Collection<Long> ids) {
        return userRepository.findAllByIdIn(ids);
    }

    public Map<Long, User> mapById(Collection<Long> ids) {
        return findAllByIdIn(ids).stream().collect(Collectors.toMap(User::getId, user -> user));
    }

}
