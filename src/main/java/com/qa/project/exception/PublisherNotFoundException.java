package com.qa.project.exception;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Publisher does not exist with that id!")
public class PublisherNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 3L;
}
