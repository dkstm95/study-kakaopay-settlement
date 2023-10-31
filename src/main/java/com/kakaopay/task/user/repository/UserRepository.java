package com.kakaopay.task.user.repository;

import com.kakaopay.task.user.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAllByIdIn(Collection<Long> userIds);
}
