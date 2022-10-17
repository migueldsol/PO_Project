package prr.core;

import java.io.Serializable;
import java.io.IOException;

import prr.app.exception.DuplicateClientKeyException;
import prr.app.exception.DuplicateTerminalKeyException;
import prr.app.exception.InvalidTerminalKeyException;
import prr.app.exception.UnknownClientKeyException;
import prr.core.exception.UnrecognizedEntryException;
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


  public boolean registerClient(String key, String name, int taxNumber){
    if (_clients.containsKey(key)) {
      return false;
    }
    Client newClient = new Client(key,name,taxNumber);
    _clients.put(key,newClient);
    return true;
  }


  public List <String> toStringAllClients(){
    List <String> message = new ArrayList <>();
    for (Client i : _clients.values()){
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
    
    Double payments = client.getClientPayments();
    Double debts = client.getClientDebts();

    deepClient.setPayments(payments);
    deepClient.setDebts(debts);

    return deepClient;
  }

  //FIXME pode nao ser necessario
  public Collection <Client> getAllDeepClients() {
    Collection <Client> values = _clients.values();
    return Collections.unmodifiableCollection(values);
  }

  public Terminal registerTerminal(String key, TerminalType type, Client client) throws InvalidTerminalKeyException, DuplicateTerminalKeyException{
    try{
      Integer.parseInt(key);
    } catch (NumberFormatException e){
      throw new InvalidTerminalKeyException(key);
    }
    if (key.length() != 6){
      throw new InvalidTerminalKeyException(key);
    }
    if (_terminals.containsKey(key)){
      throw new DuplicateTerminalKeyException(key);
    }

    Terminal newTerminal;

    if(type == TerminalType.BASIC){
      newTerminal = new BasicTerminal(key, type, client);
      }
      
    else {
      newTerminal = new FancyTerminal(key, type, client);
    }
    
    this.addTerminal(newTerminal);;
    return newTerminal;
  }

  public Terminal registerTerminal(String key, TerminalType type, String clientKey) throws DuplicateTerminalKeyException, InvalidTerminalKeyException {
    Client client = this._clients.get(clientKey);
    return registerTerminal(key,type,client);
  }

  public void addTerminal(Terminal terminal) {
    _terminals.put(terminal.getKey(),terminal);
  }

  public void addFriend(Terminal terminal, Terminal friend){
    terminal.addFriendlyTerminal(friend);
  }

  public void addFriend(String terminalKey, String friendKey) throws UnknownClientKeyException {

    Integer terminal = Integer.valueOf(terminalKey);
    Integer friend = Integer.valueOf(friendKey);
    Terminal newTerminal = this._terminals.get(terminal);
    Terminal _friend = this._terminals.get(friend);
    addFriend(newTerminal,_friend);
  }

  public List <String> toStringAllTerminals(){
    List <String> message = new ArrayList <>();
    for (Terminal i : _terminals.values()){
      message.add(i.toString());
    }
    return message;
  }
  //QUESTIONS is it important to return a string to not have any clients on the app
  //          or should i just deal w clients on the app?
  public String toStringTerminal(Terminal terminalID){
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
  

  // FIXME define attributes
  // FIXME define contructor(s)
  // FIXME define methods

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
