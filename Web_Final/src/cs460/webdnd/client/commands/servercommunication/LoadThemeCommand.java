/**
 * 
 */
package cs460.webdnd.client.commands.servercommunication;

import java.util.LinkedList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.SC;

import cs460.webdnd.client.CState;
import cs460.webdnd.client.DataServiceAsync;
import cs460.webdnd.client.mvc.controller.ICommand;
import cs460.webdnd.client.mvc.model.IModel;
import cs460.webdnd.client.utilities.Misc;
import cs460.webdnd.shared.FileInformation;
import cs460.webdnd.shared.Response;
import cs460.webdnd.shared.Response.Res;

/**
 * @author niallschavez
 */
public class LoadThemeCommand implements ICommand {
	
	String theme;
	IModel model;
	public LoadThemeCommand(String theme, IModel model){
		this.theme=theme;
		this.model=model;
	}
	@Override
	public void run() {
		CState state = Misc.getCState(model);
		if(state == null)
			return;
		state.getSharedService().getFileInfo(theme, new GetThemesCallBack(model));
		
	}

	/**
	 * 
	 * @author Theodore Schnepper
	 *
	 */
	private class GetThemesCallBack implements AsyncCallback<Response<LinkedList<FileInformation>>> {
		private IModel model;
		public GetThemesCallBack(IModel model) {
			this.model = model;
		}

		@Override
		public void onFailure(Throwable caught) { }

		@Override
		public void onSuccess(Response<LinkedList<FileInformation>> result) {
			if( result.getResponse() != Res.ALL_OK){
				SC.say("Error returned from the server: \""+result.getResponse()+"\"");
				return;
			}
			CState state = Misc.getCState(model);
			if(state == null)
				return;
			
			CopyFileConfirmation cfc = new CopyFileConfirmation(result.getFile().size());
			
			for(FileInformation fi: result.getFile())
				state.getSharedService().getFile(theme, fi.getFileName(), new LoadSelectedTheme(fi, state, cfc));
			
		}
	}
	
	/**
	 * 
	 * @author Theodore Schnepper
	 *
	 */
	private class LoadSelectedTheme implements AsyncCallback<Response<String>> {
		private FileInformation fi;
		private CState state;
		private CopyFileConfirmation cfc;
		public LoadSelectedTheme(FileInformation fi, CState state, CopyFileConfirmation cfc) {
			this.fi = fi;
			this.state = state;
			this.cfc = cfc;
		}

		@Override
		public void onFailure(Throwable caught) {
			SC.say("Unable to Connect to Server While Trying to Retrieve File: " + fi.getFileName());
		}

		@Override
		public void onSuccess(Response<String> result) {
			if( result.getResponse() != Res.ALL_OK){
				SC.say("Unable to Retrieve File From Server: " + fi.getFileName() + " Reason: " + result.getResponse());
				return;
			}
			DataServiceAsync ds = state.getDataService();
			ds.sendHtml(result.getFile(), fi.getFileName(), cfc);
		}
	}
	
	private class CopyFileConfirmation implements AsyncCallback<Response<Boolean>>{
		private int limit;
		private int num;
		public CopyFileConfirmation(int limit){
			this.limit = limit;
			this.num = 0;
		}
		@Override
		public void onFailure(Throwable caught) {
			SC.say("Unable to Contact Server When Trying to Save: ");
		}

		@Override
		public void onSuccess(Response<Boolean> result) {
			num++;
			if(result.getResponse() != Res.ALL_OK){
				SC.say("Unable to Save to Server: " + result.getResponse());
				return;
			}
			System.out.println("Sucessfully Copied File: ");
			
			if(num >= limit ){
				model.run(new LoadCurrentProjectCommand(model));
			}
		}
	}	
}
