package com.belazy.basics.auth.model.vo;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * 用户信息
 * @author tangcp
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoVo implements Serializable {
    private String id;
    private String account;
    private String name;
    private String phone;
    private String nickname;
    private List<String> roles;
}
