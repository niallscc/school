package cs460.webdnd.client.elements;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;

import com.smartgwt.client.types.Visibility;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.SubmitItem;
import com.smartgwt.client.widgets.form.fields.TextItem;


import cs460.webdnd.client.CState;
import cs460.webdnd.client.ProjectServiceAsync;
import cs460.webdnd.client.utilities.Misc;

import cs460.webdnd.client.commands.GetThemesCommand;
import cs460.webdnd.client.commands.TemplateChooserCommand;
import cs460.webdnd.client.commands.editor.RenderCommand;
import cs460.webdnd.client.commands.servercommunication.DeleteProjectCommand;
import cs460.webdnd.client.commands.servercommunication.LoadProjectCommand;
import cs460.webdnd.client.commands.servercommunication.LoadThemeCommand;
import cs460.webdnd.client.commands.servercommunication.NameProjectCommand;
import cs460.webdnd.client.commands.servercommunication.NewProjectCommand;

import cs460.webdnd.client.commands.servercommunication.SaveProjectCommand;
import cs460.webdnd.client.elements.userAccount.ModifyAccount;
import cs460.webdnd.client.elements.workspace.CreatedTabs;
import cs460.webdnd.client.mvc.controller.IController;
import cs460.webdnd.shared.Response;

/**
 * @author niallschavez
 */
public class PrimaryMenuBar {
	// protected final loginServiceAsync loginService = GWT
	// .create(loginService.class);
	protected boolean drawn=false;
	protected MenuBar primaryMenuBar;
	protected MenuBar accountBar;
	protected Canvas fileCanvas, accountCanvas, viewCanvas;
	private Canvas menuBarCanvas;
	protected Canvas mainCanvas;
	protected IController cont;
	protected String temp_canvas_serialized;

	public PrimaryMenuBar(IController cont, final Canvas mainCanvas) {
		accountCanvas= new Canvas();
		fileCanvas = new Canvas();
		viewCanvas = new Canvas();
		this.cont = cont;
		accountBar= new MenuBar(true);
		primaryMenuBar = new MenuBar();
		menuBarCanvas = new Canvas();
		this.mainCanvas = mainCanvas;
		create();
	}

