package com.soda.machine.api.config;

public final class EnvConfig {

	public static final String SDLC_KEY = "sdlc.environment.key";

	public static final String SERVER_ENV_DEV = "DEV";

	public static final String SERVER_ENV_QAT = "QAT";

	public static final String SERVER_ENV_PRD = "PRD";

//	private static final List<String> VALID_ENVIRONMENTS = Collections
//			.unmodifiableList(Arrays.asList(SERVER_ENV_DEV, SERVER_ENV_QAT, SERVER_ENV_PRD));

	public static String getEnv() {
		return BaseConfig.getInstance().getProperty(SDLC_KEY);
	}

	public static Boolean isDev() {
		return SERVER_ENV_DEV.equals(getEnv());
	}

	public static Boolean isQAT() {
		return SERVER_ENV_QAT.equals(getEnv());
	}

	public static Boolean isPrd() {
		return SERVER_ENV_PRD.equals(getEnv());
	}

}
