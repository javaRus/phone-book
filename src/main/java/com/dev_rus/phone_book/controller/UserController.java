package com.dev_rus.phone_book.controller;

import com.dev_rus.phone_book.domain.dto.NewUserDto;
import com.dev_rus.phone_book.domain.resource.UserResource;
import com.dev_rus.phone_book.service.user.UserService;
import com.dev_rus.phone_book.service.validator.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ruslan on 14.03.17.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final ValidationService validationService;

    @Autowired
    public UserController(final UserService userService,
                          final ValidationService validationService) {
        this.userService = userService;
        this.validationService = validationService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserResource> create(@RequestBody final NewUserDto newUserDto) {
        validationService.validate(newUserDto);
        final UserResource resource = userService.create(newUserDto);
        return ResponseEntity.ok(resource);
    }
}
