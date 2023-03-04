package com.jacobjohn.management.controller;

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
import com.jacobjohn.common.annotation.Log;
import com.jacobjohn.common.core.controller.BaseController;
import com.jacobjohn.common.core.domain.AjaxResult;
import com.jacobjohn.common.enums.BusinessType;
import com.jacobjohn.management.domain.Residentinfo;
import com.jacobjohn.management.service.IResidentinfoService;
import com.jacobjohn.common.utils.poi.ExcelUtil;
import com.jacobjohn.common.core.page.TableDataInfo;

/**
 * 居民信息Controller
 * 
 * @author JacobJohn
 * @date 2023-02-27
 */
@RestController
@RequestMapping("/management/residentinfo")
public class ResidentinfoController extends BaseController
{
    @Autowired
    private IResidentinfoService residentinfoService;

    /**
     * 查询居民信息列表
     */
    @PreAuthorize("@ss.hasPermi('management:residentinfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(Residentinfo residentinfo)
    {
        startPage();
        List<Residentinfo> list = residentinfoService.selectResidentinfoList(residentinfo);
        return getDataTable(list);
    }

    /**
     * 导出居民信息列表
     */
    @PreAuthorize("@ss.hasPermi('management:residentinfo:export')")
    @Log(title = "居民信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Residentinfo residentinfo)
    {
        List<Residentinfo> list = residentinfoService.selectResidentinfoList(residentinfo);
        ExcelUtil<Residentinfo> util = new ExcelUtil<Residentinfo>(Residentinfo.class);
        util.exportExcel(response, list, "居民信息数据");
    }

    /**
     * 获取居民信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('management:residentinfo:query')")
    @GetMapping(value = "/{idcardInfo}")
    public AjaxResult getInfo(@PathVariable("idcardInfo") String idcardInfo)
    {
        return success(residentinfoService.selectResidentinfoByIdcardInfo(idcardInfo));
    }

    /**
     * 新增居民信息
     */
    @PreAuthorize("@ss.hasPermi('management:residentinfo:add')")
    @Log(title = "居民信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Residentinfo residentinfo)
    {
        return toAjax(residentinfoService.insertResidentinfo(residentinfo));
    }

    /**
     * 修改居民信息
     */
    @PreAuthorize("@ss.hasPermi('management:residentinfo:edit')")
    @Log(title = "居民信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Residentinfo residentinfo)
    {
        return toAjax(residentinfoService.updateResidentinfo(residentinfo));
    }

    /**
     * 删除居民信息
     */
    @PreAuthorize("@ss.hasPermi('management:residentinfo:remove')")
    @Log(title = "居民信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{idcardInfos}")
    public AjaxResult remove(@PathVariable String[] idcardInfos)
    {
        return toAjax(residentinfoService.deleteResidentinfoByIdcardInfos(idcardInfos));
    }
}
