package cn.co.allttss.api.mapper;

import cn.co.allttss.api.entity.DeUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DeUserMapper {

    /**
     * 查询出表中所有的人员
     *
     * @return
     */
    List<DeUser> getAll();


    /**
     * 根据ID去更新
     *
     * @param user
     */
    void updateByPrimaryKey(DeUser user);

}