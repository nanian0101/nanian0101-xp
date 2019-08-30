package com.xp.ssm.mapper;

import com.xp.ssm.entity.Like;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LikeMapper {

    /**
     * 添加点赞详情信息
     * @param like 点赞信息
     * @return 受影响行数
     */
    Integer addLikeInfo(Like like);

    /**
     * 取消点赞
     * @param lid 日志id
     * @param uid 用户id
     * @return 受影响行数
     */
    @Delete("DELETE FROM likes WHERE lid=#{lid} AND uid=#{uid}")
    Integer removeLike(@Param("lid") Integer lid,@Param("uid") Integer uid);

    /**
     * 查询用户点过赞的日志
     * @param uid 用户id
     * @return 点过赞的日志集合
     */
    @Select("SELECT distinct lid FROM likes WHERE uid=#{uid}")
    List<Integer> lidByUid(Integer uid);
}
