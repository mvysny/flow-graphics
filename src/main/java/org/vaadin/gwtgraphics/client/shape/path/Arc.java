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
 * This class represents an arc step.
 * 
 * @author Henri Kerola
 * 
 */
public class Arc extends LineTo {

	private int rx;

	private int ry;

	private int xAxisRotation;

	private boolean largeArc;

	private boolean sweep;

	public Arc(boolean relativeCoords, int rx, int ry, int xAxisRotation,
			boolean largeArc, boolean sweep, int x, int y) {
		super(relativeCoords, x, y);
		this.rx = rx;
		this.ry = ry;
		this.xAxisRotation = xAxisRotation;
		this.largeArc = largeArc;
		this.sweep = sweep;

	}

	public int getRx() {
		return rx;
	}

	public int getRy() {
		return ry;
	}

	public int getxAxisRotation() {
		return xAxisRotation;
	}

	public boolean isLargeArc() {
		return largeArc;
	}

	public boolean isSweep() {
		return sweep;
	}

	@Override
	public String getSVGString() {
		return isRelativeCoords() ? "a" : "A" + getRx() + "," + getRy() + " "
				+ getxAxisRotation() + " " + (isLargeArc() ? "1" : "0") + ","
				+ (isSweep() ? "1" : "0") + " " + getX() + "," + getY();
	}
}