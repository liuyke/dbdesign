package org.liuyk.desgin.model;

import java.util.Set;

public class DBDriversLoader {

	private Set<String> drivers;

	public void setDrivers(Set<String> drivers) {
		this.drivers = drivers;
	}

	public void init() {
		for (String driver : drivers) {
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

}
