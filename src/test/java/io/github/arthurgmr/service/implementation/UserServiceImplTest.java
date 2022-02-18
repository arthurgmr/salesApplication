package io.github.arthurgmr.service.implementation;

import static org.mockito.Mockito.verify;

import java.util.UUID;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.github.arthurgmr.domain.entity.UserEntity;
import io.github.arthurgmr.domain.repository.IUserRepository;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private IUserRepository userRepository;

    private UserServiceImpl underTest;
    
    @BeforeEach
    void setUp() {
        underTest = new UserServiceImpl(userRepository);
    }

    @Test
    void testSaveUser() {

        UserEntity user = new UserEntity("testLogin", "passwordTest", false);

        underTest.saveUser(user);

        ArgumentCaptor<UserEntity> userArgumentCaptor = ArgumentCaptor.forClass(UserEntity.class);

        verify(userRepository).save(userArgumentCaptor.capture());

        UserEntity capturedUser = userArgumentCaptor.getValue();

        AssertionsForClassTypes.assertThat(capturedUser).isEqualTo(user);

    }

    @Test
    @Disabled
    void testAuthenticate() {

    }

    @Test
    void testGetUser() {

        UUID uuid = UUID.randomUUID();

        System.out.println(uuid);

        BDDMockito.given(userRepository.existsById(uuid)).willReturn(true);

        underTest.getUser(uuid);

        verify(userRepository).findById(uuid);

    }

    @Test
    @Disabled
    void testLoadUserByUsername() {

    }

}
