package org.acme;

import org.acme.entities.Tweet;
import org.acme.services.StreamingService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RestController
@RequestMapping("/hello-resteasy")
public class GreetingResource {

    private static final Logger log = Logger.getLogger(GreetingResource.class);

    @Autowired
    StreamingService streamingService;

    @GetMapping(produces = "application/json")
    public Response list() {
        return Response.ok(streamingService.getTweets()).build();
    }
}