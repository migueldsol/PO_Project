package prr.core;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Set;
// FIXME add more import if needed (cannot import from pt.tecnico or prr.app)

/**
 * Abstract terminal.
 */
abstract public class Terminal implements Serializable /* FIXME maybe addd more interfaces */{

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202208091753L;
  private final String KEY;
  //private List <Communication> _communicationsMade;
  //private List <Communication> _communicationsReceived;
  private TerminalState _terminalState;
  private final Client CLIENT;
  private double _payments;
  private double _debts;
  private Set <Terminal> _friendlyTerminals;
  private final TerminalType TERMINAL_TYPE; 

  public Terminal(String key, String type, Client client){
    KEY = key;
    TERMINAL_TYPE = TerminalType.valueOf(type);
    CLIENT = client;
    // _communicationsMade = new List<>();
    // _communicationsReceived = new List<>();
    _terminalState = TerminalState.ON;
    _friendlyTerminals = new Set<>();

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

    //      getMethods()                    //

  /*
   * public List <Communication> getCommunicationsMade(){
   *  return Collections.unmodifiableList(_communicationsMade);
   * }
   */

   /*
    * public List <Communication> getCommunicationsReceived(){
    *   return Collections.unmodifiableList(_communicationsReceived);
    *}
    */
    
    public String getKey(){
      return KEY;
    }

    public TerminalState getTerminalState(){
      return _terminalState;
    }

    public Client getClient(){
      return CLIENT;
    }

    public double getPayments(){
      return _payments;
    }

    public double getDebts(){
      return _debts;
    }

    public Set <Terminal> getFriendlyTerminals(){
      return Collections.unmodifiableSet(_friendlyTerminals);
    }

    public TerminalType getTerminalType(){
      return TERMINAL_TYPE;
    }

    public boolean addFriendlyTerminal(Terminal newTerminal){
      return _friendlyTerminals.add(newTerminal);
    }
}
