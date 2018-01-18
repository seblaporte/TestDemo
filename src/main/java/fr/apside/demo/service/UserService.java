package fr.apside.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.apside.demo.domain.Address;
import fr.apside.demo.domain.User;
import fr.apside.demo.exception.NoAddressRetrievedException;
import fr.apside.demo.exception.UserAlreadyExistsException;
import fr.apside.demo.mapper.UserMapper;
import fr.apside.demo.repository.UserRepository;
import fr.apside.demo.web.dto.UserDto;

@Service
public class UserService {
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	UserRepository userRepository;

	public static final String MESSAGE_USER_EXISTS = "Un utilisateur existe déjà avec l'adresse email %s.";
	
	/**
	 * Create user if not exists
	 * @param userDto
	 * @return User with id from repository
	 * @throws UserAlreadyExistsException if user already exists
	 * @throws NoAddressRetrievedException 
	 */
	public User createUserIfNotExists(UserDto userDto) throws UserAlreadyExistsException, NoAddressRetrievedException {
		
		User user = UserMapper.instance.userDtoToUser(userDto);

		if (isUserExists(user)) {
			throw new UserAlreadyExistsException(String.format(MESSAGE_USER_EXISTS, user.getEmail()));
		}
		
		Address addressFromRepository = addressService.createAddressIfNotExists(user.getAddress());
		user.setAddress(addressFromRepository);
		
		return userRepository.save(user);
	}
	
	/**
	 * Check if user exists
	 * @param user
	 * @return true is user exists
	 */
	public boolean isUserExists(User user) {
		
		return userRepository.findByEmail(user.getEmail()) != null ? true : false;
	}
}
