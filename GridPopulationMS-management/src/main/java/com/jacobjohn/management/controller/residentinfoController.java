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
import com.jacobjohn.management.domain.residentinfo;
import com.jacobjohn.management.service.IresidentinfoService;
import com.jacobjohn.common.utils.poi.ExcelUtil;
import com.jacobjohn.common.core.page.TableDataInfo;

/**
 * 居民信息Controller
 * 
 * @author JacobJohn
 * @date 2023-03-04
 */
@RestController
@RequestMapping("/management/residentinfo")
public class residentinfoController extends BaseController
{
    @Autowired
    private IresidentinfoService residentinfoService;

    /**
     * 查询居民信息列表
     */
    @PreAuthorize("@ss.hasPermi('management:residentinfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(residentinfo residentinfo)
    {
        startPage();
        List<residentinfo> list = residentinfoService.selectresidentinfoList(residentinfo);
        return getDataTable(list);
    }

    /**
     * 导出居民信息列表
     */
    @PreAuthorize("@ss.hasPermi('management:residentinfo:export')")
    @Log(title = "居民信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, residentinfo residentinfo)
    {
        List<residentinfo> list = residentinfoService.selectresidentinfoList(residentinfo);
        ExcelUtil<residentinfo> util = new ExcelUtil<residentinfo>(residentinfo.class);
        util.exportExcel(response, list, "居民信息数据");
    }

    /**
     * 获取居民信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('management:residentinfo:query')")
    @GetMapping(value = "/{idcardInfo}")
    public AjaxResult getInfo(@PathVariable("idcardInfo") String idcardInfo)
    {
        return success(residentinfoService.selectresidentinfoByIdcardInfo(idcardInfo));
    }

    /**
     * 新增居民信息
     */
    @PreAuthorize("@ss.hasPermi('management:residentinfo:add')")
    @Log(title = "居民信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody residentinfo residentinfo)
    {
        return toAjax(residentinfoService.insertresidentinfo(residentinfo));
    }

    /**
     * 修改居民信息
     */
    @PreAuthorize("@ss.hasPermi('management:residentinfo:edit')")
    @Log(title = "居民信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody residentinfo residentinfo)
    {
        return toAjax(residentinfoService.updateresidentinfo(residentinfo));
    }

    /**
     * 删除居民信息
     */
    @PreAuthorize("@ss.hasPermi('management:residentinfo:remove')")
    @Log(title = "居民信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{idcardInfos}")
    public AjaxResult remove(@PathVariable String[] idcardInfos)
    {
        return toAjax(residentinfoService.deleteresidentinfoByIdcardInfos(idcardInfos));
    }
}
