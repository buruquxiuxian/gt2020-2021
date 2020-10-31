package cn.co.allttss.api.sercive.impl;


import cn.co.allttss.api.entity.DeUser;
import cn.co.allttss.api.mapper.DeUserMapper;
import cn.co.allttss.api.sercive.DeUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DeUserServiceImpl implements DeUserService {

    private static final Logger logger = LoggerFactory.getLogger(DeUserServiceImpl.class);

    @Resource
    private DeUserMapper userDao;

    @Override
    public List<DeUser> getAllUser() {
        List<DeUser> users = userDao.getAll();
        return users;
    }

    @Override
    public void updateByPrimaryKey(DeUser user) {
        userDao.updateByPrimaryKey(user);
    }

}