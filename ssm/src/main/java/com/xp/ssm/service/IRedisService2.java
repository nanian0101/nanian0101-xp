package com.xp.ssm.service;

import com.xp.ssm.entity.User;

import java.util.List;

public interface IRedisService2 {
     /**
      * 取用户信息
      * @param key
      * @return
      */
     User getUser(String key);

     /**
      * 存用户信息
      * @param key
      */
     void setUser(String key,User user);


     /**list类型模拟购物任务队列*/
     /**
      * 付款成功后生成任务队列
      * @param carid
      */
     void listQueueInit(String carid);

     /**
      * 触发事件,
      * @param carid
      */
     void listQueueTouch(String carid);

     /**
      * 客户查询,我的快递到哪了
      * @param carid
      * @return
      */
     List<String> listQueueSucc(String carid);

     /**
      * 物流查询还有多少任务没完成
      * @param carid
      * @return
      */
     List<String> listQueueWait(String carid);
}
