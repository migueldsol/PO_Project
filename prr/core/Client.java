package prr.core;

import java.util.List;

public class Client {
    private String _key;
    private String _name;
    private int _taxNumber;
    private List<Terminal> _terminals;
    private ClientType _clientType;
    private double _payments;
    private double _debts;
    private PricingSystem _pricingSystem;
    private List<Notification> _notifications;
    private boolean _notificationsOn;

    public Client(String key, String name, int taxNumber, PricingSystem pricingSystem) {
        _key = key;
        _name = name;
        _taxNumber = taxNumber;
        // _terminals = new List<>();
        // _clientType = "Normal":
        _pricingSystem = pricingSystem;
        // _notifications = new List<>();
        _notificationsOn = true;
    }

    public String getKey() {
        return _key;
    }

    public String getName() {
        return _name;
    }

    public int getTaxNumber() {
        return _taxNumber;
    }

    public List<Terminal> getTerminalsList() {
        return _terminals.unmodifiableList();
    }

    public ClientType getClientType() {
        return _clientType;
    }

    public double getClientPayments() {
        return _payments;
    }

    public double getClientDebts() {
        return _debts;
    }

    public PricingSystem getPricingSystem() {
        return _pricingSystem;
    }

    public List<Notification> getNotificationsList() {
        return _notifications.unmodifiableList();
    }

    public boolean getNotificationsOn() {
        return _notificationsOn;
    }

    public double getBalance() {
        return _payments - _debts;
    }

    public boolean registerTerminal(String key, String type) {
        Terminal terminal = new Terminal(key, type);
        return _terminals.add(terminal);
    }

}
