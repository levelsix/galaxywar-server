package com.lvl6.galaxywar.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.lvl6.galaxywar.events.RequestEvent;
import com.lvl6.galaxywar.proto.ProtocolsProto.EventProtocolRequest;
import com.lvl6.galaxywar.utils.MiscMethods;



@Component
public abstract class EventController{


	private static Logger log = LoggerFactory.getLogger(EventController.class);

	
	public abstract RequestEvent createRequestEvent();

	public void processEvent(RequestEvent event){
		/*MiscMethods.setMDCProperties(	null, reqEvent.getPlayerId(), MiscMethods.getIPOfPlayer(server, reqEvent.getPlayerId(),	null));*/
		try {
			log.info("Received event: " + event.toString());
			processRequestEvent(event);
		}catch(Throwable t) {
			log.error("Error processing GameEvent", t);
		}
		MiscMethods.purgeMDCProperties();
	}


	/**
	 * subclasses must implement to provide their Event type
	 */
	public abstract EventProtocolRequest getEventType();

	protected abstract void processRequestEvent(RequestEvent event);

}
