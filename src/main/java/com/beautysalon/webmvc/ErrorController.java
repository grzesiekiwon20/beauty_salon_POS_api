package com.beautysalon.webmvc;

import com.beautysalon.handler.ExceptionResponse;
import org.apache.tomcat.websocket.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import static com.beautysalon.handler.BusinessErrorCodes.*;

@ControllerAdvice
public class ErrorController {

    private static Logger logger = LoggerFactory.getLogger(ErrorController.class);

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String exception(final Throwable throwable, final Model model) {
        logger.error("Exception during execution of SpringSecurity application", throwable);
        String errorMessage = (throwable != null ? throwable.getMessage() : "Unknown error");
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("httpStatus", HttpStatus.INTERNAL_SERVER_ERROR);
        return "error";
    }
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String exception(final Model model) {
        logger.error("Exception during login process");
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setBusinessErrorCode(BAD_CREDENTIALS.getCode());
        exceptionResponse.setBusinessErrorDescription(BAD_CREDENTIALS.getDescription());
        exceptionResponse.setError("Login and / or Password is incorrect");
        String errorMessage = exceptionResponse.getError();
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("httpStatus", HttpStatus.UNAUTHORIZED);
        return "error";
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    private String handleUsernameNoFoundException(final Model model){
        logger.error("Username not found");
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setBusinessErrorCode(USERNAME_NOT_FOUND.getCode());
        exceptionResponse.setBusinessErrorDescription(USERNAME_NOT_FOUND.getDescription());
        exceptionResponse.setError("Incorrect username try again");
        String errorMessage = exceptionResponse.getError();
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("httpStatus", HttpStatus.UNAUTHORIZED);
        return "/user/login";
    }
//    @ExceptionHandler(value = AuthenticationException.class)
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    private String handleAuthenticationException(final Model model){
//        logger.error("Incorrect credentials");
//        ExceptionResponse exceptionResponse = new ExceptionResponse();
//        exceptionResponse.setBusinessErrorCode(USERNAME_NOT_FOUND.getCode());
//        exceptionResponse.setBusinessErrorDescription(USERNAME_NOT_FOUND.getDescription());
//        exceptionResponse.setError("Incorrect username try again");
//        String errorMessage = exceptionResponse.getError();
//        model.addAttribute("errorMessage", errorMessage);
//        model.addAttribute("httpStatus", HttpStatus.UNAUTHORIZED);
//        return "error";
//    }

}