	private void create() {
		MenuItem save = new MenuItem("Save", new Command() {
			@Override public void execute() {
				cont.getModel().run(new SaveProjectCommand(cont.getModel()));
				fileCanvas.setVisibility(Visibility.HIDDEN);
				viewCanvas.setVisibility(Visibility.HIDDEN);
				accountCanvas.setVisibility(Visibility.HIDDEN);
			}

		});
		MenuItem load = new MenuItem("Load", new Command() {
			@Override public void execute() {
				fileCanvas.setVisibility(Visibility.HIDDEN);
				viewCanvas.setVisibility(Visibility.HIDDEN);
				accountCanvas.setVisibility(Visibility.HIDDEN);
				final CState state = (CState) cont.getModel().getState();
				final ProjectServiceAsync service = state.getProjectService();

				final Canvas load = new Canvas();
				load.setBackgroundColor("white");
				load.setTop(40);
				load.setLeft(40);

				final DynamicForm form = new DynamicForm();
				form.setGroupTitle("Load a Project");
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
				
				//projects.setCanEdit(false);
				service.getProjects(new GetNamesCallBack(projects));
				
				
				ButtonItem submit = new ButtonItem();
				submit.setTitle("Submit");
				
				submit.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
					@Override
					public void onClick(
							com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {

						cont.getModel().run(new LoadProjectCommand(projects.getItemText(projects.getSelectedIndex()), cont.getModel()));
						load.setVisibility(Visibility.HIDDEN);
						
					}
				});
				
				

				ButtonItem cancel = new ButtonItem();
				cancel.setTitle("Cancel");
				cancel.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
					@Override
					public void onClick(
							com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
						load.setVisibility(Visibility.HIDDEN);
						
					}
				});
				Canvas projCanvas= new Canvas();
				projCanvas.setLeft("90px");
				projCanvas.setTop("20px");
				projCanvas.addChild(projects);
				load.addChild(projCanvas);
				form.setFields( submit, cancel);

				load.addChild(form);

				mainCanvas.addChild(load);
			}

		});
		MenuItem templateChooser= new MenuItem("Prebuilt Template", new Command(){
			@Override
			public void execute() {
				fileCanvas.setVisibility(Visibility.HIDDEN);
				viewCanvas.setVisibility(Visibility.HIDDEN);
				accountCanvas.setVisibility(Visibility.HIDDEN);
				cont.getModel().run(new TemplateChooserCommand(cont.getModel()));
			}
		});
		
		MenuItem projectChooser= new MenuItem("Prebuilt Project", new Command(){
			@Override
			public void execute() {
				fileCanvas.setVisibility(Visibility.HIDDEN);
				viewCanvas.setVisibility(Visibility.HIDDEN);
				accountCanvas.setVisibility(Visibility.HIDDEN);
				
			
				//final CState state = (CState) cont.getModel().getState();
				//final ProjectServiceAsync service = state.getProjectService();

				final Canvas load = new Canvas();
				load.setBackgroundColor("white");
				load.setTop(40);
				load.setLeft(40);

				final DynamicForm form = new DynamicForm();
				form.setGroupTitle("Load a Project");
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
				//projects.setCanEdit(false);
				cont.getModel().run(new GetThemesCommand(cont.getModel(), projects));

				ButtonItem submit = new ButtonItem();
				submit.setTitle("Submit");
				submit.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
					@Override
					public void onClick(
							com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {

						cont.getModel().run(
								new LoadThemeCommand(projects.getItemText(projects.getSelectedIndex()), cont
										.getModel()));
						load.setVisibility(Visibility.HIDDEN);
					}
				});

				ButtonItem cancel = new ButtonItem();
				cancel.setTitle("Cancel");
				cancel.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
					@Override
					public void onClick(
							com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
						load.setVisibility(Visibility.HIDDEN);
					}
				});
				Canvas projCanvas= new Canvas();
				projCanvas.setLeft("90px");
				projCanvas.setTop("20px");
				projCanvas.addChild(projects);
				load.addChild(projCanvas);
				form.setFields( submit, cancel);

				load.addChild(form);

				mainCanvas.addChild(load);
				
			}
		});
		
		
		MenuItem newProj = new MenuItem("New", new Command() {

			@Override
			public void execute() {
				fileCanvas.setVisibility(Visibility.HIDDEN);
				viewCanvas.setVisibility(Visibility.HIDDEN);
				accountCanvas.setVisibility(Visibility.HIDDEN);
				fileCanvas.setVisibility(Visibility.HIDDEN);

				final Canvas save = new Canvas();

				save.setBackgroundColor("white");
				save.setTop(40);
				save.setLeft(40);
				final DynamicForm form = new DynamicForm();
				form.setGroupTitle("New project name");
				form.setIsGroup(true);
				form.setWidth(300);
				form.setHeight(120);
				form.setNumCols(2);
				form.setColWidths(60, "*");

				form.setPadding(5);
				form.setCanDragResize(true);
				form.setResizeFrom("R");

				final TextItem messageItem = new TextItem();

				messageItem.setColSpan(2);
				messageItem.setWidth("*");
				messageItem.setTitle("");

				final ButtonItem submit = new ButtonItem();
				submit.setTitle("Submit");
				submit.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
					@Override
					public void onClick(
							com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {

						save.setVisibility(Visibility.HIDDEN);

						if (!(cont.getModel().getState() instanceof CState))
							return;
						CState state = (CState) cont.getModel().getState();
						if (state.getMenuEditor() == null)
							state.setMenuEditor(new CreatedTabs(mainCanvas,
									cont.getModel()));
						cont.getModel().run(
								new NewProjectCommand(messageItem
										.getValueAsString(), cont.getModel()));
						
					}
					
				});
				SubmitItem cancel = new SubmitItem();
				cancel.setTitle("Cancel");
				cancel.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
					@Override
					public void onClick(
							com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
						save.setVisibility(Visibility.HIDDEN);
						
					}
				});
				form.setFields(messageItem, submit, cancel);

				save.addChild(form);

				mainCanvas.addChild(save);
			}

		});

		MenuItem renameProj = new MenuItem("Rename", new Command() {

			@Override
			public void execute() {
				fileCanvas.setVisibility(Visibility.HIDDEN);
				viewCanvas.setVisibility(Visibility.HIDDEN);
				accountCanvas.setVisibility(Visibility.HIDDEN);
				final Canvas renameCanvas = new Canvas();

				renameCanvas.setBackgroundColor("white");
				renameCanvas.setTop(40);
				renameCanvas.setLeft(40);

				final DynamicForm form = new DynamicForm();
				form.setGroupTitle("Rename This Project");
				form.setIsGroup(true);
				form.setWidth(300);
				form.setHeight(120);
				form.setNumCols(2);
				form.setColWidths(60, "*");

				form.setPadding(5);
				form.setCanDragResize(true);
				form.setResizeFrom("R");

				final TextItem messageItem = new TextItem();
				messageItem.setTitle(null);
				messageItem.setColSpan(2);
				messageItem.setWidth("*");

				ButtonItem submit = new ButtonItem();
				submit.setTitle("Submit");
				submit.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {

					@Override
					public void onClick(
							com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
						cont.getModel().run(
								new NameProjectCommand(messageItem
										.getValueAsString(), cont.getModel()));
						renameCanvas.setVisibility(Visibility.HIDDEN);
						
					}

				});

				ButtonItem cancel = new ButtonItem();
				cancel.setTitle("Cancel");
				cancel.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
					@Override
					public void onClick(
							com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
						renameCanvas.setVisibility(Visibility.HIDDEN);
						
					}
				});

				form.setFields(messageItem, submit, cancel);

				renameCanvas.addChild(form);

				mainCanvas.addChild(renameCanvas);
			}
		});

		MenuItem deleteProj = new MenuItem("Delete", new Command() {

			@Override
			public void execute() {
				fileCanvas.setVisibility(Visibility.HIDDEN);
				final CState state = (CState) cont.getModel().getState();
				final ProjectServiceAsync service = state.getProjectService();
				
				final Canvas delete = new Canvas();
				delete.setBackgroundColor("white");
				delete.setTop(40);
				delete.setLeft(40);

				final DynamicForm form = new DynamicForm();
				form.setGroupTitle("Delete a Project");
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
				//projects.setCanEdit(false);
				service.getProjects(new GetNamesCallBack(projects));

				ButtonItem submit = new ButtonItem();
				submit.setTitle("Submit");
				submit.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
					@Override
					public void onClick(
							com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {

						cont.getModel().run(
								new DeleteProjectCommand(projects.getItemText(projects.getSelectedIndex()), cont.getModel()));
						delete.setVisibility(Visibility.HIDDEN);
						
					}
				});

				ButtonItem cancel = new ButtonItem();
				cancel.setTitle("Cancel");
				cancel.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
					@Override
					public void onClick(
							com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
						delete.setVisibility(Visibility.HIDDEN);

					}
				});
				Canvas projCanvas= new Canvas();
				projCanvas.setLeft("90px");
				projCanvas.setTop("20px");
				projCanvas.addChild(projects);
				delete.addChild(projCanvas);
				form.setFields( submit, cancel);

				delete.addChild(form);

				mainCanvas.addChild(delete);
			}
		});
		
		MenuItem logout = new MenuItem("Logout", new Command() {
		
			@Override
			public void execute() {
				fileCanvas.setVisibility(Visibility.HIDDEN);
				viewCanvas.setVisibility(Visibility.HIDDEN);
				accountCanvas.setVisibility(Visibility.HIDDEN);
				Misc.getCState(cont).getLoginService().logout(logoutAsync);
				Window.Location.reload();
			}

			AsyncCallback<Response<Boolean>> logoutAsync = new AsyncCallback<Response<Boolean>>() {
				@Override
				public void onFailure(Throwable caught) {
				}

				@Override
				public void onSuccess(Response<Boolean> result) {
				}
			};
		});
		
		MenuItem profile = new MenuItem("Preferences", new Command() {

			@Override
			public void execute() {
				fileCanvas.setVisibility(Visibility.HIDDEN);
				viewCanvas.setVisibility(Visibility.HIDDEN);
				accountCanvas.setVisibility(Visibility.HIDDEN);
				new ModifyAccount(cont.getModel());
			}
		});

		MenuItem zipper = new MenuItem("Publish", new Command() {
			@Override
			public void execute() {
				fileCanvas.setVisibility(Visibility.HIDDEN);
				viewCanvas.setVisibility(Visibility.HIDDEN);
				accountCanvas.setVisibility(Visibility.HIDDEN);
				CState state = Misc.getCState(cont);
				/**
				 * This ensures that when we go to render something that it
				 * changes the selection to index instead of whatever else we
				 * were on.
				 */
				state.getMenuEditor().getTabSet().setSelectedTab(1);
				cont.getModel().run(new RenderCommand("", cont));
				Window.open(
						"/web_final/FileServlet?folder.zip",
						"_blank", "its a zip folder");
			}
		});
		MenuItem render = new MenuItem("Live Preview", new Command() {

			@Override
			public void execute() {
				viewCanvas.setVisibility(Visibility.HIDDEN);
				CState state = Misc.getCState(cont);

				if (state == null)
					return;
				/**
				 * This ensures that when we go to render something that it
				 * changes the selection to index instead of whatever else we
				 * were on.
				 */
				state.getMenuEditor().getTabSet().setSelectedTab(1);
				cont.getModel().run(new RenderCommand("render", cont));
			}
		});
		
		Misc.getCState(cont).getLoginService().getFirstName(new GetFirstNameCallBack());
		
		final MenuBar fileSubMenuBar = new MenuBar(true);
			
			fileSubMenuBar.addItem(newProj);
			fileSubMenuBar.addItem(load);
			fileSubMenuBar.addItem(save);
			fileSubMenuBar.addItem(renameProj);
			fileSubMenuBar.addItem(deleteProj);
			fileSubMenuBar.addSeparator();
			fileSubMenuBar.addItem(projectChooser);
			fileSubMenuBar.addItem(templateChooser);
			
		final MenuBar viewMenuBar= new MenuBar(true);
		
			viewMenuBar.addItem(render);
			viewMenuBar.addItem(zipper);
			
		
			
		MenuItem file= new MenuItem("File ", new Command(){

			@Override
			public void execute() {
				
				viewCanvas.setVisibility(Visibility.HIDDEN);	
				accountCanvas.setVisibility(Visibility.HIDDEN);	
				fileCanvas.setBackgroundColor("white");
				fileCanvas.setTop(32);
				fileCanvas.setWidth(50);
			
				fileCanvas.setBorder("1px solid");
				
				
				if (!drawn) {
					fileCanvas.addChild(fileSubMenuBar);
					fileCanvas.draw();
					drawn = true;
				}
				else {
					fileCanvas.redraw();
				}
				fileCanvas.setVisibility(Visibility.VISIBLE);
			}
			
		});
		
		MenuItem view = new MenuItem ("View", new Command(){

			@Override
			public void execute() {
				fileCanvas.setVisibility(Visibility.HIDDEN);
				accountCanvas.setVisibility(Visibility.HIDDEN);	
				viewCanvas.setBackgroundColor("white");
				viewCanvas.setTop(32);
				viewCanvas.setLeft(50);
				viewCanvas.setWidth(50);
				viewCanvas.setBorder("1px solid");
				viewCanvas.addChild(viewMenuBar);
				viewCanvas.draw();
				viewCanvas.setVisibility(Visibility.VISIBLE);
				
			}
			
		});
		
		accountCanvas.setBorder("1px solid");
		accountBar.addItem(profile);
		accountBar.addItem(logout);
		
		primaryMenuBar.addItem(file);
		primaryMenuBar.addSeparator();
		primaryMenuBar.addItem(view);
		primaryMenuBar.addSeparator();
		MenuItem i = new MenuItem("", primaryMenuBar);
		i.setWidth("81%");
		i.setEnabled(false);
		primaryMenuBar.addItem(i);
		
		primaryMenuBar.setWidth("100%");
		primaryMenuBar.setHeight("30px");

		fileSubMenuBar.addHandler( new myMouseOutHandler(), MouseOutEvent.getType());
		viewMenuBar.addHandler( new myMouseOutHandler(), MouseOutEvent.getType());
		accountBar.addHandler( new myMouseOutHandler(), MouseOutEvent.getType());
		menuBarCanvas.setWidth100();
		menuBarCanvas.setHeight("30px");
		menuBarCanvas.setLeft(0);
		menuBarCanvas.setTop(0);
		
		primaryMenuBar.addStyleName("myMenuBar");
		menuBarCanvas.addChild(primaryMenuBar);	
	}

	public Canvas getMenuBar(){
		return menuBarCanvas;
	}
	
	protected class myMouseOutHandler implements MouseOutHandler{

		@Override
		public void onMouseOut(MouseOutEvent event) {
		
			accountCanvas.setVisibility(Visibility.HIDDEN);
			fileCanvas.setVisibility(Visibility.HIDDEN);
			viewCanvas.setVisibility(Visibility.HIDDEN);
			
		}
	}
	
	private class GetFirstNameCallBack implements AsyncCallback<String>{
		MenuItem name;
		public GetFirstNameCallBack(){
			
		}
		@Override
		public void onFailure(Throwable caught) {}

		@Override
		public void onSuccess(String result) {
			name= new MenuItem("Welcome, "+result, new Command(){

				@Override
				public void execute() {
					fileCanvas.setVisibility(Visibility.HIDDEN);
					viewCanvas.setVisibility(Visibility.HIDDEN);	
					accountCanvas.setBackgroundColor("white");
					accountCanvas.setTop(32);
					accountCanvas.setLeft("85%");
					accountCanvas.setWidth(50);
					accountCanvas.addChild(accountBar);
					accountCanvas.draw();
					accountCanvas.setVisibility(Visibility.VISIBLE);
				}
			});
			
			primaryMenuBar.addItem(name);
		}
		
	}
	
	private class GetNamesCallBack implements AsyncCallback<Response<LinkedList<String>>> {
		ListBox projects;

		public GetNamesCallBack(ListBox projects) {
			this.projects = projects;
		}

		@Override
		public void onFailure(Throwable caught) { 
		}

		@Override
		public void onSuccess(Response<LinkedList<String>> result) {
			List<String> res = result.getFile();
			
			for (String name : res) {
				projects.addItem(name);
			}
		}
	}
}