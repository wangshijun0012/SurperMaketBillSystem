package cn.onesdream.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * smbms_role
 * @author 
 */
@Data
public class Role implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 创建者
     */
    private Long createdBy;

    /**
     * 创建时间
     */
    private Date creationDate;

    /**
     * 修改者
     */
    private Long modifyBy;

    /**
     * 修改时间
     */
    private Date modifyDate;

    private static final long serialVersionUID = 1L;
}