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
package org.vaadin.gwtgraphics.client.impl;

import com.google.gwt.dom.client.Element;

/**
 * This class contains some hacks for the SVG implementation of the Safari
 * browser. It seems that Safari 4 works correctly without these hacks.
 * 
 * @author Henri Kerola
 * 
 */
public class SafariSVGImpl extends SVGImpl {

	@Override
	public void setX(Element element, int x, boolean attached) {
		super.setX(element, x, attached);
		if (element.getTagName().equals("text")) {
			// When a text element is already added to the document's DOM tree,
			// for some reason element's position doesn't update without setting
			// some other attribute.
			setTextFontSize(element, getTextFontSize(element), attached);
		}
	}

	@Override
	public void setY(Element element, int y, boolean attached) {
		super.setY(element, y, attached);
		if (element.getTagName().equals("text")) {
			// When a text element is already added to the document's DOM tree,
			// for some reason element's position doesn't update without setting
			// some other attribute.
			setTextFontSize(element, getTextFontSize(element), attached);
		}
	}

	@Override
	public void setRectangleRoundedCorners(Element element, int radius) {
		super.setRectangleRoundedCorners(element, radius);
		// Set some other attribute to make Safari round the corners
		// immediately.
		setWidth(element, getWidth(element));
	}

}
