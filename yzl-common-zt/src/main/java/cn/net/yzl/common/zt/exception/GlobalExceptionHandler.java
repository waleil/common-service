package cn.net.yzl.common.zt.exception;

import cn.net.yzl.common.entity.ComResponse;
import cn.net.yzl.common.enums.ResponseCodeEnums;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 *  全局异常类处理
 */
@ControllerAdvice
public class GlobalExceptionHandler  {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 参数绑定异常处理
     * @param BindException
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public Object errorHandler(BindException e) {
        logger.error("发生参数校验异常,原因是:{}"+e.getMessage());
        BindingResult result = e.getBindingResult();
        StringBuilder errorMsg = new StringBuilder();
        for (ObjectError error : result.getAllErrors()) {
            errorMsg.append(error.getDefaultMessage()).append(",");
        }
        errorMsg.delete(errorMsg.length() - 1, errorMsg.length());
        return ComResponse.fail(ResponseCodeEnums.PARAMS_TYPE_ERROR_CODE.getCode(),errorMsg.toString());
    }

    /**
     * 参数校验异常处理
     * @param MethodArgumentNotValidException
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        logger.error("发生参数校验异常,原因是:{}"+e.getMessage());
        StringBuilder errorMsg = new StringBuilder();

        BindingResult bindingResult = e.getBindingResult();
        for (FieldError error : bindingResult.getFieldErrors()) {
            errorMsg.append(error.getDefaultMessage()).append(",");
        }
        errorMsg.delete(errorMsg.length() - 1, errorMsg.length());
        return ComResponse.fail(ResponseCodeEnums.PARAMS_TYPE_ERROR_CODE.getCode(),errorMsg.toString());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object exceptionHandler(Exception e){
        logger.error("发生业务异常,原因是:{}"+e.getMessage());
        return ComResponse.fail(ResponseCodeEnums.SERVICE_ERROR_CODE.getCode(),ResponseCodeEnums.SERVICE_ERROR_CODE.getMessage());
    }
}