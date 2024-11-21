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
package org.vaadin.gwtgraphics.client.shape.path;

/**
 * This class represents Path's lineTo step. Draws a straight line from the
 * current point to a new point.
 * 
 * @author Henri Kerola
 * 
 */
public class LineTo extends MoveTo {

	/**
	 * Instantiates a new LineTo step with given properties.
	 * 
	 * @param relativeCoords
	 *            true if given coordinates are relative
	 * @param x
	 *            the x-coordinate in pixels
	 * @param y
	 *            the y-coordinate in pixels
	 */
	public LineTo(boolean relativeCoords, int x, int y) {
		super(relativeCoords, x, y);
	}

	@Override
	public String getSVGString() {
		return isRelativeCoords() ? "l" : "L" + getX() + " " + getY();
	}
}
