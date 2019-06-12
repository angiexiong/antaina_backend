package com.antaina.exception;

import com.antaina.enums.BaseResult;
import com.antaina.resp.Message;
import com.antaina.resp.RespBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * 全局异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> error(Exception e) {
        log.error("异常信息打印:", e);
        return RespBuilder.build(BaseResult.ERROR, "服务器请求异常!");
    }

    /**
     * 业务异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> error(BusinessException e) {
        log.error("异常信息打印:", e);
        int code = e.getCode();
        String message = e.getMessage();
        Message<?> msg = new Message<>(code, message);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    /**
     * 参数异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ParameterCheckException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> error(ParameterCheckException e) {
        log.error("异常信息打印:", e);
        List<ObjectError> allErrors = e.getAllErrors(); // 拿到异常信息
        if (allErrors.size() > 0) {
            String defaultMessage = allErrors.get(0).getDefaultMessage();
            int code = 500;
            try {
                code = Integer.valueOf(defaultMessage);
            } catch (Exception ignored) {

            }
            return RespBuilder.build(new Message<>(code, "参数校验异常"));
        }
        return RespBuilder.build(new Message<>(BaseResult.ERROR));
    }

    /**
     * 获取异常信息, 拼成String
     *
     * @param errors
     * @return
     */
    private String getErrMessage(List<ObjectError> errors) {
        StringBuilder strBuilder = new StringBuilder();
        for (ObjectError error : errors) {
            log.error("code: {}, message: {}", error.getCode(), error.getDefaultMessage());
            strBuilder.append(error.getDefaultMessage()).append("\n");
        }
        return strBuilder.toString();
    }

}