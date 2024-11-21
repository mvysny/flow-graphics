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
package org.vaadin.flowgraphics.client.impl.util;

import org.jsoup.nodes.Element;
import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Tag;

/**
 * This class contains helpers used by the SVGImpl class.
 * 
 * @author Henri Kerola
 * 
 */
public abstract class SVGUtil {

	public static final String SVG_NS = "http://www.w3.org/2000/svg";

	public static final String XLINK_NS = "http://www.w3.org/1999/xlink";

	public static Element createSVGElementNS(String tag) {
		return createElementNS(SVG_NS, tag);
	}

	private static Element createElementNS(String ns, String tag) {
		// ignore ns: it's always SVG_NS. We'll add the namespace only to the root <svg> element.
		// This will allow us to work correctly with older JSoup.
		final Element element = new Element(tag);
		if (tag.equals("svg")) {
			element.attr("xmlns", ns);
		}
		return element;
	}

	public static void setAttributeNS(Element elem, String attr, int value) {
		setAttributeNS(null, elem, attr, "" + value);
	}

	public static void setAttributeNS(Element elem, String attr, String value) {
		setAttributeNS(null, elem, attr, value);
	}

	public static void setAttributeNS(String uri, Element elem,
			String attr, String value) {
		// @todo mavi jsoup doesn't support attribute namespaces?
		elem.attributes().put(attr, value);
	}

	public static void setClassName(Element element, String name) {
		element.attr("class", name);
	}

	public static SVGBBox getBBBox(Element element, boolean attached) {
		// @todo mavi no way to retrieve bounding box on server-side.
		return new SVGBBox();
	}
	/*-{
		var bbox = null;
		if (attached) {
			bbox = element.getBBox();
		} else {
			var ns = @org.vaadin.gwtgraphics.client.impl.util.SVGUtil::SVG_NS;
			var svg = $doc.createElementNS(ns, "svg");
			var parent = element.parentNode;
			svg.appendChild(element);
			$doc.documentElement.appendChild(svg);
			bbox = element.getBBox();
			$doc.documentElement.removeChild(svg);
			if (parent != null) {
				parent.appendChild(element);
			}
		}
		return bbox;
	}-*/;

}
