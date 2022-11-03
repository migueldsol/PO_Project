package prr.core;

import java.util.List;

public interface Notifiable {
    String getNotificationMessage(String TerminalId, NotificationType notificationType);

    List <String> getAllNotificationMessage();

    void sendNotification(String TerminalId, NotificationType notificationType);
}
