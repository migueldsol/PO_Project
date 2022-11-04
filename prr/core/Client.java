package prr.core;

import java.io.Serial;
import java.util.*;
import java.io.Serializable;

public class Client implements Serializable, Observer{

    private static final long serialVersionUID = 202208091753L;
    private final String KEY;
    private final String NAME;
    private final int TAX_NUMBER;
    private final SortedMap<String,Terminal> _terminals;
    private ClientType _clientType;
    //QUESTIONS vale a pena termos este atributos?
    //  Pq nao ter só um metodo que calcula qd for preciso?
    private boolean _notificationsOn;
    private PricingSystem _pricingSystem;
    private NotificationSender _deliverySystem;

    public Client(String key, String name, int taxNumber) {
        KEY = key;
        NAME = name;
        TAX_NUMBER = taxNumber;
        _terminals = new TreeMap<>();
        _clientType = new NormalType(this);
        _notificationsOn = true;
        _pricingSystem = new BasePricingSystem();
        _deliverySystem = new NotificationSender(new OmissionSystem());
    }

    public String getKey() {
        String deepKey = KEY;
        return deepKey;
    }

    public double getClientPayments() {
        double payments = 0;
        for(Terminal terminal: _terminals.values()){
            payments += terminal.getPayments();
        }
        return payments;
    }

    public double getClientDebts(){
        double debts = 0;
        for(Terminal terminal: _terminals.values()){
            debts += terminal.getDebts();
        }
        return debts;
    }

    public void registerTerminal(Terminal terminal) {
        _terminals.put(terminal.getKey(),terminal);
    }

    public String getStringNotificationsOn(){
        if (_notificationsOn){
            return "YES";
        }
        return "NO";
    }

    public String toString(){
        return "CLIENT|" + KEY + "|" + NAME + "|" + TAX_NUMBER + "|" + getType().toString() + "|" + getStringNotificationsOn() +
         "|" + _terminals.size() + "|" + Math.round(getClientPayments()) + "|" + Math.round(getClientDebts());

    }

    public void setType(ClientType newType){
        _clientType = newType;
    }

    public ClientType getType(){
        return _clientType;
    }
    //FIXME ns se esta certo
    //FIXME codigo repetido

    public PricingSystem getPricingSystem(){
        return _pricingSystem;
    }

    /*FIXME 
        temos que pensar se colocamos aqui a communication
        e deixamos o clientType lidar com o tipo de communication
        ou se o terminal é que lida com isso
    */
    public double getBalance(){
        double balance = 0;
        for(Terminal terminal: _terminals.values()){
            balance += terminal.getBalance();
        }
        return balance;
    }
    public boolean setNotificationOn(){
        if (!_notificationsOn){
            _notificationsOn = true;
            return true;
        }
        else {
            return false;
        }
    }

    public boolean setNotificationOff(){
        if (_notificationsOn){
            _notificationsOn = false;
            return true;
        }
        return false;
    }

    public List<Communication> getCommunicationsMade(){
        ArrayList<Communication> communications = new ArrayList<>();
        for (Terminal i : _terminals.values()){
            communications.addAll(i.getCommunicationsMade());
        }
        communications.sort(new CommunicationComparator());
        return communications;
    }

    public List<Communication> getCommunicationsReceived(){
        ArrayList<Communication> communications = new ArrayList<>();
        for (Terminal i : _terminals.values()){
            communications.addAll(i.getCommunicationsReceived());
        }
        communications.sort(new CommunicationComparator());
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
