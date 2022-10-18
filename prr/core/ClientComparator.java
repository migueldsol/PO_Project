package prr.core;

import prr.core.Client;

import java.util.Comparator;

public class ClientComparator implements Comparator<Client> {
    @Override
    public int compare(Client c1, Client c2){
        return c1.getKey().compareTo(c2.getKey());
    }
}
