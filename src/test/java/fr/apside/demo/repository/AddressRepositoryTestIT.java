package fr.apside.demo.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.apside.demo.domain.Address;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressRepositoryTestIT {

    @Autowired
    AddressRepository addressRepository;

    @Test
    public void it_should_find_address() {

        Address address = createAddressExample();
        addressRepository.save(address);

        Address foundAddress = addressRepository.findByNumberAndStreetAndPostcodeAndCity(
                address.getNumber(),
                address.getStreet(),
                address.getPostcode(),
                address.getCity());

        assertEquals(address.getNumber(), foundAddress.getNumber());
        assertEquals(address.getStreet(), foundAddress.getStreet());
        assertEquals(address.getPostcode(), foundAddress.getPostcode());
        assertEquals(address.getCity(), foundAddress.getCity());
    }

    private Address createAddressExample() {

        Address address = new Address();
        address.setNumber("2");
        address.setStreet("Place de la Gare");
        address.setPostcode("37700");
        address.setCity("Saint-Pierre-des-Corps");

        return address;
    }
}
