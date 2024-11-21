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
package org.vaadin.flowgraphics.client;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.internal.StateTree;
import org.jsoup.nodes.Element;
import org.vaadin.flowgraphics.client.gwt.Widget;
import org.vaadin.flowgraphics.client.impl.SVGImpl;

/**
 * The following example shows how a DrawingArea instance is created and added
 * to a GWT application. A rectangle is added to this DrawingArea instance. When
 * the user clicks this rectangle its color changes.
 * 
 * <pre>
 * DrawingArea canvas = new DrawingArea(200, 200);
 * RootPanel.get().add(canvas);
 * 
 * Rectangle rect = new Rectangle(10, 10, 100, 50);
 * canvas.add(rect);
 * rect.setFillColor(&quot;blue&quot;);
 * rect.addClickHandler(new ClickHandler() {
 * 	public void onClick(ClickEvent event) {
 * 		Rectangle rect = (Rectangle) event.getSource();
 * 		if (rect.getFillColor().equals(&quot;blue&quot;)) {
 * 			rect.setFillColor(&quot;red&quot;);
 * 		} else {
 * 			rect.setFillColor(&quot;blue&quot;);
 * 		}
 * 	}
 * });
 * </pre>
 * 
 * @author Henri Kerola
 * 
 */
public class DrawingArea extends Div implements Widget, VectorObjectContainer {

	private static final SVGImpl impl = new SVGImpl();

	private final Element root;

	private List<VectorObject> childrens = new ArrayList<VectorObject>();

	/**
	 * Creates a DrawingArea of given width and height.
	 * 
	 * @param width
	 *            the width of DrawingArea in pixels
	 * @param height
	 *            the height of DrawingArea in pixels
	 */
	public DrawingArea(int width, int height) {
		root = getImpl().createDrawingArea(null, width, height);
		setWidth(width);
		setHeight(height);
	}

	/**
	 * If you modify this element directly, don't forget to call {@link #flushLazy()}!
	 * @return the root SVG element.
	 */
	public Element getSvgElement() {
		return root;
	}

	protected SVGImpl getImpl() {
		return impl;
	}

	/**
	 * Returns a String that indicates what graphics renderer is used. This
	 * String is "VML" for Internet Explorer and "SVG" for other browsers.
	 * 
	 * @return the used graphics renderer
	 */
	public String getRendererString() {
		return getImpl().getRendererString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.vaadin.gwtgraphics.client.VectorObjectContainer#add(org.vaadin.
	 * gwtgraphics.client.VectorObject)
	 */
	public VectorObject add(VectorObject vo) {
		getImpl().add(root, vo.getElement(), vo.isAttached());
		vo.setParent(this);
		childrens.add(vo);
		flushLazy();
		return vo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.vaadin.gwtgraphics.client.VectorObjectContainer#insert(org.vaadin
	 * .gwtgraphics.client.VectorObject, int)
	 */
	public VectorObject insert(VectorObject vo, int beforeIndex) {
		if (beforeIndex < 0 || beforeIndex > getVectorObjectCount()) {
			throw new IndexOutOfBoundsException();
		}

		if (childrens.contains(vo)) {
			childrens.remove(vo);
		}

		childrens.add(beforeIndex, vo);
		vo.setParent(this);
		getImpl().insert(root, vo.getElement(), beforeIndex, vo.isAttached());
		flushLazy();

		return vo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.vaadin.gwtgraphics.client.VectorObjectContainer#bringToFront(org.
	 * vaadin.gwtgraphics.client.VectorObject)
	 */
	public VectorObject bringToFront(VectorObject vo) {
		if (vo.getParent() != this) {
			return null;
		}
		getImpl().bringToFront(root, vo.getElement());
		flushLazy();
		return vo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.vaadin.gwtgraphics.client.VectorObjectContainer#remove(org.vaadin
	 * .gwtgraphics.client.VectorObject)
	 */
	public VectorObject remove(VectorObject vo) {
		if (vo.getParent() != this) {
			return null;
		}
		vo.setParent(null);
		vo.getElement().remove();
		childrens.remove(vo);
		flushLazy();
		return vo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.vaadin.gwtgraphics.client.VectorObjectContainer#clear()
	 */
	public void clear() {
		List<VectorObject> childrensCopy = new ArrayList<VectorObject>();
		childrensCopy.addAll(childrens);
		for (VectorObject vo : childrensCopy) {
			this.remove(vo);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.vaadin.gwtgraphics.client.VectorObjectContainer#getVectorObject(int)
	 */
	public VectorObject getVectorObject(int index) {
		return childrens.get(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.vaadin.gwtgraphics.client.VectorObjectContainer#getVectorObjectCount
	 * ()
	 */
	public int getVectorObjectCount() {
		return childrens.size();
	}

	/**
	 * Sets the width of the DrawingArea in pixels.
	 * 
	 * @param width
	 *            the new width in pixels
	 */
	public void setWidth(int width) {
		setWidth(width + "px");
	}

	/**
	 * Sets the height of the DrawingArea in pixels.
	 * 
	 * @param height
	 *            the new height
	 */
	public void setHeight(int height) {
		setHeight(height + "px");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.UIObject#setHeight(java.lang.String)
	 */
	@Override
	public void setHeight(String height) {
		super.setHeight(height);
		boolean successful = false;
		if (height != null && height.endsWith("px")) {
			try {
				getImpl().setHeight(root, Integer.parseInt(height.substring(0,
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.UIObject#setWidth(java.lang.String)
	 */
	@Override
	public void setWidth(String width) {
		super.setWidth(width);
		boolean successful = false;
		if (width != null && width.endsWith("px")) {
			try {
				getImpl().setWidth(root, Integer
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.google.gwt.user.client.ui.UIObject#setStyleName(java.lang.String)
	 */
	@Override
	public void setStyleName(String style) {
		getElement().getClassList().clear();
		getElement().getClassList().add(
				style + " " + style + "-" + getImpl().getStyleSuffix());
	}

	@Override
	protected void onAttach(AttachEvent attachEvent) {
		super.onAttach(attachEvent);
		for (VectorObject vo : childrens) {
			vo.onAttach();
		}
	}

	@Override
	protected void onDetach(DetachEvent detachEvent) {
		for (VectorObject vo : childrens) {
			vo.onDetach();
		}
		super.onDetach(detachEvent);
	}

	public void flush() {
		getElement().setProperty("innerHTML", root.toString());
		if (flushRegistration != null) {
			flushRegistration.remove();
			flushRegistration = null;
		}
	}

	private StateTree.ExecutionRegistration flushRegistration = null;
	public void flushLazy() {
		if (flushRegistration == null) {
			flushRegistration = UI.getCurrent().beforeClientResponse(this, ctx -> flush());
		}
	}
}
