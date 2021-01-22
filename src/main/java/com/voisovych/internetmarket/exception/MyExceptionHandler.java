package com.voisovych.internetmarket.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class MyExceptionHandler {

    Logger logger = LogManager.getLogger(MyExceptionHandler.class);

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleException() {

        logger.error("You gave an incorrect value for some field");
        return new ResponseEntity("You gave an incorrect value for some field", HttpStatus.BAD_REQUEST);
    }


//    @ExceptionHandler(value = {ApiRequestException.class})
//    public ResponseEntity<Object> handlerApiRequestException(ApiRequestException e) {
//        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
//
//        ApiException apiException = new ApiException(
//                e.getMessage(),
//                e,
//                badRequest,
//                ZonedDateTime.now(ZoneId.of("Z"))
//        );
//
//        return new ResponseEntity<>(apiException,badRequest);
//    }
}
