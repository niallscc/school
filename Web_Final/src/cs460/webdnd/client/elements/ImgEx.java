package cs460.webdnd.client.elements;

import com.smartgwt.client.widgets.Img;

public class ImgEx extends Img {
	private final String realPath;

	public ImgEx(String path, String realPath) {
		super(path);
		this.realPath = realPath;
	}

	public String getPath() {
		return realPath;
	}
}