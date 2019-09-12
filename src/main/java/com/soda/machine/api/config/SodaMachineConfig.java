package com.soda.machine.api.config;

public class SodaMachineConfig extends Config {

	private static final long serialVersionUID = -8538015303063275113L;
	private static final SodaMachineConfig config = new SodaMachineConfig();
	
	private SodaMachineConfig() {
		new PropertyFileConfigProvider("soda-machine-api.config").loadProperties(this);
	}
	
	public static Config getInstance() {
		return config;
	}

	@Override
	protected Config getPropertyContainer() {
		return getInstance();
	}

}
