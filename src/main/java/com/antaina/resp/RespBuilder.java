package com.antaina.resp;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RespBuilder {

    public static ResponseEntity<Object> build(IRet ret) {
        return build(ret, HttpStatus.OK);
    }

    public static ResponseEntity<Object> build(IRet ret, HttpStatus status) {
        return new ResponseEntity<>(new Message<>(ret), status);
    }

    public static ResponseEntity<Object> build(IRet ret, Object data) {
        return build(ret, data, HttpStatus.OK);
    }

    public static ResponseEntity<Object> build(IRet ret, Object data, HttpStatus status) {
        return new ResponseEntity<>(new Message<>(ret, data), status);
    }

    public static ResponseEntity<Object> build(Object body, HttpStatus status) {
        return new ResponseEntity<>(body, null, status);
    }

    public static ResponseEntity<Object> build(Message<?> message) {
        return build(message, HttpStatus.OK);
    }

    public static ResponseEntity<Object> build(Message<?> message, HttpStatus status) {
        return new ResponseEntity<>(message, status);
    }

    public static ResponseEntity<Object> build(Object data) {
        return build(data, HttpStatus.OK);
    }

    public static ResponseEntity<Object> build(HttpStatus status) {
        return new ResponseEntity<>(null, null, status);
    }

    public static ResponseEntity<Object> build(HttpHeaders headers, HttpStatus status) {
        return new ResponseEntity<>(null, headers, status);
    }

}

