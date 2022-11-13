package com.yytech.mpcdsepc.websocket;

import com.yytech.mpcdsepc.entity.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@Slf4j
@ServerEndpoint("/websocket/{id}")
public class TestWebSocket {
    private Session session;

    private String userId;

    //存着所有的websocket会话，这个目前的作用除了用来发全部消息和看在线人数以外感觉没用
    private static CopyOnWriteArraySet<TestWebSocket> webSockets =new CopyOnWriteArraySet<>();

    //存着每个用户以及其对应的会话，这个可以用来发送点对点消息
    private static ConcurrentHashMap<String,Session> sessionPool = new ConcurrentHashMap<String,Session>();

    @OnOpen
    public void onOpen(Session session, @PathParam(value="id")String userId) {
        try {
            this.session = session;
            this.userId = userId;
            //将本次会话存入websocket连接Set
            webSockets.add(this);
            //把加入的用户ID以及对应对话加入Map
            sessionPool.put(userId, session);
            this.sendAllMessage(Message.OnlineCount(webSockets.size()));
            System.out.println("【websocket消息】有新的连接，总数为:" + webSockets.size());
//            log.info("【websocket消息】有新的连接，总数为:"+webSockets.size());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(555);
        }
    }
    @OnMessage
    public void onMessage(String message) {
        log.info("【websocket消息】收到客户端消息:"+message);
    }


    @OnError
    public void onError(Session session, Throwable error) {

        log.error("用户错误,原因:"+error.getMessage());
        error.printStackTrace();
    }

    @OnClose
    public void onClose(){
        webSockets.remove(this);
        log.info("有老6离开了，当前人数:" + webSockets.size());
    }

    public void sendAllMessage(String message) {
        log.info("【websocket消息】广播消息:"+message);
        for(TestWebSocket webSocket : webSockets) {
            try {
                if(webSocket.session.isOpen()) {
                    webSocket.session.getAsyncRemote().sendText(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void sendOneMessage(String userId, String message) {
        Session session = sessionPool.get(userId);
        if (session != null&&session.isOpen()) {
            try {
                log.info("【websocket消息】 单点消息:"+message);
                session.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void sendMoreMessage(String[] userIds, String message) {
        for(String userId:userIds) {
            Session session = sessionPool.get(userId);
            if (session != null&&session.isOpen()) {
                try {
                    log.info("【websocket消息】 单点消息:"+message);
                    session.getAsyncRemote().sendText(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
