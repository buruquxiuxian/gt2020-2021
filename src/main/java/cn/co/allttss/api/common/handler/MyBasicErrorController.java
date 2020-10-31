package cn.co.allttss.api.common.handler;


import cn.co.allttss.api.common.message.UnicomResponseEnums;
import cn.co.allttss.api.common.util.CommonUtil;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @create: 2020-08-01
 **/
@RestController
public class MyBasicErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @RequestMapping("/error")
    public Result<Object> handleError(HttpServletRequest request) {
        //获取statusCode:401,404,500
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == 404) {
            return new Result<>(false, null, UnicomResponseEnums.NOT_FOUND, CommonUtil.getTime());
        } else {
            return new Result<>(false, null, UnicomResponseEnums.METHOD_NOT_ALLOWED, CommonUtil.getTime());
        }

    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

}