package main.java.com.jhayes.sql;

/**
 * Created by hayesj3 on 1/28/2016.
 */
public enum FrameworkStates {
	EMBEDDED("embedded", "jdbc:derby:", null),
	CLIENT("derbyclient", "jdbc:derby://localhost:1527/", null),
	SHUTDOWNDB("shutdown", null, ";shutdown=true"),
	SHUTDOWNDERBY("shutdownAll", null, ";shutdown=true");

	private String value;
	private String protocol;
	private String urlAddons;

	public String getValue() { return this.value; }
	public String getProtocol() { return this.protocol; }
	public String getUrlAddons() { return this.urlAddons; }

	FrameworkStates(String value, String protocol, String urlAddons) {
		this.value = value;
		this.protocol = protocol;
		this.urlAddons = urlAddons;
	}
}
