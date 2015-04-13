package com.intetics.workshop.jmeter.service;


import com.google.common.base.Optional;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.intetics.workshop.jmeter.domain.AuthToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import static org.springframework.util.Assert.notNull;

@Service
public class SimpleCustomAuthService
        implements AuthService {

    private static final SecureRandom RANDOM = new SecureRandom();
    private Cache<String, String> knownTokens;
    private int tokensExpirationInSeconds;

    @Override
    public AuthToken authenticate(String login, String password) {
        notNull(login);
        notNull(password);

        String credentials = createCredentials(login, password);
        knownTokens.asMap().values().remove(credentials);

        AuthToken authToken = new AuthToken(
                new BigInteger(130, RANDOM).toString(32),
                createExpirationDate());

        knownTokens.put(authToken.getToken(), credentials);

        return authToken;
    }

    @Override
    public boolean isValid(String token) {
        notNull(token);
        return Optional.fromNullable(knownTokens.getIfPresent(token))
                .isPresent();
    }

    @Value("${tokens.expiration.seconds:60}")
    public void setTokensExpiration(int seconds) {
        this.tokensExpirationInSeconds = seconds;
        this.knownTokens = CacheBuilder.newBuilder()
                .expireAfterAccess(seconds, TimeUnit.SECONDS)
                .build();
    }

    private Calendar createExpirationDate() {
        Calendar expirationDate = Calendar.getInstance();
        expirationDate.add(Calendar.SECOND, this.tokensExpirationInSeconds);
        return expirationDate;
    }

    private String createCredentials(String login, String password) {
        return String.format("%s:%s", login, password);
    }
}
