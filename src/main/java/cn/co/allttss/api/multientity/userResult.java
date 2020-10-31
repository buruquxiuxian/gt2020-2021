package cn.co.allttss.api.multientity;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.annotations.Result;
import org.springframework.stereotype.Repository;

import java.util.List;

@Getter
@Setter
public class userResult {

    private int id;
    private String name;
    private int age;
    private String sex;
}
