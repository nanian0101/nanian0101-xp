package com.xp.ssm.mapper.VO;

import com.xp.ssm.entity.VO.UserComment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserCommentMapper {

    /**
     * 查询指定日志的评论信息
     * @param lid 日志id
     * @return 品论信息
     */List<UserComment> findByLid(Integer lid);

}
