package prr.core;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

public class Client implements Serializable{
    private final String KEY;
    private final String NAME;
    private final int TAX_NUMBER;
    private Map<String,Terminal> _terminals;
    private ClientType _clientType;
    private double _payments;
    private double _debts;
    private List<Notification> _notifications;
    private boolean _notificationsOn;

    public Client(String key, String name, int taxNumber) {
        KEY = key;
        NAME = name;
        TAX_NUMBER = taxNumber;
        _terminals = new HashMap<>();
        _clientType = ClientType.NORMAL;
        _notifications = new ArrayList<>();
        _notificationsOn = true;
    }

    public String getKey() {
        String deepKey = new String(KEY);
        return deepKey;
    }

    public String getName() {
        String deepName = new String(NAME);
        return deepName;
    }

    public int getTaxNumber() {
        return TAX_NUMBER;
    }

    public Map<String,Terminal> getDeepTerminals() {
        return Collections.unmodifiableMap(_terminals);
    }

    public void setTerminals(Map <String, Terminal> map){
        _terminals = map;
    }

    public ClientType getClientType() {
        return _clientType;
    }

    public double getClientPayments() {
        return _payments;
    }


    public void setPayments(float payments){
        _payments = payments;
    }

    public double getClientDebts(){
        return _debts;
    }

    public void setDebts(float debts){
        _debts = debts;
    }

    public List<Notification> getNotificationsList() {
        return Collections.unmodifiableList(_notifications);
    }

    public boolean getNotificationsOn() {
        return _notificationsOn;
    }

    public double getBalance() {
        return _payments - _debts;
    }


    public boolean registerTerminal(Terminal terminal) {
        if (_terminals.containsKey(terminal.getKey())){
            return false;
        }
        _terminals.put(terminal.getKey(),terminal);
        return true;
    }


    public String getStringNotificationsOn(){
        if (_notificationsOn == true){
            return "YES";
        }
        return "NO";
    }


    //FIXME passar ints/doubles para strings
    public String toString(){
        return "CLIENT|" + KEY + "|" + NAME + "|" + TAX_NUMBER + "|" + _clientType.toString() + "|" + getStringNotificationsOn() +
         "|" + Integer.toString(_terminals.size()) + "|" + Math.round(getClientPayments()) + "|" + Math.round(getClientDebts());

    }

}
