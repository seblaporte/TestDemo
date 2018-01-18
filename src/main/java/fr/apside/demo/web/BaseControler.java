package fr.apside.demo.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import fr.apside.demo.exception.ClientErrorException;
import fr.apside.demo.exception.NoAddressRetrievedException;
import fr.apside.demo.exception.UserAlreadyExistsException;
import fr.apside.demo.web.dto.ErrorDto;

@ControllerAdvice
public class BaseControler {

    @ExceptionHandler({ UserAlreadyExistsException.class })
    public ResponseEntity<ErrorDto> userAlreadyExists(final ClientErrorException e) {
	return new ResponseEntity<ErrorDto>(new ErrorDto(e.getMessage(), ""), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({ NoAddressRetrievedException.class })
    public ResponseEntity<ErrorDto> noAddressRetrieved(final ClientErrorException e) {
	return new ResponseEntity<ErrorDto>(new ErrorDto(e.getMessage(), ""), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
