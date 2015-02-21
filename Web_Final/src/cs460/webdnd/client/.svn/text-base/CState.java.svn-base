package cs460.webdnd.client;

import java.util.List;

import cs460.webdnd.client.elements.WebPage;
import cs460.webdnd.client.elements.workspace.CreatedTabs;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.FormPanel;
import com.smartgwt.client.types.Visibility;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Img;

import cs460.webdnd.client.mvc.model.IModel;
import cs460.webdnd.client.mvc.model.IState;

/**
 * This holds all information for Model. If anything needs to be kept track of,
 * it should be here.
 * 
 * @author Theodore Schnepper
 */
public class CState implements IState {
	/* Login Service */
	protected final LoginServiceAsync loginService = GWT.create(LoginService.class);
	/* Data Service */
	protected final DataServiceAsync data_service = GWT.create(DataService.class);
	/* Data Service Impl */
	protected final ProjectServiceAsync project_service = GWT.create(ProjectService.class);
	/* Shared Service Impl */
	protected final SharedServiceAsync shared_service = GWT.create(SharedService.class);
	/* Upload Action URL */
	protected final static String UPLOAD_ACTION_URL = GWT.getModuleBaseURL() + "FileServlet";

	public LoginServiceAsync getLoginService() { return loginService; }
	public DataServiceAsync getDataService() { return data_service; }
	public ProjectServiceAsync getProjectService() { return project_service; }
	public SharedServiceAsync getSharedService() { return shared_service; }
	public String getUploadActionURL() { return UPLOAD_ACTION_URL; }

	private CreatedTabs tabEditor;
	private Canvas mainselection;
	private Canvas colorSelection;
	private Canvas createdForm;
	private Canvas currentPane;
	private Canvas mainCanvas;
	private List<WebPage> pages;
	private WebPage template;
	private FormPanel uploadForm;
	private IModel model;
	private Canvas formCanvas;
	private List<Img> images;

	public CState(IModel model) {
		this.model = model;
	}

	public Canvas getSelection() {
		return mainselection;
	}

	public void setSelection(Canvas newSelection) {
		this.mainselection = newSelection;
		// this.update();
	}

	public void deleteSelection() {
		if (mainselection != null)
			mainselection.setVisibility(Visibility.HIDDEN);
	}

	public Canvas getColorToolSelection() {
		return colorSelection;
	}

	public void setColorToolSelection(Canvas newSelection) {
		this.colorSelection = newSelection;
		this.update();
	}

	public CreatedTabs getMenuEditor() {
		return tabEditor;
	}

	public Canvas getCurrentPane() {
		return currentPane;
	}

	public void setCurrentPane(Canvas canvas) {
		this.currentPane = canvas;
	}

	public void setMenuEditor(CreatedTabs t) {
		this.tabEditor = t;
	}

	public Canvas getForm() {
		return createdForm;
	}

	public void setForm(Canvas createdForm) {
		this.createdForm = createdForm;
	}

	@Override
	public void update() {
		this.model.updateViews();
	}

	public void setUploadPanel(FormPanel upload) {
		this.uploadForm = upload;
	}

	public FormPanel getUploadPanel() {
		return uploadForm;
	}

	public List<WebPage> getPages() {
		return pages;
	}

	public void setPages(List<WebPage> pages) {
		this.pages = pages;
	}

	public void setTemplate(WebPage template) {
		this.template = template;
	}

	public WebPage getTemplate() {
		return template;
	}

	public void setFormCanvas(Canvas formCanvas) {
		this.formCanvas = formCanvas;
	}

	public Canvas getFormCanvas() {
		return formCanvas;
	}

	public void setMainCanvas(Canvas mainCanvas) {
		this.mainCanvas = mainCanvas;
	}

	public Canvas getMainCanvas() {
		return mainCanvas;
	}
	
	public void setImageList(List<Img> images){
		this.images = images;
	}
	
	public List<Img> getImageList(){
		return images;
	}
}