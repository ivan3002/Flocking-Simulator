
public class SystemProperties {

	/**
	 * Prints out most of the default system properties.
	 */
	public void printProperties() {
		System.out.println("Java version: \"" + System.getProperty("java.version") + "\"");
		System.out.println("Java vendor: \"" + System.getProperty("java.vendor") + "\"");
		System.out.println("Java home: \"" + System.getProperty("java.home") + "\"");
		System.out.println("Java class path: \"" + System.getProperty("java.class.path") + "\"");
		System.out.println("OS arch: \"" + System.getProperty("os.arch") + "\"");
		System.out.println("OS name: \"" + System.getProperty("os.name") + "\"");
		System.out.println("OS version: \"" + System.getProperty("os.version") + "\"");
		System.out.println("User dir: \"" + System.getProperty("user.dir") + "\"");
		System.out.println("User home: \"" + System.getProperty("user.home") + "\"");
		System.out.println("User name: \"" + System.getProperty("user.name") + "\"");
	}

	public static void main(String[] args) {
		SystemProperties sysProperties = new SystemProperties();
		sysProperties.printProperties();
	}
}
