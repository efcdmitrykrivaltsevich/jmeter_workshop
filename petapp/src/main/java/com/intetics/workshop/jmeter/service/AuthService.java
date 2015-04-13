package com.intetics.workshop.jmeter.service;

import com.intetics.workshop.jmeter.domain.AuthToken;

public interface AuthService {

    AuthToken authenticate(String login, String password);

    boolean isValid(String token);
}
