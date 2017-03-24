package io.crowdcode.flaschenlager.user.repository;

import io.crowdcode.flaschenlager.user.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser() throws Exception {
        User user = buildDefaultUser();

        userRepository.save(user);

        assertThat(user.getId(), is(notNullValue()));
    }

    @Test
    public void testFindByEmail() throws Exception {
        User user = entityManager.persist(buildDefaultUser());

        assertThat(userRepository.findByEmail("junit@crowdcode.io").getId(), is(user.getId()));
    }

    private User buildDefaultUser() {
        return new User()
                .setName("Name")
                .setPassword("secret")
                .setEmail("junit@crowdcode.io");
    }
}