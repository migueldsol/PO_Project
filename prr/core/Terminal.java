package prr.core;

import java.io.Serializable;
import java.util.*;
import java.util.ArrayList;


/**
 * Abstract terminal.
 */
abstract public class Terminal implements Serializable, Subject{

  /**
   * Serial number for serialization.
   */
  private static final long serialVersionUID = 202208091753L;

  public final TerminalType TERMINAL_TYPE;
  private final String KEY;
  private final List<Communication> _communicationsMade;
  private final List<Communication> _communicationsReceived;
  private TerminalState _terminalState;
  private InteractiveCommunication _currentCommunication;
  private TerminalState _previousState;
  private final Client CLIENT;
  private final List <Terminal> _friendlyTerminals;

  private List <Observer> _observers;

  public Terminal(String key, Client client, TerminalType terminalType) {
    KEY = key;
    CLIENT = client;
    _communicationsMade = new ArrayList<>();
    _communicationsReceived = new ArrayList<>();
    _terminalState = new TerminalIdle(this);
    _previousState = _terminalState;

    _friendlyTerminals = new ArrayList<>();
    TERMINAL_TYPE = terminalType;
    _observers = new ArrayList<>();

  }

  public TerminalState getPreviousState(){
    return _previousState;
  }
  public void changePreviousState(TerminalState state){
    _previousState = state;
  }
  public void addCurrentCommunication(InteractiveCommunication communication){
    _currentCommunication = communication;
  }

  public void removeCurrentCommunication(){
    _currentCommunication = null;
  }

  public InteractiveCommunication getCurrentComunication(){
    return _currentCommunication;
  }

  public double getBalance(){
    return getPayments() - getDebts();
  }
  public double getDebts(){
    double debt = 0;
    for(Communication communication: _communicationsMade){
      if(!communication.isPaid()){
        debt += communication.getPrice();
      }
    }
    return debt;
  }

  public double getPayments(){
    double payment = 0;
    for(Communication communication: _communicationsMade){
      if(communication.isPaid()){
        payment += communication.getPrice();
      }
    }
    return payment;
  }

  public void setState(TerminalState state){
    NotificationType notificationType = changeType(this.getTerminalState(), state);
    if (notificationType != null){
      notifyObserver(notificationType);
    }
    _terminalState = state;
  }

  public NotificationType changeType(TerminalState inicialState, TerminalState finalState){

    boolean inicialOff = inicialState.isOff();
    boolean finalIdle = finalState.isIdle();
    boolean finalSilence = finalState.isSilence();
    
    if (inicialOff && finalSilence){
        return NotificationType.O2S;
    }
    else if (inicialOff && finalIdle){
        return NotificationType.O2I;
    }
    else if (inicialState.isSilence() && finalIdle){
        return NotificationType.S2I;
    }
    else if (inicialState.isBusy() && finalIdle){
        return NotificationType.B2I;
    }
    return null;
}
  public void addCommunicationMade(Communication communication){
    _communicationsMade.add(communication);
  }

  public void addCommunicationReceived(Communication communication){
    _communicationsReceived.add(communication);
  }
  public Communication getMadeCommunication(int id){
    for(Communication comm: _communicationsMade){
      if(comm.getId() == id){
        return comm;
      }
    }
    return null;
  }

  public abstract void makeCommunication(String targetKey, String type);

  public abstract void startInteractiveCommunication(String targetKey, String type);

  public String getKey() {
    return KEY;
  }

  public TerminalState getTerminalState() {return _terminalState;}

  public Client getClient() {
    return CLIENT;
  }

  public boolean canEndCommunication(){
    return (_currentCommunication != null);
  }

  public Communication getLastCommunication(){
    return _communicationsMade.get((_communicationsMade.size()-1));
  }

  public void addFriendlyTerminal(Terminal newTerminal) {
    if (!_friendlyTerminals.contains(newTerminal)){
      _friendlyTerminals.add(newTerminal);
    }
  }

  public void removeFriendlyTerminal(Terminal newTerminal){
    _friendlyTerminals.remove(newTerminal);
  }
  abstract public TerminalType getTerminalType();

  public void addInteractiveCommunicationMade(Communication communication){
    _communicationsMade.add(communication);
    this.getTerminalState().changeToBusy();
  }

  public void addInteractiveCommunicationReceived(Communication communication){
    _communicationsReceived.add(communication);
    this.getTerminalState().changeToBusy();
  }

  public boolean isFriend(Terminal terminal){
    return _friendlyTerminals.contains(terminal);
  }

  public boolean hasActivity(){
    return !_communicationsMade.isEmpty() && !_communicationsReceived.isEmpty();
  }

  public List<Communication> getCommunicationsMade(){
    return _communicationsMade;
  }

  public List<Communication> getCommunicationsReceived(){
    return _communicationsReceived;
  }
  @Override
  public String toString() {
    if (_friendlyTerminals.isEmpty()) {
      return getTerminalType().name() + "|" + KEY + "|" + CLIENT.getKey() + "|" + _terminalState.toString() + "|"
              + Math.round(getPayments()) + "|" + Math.round(getDebts());
    }
    List<String> friends = new ArrayList<>();
    for (Terminal i : _friendlyTerminals){
      friends.add(i.getKey());
    }
    Collections.sort(friends);

    return getTerminalType().name() + "|" + KEY + "|" + CLIENT.getKey() + "|" + _terminalState.toString() + "|"
            + Math.round(getDebts()) + "|" + Math.round(getPayments()) + "|" + String.join(",",friends);
  }

  public void registerObserver(Observer observer){
    if (!_observers.contains(observer)){
      _observers.add(observer);
    }
  }
  public void unregisterObservers(){
    _observers = new ArrayList<>();
  }

  public void notifyObserver(NotificationType notificationType){
    for (Observer i : _observers){
      i.update(this.KEY,notificationType);
    }
    unregisterObservers();
  }
}
