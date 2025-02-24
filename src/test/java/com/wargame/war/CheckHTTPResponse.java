package com.wargame.war;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // Can start web server with random port number.
public class CheckHTTPResponse {
    @LocalServerPort // Get the random port and annotate it.
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate; // Tells spring to get a test rest template.

    @Test
    public void shouldPassIfStringMatches() {
        assertEquals("War!",
                testRestTemplate.getForObject("http://localhost:" + port + "/", String.class));
    }


}
