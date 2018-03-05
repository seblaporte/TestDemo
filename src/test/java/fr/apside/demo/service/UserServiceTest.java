package fr.apside.demo.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.apside.demo.domain.Address;
import fr.apside.demo.domain.User;
import fr.apside.demo.exception.UserAlreadyExistsException;
import fr.apside.demo.repository.UserRepository;
import fr.apside.demo.web.dto.AddressDto;
import fr.apside.demo.web.dto.UserDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    AddressService addressService;

    @Mock
    UserRepository userRepository;

    private UserDto user;

    @Before
    public void init() {

        AddressDto address = new AddressDto();
        address.setNumber("2");
        address.setStreet("Place de la Gare");
        address.setPostcode("37700");
        address.setCity("Saint-Pierre-des-Corps");

        user = new UserDto();
        user.setEmail("laporte.0209@apside-groupe.com");
        user.setName("Sébastien");
        user.setSurname("Laporte");
        user.setAddress(address);
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void it_should_not_create_user() throws Exception {

        when(userRepository.findByEmail("laporte.0209@apside-groupe.com")).thenReturn(new User());

        userService.createUserIfNotExists(user);
    }

    @Test
    public void it_should_create_user_without_error() throws Exception {

        when(userRepository.findByEmail("laporte.0209@apside-groupe.com")).thenReturn(null);
        when(addressService.createAddressIfNotExists(Mockito.any())).thenReturn(new Address());
        when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(new User());

        User userCreated = userService.createUserIfNotExists(user);

        assertNotNull(userCreated);
    }
}
