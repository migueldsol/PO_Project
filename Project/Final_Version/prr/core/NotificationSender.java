package prr.core;

import java.io.Serializable;
import java.util.List;

public class NotificationSender implements Notifiable, Serializable {

    private static final long serialVersionUID = 202208091753L;

    private Notifiable _instance;

    public NotificationSender(Notifiable instance){
        _instance = instance;
    }

    public Notifiable getInstance(){
        return _instance;
    }

    @Override
    public  void sendNotification(String key, NotificationType notificationType){
        _instance.sendNotification(key, notificationType);
    }

    @Override
    public  List <String> getAllNotificationMessage(){
        return _instance.getAllNotificationMessage();
    }

    @Override
    public  String getNotificationMessage(String terminalKey, NotificationType notificationType){
        return _instance.getNotificationMessage(terminalKey, notificationType);
    }
}
