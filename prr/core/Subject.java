package prr.core;

public interface Subject {
    void registerObserver(Observer observer);
    void unregisterObservers();
    void notifyObserver(NotificationType notificationType);
}
