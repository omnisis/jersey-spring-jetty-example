package com.nextinstruction.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Exception> {
    private Logger LOG = LoggerFactory.getLogger(GlobalExceptionMapper.class);

    public GlobalExceptionMapper() {
        LOG.info("GlobalExceptionMapper wired up");
    }

    @Override
    public Response toResponse(Exception e) {
        return Response.status(Response.Status.BAD_REQUEST)
                .type(MediaType.TEXT_PLAIN_TYPE)
                .entity(e.getMessage()).build();
    }
}
