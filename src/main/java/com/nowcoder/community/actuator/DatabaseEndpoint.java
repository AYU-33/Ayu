package com.nowcoder.community.actuator;/*
    @author AYU
    */

import com.nowcoder.community.util.CommunityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
@Endpoint(id = "database")
public class DatabaseEndpoint {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseEndpoint.class);

    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;

    @ReadOperation//get请求才能访问
    public String checkConnection() {
        try (
                Connection conn = dataSource.getConnection();//小括号里错误直接关闭
        ) {
            return CommunityUtil.getJsonString(0, "获取连接成功!");
        } catch (SQLException e) {
            logger.error("获取连接失败:" + e.getMessage());
            return CommunityUtil.getJsonString(1, "获取连接失败!");
        }
    }
}
