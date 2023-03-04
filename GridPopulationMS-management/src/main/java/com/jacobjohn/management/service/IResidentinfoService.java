package com.jacobjohn.management.service;

import java.util.List;
import com.jacobjohn.management.domain.Residentinfo;

/**
 * 居民信息Service接口
 * 
 * @author JacobJohn
 * @date 2023-02-27
 */
public interface IResidentinfoService 
{
    /**
     * 查询居民信息
     * 
     * @param idcardInfo 居民信息主键
     * @return 居民信息
     */
    public Residentinfo selectResidentinfoByIdcardInfo(String idcardInfo);

    /**
     * 查询居民信息列表
     * 
     * @param residentinfo 居民信息
     * @return 居民信息集合
     */
    public List<Residentinfo> selectResidentinfoList(Residentinfo residentinfo);

    /**
     * 新增居民信息
     * 
     * @param residentinfo 居民信息
     * @return 结果
     */
    public int insertResidentinfo(Residentinfo residentinfo);

    /**
     * 修改居民信息
     * 
     * @param residentinfo 居民信息
     * @return 结果
     */
    public int updateResidentinfo(Residentinfo residentinfo);

    /**
     * 批量删除居民信息
     * 
     * @param idcardInfos 需要删除的居民信息主键集合
     * @return 结果
     */
    public int deleteResidentinfoByIdcardInfos(String[] idcardInfos);

    /**
     * 删除居民信息信息
     * 
     * @param idcardInfo 居民信息主键
     * @return 结果
     */
    public int deleteResidentinfoByIdcardInfo(String idcardInfo);
}
