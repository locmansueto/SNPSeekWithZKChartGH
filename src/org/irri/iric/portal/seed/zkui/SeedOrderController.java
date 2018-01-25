package org.irri.iric.portal.seed.zkui;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.admin.WorkspaceFacade;
import org.irri.iric.portal.domain.Variety;
import org.irri.iric.portal.seed.InlineEditingViewModel;
import org.irri.iric.portal.seed.OrderData;
import org.irri.iric.portal.seed.service.SeedOrderService;
import org.irri.iric.portal.variety.VarietyFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.bind.impl.BinderUtil;
import org.zkoss.json.JSONObject;
import org.zkoss.support.smalltalk.recaptcha.RecaptchaVerifier;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Row;
import org.zkoss.zul.SimpleListModel;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

@Controller
@Scope(value = "session")

public class SeedOrderController extends SelectorComposer<Window> {

	// @Autowired
	// VarietyFacade variety;
	@Autowired
	WorkspaceFacade workspace;
	@Autowired
	@Qualifier("IRGCSeedOrderService")
	SeedOrderService orderservice;
	@Autowired
	VarietyFacade variety;

	final String SECRET = "6Lfjt0AUAAAAAKh3mwPghq2_Kqk8D_OayVaMdTId";
	/*
	 * @Wire private Listbox listboxVarlist;
	 */
	@Wire
	private Button buttonOrder;
	@Wire
	private Button buttonClear;
	@Wire
	private Textbox textboxName;
	@Wire
	private Textbox textboxInstitution;
	@Wire
	private Textbox textboxAddress1;
	@Wire
	private Textbox textboxAddress2;
	@Wire
	private Listbox listboxCountry;
	@Wire
	private Listbox listboxCategory;
	@Wire
	private Textbox textboxAutname;
	@Wire
	private Textbox textboxAutaddress;
	@Wire
	private Textbox textboxPosition;

	@Wire
	private Listbox listboxVarlists;

	@Wire
	private Grid gridOrders;
	@Wire
	private Textbox textboxTotalGram;
	@Wire
	private Textbox textboxTotalPrice;
	@Wire
	private Spinner spinnerAllGrams;
	@Wire
	private Button buttonAddList;
	@Wire
	private Div divBindgrid;

	@Wire
	private Textbox textboxCountryCategory;
	@Wire
	private Button buttonAllGrams;
	@Wire
	private Textbox textboxNoItems;
	// private order vm;
	@Wire
	private Button butonAddAccession;
	@Wire
	private Listbox listboxAccession;

	@Wire
	private Button butonAddVarname;
	@Wire
	private Listbox listboxVarname;
	@Wire
	private Div divSMTA;
	@Wire
	private Button buttonSendOrder;
	@Wire
	private Iframe iframePDF;

	@Wire
	private Listbox listboxPdfMethod;

	@Wire
	private Row rowAuthorized;
	@Wire
	private Radio radioShrink;
	@Wire
	private Radio radioSigned;
	@Wire
	private Checkbox checkboxAcceptSMTA;
	@Wire
	private Textbox textboxEmailReq;
	@Wire
	private Textbox textboxEmailAuth;
	@Wire
	private Textbox textboxPhoneReq;
	@Wire
	private Textbox textboxPhoneAuth;
	@Wire
	private Hbox hboxAcceptanceType;
	@Wire
	private Textbox textboxPostalCode;

	private Boolean userClick;

	public SeedOrderController() {
		super();
		userClick = false;
		variety = (VarietyFacade) AppContext.checkBean(variety, "VarietyFacade");
		workspace = (WorkspaceFacade) AppContext.checkBean(workspace, "WorkspaceFacade");

		orderservice = (SeedOrderService) AppContext.checkBean(orderservice, "IRGCSeedOrderService");

		AppContext.debug("SeedOrderController() created");
	}

