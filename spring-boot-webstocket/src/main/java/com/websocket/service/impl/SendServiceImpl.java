package com.websocket.service.impl;

import com.websocket.service.SendService;
import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.io.IOException;
import java.util.List;

/**
 * @Fun Description //TODO
 * @Date 2020/6/4 00:19 04
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/
@Service
public class SendServiceImpl implements SendService {
    @Override
    public void sendBatch(List<Session> sessionList, String message) throws IOException {
        for (Session session : sessionList) {
            sendMessage(session, message);
        }
    }

    @Override
    public void sendMessage(Session session, String message) throws IOException {
        session.getBasicRemote().sendText(message);
    }
}
