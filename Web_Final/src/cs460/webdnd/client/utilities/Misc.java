package cs460.webdnd.client.utilities;

import cs460.webdnd.client.mvc.controller.IController;
import cs460.webdnd.client.mvc.model.IModel;
import cs460.webdnd.client.mvc.model.IState;
import cs460.webdnd.client.CState;

/**
 * Miscellaneous helper class for small functions that aren't really a part of
 * anything.
 * 
 * @author Theodore Schnepper
 * @author Ian Mallett
 */
public final class Misc {
	/**
	 * The enumeration of the modules. Good for passing into "void .warn(...)".
	 * 
	 * @author Ian Mallett
	 */
	public static enum MODULE {
		MODULE_PARSER("PARSER"), MODULE_SERIALIZE("SERIALIZE"), MODULE_COMPRESS(
				"(DE)COMPRESS"), MODULE_CRYPTOGRAPHY("CRYPTOGRAPHY");
		private MODULE(String text) {
			this.text = text;
		}

		@Override
		public final String toString() {
			return text;
		}

		private final String text;
	};

	/**
	 * Outputs a warning to stderr. Formats newlines specially, so use the \n
	 * character in your text as you wish!
	 * 
	 * @param msg
	 *            the warning's message. Use newlines intuitively!
	 * @param module
	 *            the module generating the error
	 */
	public final static void warn(final String msg, final MODULE module) {
		System.err.print(module.toString() + " WARNING: ");
		final String[] lines = msg.split("\n");
		System.err.println(lines[0]);
		for (int i = 1; i < lines.length; ++i) {
			System.err.println("                " + lines[i]);
		}
	}

	/***
	 * This will return the IState cast as a CState provided, provided the
	 * IState is an instance of CState, and that the state itself isn't null. If
	 * none of these conditions are met, this returns null.
	 * 
	 * @param state
	 *            the IState to cast to a CState
	 * @return CState cast of the IState or null if it is not an instance of
	 *         CState.
	 */
	public final static CState getCState(final IState state) {
		final CState cstate;
		if (state instanceof CState)
			cstate = (CState) state;
		else
			cstate = null;

		return cstate;
	}

	/***
	 * This will return the IState of the model cast as a CState provided,
	 * provided the IState is an instance of CState, and that the model itself
	 * isn't null. If none of these conditions are met, this returns null.
	 * 
	 * @param model
	 *            the Model to get the CState From
	 * @return The CState of the Model, or null if an issue was encountered.
	 */
	public final static CState getCState(final IModel model) {
		if (model == null)
			return null;
		return getCState(model.getState());
	}

	/***
	 * This will return the IState of the model cast as a CState provided,
	 * provided the IState is an instance of CState, and that the IController
	 * itself isn't null. If none of these conditions are met, this returns
	 * null.
	 * 
	 * @param cont
	 *            the IController to pull the CState from.
	 * @return The CState of the IController's Model, or null if this is not
	 *         possible.
	 */
	public final static CState getCState(IController cont) {
		if (cont == null)
			return null;
		return getCState(cont.getModel());
	}
}