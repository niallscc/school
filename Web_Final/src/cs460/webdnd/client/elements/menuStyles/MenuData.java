/**
 * @author niallschavez
 * this is the list of menu styles we have at our disposal :P 
 */
package cs460.webdnd.client.elements.menuStyles;

public class MenuData {
	private final static MenuRecord[] records = getNewRecords();

	public static MenuRecord[] getRecords() {
		return records;
	}

	public final static MenuRecord[] getNewRecords() {
		return new MenuRecord[] {

				new MenuRecord("Green Bliss", "Green", "N. Chavez","3/12/2012", "greenBliss"),
				new MenuRecord("Blue Bonanza", "Blue", "N. Chavez","3/12/2012", "blueBonanza"),
				new MenuRecord("Black Mamba", "Black", "N. Chavez","3/12/2012", "BlackMamba"),
				new MenuRecord("Red Robin", "Red", "N. Chavez", "3/12/2012","redRobin"),
				new MenuRecord("Gray Goose", "White", "N. Chavez","3/12/2012", "grayGoose"),
				new MenuRecord("Brown Bungaloo", "Brown", "N. Chavez","3/12/2012", "BrownBungaloo"),
				new MenuRecord("Orange Tangerine", "orange", "N. Chavez","3/12/2012", "orangeTangerine") };
	}
}
