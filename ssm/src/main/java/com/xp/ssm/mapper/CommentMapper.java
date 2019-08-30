package com.xp.ssm.mapper;

import com.xp.ssm.entity.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface CommentMapper {

    /**
     * 添加评论
     * @param comment 评论信息
     * @return 受影响行数
     */
    @Insert("INSERT INTO comments (lid,uid,content,comm_user,comm_time) VALUES (#{lid},#{uid},#{content}," +
            "#{commUser},#{commTime})")
    Integer addComments(Comment comment);
}
