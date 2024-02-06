package prr.core;

import java.util.Comparator;

public class ClientDebtComparator implements Comparator<Client> {
    @Override
    public int compare(Client c1, Client c2){
         if(c2.getClientDebts() - c1.getClientDebts() == 0){
             return c1.getKey().toLowerCase().compareTo(c2.getKey().toLowerCase());
         }
         return (int) (c2.getClientDebts() - c1.getClientDebts());
    }
}
