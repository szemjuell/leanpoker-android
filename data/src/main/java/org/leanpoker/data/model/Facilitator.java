package org.leanpoker.data.model;

/**
 * Created by tbalogh on 06/09/15.
 */
public class Facilitator {
	private final String mName;

	public Facilitator(final String name) {
		this.mName = name;
	}

	public String getName() {
		return mName.replace(mName.charAt(0), Character.toUpperCase(mName.charAt(0)));
	}
}
