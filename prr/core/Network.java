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
    PricingSystem base = new BasePricingSystem();
    this.addPricingSystem(base);
    _terminals = new HashMap<String,Terminal>();
    _communications = new ArrayList<Communication>();
  }

  /**
   *
   * @param id
   * @param terminal
   * @return
   */
  public boolean payCommunication(int id, Terminal terminal){
    if(terminal.getMadeCommunication(id) == null){
      return false;
    }
    else{
      // FIXME: resolve payment
      return true;
    }
  }

  /**
   * getTerminal -> returns a terminal given a certain terminal id
   * @param terminalID
   * @return returns the terminal with given terminalID
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
   * @return  return if the client was added to the network
   */

  public void registerClient(String key, String name, int taxNumber) throws ClientKeyAlreadyExistsException{
    if (_clients.containsKey(key)) {
      throw new ClientKeyAlreadyExistsException(key);
    }
    Client newClient = new Client(key,name,taxNumber);
    _clients.put(key,newClient);
  }

  /**
   * toStringAllClients -> returns a string with all the clients
   * @return returns a String List with all the clients ready to print
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
   * @return returns client as a String ready to show
   */
  public String toStringClient(String clientID){
    Client client = _clients.get(clientID);
    if (client == null){
      return null;
    }
    return client.toString();
  }


  public boolean turnOffNotification(String clientID) throws KeyNotFoundException{
    Client client = _clients.get(clientID);
    if (client == null){
      throw new KeyNotFoundException(clientID);
    }
    return client.setNotificationOff();
  }

  public boolean TurnOnNotification(String clientID) throws KeyNotFoundException{
    Client client = _clients.get(clientID);
    if (client == null){
      throw new KeyNotFoundException(clientID);
    }
    return client.setNotificationOn();
  }
  
 /**
   * registerTerminal -> register a terminal in the network
   * @param key
   * @param type
   * @param client
   * @throws NumberFormatException
   * @throws InvalidSizeKey
   * @throws TerminalKeyAlreadyExistsException
   */
  public void registerTerminal(String key, TerminalType type, Client client) throws NumberFormatException,InvalidSizeKey, TerminalKeyAlreadyExistsException{
    
    Integer.parseInt(key);

    if (key.length() != 6){
      throw new InvalidSizeKey(key);
    }
    if (_terminals.containsKey(key)){
      throw new TerminalKeyAlreadyExistsException(key);
    }

    Terminal newTerminal;

    if(type == TerminalType.BASIC){
      newTerminal = new BasicTerminal(key, client, type);
      }

    else {
      newTerminal = new FancyTerminal(key, client,type);
    }
    
    this.addTerminal(newTerminal);
    client.registerTerminal(newTerminal);
  }

  /**
   * registerTerminal -> gets the client to register the terminal
   * and registers the terminal
   * @param key
   * @param type
   * @param clientKey
   * @throws NumberFormatException
   * @throws InvalidSizeKey
   * @throws KeyNotFoundException
   * @throws TerminalKeyAlreadyExistsException
   */
  public void registerTerminal(String key, TerminalType type, String clientKey)
  throws NumberFormatException, InvalidSizeKey, KeyNotFoundException, TerminalKeyAlreadyExistsException {
    Client client = _clients.get(clientKey);
    if(client == null){
      throw new KeyNotFoundException(key);
    }
    registerTerminal(key,type,client);
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
    Terminal newTerminal = _terminals.get(terminalKey);
    Terminal friend = _terminals.get(friendKey);
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
   * removeFriend -> gets the terminal and its friend
   * and removes the friend 
   * @param terminalKey
   * @param friendKey
   * @throws KeyNotFoundException
   */
  public void removeFriend(String terminalKey, String friendKey) throws KeyNotFoundException{
    if(!_terminals.containsKey(friendKey)){
      throw new KeyNotFoundException(friendKey);
    }
    Terminal newTerminal = _terminals.get(terminalKey);
    Terminal _friend = _terminals.get(friendKey);
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

  public Terminal checkTerminalKey(String terminal) throws KeyNotFoundException{
    if(_terminals.get(terminal)== null){
      throw new KeyNotFoundException(terminal);
    }
    return _terminals.get(terminal);
  }
  public void addCommunications(Terminal terminal, Terminal targetTerminal, Communication communication){
    terminal.addCommunicationMade(communication);
    targetTerminal.addCommunicationReceived(communication);
    _communications.add(communication);
  }
  public boolean textCommunication(Terminal terminal,String secondTerminal,String message) throws  KeyNotFoundException{
    Terminal targetTerminal = checkTerminalKey(secondTerminal);
    //QUESTIONS substituir "SILENCE" pelo atributo da classe? É seguro?
    if(targetTerminal.getTerminalState().toString().equals("OFF")){
      return false;
    }
    if(_communications.isEmpty()) {
      TextCommunication communication = new TextCommunication(1, terminal, targetTerminal, message);
      addCommunications(terminal,targetTerminal,communication);
    }else{
      Communication temp = _communications.get((_communications.size()-1));
      TextCommunication communication = new TextCommunication(temp.getId(), terminal, targetTerminal, message);
      addCommunications(terminal,targetTerminal,communication);
    }
    return true;
  }
  public double getClientPayments(String clientId) throws KeyNotFoundException{
    Client client = _clients.get(clientId);
    if (client == null){
      throw new KeyNotFoundException(clientId);
    }
    return client.getClientPayments();
  }

  public double getClientDebts(String clientId) throws KeyNotFoundException{
    Client client = _clients.get(clientId);
    if (client == null){
      throw new KeyNotFoundException(clientId);
    }
    return client.getClientDebts();
  }


  /**
   * importFile ->Read text input file and create corresponding domain entities.
   * 
   * @param filename name of the text input file
   * @throws UnrecognizedEntryException if some entry is not correct
   * @throws IOException                if there is an IO erro while processing
   *                                    the text file
   */
  void importFile(String filename) throws UnrecognizedEntryException, IOException{
    Parser parser = new Parser(this);
    parser.parseFile(filename);
  }

}
