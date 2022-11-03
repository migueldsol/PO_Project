package prr.core;

import java.util.Comparator;

public class CommunicationComparator implements Comparator<Communication> {
    @Override
    public int compare(Communication c1, Communication c2){
        return c2.getId()-c1.getId();
    }
}

