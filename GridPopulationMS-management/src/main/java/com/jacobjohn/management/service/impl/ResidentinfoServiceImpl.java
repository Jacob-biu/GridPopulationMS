package com.jacobjohn.management.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jacobjohn.management.mapper.ResidentinfoMapper;
import com.jacobjohn.management.domain.Residentinfo;
import com.jacobjohn.management.service.IResidentinfoService;

/**
 * 居民信息Service业务层处理
 * 
 * @author JacobJohn
 * @date 2023-02-27
 */
@Service
public class ResidentinfoServiceImpl implements IResidentinfoService 
{
    @Autowired
    private ResidentinfoMapper residentinfoMapper;

    /**
     * 查询居民信息
     * 
     * @param idcardInfo 居民信息主键
     * @return 居民信息
     */
    @Override
    public Residentinfo selectResidentinfoByIdcardInfo(String idcardInfo)
    {
        return residentinfoMapper.selectResidentinfoByIdcardInfo(idcardInfo);
    }

    /**
     * 查询居民信息列表
     * 
     * @param residentinfo 居民信息
     * @return 居民信息
     */
    @Override
    public List<Residentinfo> selectResidentinfoList(Residentinfo residentinfo)
    {
        return residentinfoMapper.selectResidentinfoList(residentinfo);
    }

    /**
     * 新增居民信息
     * 
     * @param residentinfo 居民信息
     * @return 结果
     */
    @Override
    public int insertResidentinfo(Residentinfo residentinfo)
    {
        return residentinfoMapper.insertResidentinfo(residentinfo);
    }

    /**
     * 修改居民信息
     * 
     * @param residentinfo 居民信息
     * @return 结果
     */
    @Override
    public int updateResidentinfo(Residentinfo residentinfo)
    {
        return residentinfoMapper.updateResidentinfo(residentinfo);
    }

    /**
     * 批量删除居民信息
     * 
     * @param idcardInfos 需要删除的居民信息主键
     * @return 结果
     */
    @Override
    public int deleteResidentinfoByIdcardInfos(String[] idcardInfos)
    {
        return residentinfoMapper.deleteResidentinfoByIdcardInfos(idcardInfos);
    }

    /**
     * 删除居民信息信息
     * 
     * @param idcardInfo 居民信息主键
     * @return 结果
     */
    @Override
    public int deleteResidentinfoByIdcardInfo(String idcardInfo)
    {
        return residentinfoMapper.deleteResidentinfoByIdcardInfo(idcardInfo);
    }
}
