package com.jlau.algsystem.config;

import com.jlau.algsystem.utils.CodeUtil;
import com.jlau.algsystem.utils.Result;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by cxr1205628673 on 2020/4/30.
 */
@ControllerAdvice
@ResponseBody
public class ExceptionHandler {
    private Logger log = LoggerFactory.getLogger(ExceptionHandler.class);
    @org.springframework.web.bind.annotation.ExceptionHandler({Exception.class})
    public Result except(Exception e){
        String errMessage = e.getMessage();
        System.err.println(errMessage);
        log.error(errMessage);
        return new Result(CodeUtil.COMMON_FAIL.getCode(), errMessage,"error");
    }
}
