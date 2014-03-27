package com.nextinstruction.resources;

import com.nextinstruction.model.PersonBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Path("/person")
@Singleton
public class PersonResource {
    private Logger LOG = LoggerFactory.getLogger(PersonResource.class);

    public PersonResource() {
        LOG.info("PersonResource created!");
    }

    @Path("person.json")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PersonBean personJSON() {
        final PersonBean personBean = new PersonBean();
        personBean.setAge(21);
        personBean.setName("John Doe");
        try {
            personBean.setDob(new SimpleDateFormat("yyyyMMdd").parse("19900112"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return personBean;
    }

    @Path("person.xml")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public PersonBean personXML() {
        final PersonBean personBean = new PersonBean();
        personBean.setAge(21);
        personBean.setName("John Doe");
        try {
            personBean.setDob(new SimpleDateFormat("yyyyMMdd").parse("19900112"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return personBean;
    }
}
