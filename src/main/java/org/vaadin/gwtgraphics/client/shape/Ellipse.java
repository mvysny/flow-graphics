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
 * Ellipse represents an ellipse.
 * 
 * @author Henri Kerola
 * 
 */
public class Ellipse extends Shape {

	/**
	 * Creates a new Ellipse with the given position and radius properties.
	 * 
	 * @param x
	 *            the x-coordinate position of the center of the ellipse in
	 *            pixels
	 * @param y
	 *            the y-coordinate position of the center of the ellipse in
	 *            pixels
	 * @param radiusX
	 *            the x-axis radius of the ellipse in pixels
	 * @param radiusY
	 *            the y-axis radius of the ellipse in pixels
	 */
	public Ellipse(int x, int y, int radiusX, int radiusY) {
		setRadiusX(radiusX);
		setRadiusY(radiusY);
		setX(x);
		setY(y);
	}

	@Override
	protected Class<? extends VectorObject> getType() {
		return Ellipse.class;
	}

	/**
	 * Returns the x-axis radius of the ellipse in pixels.
	 * 
	 * @return the x-axis radius of the ellipse in pixels
	 */
	public int getRadiusX() {
		return getImpl().getEllipseRadiusX(getElement());
	}

	/**
	 * Sets the x-axis radius of the ellipse in pixels.
	 * 
	 * @param radiusX
	 *            the x-axis radius of the ellipse in pixels
	 */
	public void setRadiusX(int radiusX) {
		getImpl().setEllipseRadiusX(getElement(), radiusX);
	}

	/**
	 * Returns the y-axis radius of the ellipse in pixels.
	 * 
	 * @return the y-axis radius of the ellipse in pixels
	 */
	public int getRadiusY() {
		return getImpl().getEllipseRadiusY(getElement());
	}

	/**
	 * Sets the y-axis radius of the ellipse in pixels.
	 * 
	 * @param radiusY
	 *            the y-axis radius of the ellipse in pixels
	 */
	public void setRadiusY(int radiusY) {
		getImpl().setEllipseRadiusY(getElement(), radiusY);
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
		if ("radiusx".equals(property)) {
			setRadiusX((int) value);
		} else if ("radiusy".equals(property)) {
			setRadiusY((int) value);
		} else {
			super.setPropertyDouble(property, value);
		}
	}

}
