package com.nextinstruction.resources;

import com.nextinstruction.exception.AppException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Path("/epochtime")
public class UnixTimestampResource {



    @Path("{year}/{month}/{day}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public long toEpochTime(@PathParam("year") String year,
                            @PathParam("month") String month,
                            @PathParam("day") String day) {

        final String dateString = String.format("%s%s%s",year,month,day);

        try {
            final Date date = new SimpleDateFormat("yyyyMMdd").parse(dateString);
            return date.getTime()/1000;
        } catch(ParseException e) {
            throw new AppException("Invalid Date Params: y="+year+",m="+month+",d="+day,e);
        }

    }
}
