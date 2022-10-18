package prr.core;

import java.io.Serializable;
import java.io.IOException;


import prr.core.exception.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

// FIXME add more import if needed (cannot import from pt.tecnico or prr.app)

/*
 * Class Store implements a store.
 */
public class Network implements Serializable {

  /** Serial number for serialization. */

  private static final long serialVersionUID = 202208091753L;
  private Map<String,Client> _clients;
  private List<PricingSystem> _pricingSystems;
  private Map<String,Terminal> _terminals;
  private List<Communication> _communications;

  public Network() {
    _clients = new HashMap<String,Client>();
    _pricingSystems = new ArrayList<PricingSystem>();
    PricingSystem base = new PricingSystem();
    this.addPricingSystem(base);
    _terminals = new HashMap<String,Terminal>();
    _communications = new ArrayList<Communication>();
  }

  static int roundDouble(double num){
    float number = Math.round(num);
    int newNumber = (int) number;
    return newNumber;
  }
  public Terminal getTerminal(String terminalID) throws KeyNotFoundException {
    if(!_terminals.containsKey(terminalID)){
      throw new KeyNotFoundException(terminalID);
    }
    return _terminals.get(terminalID);
  }

  public boolean registerClient(String key, String name, int taxNumber){
    if (_clients.containsKey(key)) {
      return false;
    }
    Client newClient = new Client(key,name,taxNumber);
    _clients.put(key,newClient);
    return true;
  }


  public List <String> toStringAllClients(){
    List<String> message = new ArrayList<>();
    List<Client> order = new ArrayList<>(_clients.values());
    Collections.sort(order,new ClientComparator());
    for (Client i : order){
      message.add(i.toString());
    }
    return message;
  }
  //QUESTIONS is it important to return a string to not have any clients on the app 
  //          or should i just deal w clients on the app?
  public String toStringClient(String clientID){
    Client client = _clients.get(clientID);
    if (client == null){
      return null;
    }
    return client.toString();
  }

  //FIXME pode nao ser necessario
  public Client getDeepClient(String clientID){
    Client client = _clients.get(clientID);
    Client deepClient = new Client(client.getKey(), client.getName(), client.getTaxNumber());
    Map <String, Terminal>  deepTerminals = client.getDeepTerminals();
    deepClient.setTerminals(deepTerminals);
    
    float payments = roundDouble(client.getClientPayments());
    float debts = roundDouble(client.getClientDebts());

    deepClient.setPayments(payments);
    deepClient.setDebts(debts);

    return deepClient;
  }

  //FIXME pode nao ser necessario
  public Collection <Client> getAllDeepClients() {
    Collection <Client> values = _clients.values();
    return Collections.unmodifiableCollection(values);
  }

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

  public Terminal registerTerminal(String key, TerminalType type, String clientKey)
  throws NumberFormatException, InvalidSizeKey, KeyNotFoundException, TerminalKeyAlreadyExistsException {
    Client client = this._clients.get(clientKey);
    if(client == null){
      throw new KeyNotFoundException(key);
    }
    return registerTerminal(key,type,client);
  }

  public void addTerminal(Terminal terminal) {
    _terminals.put(terminal.getKey(),terminal);
  }

  public void addFriend(Terminal terminal, Terminal friend){
    terminal.addFriendlyTerminal(friend);
  }

  public void addFriend(String terminalKey, String friendKey) throws KeyNotFoundException {
    if(!_terminals.containsKey(friendKey)){
      throw new KeyNotFoundException(friendKey);
    }
    Terminal newTerminal = this._terminals.get(terminalKey);
    Terminal _friend = this._terminals.get(friendKey);
    addFriend(newTerminal,_friend);
  }

  public void removeFriend(Terminal terminal, Terminal friend){ terminal.removeFriendlyTerminal(friend);}
  public void removeFriend(String terminalKey, String friendKey) throws KeyNotFoundException{
    if(!_terminals.containsKey(friendKey)){
      throw new KeyNotFoundException(friendKey);
    }
    Terminal newTerminal = this._terminals.get(terminalKey);
    Terminal _friend = this._terminals.get(friendKey);
    removeFriend(newTerminal,_friend);
  }

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

  public List <String> toStringAllTerminals(){
    List <String> message = new ArrayList <>();
    List<Terminal> order = new ArrayList<>(_terminals.values());
    Collections.sort(order,new TerminalComparator());
    for (Terminal i : order){
      message.add(i.toString());
    }
    return message;
  }

  public String toStringTerminal(String terminalID){
    Terminal terminal = _terminals.get(terminalID);
    return terminal.toString();
  }

  public Map <String, Terminal> getDeepTerminals(){
    return Collections.unmodifiableMap(_terminals);
  } 

  public void addPricingSystem(PricingSystem pricingSystem) {
    _pricingSystems.add(pricingSystem);
  }
  


  public Map<String,Terminal> getAllTerminals() {
    //FIXME problemas de privacidade
    return _terminals;
  }

  
  public void addCommunication(Communication communication) {
    _communications.add(communication);
  }

  public List<Communication> getAllCommunications() {
    return Collections.unmodifiableList(_communications);
  }
  

  /**
   * Read text input file and create corresponding domain entities.
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
