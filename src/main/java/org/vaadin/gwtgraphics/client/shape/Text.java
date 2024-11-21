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
package org.vaadin.gwtgraphics.client.shape;

import org.vaadin.gwtgraphics.client.Shape;
import org.vaadin.gwtgraphics.client.VectorObject;

/**
 * Embed text into DrawingArea.
 * 
 * @author Henri Kerola
 * 
 */
public class Text extends Shape {

	/**
	 * Creates a new instance of Text. Position and the text to be rendered are
	 * given as paramaters. Font family is set to "Arial" and font size to 20px.
	 * 
	 * @param x
	 *            the x-coordinate position in pixels
	 * @param y
	 *            the y-coordinate position in pixels
	 * @param text
	 *            the text to be rendered
	 */
	public Text(int x, int y, String text) {
		setX(x);
		setY(y);
		setText(text);
		setFontFamily("Arial");
		setFontSize(20);
	}

	@Override
	protected Class<? extends VectorObject> getType() {
		return Text.class;
	}

	/**
	 * Returns the rendered text.
	 * 
	 * @return the rendered text
	 */
	public String getText() {
		return getImpl().getText(getElement());
	}

	/**
	 * Sets the text to be rendered.
	 * 
	 * @param text
	 *            the text
	 */
	public void setText(String text) {
		getImpl().setText(getElement(), text, isAttached());
	}

	/**
	 * Returns the font family of the text.
	 * 
	 * @return the font family
	 */
	public String getFontFamily() {
		return getImpl().getTextFontFamily(getElement());
	}

	/**
	 * Sets the font family of the text.
	 * 
	 * @param family
	 *            the font family
	 */
	public void setFontFamily(String family) {
		getImpl().setTextFontFamily(getElement(), family, isAttached());
	}

	/**
	 * Returns the font size of the text.
	 * 
	 * @return the size
	 */
	public int getFontSize() {
		return getImpl().getTextFontSize(getElement());
	}

	/**
	 * Sets the font size of the text.
	 * 
	 * @param size
	 *            the size
	 */
	public void setFontSize(int size) {
		getImpl().setTextFontSize(getElement(), size, isAttached());
	}

	/**
	 * Returns the width of the rendered text in pixels.
	 * 
	 * @return the width of the rendered text in pixels
	 */
	public int getTextWidth() {
		return getImpl().getTextWidth(getElement());
	}

	/**
	 * Returns the height of the rendered text in pixels.
	 * 
	 * @return the height of the rendered text in pixels
	 */
	public int getTextHeight() {
		return getImpl().getTextHeight(getElement());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.vaadin.gwtgraphics.client.Shape#setPropertyDouble(java.lang.String,
	 * double)
	 */
	public void setPropertyDouble(String property, double value) {
		property = property.toLowerCase();
		if ("fontsize".equals(property)) {
			setFontSize((int) value);
		} else {
			super.setPropertyDouble(property, value);
		}
	}
}
