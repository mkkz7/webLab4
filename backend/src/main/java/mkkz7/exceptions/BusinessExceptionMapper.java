package mkkz7.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.Map;

@Provider
public class BusinessExceptionMapper implements ExceptionMapper<BusinessException> {

    @Override
    public Response toResponse(BusinessException exception) {
        return Response
                .status(exception.getStatus())
                .entity(Map.of(
                        "success", false,
                        "message", exception.getMessage()
                ))
                .build();
    }
}
