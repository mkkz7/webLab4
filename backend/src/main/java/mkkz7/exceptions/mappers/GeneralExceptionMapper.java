package mkkz7.exceptions.mappers;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.Map;

@Provider
public class GeneralExceptionMapper implements ExceptionMapper<Throwable> {
    @Override
    public Response toResponse(Throwable exception){
        exception.printStackTrace();
        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(Map.of(
                        "success",false,
                        "message","Internal server error"
                ))
                .build();
    }
}
