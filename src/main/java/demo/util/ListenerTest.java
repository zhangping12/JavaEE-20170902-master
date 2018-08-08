package demo.util;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

//@WebListener("/*")
public class ListenerTest implements HttpSessionAttributeListener {
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        System.out.println("added: " + event.getName() + " : " + event.getValue());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        System.out.println("removed: " + event.getName() + " : " + event.getValue());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        System.out.println("replaced: " + event.getName() + " : " + event.getValue());
    }
}
