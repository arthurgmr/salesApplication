package io.github.arthurgmr.domain.repository;

import java.util.Optional;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import io.github.arthurgmr.domain.entity.UserEntity;

@DataJpaTest
public class IUserRepositoryTest {

    @Autowired
    private IUserRepository underTest;
    
    @Test
    void testFindByLogin() {
        //given        
        UserEntity user = new UserEntity("testLogin", "passwordTest", false);
        underTest.save(user);

        //when
        Optional<UserEntity> userExpected = underTest.findByLogin(user.getLogin());

        //then
        AssertionsForClassTypes.assertThat(userExpected).hasValue(user);
    }

    
}
