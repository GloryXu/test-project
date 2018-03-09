package jmx.advance.notification;

import javax.management.Notification;
import javax.management.NotificationListener;

public class HelloListener implements NotificationListener {

    public void handleNotification(Notification notification, Object handback) {
        if (handback instanceof Hello) {
            Hello hello = (Hello) handback;
            hello.printHello(notification.getMessage());
        }
    }

}
