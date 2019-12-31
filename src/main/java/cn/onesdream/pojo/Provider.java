package cn.onesdream.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * smbms_provider
 * @author 
 */
@Data
public class Provider implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 供应商编码
     */
    private String proCode;

    /**
     * 供应商名称
     */
    private String proName;

    /**
     * 供应商详细描述
     */
    private String proDesc;

    /**
     * 供应商联系人
     */
    private String proContact;

    /**
     * 联系电话
     */
    private String proPhone;

    /**
     * 地址
     */
    private String proAddress;

    /**
     * 传真
     */
    private String proFax;

    /**
     * 创建者（userId）
     */
    private Long createdBy;

    /**
     * 创建时间
     */
    private Date creationDate;

    /**
     * 更新时间
     */
    private Date modifyDate;

    /**
     * 更新者（userId）
     */
    private Long modifyBy;

    private static final long serialVersionUID = 1L;
}