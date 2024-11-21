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

import org.vaadin.gwtgraphics.client.gwt.AbstractWidget;
import org.vaadin.gwtgraphics.client.gwt.Widget;
import org.vaadin.gwtgraphics.client.impl.SVGImpl;

/**
 * An abstract base class for drawing objects the DrawingArea can contain.
 * 
 * @author Henri Kerola
 */
public abstract class VectorObject extends AbstractWidget {

	private Widget parent;

	private static final SVGImpl impl = new SVGImpl();

	public VectorObject() {
		setElement(impl.createElement(getType()));
	}

	protected SVGImpl getImpl() {
		return impl;
	}

	protected abstract Class<? extends VectorObject> getType();

	public int getRotation() {
		return getImpl().getRotation(getElement());
	}

	public void setRotation(int degree) {
		getImpl().setRotation(getElement(), degree, isAttached());
	}

	public Widget getParent() {
		return parent;
	}

	public void setParent(Widget parent) {
		Widget oldParent = this.parent;
		if (parent == null) {
			if (oldParent != null && oldParent.isAttachedGWT()) {
				onDetach();
				assert !isAttached() : "Failure of "
						+ this.getClass().getName()
						+ " to call super.onDetach()";
			}
			this.parent = null;
		} else {
			if (oldParent != null) {
				throw new IllegalStateException(
						"Cannot set a new parent without first clearing the old parent");
			}
			this.parent = parent;
			if (parent.isAttachedGWT()) {
				onAttach();
				assert isAttached() : "Failure of " + this.getClass().getName()
						+ " to call super.onAttach()";
			}
		}
	}

	@Override
	public void setStyleName(String style) {
		getImpl().setStyleName(getElement(), style);
	}

	@Override
	public void setHeight(String height) {
		throw new UnsupportedOperationException(
				"VectorObject doesn't support setHeight");
	}

	@Override
	public void setWidth(String width) {
		throw new UnsupportedOperationException(
				"VectorObject doesn't support setWidth");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.Widget#onAttach()
	 */
	@Override
	protected void onAttach() {
		super.onAttach();
		getImpl().onAttach(getElement(), isAttached());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.Widget#onDetach()
	 */
	@Override
	protected void onDetach() {
		super.onDetach();
	}
}