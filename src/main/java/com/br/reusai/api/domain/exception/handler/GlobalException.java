package com.br.reusai.api.domain.exception.handler;

import com.br.reusai.api.domain.exception.BusinessException;
import com.br.reusai.api.domain.exception.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

@ControllerAdvice
@RestController
public class GlobalException {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ResponseError> handleException(Exception e, WebRequest request) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT.value())
                .body(new ResponseError(e.getMessage()));
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<ResponseError> handleImageUploadException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body(new ResponseError(e.getMessage()));
    }
}
