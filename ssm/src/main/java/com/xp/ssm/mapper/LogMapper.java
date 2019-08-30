package com.xp.ssm.mapper;

import com.xp.ssm.entity.Log;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LogMapper {

    /**
     * 发表日志/博客
     * @param log 日志/博客
     * @return 受影响行数
     */
    Integer addLog(Log log);

    /**
     * 发表日志/博客数量
     * @param log 日志/博客
     * @return 受影响行数
     */
    @Select("SELECT COUNT(uid) FROM log WHERE uid=#{uid}")
    Integer countLog(Integer uid);

    /**
     * 查询我的所有博客
     * @return 我的博客
     */
    List<Log> findMyLog(@Param("uid") Integer uid,@Param("curr") Integer curr,@Param("limit") Integer limit);

    /**
     * 根据日志id查询日志信息
     * @param lid 日志id
     * @return 日志信息
     */
    @Select("SELECT * FROM log WHERE lid=#{lid}")
    Log findLogByLid(Integer lid);

    /**
     * 删除日志
     * @param lid
     * @return
     */
    @Delete("DELETE FROM log WHERE lid = #{lid}")
    Integer RemoveByLid(Integer lid);

    /**
     * 查询所有博客
     * @return 所有博客
     */
    List<Log> findAllLog(@Param("curr") Integer curr,@Param("limit") Integer limit);

    /**
     * 查询所有博客数量
     * @return 所有博客数量
     */
    @Select("SELECT COUNT(lid) FROM log")
    Integer findAllLogCount();

    /**
     * 点赞日志
     * @return
     */
    @Update("update log set num = num + 1 WHERE lid = #{lid}")
    Integer likeLog(Integer lid);

    /**
     * 取消点赞
     * @return
     */
    @Update("update log set num = num - 1 WHERE lid = #{lid}")
    Integer noLikeLog(Integer lid);
}
