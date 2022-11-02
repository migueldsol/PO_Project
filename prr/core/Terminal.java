package prr.core;

import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;


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
  private final Map<Integer, Communication> _communicationsMade;
  private final Map<Integer, Communication> _communicationsReceived;
  private TerminalState _terminalState;

  //FIXME colocar final
  private TerminalState _off;
  private TerminalState _busy;
  private TerminalState _silence;
  private TerminalState _idle;

  private final Client CLIENT;
  private double _payments;
  private double _debts;

  private final TreeMap<String, Terminal> _friendlyTerminals;

  public Terminal(String key, Client client, TerminalType terminalType) {
    KEY = key;
    CLIENT = client;
    _communicationsMade = new HashMap<>();
    _communicationsReceived = new HashMap<>();
    
    _off = new TerminalOff(this);
    _busy = new TerminalBusy(this);
    _silence = new TerminalSilence(this);
    _idle = new TerminalIdle(this);
    _terminalState = _idle;

    _friendlyTerminals = new TreeMap<>();
    TERMINAL_TYPE = terminalType;

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

  public double getPayments() {
    return _payments;
  }

  public double getDebts() {
    return _debts;
  }

  public void addFriendlyTerminal(Terminal newTerminal) {
    _friendlyTerminals.put(newTerminal.getKey(), newTerminal);
  }

  public void removeFriendlyTerminal(Terminal newTerminal){
    _friendlyTerminals.remove(newTerminal.getKey(),newTerminal);
  }
  abstract public TerminalType getTerminalType();

  public boolean hasActivity(){
    return !_communicationsMade.isEmpty() || !_communicationsReceived.isEmpty();
  }
  @Override
  public String toString() {
    if (_friendlyTerminals.isEmpty()) {
      return getTerminalType().name() + "|" + KEY + "|" + CLIENT.getKey() + "|" + _terminalState.toString() + "|"
              + Math.round(_debts) + "|" + Math.round(_payments);
    }
    List<String> friends = new ArrayList<>(_friendlyTerminals.keySet());

    return getTerminalType().name() + "|" + KEY + "|" + CLIENT.getKey() + "|" + _terminalState.toString() + "|"
            + Math.round(_debts) + "|" + Math.round(_payments) + "|" + String.join(",",friends);
  }
}
