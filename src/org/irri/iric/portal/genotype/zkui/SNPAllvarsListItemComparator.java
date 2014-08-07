package org.irri.iric.portal.genotype.zkui;



import java.io.Serializable;
import java.util.Comparator;
 

 
public class SNPAllvarsListItemComparator implements Comparator<Object>, Serializable {
    private static final long serialVersionUID = -2127053833562854322L;
     
    private boolean asc = true;
    private int type = 0;
    
 
    public SNPAllvarsListItemComparator(boolean asc, int type) {
        this.asc = asc;
        this.type = type;
    }
 
    public int getType() {
        return type;
    }
 
    public void setType(int type) {
        this.type = type;
    }
 
    @Override
    public int compare(Object o1, Object o2) {
    	SNPAllvarsListItem variety1 = (SNPAllvarsListItem) o1;
       	SNPAllvarsListItem variety2 = (SNPAllvarsListItem) o2;
       	
        switch (type) {
        case 1: // Compare Varname
            return variety1.getVarname().compareTo(variety2.getVarname()) * (asc ? 1 : -1);
        case 2: // Compare Mismatches
            return variety1.getMismatches().compareTo(variety2.getMismatches()) * (asc ? 1 : -1);
        }
    	return 0;
 
    }
 
}