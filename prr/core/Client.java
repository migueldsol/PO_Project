package prr.core;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

public class Client {
    private String _key;
    private String _name;
    private int _taxNumber;
    private Map<Integer,Terminal> _terminals;
    private ClientType _clientType;
    private double _payments;
    private double _debts;
    private List<Notification> _notifications;
    private boolean _notificationsOn;

    public Client(String key, String name, int taxNumber) {
        _key = key;
        _name = name;
        _taxNumber = taxNumber;
        _terminals = new HashMap<>();
        _clientType = ClientType.NORMAL;
        _notifications = new ArrayList<>();
        _notificationsOn = true;
    }

    public String getKey() {
        String deepKey = new String(_key);
        return deepKey;
    }

    public String getName() {
        String deepName = new String(_name);
        return deepName;
    }

    public int getTaxNumber() {
        return _taxNumber;
    }

    public Map<Integer,Terminal> getDeepTerminals() {
        return Collections.unmodifiableMap(_terminals);
    }

    public void setTerminals(Map <Integer, Terminal> map){
        _terminals = map;
    }

    public ClientType getClientType() {
        return _clientType;
    }

    public double getClientPayments() {
        return _payments;
    }

    public void setPayments(double payments){
        _payments = payments;
    }

    public double getClientDebts() {
        return _debts;
    }

    public void setDebts(double debts){
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

    public String toString(){
        return "CLIENT|" + _key + "|" + _name + "|" + _taxNumber + "|" + _clientType + "|" + _notifications.toString() +
         "|" + _terminals.toString() + "|" + _payments + "|" + _debts; 

    }

}
