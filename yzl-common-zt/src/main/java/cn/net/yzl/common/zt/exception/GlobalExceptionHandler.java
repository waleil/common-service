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
     * @param bindException
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public Object errorHandler(BindException bindException) {
        logger.error("发生参数校验异常,原因是:{}",bindException.getMessage());
        BindingResult result = bindException.getBindingResult();
        StringBuilder errorMsg = new StringBuilder();
        for (ObjectError error : result.getAllErrors()) {
            errorMsg.append(error.getDefaultMessage()).append(",");
        }
        errorMsg.delete(errorMsg.length() - 1, errorMsg.length());
        return ComResponse.fail(ResponseCodeEnums.PARAMS_TYPE_ERROR_CODE.getCode(),errorMsg.toString());
    }

    /**
     * 参数校验异常处理
     * @param methodArgumentNotValidException
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {

        logger.error("发生参数校验异常,原因是:{}",methodArgumentNotValidException.getMessage());
        StringBuilder errorMsg = new StringBuilder();

        BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
        for (FieldError error : bindingResult.getFieldErrors()) {
            errorMsg.append(error.getDefaultMessage()).append(",");
        }
        errorMsg.delete(errorMsg.length() - 1, errorMsg.length());
        return ComResponse.fail(ResponseCodeEnums.PARAMS_TYPE_ERROR_CODE.getCode(),errorMsg.toString());
    }

    /**
     * 全异常处理
     * @param exception
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object exceptionHandler(Exception exception){
        logger.error("发生业务异常,原因是:{}",exception.getMessage());
        return ComResponse.fail(ResponseCodeEnums.SERVICE_ERROR_CODE.getCode(),ResponseCodeEnums.SERVICE_ERROR_CODE.getMessage());
    }
}