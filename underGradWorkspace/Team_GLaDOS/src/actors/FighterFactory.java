package actors;

import java.io.Serializable;

import gameBoard.FlatMap;

/**
 * A factory class to create Fighter types. Used for character selection.
 * @author Ryan Hammer
 *
 */
public class FighterFactory implements PCFactory, Serializable {

	/**
	 * Used for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Returns a PC of type Fighter.
	 * @return PC The type of being to return
	 */
	@Override
	public PC createPlayerClass(FlatMap location, String name, String gender) {
		return new Fighter(location, name, gender);
	}

}
