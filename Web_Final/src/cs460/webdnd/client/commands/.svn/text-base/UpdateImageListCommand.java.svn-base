package cs460.webdnd.client.commands;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Img;

import cs460.webdnd.client.CState;
import cs460.webdnd.client.elements.ImgEx;
import cs460.webdnd.client.mvc.controller.ICommand;
import cs460.webdnd.client.mvc.model.IModel;
import cs460.webdnd.client.utilities.Misc;
import cs460.webdnd.shared.FileInformation;
import cs460.webdnd.shared.Response;
import cs460.webdnd.shared.FileInformation.FileType;
import cs460.webdnd.shared.Response.Res;

/**
 * This Command updates the local copy of the Image List on the Client.  This Command
 * should only be called whenever a file is uploaded, or when the project is first loaded.
 * 
 * @author Theodore Schnepper
 */
public class UpdateImageListCommand implements ICommand {
	protected IModel model;
	
	public UpdateImageListCommand(IModel model){
		this.model = model;
	}
	@Override public void run() {
		CState state = Misc.getCState(model);
		if(state == null) return;
		state.getDataService().getFiles(new PopulateImageList(model));
	}
	
	private class PopulateImageList implements AsyncCallback<Response<LinkedList<FileInformation>>> {
		private List<Img> images;
		private CState state;
		//private ImageExplorer ie;
		
		public PopulateImageList(IModel model) {
			this.images = new ArrayList<Img>();
			this.state = Misc.getCState(model);
		}
		
		@Override
		public void onFailure(Throwable caught) {
			SC.say("Unable to Connect to Server When Attempting to Update Image List.");
		}
		
		@Override
		public void onSuccess(Response<LinkedList<FileInformation>> result) {
			if (result==null || result.getResponse()!=Res.ALL_OK) {
				SC.say("Unable to get List of Files: "
						+ result.getResponse().toString());
				return;
			}
			images.clear();
			for (FileInformation f : result.getFile()) {
				System.out.println(f.getFileName());
				System.out.println(f.getType());
				if (f.getType() == FileType.IMAGE) {
					String pathReal = state.getUploadActionURL() + "?file=" + f.getFileName();
					String path = state.getUploadActionURL() + "?thumb=" + f.getFileName();
					ImgEx i = new ImgEx(path, pathReal);
					System.out.println("list populated");
					images.add(i);
				}
			}
			CState state = Misc.getCState(model);
			if(state == null)
				return;
			state.setImageList(images);
			state.update();
		}
	}
}