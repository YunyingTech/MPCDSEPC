package com.yytech.mpcdsepc.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
    private static ConcurrentHashMap<String,String> EditDocumentMap = new ConcurrentHashMap<String,String>();

    @OnOpen
    public void onOpen(Session session, @PathParam(value="id")String userId) {
        try {
            this.session = session;
            this.userId = userId;
            if (sessionPool.get(userId) != null) {
//                this.session.close(new CloseReason(CloseReason.CloseCodes.TRY_AGAIN_LATER,"当前账号在别处已经登陆"));
               sessionPool.get(userId).close(new CloseReason(CloseReason.CloseCodes.CANNOT_ACCEPT,"账号在别处登陆，你已被迫下线"));
            }
            //将本次会话存入websocket连接Set
            webSockets.add(this);
            //把加入的用户ID以及对应对话加入Map
            sessionPool.put(userId, session);
            this.sendAllMessage(Message.OnlineCount(webSockets.size()));
            System.out.println("【websocket消息】有新的连接，总数为:" + webSockets.size());
            log.info("【websocket消息】有新的连接，总数为:"+sessionPool);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(555);
        }
    }
    @OnMessage
    public void onMessage(String message) {
        JSONObject jsonObject = JSON.parseObject(message);
        if ("lock".equals(jsonObject.get("type").toString())) {
//            EditDocumentMap.put()
            this.sendAllMessage(Message.Lock(jsonObject.get("data").toString()));
        }
        log.info("【websocket消息】收到客户端消息:"+message);
    }


    @OnError
    public void onError(Session session, Throwable error) {

        log.error("用户错误,原因:"+error.getMessage());
        error.printStackTrace();
    }

    @OnClose
    public void onClose(){
        if (webSockets.contains(this)) {
            webSockets.remove(this);
        }

        this.sendAllMessage(Message.OnlineCount(webSockets.size()));
        System.out.println(sessionPool);
        System.out.println(webSockets);
        if (userId != null && sessionPool.containsKey(userId)) {
            sessionPool.remove(userId);
        }
        System.out.println(userId);
        System.out.println(sessionPool);
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
