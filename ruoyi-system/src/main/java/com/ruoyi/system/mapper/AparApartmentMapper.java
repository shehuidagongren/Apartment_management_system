package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.AparApartment;

/**
 * 公寓Mapper接口
 * 
 * @author clp
 * @date 2024-06-11
 */
public interface AparApartmentMapper 
{
    /**
     * 查询公寓
     * 
     * @param id 公寓主键
     * @return 公寓
     */
    public AparApartment selectAparApartmentById(Long id);

    /**
     * 查询公寓列表
     * 
     * @param aparApartment 公寓
     * @return 公寓集合
     */
    public List<AparApartment> selectAparApartmentList(AparApartment aparApartment);

    /**
     * 新增公寓
     * 
     * @param aparApartment 公寓
     * @return 结果
     */
    public int insertAparApartment(AparApartment aparApartment);

    /**
     * 修改公寓
     * 
     * @param aparApartment 公寓
     * @return 结果
     */
    public int updateAparApartment(AparApartment aparApartment);

    /**
     * 删除公寓
     * 
     * @param id 公寓主键
     * @return 结果
     */
    public int deleteAparApartmentById(Long id);

    /**
     * 批量删除公寓
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAparApartmentByIds(Long[] ids);
}
