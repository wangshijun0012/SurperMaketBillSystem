package cn.onesdream.pojo;

import java.io.Serializable;
import java.util.Date;

import cn.onesdream.util.Age;
import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

/**
 * smbms_user
 * @author 
 */
@Data
public class User implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 用户编码
     */
    private String userCode;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 性别（1:女、 2:男）
     */
    private Integer gender;

    /**
     * 出生日期
     */
    private Date birthday;
    @TableField(exist = false)
    private Integer age;
    /**
     * 手机
     */
    private String phone;

    /**
     * 地址
     */
    private String address;

    /**
     * 用户角色（取自角色表-角色id）
     */
    private Long userRole;

    /**
     * 创建者（userId）
     */
    private Long createdBy;

    /**
     * 创建时间
     */
    private Date creationDate;

    /**
     * 更新者（userId）
     */
    private Long modifyBy;

    /**
     * 更新时间
     */
    private Date modifyDate;
    @TableField(exist = false)
    private String roleName;
    @TableField(exist = false)
    private Role role;
    private static final long serialVersionUID = 1L;

    public void setBirthday(Date birthday) {
        int age = Age.getAgeByBirthday(birthday);
        this.age = age;
        this.birthday = birthday;
    }
}