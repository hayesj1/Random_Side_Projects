package test.java.com.jhayes.main;

import main.java.com.jhayes.sql.DataBase;
import main.java.com.jhayes.sql.SQLSystem;

/**
 * Created by hayesj3 on 1/28/2016.
 */
public class Main {

	public static void main(String[] args) {
		DataBase db = SQLSystem.getDataBase("test");
		System.out.println("Created database " + db + "!");
	}
}
