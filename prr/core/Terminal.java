package prr.core;

import java.io.Serializable;
import java.util.List;
// FIXME add more import if needed (cannot import from pt.tecnico or prr.app)

/**
 * Abstract terminal.
 */
abstract public class Terminal implements Serializable /* FIXME maybe addd more interfaces */{

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202208091753L;
  private String _key;
  //private List <Communication> _communicationsMade;
  //private List <Communication> _communicationsReceived;
  private TerminalState _state;
  //private Client _client;
  private double _payments;
  private double _debts;
  private List <Terminal> _friendlyTerminals;
  private TerminalType _terminalType; 

  public Terminal(String key, String type, String clientKey){
    _key = key;
    _terminalType = TerminalType.valueOf(type);
    //_client = network.getclient(clientKey);
    // _communicationsMade = new List<>();
    // _communicationsReceived = new List<>();
    _state = TerminalState.ON;
    // _friendlyTerminals = new List<>();

  }
  // FIXME define methods
  
  /**
   * Checks if this terminal can end the current interactive communication.
   *
   * @return true if this terminal is busy (i.e., it has an active interactive communication) and
   *          it was the originator of this communication.
   **/
  public boolean canEndCurrentCommunication() {
    // FIXME add implementation code
  }
  
  /**
   * Checks if this terminal can start a new communication.
   *
   * @return true if this terminal is neither off neither busy, false otherwise.
   **/
  public boolean canStartCommunication() {
    // FIXME add implementation code
  }
}
