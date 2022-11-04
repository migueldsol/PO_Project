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
  private final Map<String, Client> _clients;
  //TODO: PricingSystem vale a pena ter uma lista visto que so aplicamos 1
  private final List<PricingSystem> _pricingSystems;
  private final Map<String, Terminal> _terminals;
  private final List<Communication> _communications;

  public Network() {
    _clients = new HashMap<>();
    _pricingSystems = new ArrayList<>();
    PricingSystem base = new BasePricingSystem();
    this.addPricingSystem(base);
    _terminals = new HashMap<>(); //FIXME passar para SortedMap
    _communications = new ArrayList<>();
  }

  /**
   * payCommunication - Pay a communication
   *
   * @param id - id of the communication
   * @param terminal - terminal where the communication is
   * @return true if the communication has been paid
   */
  public boolean payCommunication(int id, Terminal terminal) {
    if (terminal.getMadeCommunication(id) == null) {
      return false;
    } else {
      terminal.getMadeCommunication(id).pay();
      if(terminal.getClient().getType().isNormal()){
        terminal.getClient().getType().changeType();
      }
      return true;
    }
  }

  /**
   * getTerminal -> returns a terminal given a certain terminal id
   * 
   * @param terminalID - terminal identification
   * @return returns the terminal with given terminalID
   * @throws KeyNotFoundException - if hey doesn't exist
   */
  public Terminal getTerminal(String terminalID) throws KeyNotFoundException {
    if (!_terminals.containsKey(terminalID)) {
      throw new KeyNotFoundException(terminalID);
    }
    return _terminals.get(terminalID);
  }

  /**
   * registerClient -> register a new client
   * 
   * @param key - client id
   * @param name - client name
   * @param taxNumber - client tax number
   */

  public void registerClient(String key, String name, int taxNumber) throws ClientKeyAlreadyExistsException {
    if (_clients.containsKey(key)) {
      throw new ClientKeyAlreadyExistsException(key);
    }
    Client newClient = new Client(key, name, taxNumber);
    _clients.put(key, newClient);
  }

  /**
   * toStringAllClients -> returns a string with all the clients
   * 
   * @return returns a String List with all the clients ready to print
   */
  public List<String> toStringAllClients() {
    List<String> message = new ArrayList<>();
    List<Client> order = new ArrayList<>(_clients.values());
    Collections.sort(order, new ClientComparator());
    for (Client i : order) {
      message.add(i.toString());
    }
    return message;
  }

  /**
   * toStringClient -> returns a string with all the information of a client
   * 
   * @param clientID
   * @return returns client as a String ready to show
   */
  public String toStringClient(String clientID) {
    Client client = _clients.get(clientID);
    if (client == null) {
      return null;
    }
    return client.toString();
  }

  /**
   *  toStringNotificaions -> returns a string with all the notifications of a client
   * @param clientID
   * @return
   */
  public List<String> toStringNotifications(String clientID){
    Client client = _clients.get(clientID);
    if (client == null){
      return null;
    }
    return client.getNotifications();
  }

  /**
   * turnOffNotification -> turns off a notification
   * @param clientID
   * @return
   * @throws KeyNotFoundException
   */
  public boolean turnOffNotification(String clientID) throws KeyNotFoundException {
    Client client = _clients.get(clientID);
    if (client == null) {
      throw new KeyNotFoundException(clientID);
    }
    return client.setNotificationOff();
  }

  /**
   * TurnOnNotification -> turns on a notification
   * @param clientID
   * @return
   * @throws KeyNotFoundException
   */
  public boolean TurnOnNotification(String clientID) throws KeyNotFoundException {
    Client client = _clients.get(clientID);
    if (client == null) {
      throw new KeyNotFoundException(clientID);
    }
    return client.setNotificationOn();
  }

  /**
   * registerTerminal -> register a terminal in the network
   * 
   * @param key
   * @param type
   * @param client
   * @throws NumberFormatException
   * @throws InvalidSizeKey
   * @throws TerminalKeyAlreadyExistsException
   */
  public void registerTerminal(String key, TerminalType type, Client client)
      throws NumberFormatException, InvalidSizeKey, TerminalKeyAlreadyExistsException {

    Integer.parseInt(key);

    if (key.length() != 6) {
      throw new InvalidSizeKey(key);
    }
    if (_terminals.containsKey(key)) {
      throw new TerminalKeyAlreadyExistsException(key);
    }

    Terminal newTerminal;

    if (type == TerminalType.BASIC) {
      newTerminal = new BasicTerminal(key, client, type);
    }

    else {
      newTerminal = new FancyTerminal(key, client, type);
    }

    this.addTerminal(newTerminal);
    client.registerTerminal(newTerminal);
  }

  /**
   * registerTerminal -> gets the client to register the terminal
   * and registers the terminal
   * 
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
    if (client == null) {
      throw new KeyNotFoundException(key);
    }
    registerTerminal(key, type, client);
  }

  /**
   * addTerminal -> adds a terminal to the network
   * 
   * @param terminal
   */

  public void addTerminal(Terminal terminal) {
    _terminals.put(terminal.getKey(), terminal);
  }

  /**
   * addFriend -> addFriendlyTerminal
   * 
   * @param terminal
   * @param friend
   */
  public void addFriend(Terminal terminal, Terminal friend) {
    terminal.addFriendlyTerminal(friend);
  }

  /**
   * addFriend -> addFriendlyTerminal
   * 
   * @param terminalKey
   * @param friendKey
   * @throws KeyNotFoundException
   */
  public void addFriend(String terminalKey, String friendKey) throws KeyNotFoundException {
    if (!_terminals.containsKey(friendKey)) {
      throw new KeyNotFoundException(friendKey);
    }
    else if(!terminalKey.equals(friendKey)) {
      Terminal newTerminal = _terminals.get(terminalKey);
      Terminal friend = _terminals.get(friendKey);
      addFriend(newTerminal, friend);
    }
  }

  /**
   * removeFriend -> remove a friend from a terminal
   * 
   * @param terminal
   * @param friend
   */
  public void removeFriend(Terminal terminal, Terminal friend) {
    terminal.removeFriendlyTerminal(friend);
  }

  /**
   * removeFriend -> gets the terminal and its friend
   * and removes the friend
   * 
   * @param terminalKey
   * @param friendKey
   * @throws KeyNotFoundException
   */
  public void removeFriend(String terminalKey, String friendKey) throws KeyNotFoundException {
    if (!_terminals.containsKey(friendKey)) {
      throw new KeyNotFoundException(friendKey);
    }
    Terminal newTerminal = _terminals.get(terminalKey);
    Terminal _friend = _terminals.get(friendKey);
    removeFriend(newTerminal, _friend);
  }

  /**
   * unusedTerminalsToString -> returns a string with all the unused terminals
   * 
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

  public List<String> doShowAllCommunications(){
    List<String> message = new ArrayList<>();
    for(Communication comm: _communications){
      message.add(comm.toString());
    }
    return message;
  }

  /**
   * toStringAllTerminals -> returns a message with all terminals
   * 
   * @return
   */
  public List<String> toStringAllTerminals() {
    List<String> message = new ArrayList<>();
    List<Terminal> order = new ArrayList<>(_terminals.values());
    Collections.sort(order, new TerminalComparator());
    for (Terminal i : order) {
      message.add(i.toString());
    }
    return message;
  }

  /**
   * addPricingSystem -> adds a pricing system to the network
   * 
   * @param pricingSystem
   */
  public void addPricingSystem(PricingSystem pricingSystem) {
    _pricingSystems.add(pricingSystem);
  }

  /**
   * checkTerminals -> checks if the terminals are equal
   * @param terminalOne
   * @param terminalTwo
   * @return
   */
  public boolean checkTerminals(Terminal terminalOne, Terminal terminalTwo) {
    return terminalOne.getKey().equals(terminalTwo.getKey());
  }

  /**
   * checkTerminalKey -> checks if the terminal key exist
   * @param terminal
   * @return the terminal
   * @throws KeyNotFoundException
   */
  public Terminal checkTerminalKey(String terminal) throws KeyNotFoundException {
    if (_terminals.get(terminal) == null) {
      throw new KeyNotFoundException(terminal);
    }
    return _terminals.get(terminal);
  }

  public void addCommunications(Terminal terminal, Terminal targetTerminal, Communication communication) {
    terminal.addCommunicationMade(communication);
    targetTerminal.addCommunicationReceived(communication);
    _communications.add(communication);
  }


  //FIXME e possivel que seja para por nos terminais (disse o Francisco)
  public boolean textCommunication(Terminal terminal, String secondTerminal, String message)
      throws KeyNotFoundException {
    Terminal targetTerminal = checkTerminalKey(secondTerminal);
    // QUESTIONS substituir "SILENCE" pelo atributo da classe? É seguro?
    if (targetTerminal.getTerminalState().isOff()) {
      if (terminal.getClient().getNotificationsOn()){
        targetTerminal.registerObserver(terminal.getClient());
      }
      return false;
    }
    if (checkTerminals(terminal, targetTerminal)) {
      return true;  //SOL isto esta certo?
    }
    TextCommunication communication = new TextCommunication((_communications.size() + 1), terminal, targetTerminal,
        message);
    communication.setPrice(terminal.getClient().getType().getTarrif(communication));
    if (terminal.isFriend(targetTerminal.getKey())) {
      communication.discount();
    }
    communication.endCommunication();
    addCommunications(terminal, targetTerminal, communication);
    if(terminal.getClient().getType().isGold() || terminal.getClient().getType().isPlatinum()){
      terminal.getClient().getType().changeType();
    }
    return true;
  }

  public double getClientPayments(String clientId) throws KeyNotFoundException {
    Client client = _clients.get(clientId);
    if (client == null) {
      throw new KeyNotFoundException(clientId);
    }
    return client.getClientPayments();
  }

  public double getClientDebts(String clientId) throws KeyNotFoundException {
    Client client = _clients.get(clientId);
    if (client == null) {
      throw new KeyNotFoundException(clientId);
    }
    return client.getClientDebts();
  }

  public void addInteractiveCommunication(Terminal terminal, Terminal targetTerminal, Communication communication) {
    terminal.addInteractiveCommunicationMade(communication);
    targetTerminal.addInteractiveCommunicationReceived(communication);
    _communications.add(communication);
  }

  public String startInteractiveCommunication(Terminal terminal, String terminalKey, String typeComm)
      throws KeyNotFoundException, UnsuportedInteractiveCommunicationException, FailedInteractiveCommunicationException,Exception {
    Terminal targetTerminal = checkTerminalKey(terminalKey);
    if (typeComm.equals("VIDEO") && targetTerminal.getTerminalType() == TerminalType.BASIC) {
      throw new UnsuportedInteractiveCommunicationException();
    } 
    else if (!targetTerminal.getTerminalState().canReceiveInteractiveCommunication()) {
      if (terminal.getClient().getNotificationsOn()){
        //FIXME estou a adicionar uma notificação e devia de fazer uma função no network para isto
        targetTerminal.registerObserver(terminal.getClient());
      }
      throw new FailedInteractiveCommunicationException(targetTerminal.getTerminalState());
    }
    if (checkTerminals(terminal, targetTerminal)) {
      throw new Exception();
    }
    InteractiveCommunication communication;
    if (typeComm.equals("VIDEO")) {
      communication = new VideoCommunication((_communications.size() + 1), terminal, targetTerminal);
    } else {
      communication = new VoiceCommunication((_communications.size() + 1), terminal, targetTerminal);
    }
    addInteractiveCommunication(terminal, targetTerminal, communication);
    terminal.addCurrentCommunication(communication);
    return null;
  }

  public double endCommunication(Terminal terminal, int duration) {
    InteractiveCommunication current = terminal.getCurrentComunication();
    current.setDuration(duration);
    current.setPrice(terminal.getClient().getType().getTarrif(current));
    if (terminal.isFriend(current.getDestinationId())) {
      current.discount(); 
    }
    current.endCommunication();
    Terminal target = _terminals.get(current.getDestinationId());
    target.setState(target.getPreviousState());
    terminal.removeCurrentCommunication();
    terminal.setState(terminal.getPreviousState());
    if(terminal.getClient().getType().isGold() || terminal.getClient().getType().isPlatinum()){
      terminal.getClient().getType().changeType();
    }
    return current.getPrice();
  }

  public String showOngoingCommunication(Terminal terminal) throws OngoingCommunicationNotFound{
    if(terminal.getCurrentComunication() == null){
      throw new OngoingCommunicationNotFound();
    }
    return terminal.getCurrentComunication().toString();
  }

  public void registerObserver(Terminal Senderterminal, String targetTerminalKey){
    Terminal targetTerminal = _terminals.get(targetTerminalKey);

    targetTerminal.registerObserver(Senderterminal.getClient());
  }

  /**
   * importFile ->Read text input file and create corresponding domain entities.
   * 
   * @param filename name of the text input file
   * @throws UnrecognizedEntryException if some entry is not correct
   * @throws IOException                if there is an IO erro while processing
   *                                    the text file
   */
  void importFile(String filename) throws UnrecognizedEntryException, IOException {
    Parser parser = new Parser(this);
    parser.parseFile(filename);
  }

}
