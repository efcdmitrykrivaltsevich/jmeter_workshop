package com.intetics.workshop.jmeter.web;

import com.intetics.workshop.jmeter.domain.AuthToken;
import com.intetics.workshop.jmeter.service.AuthService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.util.Assert.notNull;

@RestController
public class AuthController {

    private static final Logger LOGGER = Logger.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/api/auth", produces = {MediaType.APPLICATION_XML_VALUE}, method = RequestMethod.POST)
    public AuthToken authenticate(
            @RequestParam("login") String login,
            @RequestParam("password") String password) {
        notNull(login);
        notNull(password);

        AuthToken authToken = authService.authenticate(login, password);
        LOGGER.info(String.format("Client '%s' received a token '%s'", login, authToken.getToken()));

        return authToken;
    }
}
