package cs460.webdnd.client.commands;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;

import cs460.webdnd.client.CState;
import cs460.webdnd.client.SharedServiceAsync;

import cs460.webdnd.client.commands.servercommunication.LoadTemplateCommand;
import cs460.webdnd.client.mvc.controller.ICommand;
import cs460.webdnd.client.mvc.model.IModel;
import cs460.webdnd.client.utilities.Misc;
import cs460.webdnd.shared.FileInformation;
import cs460.webdnd.shared.Response;

/**
 * This class returns to Load Template Command the populated with the names of available templates to load.
 * 
 * @author niallschavez
 */
public class TemplateChooserCommand  implements ICommand{
	protected IModel model;
	protected Canvas templateChooser;
	
	public TemplateChooserCommand(IModel model){
		templateChooser= new Canvas();
		this.model=model;
	}
	@Override
	public void run() {
		final CState state = Misc.getCState(model);
		if(state == null)
			return;
		final SharedServiceAsync service = state.getSharedService();
		
		
		templateChooser.setBackgroundColor("white");
		templateChooser.setTop(40);
		templateChooser.setLeft(40);

		final DynamicForm form = new DynamicForm();
		form.setGroupTitle("Load a template");
		form.setIsGroup(true);
		form.setWidth(300);
		form.setHeight(120);
		form.setNumCols(2);
		form.setColWidths(60, "*");

		form.setPadding(5);
		form.setCanDragResize(true);
		form.setResizeFrom("R");

		final ListBox projects = new ListBox();
		projects.setTitle("");
		
		service.getFileInfo("template", new GetTemplatesCallback(projects));
		

		ButtonItem submit = new ButtonItem();
		submit.setTitle("Submit");
		submit.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
			@Override
			public void onClick(
					com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
				model.run(
						new LoadTemplateCommand(projects.getItemText(projects.getSelectedIndex())+".bin", model));
				templateChooser.destroy();
			}
		});

		ButtonItem cancel = new ButtonItem();
		cancel.setTitle("Cancel");
		cancel.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
			@Override
			public void onClick(
					com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
				templateChooser.destroy();

			}
		});
		Canvas projCanvas= new Canvas();
		projCanvas.setLeft("90px");
		projCanvas.setTop("20px");
		projCanvas.addChild(projects);
		templateChooser.addChild(projCanvas);
		form.setFields(submit, cancel);

		templateChooser.addChild(form);

		state.getMainCanvas().addChild(templateChooser);
	}	
	
	private class GetTemplatesCallback implements AsyncCallback<Response<LinkedList<FileInformation>>> {
		private ListBox projects;

		public GetTemplatesCallback(ListBox projects) {
			this.projects = projects;
		}

		@Override
		public void onFailure(Throwable caught) {
			SC.say("Error caught" + caught);
			System.out.println("err: "+ caught);
		}

		@Override
		public void onSuccess(Response<LinkedList<FileInformation>> result) {

				List<FileInformation> res = result.getFile();
				
				for (FileInformation name : res) {
					String filename = name.getFileName().substring(0,name.getFileName().indexOf('.'));
					if(filename.equalsIgnoreCase("index") || filename.equalsIgnoreCase("template"))
						continue;
					projects.addItem(filename);
				}	
				//templateChooser.redraw();

		}
	}
}