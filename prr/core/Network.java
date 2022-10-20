package prr.core;

import java.io.Serializable;
import java.io.IOException;


import prr.core.exception.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collections;
import java.util.HashMap;

public class Network implements Serializable {

  /** Serial number for serialization. */

  private static final long serialVersionUID = 202208091753L;
  private final Map<String,Client> _clients;
  private final List<PricingSystem> _pricingSystems;
  private final Map<String,Terminal> _terminals;
  private final List<Communication> _communications;

  public Network() {
    _clients = new HashMap<String,Client>();
    _pricingSystems = new ArrayList<PricingSystem>();
    PricingSystem base = new PricingSystem();
    this.addPricingSystem(base);
    _terminals = new HashMap<String,Terminal>();
    _communications = new ArrayList<Communication>();
  }

  /**
   * getTerminal -> returns a terminal given a certain terminal id
   * @param terminalID
   * @return
   * @throws KeyNotFoundException
   */
  public Terminal getTerminal(String terminalID) throws KeyNotFoundException {
    if(!_terminals.containsKey(terminalID)){
      throw new KeyNotFoundException(terminalID);
    }
    return _terminals.get(terminalID);
  }

  /**
   * registerClient -> register a new client
   * @param key
   * @param name
   * @param taxNumber
   * @return
   */

  public boolean registerClient(String key, String name, int taxNumber){
    if (_clients.containsKey(key)) {
      return false;
    }
    Client newClient = new Client(key,name,taxNumber);
    _clients.put(key,newClient);
    return true;
  }

  /**
   * toStringAllClients -> returns a string with all the clients
   * @return
   */
  public List <String> toStringAllClients(){
    List<String> message = new ArrayList<>();
    List<Client> order = new ArrayList<>(_clients.values());
    Collections.sort(order,new ClientComparator());
    for (Client i : order){
      message.add(i.toString());
    }
    return message;
  }

  /**
   * toStringClient -> returns a string with all the information of a client
   * @param clientID
   * @return
   */
  public String toStringClient(String clientID){
    Client client = _clients.get(clientID);
    if (client == null){
      return null;
    }
    return client.toString();
  }

  /**
   * registerTerminal -> register a terminal in the network
   * @param key
   * @param type
   * @param client
   * @return
   * @throws NumberFormatException
   * @throws InvalidSizeKey
   * @throws TerminalKeyAlreadyExistsException
   */

  public Terminal registerTerminal(String key, TerminalType type, Client client) throws NumberFormatException,InvalidSizeKey, TerminalKeyAlreadyExistsException{
    
    Integer.parseInt(key);

    if (key.length() != 6){
      throw new InvalidSizeKey(key);
    }
    if (_terminals.containsKey(key)){
      throw new TerminalKeyAlreadyExistsException(key);
    }

    Terminal newTerminal;

    if(type == TerminalType.BASIC){
      newTerminal = new BasicTerminal(key, type, client);
      }
      
    else {
      newTerminal = new FancyTerminal(key, type, client);
    }
    
    this.addTerminal(newTerminal);
    client.registerTerminal(newTerminal);
    return newTerminal;
  }

  /**
   * refisterTerminal -> adds a terminal
   * @param key
   * @param type
   * @param clientKey
   * @return
   * @throws NumberFormatException
   * @throws InvalidSizeKey
   * @throws KeyNotFoundException
   * @throws TerminalKeyAlreadyExistsException
   */
  public Terminal registerTerminal(String key, TerminalType type, String clientKey)
  throws NumberFormatException, InvalidSizeKey, KeyNotFoundException, TerminalKeyAlreadyExistsException {
    Client client = this._clients.get(clientKey);
    if(client == null){
      throw new KeyNotFoundException(key);
    }
    return registerTerminal(key,type,client);
  }

  /**
   * addTerminal -> adds a terminal to the network
   * @param terminal
   */

  public void addTerminal(Terminal terminal) {
    _terminals.put(terminal.getKey(),terminal);
  }
  /**
   * addFriend -> addFriendlyTerminal
   * @param terminal
   * @param friend
   * @throws KeyNotFoundException
   */
  public void addFriend(Terminal terminal, Terminal friend){
    terminal.addFriendlyTerminal(friend);
  }

  /**
   * addFriend -> addFriendlyTerminal
   * @param terminalKey
   * @param friendKey
   * @throws KeyNotFoundException
   */
  public void addFriend(String terminalKey, String friendKey) throws KeyNotFoundException {
    if(!_terminals.containsKey(friendKey)){
      throw new KeyNotFoundException(friendKey);
    }
    Terminal newTerminal = this._terminals.get(terminalKey);
    Terminal friend = this._terminals.get(friendKey);
    addFriend(newTerminal, friend);
  }

  /**
   * removeFriend -> remove a friend from a terminal
   * @param terminal
   * @param friend
   */
  public void removeFriend(Terminal terminal, Terminal friend){ 
    terminal.removeFriendlyTerminal(friend);
  }

  /**
   * removeFriend -> remove a friend from a terminal
   * @param terminalKey
   * @param friendKey
   * @throws KeyNotFoundException
   */
  public void removeFriend(String terminalKey, String friendKey) throws KeyNotFoundException{
    if(!_terminals.containsKey(friendKey)){
      throw new KeyNotFoundException(friendKey);
    }
    Terminal newTerminal = this._terminals.get(terminalKey);
    Terminal _friend = this._terminals.get(friendKey);
    removeFriend(newTerminal,_friend);
  }

  /**
   * unusedTerminalsToString -> returns a string with all the unused terminals
   * @return
   */
  public List<String> unusedTerminalsToString() {
    List<String> message = new ArrayList<>();
    List<Terminal> order = new ArrayList<>(_terminals.values());
    Collections.sort(order, new TerminalComparator());
    for (Terminal i : order) {
      if (!i.hasActivity()) {
        message.add(i.toString());
      }
    }
      return message;
  }

  /**
   * toStringAllTerminals -> returns a message with all terminals
   * @return
   */
  public List <String> toStringAllTerminals(){
    List <String> message = new ArrayList <>();
    List<Terminal> order = new ArrayList<>(_terminals.values());
    Collections.sort(order,new TerminalComparator());
    for (Terminal i : order){
      message.add(i.toString());
    }
    return message;
  }

  /**
   * addPricingSystem -> adds a pricing system to the network
   * @param pricingSystem
   */
  public void addPricingSystem(PricingSystem pricingSystem) {
    _pricingSystems.add(pricingSystem);
  }

  /**
   * importFile ->Read text input file and create corresponding domain entities.
   * 
   * @param filename name of the text input file
   * @throws UnrecognizedEntryException if some entry is not correct
   * @throws IOException                if there is an IO erro while processing
   *                                    the text file
   */
  void importFile(String filename) throws UnrecognizedEntryException, IOException /* FIXME maybe other exceptions */ {
    Parser parser = new Parser(this);
    parser.parseFile(filename);
  }
}
