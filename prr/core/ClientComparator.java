package prr.core;

import java.util.Comparator;

public class ClientComparator implements Comparator<Client> {
    @Override
    public int compare(Client c1, Client c2){
        return c1.getKey().toLowerCase().compareTo(c2.getKey().toLowerCase());
    }
}
