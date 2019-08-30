package com.xp.ssm.service.VO;

import com.xp.ssm.entity.VO.UserComment;

import java.util.List;

public interface IUserCommentService {

    /**
     * 查询指定日志的评论信息
     * @param lid 日志id
     * @return 品论信息
     */
    List<UserComment> listByLid(Integer lid);
}
