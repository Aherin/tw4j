package org.acme;

import org.jboss.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello-resteasy")
public class GreetingResource {

    private static final Logger log = Logger.getLogger(GreetingResource.class);

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        log.info("info");
        log.debug("debug");
        log.info(log.isDebugEnabled());
        log.debug("cristian");
        return "Hello RESTEasy";
    }
}