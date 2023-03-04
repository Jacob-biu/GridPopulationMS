package com.jacobjohn.management.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jacobjohn.common.annotation.Excel;
import com.jacobjohn.common.core.domain.BaseEntity;

/**
 * 居民信息对象 table_residentinfo
 * 
 * @author JacobJohn
 * @date 2023-03-04
 */
public class residentinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 姓名 */
    @Excel(name = "姓名")
    private String pName;

    /** 证件类型 */
    @Excel(name = "证件类型")
    private Integer idcardType;

    /** 证件号码 */
    @Excel(name = "证件号码")
    private String idcardInfo;

    /** 性别 */
    @Excel(name = "性别")
    private Integer pSex;

    /** 国籍 */
    @Excel(name = "国籍")
    private Integer pNation;

    /** 职业 */
    @Excel(name = "职业")
    private String pJob;

    /** 住址 */
    @Excel(name = "住址")
    private String pAddress;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String pPhone;

    /** 政治面貌 */
    @Excel(name = "政治面貌")
    private Integer pParty;

    /** 家庭人数 */
    @Excel(name = "家庭人数")
    private Integer pFamilynum;

    /** 是否高龄 */
    @Excel(name = "是否高龄")
    private Integer pOlder;

    /** 备注 */
    @Excel(name = "备注")
    private String pNotice;

    public void setpName(String pName) 
    {
        this.pName = pName;
    }

    public String getpName() 
    {
        return pName;
    }
    public void setIdcardType(Integer idcardType) 
    {
        this.idcardType = idcardType;
    }

    public Integer getIdcardType() 
    {
        return idcardType;
    }
    public void setIdcardInfo(String idcardInfo) 
    {
        this.idcardInfo = idcardInfo;
    }

    public String getIdcardInfo() 
    {
        return idcardInfo;
    }
    public void setpSex(Integer pSex) 
    {
        this.pSex = pSex;
    }

    public Integer getpSex() 
    {
        return pSex;
    }
    public void setpNation(Integer pNation) 
    {
        this.pNation = pNation;
    }

    public Integer getpNation() 
    {
        return pNation;
    }
    public void setpJob(String pJob) 
    {
        this.pJob = pJob;
    }

    public String getpJob() 
    {
        return pJob;
    }
    public void setpAddress(String pAddress) 
    {
        this.pAddress = pAddress;
    }

    public String getpAddress() 
    {
        return pAddress;
    }
    public void setpPhone(String pPhone) 
    {
        this.pPhone = pPhone;
    }

    public String getpPhone() 
    {
        return pPhone;
    }
    public void setpParty(Integer pParty) 
    {
        this.pParty = pParty;
    }

    public Integer getpParty() 
    {
        return pParty;
    }
    public void setpFamilynum(Integer pFamilynum) 
    {
        this.pFamilynum = pFamilynum;
    }

    public Integer getpFamilynum() 
    {
        return pFamilynum;
    }
    public void setpOlder(Integer pOlder) 
    {
        this.pOlder = pOlder;
    }

    public Integer getpOlder() 
    {
        return pOlder;
    }
    public void setpNotice(String pNotice) 
    {
        this.pNotice = pNotice;
    }

    public String getpNotice() 
    {
        return pNotice;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("pName", getpName())
            .append("idcardType", getIdcardType())
            .append("idcardInfo", getIdcardInfo())
            .append("pSex", getpSex())
            .append("pNation", getpNation())
            .append("pJob", getpJob())
            .append("pAddress", getpAddress())
            .append("pPhone", getpPhone())
            .append("pParty", getpParty())
            .append("pFamilynum", getpFamilynum())
            .append("pOlder", getpOlder())
            .append("pNotice", getpNotice())
            .toString();
    }
}
