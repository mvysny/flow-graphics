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
package org.vaadin.gwtgraphics.client.animation;

import com.google.gwt.animation.client.Animation;

/**
 * This class can be used to animate classes implementing the Animatable
 * interface.
 * 
 * @author Henri Kerola
 * 
 */
public class Animate {

	private Animatable target;

	private String property;

	private double startValue;

	private double endValue;

	private int duration;

	private Animation animation = new Animation() {

		@Override
		protected void onUpdate(double progress) {
			double value = (endValue - startValue) * progress + startValue;
			target.setPropertyDouble(property, value);
		}

		@Override
		protected void onComplete() {
			super.onComplete();
			Animate.this.onComplete();
		};
	};

	public Animate(Animatable target, String property, double startValue,
			double endValue, int duration) {
		this.target = target;
		this.property = property;
		this.startValue = startValue;
		this.endValue = endValue;
		this.duration = duration;
	}

	/**
	 * Start the animation.
	 */
	public void start() {
		animation.run(duration);
	}

	/**
	 * Stop the animation.
	 */
	public void stop() {
		animation.cancel();
	}

	/**
	 * Called immediately after the animation completes.
	 */
	protected void onComplete() {
	}

	public Animatable getTarget() {
		return target;
	}

	public String getProperty() {
		return property;
	}

	public double getStartValue() {
		return startValue;
	}

	public double getEndValue() {
		return endValue;
	}

	public int getDuration() {
		return duration;
	}

}
