package com.demo.io;

/**
 * @author developer.wang
 * @date 2020/3/24
 */
public interface CrazyItProtocol {

    /**
     * 定义协议字符串的长度
     */
    int PROTOCOL_LENGTH = 2;
    /**
     * 下面是一些协议字符串，服务器端和客户端交换的信息都应该在前、后添加这种特殊字符
     */
    String MSG_ROUND = "$";
    String USER_ROUND = "&";
    String LOGIN_SUCCESS = "1";
    String NAME_REP = "-1";
    String PRIVATE_ROUND = "⭐️";
    String SPLIT_SIGN = "✳";
}
