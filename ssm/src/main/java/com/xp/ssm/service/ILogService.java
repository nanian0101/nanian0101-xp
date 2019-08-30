package com.xp.ssm.service;

import com.xp.ssm.entity.Log;

import java.util.List;

public interface ILogService {

    /**
     * 发表日志/博客
     * @param log 日志/博客
     * @return 受影响行数
     */
    void insertLog(Log log);

    /**
     * 发表日志/博客数量
     * @param log 日志/博客
     * @return 受影响行数
     */
    Integer numLog(Integer uid);

    /**
     * 查询我的所有博客
     * @return 我的博客
     */
    List<Log> listMyLog(Integer uid,Integer curr,Integer limit);

    /**
     * 根据日志id查询日志信息
     * @param lid 日志id
     * @return 日志信息
     */
    Log selectLogByLid(Integer lid ,Integer uid);

    /**
     *      * 删除日志
     *      * @param lid
     *      * @return
     */
    void DeleteByLid(Integer lid);

    /**
     * 查询所有博客
     * @return 所有博客
     */
    List<Log> selectAllLog(Integer curr,Integer limit);

    /**
     * 查询所有博客数量
     * @return 所有博客数量
     */
    Integer selectAllLogCount();
}
