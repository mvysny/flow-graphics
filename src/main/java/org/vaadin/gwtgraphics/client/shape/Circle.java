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
 * Circle represents a circle.
 * 
 * @author Henri Kerola
 */
public class Circle extends Shape {

	/**
	 * Creates a new Circle with the given position and radius properties.
	 * 
	 * @param x
	 *            the x-coordinate position of the center of the circle in
	 *            pixels
	 * @param y
	 *            the y-coordinate position of the center of the circle in
	 *            pixels
	 * @param radius
	 *            the radius of the circle in pixels
	 */
	public Circle(int x, int y, int radius) {
		setRadius(radius);
		setX(x);
		setY(y);
	}

	@Override
	protected Class<? extends VectorObject> getType() {
		return Circle.class;
	}

	/**
	 * Returns the radius of the circle in pixels.
	 * 
	 * @return the radius of the circle in pixels
	 */
	public int getRadius() {
		return getImpl().getCircleRadius(getElement());
	}

	/**
	 * Sets the radius of the circle in pixels.
	 * 
	 * @param radius
	 *            the radius of the circle in pixels
	 */
	public void setRadius(int radius) {
		getImpl().setCircleRadius(getElement(), radius);
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
		if ("radius".equals(property)) {
			setRadius((int) value);
		} else {
			super.setPropertyDouble(property, value);
		}
	}
}
