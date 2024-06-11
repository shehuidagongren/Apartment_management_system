package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 公寓对象 apar_apartment
 * 
 * @author clp
 * @date 2024-06-11
 */
public class AparApartment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 表单编号 */
    private Long id;

    /** 公寓编号 */
    @Excel(name = "公寓编号")
    private Long apartmentId;

    /** 单元编号 */
    @Excel(name = "单元编号")
    private Long unitId;

    /** 楼层编号 */
    @Excel(name = "楼层编号")
    private Long floodId;

    /** 房间编号 */
    @Excel(name = "房间编号")
    private Long roomId;

    /** 床位编号 */
    @Excel(name = "床位编号")
    private Long bedId;

    /** 弃用标志（0正常；1弃用2；危床） */
    @Excel(name = "弃用标志", readConverterExp = "0=正常；1弃用2；危床")
    private Long flag;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setApartmentId(Long apartmentId) 
    {
        this.apartmentId = apartmentId;
    }

    public Long getApartmentId() 
    {
        return apartmentId;
    }
    public void setUnitId(Long unitId) 
    {
        this.unitId = unitId;
    }

    public Long getUnitId() 
    {
        return unitId;
    }
    public void setFloodId(Long floodId) 
    {
        this.floodId = floodId;
    }

    public Long getFloodId() 
    {
        return floodId;
    }
    public void setRoomId(Long roomId) 
    {
        this.roomId = roomId;
    }

    public Long getRoomId() 
    {
        return roomId;
    }
    public void setBedId(Long bedId) 
    {
        this.bedId = bedId;
    }

    public Long getBedId() 
    {
        return bedId;
    }
    public void setFlag(Long flag) 
    {
        this.flag = flag;
    }

    public Long getFlag() 
    {
        return flag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("apartmentId", getApartmentId())
            .append("unitId", getUnitId())
            .append("floodId", getFloodId())
            .append("roomId", getRoomId())
            .append("bedId", getBedId())
            .append("flag", getFlag())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
