package cn.co.allttss.api.controller;

import cn.co.allttss.api.entity.DeUser;
import cn.co.allttss.api.sercive.DeUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("apidemo/star")
public class DeUserController {

    @Resource
    private DeUserService userService;

    @RequestMapping(value = "/deluser", method = RequestMethod.GET)
    public List<DeUser> getAll() {
        List<DeUser> user = userService.getAllUser();
        return user;
    }

    @RequestMapping(value = "/deluser", method = RequestMethod.PUT)
    public String update(DeUser user) {
        String message;
        try {
            userService.updateByPrimaryKey(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }
}