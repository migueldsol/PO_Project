package prr.core;

import java.util.Comparator;

public class TerminalComparator implements Comparator<Terminal> {
    @Override
    public int compare(Terminal t1, Terminal t2){
        return t1.getKey().compareTo(t2.getKey());
    }
}
