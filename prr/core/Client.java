package prr.core;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Client implements Serializable{

    private static final long serialVersionUID = 202208091753L;
    private final String KEY;
    private final String NAME;
    private final int TAX_NUMBER;
    private final Map<String,Terminal> _terminals;
    private ClientType _clientType;

    //FIXME meter merdas final
    private ClientType _normalType;
    private ClientType _goldType;
    private ClientType _platinumType;
    private double _payments;
    private double _debts;
    private final List<Notification> _notifications;
    private final boolean _notificationsOn;
    private PricingSystem _pricingSystem;

    public Client(String key, String name, int taxNumber) {
        KEY = key;
        NAME = name;
        TAX_NUMBER = taxNumber;
        _terminals = new HashMap<>();
        _normalType = new NormalType(this);
        _goldType = new GoldType(this);
        _platinumType = new PlatinumType(this);
        _clientType =  _normalType;
        _notifications = new ArrayList<>();
        _notificationsOn = true;
        _pricingSystem = new BasePricingSystem();
    }

    public String getKey() {
        String deepKey = KEY;
        return deepKey;
    }

    public double getClientPayments() {
        return _payments;
    }

    public double getClientDebts(){
        return _debts;
    }

    public boolean registerTerminal(Terminal terminal) {
        if (_terminals.containsKey(terminal.getKey())){
            return false;
        }
        _terminals.put(terminal.getKey(),terminal);
        return true;
    }

    public String getStringNotificationsOn(){
        if (_notificationsOn){
            return "YES";
        }
        return "NO";
    }

    public String toString(){
        return "CLIENT|" + KEY + "|" + NAME + "|" + TAX_NUMBER + "|" + _clientType.toString() + "|" + getStringNotificationsOn() +
         "|" + _terminals.size() + "|" + Math.round(getClientPayments()) + "|" + Math.round(getClientDebts());

    }

    public void setType(ClientType newType){
        _clientType = newType;
    }

    public void changeType(){
        _clientType.changeType();
    }

    public PricingSystem getPricingSystem(){
        return _pricingSystem;
    }

    /*FIXME 
        temos que pensar se colocamos aqui a communication
        e deixamos o clientType lidar com o tipo de communication
        ou se o terminal é que lida com isso
    */
    public int getTarrif(){
        return 0; //FIXME implementar
    }

    public int getBalance(){
        return 0; //FIXME implementar, falta communications
    }

    public ClientType getNormalType(){
        return _normalType;
    }

    public ClientType getGoldType(){
        return _goldType;
    }
    
    public ClientType getPlatinumType(){
        return _platinumType;
    }
}
