package fr.apside.demo.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.apside.demo.domain.Address;
import fr.apside.demo.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTestIT {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Test
    public void it_should_find_user() {

        User user = createUserExample();

        addressRepository.save(user.getAddress());
        userRepository.save(user);

        User foundUser = userRepository.findByEmail(user.getEmail());

        assertEquals(user.getName(), foundUser.getName());
        assertEquals(user.getSurname(), foundUser.getSurname());
        assertEquals(user.getEmail(), foundUser.getEmail());
        assertEquals(user.getAddress().getNumber(), foundUser.getAddress().getNumber());
    }

    private User createUserExample() {

        Address address = new Address();
        address.setNumber("2");
        address.setStreet("Place de la Gare");
        address.setPostcode("37700");
        address.setCity("Saint-Pierre-des-Corps");

        User user = new User();
        user.setName("Laporte");
        user.setSurname("Sébastien");
        user.setEmail("laporte.0209@apside-groupe.com");
        user.setAddress(address);

        return user;
    }
}
