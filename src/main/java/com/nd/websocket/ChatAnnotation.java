package com.nd.websocket;


import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpSession;
import javax.websocket.EncodeException;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.nd.entity.WsObject;



@ServerEndpoint(value = "/chat/{room}",configurator=GetHttpSessionConfigurator.class,encoders={ServerEncoder.class})
public class ChatAnnotation {

    private static final AtomicInteger connectionIds = new AtomicInteger(0);
    private static final Set<ChatAnnotation> connections =
            new CopyOnWriteArraySet<>();
    private String realname;
    private String roomid;
    private Session session;
    private WsObject wsObject;
    
    public ChatAnnotation() {
    	wsObject=new WsObject();
    }

    @OnOpen
    public void start(Session session,EndpointConfig config,@PathParam("room")String room) {
        roomid = room;
    	realname = this.getUsername(config);
    	if(realname!=null){
        	this.session = session;
            connections.add(this);
            String message = String.format("* %s %s", realname, "进入圆桌.");
            wsObject.setMsg(message);
        	wsObject.setStatus(1);
            broadcast(wsObject);
    	}else{
            try {
				session.getBasicRemote().sendText("请先登录!!!");
			} catch (IOException e) {
				System.out.println("Chat Error: Failed to send message to client");
                try {
                    this.session.close();
                } catch (IOException e1) {
                    // Ignore
                }
			}
    	}
    }

    @OnClose
    public void end() {
        connections.remove(this);
        String message = String.format("* %s %s",
                realname, "has disconnected.");
        wsObject.setMsg(message);
        wsObject.setStatus(0);
        broadcast(wsObject);
    }

    @OnMessage
    public void incoming(String message) {
    		wsObject.setMsg(message);
        	wsObject.setStatus(2);
    	broadcast(wsObject);
    }

    @OnError
    public void onError(Throwable t) throws Throwable {
        System.out.println("Chat Error: " + t.toString());
    }
    
    
    private void broadcast(String msg) {
        for (ChatAnnotation client : connections) {
        	if(client.roomid.equals(roomid)){
        		try {
                    synchronized (client) {
                        client.session.getBasicRemote().sendText(msg);
                    }
                } catch (IOException e) {
                	System.out.println("Chat Error: Failed to send message to client");
                    connections.remove(client);
                    try {
                        client.session.close();
                    } catch (IOException e1) {
                        // Ignore
                    }
                    String message = String.format("* %s %s",
                            client.realname, "退出圆桌.");
                    broadcast(message);
                }
        	}
            
        }
    }
    private void broadcast(WsObject ws) {
        for (ChatAnnotation client : connections) {
        	if(client.roomid.equals(roomid)){
        		try {
                    synchronized (client) {
                        client.session.getBasicRemote().sendObject(ws);
                    }
                } catch (IOException e) {
                	System.out.println("Chat Error: Failed to send message to client");
                    connections.remove(client);
                    try {
                        client.session.close();
                    } catch (IOException e1) {
                        // Ignore
                    }
                    String message = String.format("* %s %s",
                            client.realname, "has been disconnected.");
                    broadcast(wsObject);
                }catch (EncodeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
            
        }
    }
    
    private String getUsername(EndpointConfig config){
    	HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
    	String name=null;
    	if(httpSession!=null){
        	name = (String) httpSession.getAttribute("realname");
        	String img = (String) httpSession.getAttribute("img");
        	wsObject.setImg(img);
        	wsObject.setName(name);
        }
    	return name;
    }
    
}
