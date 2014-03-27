package com.nextinstruction.resources;

import com.nextinstruction.model.Quote;
import com.nextinstruction.services.QuoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Quotes resource (exposed at "quotes" path)
 */
// Disabling this annotation takes the spring early instantiation out of the equation,
// instead Jersey will lazily create the instance the first time the resource is accessed
// but Spring will still be able to inject any needed dependencies from the Spring Context
//@Component
@Path("quotes")
@Singleton
public class QuoteResource {
    private Logger LOG = LoggerFactory.getLogger(QuoteResource.class);

    @Autowired private QuoteService quoteService;

    public QuoteResource() {
        LOG.info("QuoteResource Created!");
    }

    /**
     * Returns a quote as plain text.
     */
    @GET
    @Path("text")
    @Produces(MediaType.TEXT_PLAIN)
    public String quoteAsString() {
        return quoteService.getRandomQuote().toString();
    }

    /**
     * Example showing how our custom exception mapping works.
     * @return
     */
    @Path("error")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getError() {
        throw new IllegalArgumentException("Error!");
    }

    /**
     * JSON formatted quotes.
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Quote getQuoteJSON() {
        return quoteService.getRandomQuote();
    }

}
