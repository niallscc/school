package cs460.webdnd.client.commands.servercommunication;

import java.util.LinkedList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.SC;

import cs460.webdnd.client.CState;
import cs460.webdnd.client.mvc.controller.ICommand;
import cs460.webdnd.client.mvc.model.IModel;
import cs460.webdnd.client.utilities.Misc;
import cs460.webdnd.shared.FileInformation;
import cs460.webdnd.shared.Response;
import cs460.webdnd.shared.Response.Res;

/***
 * Will check to see if the file you are attempting to delete is on the server.  If it is, it will attempt to delete it.
 * 
 * @author Theodore Schnepper
 *
 */
public class DeleteFileCommand implements ICommand {

	private String filename;
	protected IModel model;
	
	public DeleteFileCommand(String filename, IModel model){
		this.filename = filename;
		this.model = model;
	}
	
	@Override
	public void run() {
		CState state = Misc.getCState(model);
		if(state == null)
			return;
		
		state.getDataService().getFiles(new CheckFileList(filename, state));
		
	}
	
	/**
	 * This class retrieves the FileInformation for this Project from the server, and simply
	 * checks to see if the specified File is found within the list of files.  After which it will
	 * proceed to attempt to delete the file on the Server.  If an error occurs, the user will be
	 * notified appropriately.
	 * 
	 * @author Theodore Schnepper
	 *
	 */
	private class CheckFileList implements AsyncCallback<Response<LinkedList<FileInformation>>>{
		private String filename;
		private CState state;
		public CheckFileList(String filename, CState state){
			this.filename = filename;
			this.state = state;
		}
		
		
		@Override
		public void onFailure(Throwable caught) {
			SC.say("Unable to Connect to Server When Attempting to Retrieve File Information.");
		}

		@Override
		public void onSuccess(Response<LinkedList<FileInformation>> result) {
			if(result == null)
				return;
			
			if(result.getResponse() != Res.ALL_OK){
				SC.say("Unable to Retrieve File Information: " + result.getResponse());
				return;
			}
			
			boolean found = false;
			for(FileInformation f : result.getFile()){
				if(f.getFileName().equalsIgnoreCase(filename)){
					found = true;
					break;
				}
			}
			
			if(found)
				state.getDataService().deleteFile(filename, new DeleteConfirmation(filename));
		}
	}
	
	/**
	 * Just a simple DeleteConfirmation class to handle the delete success or failure event returned
	 * from the Server.  If an error occurs, it will notify the user gracefully. 
	 * 
	 * @author Theodore Schnepper
	 *
	 */
	private class DeleteConfirmation implements AsyncCallback<Response<Boolean>>{

		private String filename;
		public DeleteConfirmation(String filename){
			this.filename = filename;
		}
		
		@Override
		public void onFailure(Throwable caught) {
			SC.say("Unable to Connect to Server when Attempting to Delete File: " + filename);
		}

		@Override
		public void onSuccess(Response<Boolean> result) {
			if(result == null)
				return;
			
			if(result.getResponse() != Res.ALL_OK){
				SC.say("Unable to Delete the File " + filename + ": " + result.getResponse());
				return;
			}
			
			System.out.println("Deleted Filed: " + filename);
		}
		
	}

}
