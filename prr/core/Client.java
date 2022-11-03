package prr.core;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.SortedMap;
import java.util.HashMap;
import java.util.Iterator;

public class Client implements Serializable, Observer{

    private static final long serialVersionUID = 202208091753L;
    private final String KEY;
    private final String NAME;
    private final int TAX_NUMBER;
    private final Map<String,Terminal> _terminals;
    private ClientType _clientType;

    //FIXME nao e preciso isto;
    private ClientType _normalType;
    private ClientType _goldType;
    private ClientType _platinumType;

    //QUESTIONS vale a pena termos este atributos?
    //  Pq nao ter só um metodo que calcula qd for preciso?
    private double _payments;
    private double _debts;
    private boolean _notificationsOn;
    private PricingSystem _pricingSystem;
    private NotificationSender _deliverySystem;

    public Client(String key, String name, int taxNumber) {
        KEY = key;
        NAME = name;
        TAX_NUMBER = taxNumber;
        _terminals = new HashMap<>();
        _normalType = new NormalType(this);
        _goldType = new GoldType(this);
        _platinumType = new PlatinumType(this);
        _clientType =  _normalType;
        _notificationsOn = true;
        _pricingSystem = new BasePricingSystem();
        _deliverySystem = new NotificationSender(new OmissionSystem());
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

    public ClientType getType(){
        return _clientType;
    }
    //FIXME ns se esta certo
    //FIXME codigo repetido
    public boolean verifyGoldToPlatinum(){
        SortedMap <Integer,Communication> communications = this.getCommunicationsMade();
        List <Communication> communicationList = new ArrayList<>(communications.values());
        Collections.reverse(communicationList);
        Iterator<Communication> iterator = communicationList.iterator();
        Communication communication = iterator.next();
        int i = 0;
        while (i < 5){
            if (!iterator.hasNext()){
                return false;
            }
            else if (!communication.isVoice()){
                return false;
            }
            else if (!communication.hasEnded()){
                return false;
            }
            i++;
            communication = iterator.next();
        }
        return true;
    }

    public boolean verifyPlatinumToGold(){
        SortedMap<Integer,Communication> communications = this.getCommunicationsMade();
        List<Communication> communicationsList = new ArrayList<>(communications.values());
        Collections.reverse(communicationsList);
        Iterator<Communication> iterator = communicationsList.iterator();
        Communication communication = iterator.next();
        int i = 0;
        while (i < 2){
            if (!iterator.hasNext()){
                return false;
            }
            else if (!communication.isText()){
                return false;
            }
            i++;
            communication = iterator.next();
        }
        return true;
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

    public boolean setNotificationOn(){
        if (_notificationsOn != true){
            _notificationsOn = true;
            return true;
        }
        else {
            return false;
        }
    }

    public boolean setNotificationOff(){
        if (_notificationsOn != false){
            _notificationsOn = false;
            return true;
        }
        return false;
    }

    public SortedMap<Integer,Communication> getCommunicationsMade(){
        SortedMap <Integer, Communication> communications = new TreeMap<>();
        Collection <Terminal> terminals = _terminals.values();
        for (Terminal i : terminals){
            communications.putAll(i.getCommunicationsMade());
        }
        return communications;
    }

    public boolean getNotificationsOn(){
        return _notificationsOn;
    }

    public void update(String key,NotificationType notificationType){
        _deliverySystem.sendNotification(key, notificationType);
    }

    public List <String> getNotifications(){
        return _deliverySystem.getAllNotificationMessage();
    }
}
