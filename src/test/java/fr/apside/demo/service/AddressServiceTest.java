package fr.apside.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.apside.demo.domain.Address;
import fr.apside.demo.exception.NoAddressRetrievedException;
import fr.apside.demo.repository.AddressRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressServiceTest {

    @InjectMocks
    AddressService addressService;

    @Mock
    AddressRepository addressRepository;

    @Mock
    AddressDataGouvService addressDataGouvService;

    private Address addressExample;

    private String searchAddress;

    @Before
    public void init() {

        addressExample = new Address();
        addressExample.setNumber("2");
        addressExample.setStreet("Place de la Gare");
        addressExample.setPostcode("37700");
        addressExample.setCity("Saint-Pierre-des-Corps");

        searchAddress = String.format(
                "%s %s %s %s",
                addressExample.getNumber(),
                addressExample.getStreet(),
                addressExample.getPostcode(),
                addressExample.getCity());
    }

    @Test
    public void anti_pattern() throws NoAddressRetrievedException {
        // Creation
        when(
                addressRepository.findByNumberAndStreetAndPostcodeAndCity(
                        addressExample.getNumber(),
                        addressExample.getStreet(),
                        addressExample.getPostcode(),
                        addressExample.getCity())).thenReturn(null);
        when(addressDataGouvService.searchAddress(searchAddress)).thenReturn(addressExample);
        addressExample.setId(1);
        when(addressRepository.save(addressExample)).thenReturn(addressExample);

        Address result = addressService.createAddressIfNotExists(addressExample);

        assertEquals((Integer) 1, result.getId());
        assertEquals("2", result.getNumber());
        assertEquals("Place de la Gare", result.getStreet());
        assertEquals("37700", result.getPostcode());
        assertEquals("Saint-Pierre-des-Corps", result.getCity());

        // Recuperation existant
        when(
                addressRepository.findByNumberAndStreetAndPostcodeAndCity(
                        addressExample.getNumber(),
                        addressExample.getStreet(),
                        addressExample.getPostcode(),
                        addressExample.getCity())).thenReturn(addressExample);

        result = addressService.createAddressIfNotExists(addressExample);

        assertEquals("2", result.getNumber());
        assertEquals("Place de la Gare", result.getStreet());
        assertEquals("37700", result.getPostcode());
        assertEquals("Saint-Pierre-des-Corps", result.getCity());
    }

    @Test
    public void it_should_return_created_address() throws NoAddressRetrievedException {

        when(
                addressRepository.findByNumberAndStreetAndPostcodeAndCity(
                        addressExample.getNumber(),
                        addressExample.getStreet(),
                        addressExample.getPostcode(),
                        addressExample.getCity())).thenReturn(null);
        when(addressDataGouvService.searchAddress(searchAddress)).thenReturn(addressExample);
        addressExample.setId(1);
        when(addressRepository.save(addressExample)).thenReturn(addressExample);

        Address result = addressService.createAddressIfNotExists(addressExample);

        assertEquals((Integer) 1, result.getId());
        assertEquals("2", result.getNumber());
        assertEquals("Place de la Gare", result.getStreet());
        assertEquals("37700", result.getPostcode());
        assertEquals("Saint-Pierre-des-Corps", result.getCity());
    }

    @Test
    public void it_should_return_existing_address() throws NoAddressRetrievedException {

        when(
                addressRepository.findByNumberAndStreetAndPostcodeAndCity(
                        addressExample.getNumber(),
                        addressExample.getStreet(),
                        addressExample.getPostcode(),
                        addressExample.getCity())).thenReturn(addressExample);

        Address result = addressService.createAddressIfNotExists(addressExample);

        assertEquals("2", result.getNumber());
        assertEquals("Place de la Gare", result.getStreet());
        assertEquals("37700", result.getPostcode());
        assertEquals("Saint-Pierre-des-Corps", result.getCity());
    }

    @Test(expected = NoAddressRetrievedException.class)
    public void it_should_fail_with_wrong_address() throws NoAddressRetrievedException {

        Address addressInvalid = new Address();
        addressInvalid.setNumber("470");
        addressInvalid.setStreet("rue de Tintagel");
        addressInvalid.setPostcode("37000");
        addressInvalid.setCity("Logres");

        when(
                addressRepository.findByNumberAndStreetAndPostcodeAndCity(
                        addressInvalid.getNumber(),
                        addressInvalid.getStreet(),
                        addressInvalid.getPostcode(),
                        addressInvalid.getCity())).thenReturn(null);
        when(addressDataGouvService.searchAddress(ArgumentMatchers.any()))
        .thenThrow(new NoAddressRetrievedException("Adresse inconnue"));

        addressService.createAddressIfNotExists(addressInvalid);

        fail("should fail");
    }

}
