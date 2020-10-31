package cn.co.allttss.api.sercive;


import cn.co.allttss.api.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUser();

    User getUser(String name);

    void add(User user);

    void updateByPrimaryKey(User user);

    void delete(String id);
}