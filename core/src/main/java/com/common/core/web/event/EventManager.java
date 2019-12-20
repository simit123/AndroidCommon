package com.common.core.web.event;

import android.support.annotation.NonNull;

import java.util.HashMap;

/**
 * @author by wuYang
 * @date 2019/10/15
 * @describe
 */
public class EventManager {

    // TODO: 2019/10/15 map 集合的作用
    private static final HashMap<String, Event> EVENTS = new HashMap<>();

    private EventManager() {
    }

    private static final class Holder {
        private static final EventManager INSTANCE = new EventManager();
    }

    public static EventManager getInstance() {
        return Holder.INSTANCE;
    }

    public void addEvent(@NonNull String name, @NonNull Event event) {
        EVENTS.put(name, event);
    }

    public Event createEvent(@NonNull String action) {
        final Event event = EVENTS.get(action);
        if (event == null) {
            return new UndefineEvent();
        }
        return event;
    }

}
