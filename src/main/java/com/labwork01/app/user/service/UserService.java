package com.labwork01.app.user.service;

import com.labwork01.app.configuration.jwt.JwtException;
import com.labwork01.app.configuration.jwt.JwtProvider;
import com.labwork01.app.user.controller.UserInfoDto;
import com.labwork01.app.user.controller.UserLoginDto;
import com.labwork01.app.user.model.User;
import com.labwork01.app.user.model.UserRole;
import com.labwork01.app.user.repository.UserRepository;
import com.labwork01.app.util.validation.ValidationException;
import com.labwork01.app.util.validation.ValidatorUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ValidatorUtil validatorUtil;
    private final JwtProvider jwtProvider;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       ValidatorUtil validatorUtil,
                       JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.validatorUtil = validatorUtil;
        this.jwtProvider = jwtProvider;
    }

    public Page<User> findAllPages(int page, int size) {
        return userRepository.findAll(PageRequest.of(page - 1, size, Sort.by("id").ascending()));
    }

    public User findByLogin(String login) {
        return userRepository.findOneByLoginIgnoreCase(login);
    }

    public User createUser(String login, String password, String passwordConfirm) {
        return createUser(login, password, passwordConfirm, UserRole.USER);
    }

    public User createUser(String login, String password, String passwordConfirm, UserRole role) {
        if (findByLogin(login) != null) {
            throw new UserExistsException(login);
        }
        final User user = new User(login, passwordEncoder.encode(password), role);
        validatorUtil.validate(user);
        if (!Objects.equals(password, passwordConfirm)) {
            throw new ValidationException("Passwords not equals");
        }
        return userRepository.save(user);
    }

    public UserInfoDto loginAndGetToken(UserLoginDto userDto) {
        final User user = findByLogin(userDto.getLogin());
        if (user == null) {
            throw new UserNotFoundException(userDto.getLogin());
        }
        if (!passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            throw new UserNotFoundException(user.getLogin());
        }
        return new UserInfoDto(jwtProvider.generateToken(user.getLogin()), user.getLogin(), user.getRole());
    }

    public UserDetails loadUserByToken(String token) throws UsernameNotFoundException {
        if (!jwtProvider.isTokenValid(token)) {
            throw new JwtException("Bad token");
        }
        final String userLogin = jwtProvider.getLoginFromToken(token)
                .orElseThrow(() -> new JwtException("Token is not contain Login"));
        return loadUserByUsername(userLogin);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User userEntity = findByLogin(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(
                userEntity.getLogin(), userEntity.getPassword(), Collections.singleton(userEntity.getRole()));
    }
}
