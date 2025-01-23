package dev.wallace.agregadorinvestimentos.service;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.wallace.agregadorinvestimentos.repository.UserRepository;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks UserService userService;


        @Nested
        class createUser{
            @Test
            void name(){

            }
        }
}
