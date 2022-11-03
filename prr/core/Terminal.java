package prr.core;

import java.io.Serializable;
import java.util.*;


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
  private final TreeMap<String, Terminal> _friendlyTerminals;

  private List <Observer> _observers;

  public Terminal(String key, Client client, TerminalType terminalType) {
    KEY = key;
    CLIENT = client;
    _communicationsMade = new ArrayList<>();
    _communicationsReceived = new ArrayList<>();
    _terminalState = new TerminalIdle(this);
    _previousState = _terminalState;

    _friendlyTerminals = new TreeMap<>();
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
    for(Communication communication: _communicationsMade()){
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

  //FIXME verificar 
  public void setState(TerminalState state){
    NotificationType notificationType = changeType(this.getPreviousState(), state);
    if (notificationType != null){
      notifyObserver(notificationType);
    }
    _terminalState = state;
  }

  public NotificationType changeType(TerminalState inicialState, TerminalState finalState){

    boolean inicialOff = inicialState.isOff();
    boolean inicialBusy = inicialState.isBusy();
    boolean inicialSilence = inicialState.isSilence();
    boolean finalIdle = finalState.isIdle();
    boolean finalSilence = finalState.isSilence();
    
    if (inicialOff && finalSilence){
        return NotificationType.O2S;
    }
    else if (inicialOff && finalIdle){
        return NotificationType.O2I;
    }
    else if (inicialSilence && finalIdle){
        return NotificationType.S2I;
    }
    else if (inicialBusy && finalIdle){
        return NotificationType.B2I;
    }
    return null;
}
  public void addCommunicationMade(Communication communication){
    _communicationsMade.add(communication.getId(),communication);
  }

  public void addCommunicationReceived(Communication communication){
    _communicationsReceived.add(communication.getId(),communication);
  }
  public Communication getMadeCommunication(int id){
    return _communicationsMade.get(id);
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
    return _communicationsMade.get(_communicationsMade.lastKey());
  }

  public void addFriendlyTerminal(Terminal newTerminal) {
    _friendlyTerminals.put(newTerminal.getKey(), newTerminal);
  }

  public void removeFriendlyTerminal(Terminal newTerminal){
    _friendlyTerminals.remove(newTerminal.getKey(),newTerminal);
  }
  abstract public TerminalType getTerminalType();

  public void addInteractiveCommunicationMade(Communication communication){
    _communicationsMade.put(communication.getId(),communication);
    this.getTerminalState().changeToBusy();
  }

  public void addInteractiveCommunicationReceived(Communication communication){
    _communicationsReceived.put(communication.getId(),communication);
    this.getTerminalState().changeToBusy();
  }

  public boolean isFriend(String terminalId){
    return this._friendlyTerminals.containsKey(terminalId);
  }

  public boolean hasActivity(){
    return !_communicationsMade.isEmpty() || !_communicationsReceived.isEmpty();
  }

// TODO: pode se passar um treemap normalmente ou tem que ser unmodifyable
  public TreeMap<Integer,Communication> getCommunicationsMade(){
    return _communicationsMade;
  }

  public TreeMap<Integer,Communication> getCommunicationsReceived(){
    return _communicationsReceived;
  }
  @Override
  public String toString() {
    if (_friendlyTerminals.isEmpty()) {
      return getTerminalType().name() + "|" + KEY + "|" + CLIENT.getKey() + "|" + _terminalState.toString() + "|"
              + Math.round(getPayments()) + "|" + Math.round(getDebts());
    }
    List<String> friends = new ArrayList<>(_friendlyTerminals.keySet());

    return getTerminalType().name() + "|" + KEY + "|" + CLIENT.getKey() + "|" + _terminalState.toString() + "|"
            + Math.round(getDebts()) + "|" + Math.round(getPayments()) + "|" + String.join(",",friends);
  }

  public void registerObserver(Observer observer){
    if (!_observers.contains(observer)){
      _observers.add(observer);
    }
  }
    //FIXME posso fazer isto?
  public void unregisterObservers(){
    _observers = new ArrayList<>();
  }

  public void notifyObserver(NotificationType notificationType){
    for (Observer i : _observers){
      i.update(this.KEY,notificationType);
    }
    unregisterObservers();
  }

  //QUESTIONS verificar se o terminal de chegada é basic numa video commu
}
