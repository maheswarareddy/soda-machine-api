package com.soda.machine.api.config;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

public class PropertyFileConfigProvider {

	private String filePath;

	private Boolean loadEnvironmentConfig;

	public PropertyFileConfigProvider(String filePath) {
		this(filePath, true);
	}

	public PropertyFileConfigProvider(String filePath, Boolean loadEnvironmentConfig) {
		this.filePath = filePath;
		this.loadEnvironmentConfig = loadEnvironmentConfig;
	}

	public void loadProperties(Properties props) {
		if (filePath == null) {
			return;
		}
		loadProperties(props, filePath);
		if (loadEnvironmentConfig) {
			loadProperties(props, getEnvironmentPropertyFile());
		}
	}

	private String getEnvironmentPropertyFile() {
		String environmentFile = FilenameUtils.getFullPath(filePath) + FilenameUtils.getBaseName(filePath) + "."
				+ EnvConfig.getEnv() + "." + FilenameUtils.getExtension(filePath);
		return environmentFile;
	}

	public void loadProperties(Properties props, String fileName) {
		InputStream stream = null;
		try {
			URL resource = this.getClass().getClassLoader().getResource(fileName);
			if (resource == null) {
				return;
			} else {
				stream = resource.openStream();

				props.load(stream);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(stream);
		}
	}

}
