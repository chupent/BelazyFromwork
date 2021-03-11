package com.belazy.business.test.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author tangcp
 */
@Data
public class UserEntity  implements Serializable {
    private int age;
    private String username;
}
