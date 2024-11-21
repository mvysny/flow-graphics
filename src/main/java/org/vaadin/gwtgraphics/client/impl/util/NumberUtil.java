/*
 * Copyright 2011 Henri Kerola
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.vaadin.gwtgraphics.client.impl.util;

import com.google.gwt.dom.client.Element;

/**
 * This util class contains some static helpers for number parsing.
 * 
 * @author Henri Kerola
 * 
 */
public abstract class NumberUtil {

	public static int parseIntValue(Element element, String attr, int defaultVal) {
		return parseIntValue(element.getAttribute(attr), defaultVal);
	}

	public static int parseIntValue(String value, int defaultVal) {
		if (value == null) {
			return defaultVal;
		}
		if (value.endsWith("px")) {
			value = value.substring(0, value.length() - 2);
		}
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return defaultVal;
		}
	}

	/**
	 * Parses a double value from a given String. If this String is null or
	 * does't contain a parsable double, defaultVal is returned.
	 * 
	 * @param value
	 *            String to be parsed.
	 * @param defaultVal
	 * @return
	 */
	public static double parseDoubleValue(String value, double defaultVal) {
		if (value == null) {
			return defaultVal;
		}
		if (value.endsWith("px")) {
			value = value.substring(0, value.length() - 2);
		}
		try {
			return Double.parseDouble(value);
		} catch (NumberFormatException e) {
			return defaultVal;
		}
	}
}
