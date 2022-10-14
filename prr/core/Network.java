package prr.core;

import java.io.Serializable;
import java.io.IOException;
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
    Client newClient = new Client(key,name,taxNumber);
    if (_clients.containsKey(key)){
      return false;
    }
    _clients.put(key,newClient);
    return true;
  }

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


  public Collection <Client> getAllClients() {
    //FIXME problemas de privacidade
    Collection <Client> values = _clients.values();
    return Collections.unmodifiableCollection(values);
  }

  public boolean registerTerminal(String key, TerminalType type, Client client){
    if (_terminals.containsKey(key)){
      return false;
    }

    Terminal newTerminal;

    if(type == TerminalType.BASIC){
      newTerminal = new BasicTerminal(key, type, client);
      }
      
    else {
      newTerminal = new FancyTerminal(key, type, client);
    }
    
    this.addTerminal(newTerminal);;
    return true;
  }

  public void addTerminal(Terminal terminal) {
    _terminals.put(terminal.getKey(),terminal);
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
    // FIXME implement method
  }
}
