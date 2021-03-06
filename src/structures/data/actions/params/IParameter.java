package structures.data.actions.params;

import java.util.List;

import exceptions.ParameterParseException;

/**
 * An IAction holds multiple IParameters, which have
 * the ability to parse themselves from Strings.
 *
 * @author Austin
 *
 */
public interface IParameter {

	/**
	 * Attempts to parse a String to whatever kind of value we hold.
	 * This has to be done successfully before getValue() is called.
	 *
	 * @param string
	 * @throws ParameterParseException A useful error to tell the user
	 * 	what happened.
	 */
	void parse(String string) throws ParameterParseException;

	/**
	 * If parsing was successful, we still store the original
	 * string unmodified in case you need a String representation.
	 *
	 * @return
	 */

	String getOriginal();

	/**
	 * The title of the parameter, used for a label or whatever.
	 *
	 * @return
	 */

	String getTitle();

	/**
	 * Actual implementations will return more specific values
	 * like booleans or doubles. This is the parsed value.
	 *
	 * @return The parsed value
	 */

	Object getValue();

	/**
	 * Returns one of the types from the
	 * following enum.
	 *
	 * @return Type of IParameter
	 */
	type getType();

	enum type {

		DOUBLE,
		INTEGER,
		STRING,
		GROOVY,
		CHECKBOX,
		OBJECT_SELECT,
		SPRITE_SELECT,
		SOUND_SELECT,
		ROOM_SELECT,
		SELECT
	}
}
