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
package org.vaadin.gwtgraphics.client;

/**
 * This interface must be implemented by VectorObjects that can be stroked.
 * 
 * @author Henri Kerola
 * 
 */
public interface Strokeable {

	/**
	 * Returns the stroke color.
	 */
	public abstract String getStrokeColor();

	/**
	 * Sets stroke color. The color value is specified using one of the CSS2
	 * color notations. For example, the following values are legal:
	 * <ul>
	 * <li>red
	 * <li>#ff0000
	 * <li>#f00
	 * <li>rgb(255, 0, 0)
	 * <li>rgb(100%, 0%, 0%)
	 * </ul>
	 * 
	 * @see <a href="http://www.w3.org/TR/CSS2/syndata.html#value-def-color">http://www.w3.org/TR/CSS2/syndata.html#value-def-color</a>
	 * @param color
	 *            the new stroke color
	 */
	public abstract void setStrokeColor(String color);

	/**
	 * Returns the stroke width in pixels.
	 * 
	 * @return the stroke width in pixels
	 */
	public abstract int getStrokeWidth();

	/**
	 * Sets the stroke width in pixels.
	 * 
	 * @param width
	 *            the stroke width in pixels
	 */
	public abstract void setStrokeWidth(int width);

	/**
	 * Returns the stroke opacity of the element.
	 * 
	 * @return the current stroke opacity
	 */
	public abstract double getStrokeOpacity();

	/**
	 * Sets the stroke opacity of the element. The initial value 1.0 means fully
	 * opaque stroke. On the other hand, value 0.0 means fully transparent
	 * paint.
	 * 
	 * @param opacity
	 *            the new stroke opacity
	 */
	public abstract void setStrokeOpacity(double opacity);

}