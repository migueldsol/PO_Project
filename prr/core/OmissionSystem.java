package prr.core;

import java.util.ArrayList;
import java.util.List;

public class OmissionSystem implements Notifiable{

    private List <String> _notifications;
    public OmissionSystem(){
        _notifications = new ArrayList<>();
    }
    @Override
    public String getNotificationMessage(String terminalId, NotificationType notificationType) {
        return notificationType.toString() + "|" + terminalId;
    }
    @Override
    public List<String> getAllNotificationMessage() {
        List <String> notifications = new ArrayList<>(_notifications);
        _notifications = new ArrayList<>();
        return notifications;
    }
    @Override
    public void sendNotification(String terminalId, NotificationType notificationType) {
        String message = getNotificationMessage(terminalId, notificationType);
        _notifications.add(message);
    }
    
}
