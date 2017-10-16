package com.sapient.taf.drivermanager;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import io.appium.java_client.AppiumDriver;

public class Driver<T extends WebDriver> {
	private T driverClass;
	private WebDriver driver;

	public Driver(Capabilities capabilities) throws InstantiationException, IllegalAccessException,
			NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		Constructor<? extends WebDriver> driverConstructor = driverClass.getClass().getConstructor(Capabilities.class);
		this.driver = driverConstructor.newInstance(capabilities);
	}

	public Driver(URL remoteUrl, Capabilities capabilities) throws NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Constructor<? extends WebDriver> driverConstructor = driverClass.getClass().getConstructor(URL.class,
				Capabilities.class);
		this.driver = driverConstructor.newInstance(remoteUrl, capabilities);
	}

	public WebDriver getWebDriver() {
		return driver;
	}

	public AppiumDriver<?> getMobileDriver() {
		if (driver instanceof AppiumDriver) {
			return (AppiumDriver<?>) driver;
		} else {
			throw new RuntimeException("Driver initialized is not of Mobile Driver type, Driver Type - "
					+ driverClass.getClass().getName());
		}
	}
}