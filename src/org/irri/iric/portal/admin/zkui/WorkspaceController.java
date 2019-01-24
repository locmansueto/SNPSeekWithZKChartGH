package org.irri.iric.portal.admin.zkui;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.servlet.http.HttpSession;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.admin.WorkspaceFacade;
import org.irri.iric.portal.dao.ListItemsDAO;
import org.irri.iric.portal.domain.Locus;
import org.irri.iric.portal.domain.MultiReferencePosition;
import org.irri.iric.portal.domain.MultiReferencePositionImpl;
import org.irri.iric.portal.domain.MultiReferencePositionImplAllelePvalue;
import org.irri.iric.portal.domain.SnpsAllvarsPos;
import org.irri.iric.portal.domain.Variety;
import org.irri.iric.portal.domain.VarietyPlus;
import org.irri.iric.portal.genomics.GenomicsFacade;
import org.irri.iric.portal.genotype.GenotypeFacade;
//import org.irri.iric.portal.hdf5.dao.SNPUni3kVarietiesAllele1DAO;
import org.irri.iric.portal.variety.VarietyFacade;
import org.irri.iric.portal.variety.zkui.VarietyListItemRenderer;
import org.irri.iric.portal.zk.CookieController;
import org.irri.iric.portal.zk.ListboxMessageBox;
import org.irri.iric.portal.zk.SessionController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.OpenEvent;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Bandbox;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.SimpleListModel;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vbox;

@Controller
@Scope("session")
// @Scope("request")
public class WorkspaceController extends SelectorComposer<Component> {

	CookieController cookieController = new CookieController();
	SessionController sessionController = new SessionController();

	@Autowired
	private ListItemsDAO listitemsdao;
	@Autowired
	@Qualifier("WorkspaceFacade")
	private WorkspaceFacade workspace;
	@Autowired
	private VarietyFacade variety;
	@Autowired
	private GenotypeFacade genotype;
	@Autowired
	private GenomicsFacade genomics;

	@Wire
	private Checkbox checkboxSavedata;

	@Wire
	private Checkbox checkboxAutoconfirm;
	@Wire
	private Checkbox checkboxVerifySNP;

	@Wire
	private Label labelMsgSNP;
	@Wire
	private Listheader listheaderPosition;

	@Wire
	private Listbox listboxListnames;
	@Wire
	private Listbox listboxVarieties;
	@Wire
	private Listheader listheaderPhenotype;

	@Wire
	private Listbox listboxPositions;
	@Wire
	private Listbox listboxLocus;
	@Wire
	private Button buttonQueryIric;
	@Wire
	private Button buttonCreate;
	@Wire
	private Button buttonSave;
	@Wire
	private Button buttonCancel;
	@Wire
	private Button buttonDelete;
	@Wire
	private Vbox vboxEditNewList;
	@Wire
	private Textbox txtboxEditNewList;
	@Wire
	private Textbox txtboxEditListname;
	@Wire
	private Button buttonDownload;

	@Wire
	private Button buttonUpload;
	// @Wire
	// private Fileupload fileupload;

	@Wire
	private Radio radioVariety;
	@Wire
	private Radio radioSNP;
	@Wire
	private Radio radioLocus;
	@Wire
	private Listbox selectChromosome;
	@Wire
	private Div divMsgVariety;
	@Wire
	private Div divMsgSNP;

	@Wire
	private Div divMsgLocus;
	@Wire
	private Label labelNItems;

	@Wire
	private Div divSetOps;
	@Wire
	private Button buttonUnion;
	@Wire
	private Button buttonIntersect;
	@Wire
	private Button buttonAminusB;
	@Wire
	private Button buttonBminusA;
	@Wire
	private Textbox textboxResultSet;

	@Wire
	private Vbox vboxListMembers;

	@Wire
	private Textbox textboxFrom;

	@Wire
	private Checkbox checkboxSNPAlelle;
	@Wire
	private Checkbox checkboxSNPPValue;
	@Wire
	private Label labelMsgFormat;
	@Wire
	private Div divSNPMoreData;

	@Wire
	private Hbox hboxDataset;

	// @Wire
	// private Checkbox checkboxPhenotype;
	@Wire
	private Label labelVarietyFormat;

	@Wire
	private Div divHasPhenotype;
	// @Wire
	// private Label labelPhenotypeName;
	@Wire
	private Textbox textboxPhenotypename;

	@Wire
	private Radio radioQuantitative;
	@Wire
	private Radio radioCategorical;
	@Wire
	private Radio radioNoPhenotype;
	@Wire
	private Listbox listboxVariantset;
	@Wire
	private Listbox listboxDataset;
	@Wire
	private Bandbox bandboxVarietyset;

	public WorkspaceController() {
		super();
		// TODO Auto-generated constructor stub
		AppContext.debug("created WorkspaceController:" + this);
	}

	/*
	 * private boolean is3k() { return checkbox3k.isSelected(); // .isChecked();
	 * //return listboxDataset.getSelectedItem().getValue().equals("3k"); }
	 */

	private HttpSession getHttpSession() {
		return (HttpSession) Sessions.getCurrent().getNativeSession();
	}

	@Listen("onClick =#buttonDownload")
	public void onclickDownloadLists() {
		workspace = (WorkspaceFacade) AppContext.checkBean(workspace, "WorkspaceFacade");
		workspace.downloadLists();
	}

	@Listen("onClick =#checkboxVerifySNP")
	public void onclickverifySNP() {
		if (checkboxVerifySNP.isChecked()) {
			checkboxAutoconfirm.setChecked(false);
			this.checkboxAutoconfirm.setDisabled(false);
		} else {
			checkboxAutoconfirm.setChecked(true);
			checkboxAutoconfirm.setDisabled(true);
		}
	}

	@Listen("onClick =#checkboxSNPAlelle")
	// @Listen("onCheck =#checkboxSNPAlelle")
	// public void onclickcheckboxSNPAlelle(CheckEvent ev) {
	public void onclickcheckboxSNPAlelle() {
		// if(ev!=null && ev.getData()!=null)
		// checkboxSNPAlelle.setChecked((Boolean)ev.getData());
		// if(ev!=null) checkboxSNPAlelle.setChecked((Boolean)ev);
		updateSNPFormatMsg();

	}

	@Listen("onClick =#checkboxSNPPValue")
	public void onclickcheckboxSNPPValue() {
		updateSNPFormatMsg();
	}

	@Listen("onClick = #radioNoPhenotype")
	public void onclickradioNoPhenotype() {
		divHasPhenotype.setVisible(false);
		if (radioNoPhenotype.isSelected())
			labelVarietyFormat.setValue("Format: name/iris_id/accession");
		else {
			labelVarietyFormat
					.setValue("Format: name/iris_id/accession,phenotype1,phenotype2,phenotype3,.. (use ,, for missing");
			divHasPhenotype.setVisible(true);
		}
	}

	@Listen("onClick = #radioCategorical")
	public void onclickradioCategorical() {
		divHasPhenotype.setVisible(false);
		if (radioNoPhenotype.isSelected())
			labelVarietyFormat.setValue("Format: name/iris_id/accession");
		else {
			labelVarietyFormat
					.setValue("Format: name/iris_id/accession,phenotype1,phenotype2,phenotype3,.. (use ,, for missing");
			divHasPhenotype.setVisible(true);
		}
	}

	@Listen("onClick = #radioQuantitative")
	public void onclickradioQuant() {
		divHasPhenotype.setVisible(false);
		if (radioNoPhenotype.isSelected())
			labelVarietyFormat.setValue("Format: name/iris_id/accession");
		else {
			labelVarietyFormat
					.setValue("Format: name/iris_id/accession,phenotype1,phenotype2,phenotype3,.. (use ,, for missing");
			divHasPhenotype.setVisible(true);
		}
	}

	/*
	 * @Listen("onUpload =#fileupload") public void onupload() { workspace =
	 * (WorkspaceFacade)AppContext.checkBean(workspace , "WorkspaceFacade");
	 * workspace.uploadLists();
	 * 
	 * }
	 */

	@Listen("onOpen = #bandboxVarietyset")
	public void onOpencheckboxdroplistGenotyperun(OpenEvent e) throws InterruptedException {
		AppContext.debug("e.isOpen()=" + e.isOpen() + " text=" + bandboxVarietyset.getText() + "  value="
				+ bandboxVarietyset.getValue());
		if (e.isOpen()) {
			// setVarietyset(getVarietyset());
			setVarietyset(getDataset());
			return;
		}

	}

	@Listen("onSelect = #listboxDataset")
	public void onSelectcheckboxdroplistGenotyperun(Event e) throws InterruptedException {

		String str = "";

		for (Listitem li : listboxDataset.getItems()) {
			if (!li.isSelected()) {
				continue;
			}
			if (!str.isEmpty()) {
				str += ", ";
			}
			str += li.getLabel();
		}
		// Bandbox bandbox = (Bandbox)comp.getFellow("bd");
		// bandbox.setValue(str);
		bandboxVarietyset.setValue(str);
	}

	private void setVarietyset(Set s) {
		String str = "";
		for (Object li : s) {
			if (!str.isEmpty()) {
				str += ", ";
			}
			str += (String) li;
		}
		bandboxVarietyset.setValue(str);
		Set setsel = new HashSet();
		for (Listitem li : listboxDataset.getItems()) {
			if (s.contains(li.getLabel())) {
				setsel.add(li);
			}
		}
		listboxDataset.setSelectedItems(setsel);
	}

	private Set getDataset() {
		Set s = new LinkedHashSet();
		String[] ds = bandboxVarietyset.getText().split(",");
		for (int i = 0; i < ds.length; i++)
			s.add(ds[i].trim());
		return s;

		/*
		 * if(checkbox3k.isChecked()) return VarietyFacade.DATASET_SNPINDELV2_IUPAC;
		 * else if (checkboxHDRA.isChecked()) return VarietyFacade.DATASET_SNP_HDRA;
		 * else if (checkboxGQ92.isChecked()) return VarietyFacade.DATASET_SNP_GOPAL92;
		 * 
		 * return "";
		 */

		/*
		 * if(is3k()) return VarietyFacade.DATASET_SNPINDELV2_IUPAC; else return
		 * VarietyFacade.DATASET_SNP_HDRA;
		 */
	}

