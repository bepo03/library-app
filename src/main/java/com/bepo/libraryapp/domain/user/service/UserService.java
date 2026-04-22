package com.bepo.libraryapp.domain.user.service;

import com.bepo.libraryapp.domain.user.dto.request.UserCreateRequest;
import com.bepo.libraryapp.domain.user.dto.request.UserUpdateRequest;
import com.bepo.libraryapp.domain.user.dto.response.UserDeleteResponse;
import com.bepo.libraryapp.domain.user.dto.response.UserResponse;
import com.bepo.libraryapp.domain.user.entity.User;
import com.bepo.libraryapp.domain.user.exception.NameDuplicateException;
import com.bepo.libraryapp.domain.user.exception.UserNotFoundException;
import com.bepo.libraryapp.domain.user.mapper.UserMapper;
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
    private final UserMapper userMapper;

    public UserResponse saveUser(UserCreateRequest request) {
        log.info("새로운 회원 저장: {}", request.getName());
        User user = userMapper.toEntity(request);

        validateDuplicateUser(user);

        User savedUser = userRepository.save(user);
        log.info("회원 저장 성공: ID: {}", savedUser.getId());

        return userMapper.toResponse(savedUser);
    }

    public List<UserResponse> getUser() {
        List<User> users = userRepository.findAll();
        log.info("회원 조회: {}개", users.size());

        return users.stream()
                .map(user -> user.toResponse(user))
                .toList();
    }

    public UserResponse updateUser(UserUpdateRequest request) {
        User user = userRepository.findById(request.getId())
                .orElseThrow(() -> new UserNotFoundException(request.getId()));

        validateDuplicateUserForUpdate(request.getId(), request.getName());

        userMapper.updateEntity(request, user);

        User updatedUser = userRepository.save(user);

        return userMapper.toResponse(updatedUser);
    }

    public UserDeleteResponse deleteUser(String name) {
        User user = userRepository.findByName(name)
                .orElseThrow(() -> new UserNotFoundException(name));

        userRepository.delete(user);

        return UserDeleteResponse.of(name, true);
    }


    private void validateDuplicateUser(User user) {
        userRepository.findByName(user.getName())
                .ifPresent(foundUser -> {
                    throw new NameDuplicateException(foundUser.getName());
                });
    }

    private void validateDuplicateUserForUpdate(Long userId, String name) {
        userRepository.findByName(name)
                .filter(foundUser -> !foundUser.getId().equals(userId))
                .ifPresent(foundUser -> {
                    throw new NameDuplicateException(name);
                });
    }
}
