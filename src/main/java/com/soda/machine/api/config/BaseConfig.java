package com.soda.machine.api.config;

public final class BaseConfig extends Config {

	private static final long serialVersionUID = -6979547258459571181L;
	private static final BaseConfig config = new BaseConfig();

	public BaseConfig() {
	}

	public static Config getInstance() {
		return config;
	}

	@Override
	protected  Config getPropertyContainer() {
		return getInstance();
	}

}
