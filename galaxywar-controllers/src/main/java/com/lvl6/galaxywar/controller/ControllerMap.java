package com.lvl6.galaxywar.controller;

import java.util.Hashtable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lvl6.galaxywar.proto.ProtocolsProto.EventProtocolRequest;
import com.lvl6.galaxywar.controller.EventController;


@Component
public class ControllerMap implements InitializingBean {

	private static Logger log = LoggerFactory.getLogger(ControllerMap.class);
	
	private Hashtable<EventProtocolRequest, EventController> eventControllers = new Hashtable<EventProtocolRequest, EventController>();
	
	@Autowired
	protected List<EventController> eventControllerList;
	public void setEventControllerList(List<EventController> eventControllerList) {
		this.eventControllerList = eventControllerList;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		mapControllers();
	}
	
	protected void mapControllers() {
		log.info("Adding event controllers to eventControllers controllerType-->controller map");
		for(EventController ec: eventControllerList) {
			eventControllers.put(ec.getEventType(), ec);
		}
	}
	
	
	/**
	 * finds the EventController for a given event type
	 * @throws Exception 
	 */
	public EventController getEventControllerByEventType(EventProtocolRequest eventType) {
		if(eventType == null) {
			throw new RuntimeException("EventProtocolRequest (eventType) is null");
		}
		if(eventControllers.containsKey(eventType)) {
			EventController ec = eventControllers.get(eventType);
			if (ec == null) {
				log.error("no eventcontroller for eventType: " + eventType);
				throw new RuntimeException("EventController of type: "+eventType+" not found");
			}
			return ec;
		}
		throw new RuntimeException("EventController of type: "+eventType+" not found");
	}

}
