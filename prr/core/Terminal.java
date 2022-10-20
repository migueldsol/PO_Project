package prr.core;

import java.io.Serializable;
import java.util.*;

// FIXME add more import if needed (cannot import from pt.tecnico or prr.app)

/**
 * Abstract terminal.
 */
abstract public class Terminal implements Serializable /* FIXME maybe addd more interfaces */ {

  /**
   * Serial number for serialization.
   */
  private static final long serialVersionUID = 202208091753L;
  private final String KEY;
  private Map<Integer, Communication> _communicationsMade;
  private Map<Integer, Communication> _communicationsReceived;
  private TerminalState _terminalState;
  private final Client CLIENT;
  private double _payments;
  private double _debts;

  private TreeMap<String, Terminal> _friendlyTerminals;

  public Terminal(String key, TerminalType type, Client client) {
    KEY = key;
    CLIENT = client;
    _communicationsMade = new HashMap<>();
    _communicationsReceived = new HashMap<>();
    _terminalState = TerminalState.IDLE;
    _friendlyTerminals = new TreeMap<>();

  }

  /**
   * Checks if this terminal can end the current interactive communication.
   *
   * @return true if this terminal is busy (i.e., it has an active interactive
   * communication) and
   * it was the originator of this communication.
   **/

  public boolean canEndCurrentCommunication() {
    if(_terminalState == TerminalState.BUSY){
      return true;
    }
    return false;
  }

  public boolean changeState(TerminalState state){
    switch (state) {
      case IDLE -> changeToIdle();
      case SILENCE -> changeToSilence();
      case BUSY -> changeToBusy();
      case OFF -> changeToOff();
    }
    return false;
  }

  public boolean changeToIdle() {
    if (_terminalState == TerminalState.SILENCE || _terminalState == TerminalState.OFF
            || _terminalState == TerminalState.BUSY) {
      _terminalState = TerminalState.IDLE;
      return true;
    } else {
      return false;
    }

  }

  public boolean changeToSilence() {
    if (_terminalState == TerminalState.BUSY || _terminalState == TerminalState.IDLE) {
      _terminalState = TerminalState.SILENCE;
      return true;
    } else {
      return false;
    }

  }

  public boolean changeToBusy() {
    if (_terminalState == TerminalState.IDLE || _terminalState == TerminalState.SILENCE) {
      _terminalState = TerminalState.BUSY;
      return true;
    } else {
      return false;
    }

  }

  public boolean changeToOff() {
    if (_terminalState == TerminalState.IDLE || _terminalState == TerminalState.SILENCE) {
      _terminalState = TerminalState.OFF;
      return true;
    } else {
      return false;
    }

  }

  /**
   * Checks if this terminal can start a new communication.
   *
   * @return true if this terminal is neither off neither busy, false otherwise.
   **/

  public boolean canStartCommunication() {
    if(_terminalState == TerminalState.OFF || _terminalState == TerminalState.BUSY){
      return false;
    }
    return true;
  }

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
    if(!_communicationsMade.isEmpty() || !_communicationsReceived.isEmpty()){
      return true;
    }
    return false;
  }
  @Override
  public String toString() {
    if (_friendlyTerminals.isEmpty()) {
      return getTerminalType().name() + "|" + KEY + "|" + CLIENT.getKey() + "|" + _terminalState.name() + "|"
              + Math.round(_debts) + "|" + Math.round(_payments);
    }
    List<String> friends = new ArrayList<>(_friendlyTerminals.keySet());

    return getTerminalType().name() + "|" + KEY + "|" + CLIENT.getKey() + "|" + _terminalState.name() + "|"
            + Math.round(_debts) + "|" + Math.round(_payments) + "|" + String.join(",",friends);
  }
}