	private void updateSNPFormatMsg() {

		if (selectChromosome.getSelectedItem().getLabel().equals("ANY")) {

			labelMsgFormat.setValue("Format:  chromosome position");

		} else if (selectChromosome.getSelectedItem().getLabel().isEmpty()) {
			labelMsgSNP.setValue("Select chromosome/contig.");
			labelMsgFormat.setValue("Format:");

		} else {
			labelMsgSNP.setValue("Type or paste SNP positions, one position per line.");
			labelMsgFormat.setValue("Format: position");
		}

		if (this.checkboxSNPAlelle.isChecked())
			labelMsgFormat.setValue(labelMsgFormat.getValue() + "  allele");
		if (this.checkboxSNPPValue.isChecked())
			labelMsgFormat.setValue(labelMsgFormat.getValue() + "  -log(p)");

	}

	@Listen("onSelect =#selectChromosome")
	public void onselectContig() {

		if (selectChromosome.getSelectedItem().getLabel().equals("ANY")) {

			// labelMsgSNP" pre="true" value="Set chromosome and SNP positions here"/>";
			labelMsgSNP
					.setValue("Type or paste Contig and SNP positions, one contig name/chr no. and position per line.");
			divSNPMoreData.setVisible(true);

		} else if (selectChromosome.getSelectedItem().getLabel().isEmpty()) {
			labelMsgSNP.setValue("Select chromosome/contig.");
			divSNPMoreData.setVisible(false);
		} else {
			labelMsgSNP.setValue("Type or paste SNP positions, one position per line.");
			divSNPMoreData.setVisible(false);

		}
		updateSNPFormatMsg();
	}

	@Listen("onClick =#buttonUpload")
	public void onclickUploadLists() {

		Fileupload.get(new EventListener() {
			@Override
			public void onEvent(Event event) throws Exception {
				

				try {
					org.zkoss.util.media.Media media = ((UploadEvent) event).getMedia();
					workspace = (WorkspaceFacade) AppContext.checkBean(workspace, "WorkspaceFacade");
					if (workspace.uploadLists(media.getStringData())) {
						AppContext.debug("upload successfull..");

						if (radioSNP.isSelected())
							Events.sendEvent("onClick", radioSNP, null);
						else if (radioVariety.isSelected())
							Events.sendEvent("onClick", radioVariety, null);
						else if (radioLocus.isSelected())
							Events.sendEvent("onClick", radioLocus, null);
					} else
						Messagebox.show("Upload failed");

				} catch (InvocationTargetException ex) {
					AppContext.debug(ex.getCause().getMessage());
					ex.getCause().getStackTrace();
					Messagebox.show("Upload failed");
				} catch (Exception ex) {
					AppContext.debug(ex.getMessage());
					ex.getStackTrace();
					Messagebox.show("Upload failed");
				}
			}
		});

	}

	@Listen("onClick = #radioVariety")
	public void onclickVariety() {

		radioVariety.setSelected(true);
		workspace = (WorkspaceFacade) AppContext.checkBean(workspace, "WorkspaceFacade");

		List listVarlistNames = new ArrayList();
		listVarlistNames.addAll(workspace.getVarietylistNames());
		SimpleListModel model = new SimpleListModel(listVarlistNames);
		model.setMultiple(true);
		listboxListnames.setModel(model);
		if (listVarlistNames.size() > 0) {
			listboxListnames.setSelectedIndex(listVarlistNames.size() - 1);

			AppContext.debug(listboxListnames.getSelectedItem().getLabel() + "  selected");
			Events.sendEvent("onSelect", listboxListnames, null);
		} else
			labelNItems.setVisible(false);
		listboxPositions.setVisible(false);
		listboxVarieties.setVisible(true);
		listboxLocus.setVisible(false);

		// labelNItems.setVisible(false);

		divMsgVariety.setVisible(true);
		divMsgSNP.setVisible(false);
		divMsgLocus.setVisible(false);
		hboxDataset.setVisible(true);

	}

	@Listen("onClick = #radioLocus")
	public void onclickLocus() {

		radioLocus.setSelected(true);
		workspace = (WorkspaceFacade) AppContext.checkBean(workspace, "WorkspaceFacade");

		List listLocuslistNames = new ArrayList();
		listLocuslistNames.addAll(workspace.getLocuslistNames());
		SimpleListModel model = new SimpleListModel(listLocuslistNames);
		model.setMultiple(true);
		listboxListnames.setModel(model);
		if (listLocuslistNames.size() > 0) {
			listboxListnames.setSelectedIndex(listLocuslistNames.size() - 1);

			AppContext.debug(listboxListnames.getSelectedItem().getLabel() + "  selected");
			Events.sendEvent("onSelect", listboxListnames, null);
		} else
			labelNItems.setVisible(false);
		listboxPositions.setVisible(false);
		listboxVarieties.setVisible(false);
		listboxLocus.setVisible(true);

		// labelNItems.setVisible(false);

		divMsgVariety.setVisible(false);
		divMsgSNP.setVisible(false);
		divMsgLocus.setVisible(true);

		hboxDataset.setVisible(false);
	}

	@Listen("onClick = #radioSNP")
	public void onclickSNP() {

		radioSNP.setSelected(true);
		workspace = (WorkspaceFacade) AppContext.checkBean(workspace, "WorkspaceFacade");

		List listNames = new ArrayList();
		listNames.addAll(workspace.getSnpPositionListNames());
		SimpleListModel model = new SimpleListModel(listNames);
		model.setMultiple(true);
		listboxListnames.setModel(model);
		if (listNames.size() > 0) {

			listboxListnames.setSelectedIndex(listNames.size() - 1);

			AppContext.debug(listboxListnames.getSelectedItem().getLabel() + "  selected");
			Events.sendEvent("onSelect", listboxListnames, null);
		} else
			labelNItems.setVisible(false);

		listboxVarieties.setVisible(false);
		listboxPositions.setVisible(true);
		// labelNItems.setVisible(false);
		listboxLocus.setVisible(false);

		divMsgVariety.setVisible(false);
		divMsgSNP.setVisible(true);
		divMsgLocus.setVisible(false);

		updateSNPFormatMsg();

		hboxDataset.setVisible(true);
	}

	private Integer getChrFromSNPListLabel(String strlabel) {
		return Integer.valueOf(strlabel.split(":")[0].replace("CHR", "").trim());
	}

	@Listen("onClick = #buttonUnion")
	public void onclickUnion() {
		Set setUnion = new HashSet();
		Iterator<String> itSelitems = getListNamesSelection().iterator();
		workspace = (WorkspaceFacade) AppContext.checkBean(workspace, "WorkspaceFacade");

		Set setHasP = new HashSet(workspace.getSnpPositionPvalueListNames());
		Set sethasAllele = new HashSet(workspace.getSnpPositionAlleleListNames());
		boolean hasP = true;
		boolean hasAl = true;

		while (itSelitems.hasNext()) {
			String listname = itSelitems.next();
			if (radioVariety.isSelected())
				setUnion.addAll(workspace.getVarieties(listname));
			else if (radioLocus.isSelected())
				setUnion.addAll(workspace.getLoci(listname));
			else if (radioSNP.isSelected()) {
				setUnion.addAll(workspace.getSnpPositions(listname.split(":")[0], listname.split(":")[1]));
				hasP = hasP && setHasP.contains(listname);
				hasAl = hasAl && sethasAllele.contains(listname);
			}

		}
		if (radioVariety.isSelected())
			addVarlistFromSetops(setUnion);
		else if (radioLocus.isSelected())
			addLocuslistFromSetops(setUnion);
		else if (radioSNP.isSelected())
			this.addPoslistFromSetops(setUnion, hasAl, hasP);

	}

	@Listen("onClick = #buttonIntersect")
	public void onclickIntersect() {
		Set setUnion = new HashSet();
		Iterator<String> itSelitems = getListNamesSelection().iterator();
		Set setHasP = new HashSet(workspace.getSnpPositionPvalueListNames());
		Set sethasAllele = new HashSet(workspace.getSnpPositionAlleleListNames());
		boolean hasP = true;
		boolean hasAl = true;

		workspace = (WorkspaceFacade) AppContext.checkBean(workspace, "WorkspaceFacade");
		if (itSelitems.hasNext()) {
			String listname = itSelitems.next();
			if (radioVariety.isSelected())
				setUnion.addAll(workspace.getVarieties(listname));
			else if (radioLocus.isSelected())
				setUnion.addAll(workspace.getLoci(listname));
			else if (radioSNP.isSelected()) {
				setUnion.addAll(workspace.getSnpPositions(listname.split(":")[0], listname.split(":")[1]));
				hasP = hasP && setHasP.contains(listname);
				hasAl = hasAl && sethasAllele.contains(listname);

			}
		}
		while (itSelitems.hasNext()) {
			String listname = itSelitems.next();

			if (radioVariety.isSelected())
				setUnion.retainAll(workspace.getVarieties(listname));
			else if (radioLocus.isSelected())
				setUnion.retainAll(workspace.getLoci(listname));
			else if (radioSNP.isSelected()) {
				setUnion.retainAll(workspace.getSnpPositions(listname.split(":")[0], listname.split(":")[1]));
				hasP = hasP && setHasP.contains(listname);
				hasAl = hasAl && sethasAllele.contains(listname);

			}
		}
		if (radioVariety.isSelected())
			addVarlistFromSetops(setUnion);
		else if (radioLocus.isSelected())
			addLocuslistFromSetops(setUnion);
		else if (radioSNP.isSelected())
			this.addPoslistFromSetops(setUnion, hasAl, hasP);

	}

	@Listen("onClick = #buttonAminusB")
	public void onclickAminusB() {
		Set setUnion = new HashSet();

		Iterator<String> itSelitems = getListNamesSelection().iterator();
		workspace = (WorkspaceFacade) AppContext.checkBean(workspace, "WorkspaceFacade");
		if (itSelitems.hasNext()) {
			String listname = itSelitems.next();
			if (radioVariety.isSelected())
				setUnion.addAll(workspace.getVarieties(listname));
		}
		if (itSelitems.hasNext()) {
			String listname = itSelitems.next();
			if (radioVariety.isSelected())
				setUnion.removeAll(workspace.getVarieties(listname));
		}
		if (radioVariety.isSelected())
			addVarlistFromSetops(setUnion);
	}

