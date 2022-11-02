package prr.core;

import java.io.Serializable;

public class Notification implements Serializable, Observer{
    private static final long serialVersionUID = 202208091753L;
    
    private String _terminalId;

    private boolean _notify;

    private NotificationType _notificationType;

    public Notification(String terminalId){
        _terminalId = new String(terminalId);
    }

    public void update(NotificationType notificationType){
        _notificationType = notificationType;
        _notify = true;
    }

    public String toString(){
        return _notificationType.toString() + "|" + _terminalId;
    }
}
