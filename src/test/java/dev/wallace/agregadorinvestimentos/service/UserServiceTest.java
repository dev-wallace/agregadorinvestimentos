package dev.wallace.agregadorinvestimentos.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
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

    @Captor
    private ArgumentCaptor<UUID> uuiduseArgumentCaptor;

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
   

    @Nested
    class getUserById{

        @Test
        @DisplayName("Should get user by id with success when optional is present")
        void shouldGetUserByIdWithSuccessWhenOptionalIsPresent() {

            //Arrange
            var user = new User(
                UUID.randomUUID(),
                "username",
                "email@email.com",
                "password",
                Instant.now(),
                null
        );

        doReturn(Optional.of( user)).when(userRepository).findById(uuiduseArgumentCaptor.capture());


          

            //Act
            var output= userService.getUserById(user.getUserId().toString());

            //Assert
            assertTrue(output.isPresent());
            assertEquals(user.getUserId(), uuiduseArgumentCaptor.getValue());

        }



        @Test
        @DisplayName("Should get user by id with success when optional is empty")
        void shouldGetUserByIdWithSuccessWhenOptionalIsEmpty() {

            //Arrange
            var userId = UUID.randomUUID();

        doReturn(Optional.empty()).when(userRepository).findById(uuiduseArgumentCaptor.capture());


          

            //Act
            var output= userService.getUserById(userId.toString());

            //Assert
            assertTrue(output.isEmpty());
            assertEquals(userId, uuiduseArgumentCaptor.getValue());

        }
        
    }

    @Nested
    class listUsers{

        @Test
        @DisplayName("Should return all users with success")
        void shouldReturnAllUserswithSuccess() {
            
        //arrange
          //Arrange
          var user = new User(
            UUID.randomUUID(),
            "username",
            "email@email.com",
            "password",
            Instant.now(),
            null
    );
            var userList = List.of(user);
        doReturn(userList)
        .when(userRepository)
        .findAll();

        //act
        var output = userService.listUsers();


        //assert
        assertNotNull(output);
        assertEquals(userList.size(), output.size());
        
            

          
     }
    }
   
   @Nested
    class deleteById{


        @Test
        @DisplayName("Should delete user with success")
        void shouldDeleteUserWitchSuccess() {
               

            doReturn(true)
            .when(userRepository)
            .existsById(uuiduseArgumentCaptor.capture());



        doNothing()
        .when(userRepository)
        .deleteById(uuiduseArgumentCaptor.capture());


          

            //Act

            var userId = UUID.randomUUID();
             userService.deleteById(userId.toString());

            //Assert
            var idList = uuiduseArgumentCaptor.getAllValues();

            assertEquals(userId,idList.get(0));
            assertEquals(userId, idList.get(1));
            
        
           verify(userRepository, times(1))
           .existsById(idList.get(0));

           verify(userRepository, times(1))
           .deleteById(idList.get(1));
        }
    }
    

}
