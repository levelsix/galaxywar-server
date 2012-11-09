package com.lvl6.galaxywar.utils;

import org.slf4j.MDC;

import com.lvl6.galaxywar.properties.MDCKeys;

public class MiscMethods {

  //private static Logger log = LoggerFactory.getLogger(MiscMethods.class);



  public static void purgeMDCProperties(){
    MDC.remove(MDCKeys.UDID);
    MDC.remove(MDCKeys.PLAYER_ID);
    MDC.remove(MDCKeys.IP);
  }

  public static void setMDCProperties(String udid, String playerId, String ip) {
    purgeMDCProperties();
    if (udid != null) MDC.put(MDCKeys.UDID, udid);
    if (ip != null) MDC.put(MDCKeys.IP, ip);
    if (playerId != null) MDC.put(MDCKeys.PLAYER_ID, playerId);
  }

  
  
}
