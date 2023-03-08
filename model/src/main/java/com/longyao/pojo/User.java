package com.longyao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("seer_user")
public class User {
    /**
     * 米米号
     */
    @TableId(type = IdType.AUTO)
    private int id;
    /**
     * 用户名
     */
    private String name;
    /**
     * 密码
     */
    private String password;
    /**
     * 拥有精灵
     */
    private String elves;
    /**
     * 背包精灵
     */
    private String bagElves;
    /**
     * 当前精灵
     */
    private String currentElve;
    /**
     * 当前精灵
     */
    private String isVIP;
}
