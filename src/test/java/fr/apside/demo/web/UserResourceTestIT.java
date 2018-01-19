package fr.apside.demo.web;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import fr.apside.demo.domain.Address;
import fr.apside.demo.domain.User;
import fr.apside.demo.repository.AddressRepository;
import fr.apside.demo.repository.UserRepository;
import fr.apside.demo.web.dto.UserDto;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserResourceTestIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Before
    public void init() {

        User user = new User();
        Address address = new Address();
        address.setNumber("2");
        address.setStreet("Place de la Gare");
        address.setPostcode("37700");
        address.setCity("Saint-Pierre-des-Corps");
        user.setAddress(address);
        user.setId(1);
        user.setEmail("laporte.0209@apside-groupe.com");
        user.setName("Laporte");
        user.setSurname("Sébastien");

        addressRepository.save(address);
        userRepository.save(user);
    }

    @Test
    public void it_should_retourn_ok_status_with_created_user_id() {

        ResponseEntity<UserDto> response = testRestTemplate.getForEntity("/user/1", UserDto.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void it_should_return_no_content_status() {

        ResponseEntity<UserDto> response = testRestTemplate.getForEntity("/user/2", UserDto.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}