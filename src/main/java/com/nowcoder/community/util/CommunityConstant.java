package com.nowcoder.community.util;/*
    @author AYU
    */

public interface CommunityConstant {
    //激活成功
    int ACTIVATION_SUCCESS = 0;
    //重复激活
    int ACTIVATION_REPEAT = 1;
    //激活失败
    int ACTIVATION_FAILURE = 2;
    //默认的登录凭证的超时时间
    int DEFAULT_EXPIRED_SECONDS = 3600 * 12;
    //记住状态的登录凭证的超时时间
    int REMEMBER_EXPIRED_SECONDS = 3600 * 24 * 100;
     //实体类型帖子
    int ENTITY_TYPE_POST = 1;
    //实体类型评论
    int ENTITY_TYPE_COMMENT = 2;
    //实体类型用户
    int ENTITY_TYPE_USER = 3;
    //评论
    String TOPIC_COMMENT = "comment";
    //点赞
    String TOPIC_LIKE = "like";
    //关注
    String TOPIC_FOLLOW = "follow";
    //发帖
    String TOPIC_PUBLISH = "publish";
    //删帖
    String TOPIC_DELETE = "delete";
    //分享
    String TOPIC_SHARE = "share";
    //系统用户ID
    int SYSTEM_USER_ID = 1;
    //普通用户
    String AUTHORITY_USER = "user";
    //管理员
    String AUTHORITY_ADMIN = "admin";
    //版主
    String AUTHORITY_MODERATOR = "moderator";
}
