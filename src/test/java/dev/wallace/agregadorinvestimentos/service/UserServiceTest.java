package dev.wallace.agregadorinvestimentos.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import java.time.Instant;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.wallace.agregadorinvestimentos.controller.CreateUserDto;
import dev.wallace.agregadorinvestimentos.entity.User;
import dev.wallace.agregadorinvestimentos.repository.UserRepository;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks UserService userService;

    @Captor
    private ArgumentCaptor<User> useArgumentCaptor;

    @Nested
    class createUser {
        @Test
        @DisplayName("Should create a user with success")
        void shouldCreateAUserWithSuccess() {

                       var user = new User(
                    UUID.randomUUID(),
                    "username",
                    "email@email.com",
                    "password",
                    Instant.now(),
                    null
            );

            doReturn(user).when(userRepository).save(useArgumentCaptor.capture());
            var input = new CreateUserDto(
                "username",
                "email@email.com",
                "123"
        );

            var output = userService.createUser(input);

            var userCaptured = useArgumentCaptor.getValue();

            assertNotNull(output);
            assertEquals(input.username(), userCaptured.getUsername());
            assertEquals(input.email(), userCaptured.getEmail());
            assertEquals(input.password(), userCaptured.getPassword());




                    
            }
        }
        @Test
        @DisplayName("Should throw exception when error occurs")
        void shouldThrowExceptionWhenErrorOccurs() {

            doThrow(new RuntimeException()).when(userRepository).save(any());
            var input = new CreateUserDto(
                "username",
                "email@email.com",
                "123"
        );

        assertThrows(RuntimeException.class, 
        () -> userService.createUser(input));


           


            
        }
}
