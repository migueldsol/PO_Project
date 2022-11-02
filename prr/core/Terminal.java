package prr.core;

import java.io.Serializable;
import java.text.CollationElementIterator;
import java.util.*;


/**
 * Abstract terminal.
 */
abstract public class Terminal implements Serializable{

  /**
   * Serial number for serialization.
   */
  private static final long serialVersionUID = 202208091753L;

  public final TerminalType TERMINAL_TYPE;
  private final String KEY;
  private final TreeMap<Integer, Communication> _communicationsMade;
  private final TreeMap<Integer, Communication> _communicationsReceived;
  private TerminalState _terminalState;

  private InteractiveCommunication _currentCommunication;

  //FIXME colocar final
  private TerminalState _off;
  private TerminalState _busy;
  private TerminalState _silence;
  private TerminalState _idle;

  private final Client CLIENT;
  private final TreeMap<String, Terminal> _friendlyTerminals;

  public Terminal(String key, Client client, TerminalType terminalType) {
    KEY = key;
    CLIENT = client;
    _communicationsMade = new TreeMap<>();
    _communicationsReceived = new TreeMap<>();
    
    _off = new TerminalOff(this);
    _busy = new TerminalBusy(this);
    _silence = new TerminalSilence(this);
    _idle = new TerminalIdle(this);
    _terminalState = _idle;

    _friendlyTerminals = new TreeMap<>();
    TERMINAL_TYPE = terminalType;

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
  public double getDebts(){
    double debt = 0;
    for(Communication communication: _communicationsMade.values()){
      if(!communication.isPaid()){
        debt += communication.getPrice();
      }
    }
    return debt;
  }

  public double getPayments(){
    double payment = 0;
    for(Communication communication: _communicationsMade.values()){
      if(communication.isPaid()){
        payment += communication.getPrice();
      }
    }
    return payment;
  }
  public void setState(TerminalState state){
    _terminalState = state;
  }

  public TerminalState getOff(){
    return _off;
  }

  public TerminalState getIdle(){
    return _idle;
  }
  
  public TerminalState getBusy(){
    return _busy;
  }

  public TerminalState getSilence(){
    return _silence;
  }

  public void addCommunicationMade(Communication communication){
    _communicationsMade.put(communication.getId(),communication);
  }

  public void addCommunicationReceived(Communication communication){
    _communicationsReceived.put(communication.getId(),communication);
  }
  public Communication getMadeCommunication(int id){
    return _communicationsMade.get(id);
  }

  /**
   * Checks if this terminal can start a new communication.
   *
   * @return true if this terminal is neither off neither busy, false otherwise.
   **/

  public abstract void makeCommunication(String targetKey, String type);

  public abstract void startInteractiveCommunication(String targetKey, String type);

  public String getKey() {
    return KEY;
  }

  public TerminalState getTerminalState() {
    return _terminalState;
  }

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
    this.setState(this.getBusy());
  }

  public void addInteractiveCommunicationReceived(Communication communication){
    _communicationsReceived.put(communication.getId(),communication);
    this.setState(this.getBusy());
  }

  public boolean isFriend(Terminal terminal){
    return this._friendlyTerminals.containsKey(terminal.getKey());
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
              + Math.round(getDebts()) + "|" + Math.round(getPayments());
    }
    List<String> friends = new ArrayList<>(_friendlyTerminals.keySet());

    return getTerminalType().name() + "|" + KEY + "|" + CLIENT.getKey() + "|" + _terminalState.toString() + "|"
            + Math.round(getDebts()) + "|" + Math.round(getPayments()) + "|" + String.join(",",friends);
  }
}
