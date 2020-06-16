package com.websocket.controller;

import com.websocket.service.SendService;
import com.websocket.utils.SpringUtil;
import org.springframework.stereotype.Controller;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * @Fun Description //TODO
 * @Date 2020/6/4 00:16 04
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/
@ServerEndpoint(value = "/websocket")
@Controller
public class MyWebSocket {
    //静态变量，用来记录当前在线连接数。
    private static int onlineCount = 0;
    //注入Service只能使用这种方式
    private SendService sendService = SpringUtil.getBean(SendService.class);

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！ID是" + session.getId() + "    当前在线人数为" + getOnlineCount());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {

        subOnlineCount();           //在线数减1
        System.out.println("有一连接关闭！ID是：" + session.getId() + "   当前在线人数为" + getOnlineCount());
        try {
            session.close();
        } catch (IOException e) {
            System.out.println("关闭资源时出错！");
            e.printStackTrace();
        }
    }

    /**
     * 收到客户端消息后调用的方法
     */
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println("来自客户端的消息:" + message + "   ID是：" + session.getId());

        sendService.sendMessage(session, "服务器消息！");

    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误》》发生时间：" + System.currentTimeMillis() + "  ID是：" + session.getId());

        error.printStackTrace();
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        MyWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        MyWebSocket.onlineCount--;
    }
}