	@Listen("onClick = #buttonBminusA")
	public void onclickBminusA() {
		Set setLast = new HashSet();
		Set setUnion = new HashSet();
		Iterator<String> itSelitems = getListNamesSelection().iterator();
		workspace = (WorkspaceFacade) AppContext.checkBean(workspace, "WorkspaceFacade");
		if (itSelitems.hasNext()) {
			String listname = itSelitems.next();
			if (radioVariety.isSelected())
				setUnion.addAll(workspace.getVarieties(listname));
		}
		if (itSelitems.hasNext()) {
			String listname = itSelitems.next();
			if (radioVariety.isSelected())
				setLast.addAll(workspace.getVarieties(listname));
		}
		setLast.removeAll(setUnion);

		if (radioVariety.isSelected())
			addVarlistFromSetops(setLast);
	}

	private Set<String> getListNamesSelection() {
		SimpleListModel listmodel = (SimpleListModel) listboxListnames.getModel();
		Set setsel = listmodel.getSelection();
		if (setsel.size() == 0 && listmodel.getSize() > 0) {

			try {
				Object selobj = listmodel.getElementAt(listmodel.getSize() - 1);
				Set newsel = new HashSet();
				newsel.add(selobj);
				listmodel.setSelection(newsel);
				return newsel;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return setsel;
	}

	@Listen("onSelect = #listboxListnames")
	public void onselectListnames() {

		workspace = (WorkspaceFacade) AppContext.checkBean(workspace, "WorkspaceFacade");

		Set<String> selSelection = getListNamesSelection();
		// Set<Listitem> setListitems = listboxVarietylist.getSelectedItems();
		// listboxVarietylist.setSeltype(seltype);

		AppContext.debug(selSelection.size() + " getSelections selected");

		// AppContext.debug(setListitems.size() + " listitems selected" );
		// AppContext.debug(listboxVarietylist.getSelectedItem() + " item selected");

		if (selSelection.size() == 0)
			return;

		// AppContext.debug( selSelection.iterator().next() + " getSelections first
		// value" );

		if (selSelection.size() > 1) {
			vboxListMembers.setVisible(false);
			divSetOps.setVisible(true);

			/*
			 * if(selSelection.size()>2) { buttonAminusB.setVisible(false);
			 * buttonBminusA.setVisible(false); } else { buttonAminusB.setVisible(true);
			 * buttonBminusA.setVisible(true);
			 * 
			 * Iterator<String> itSelitems = selSelection.iterator(); String
			 * nameA=itSelitems.next(); String nameB=itSelitems.next();
			 * buttonAminusB.setLabel( nameA + " Minus " + nameB ); buttonBminusA.setLabel(
			 * nameB + " Minus " + nameA ); }
			 */

		} else {

			if (this.buttonSave.isVisible())
				return;

			divSetOps.setVisible(false);
			vboxListMembers.setVisible(true);

			List listTmp = new ArrayList();

			if (this.radioVariety.isSelected()) {
				listboxPositions.setVisible(false);
				listboxLocus.setVisible(false);
				// listTmp.addAll( workspace.getVarieties(
				// listboxVarietylist.getSelectedItem().getLabel() ) );
				listTmp.addAll(workspace.getVarieties(selSelection.iterator().next()));

				boolean advancedcols = listTmp.iterator().next() instanceof VarietyPlus;
				listheaderPhenotype.setVisible(advancedcols);
				listboxVarieties.setItemRenderer(new VarietyListItemRenderer(!advancedcols));

				SimpleListModel model = new SimpleListModel(listTmp);
				model.setMultiple(true);
				listboxVarieties.setModel(model);
				listboxVarieties.setVisible(true);

				AppContext.debug(listTmp.size() + " variety lists");

			} else if (this.radioLocus.isSelected()) {
				listboxVarieties.setVisible(false);
				listboxPositions.setVisible(false);
				listboxLocus.setItemRenderer(new LocusListItemRenderer());
				// listTmp.addAll( workspace.getVarieties(
				// listboxVarietylist.getSelectedItem().getLabel() ) );
				listTmp.addAll(workspace.getLoci(selSelection.iterator().next()));
				SimpleListModel model = new SimpleListModel(listTmp);
				model.setMultiple(true);
				listboxLocus.setModel(model);
				listboxLocus.setVisible(true);

				AppContext.debug(listTmp.size() + " locus lists");

			} else if (this.radioSNP.isSelected()) {
				listboxVarieties.setVisible(false);
				listboxLocus.setVisible(false);
				// String snplabels[] =
				// listboxVarietylist.getSelectedItem().getLabel().trim().split(":");
				String snplabels[] = selSelection.iterator().next().trim().split(":");
				// Integer chr = Integer.valueOf( snplabels[0].replace("CHR","").trim() );
				String chr = snplabels[0].trim();

				String listname = snplabels[1].trim();

				if (chr.equals("ANY")) {
					if (workspace.SNPListhasAllele(listname) || workspace.SNPListhasPvalue(listname))
						listheaderPosition.setLabel("(GENOME, CONTIG, POSITION, ALLELE, PVALUE)");
					else
						listheaderPosition.setLabel("(GENOME, CONTIG, POSITION)");
				} else
					listheaderPosition.setLabel("CONTIG : POSITION");

				((SNPChrPositionListitemRenderer) listboxPositions.getItemRenderer()).setChromosome(chr.toString());
				listTmp.addAll(workspace.getSnpPositions(chr, listname));
				SimpleListModel model = new SimpleListModel(listTmp);
				model.setMultiple(true);
				listboxPositions.setModel(model);
				listboxPositions.setVisible(true);

				AppContext.debug(listTmp.size() + " position lists");

			}
			labelNItems.setValue(listTmp.size() + " items in list");
			labelNItems.setVisible(true);
		}
	}

	@Listen("onClick =#buttonQueryIric")
	public void onbuttonQueryIric() {
		/*
		 * System.out.println("onClick =#buttonQueryIric");
		 * System.out.println("in queryIric..." ); SNPUni3kVarietiesAllele1DAO snpuniDAO
		 * = new SNPUni3kVarietiesAllele1DAO(); Map mapVar2Str =
		 * snpuniDAO.readSNPString("1", 1000, 1100); Iterator itVar =
		 * mapVar2Str.keySet().iterator(); while(itVar.hasNext()) { Object var =
		 * itVar.next(); System.out.println( var + " : " + mapVar2Str.get(var)); }
		 */

		// workspace = (WorkspaceFacade)AppContext.checkBean(workspace ,
		// "WorkspaceFacade");
		// workspace.queryIric();
	}

	@Listen("onClick =#buttonCreate")
	public void onbuttonCreate() {
		listboxVarieties.setVisible(false);
		listboxPositions.setVisible(false);
		listboxLocus.setVisible(false);

		this.listboxListnames.setSelectedItem(null);
		listboxListnames.setDisabled(true);

		vboxEditNewList.setVisible(true);
		buttonCreate.setVisible(false);
		buttonDelete.setVisible(false);
		buttonSave.setVisible(true);
		buttonCancel.setVisible(true);
		labelNItems.setVisible(false);

		radioSNP.setDisabled(true);
		radioVariety.setDisabled(true);
		radioLocus.setDisabled(true);
		this.buttonDownload.setDisabled(true);
		this.buttonUpload.setDisabled(true);
	}

	private void afterButtonSave(boolean success) {

		AppContext.debug("textboxFrom=" + textboxFrom + "; success=" + success);
		radioSNP.setDisabled(false);
		radioVariety.setDisabled(false);
		radioLocus.setDisabled(false);
		this.buttonDownload.setDisabled(false);
		this.buttonUpload.setDisabled(false);

		if (textboxFrom != null && success) {

			AppContext.debug("supposed!! redirecting to:" + textboxFrom.getValue());

			/*
			 * try { if(textboxFrom.getValue().equals("variety")) {
			 * Executions.sendRedirect("_variety.zul?from=varietylist"); } else
			 * if(textboxFrom.getValue().equals("snp") ) {
			 * Executions.sendRedirect("_snp.zul?from=snplist"); } else
			 * if(textboxFrom.getValue().equals("snpallele") ) {
			 * Executions.sendRedirect("_snp.zul?from=snplistallele"); } else
			 * if(textboxFrom.getValue().equals("locus") ) {
			 * Executions.sendRedirect("_locus.zul?from=locuslist"); } }catch(Exception ex)
			 * { ex.printStackTrace(); }
			 */

		}

	}

	@Listen("onClick =#buttonSave")
	public void onbuttonSave() {
		boolean success = false;

		try {

			if (txtboxEditListname.getValue().trim().isEmpty()) {
				Messagebox.show("Please provide a unique list name");
				return;
			}

			if (radioVariety.isSelected())
				success = onbuttonSaveVariety();
			else if (radioSNP.isSelected())
				// success = onbuttonSaveSNP();
				onbuttonSaveSNP();
			else if (radioLocus.isSelected())
				success = onbuttonSaveLocus();

			afterButtonSave(success);

			// radioSNP.setDisabled(false);
			// radioVariety.setDisabled(false);
			// radioLocus.setDisabled(false);

			/*
			 * if(checkboxSavedata.isChecked()) { workspace =
			 * (WorkspaceFacade)AppContext.checkBean(workspace , "WorkspaceFacade");
			 * cookieController.setCookie("mylist", workspace.getMyListsCookie(),
			 * 999999999); cookieController.setCookie("storemylist",
			 * String.valueOf(checkboxSavedata.isChecked()), 999999999 ); } else {
			 * cookieController.setCookie("mylist", null, 0);
			 * cookieController.setCookie("storemylist", null, 0); }
			 */

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@Listen("onClick =#buttonCancel")
	public void onbuttonCancel() {
		vboxEditNewList.setVisible(false);
		buttonCancel.setVisible(false);
		buttonSave.setVisible(false);
		buttonCreate.setVisible(true);
		buttonDelete.setVisible(true);

		this.buttonDownload.setDisabled(false);
		this.buttonUpload.setDisabled(false);

		if (radioSNP.isSelected())
			Events.sendEvent("onClick", radioSNP, null); // onclickSNP();
		else if (radioVariety.isSelected())
			Events.sendEvent("onClick", radioVariety, null); // onclickVariety();
		else if (radioLocus.isSelected())
			Events.sendEvent("onClick", radioLocus, null); // onclickVariety();

		radioSNP.setDisabled(false);
		radioVariety.setDisabled(false);
		radioLocus.setDisabled(false);

		try {
			if (textboxFrom != null) {
				if (textboxFrom.getValue().equals("variety")) {
					Executions.sendRedirect("_variety.zul");
				} else if (textboxFrom.getValue().equals("snp")) {
					Executions.sendRedirect("_snp.zul");
				} else if (textboxFrom.getValue().equals("locus")) {
					Executions.sendRedirect("_locus.zul");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private void addPoslistFromSetops(Set setMatched, boolean hasAllele, boolean hasPvalue) {

		if (setMatched.size() > 0) {

			AppContext.debug("Adding snp list");

			if (this.textboxResultSet.getValue().trim().isEmpty()) {
				Messagebox.show("Provide unique list name", "INVALID VALUE", Messagebox.OK, Messagebox.EXCLAMATION);
				return;
			}
			if (workspace.getSnpPositions("ANY", textboxResultSet.getValue().trim()) != null
					&& !workspace.getSnpPositions("ANY", textboxResultSet.getValue().trim()).isEmpty()) {
				Messagebox.show("Listname already exists", "INVALID VALUE", Messagebox.OK, Messagebox.EXCLAMATION);
				return;
			}

			if (workspace.addSnpPositionList("ANY", textboxResultSet.getValue().trim(), setMatched, hasAllele,
					hasPvalue)) {

				AppContext.debug(textboxResultSet.getValue().trim() + " added with " + setMatched.size() + " items");

				// txtboxEditNewList.setValue("");
				textboxResultSet.setValue("");
				listboxVarieties.setVisible(true);
				// labelNItems.setValue(listTmp.size() + " items in list");

				Events.sendEvent("onClick", radioSNP, null);
				// onclickVariety();

				labelNItems.setVisible(true);

				/*
				 * listboxVarieties.setVisible(true); vboxEditNewList.setVisible(false);
				 * buttonCreate.setVisible(true); buttonDelete.setVisible(true);
				 * buttonSave.setVisible(false); buttonCancel.setVisible(false);
				 * onclickVariety();
				 * 
				 */

			} else {
				Messagebox.show("Failed to add list", "OPERATION FAILED", Messagebox.OK, Messagebox.EXCLAMATION);
			}
		} else
			Messagebox.show("Resulting list has zero element", "INVALID VALUE", Messagebox.OK, Messagebox.EXCLAMATION);

		/*
		 * Integer chr = Integer.valueOf(selectChromosome.getSelectedItem().getLabel());
		 * if(setMatched.size()>0) {
		 * 
		 * AppContext.debug("Adding SNP list");
		 * 
		 * String newlistname = txtboxEditListname.getValue().replaceAll(":",
		 * "").trim(); if(newlistname.isEmpty()) {
		 * Messagebox.show("Provide unique list name","INVALID VALUE",Messagebox.OK,
		 * Messagebox.EXCLAMATION); return; } if(workspace.getSnpPositions(chr,
		 * newlistname)!=null && !workspace.getSnpPositions(chr,newlistname).isEmpty())
		 * { Messagebox.show("Listname already exists","INVALID VALUE",Messagebox.OK,
		 * Messagebox.EXCLAMATION); return; } if(workspace.addSnpPositionList(chr,
		 * newlistname , setMatched)) {
		 * 
		 * AppContext.debug(newlistname + " added with " + setMatched.size() + " items"
		 * );
		 * 
		 * txtboxEditNewList.setValue(""); txtboxEditListname.setValue("");
		 * listboxVarieties.setVisible(true); vboxEditNewList.setVisible(false);
		 * buttonCreate.setVisible(true); buttonDelete.setVisible(true);
		 * buttonSave.setVisible(false); buttonCancel.setVisible(false);
		 * 
		 * onclickSNP();
		 * 
		 * } else {
		 * Messagebox.show("Failed to add list","OPERATION FAILED",Messagebox.OK,
		 * Messagebox.EXCLAMATION); } }
		 */
	}

	private void addVarlistFromSetops(Set setMatched) {
		if (setMatched.size() > 0) {

			AppContext.debug("Adding variety list");

			if (this.textboxResultSet.getValue().trim().isEmpty()) {
				Messagebox.show("Provide unique list name", "INVALID VALUE", Messagebox.OK, Messagebox.EXCLAMATION);
				return;
			}
			if (workspace.getVarieties(textboxResultSet.getValue().trim()) != null
					&& !workspace.getVarieties(textboxResultSet.getValue().trim()).isEmpty()) {
				Messagebox.show("Listname already exists", "INVALID VALUE", Messagebox.OK, Messagebox.EXCLAMATION);
				return;
			}

			/*
			 * boolean hasPhenotype=false; hasPhenotype = setMatched.iterator().next()
			 * instanceof VarietyPlusPlus;
			 */

			if (workspace.addVarietyList(textboxResultSet.getValue().trim(), setMatched, getDataset())) {

				AppContext.debug(textboxResultSet.getValue().trim() + " added with " + setMatched.size() + " items");

				// txtboxEditNewList.setValue("");
				textboxResultSet.setValue("");
				listboxVarieties.setVisible(true);
				// labelNItems.setValue(listTmp.size() + " items in list");

				Events.sendEvent("onClick", radioVariety, null);
				// onclickVariety();

				labelNItems.setVisible(true);

				/*
				 * listboxVarieties.setVisible(true); vboxEditNewList.setVisible(false);
				 * buttonCreate.setVisible(true); buttonDelete.setVisible(true);
				 * buttonSave.setVisible(false); buttonCancel.setVisible(false);
				 * onclickVariety();
				 * 
				 */

			} else {
				Messagebox.show("Failed to add list", "OPERATION FAILED", Messagebox.OK, Messagebox.EXCLAMATION);
			}
		} else
			Messagebox.show("Resulting list has zero element", "INVALID VALUE", Messagebox.OK, Messagebox.EXCLAMATION);

	}

	private void addLocuslistFromSetops(Set setMatched) {
		if (setMatched.size() > 0) {

			AppContext.debug("Adding locus list");

			if (this.textboxResultSet.getValue().trim().isEmpty()) {
				Messagebox.show("Provide unique list name", "INVALID VALUE", Messagebox.OK, Messagebox.EXCLAMATION);
				return;
			}
			if (workspace.getLoci(textboxResultSet.getValue().trim()) != null
					&& !workspace.getLoci(textboxResultSet.getValue().trim()).isEmpty()) {
				Messagebox.show("Listname already exists", "INVALID VALUE", Messagebox.OK, Messagebox.EXCLAMATION);
				return;
			}

			if (workspace.addLocusList(textboxResultSet.getValue().trim(), setMatched)) {

				AppContext.debug(textboxResultSet.getValue().trim() + " added with " + setMatched.size() + " items");

				// txtboxEditNewList.setValue("");
				textboxResultSet.setValue("");
				listboxVarieties.setVisible(true);
				// labelNItems.setValue(listTmp.size() + " items in list");

				Events.sendEvent("onClick", radioLocus, null);
				// onclickVariety();

				labelNItems.setVisible(true);

				/*
				 * listboxVarieties.setVisible(true); vboxEditNewList.setVisible(false);
				 * buttonCreate.setVisible(true); buttonDelete.setVisible(true);
				 * buttonSave.setVisible(false); buttonCancel.setVisible(false);
				 * onclickVariety();
				 * 
				 */

			} else {
				Messagebox.show("Failed to add list", "OPERATION FAILED", Messagebox.OK, Messagebox.EXCLAMATION);
			}
		} else
			Messagebox.show("Resulting list has zero element", "INVALID VALUE", Messagebox.OK, Messagebox.EXCLAMATION);

	}

	private boolean isMsgboxEventSuccess = false;
	private boolean isDoneModal = false;

	private Set getVariantSets() {
		Set s = new LinkedHashSet();
		for (Listitem item : listboxVariantset.getSelectedItems()) {
			s.add(item.getLabel());
		}
		return s;
	}

	private void onbuttonSaveSNP() {

		genotype = (GenotypeFacade) AppContext.checkBean(genotype, "GenotypeFacade");

		String organism = AppContext.getDefaultOrganism();
		if (selectChromosome.getSelectedItem() == null)
			return;
		String selchr = selectChromosome.getSelectedItem().getLabel();

		boolean hasAllele = this.checkboxSNPAlelle.isChecked();
		boolean hasPvalue = this.checkboxSNPPValue.isChecked();

		if (selchr.equals("ANY")) {

			String lines[] = txtboxEditNewList.getValue().trim().split("\n");

			Map<String, Map> mapChr2Pos2Pvalue = new HashMap();
			Map<String, Map> mapChr2Pos2Allele = new HashMap();

			Map<String, Set> mapChr2Set = new TreeMap();
			for (int isnp = 0; isnp < lines.length; isnp++) {
				try {
					String chrposline = lines[isnp].trim();
					if (chrposline.isEmpty())
						continue;
					String chrpos[] = chrposline.split("\\s+");
					String chr = "";
					try {
						int intchr = Integer.valueOf(chrpos[0]);
						if (intchr > 9)
							chr = "chr" + intchr;
						else
							chr = "chr0" + intchr;
					} catch (Exception ex) {
						chr = chrpos[0].toLowerCase();
					}

					BigDecimal pos = null;
					try {
						pos = BigDecimal.valueOf(Long.valueOf(chrpos[1]));
					} catch (Exception ex) {
						AppContext.debug("Invalid position chrome position");
						continue;
					}

					Map<BigDecimal, String> mapPos2Allele = mapChr2Pos2Allele.get(chr);
					if (mapPos2Allele == null) {
						mapPos2Allele = new HashMap();
						mapChr2Pos2Allele.put(chr, mapPos2Allele);
					}
					Map<BigDecimal, Double> mapPos2Pvalue = mapChr2Pos2Pvalue.get(chr);
					if (mapPos2Pvalue == null) {
						mapPos2Pvalue = new HashMap();
						mapChr2Pos2Pvalue.put(chr, mapPos2Pvalue);
					}

					if (hasAllele) {
						mapPos2Allele.put(pos, chrpos[2]);
						if (hasPvalue) {
							try {
								mapPos2Pvalue.put(pos, Double.valueOf(chrpos[3]));
							} catch (Exception ex) {
								AppContext.debug("Invalid p-value " + chrpos[3]);
							}
						}
					} else if (hasPvalue) {
						try {
							mapPos2Pvalue.put(pos, Double.valueOf(chrpos[2]));
						} catch (Exception ex) {
							AppContext.debug("Invalid number " + chrpos[2]);
						}
					}

					Set setPos = mapChr2Set.get(chr);
					if (setPos == null) {
						setPos = new HashSet();
						mapChr2Set.put(chr, setPos);
					}
					setPos.add(pos);

				} catch (Exception ex) {
					AppContext.debug("onbuttonSaveSNP exception: ");
					ex.printStackTrace();
				}
			}

			Set<MultiReferencePosition> setSNPDBPos = new HashSet();
			// Set<MultiReferencePosition> setCoreSNPDBPos = new HashSet();

			Set setSNP = null;
			Set setChrSNP = new HashSet();
			Iterator<String> itChr = mapChr2Set.keySet().iterator();
			while (itChr.hasNext()) {
				String chr = itChr.next();
				setSNP = mapChr2Set.get(chr);

				if (hasAllele || hasPvalue) {
					if (this.checkboxVerifySNP.isChecked()) {
						Iterator<SnpsAllvarsPos> itSnpsDB = genotype.checkSNPInChromosome(chr, setSNP, getVariantSets())
								.iterator();
						while (itSnpsDB.hasNext()) {
							BigDecimal ipos = itSnpsDB.next().getPosition();
							setSNPDBPos.add(new MultiReferencePositionImplAllelePvalue(organism, chr, ipos,
									(String) mapChr2Pos2Allele.get(chr).get(ipos),
									(Double) mapChr2Pos2Pvalue.get(chr).get(ipos)));
						}

						//
						// if(this.checkbox3kSnps.isSelected()) {
						// Iterator<SnpsAllvarsPos> itSnpsDB = genotype.checkSNPInChromosome(chr,
						// setSNP, SnpsAllvarsPosDAO.TYPE_3KALLSNP ).iterator();
						// while(itSnpsDB.hasNext()) {
						// BigDecimal ipos= itSnpsDB.next().getPosition();
						// setSNPDBPos.add( new MultiReferencePositionImplAllelePvalue(organism, chr,
						// ipos, (String)mapChr2Pos2Allele.get(chr).get(ipos),
						// (Double)mapChr2Pos2Pvalue.get(chr).get(ipos)));
						// }
						//
						// /*
						// Iterator<SnpsAllvarsPos> itCoreSnpsDB = genotype.checkSNPInChromosome(chr,
						// setSNP, SnpsAllvarsPosDAO.TYPE_3KCORESNP ).iterator();
						// while(itCoreSnpsDB.hasNext()) {
						// BigDecimal ipos= itCoreSnpsDB.next().getPosition();
						// setCoreSNPDBPos.add( new MultiReferencePositionImplAllelePvalue(organism,
						// chr, ipos, mapPos2Allele.get(ipos), mapPos2Pvalue.get(ipos)));
						// }
						// */
						//
						// } else if(this.checkboxHDRASnps.isSelected()) {
						// Iterator<SnpsAllvarsPos> itSnpsDB = genotype.checkSNPInChromosome(chr,
						// setSNP, SnpsAllvarsPosDAO.TYPE_HDRASNP ).iterator();
						// while(itSnpsDB.hasNext()) {
						// BigDecimal ipos= itSnpsDB.next().getPosition();
						// setSNPDBPos.add( new MultiReferencePositionImplAllelePvalue(organism, chr,
						// ipos, (String)mapChr2Pos2Allele.get(chr).get(ipos),
						// (Double)mapChr2Pos2Pvalue.get(chr).get(ipos) ));
						// }
						// }

					}

					Iterator<BigDecimal> itPos = setSNP.iterator();
					while (itPos.hasNext()) {
						BigDecimal ipos = itPos.next();
						setChrSNP.add(new MultiReferencePositionImplAllelePvalue(organism, chr, ipos,
								(String) mapChr2Pos2Allele.get(chr).get(ipos),
								(Double) mapChr2Pos2Pvalue.get(chr).get(ipos)));
					}

				} else {
					// no p or allele value
					if (this.checkboxVerifySNP.isChecked()) {
						Iterator<SnpsAllvarsPos> itSnpsDB = genotype.checkSNPInChromosome(chr, setSNP, getVariantSets())
								.iterator();
						while (itSnpsDB.hasNext()) {
							setSNPDBPos
									.add(new MultiReferencePositionImpl(organism, chr, itSnpsDB.next().getPosition()));
						}

						//
						// if(this.checkbox3kSnps.isSelected()) {
						// Iterator<SnpsAllvarsPos> itSnpsDB = genotype.checkSNPInChromosome(chr,
						// setSNP, SnpsAllvarsPosDAO.TYPE_3KALLSNP ).iterator();
						// while(itSnpsDB.hasNext()) {
						// setSNPDBPos.add( new MultiReferencePositionImpl(organism, chr,
						// itSnpsDB.next().getPosition()));
						// }
						// /*
						// Iterator<SnpsAllvarsPos> itCoreSnpsDB = genotype.checkSNPInChromosome(chr,
						// setSNP, SnpsAllvarsPosDAO.TYPE_3KCORESNP ).iterator();
						// while(itCoreSnpsDB.hasNext()) {
						// setCoreSNPDBPos.add( new MultiReferencePositionImpl(organism, chr,
						// itCoreSnpsDB.next().getPosition()));
						// }
						// */
						// } else if(this.checkboxHDRASnps.isSelected()) {
						// Iterator<SnpsAllvarsPos> itSnpsDB = genotype.checkSNPInChromosome(chr,
						// setSNP, SnpsAllvarsPosDAO.TYPE_HDRASNP).iterator();
						// while(itSnpsDB.hasNext()) {
						// setSNPDBPos.add( new MultiReferencePositionImpl(organism, chr,
						// itSnpsDB.next().getPosition()));
						// }
						// }
					}
					Iterator<BigDecimal> itPos = setSNP.iterator();
					while (itPos.hasNext()) {
						setChrSNP.add(new MultiReferencePositionImpl(organism, chr, itPos.next()));
					}
				}

			}

			if (this.checkboxVerifySNP.isChecked())
				// onbuttonSaveSNPInChr(setChrSNP, setSNPDBPos, setCoreSNPDBPos, hasAllele,
				// hasPvalue);
				onbuttonSaveSNPInChr(setChrSNP, setSNPDBPos, null, hasAllele, hasPvalue);
			else
				onbuttonSaveSNPInChr(setChrSNP, null, null, hasAllele, hasPvalue);

		} else {
			Messagebox.show("Single chromosome not handled");

			// // chr specific
			// String lines[] = txtboxEditNewList.getValue().trim().split("\n");
			// Set setSNP = new HashSet();
			// for(int isnp=0; isnp<lines.length; isnp++) {
			// try {
			// String num = lines[isnp].trim();
			// if(num.isEmpty()) continue;
			// setSNP.add( BigDecimal.valueOf( Long.valueOf( num )));
			// } catch (Exception ex)
			// {
			// AppContext.debug("onbuttonSaveSNP exception: ");
			// ex.printStackTrace();
			// }
			// }
			//
			// Set<BigDecimal> setSNPDBPos = new HashSet();
			// String chr=selectChromosome.getSelectedItem().getLabel();
			// Iterator<SnpsAllvarsPos> itSnpsDB = genotype.checkSNPInChromosome(chr,
			// setSNP, SnpsAllvarsPosDAO.TYPE_3KALLSNP ).iterator();
			// while(itSnpsDB.hasNext()) {
			// setSNPDBPos.add( itSnpsDB.next().getPosition() );
			// }
			//
			// //AppContext.displayCollection("setSNPDBPos", setSNPDBPos);
			//
			// /*
			// Set<BigDecimal> setCoreSNPDBPos = new HashSet();
			// Iterator<SnpsAllvarsPos> itCoreSnpsDB = genotype.checkSNPInChromosome(chr,
			// setSNP, SnpsAllvarsPosDAO.TYPE_3KCORESNP ).iterator();
			// while(itCoreSnpsDB.hasNext()) {
			// setCoreSNPDBPos.add( itCoreSnpsDB.next().getPosition() );
			// }
			// */
			//
			// //AppContext.displayCollection("setCoreSNPDBPos", setCoreSNPDBPos);
			//
			//
			// //onbuttonSaveSNPInChr(setSNP , setSNPDBPos, setCoreSNPDBPos);
			// onbuttonSaveSNPInChr(setSNP , setSNPDBPos, null);
		}
	}

	private void onbuttonSaveSNPInChr(Set setSNP, Set setSNPDBPos, Set setCoreSNPDBPos) {
		onbuttonSaveSNPInChr(setSNP, setSNPDBPos, setCoreSNPDBPos, false, false);
	}

	private void onbuttonSaveSNPInChr(Set setSNP, Set setSNPDBPos, Set setCoreSNPDBPos, final boolean hasAllele,
			final boolean hasPvalue) {
		workspace = (WorkspaceFacade) AppContext.checkBean(workspace, "WorkspaceFacade");

		if (setCoreSNPDBPos == null && setSNPDBPos != null) {
			setCoreSNPDBPos = new HashSet(setSNPDBPos);
		}

		isMsgboxEventSuccess = false;
		isDoneModal = false;

		final String chr = selectChromosome.getSelectedItem().getLabel();
		final String newlistname = txtboxEditListname.getValue().replaceAll(":", "").trim();

		if (setSNPDBPos == null && setCoreSNPDBPos == null) {

			if (workspace.addSnpPositionList(chr, newlistname, setSNP, hasAllele, hasPvalue)) {

				AppContext.debug(newlistname + " added with " + setSNP.size() + " items");
				txtboxEditNewList.setValue("");
				txtboxEditListname.setValue("");
				listboxVarieties.setVisible(false);
				vboxEditNewList.setVisible(false);
				buttonCreate.setVisible(true);
				buttonDelete.setVisible(true);
				buttonSave.setVisible(false);
				buttonCancel.setVisible(false);

				// Messagebox.show("SNP List with " + setSNP.size() + " (unverified) positions
				// created with name" + newlistname ,"OPERATION SUCCESFUL",Messagebox.OK,
				// Messagebox.EXCLAMATION);
				Events.sendEvent("onClick", radioSNP, null);
				afterButtonSave(true);
			} else {
				Messagebox.show("Failed to add list", "OPERATION FAILED", Messagebox.OK, Messagebox.EXCLAMATION);
				afterButtonSave(false);
			}
			return;
		}

		// Set snps = ;

		// AppContext.displayCollection("setSNPs", setSNP.size());
		// AppContext.displayCollection("setSNPDBPos", setSNPDBPos);
		// pContext.displayCollection("setCoreSNPDBPos", setCoreSNPDBPos);

		// list in snp universe
		final Set setMatched = new TreeSet(setSNP);
		setMatched.retainAll(setSNPDBPos);

		// AppContext.displayCollection("setMatched", setMatched);

		if (setMatched.size() == 0) {
			Messagebox.show("No identified SNP positions", "WARNING", Messagebox.OK, Messagebox.EXCLAMATION);
			afterButtonSave(false);
			// return false;
		}

		// list not in snp universe
		Set setMinus = new TreeSet(setSNP);
		setMinus.removeAll(setSNPDBPos);

		// AppContext.displayCollection("setMinus", setMinus);

		// list in snp universe not in core
		Set setMatchedNotInCore = new TreeSet(setMatched);
		setMatchedNotInCore.removeAll(setCoreSNPDBPos);

		// AppContext.displayCollection("setMatchedNotInCore", setMatchedNotInCore);

		if (setMinus.size() > 0 || setMatchedNotInCore.size() > 0) {

			if (setMatched.size() > 0) {
				StringBuffer buff = new StringBuffer();
				if (setMinus.size() > 0) {
					buff.append("Not SNP positions: " + setMinus.size() + "\n");
					Iterator itVar = setMinus.iterator();
					while (itVar.hasNext()) {
						buff.append(itVar.next());
						buff.append("\n");
					}
				}

				buff.append("SNP positions in the list: " + setMatched.size() + "\n");

				if (setMatchedNotInCore.size() > 0) {
					buff.append("SNP positions not in Core set: " + setMatchedNotInCore.size() + "\n");
					Iterator itVar = setMatchedNotInCore.iterator();
					while (itVar.hasNext()) {
						buff.append(itVar.next());
						buff.append("\n");
					}
				}

				// buff.append("Do you want to proceed?");

				/*
				 * int intret = Messagebox.show(buff.toString(),"WARNING",Messagebox.YES |
				 * Messagebox.NO, Messagebox.QUESTION); System.out.print("intret=" + intret);
				 * if(intret==Messagebox.YES ) {} else return;
				 */

				//
				if (newlistname.isEmpty()) {
					Messagebox.show("Provide unique list name", "INVALID VALUE", Messagebox.OK, Messagebox.EXCLAMATION);
					afterButtonSave(false);
					// return false;
				}
				if (workspace.getSnpPositions(chr, newlistname) != null
						&& !workspace.getSnpPositions(chr, newlistname).isEmpty()) {
					Messagebox.show("Listname already exists", "INVALID VALUE", Messagebox.OK, Messagebox.EXCLAMATION);
					afterButtonSave(false);
					// return false;
				}

				if (this.checkboxAutoconfirm.isChecked() || setSNP.size() > 50) {
					AppContext.debug("Adding SNP list");
					if (workspace.addSnpPositionList(chr, newlistname, setMatched, hasAllele, hasPvalue)) {

						AppContext.debug(newlistname + " added with " + setMatched.size() + " items");

						txtboxEditNewList.setValue("");
						txtboxEditListname.setValue("");
						listboxVarieties.setVisible(false);
						vboxEditNewList.setVisible(false);
						buttonCreate.setVisible(true);
						buttonDelete.setVisible(true);
						buttonSave.setVisible(false);
						buttonCancel.setVisible(false);

						try {
							String tmpreportfile = AppContext.getTempDir() + "savesnplist-report-"
									+ AppContext.createTempFilename() + ".txt";
							String filetype = "text/plain";
							Filedownload.save(buff.toString(), filetype, tmpreportfile);
							// AppContext.debug("File download complete! Saved to: "+filename);
							org.zkoss.zk.ui.Session zksession = Sessions.getCurrent();
							AppContext.debug("snplist-report downlaod complete!" + tmpreportfile + " Downloaded to:"
									+ zksession.getRemoteHost() + "  " + zksession.getRemoteAddr());
						} catch (Exception ex) {
							ex.printStackTrace();
						}

						Messagebox.show(
								"SNP List with " + setMatched.size() + " positions created with name" + newlistname,
								"OPERATION SUCCESFUL", Messagebox.OK, Messagebox.EXCLAMATION);

						Events.sendEvent("onClick", radioSNP, null);
						afterButtonSave(true);

					} else {
						Messagebox.show("Failed to add list", "OPERATION FAILED", Messagebox.OK,
								Messagebox.EXCLAMATION);
						afterButtonSave(false);
					}

				} else {
					// Messagebox.show(buff.toString(),"WARNING",Messagebox.YES | Messagebox.NO,
					// Messagebox.QUESTION, 0,
					List listmsg = new ArrayList();
					String[] lines = buff.toString().split("\n");
					for (int iline = 0; iline < lines.length; iline++)
						listmsg.add(lines[iline]);
					try {
						ListboxMessageBox.show("Do you want to proceed?", "Create SNP List",
								Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, listmsg,
								new org.zkoss.zk.ui.event.EventListener() {
									@Override
									public void onEvent(Event e) throws Exception {
										
										if (e.getName().equals(Messagebox.ON_YES)) {

											AppContext.debug("Adding SNP list");

											if (workspace.addSnpPositionList(chr, newlistname, setMatched, hasAllele,
													hasPvalue)) {

												AppContext.debug(
														newlistname + " added with " + setMatched.size() + " items");

												txtboxEditNewList.setValue("");
												txtboxEditListname.setValue("");
												listboxVarieties.setVisible(false);
												vboxEditNewList.setVisible(false);
												buttonCreate.setVisible(true);
												buttonDelete.setVisible(true);
												buttonSave.setVisible(false);
												buttonCancel.setVisible(false);

												Events.sendEvent("onClick", radioSNP, null);
												afterButtonSave(true);

											} else {
												Messagebox.show("Failed to add list", "OPERATION FAILED", Messagebox.OK,
														Messagebox.EXCLAMATION);
												afterButtonSave(false);
											}

										} else {
											afterButtonSave(false);
										}
									}
								});
					} catch (Exception ex) {
						ex.printStackTrace();
					}

				}

			} else {
				Messagebox.show("No identified SNP positions", "WARNING", Messagebox.OK, Messagebox.EXCLAMATION);
				afterButtonSave(false);
				// return false;

			}

		} else {

			AppContext.debug("Adding SNP list");

			if (workspace.addSnpPositionList(chr, newlistname, setMatched, hasAllele, hasPvalue)) {

				AppContext.debug(newlistname + " added with " + setMatched.size() + " items");

				txtboxEditNewList.setValue("");
				txtboxEditListname.setValue("");
				listboxVarieties.setVisible(false);
				vboxEditNewList.setVisible(false);
				buttonCreate.setVisible(true);
				buttonDelete.setVisible(true);
				buttonSave.setVisible(false);
				buttonCancel.setVisible(false);

				Events.sendEvent("onClick", radioSNP, null);
				afterButtonSave(true);

			} else {
				Messagebox.show("Failed to add list", "OPERATION FAILED", Messagebox.OK, Messagebox.EXCLAMATION);
				afterButtonSave(false);
			}

		}
		// afterButtonSave(true);
		// return true;

	}

	private void addVarlist(Set setMatched) {
		addVarlist(setMatched, 0, null, null);
	}

	private void addVarlist(Set setMatched, int hasPhenotype, List phennames, Map<BigDecimal, Object[]> mapVarid2Phen) {
		if (setMatched.size() > 0) {

			AppContext.debug("Adding variety list");

			if (txtboxEditListname.getValue().trim().isEmpty()) {
				Messagebox.show("Provide unique list name", "INVALID VALUE", Messagebox.OK, Messagebox.EXCLAMATION);
				return;
			}
			if (workspace.getVarieties(txtboxEditListname.getValue().trim()) != null
					&& !workspace.getVarieties(txtboxEditListname.getValue().trim()).isEmpty()) {
				Messagebox.show("Listname already exists", "INVALID VALUE", Messagebox.OK, Messagebox.EXCLAMATION);
				return;
			}
			if (mapVarid2Phen != null && setMatched.size() != mapVarid2Phen.size()) {
				Messagebox.show(
						"Variety size not equal to phenotype size " + setMatched.size() + ", " + mapVarid2Phen.size(),
						"INVALID ENTRIES", Messagebox.OK, Messagebox.EXCLAMATION);
				return;
			}
			if (workspace.addVarietyList(txtboxEditListname.getValue().trim(), setMatched, getDataset(), hasPhenotype,
					phennames, mapVarid2Phen)) {

				AppContext.debug(txtboxEditListname.getValue().trim() + " added with " + setMatched.size() + " items");

				txtboxEditNewList.setValue("");
				txtboxEditListname.setValue("");
				listboxVarieties.setVisible(true);
				vboxEditNewList.setVisible(false);
				buttonCreate.setVisible(true);
				buttonDelete.setVisible(true);
				buttonSave.setVisible(false);
				buttonCancel.setVisible(false);

				Events.sendEvent("onClick", radioVariety, null);
				// onclickVariety();

			} else {
				Messagebox.show("Failed to add list", "OPERATION FAILED", Messagebox.OK, Messagebox.EXCLAMATION);
			}
		}
	}

	private void addLocuslist(Set setMatched) {
		if (setMatched.size() > 0) {

			AppContext.debug("Adding locus list");

			if (txtboxEditListname.getValue().trim().isEmpty()) {
				Messagebox.show("Provide unique list name", "INVALID VALUE", Messagebox.OK, Messagebox.EXCLAMATION);
				return;
			}
			if (workspace.getLoci(txtboxEditListname.getValue().trim()) != null
					&& !workspace.getLoci(txtboxEditListname.getValue().trim()).isEmpty()) {
				Messagebox.show("Listname already exists", "INVALID VALUE", Messagebox.OK, Messagebox.EXCLAMATION);
				return;
			}
			if (workspace.addLocusList(txtboxEditListname.getValue().trim(), setMatched)) {

				AppContext.debug(txtboxEditListname.getValue().trim() + " added with " + setMatched.size() + " items");

				txtboxEditNewList.setValue("");
				txtboxEditListname.setValue("");
				// listboxVarieties.setVisible(true);
				listboxLocus.setVisible(true);
				vboxEditNewList.setVisible(false);
				buttonCreate.setVisible(true);
				buttonDelete.setVisible(true);
				buttonSave.setVisible(false);
				buttonCancel.setVisible(false);

				Events.sendEvent("onClick", radioLocus, null);
				// onclickVariety();

			} else {
				Messagebox.show("Failed to add list", "OPERATION FAILED", Messagebox.OK, Messagebox.EXCLAMATION);
			}
		}
	}

	private class LocusComparator implements Comparator {

		@Override
		public int compare(Object o1, Object o2) {
			
			Locus l1 = (Locus) o1;
			Locus l2 = (Locus) o2;

			int ret = l1.getContig().compareTo(l2.getContig());
			if (ret == 0) {
				ret = l1.getFmin().compareTo(l2.getFmin());
				if (ret == 0) {
					ret = l1.getFmax().compareTo(l2.getFmax());
					if (ret == 0) {
						ret = l1.getUniquename().compareToIgnoreCase(l2.getUniquename());
					}
				}
			}

			return ret;
		}

	}

	private boolean onbuttonSaveLocus() {

		// Messagebox.show("Not yet implemented");

		isMsgboxEventSuccess = false;
		// genomics = (GenomicsFacade)AppContext.checkBean(genomics , "GenomicsFacade");
		genotype = (GenotypeFacade) AppContext.checkBean(genomics, "GenotypeFacade");
		workspace = (WorkspaceFacade) AppContext.checkBean(workspace, "WorkspaceFacade");

		Set setReadNames = new HashSet();

		List listNoMatch = new ArrayList();
		// final Set setMatched = new TreeSet();
		final Set setMatched = new TreeSet(new LocusComparator());
		String lines[] = txtboxEditNewList.getValue().trim().split("\n");
		for (int i = 0; i < lines.length; i++) {

			Locus locus = null;
			String locusstr = lines[i].trim().toUpperCase();

			AppContext.debugIterate("checking locus " + locusstr);

			if (locusstr.isEmpty())
				continue;
			if (setReadNames.contains(locusstr))
				continue;
			setReadNames.add(locusstr);

		}

		try {
			setMatched.addAll(genotype.getGeneFromNames(setReadNames, AppContext.getDefaultOrganism())); // genomics.getLocusByName(locusstr);
		} catch (Exception ex) {
			ex.printStackTrace();
			Messagebox.show(ex.getMessage(), "LOCUS QUERY EXCEPTION", Messagebox.OK, Messagebox.EXCLAMATION);
		}

		if (setMatched.size() == 0) {
			Messagebox.show("No identified loci", "WARNING", Messagebox.OK, Messagebox.EXCLAMATION);
			return false;
		}

		if (setMatched.size() < setReadNames.size())
			Messagebox.show("Only " + setMatched.size() + " of " + setReadNames.size() + " locus names are recognized",
					"WARNING", Messagebox.OK, Messagebox.EXCLAMATION);

		addLocuslist(setMatched);
		return true;

	}

	private boolean onbuttonSaveLocus2() {

		// Messagebox.show("Not yet implemented");

		isMsgboxEventSuccess = false;
		// genomics = (GenomicsFacade)AppContext.checkBean(genomics , "GenomicsFacade");
		genotype = (GenotypeFacade) AppContext.checkBean(genomics, "GenotypeFacade");
		workspace = (WorkspaceFacade) AppContext.checkBean(workspace, "WorkspaceFacade");

		Set setReadNames = new HashSet();

		List listNoMatch = new ArrayList();
		// final Set setMatched = new TreeSet();
		final Set setMatched = new TreeSet(new LocusComparator());
		String lines[] = txtboxEditNewList.getValue().trim().split("\n");
		for (int i = 0; i < lines.length; i++) {

			Locus locus = null;
			String locusstr = lines[i].trim().toUpperCase();

			AppContext.debugIterate("checking locus " + locusstr);

			if (locusstr.isEmpty())
				continue;
			if (setReadNames.contains(locusstr))
				continue;
			setReadNames.add(locusstr);

			try {
				locus = genotype.getGeneFromName(locusstr, AppContext.getDefaultOrganism()); // genomics.getLocusByName(locusstr);
				if (locus == null)
					listNoMatch.add(locusstr);
				else
					setMatched.add(locus);

				// } catch (InvocationTargetException e) {
				// Answer:
				// e.getCause().printStackTrace();
				// Messagebox.show(e.getCause().getMessage(), "LOCUS QUERY
				// InvocationTargetException", Messagebox.OK, Messagebox.EXCLAMATION);
			} catch (Exception ex) {
				ex.printStackTrace();
				Messagebox.show(ex.getMessage(), "LOCUS QUERY EXCEPTION", Messagebox.OK, Messagebox.EXCLAMATION);
			}
		}

		if (setMatched.size() == 0) {
			Messagebox.show("No identified loci", "WARNING", Messagebox.OK, Messagebox.EXCLAMATION);
			return false;
		}

		if (listNoMatch.size() > 0) {

			if (setMatched.size() > 0) {
				StringBuffer buff = new StringBuffer();
				buff.append("Recognized loci in the list: " + setMatched.size() + "\n");

				buff.append("Cannot identify these loci: " + listNoMatch.size() + "\n");
				Iterator itVar = listNoMatch.iterator();
				while (itVar.hasNext()) {
					buff.append(itVar.next());
					buff.append("\n");
				}
				// buff.append("Do you want to proceed?");

				// Messagebox.show(buff.toString(),"WARNING",Messagebox.YES | Messagebox.NO,
				// Messagebox.QUESTION, 0,
				try {
					List listmsg = new ArrayList();
					String[] msglines = buff.toString().split("\n");
					for (int iline = 0; iline < msglines.length; iline++)
						listmsg.add(msglines[iline]);
					ListboxMessageBox.show("Do you want to proceed?", "Create Locus List",
							Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, listmsg,
							new org.zkoss.zk.ui.event.EventListener() {
								@Override
								public void onEvent(Event e) throws Exception {
									
									AppContext.debug(e.getName() + " pressed");

									if (Messagebox.ON_YES.equals(e.getName())) {
										// OK is clicked
										addLocuslist(setMatched);
										isMsgboxEventSuccess = true;

									} else
										Messagebox.show("Failed to add list", "OPERATION FAILED", Messagebox.OK,
												Messagebox.EXCLAMATION);
								}
							});
				} catch (Exception ex) {
					ex.printStackTrace();
				}

				return isMsgboxEventSuccess;

			} else {
				Messagebox.show("No identified loci", "WARNING", Messagebox.OK, Messagebox.EXCLAMATION);
				return false;

			}

		} else {
			addLocuslist(setMatched);
			return true;
		}

	}

	private boolean onbuttonSaveVariety() {

		isMsgboxEventSuccess = false;
		variety = (VarietyFacade) AppContext.checkBean(variety, "VarietyFacade");
		workspace = (WorkspaceFacade) AppContext.checkBean(workspace, "WorkspaceFacade");

		List listNoMatch = new ArrayList();
		final Set setMatched = new TreeSet();
		String lines[] = txtboxEditNewList.getValue().trim().split("\n");
		Map<String, Object[]> mapVar2Phen = new HashMap();
		Set setNames = new HashSet();

		Set setPhennames = new LinkedHashSet();
		int intPhenotype = 0;

		if (!radioNoPhenotype.isSelected()) {
			if (this.radioQuantitative.isSelected())
				intPhenotype = 1;
			if (this.radioCategorical.isSelected())
				intPhenotype = 2;
			String phennames[] = textboxPhenotypename.getValue().trim().split(",");
			for (int ip = 0; ip < phennames.length; ip++) {
				setPhennames.add(phennames[ip].trim());
			}
			if (phennames.length != setPhennames.size()) {
				Messagebox.show("There are duplicates in phenotype names");
				return false;
			}
		}

		for (int i = 0; i < lines.length; i++) {
			Variety var = null;
			String varstr = null;
			Double phenvalue[] = null;
			if (lines[i].trim().isEmpty())
				continue;
			if (intPhenotype == 0)
				varstr = lines[i].trim().toUpperCase();
			else {
				String varstrs[] = lines[i].trim().split(",");
				varstr = varstrs[0].trim().toUpperCase();
				if (intPhenotype == 1) {
					Double dvalue[] = new Double[setPhennames.size()];
					// quant
					for (int ip = 0; ip < setPhennames.size(); ip++) {
						try {
							String strval = varstrs[ip + 1].trim();
							if (strval.isEmpty())
								continue;
							try {
								dvalue[ip] = Double.valueOf(strval);
							} catch (Exception ex) {
								ex.printStackTrace();
								Messagebox.show("Invalid quantitative value " + strval + " in variety " + varstr);
								return false;
							}
						} catch (Exception ex1) {
							ex1.printStackTrace();
							Messagebox.show("Number of columns did not match for variety " + varstr);
							return false;
						}
					}
					mapVar2Phen.put(varstr, dvalue);
				} else if (intPhenotype == 2) {
					// cat
					String svalue[] = new String[setPhennames.size()];
					for (int ip = 0; ip < setPhennames.size(); ip++) {
						try {
							String strval = varstrs[ip + 1].trim();
							if (strval.isEmpty())
								continue;
							svalue[ip] = strval;
						} catch (Exception ex1) {
							ex1.printStackTrace();
							Messagebox.show("Number of columns did not match for variety " + varstr);
							return false;
						}
					}
					mapVar2Phen.put(varstr, svalue);
				}

			}
			if (varstr.isEmpty()) {
				if (intPhenotype > 0) {
					Messagebox.show("Blank variety name encountered");
					return false;
				} else
					continue;
			}

			setNames.add(varstr);
		}

		Set<Variety> varset = new HashSet(variety.getGermplasmByNames(setNames, getDataset()));
		if (varset.size() < setNames.size())
			varset.addAll(variety.getGermplasmByIrisIds(setNames, getDataset()));
		// if(varset.size()<setNames.size())
		// varset.addAll( variety.getGermplasmsByAccessions(setNames, getDataset()));

		/*
		 * if(varset.size()<setNames.size()) varset.addAll(
		 * variety.getGermplasmByNamesLike( setNames, getDataset()));
		 */

		if (varset.size() == 0) {
			Messagebox.show("No identified varieties", "WARNING", Messagebox.OK, Messagebox.EXCLAMATION);
			return false;
		}

		if (varset.size() < setNames.size()) {

			Set<String> setTerms = new HashSet();
			for (Variety var : varset) {
				setTerms.add(var.getName());
				setTerms.add(var.getAccession());
				setTerms.add(var.getIrisId());
				setTerms.add(var.getIrisId().replace("IRIS_", "").replace("IRIS", "").trim());
			}
			Set<String> setNamesQuery = new HashSet(setNames);
			setNamesQuery.removeAll(setTerms);
			StringBuffer buffNot = new StringBuffer();
			buffNot.append("Check ");
			Iterator<String> itvar = setNamesQuery.iterator();
			while (itvar.hasNext()) {
				String varl = itvar.next();
				buffNot.append(varl);
				if (itvar.hasNext())
					buffNot.append(",");
			}

			/*
			 * List<Variety> listLike=variety.getGermplasmByNamesLike( setNames,
			 * getDataset()); listLike.removeAll(varset); StringBuffer buffNot=new
			 * StringBuffer(); buffNot.append("Check "); Iterator<Variety>
			 * itvar=listLike.iterator(); while(itvar.hasNext()) { Variety
			 * varl=itvar.next(); buffNot.append(varl.getVarietyId() + " " + varl.getName()
			 * ); if(itvar.hasNext()) buffNot.append(","); }
			 */
			Messagebox.show(
					"Only " + varset.size() + " of " + setNames.size() + " variety names are recognized:" + buffNot,
					"WARNING", Messagebox.OK, Messagebox.EXCLAMATION);
			return false;
		}

		Map<BigDecimal, Variety> mapVarid2var = new HashMap();
		Iterator<Variety> itvar = varset.iterator();
		while (itvar.hasNext()) {
			Variety var = itvar.next();
			mapVarid2var.put(var.getVarietyId(), var);
		}

		Set setRemovevar = new HashSet();
		Map<BigDecimal, Object[]> mapVarid2Phen = new HashMap();
		if (intPhenotype > 0) {
			Iterator<String> itvarstr = mapVar2Phen.keySet().iterator();
			while (itvarstr.hasNext()) {
				String varstr = itvarstr.next();
				// BigDecimal varid=AppContext.getVarunique2Id(varstr, true, true, true,
				// variety.getMapId2Variety(getDataset()));
				BigDecimal varid = AppContext.getVarunique2Id(varstr, true, true, true, mapVarid2var);

				Variety var = mapVarid2var.get(varid);
				if (var == null) {
					Collection colvar = variety.getGermplasmByName(varstr, getDataset());
					if (colvar == null || colvar.isEmpty()) {
						var = variety.getGermplasmByIrisId(varstr, getDataset());
						if (var == null)
							var = variety.getGermplasmByAccession(varstr, getDataset());
						if (var == null) {
							AppContext.debug(varstr + " not found");
							continue;
						}
						varid = var.getVarietyId();
						mapVarid2Phen.put(varid, mapVar2Phen.get(varstr));
					} else {
						Iterator itvar2 = colvar.iterator();
						while (itvar2.hasNext()) {
							Variety var2 = (Variety) itvar2.next();
							mapVarid2Phen.put(var2.getVarietyId(), mapVar2Phen.get(varstr));
						}
					}
					/*
					 * var=variety.getGermplasmByName( varstr, getDataset()); if(var==null) var=
					 * variety.getGermplasmByIrisId( varstr, getDataset()); //if(var==null) var=
					 * variety.getGermplasmByNameLike( varstr, getDataset()); if(var==null) {
					 * AppContext.debug(varstr +" not found"); continue; } varid=var.getVarietyId();
					 */

				} else {
					mapVarid2Phen.put(varid, mapVar2Phen.get(varstr));
				}

			}
			List lPhennames = new ArrayList();
			lPhennames.addAll(setPhennames);
			addVarlist(varset, intPhenotype, lPhennames, mapVarid2Phen);
			return true;
		} else {
			addVarlist(varset);
			return true;
		}

	}

	@Listen("onClick =#buttonDelete")
	public void onbuttonDelete() {
		/*
		 * workspace = (WorkspaceFacade)AppContext.checkBean(workspace ,
		 * "WorkspaceFacade");
		 * 
		 * if(this.radioVariety.isSelected()) {
		 * 
		 * workspace.deleteVarietyList( listboxListnames.getSelectedItem().getLabel() );
		 * List listVarlistNames=new ArrayList(); listVarlistNames.addAll(
		 * workspace.getVarietylistNames( )); listboxListnames.setModel( new
		 * SimpleListModel(listVarlistNames)); listboxListnames.setSelectedIndex(0);
		 * Events.sendEvent( "onClick", radioLocus, null); } else
		 * if(this.radioSNP.isSelected()) {
		 * 
		 * workspace.deleteVarietyList( listboxListnames.getSelectedItem().getLabel() );
		 * List listVarlistNames=new ArrayList(); listVarlistNames.addAll(
		 * workspace.getVarietylistNames( )); listboxListnames.setModel( new
		 * SimpleListModel(listVarlistNames)); listboxListnames.setSelectedIndex(0);
		 * Events.sendEvent( "onClick", radioLocus, null); }
		 */

	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		try {

			init();

			// if(textboxFrom.getValue().equals("snpallele"))
			// this.checkboxSNPAlelle.setChecked(true);

			listitemsdao = (ListItemsDAO) AppContext.checkBean(listitemsdao, "ListItems");
			List listVS = new ArrayList();
			listVS.addAll(listitemsdao.getSnpsets());

			variety = (VarietyFacade) AppContext.checkBean(variety, "VarietyFacade");
			List listDatasets = variety.getDatasets();

			workspace = (WorkspaceFacade) AppContext.checkBean(workspace, "WorkspaceFacade");
			List listVarlistNames = new ArrayList();
			// if(listVarlistNames.size()==0) listVarlistNames.add("");
			// listVarlistNames.addAll( workspace.getVarietylistNames(
			// (HttpSession)Sessions.getCurrent().getNativeSession() ));
			listVarlistNames.addAll(workspace.getVarietylistNames());
			AppContext.debug("listVarlistNames=" + listVarlistNames.size());

			List listContigs = new ArrayList();
			listContigs.add("");
			listContigs.add("chr01");
			listContigs.add("chr02");
			listContigs.add("chr03");
			listContigs.add("chr04");
			listContigs.add("chr05");
			listContigs.add("chr06");
			listContigs.add("chr07");
			listContigs.add("chr08");
			listContigs.add("chr09");
			listContigs.add("chr10");
			listContigs.add("chr11");
			listContigs.add("chr12");
			listContigs.add("ANY");

			// AppContext.debug("myworkspace.zul init done..")
			SimpleListModel listmodel = new SimpleListModel(listVarlistNames);
			listmodel.setMultiple(true);
			listboxListnames.setModel(listmodel);

			listboxPositions.setItemRenderer(new SNPChrPositionListitemRenderer());
			listboxVarieties.setItemRenderer(new VarietyListItemRenderer());
			listboxLocus.setItemRenderer(new LocusListItemRenderer());

			SimpleListModel m = new SimpleListModel(listDatasets);
			m.setMultiple(true);
			listboxDataset.setModel(m);

			selectChromosome.setModel(new SimpleListModel(listContigs));
			selectChromosome.setSelectedIndex(listContigs.size() - 1);

			if (listVS.size() < 4)
				listboxVariantset.setRows(listVS.size());
			SimpleListModel listmodelvs = new SimpleListModel(listVS);
			listmodelvs.setMultiple(true);
			listboxVariantset.setModel(listmodelvs);

			String from = Executions.getCurrent().getParameter("from");
			String src = Executions.getCurrent().getParameter("src");
			String phen = Executions.getCurrent().getParameter("phenotype");
			if (src != null)
				textboxFrom.setValue(src);

			if (from != null) {
				if (from.equals("variety")) {
					Events.postEvent("onClick", radioVariety, null);
					Events.postEvent("onClick", buttonCreate, null);
					if (phen != null && phen.equals("true")) {
						Events.postEvent("onClick", radioQuantitative, null);
					}
				} else if (from.equals("snp")) {
					Events.postEvent("onClick", radioSNP, null);
					Events.postEvent("onClick", buttonCreate, null);
					String hasallele = Executions.getCurrent().getParameter("hasallele");
					if (hasallele != null) {
						textboxFrom.setValue("snpallele");
						Events.postEvent("onCheck", checkboxSNPAlelle, null);

					}
				} else if (from.equals("locus")) {
					Events.postEvent("onClick", radioLocus, null);
					Events.postEvent("onClick", buttonCreate, null);
				}

				buttonDownload.setDisabled(true);
			}

			AppContext.debug("doAfterCompose ..done");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private void init() {

		if (isLoggedIn()) {
			// Log in the man
			// switchToLoggedInView();
		} else {
			// switchToLoggedOutView();
			// Try to fill the form with saved data from cookie
			autoFillMyList();
		}
	}

	private void autoFillMyList() {
		sessionController.setSessionObject("isLoggedIn", true);
		String storestr = cookieController.getCookie("storemylist");

		if (storestr != null)
			checkboxSavedata.setValue(Boolean.valueOf(storestr));

		String mylist = cookieController.getCookie("mylist");

		if (mylist != null) {
			workspace = (WorkspaceFacade) AppContext.checkBean(workspace, "WorkspaceFacade");
			workspace.setMyListsCookie(mylist);
		}
	}

	/**
	 * Checks if the user is already logged in
	 *
	 * @return Returns true if the user is logged in, false if not.
	 */
	private boolean isLoggedIn() {
		if (sessionController.sessionIsNew()) {
			// Return false if session is fresh
			return false;
		} else {
			// Returns the status that's set in the session object
			Object status = sessionController.getSessionObject("isLoggedIn");
			if (status == null) {
				return false;
			} else {
				return (Boolean) status;
			}
		}
	}

}
