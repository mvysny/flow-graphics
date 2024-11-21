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
 * A class implementing this interface can be positioned by setting the x and y
 * coordinates.
 * 
 * @author Henri Kerola
 * 
 */
public interface Positionable {

	/**
	 * Returns the x-coordinate position of the element.
	 * 
	 * @return the x-coordinate position in pixels
	 */
	public abstract int getX();

	/**
	 * Sets the x-coordinate position of the element.
	 * 
	 * @param x
	 *            the new x-coordinate position in pixels
	 */
	public abstract void setX(int x);

	/**
	 * Returns the y-coordinate position of the element.
	 * 
	 * @return the y-coordinate position in pixels
	 */
	public abstract int getY();

	/**
	 * Sets the y-coordinate position of the element.
	 * 
	 * @param y
	 *            the new y-coordinate position in pixels
	 */
	public abstract void setY(int y);
}