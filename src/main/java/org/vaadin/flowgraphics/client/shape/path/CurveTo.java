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
package org.vaadin.flowgraphics.client.shape.path;

/**
 * This class represents Path's curveto step. Draws a cubic bezier curve from
 * the current point to (x, y). (x1, y1) is the control point for the beginning
 * of the curve. (x2, y2) is the control point for the end of the curve.
 * 
 * @author Henri Kerola
 * 
 */
public class CurveTo extends LineTo {

	private int x1;

	private int y1;

	private int x2;

	private int y2;

	public CurveTo(boolean relativeCoords, int x1, int y1, int x2, int y2,
			int x, int y) {
		super(relativeCoords, x, y);
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	public int getX1() {
		return x1;
	}

	public int getY1() {
		return y1;
	}

	public int getX2() {
		return x2;
	}

	public int getY2() {
		return y2;
	}

	@Override
	public String getSVGString() {
		return isRelativeCoords() ? "c" : "C" + getX1() + " " + getY1() + " "
				+ getX2() + " " + getY2() + " " + getX() + " " + getY();
	}
}