package com.lvl6.galaxywar.eventhandlers;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.lvl6.galaxywar.controller.ControllerMap;
import com.lvl6.galaxywar.controller.EventController;
import com.lvl6.galaxywar.proto.ProtocolsProto.EventProtocolRequest;

public class GameEventHandler implements MessageListener{

	
	private static Logger log = LoggerFactory.getLogger(GameEventHandler.class);

	
	@Autowired
	protected ControllerMap controllerMap;
	
	@Override
	public void onMessage(Message msg) {
		log.info("Received message");
		if(msg != null && msg.getMessageProperties() != null && msg.getMessageProperties().getHeaders() != null) {
			Map<String, Object> headers = msg.getMessageProperties().getHeaders();
			EventProtocolRequest eventType = EventProtocolRequest.valueOf((Integer) headers.get(""));
			EventController controller = controllerMap.getEventControllerByEventType(eventType);
			controller.processEvent(null);
		}else {
			throw new RuntimeException("Message was null or missing headers");
		}
	}

	
	
	public ControllerMap getControllerMap() {
		return controllerMap;
	}

	public void setControllerMap(ControllerMap controllerMap) {
		this.controllerMap = controllerMap;
	}

}