	@Override
	public void doAfterCompose(Window comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		try {

			AppContext.debug("doAfterCompose() started");

			// initialization
			workspace = (WorkspaceFacade) AppContext.checkBean(workspace, "WorkspaceFacade");
			variety = (VarietyFacade) AppContext.checkBean(variety, "VarietyFacade");
			orderservice = (SeedOrderService) AppContext.checkBean(orderservice, "SeedOrderService");

			List listVars = new ArrayList();
			listVars.add("");
			listVars.addAll(workspace.getVarietylistNames());
			listVars.add("create new list...");
			listboxVarlists.setModel(new SimpleListModel(listVars));
			if (listboxVarlists.getRows() > 0)
				listboxVarlists.setSelectedIndex(0);

			orderservice = (SeedOrderService) AppContext.checkBean(orderservice, "SeedOrderService");

			listboxCountry.setModel(new SimpleListModel(orderservice.getCountries()));
			listboxCountry.setSelectedIndex(0);

			spinnerAllGrams.setStep(10);
			spinnerAllGrams.setSelectionRange(10, 20);

			List listAcc = new ArrayList();
			listAcc.addAll(new TreeSet(variety.getIRGCAccessions()));
			listboxAccession.setModel(new SimpleListModel(listAcc));

			List listVarname = new ArrayList();
			listVarname.addAll(new TreeSet(variety.getIRGCVarietyNames()));
			listboxVarname.setModel(new SimpleListModel(listVarname));
			// listboxPdfMethod.setVisible(AppContext.isIRRILAN());

			AppContext.debug("doAfterCompose() done");

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// getMyChildren(gridOrders,0);
		// gridOrders.getChildren()
	}

	public void getMyChildren(Component c, int level) {
		String label = "";
		if (c instanceof Label)
			label = ((Label) c).getValue();

		// System.out.println(level +" " + c.getId() + " " + c.getClass() + " "
		// + label + " atts:" + c.getAttributes().keySet());
		for (Component ch : c.getChildren()) {
			getMyChildren(ch, level + 1);
		}

	}

	private void updateUI(InlineEditingViewModel vm) {
		// vm.updateTotal();
		OrderData o = vm.getData();
		ListModelList lm = (ListModelList) gridOrders.getModel();
		lm.clear();
		lm.addAll(o.getAllSeeds());

		// vm.getAllSeeds().size();
		textboxTotalGram.setValue(AppContext.decf.format(vm.getTotalGram()));
		textboxTotalPrice.setValue(AppContext.decf.format(vm.getTotalPrice()));
		textboxNoItems.setValue(vm.getNoItems().toString());
	}

	@Listen("onClick =#checkboxAcceptSMTA")
	public void onclickacceptsmta() {
		if (checkboxAcceptSMTA.isChecked() && userClick) {
			hboxAcceptanceType.setVisible(true);
			buttonOrder.setDisabled(false);
		} else {
			hboxAcceptanceType.setVisible(false);
			radioShrink.setSelected(true);
			onclickshrink();
			rowAuthorized.setVisible(false);
			buttonOrder.setDisabled(true);
		}

	}

	@Listen("onClick =#radioShrink")
	public void onclickshrink() {
		if (radioShrink.isSelected()) {
			rowAuthorized.setVisible(false);
		} else
			rowAuthorized.setVisible(true);
	}

	@Listen("onClick =#radioSigned")
	public void onclicksigned() {
		if (radioSigned.isSelected()) {
			rowAuthorized.setVisible(true);
		} else
			rowAuthorized.setVisible(false);
	}

	@Listen("onClick =#buttonAddAccession")
	public void onclickAccession() {
		if (listboxAccession.getSelectedItem().getLabel().isEmpty())
			return;
		try {
			if (listboxCategory.getSelectedItem().getValue().equals("pub")
					&& listboxCountry.getSelectedItem().getLabel().isEmpty()) {
				Messagebox.show("Select your country first");
				return;
			}

			// Set
			// vars=variety.getGermplasmByAccession(listboxAccession.getSelectedItem().getLabel(),
			// new HashSet(variety.getDatasets()));
			InlineEditingViewModel vm = (InlineEditingViewModel) BinderUtil.getBinder(divBindgrid).getViewModel();
			List l = new ArrayList();

			l.add(variety.getGermplasmByAccession(listboxAccession.getSelectedItem().getLabel(),
					new HashSet(variety.getDatasets())));

			// setCategoryCountry(String usercat, String country)

			OrderData o = vm.getData();
			o.setCategoryCountry((String) listboxCategory.getSelectedItem().getValue(),
					listboxCountry.getSelectedItem().getLabel());

			orderservice = (SeedOrderService) AppContext.checkBean(orderservice, "SeedOrderService");
			orderservice.addListToOrder(l, o, spinnerAllGrams.getValue());

			updateUI(vm);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Listen("onSelect =#listboxVarlists")
	public void onselectlist() {
		String listname = listboxVarlists.getSelectedItem().getLabel();
		if (listname.equals("create new list...")) {
			Executions.sendRedirect("_workspace.zul?from=variety&src=order");
			return;
		}
	}

	@Listen("onClick =#buttonAddVarname")
	public void onclickVarname() {
		if (listboxVarname.getSelectedItem().getLabel().isEmpty())
			return;

		try {
			if (listboxCategory.getSelectedItem().getValue().equals("pub")
					&& listboxCountry.getSelectedItem().getLabel().isEmpty()) {
				Messagebox.show("Select your country first");
				return;
			}
			List l = variety.getGermplasmByName(listboxVarname.getSelectedItem().getLabel(),
					new HashSet(variety.getDatasets()));
			InlineEditingViewModel vm = (InlineEditingViewModel) BinderUtil.getBinder(divBindgrid).getViewModel();

			// setCategoryCountry(String usercat, String country)

			OrderData o = vm.getData();
			o.setCategoryCountry((String) listboxCategory.getSelectedItem().getValue(),
					listboxCountry.getSelectedItem().getLabel());

			orderservice = (SeedOrderService) AppContext.checkBean(orderservice, "SeedOrderService");
			orderservice.addListToOrder(l, o, spinnerAllGrams.getValue());

			updateUI(vm);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Listen("onClick =#buttonAddList")
	public void onclickVarlist() {

		try {
			String listname = listboxVarlists.getSelectedItem().getLabel();
			if (listname.isEmpty())
				return;

			if (listboxCategory.getSelectedItem().getValue().equals("pub")
					&& listboxCountry.getSelectedItem().getLabel().isEmpty()) {
				Messagebox.show("Select your country first");
				return;
			}
			InlineEditingViewModel vm = (InlineEditingViewModel) BinderUtil.getBinder(divBindgrid).getViewModel();
			List l = new ArrayList();
			Set<Variety> sVars = workspace.getVarieties(listname);
			for (Variety v : sVars) {
				if (v.getAccession() != null && v.getAccession().startsWith("IRGC"))
					l.add(v);
			}

			// setCategoryCountry(String usercat, String country)

			OrderData o = vm.getData();
			o.setCategoryCountry((String) listboxCategory.getSelectedItem().getValue(),
					listboxCountry.getSelectedItem().getLabel());

			orderservice = (SeedOrderService) AppContext.checkBean(orderservice, "SeedOrderService");
			orderservice.addListToOrder(l, o, spinnerAllGrams.getValue());
			// vm.updateList();
			// divBindgrid.invalidate();

			updateUI(vm);

			/*
			 * List lVars=new ArrayList();
			 * lVars.addAll(workspace.getVarieties(listname)); SimpleListModel
			 * m=new SimpleListModel(lVars); m.setMultiple(true);
			 * listboxVariety.setModel(m);
			 */
			AppContext.debug(gridOrders.getModel().getClass() + "  " + gridOrders.getModel().getSize());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Listen("onClick =#buttonAllGrams")
	public void onclickAllGrams() {
		AppContext.debug("onClick #=buttonAllGrams");
		try {
			InlineEditingViewModel vm = (InlineEditingViewModel) BinderUtil.getBinder(divBindgrid).getViewModel();
			orderservice = (SeedOrderService) AppContext.checkBean(orderservice, "SeedOrderService");
			vm.setAllGrams(spinnerAllGrams.getValue());
			updateUI(vm);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Listen("onClick =#buttonClear")
	public void clearlist() {
		AppContext.debug("onClick #=buttonClear");
		try {
			InlineEditingViewModel vm = (InlineEditingViewModel) BinderUtil.getBinder(divBindgrid).getViewModel();
			orderservice = (SeedOrderService) AppContext.checkBean(orderservice, "SeedOrderService");
			orderservice.clearOrderlist(vm.getData());

			ListModelList lm = (ListModelList) gridOrders.getModel();
			lm.clear();
			updateUI(vm);

			vm.updateList();

			/*
			 * ListModelList lm= (ListModelList)gridOrders.getModel();
			 * lm.clear();
			 */
			AppContext.debug(gridOrders.getModel().getClass() + "  " + gridOrders.getModel().getSize());

			// vm.updateTotal();
			// divBindgrid.invalidate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Listen("onSelect =#listboxCountry")
	public void selectcountry() {

		textboxCountryCategory.setValue("");
		textboxCountryCategory.setVisible(false);
		String c = listboxCountry.getSelectedItem().getLabel();
		String usercat = listboxCategory.getSelectedItem().getValue();
		if (usercat.equals("pub")) {
			if (c.isEmpty()) {
				Messagebox.show("Select your country first");
				return;
			}
			orderservice = (SeedOrderService) AppContext.checkBean(orderservice, "SeedOrderService");
			String ccat = orderservice.getCountryCategory(c);

			textboxCountryCategory.setVisible(true);
			if (ccat.equals("low"))
				textboxCountryCategory.setValue("Public sector in low income country");
			else if (ccat.equals("lowmid"))
				textboxCountryCategory.setValue("Public sector in lower middle income country");
			else if (ccat.equals("upmid"))
				textboxCountryCategory.setValue("Public sector in upper middle income country");
			else if (ccat.equals("high"))
				textboxCountryCategory.setValue("Public sector in high income country");
		}
		InlineEditingViewModel vm = (InlineEditingViewModel) BinderUtil.getBinder(divBindgrid).getViewModel();
		orderservice = (SeedOrderService) AppContext.checkBean(orderservice, "SeedOrderService");
		orderservice.updatePrice(vm, usercat, c);

		updateUI(vm);

	}

	@Listen("onSelect =#listboxCategory")
	public void selectcategory() {

		textboxCountryCategory.setValue("");
		textboxCountryCategory.setVisible(false);
		String c = listboxCountry.getSelectedItem().getLabel();
		String usercat = listboxCategory.getSelectedItem().getValue();
		if (usercat.equals("pub")) {
			if (c.isEmpty()) {
				Messagebox.show("Select your country first");
				return;
			}
			orderservice = (SeedOrderService) AppContext.checkBean(orderservice, "SeedOrderService");
			String ccat = orderservice.getCountryCategory(c);

			textboxCountryCategory.setVisible(true);
			if (ccat.equals("low"))
				textboxCountryCategory.setValue("Public sector in low income country");
			else if (ccat.equals("lowmid"))
				textboxCountryCategory.setValue("Public sector in lower middle income country");
			else if (ccat.equals("upmid"))
				textboxCountryCategory.setValue("Public sector in upper middle income country");
			else if (ccat.equals("high"))
				textboxCountryCategory.setValue("Public sector in high income country");
		}

		if (usercat.equals("irri")) {
			for (Listitem item : listboxCountry.getItems()) {
				if (item.getLabel().equals("Philippines")) {
					listboxCountry.setSelectedItem(item);
					break;
				}
			}
		}
		InlineEditingViewModel vm = (InlineEditingViewModel) BinderUtil.getBinder(divBindgrid).getViewModel();
		orderservice = (SeedOrderService) AppContext.checkBean(orderservice, "SeedOrderService");
		orderservice.updatePrice(vm, usercat, c);

		updateUI(vm);

	}

	private boolean validateFields() {
		if (textboxName.getValue().trim().isEmpty() && textboxInstitution.getValue().trim().isEmpty()) {
			Messagebox.show("Name or Institution cannot be both blank");
			return false;
		}
		if (textboxAddress1.getValue().trim().isEmpty()) {
			Messagebox.show("Please provide your address");
			return false;
		}

		if (listboxCountry.getSelectedItem().getLabel().isEmpty()
				&& listboxCategory.getSelectedItem().getValue().equals("pub")) {
			Messagebox.show("Public sector requests should provide Country");
			return false;
		}

		if (!textboxEmailReq.getValue().trim().matches(".+@.+\\.[a-z]+")) {
			Messagebox.show("Please enter a valid email for requestor.");
			return false;
		}

		if (radioSigned.isSelected()) {
			if (textboxAutname.getValue().trim().isEmpty() || textboxPosition.getValue().trim().isEmpty()
					|| textboxAutaddress.getValue().trim().isEmpty()) {
				Messagebox.show("Incomplete information for Authorized official from your organization.");
				return false;
			}
			if (!textboxEmailAuth.getValue().trim().matches(".+@.+\\.[a-z]+")) {
				Messagebox.show("Please enter a valid email for authorized official.");
				return false;
			}
		}

		return true;
	}

	/**
	 * Example to get varieties in selected subpopulation on button click
	 */
	@Listen("onClick =#buttonOrder")
	public void onclickOrder() {

		try {
			if (!validateFields())
				return;

			InlineEditingViewModel vm = (InlineEditingViewModel) BinderUtil.getBinder(divBindgrid).getViewModel();
			OrderData o = vm.getData();

			o.setRequestorInfo(textboxName.getValue(), textboxInstitution.getValue(), textboxAddress1.getValue(),
					textboxAddress2.getValue(), listboxCountry.getSelectedItem().getLabel(),
					(String) listboxCategory.getSelectedItem().getValue(), textboxEmailReq.getValue(),
					textboxPhoneReq.getValue(), textboxPostalCode.getValue());
			// textboxAutname.getValue(),textboxPosition.getValue(), "","");

			o.setAuthorizedInfo(
					(checkboxAcceptSMTA.isChecked() ? (radioShrink.isSelected() ? "shrink-wrap" : "signed")
							: "not-smta"),
					textboxAutname.getValue(), textboxPosition.getValue(), textboxAutaddress.getValue(),
					textboxPhoneAuth.getValue(), textboxEmailAuth.getValue());
			orderservice = (SeedOrderService) AppContext.checkBean(orderservice, "SeedOrderService");
			String pdf = orderservice.order(o, listboxPdfMethod.getSelectedItem().getLabel());

			AppContext.debug("smtafile at " + pdf);
			if (pdf != null) {
				Messagebox.show("Your order has been sent.");
				/*
				 * iframePDF.setSrc(pdf); divSMTA.setVisible(true);
				 */

			} else {
				Messagebox.show("Order failed to send.");
			}

			/*
			 * orderservice=(SeedOrderService)AppContext.checkBean(orderservice,
			 * "SeedOrderService"); String pdf=orderservice.order(o,
			 * listboxPdfMethod.getSelectedItem().getLabel());
			 * AppContext.debug("smtafile at " + pdf); if(pdf!=null) {
			 * iframePDF.setSrc(pdf); divSMTA.setVisible(true); }
			 * 
			 */

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Listen("onClick =#buttonSendOrder")
	public void onclickSendOrder() {

		try {
			InlineEditingViewModel vm = (InlineEditingViewModel) BinderUtil.getBinder(divBindgrid).getViewModel();
			OrderData o = vm.getData();

			// AppContext.getHostname()+"/temp/"+orderidonly+"/smta.pdf";

			orderservice = (SeedOrderService) AppContext.checkBean(orderservice, "SeedOrderService");
			boolean success = false;
			if (listboxPdfMethod.getSelectedItem().getLabel().equals("docx"))
				success = orderservice.sendEmail(o.getEmailReq(),
						AppContext.getTempDir() + "smta" + o.getOrderid() + ".docx",
						"smta_" + o.getOrderid().replace("#", "") + ".docx", "SMTA for #" + o.getOrderid());
			else
				success = orderservice.sendEmail(o.getEmailReq(),
						AppContext.getTempDir() + o.getOrderid() + "/smta.pdf",
						"smta_" + o.getOrderid().replace("#", "") + ".pdf", "SMTA for #" + o.getOrderid());
			if (success)
				Messagebox.show("Order sent.");
			else
				Messagebox.show("Order failed to sent.");
		} catch (Exception ex) {
			ex.printStackTrace();
			Messagebox.show("Order failed to sent: Exception" + ex.getMessage());
		}

	}

	@Listen("onUserRespond = #recaptcha")
	public void verify(Event event) {
		JSONObject result;
		try {
			result = RecaptchaVerifier.verifyResponse(SECRET,
					((JSONObject) event.getData()).get("response").toString());

			userClick = Boolean.parseBoolean(result.get("success").toString());

			if (checkboxAcceptSMTA.isChecked() && userClick) {
				buttonOrder.setDisabled(false);
			} else {
				String errorCode = result.get("error-codes").toString();

			}
		} catch (Exception e) {
			Messagebox.show("Read and accept SMTA.");
		}

	}

	@Listen("onChange =#spinnerAllGrams")
	public void onchangeallgrams() {
		/*
		 * List<Seed> lseeds= (List<Seed>)gridOrders.getModel();
		 * setGram(spinnerAllGrams.intValue(), lseeds); ListModel
		 * m=gridOrders.getModel(); m.notifyAll(); //ListDataListiner
		 * dl=gridOrders.getl
		 * 
		 */
	}

	/*
	 * private void setGram(Integer gram, List<Seed> allSeeds) { for(Seed
	 * s:allSeeds) { s.setGram(gram); } //gridOrders.getm }
	 * 
	 * private Double getTotalGram(List<Seed> allSeeds) { double total=0;
	 * for(Seed s:allSeeds) { total+=s.getGram(); } return total; }
	 * 
	 * private Double getTotalPrice(List<Seed> allSeeds) { double total=0;
	 * for(Seed s:allSeeds) { total+=s.getPrice(); } return total;
	 * 
	 * }
	 */
}
