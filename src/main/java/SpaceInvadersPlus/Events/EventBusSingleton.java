package SpaceInvadersPlus.Events;

import jdk.jfr.Event;

import SpaceInvadersPlus.Events.EventType;
import SpaceInvadersPlus.Events.IObserver;
import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;


public class EventBusSingleton {
    private static EventBusSingleton instance;

    private static Map<EventType, List<IObserver>> observers = new HashMap<>();

    private EventBusSingleton() {
        for (EventType type : EventType.values()) {
            observers.put(type, new ArrayList<>());
        }
    }

    public static synchronized EventBusSingleton getInstance(){
        if (instance == null){
            instance = new EventBusSingleton();
        }
        return instance;
    }

    public void attach(IObserver observer, EventType eventType){
        if (eventType == EventType.All){
            for (EventType type : observers.keySet()){
                if (!observers.get(type).contains(observer)){
                    observers.get(type).add(observer);
                }
            }
        }
        else{
            if (!observers.get(eventType).contains(observer)) {
                observers.get(eventType).add(observer);
            }
        }
    }

    public void attach(IObserver observer, List<EventType> eventTypes) {
        for (EventType type : eventTypes) {
            observers.get(type).add(observer);
        }
    }

    public void detach(IObserver observer, EventType eventType){
        if (eventType == EventType.All){
            for (EventType type : observers.keySet()){
                if (observers.get(type).contains(observer)){
                    observers.get(type).remove(observer);
                }

            }
        }
        else{
            if (observers.get(eventType).contains(observer)) {
                observers.get(eventType).remove(observer);
            }
        }
    }

    public void postMessage(EventType eventType, String eventDescription){
        List<IObserver> observersToPost = observers.get(eventType);

        observersToPost.forEach(observer -> observer.update(eventType, eventDescription));
    }

    public List<IObserver> getObservers(EventType eventType){
        return observers.get(eventType);
    }

    public void reset() {
        observers.clear();
        for (EventType type : EventType.values()) {
            observers.put(type, new ArrayList<>());
        }
    }
}
