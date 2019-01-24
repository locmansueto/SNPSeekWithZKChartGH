package org.irri.iric.portal.seed;

import java.util.List;

import org.irri.iric.portal.AppContext;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;

public class InlineEditingViewModel {

	public OrderData getData() {
		return data;
	}

	public Integer getNoItems() {
		return data.getAllSeeds().size();
	}

	public Double getTotalGram() {
		return data.getTotalGram();
	}

	public Double getTotalPrice() {
		return data.getTotalPrice();
	}

	public List<Seed> getAllSeeds() {
		return data.getAllSeeds();
	}

	public List<String> getAllStockTypes() {
		return data.getAllStockTypes();
	}

	private OrderData data = new OrderData();

	@Command("updateTotal")
	@NotifyChange({ "totalPrice", "totalGram", "noItems", "allSeeds" })
	public void updateTotal() {

		AppContext.debug("updateTotal..." + data.getTotalPrice());
	}

	@Command("updateList")
	@NotifyChange({ "totalPrice", "totalGram", "allSeeds", "noItems" })
	public void updateList() {

		AppContext.debug("updateList..." + data.getTotalPrice());
	}

	@NotifyChange({ "totalPrice", "totalGram", "allSeeds", "noItems" })
	public void setAllGrams(int grams) {
		for (Seed s : data.getAllSeeds()) {
			s.setGram(grams);
		}
	}

	@NotifyChange({ "totalPrice", "totalGram", "allSeeds", "noItems" })
	public void updatePrice(Double priceper10gram, Double maxFree) {

		AppContext.debug("priceper10gram=" + priceper10gram + " maxFree=" + maxFree);
		for (Seed s : data.getAllSeeds()) {
			s.setPricePerGram(priceper10gram);
			s.setMaxFree(maxFree);
		}
	}

	/*
	 * private BookData data = new BookData();
	 * 
	 * public List<String> getAllStatuses() { return data.getStatuses(); }
	 * 
	 * public List<Book> getAllBooks() { return data.getAllBooks(); }
	 */

}
