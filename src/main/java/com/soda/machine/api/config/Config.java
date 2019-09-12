package com.soda.machine.api.config;

import java.util.Properties;

public abstract class Config extends Properties {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6447888585002480562L;

	protected abstract Config getPropertyContainer();

	public String getValue(String key) {

		return getPropertyContainer().getProperty(key);
	}

	public int getIntValue(String key) {
		try {
			return Integer.parseInt(getValue(key));
		} catch (Exception e) {
			return 0;
		}
	}

}
