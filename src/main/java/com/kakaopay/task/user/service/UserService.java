package com.kakaopay.task.user.service;

import com.kakaopay.task.user.domain.User;
import com.kakaopay.task.user.service.exception.UserError;
import com.kakaopay.task.user.service.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserManager userManager;

    public void validateAllUserIdExists(Collection<Long> userIds) {

        List<User> users = userManager.findAllByIdIn(userIds);

        if (users.size() < userIds.size()) {
            Set<Long> foundUserIds = users.stream().map(User::getId).collect(Collectors.toSet());
            userIds.removeAll(foundUserIds);
            throw new UserException(UserError.NOT_FOUND, userIds.iterator().next());
        }

    }

}
