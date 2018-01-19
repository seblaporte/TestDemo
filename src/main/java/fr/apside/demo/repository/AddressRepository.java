package fr.apside.demo.repository;

import org.springframework.data.repository.CrudRepository;

import fr.apside.demo.domain.Address;

public interface AddressRepository extends CrudRepository<Address, Integer> {

    public Address findByNumberAndStreetAndPostcodeAndCity(String number, String street, String postcode, String city);
}
