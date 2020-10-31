package cn.co.allttss.api.sercive.impl;


import cn.co.allttss.api.entity.User;
import cn.co.allttss.api.mapper.UserMapper;
import cn.co.allttss.api.sercive.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserMapper userDao;

    @Override
    public List<User> getAllUser() {
        logger.info("-------------------------");
        List<User> users = userDao.getAll();
        return users;
    }

    @Override
    public User getUser(String name) {
        User user = null;
        if (name != null && !"".equals(name)) {
            user = userDao.getUser(name);
        }
        return user;
    }

    @Override
    public void add(User user) {

        if (user.getName() != null & user.getId() != 0) {
            userDao.insert(user);
        } else {
        }

    }

    @Override
    public void updateByPrimaryKey(User user) {
        userDao.updateByPrimaryKey(user);
    }

    @Override
    public void delete(String id) {
        userDao.delete(id);
    }
}