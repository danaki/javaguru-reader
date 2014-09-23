package lv.javaguru.reader.datastore.repositories;

import lv.javaguru.reader.datastore.Datastore;
import lv.javaguru.reader.datastore.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = Datastore.class)

public class UserRepositoryTest {

    @Autowired UserRepository repository;

    final private User user1 = new User("Jack", "Bauer");
    final private User user2 = new User("Kim", "Bauer");
    final private User user3 = new User("David", "Palmer");
    final private User user4 = new User("Michelle", "Dessler");
    final private List<User> users = new ArrayList<>(Arrays.asList(user1, user2, user3, user4));

    @Before
    public void setUp() throws Exception {
        // save a couple of customers
        repository.save(users);
    }

    @Test
    public void userEntityCreation() throws Exception {
        final List<User> allUsers = (List<User>) repository.findAll();
        assertTrue(allUsers.containsAll(users));
    }

    @Test
    public void searchForUserByLastName() throws Exception {
        assertEquals(user3, repository.findByLastName("Palmer").get(0));
        assertEquals(2, repository.findByLastName("Bauer").size());
    }

    @Test
    public void deleteUser() {
        repository.delete(user3);
        final List<User> repositoryAll = (List<User>) repository.findAll();
        assertFalse(repositoryAll.containsAll(users));
    }
}