package com.example.springMVC.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.example.springMVC.exceptions.NotFoundException;

import lombok.extern.slf4j.Slf4j;

/**
 * Created By Prince for Project RecipeApp on Apr 27, 2020
 *
 */
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleNumberFormat(Exception exception){
        log.error("Handling Number Format Exception");
        log.error(exception.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("400error");
        modelAndView.addObject("exception", exception);
        return modelAndView;
    }
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = NotFoundException.class)
	public ModelAndView handleNotFoundEx(Exception exception) {
		log.error("Handling not found exception");
        log.error(exception.getMessage());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("404error");
		modelAndView.addObject("exception", exception) ;
		return modelAndView;
	}
}


/*
//https://medium.com/@jovannypcg/understanding-springs-controlleradvice-cd96a364033f
@ControllerAdvice
public class GlobalExceptionHandler {
    // Provides handling for exceptions throughout this service. 
    @ExceptionHandler({ UserNotFoundException.class, ContentNotAllowedException.class })
    public final ResponseEntity<ApiError> handleException(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();

        if (ex instanceof UserNotFoundException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            UserNotFoundException unfe = (UserNotFoundException) ex;

            return handleUserNotFoundException(unfe, headers, status, request);
        } else if (ex instanceof ContentNotAllowedException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            ContentNotAllowedException cnae = (ContentNotAllowedException) ex;

            return handleContentNotAllowedException(cnae, headers, status, request);
        } else {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleExceptionInternal(ex, null, headers, status, request);
        }
    }

    // Customize the response for UserNotFoundException. 
    protected ResponseEntity<ApiError> handleUserNotFoundException(UserNotFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionInternal(ex, new ApiError(errors), headers, status, request);
    }

    // Customize the response for ContentNotAllowedException.
    protected ResponseEntity<ApiError> handleContentNotAllowedException(ContentNotAllowedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errorMessages = ex.getErrors()
                .stream()
                .map(contentError -> contentError.getObjectName() + " " + contentError.getDefaultMessage())
                .collect(Collectors.toList());

        return handleExceptionInternal(ex, new ApiError(errorMessages), headers, status, request);
    }

    // A single place to customize the response body of all Exception types. 
    protected ResponseEntity<ApiError> handleExceptionInternal(Exception ex, ApiError body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        return new ResponseEntity<>(body, headers, status);
    }
}
*/