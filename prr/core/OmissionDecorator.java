package prr.core;

import java.util.ArrayList;
import java.util.List;

public class OmissionDecorator extends NotificationDecorator{

    private List <String> _notifications;
    public OmissionDecorator(Notifiable instance){
        super(instance);
        _notifications = new ArrayList<>();
    }

    @Override
    public void sendNotification(String key, NotificationType notificationType) {
        String message = super.getInstance().getNotificationString(key, notificationType);
        _notifications.add(message);
    }

    public List<String> getNotifications(){
        List<String> result = new ArrayList<>(_notifications); 
        _notifications = new ArrayList<>();
        return result;
    }
    
}
