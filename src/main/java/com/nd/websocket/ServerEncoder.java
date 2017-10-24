package com.nd.websocket;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.alibaba.fastjson.JSON;
import com.nd.entity.WsObject;  
//解码器
public class ServerEncoder implements Encoder.Text<WsObject>{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(EndpointConfig arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String encode(WsObject ws) throws EncodeException {
		return JSON.toJSONString(ws);
	}
	
}
