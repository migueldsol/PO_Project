package prr.core;

import java.io.Serializable;
import java.io.IOException;
import prr.core.exception.UnrecognizedEntryException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

  public void addClient(Client client) {
    _clients.put(client.getKey(),client);
  }

  public Map<String,Client> getAllClients() {
    //FIXME problemas de privacidade
    return _clients;
  }

  public void addPricingSystem(PricingSystem pricingSystem) {
    _pricingSystems.add(pricingSystem);
  }
  

  public void addTerminal(Terminal terminal) {
    _terminals.put(terminal.getKey(),terminal);
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
