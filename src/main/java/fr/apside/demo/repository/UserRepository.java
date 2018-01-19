package fr.apside.demo.repository;

import org.springframework.data.repository.CrudRepository;

import fr.apside.demo.domain.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    public User findByEmail(String email);

}
