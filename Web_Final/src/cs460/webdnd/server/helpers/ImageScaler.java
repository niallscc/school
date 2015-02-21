package cs460.webdnd.server.helpers;
import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.Transform;

/**
 * For thumbnail creation.
 * 
 * @author Alexandre Rogozine (alexandre.rogozine@live.com)
 */
public class ImageScaler {
	/* Thumbnail Dimensions */
	private static final int MAX_WIDTH  = 200;
	private static final int MAX_HEIGHT = 200;
	
	private ImageScaler() {}
	
	/**
	 * Returns a properly scaled thumbnail. 
	 * If it errors out, simply does not scale image.
	 * 
	 * @param oldImageData Image byte array
	 * @return Preview Image byte array
	 */
	public static byte[] getThumbnail(byte[] oldImageData)
	{
		try 
		{
			ImagesService imagesService = ImagesServiceFactory.getImagesService();
			
			Image oldImage = ImagesServiceFactory.makeImage(oldImageData);
			
			Transform resize = ImagesServiceFactory.makeResize(MAX_WIDTH, MAX_HEIGHT);
			
			Image newImage = imagesService.applyTransform(resize, oldImage);
			
			byte[] newImageData = newImage.getImageData();		
			
			return newImageData;
		}
		catch (Exception e)
		{
			Misc.warning("ImageScaler.getThumbnail { " + e.toString() + " } " );
			return oldImageData;
		}
	}
}