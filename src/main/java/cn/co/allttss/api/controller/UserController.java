package cn.co.allttss.api.controller;

import cn.co.allttss.api.common.Exception.UnicomRuntimeException;
import cn.co.allttss.api.common.message.UnicomResponseEnums;
import cn.co.allttss.api.entity.User;
import cn.co.allttss.api.sercive.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("apidemo/star")
public class UserController {
    @Resource
    private HttpServletRequest request;
    @Resource
    private UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<User> getAll() {
        List<User> user = userService.getAllUser();
        return user;
    }

    @RequestMapping(value = "/userName", method = RequestMethod.GET)
    public User getUser(String name) {

        User user = null;
        if (name != null & !"".equals(name)) {
            user = userService.getUser(name);
        }
        return user;
    }

    @RequestMapping(value = "user", method = RequestMethod.POST)
    public String add(User user) {
        String message;
        try {
            userService.add(user);
            message = "success";
        } catch (Exception e) {
            e.printStackTrace();
            message = "fail";
        }
        return message;
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public String update(User user) {
        String message;
        try {
            userService.updateByPrimaryKey(user);
            message = "success";
        } catch (Exception e) {
            e.printStackTrace();
            message = "fail";
        }
        return message;
    }

    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
    public String delete(String id) {
        String message;
        try {
            userService.delete(id);
            message = "success";
        } catch (Exception e) {
            e.printStackTrace();
            message = "fail";
        }
        return message;
    }

    @RequestMapping("/getException")
    public String DeException() {
        throw new UnicomRuntimeException(UnicomResponseEnums.BAD_REQUEST);
    }
}