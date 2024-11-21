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
 * Rectangle represents a rectangle.
 * 
 * @author Henri Kerola
 * 
 */
public class Rectangle extends Shape {

	/**
	 * Creates a new Rectangle with the given position and size properties.
	 * 
	 * @param x
	 *            the x-coordinate position of the top-left corner of the
	 *            rectangle in pixels
	 * @param y
	 *            the y-coordinate position of the top-left corner of the
	 *            rectangle in pixels
	 * @param width
	 *            the width of the Rectangle in pixels
	 * @param height
	 *            the height of the Rectangle in pixels
	 */
	public Rectangle(int x, int y, int width, int height) {
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
	}

	@Override
	protected Class<? extends VectorObject> getType() {
		return Rectangle.class;
	}

	/**
	 * Returns the width of the Rectangle in pixels.
	 * 
	 * @return the width of the Rectangle in pixels
	 */
	public int getWidth() {
		return getImpl().getWidth(getElement());
	}

	/**
	 * Sets the width of the Rectangle in pixels.
	 * 
	 * @param width
	 *            the new width in pixels
	 */
	public void setWidth(int width) {
		getImpl().setWidth(getElement(), width);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.vaadin.gwtgraphics.client.VectorObject#setWidth(java.lang.String)
	 */
	@Override
	public void setWidth(String width) {
		boolean successful = false;
		if (width != null && width.endsWith("px")) {
			try {
				setWidth(Integer
						.parseInt(width.substring(0, width.length() - 2)));
				successful = true;
			} catch (NumberFormatException e) {
			}
		}
		if (!successful) {
			throw new IllegalArgumentException(
					"Only pixel units (px) are supported");
		}
	}

	/**
	 * Returns the height of the Rectangle in pixels.
	 * 
	 * @return the height of the Rectangle in pixels
	 */
	public int getHeight() {
		return getImpl().getHeight(getElement());
	}

	/**
	 * Sets the height of the Rectangle in pixels.
	 * 
	 * @param height
	 *            the new height in pixels
	 */
	public void setHeight(int height) {
		getImpl().setHeight(getElement(), height);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.vaadin.gwtgraphics.client.VectorObject#setHeight(java.lang.String)
	 */
	@Override
	public void setHeight(String height) {
		boolean successful = false;
		if (height != null && height.endsWith("px")) {
			try {
				setHeight(Integer.parseInt(height.substring(0,
						height.length() - 2)));
				successful = true;
			} catch (NumberFormatException e) {
			}
		}
		if (!successful) {
			throw new IllegalArgumentException(
					"Only pixel units (px) are supported");
		}
	}

	/**
	 * Gets the radius of rounded corners in pixels.
	 * 
	 * @return radius of rounded corners in pixels
	 */
	public int getRoundedCorners() {
		return getImpl().getRectangleRoundedCorners(getElement());
	}

	/**
	 * Sets the radius of rounded corners in pixels. Value 0 disables rounded
	 * corners.
	 * 
	 * @param radius
	 *            radius of rounded corners in pixels
	 */
	public void setRoundedCorners(int radius) {
		if (radius < 0) {
			radius = 0;
		}
		getImpl().setRectangleRoundedCorners(getElement(), radius);
	}

	public void setPropertyDouble(String property, double value) {
		property = property.toLowerCase();
		if ("width".equals(property)) {
			setWidth((int) value);
		} else if ("height".equals(property)) {
			setHeight((int) value);
		} else if ("roundedcorners".equals(property)) {
			setRoundedCorners((int) value);
		} else {
			super.setPropertyDouble(property, value);
		}
	}
}