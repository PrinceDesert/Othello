package utils;

import java.awt.Color;
import java.io.Serializable;

public class ColorUtils implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private ColorUtils() {}

	public static Color getColorByName(String name) {
		try {
			return (Color) Color.class.getField(name.toUpperCase()).get(null);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
			return null;
		}
	}
}
