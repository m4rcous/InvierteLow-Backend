package com.example.inviertelow.platform.iam.application.queryservices;

import com.example.inviertelow.platform.iam.domain.model.aggregates.User;
import com.example.inviertelow.platform.iam.domain.model.queries.GetUserByIdQuery;
import com.example.inviertelow.platform.iam.infrastructure.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserQueryServiceImpl {

    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> handle(GetUserByIdQuery query) {
        return userRepository.findById(query.userId());
    }
}
