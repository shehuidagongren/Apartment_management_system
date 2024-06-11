package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.AparApartment;
import com.ruoyi.system.service.IAparApartmentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 公寓Controller
 * 
 * @author clp
 * @date 2024-06-11
 */
@RestController
@RequestMapping("/system/apartment")
public class AparApartmentController extends BaseController
{
    @Autowired
    private IAparApartmentService aparApartmentService;

    /**
     * 查询公寓列表
     */
    @PreAuthorize("@ss.hasPermi('system:apartment:list')")
    @GetMapping("/list")
    public TableDataInfo list(AparApartment aparApartment)
    {
        startPage();
        List<AparApartment> list = aparApartmentService.selectAparApartmentList(aparApartment);
        return getDataTable(list);
    }

    /**
     * 导出公寓列表
     */
    @PreAuthorize("@ss.hasPermi('system:apartment:export')")
    @Log(title = "公寓", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AparApartment aparApartment)
    {
        List<AparApartment> list = aparApartmentService.selectAparApartmentList(aparApartment);
        ExcelUtil<AparApartment> util = new ExcelUtil<AparApartment>(AparApartment.class);
        util.exportExcel(response, list, "公寓数据");
    }

    /**
     * 获取公寓详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:apartment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(aparApartmentService.selectAparApartmentById(id));
    }

    /**
     * 新增公寓
     */
    @PreAuthorize("@ss.hasPermi('system:apartment:add')")
    @Log(title = "公寓", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AparApartment aparApartment)
    {
        return toAjax(aparApartmentService.insertAparApartment(aparApartment));
    }

    /**
     * 修改公寓
     */
    @PreAuthorize("@ss.hasPermi('system:apartment:edit')")
    @Log(title = "公寓", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AparApartment aparApartment)
    {
        return toAjax(aparApartmentService.updateAparApartment(aparApartment));
    }

    /**
     * 删除公寓
     */
    @PreAuthorize("@ss.hasPermi('system:apartment:remove')")
    @Log(title = "公寓", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(aparApartmentService.deleteAparApartmentByIds(ids));
    }
}
