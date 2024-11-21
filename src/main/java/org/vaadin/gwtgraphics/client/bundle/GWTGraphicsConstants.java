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
package org.vaadin.gwtgraphics.client.bundle;

import com.google.gwt.i18n.client.Constants;

/**
 * The version of GWT Graphics can be read in GWT Java code as follows:
 * 
 * <pre>
 * GWTGraphicsConstants constants = GWT.create(GWTGraphicsConstants.class);
 * String version = constants.version();
 * </pre>
 * 
 * @author Henri Kerola
 * 
 */
public interface GWTGraphicsConstants extends Constants {

	/**
	 * Returns the version of GWT Graphics, e.g. "1.0.0".
	 * 
	 * @return the version of GWT Graphics
	 */
	String version();
}
