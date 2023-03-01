/*
 * @Title: project
 * @Description: TODO
 * @author MI
 * @date 2022/8/26 8:42
 * @version
 */
package com.imooc.project.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * @author MI
 * @ClassName: SexEnum
 * @Description: TODO
 * @date 2022/8/26 8:42
 */

@Getter
public enum SexEnum {
    MALE(1, "男"), FEMALE(2, "女");

    SexEnum(Integer sex, String sexName) {
        this.sex = sex;
        this.sexName = sexName;
    }
    @EnumValue
    private final Integer sex;

    private final String sexName;

}
