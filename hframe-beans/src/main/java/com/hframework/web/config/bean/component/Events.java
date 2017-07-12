package com.hframework.web.config.bean.component;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;
import java.util.List;

/**
 * generated by hframework on 2016.
 */@XStreamAlias("events")
public class Events   {

	@XStreamImplicit
    @XStreamAlias("event")
	private List<Event> eventList;

    public Events() {
    }
   
 	 	 
     public List<Event> getEventList(){
     	return eventList == null ? new ArrayList<Event>() : eventList;
     }

     public void setEventList(List<Event> eventList){
     	this.eventList = eventList;
     }
	 
}
