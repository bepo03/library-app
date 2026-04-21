package com.bepo.libraryapp.domain.user.service;

import com.bepo.libraryapp.domain.user.dto.request.UserCreateRequest;
import com.bepo.libraryapp.domain.user.dto.response.UserResponse;
import com.bepo.libraryapp.domain.user.entity.User;
import com.bepo.libraryapp.domain.user.exception.NameDuplicateException;
import com.bepo.libraryapp.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserResponse saveUser(UserCreateRequest request) {
        log.info("새로운 회원 저장: {}", request.getName());
        User user = request.toEntity();

        validateDuplicateUser(user);

        User savedUser = userRepository.save(user);
        log.info("회원 저장 성공: ID: {}", savedUser.getId());

        return savedUser.toResponse(savedUser);
    }

    public List<UserResponse> getUser() {
        List<User> users = userRepository.findAll();
        log.info("회원 조회: {}개", users.size());

        return users.stream()
                .map(user -> user.toResponse(user))
                .toList();
    }

    private void validateDuplicateUser(User newUser) {
        userRepository.findByName(newUser.getName())
                .ifPresent(user -> {
                    throw new NameDuplicateException(user.getName());
                });
    }
}
