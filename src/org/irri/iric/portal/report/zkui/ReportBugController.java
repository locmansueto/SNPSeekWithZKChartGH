package org.irri.iric.portal.report.zkui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.ReaderInputStream;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.admin.WorkspaceFacade;
import org.irri.iric.portal.report.BugReport;
import org.irri.iric.portal.seed.service.SeedOrderService;
import org.irri.iric.portal.variety.VarietyFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.image.Image;
import org.zkoss.json.JSONObject;
import org.zkoss.support.smalltalk.recaptcha.RecaptchaVerifier;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vlayout;
import org.zkoss.zul.Window;

@Controller
@Scope(value = "session")

public class ReportBugController extends SelectorComposer<Window> {

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
	private Textbox textboxName;
	@Wire
	private Textbox textboxEmail;
	@Wire
	private Textbox textboxTitle;
	@Wire
	private Textbox textboxDesc;
	@Wire
	private Textbox textboxAttachment;
	@Wire
	private Vlayout flist;

	private Boolean userClick;
	

	public ReportBugController() {
		super();

		AppContext.debug("SeedOrderController() created");
	}

	@Override
	public void doAfterCompose(Window comp) throws Exception {

		super.doAfterCompose(comp);
		try {

			AppContext.debug("doAfterCompose() started");

			userClick = false;

			orderservice = (SeedOrderService) AppContext.checkBean(orderservice, "IRGCSeedOrderService");

			AppContext.debug("doAfterCompose() done");

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// getMyChildren(gridOrders,0);
		// gridOrders.getChildren()
	}

	private boolean validateFields() {
		if (textboxName.getValue().trim().isEmpty() || textboxEmail.getValue().trim().isEmpty()
				|| textboxTitle.getValue().trim().isEmpty()) {
			Messagebox.show("Do not leave required fields blank");
			return false;
		}
		if (!textboxEmail.getValue().trim().matches(".+@.+\\.[a-z]+")) {
			Messagebox.show("Please enter a valid email for authorized official.");
			return false;
		}

		if (!userClick) {
			Messagebox.show("Please click recaptcha.");
			return false;
		}

		return true;
	}

	@Command("onFileUpload")
	public void onFileUpload(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) {
		UploadEvent upEvent = null;
		Object objUploadEvent = ctx.getTriggerEvent();
		upEvent = (UploadEvent) objUploadEvent;
		if (upEvent != null) {
			attachedFile(upEvent.getMedias());

		}
	}

	private void attachedFile(Media[] mediaArray) {

	}

	/**
	 * Example to get varieties in selected subpopulation on button click
	 */
	@Listen("onClick =#errorSubmitBtn")
	public void onclickOrder() {

		try {
			if (!validateFields())
				return;

			BugReport bgRep = new BugReport();
			bgRep.setName(textboxName.getValue());
			bgRep.setEmail(textboxEmail.getValue());
			bgRep.setTitle(textboxTitle.getValue());
			bgRep.setMsg(textboxDesc.getValue());

			List<Hlayout> attachments = flist.getChildren();

			List<Label> images = new ArrayList<Label>();
			for (Hlayout layout : attachments) {
				List<Component> str = layout.getChildren();
				images.add((Label) str.get(0));
			}

			orderservice = (SeedOrderService) AppContext.checkBean(orderservice, "SeedOrderService");

			String pdf = orderservice.report(bgRep, images);

			AppContext.debug("smtafile at " + pdf);
			if (pdf != null) {
				if (pdf.equals(AppContext.SEED_NO_ORDER))
					Messagebox.show(AppContext.SEED_NO_ORDER);
				else {
					Messagebox.show("Your bug report has been sent.");
					String pdf2 = orderservice.sendAcknowledgement(bgRep);
				}

			} else {
				Messagebox.show("Bug report failed to send. Please check all details.");
			}
			boolean success = false;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Listen("onUserRespond = #recaptcha")
	public void verify(Event event) {
		JSONObject result;
		try {
			result = RecaptchaVerifier.verifyResponse(SECRET,
					((JSONObject) event.getData()).get("response").toString());

			userClick = Boolean.parseBoolean(result.get("success").toString());

		} catch (Exception e) {
			Messagebox.show("Click recaptcha..");
		}

	}

}
