package dev.wallace.agregadorinvestimentos.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import dev.wallace.agregadorinvestimentos.controller.CreateUserDto;
import dev.wallace.agregadorinvestimentos.entity.User;
import dev.wallace.agregadorinvestimentos.repository.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UUID createUser(CreateUserDto createUserDto) {
     //retornando somente o id do usuário para seguranca paranao trafegar o objeto todo 

        // DTO -> ENTITY
        var entity = new User(
                UUID.randomUUID(),
                createUserDto.username(),
                createUserDto.email(),
                createUserDto.password(),
                Instant.now(),
                null);

        var userSaved = userRepository.save(entity);

        return userSaved.getUserId();
    }

    public Optional<User> getUserById(String userId) {

        return  userRepository.findById(UUID.fromString(userId));

       
    }

    public List<User> listUsers() {
        return userRepository.findAll();
    }
}
