package com.intetics.workshop.jmeter.web;

import com.google.common.collect.Lists;
import com.intetics.workshop.jmeter.domain.Profile;
import com.intetics.workshop.jmeter.domain.ProfileFactory;
import com.intetics.workshop.jmeter.service.AuthService;
import com.intetics.workshop.jmeter.service.Throttler;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
public class SearchController {

    private static final Logger LOGGER = Logger.getLogger(SearchController.class);
    private static final long PAGE_MIN = 1L;
    private static final long PAGE_MAX = 100L;
    private static final long LOCATION_MIN = -1L;
    private static final long LOCATION_MAX = 10000L;

    @Autowired
    private AuthService authService;

    @Autowired
    private ProfileFactory profileFactory;

    @Value("${search.throttle.min:300}")
    private int minThrottle;

    @Value("${search.throttle.max:2000}")
    private int maxThrottle;

    @RequestMapping(
            value = "/api/search",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public SearchResult<Profile> search(
            @RequestHeader("token") String token,
            @RequestParam(value = "page", required = false, defaultValue = "1") Long page,
            @RequestParam(value = "location", required = false, defaultValue = "-1") Long locationId) {
        checkToken(token);
        checkPage(page);
        checkLocation(locationId);

        LOGGER.info(String.format("Search API requested. Token %s.", token));
        Throttler.throttle(minThrottle, maxThrottle);

        return new SearchResult<>(page, Lists.newArrayList(
                profileFactory.newProfile(),
                profileFactory.newProfile(),
                profileFactory.newProfile(),
                profileFactory.newProfile(),
                profileFactory.newProfile(),
                profileFactory.newProfile(),
                profileFactory.newProfile(),
                profileFactory.newProfile(),
                profileFactory.newProfile(),
                profileFactory.newProfile()));
    }

    private void checkLocation(Long locationId) {
        if (locationId < LOCATION_MIN || locationId > LOCATION_MAX) {
            LOGGER.warn(String.format("Invalid location %d requested.", locationId));
            throw new InvalidLocationException();
        }
    }

    private void checkPage(Long page) {
        if (page < PAGE_MIN || page > PAGE_MAX) {
            LOGGER.warn(String.format("Invalid page %d requested.", page));
            throw new InvalidPageException();
        }
    }

    private void checkToken(String token) {
        if (!authService.isValid(token)) {
            LOGGER.warn(String.format("Invalid token %s.", token));
            throw new InvalidTokenException();
        }
    }

    private class SearchResult<E> {
        Long page;
        List<E> items;

        private SearchResult(Long page, List<E> items) {
            this.page = page;
            this.items = items;
        }

        public Long getPage() {
            return page;
        }

        public Collection<E> getItems() {
            return items;
        }
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    private class InvalidTokenException extends RuntimeException {
        public InvalidTokenException() {
            super("TOKEN_IS_NOT_VALID");
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private class InvalidPageException extends RuntimeException {
        public InvalidPageException() {
            super("PAGE_IS_NOT_VALID");
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private class InvalidLocationException extends RuntimeException {
        public InvalidLocationException() {
            super("LOCATION_ID_IS_NOT_VALID");
        }
    }
}
