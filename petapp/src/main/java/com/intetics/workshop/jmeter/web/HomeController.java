package com.intetics.workshop.jmeter.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return  "******************************************************\n" +
                "**                      PET APP                     **\n" +
                "******************************************************\n" +
                "Authentication:    /api/auth (POST login & password)\n" +
                "Search:            /api/search\n" +
                "                       &keywords='bla-bla-bla'\n" +
                "                       &location=<ID> (optional)\n" +
                "                       &page=<NUM> (optional, 1..100)\n" +
                "Defaults:\n" +
                "-Dtokens.expiration.seconds=60\n" +
                "-Dsearch.throttle.min=300\n" +
                "-Dsearch.throttle.max:2000";
    }

}
