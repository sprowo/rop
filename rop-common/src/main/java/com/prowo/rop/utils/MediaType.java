package com.prowo.rop.utils;

import java.util.HashMap;
import java.util.Map;

public final class MediaType extends Metadata {

	public MediaType(String name, String description) {
		super(name, description);
	}

	private static volatile Map<String, MediaType> _types = null;

	public static final MediaType APPLICATION_ALL_XML = register(
			"application/*+xml", "All application/*+xml documents");

	public static final MediaType APPLICATION_JSON = register(
			"application/json", "JavaScript Object Notation document");

	public static final MediaType IMAGE_BMP = register("image/bmp",
			"Windows bitmap");

	public static final MediaType IMAGE_GIF = register("image/gif", "GIF image");

	public static final MediaType IMAGE_ICON = register("image/x-icon",
			"Windows icon (Favicon)");

	public static final MediaType IMAGE_JPEG = register("image/jpeg",
			"JPEG image");

	public static final MediaType IMAGE_PNG = register("image/png", "PNG image");

	public static final MediaType TEXT_HTML = register("text/html",
			"HTML document");

	public static final MediaType TEXT_PLAIN = register("text/plain",
			"Plain text");

	public static final MediaType TEXT_XML = register("text/xml", "XML text");

	/**
	 * Returns the known media types map.
	 *
	 * @return the known media types map.
	 */
	private static Map<String, MediaType> getTypes() {
		if (_types == null) {
			_types = new HashMap<String, MediaType>();
		}
		return _types;
	}

	public static synchronized MediaType register(String name,
			String description) {

		if (!getTypes().containsKey(name)) {
			final MediaType type = new MediaType(name, description);
			getTypes().put(name, type);
		}

		return getTypes().get(name);
	}

	public static MediaType valueOf(String name) {
		MediaType result = null;

		if ((name != null) && !name.equals("")) {
			result = getTypes().get(name);
			if (result == null) {
				return APPLICATION_JSON;
			}
		}

		return result;
	}

	/**
	 * Returns the main type.
	 *
	 * @return The main type.
	 */
	public String getMainType() {
		String result = null;

		if (getName() != null) {
			int index = getName().indexOf('/');

			// Some clients appear to use name types without subtypes
			if (index == -1) {
				index = getName().indexOf(';');
			}

			if (index == -1) {
				result = getName();
			} else {
				result = getName().substring(0, index);
			}
		}

		return result;
	}

}
