package com.codr.aws.util;

import com.codr.aws.dto.ApiResponse;
import com.codr.aws.exceptions.BadInputException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class AppUtils {

    public static <T> void validate(T t) throws BadInputException {
        Set<String> errors = new HashSet<>();
        if (t == null) {
            errors.add("Please pass valid input.");
        } else {
            Set<ConstraintViolation<T>> violations
                    = Validation.buildDefaultValidatorFactory().getValidator().validate(t);
            if (!violations.isEmpty()) {
                for (ConstraintViolation<T> c : violations) {
                    errors.add(c.getMessage());
                }
            }
        }
        if (!errors.isEmpty()) {
            throw new BadInputException(errors);
        }
    }

    public static Response buildResponse(ApiResponse resp) throws JsonProcessingException {
        Status status = Status.OK;
        if (resp == null || resp.isError()) {
            status = Status.INTERNAL_SERVER_ERROR;
        }
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(resp).replaceAll("'\'", "");
        return Response
                .status(status)
                .entity(str)
                .build();
    }

    public static void logEx(Logger logger, ApiResponse response, Throwable t, String message) {
        logger.error(message, t);
        response.setError(true);
        if (t instanceof BadInputException) {
            BadInputException be = (BadInputException) t;
            Set<String> errors = be.getErrors();
            if (errors != null && !errors.isEmpty()) {
                response.add("errors", errors);
            } else {
                response.add("error", be.getMessage());
            }
        } else {
            response.add("error", message);
        }
        t.printStackTrace();
    }

    public static Date parseSQLDateTime(String date) {
        return parse(date, "yyyy-MM-dd HH:mm:ss");
    }

    //FIXME: GEt rid of old java date class (user java 8 api)
    public static Date parse(String date, String pattern) {
        try {
            return new SimpleDateFormat(pattern).parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

}