package cn.co.allttss.api.sercive;


import cn.co.allttss.api.entity.DeUser;

import java.util.List;

public interface DeUserService {

    List<DeUser> getAllUser();

    void updateByPrimaryKey(DeUser user);

}