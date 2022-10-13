package prr.core;

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
}
