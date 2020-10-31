package cn.co.allttss.api.entity;

import lombok.*;

import java.io.Serializable;

/**
 * @Description: java类作用描述
 * @Author: xxx
 * @Date: xxxx
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class DeUser implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
}