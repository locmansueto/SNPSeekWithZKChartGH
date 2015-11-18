package org.irri.iric.portal.variety.zkui;

import java.util.Iterator;
import java.util.List;
import java.util.Set;


import org.irri.iric.portal.domain.CvTermUniqueValues;
import org.irri.iric.portal.variety.VarietyFacade;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.SimpleListModel;

@Component("ListboxPhenotype")
@Scope("session")
public class ListboxPhenotype extends Listbox {

	Listbox listboxPhenValue;
	Listbox listboxComparator;
	VarietyFacade variety;
	private Integer phenotype_type;
	
	 public ListboxPhenotype(Listbox values, VarietyFacade varietyfacade, Listbox comparator) {
		super();
		// TODO Auto-generated constructor stub
		listboxPhenValue = values;
		variety=varietyfacade;
		listboxComparator=comparator;

	}

	public void onSelect() {
		
		setPhenotypeConstraint();
	}
	
	
	private void setPhenotypeConstraint() {
		
		List listValues = new java.util.ArrayList();
		
		System.out.println("phenotype selected:" +  getSelectedItem().getLabel());
		
		if(!getSelectedItem().getLabel().trim().isEmpty())
		{
			//Iterator<CvTermUniqueValues> itValues = variety.getPhenotypeUniqueValues( getSelectedItem().getLabel() ).iterator();
			Object retobj[] = variety.getPhenotypeUniqueValues( getSelectedItem().getLabel() );
			phenotype_type = (Integer)retobj[1];
			Iterator<CvTermUniqueValues> itValues = ((Set)retobj[0]).iterator(); // variety.getPhenotypeUniqueValues( getSelectedItem().getLabel() ).iterator();
			while(itValues.hasNext()) {
				CvTermUniqueValues value= itValues.next();
				
				if(value==null){  
					System.out.println("null value");
					continue;
				}
				
				//System.out.println(value.toString());
				
				
				listValues.add(value.getValue());
				
				//System.out.println(value.getValue());
			}
		}
		
		listboxPhenValue.setModel(new SimpleListModel(listValues ));
		if(listValues.size()>0)
			listboxPhenValue.setSelectedIndex(0);
	}

	public int getPhenotype_type() {
		return phenotype_type;
	}

	public void setPhenotype_type(Integer phenotype_type) {
		this.phenotype_type = phenotype_type;
	}
	
	
	
}
