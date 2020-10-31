package cn.co.allttss.api.common.handler;

import cn.co.allttss.api.common.Exception.UnicomRuntimeException;
import cn.co.allttss.api.common.message.UnicomResponseEnums;
import cn.co.allttss.api.common.util.CommonUtil;
import org.apache.ibatis.binding.BindingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.net.BindException;
import java.net.ConnectException;
import java.sql.SQLException;

@RestControllerAdvice(annotations = {RestController.class, Controller.class})
public class SpringExceptionHandle {


    private static final Logger logger = LoggerFactory.getLogger(SpringExceptionHandle.class);

    /**
     * 请求参数类型错误异常的捕获
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = {BindException.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Result<String> badRequest(BindException e) {
        logger.error("occurs error when execute method ,message {}", e.getMessage());
        return new Result<>(false, UnicomResponseEnums.BAD_REQUEST, CommonUtil.getTime());
    }

    /**
     * 404错误异常的捕获
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = {NoHandlerFoundException.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Result<String> badRequestNotFound(BindException e) {
        logger.error("occurs error when execute method ,message {}", e.getMessage());
        return new Result<>(false, null, UnicomResponseEnums.NOT_FOUND, CommonUtil.getTime());
    }

    /**
     * mybatis未绑定异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BindingException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<String> mybatis(Exception e) {
        logger.error("occurs error when execute method ,message {}", e.getMessage());
        return new Result<>(false, UnicomResponseEnums.BOUND_STATEMENT_NOT_FOUNT, CommonUtil.getTime());
    }

    /**
     * 自定义异常的捕获
     * 自定义抛出异常。统一的在这里捕获返回JSON格式的友好提示。
     *
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(value = {UnicomRuntimeException.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public <T> Result<T> sendError(UnicomRuntimeException exception, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        logger.error("occurs error when execute url ={} ,message {}", requestURI, exception.getMsg());
        return new Result<>(false, exception.getCode(), exception.getMsg(), CommonUtil.getTime());
    }

    /**
     * 数据库操作出现异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = {SQLException.class, DataAccessException.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<String> systemError(Exception e) {
        logger.error("occurs error when execute method ,message {}", e.getMessage());
        return new Result<>(false, UnicomResponseEnums.DATABASE_ERROR, CommonUtil.getTime());
    }

    /**
     * 网络连接失败！
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = {ConnectException.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<String> connect(Exception e) {
        logger.error("occurs error when execute method ,message {}", e.getMessage());
        return new Result<>(false, UnicomResponseEnums.CONNECTION_ERROR, CommonUtil.getTime());
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public Result<String> notAllowed(Exception e) {
        logger.error("occurs error when execute method ,message {}", e.getMessage());
        return new Result<>(false, UnicomResponseEnums.METHOD_NOT_ALLOWED, CommonUtil.getTime());
    }
}
