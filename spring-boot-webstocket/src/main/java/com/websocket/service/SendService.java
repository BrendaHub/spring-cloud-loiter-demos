package com.websocket.service;

import javax.websocket.Session;
import java.io.IOException;
import java.util.List;

/**
 * @Fun Description //TODO
 * @Date 2020/6/4 00:19 04
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/
public interface SendService {
    /**
     * 给多个用户发送数据
     *
     * @param sessionList
     * @param message
     * @throws IOException
     */
    void sendBatch(List<Session> sessionList, String message) throws IOException;

    /**
     * 发送消息
     *
     * @param session
     * @param message
     * @throws IOException
     */
    void sendMessage(Session session, String message) throws IOException;
}
