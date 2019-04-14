package com.easybuynet.web.chat;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;


/**
 *  * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 *  * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 *  * @ServerEndpoint 可以把当前类变成websocket服务类
 *  
 */
@ServerEndpoint("/websocket/{userno}")
public class WebSocketTest {
    private static int onlineCount = 0;
    private static ConcurrentHashMap<String, WebSocketTest> webSocketSet = new ConcurrentHashMap<String, WebSocketTest>();
    private Session session;
    private String userno = "";

    @OnOpen
    public void onOpen(@PathParam(value = "userno") String param, Session session, EndpointConfig config) {
        userno = param;//接收到发送消息的人员编号
        this.session = session;
        webSocketSet.put(param, this);//加入map中
        addOnlineCount();//在线数加1
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
    }


    /**
     * 连接关闭调用的方法
     *      
     */
    @OnClose
    public void onClose() {
        if (!userno.equals("")) {
            webSocketSet.remove(userno);//从set中删除
            subOnlineCount();//在线数减1
            System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
        }
    }


    /**
     *      * 收到客户端消息后调用的方法
     *      *
     *      * @param message 客户端发送过来的消息
     *      * @param session 可选的参数
     *      
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        if (false) {
            sendAll(message);
        } else {
            //给指定的人发消息
            sendToUser(message);
        }
    }


    /**
     *      * 给指定的人发送消息
     *      * @param message
     */
    public void sendToUser(String message) {
        String sendUserNo = message.split("[|]")[1];
        System.out.println(sendUserNo);
        String sendMessage = message.split("[|]")[0];
        try {
            if (webSocketSet.get(sendUserNo) != null) {
                webSocketSet.get(sendUserNo).sendMessage(userno + ":" + sendMessage);
            } else {
                System.out.println("当前用户不在线");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 给所有人发消息
     *
     * @param message      
     */


    private void sendAll(String message) {
        String sendMessage = message.split("[|]")[0];
        //遍历HashMap
        for (String key : webSocketSet.keySet()) {
            try {
                //判断接收用户是否是当前发消息的用户
                if (!userno.equals(key)) {
                    webSocketSet.get(key).sendMessage(userno + ":" + sendMessage);
                    System.out.println("key = " + key);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    public static synchronized int getOnlineCount() {
        return onlineCount;
    }


    public static synchronized void addOnlineCount() {
        WebSocketTest.onlineCount++;
    }


    public static synchronized void subOnlineCount() {
        WebSocketTest.onlineCount--;
    }

}