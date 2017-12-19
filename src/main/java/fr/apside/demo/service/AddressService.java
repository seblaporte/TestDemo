package fr.apside.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.apside.demo.domain.Address;
import fr.apside.demo.repository.AddressRepository;
import fr.apside.demo.util.AddressUtils;

@Service
public class AddressService {
	
	@Autowired
	AddressDataGouvService addressDataGouvService;
	
	@Autowired
	AddressRepository addressRepository;

	/**
	 * Create address if not exists
	 * @param address to create if not exists
	 * @return address to use
	 */
	public Address createAddressIfNotExists(Address address) {
		
		Address foundAddress = addressRepository.findByNumberAndStreetAndPostcodeAndCity(
				address.getNumber(),
				address.getStreet(),
				address.getPostcode(),
				address.getCity());
		
		if (foundAddress == null) {
			
			String searchAddress = AddressUtils.createInlineAddressFromObject(address);
			
			Address validatedAddress = addressDataGouvService.searchAddress(searchAddress);
			
			addressRepository.save(validatedAddress);
			
			return validatedAddress;
			
		} else {
			
			return foundAddress;
		}
		
	}
}
