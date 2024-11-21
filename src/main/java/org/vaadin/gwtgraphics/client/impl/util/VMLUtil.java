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

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.NodeList;

/**
 * This class contains helpers used by the VMLImpl class.
 * 
 * @author Henri Kerola
 * 
 */
public abstract class VMLUtil {

	public static final String VML_NS_PREFIX = "vml";

	public static final String VML_ELEMENT_CLASSNAME = "vml-element";

	public static Element createVMLElement(String name) {
		Element element = Document.get().createElement(
				VML_NS_PREFIX + ":" + name);
		element.setClassName(VML_ELEMENT_CLASSNAME);
		return element;
	}

	public static Element getOrCreateChildElementWithTagName(Element element,
			String name) {
		Element e = getChildElementWithTagName(element, name);
		if (e != null) {
			return e;
		}
		return element.appendChild(createVMLElement(name));
	}

	public static String getPropertyOfFirstChildElementWithTagName(
			Element element, String name, String attr) {
		Element e = getChildElementWithTagName(element, name);
		if (e != null) {
			return e.getPropertyString(attr);
		}
		return "";
	}

	public static boolean hasChildElementWithTagName(Element element,
			String name) {
		Element e = getChildElementWithTagName(element, name);
		return e != null;
	}

	private static Element getChildElementWithTagName(Element element,
			String name) {
		NodeList<Node> nodes = element.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.getItem(i);
			if (node.getNodeName().equals(name)) {
				return Element.as(node);
			}
		}
		return null;
	}

	public native static String getTagName(Element element) /*-{
		return element.tagName;
	}-*/;
}
