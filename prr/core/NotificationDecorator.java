package prr.core;

import java.util.List;

public abstract class NotificationDecorator {

    private Notifiable _instance;

    public NotificationDecorator(Notifiable instance){
        _instance = instance;
    }

    public Notifiable getInstance(){
        return _instance;
    }

    //QUESTIONS da return a uma String?
    public abstract void sendNotification(String key, NotificationType notificationType);

    //QUESTONS isto faz sentido?
    public abstract List<String> getNotifications();
}
