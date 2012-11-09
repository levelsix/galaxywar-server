package com.lvl6.galaxywar.eventhandlers;

import java.nio.ByteBuffer;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.lvl6.galaxywar.controller.ControllerMap;
import com.lvl6.galaxywar.controller.EventController;
import com.lvl6.galaxywar.events.RequestEvent;
import com.lvl6.galaxywar.utils.Attachment;

public class GameEventHandler implements MessageListener{

	
	private static Logger log = LoggerFactory.getLogger(GameEventHandler.class);

	
	@Autowired
	protected ControllerMap controllerMap;
	
	@Override
	public void onMessage(Message msg) {
		log.info("Received message");
		if(msg != null) {
			Attachment attachment = new Attachment();
			byte[] payload = (byte[]) msg.getBody();
			attachment.readBuff = ByteBuffer.wrap(payload);
			while (attachment.eventReady()) {
				processAttachment(attachment);
				attachment.reset();
			}
		}else {
			throw new RuntimeException("Message was null or missing headers");
		}
	}

	
	protected void processAttachment(Attachment attachment) {
		ByteBuffer bb = ByteBuffer.wrap(Arrays.copyOf(attachment.payload, attachment.payloadSize));
		EventController ec = controllerMap.getEventControllerByEventType(attachment.eventType);
		if (ec == null) {
			return;
		}
		RequestEvent  event = ec.createRequestEvent();
		event.setTag(attachment.tag);
		event.read(bb);
		log.debug("Recieved event from client: " + event.getPlayerId());
		ec.processEvent(event);
	}
	
	public ControllerMap getControllerMap() {
		return controllerMap;
	}

	public void setControllerMap(ControllerMap controllerMap) {
		this.controllerMap = controllerMap;
	}

}
