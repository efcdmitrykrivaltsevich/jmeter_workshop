package com.intetics.workshop.jmeter.domain;

import java.io.Serializable;
import java.util.Calendar;

public class AuthToken
        implements Serializable {

    private String token;
    private Calendar expirationDate;

    public AuthToken(String token, Calendar expirationDate) {
        this.token = token;
        this.expirationDate = expirationDate;
    }

    public String getToken() {
        return token;
    }

    public Calendar getExpirationDate() {
        return expirationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthToken authToken = (AuthToken) o;

        if (!expirationDate.equals(authToken.expirationDate)) return false;
        if (!token.equals(authToken.token)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = token.hashCode();
        result = 31 * result + expirationDate.hashCode();
        return result;
    }
}
