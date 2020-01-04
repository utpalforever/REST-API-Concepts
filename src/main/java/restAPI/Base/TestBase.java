package restAPI.Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {

	public static Properties prop;
	public int status_code_200=200;
	public int status_code_500=500;
	public int status_code_400=400;
	public int status_code_401=401;
	public int status_code_201=201;

	public TestBase() {
		try {
//			prop = new Properties();
//			FileInputStream ip = new FileInputStream(
//					"E:\\Prep\\RestAPI\\src\\main\\java\\restAPI\\Confi\\config.properties");
//			prop.load(ip);

			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\restAPI\\Confi\\config.properties");
			prop.load(ip);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
