package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AparApartmentMapper;
import com.ruoyi.system.domain.AparApartment;
import com.ruoyi.system.service.IAparApartmentService;

/**
 * 公寓Service业务层处理
 * 
 * @author clp
 * @date 2024-06-11
 */
@Service
public class AparApartmentServiceImpl implements IAparApartmentService 
{
    @Autowired
    private AparApartmentMapper aparApartmentMapper;

    /**
     * 查询公寓
     * 
     * @param id 公寓主键
     * @return 公寓
     */
    @Override
    public AparApartment selectAparApartmentById(Long id)
    {
        return aparApartmentMapper.selectAparApartmentById(id);
    }

    /**
     * 查询公寓列表
     * 
     * @param aparApartment 公寓
     * @return 公寓
     */
    @Override
    public List<AparApartment> selectAparApartmentList(AparApartment aparApartment)
    {
        return aparApartmentMapper.selectAparApartmentList(aparApartment);
    }

    /**
     * 新增公寓
     * 
     * @param aparApartment 公寓
     * @return 结果
     */
    @Override
    public int insertAparApartment(AparApartment aparApartment)
    {
        aparApartment.setCreateTime(DateUtils.getNowDate());
        return aparApartmentMapper.insertAparApartment(aparApartment);
    }

    /**
     * 修改公寓
     * 
     * @param aparApartment 公寓
     * @return 结果
     */
    @Override
    public int updateAparApartment(AparApartment aparApartment)
    {
        aparApartment.setUpdateTime(DateUtils.getNowDate());
        return aparApartmentMapper.updateAparApartment(aparApartment);
    }

    /**
     * 批量删除公寓
     * 
     * @param ids 需要删除的公寓主键
     * @return 结果
     */
    @Override
    public int deleteAparApartmentByIds(Long[] ids)
    {
        return aparApartmentMapper.deleteAparApartmentByIds(ids);
    }

    /**
     * 删除公寓信息
     * 
     * @param id 公寓主键
     * @return 结果
     */
    @Override
    public int deleteAparApartmentById(Long id)
    {
        return aparApartmentMapper.deleteAparApartmentById(id);
    }
}
