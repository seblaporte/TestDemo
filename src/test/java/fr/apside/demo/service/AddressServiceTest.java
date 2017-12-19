package fr.apside.demo.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.apside.demo.domain.Address;
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
		
		searchAddress = String.format("%s %s %s %s",
				addressExample.getNumber(),
				addressExample.getStreet(),
				addressExample.getPostcode(),
				addressExample.getCity());
	}
	
	@Test
	public void it_should_return_created_address() {
		
		when(addressRepository.findByNumberAndStreetAndPostcodeAndCity(
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
	public void it_should_return_existing_address() {
		
		when(addressRepository.findByNumberAndStreetAndPostcodeAndCity(
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
	
}
