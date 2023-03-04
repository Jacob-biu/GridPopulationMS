package com.jacobjohn.management.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jacobjohn.management.mapper.residentinfoMapper;
import com.jacobjohn.management.domain.residentinfo;
import com.jacobjohn.management.service.IresidentinfoService;

/**
 * 居民信息Service业务层处理
 * 
 * @author JacobJohn
 * @date 2023-03-04
 */
@Service
public class residentinfoServiceImpl implements IresidentinfoService 
{
    @Autowired
    private residentinfoMapper residentinfoMapper;

    /**
     * 查询居民信息
     * 
     * @param idcardInfo 居民信息主键
     * @return 居民信息
     */
    @Override
    public residentinfo selectresidentinfoByIdcardInfo(String idcardInfo)
    {
        return residentinfoMapper.selectresidentinfoByIdcardInfo(idcardInfo);
    }

    /**
     * 查询居民信息列表
     * 
     * @param residentinfo 居民信息
     * @return 居民信息
     */
    @Override
    public List<residentinfo> selectresidentinfoList(residentinfo residentinfo)
    {
        return residentinfoMapper.selectresidentinfoList(residentinfo);
    }

    /**
     * 新增居民信息
     * 
     * @param residentinfo 居民信息
     * @return 结果
     */
    @Override
    public int insertresidentinfo(residentinfo residentinfo)
    {
        return residentinfoMapper.insertresidentinfo(residentinfo);
    }

    /**
     * 修改居民信息
     * 
     * @param residentinfo 居民信息
     * @return 结果
     */
    @Override
    public int updateresidentinfo(residentinfo residentinfo)
    {
        return residentinfoMapper.updateresidentinfo(residentinfo);
    }

    /**
     * 批量删除居民信息
     * 
     * @param idcardInfos 需要删除的居民信息主键
     * @return 结果
     */
    @Override
    public int deleteresidentinfoByIdcardInfos(String[] idcardInfos)
    {
        return residentinfoMapper.deleteresidentinfoByIdcardInfos(idcardInfos);
    }

    /**
     * 删除居民信息信息
     * 
     * @param idcardInfo 居民信息主键
     * @return 结果
     */
    @Override
    public int deleteresidentinfoByIdcardInfo(String idcardInfo)
    {
        return residentinfoMapper.deleteresidentinfoByIdcardInfo(idcardInfo);
    }
}
