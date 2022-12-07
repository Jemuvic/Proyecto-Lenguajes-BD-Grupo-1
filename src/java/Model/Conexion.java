package Model;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    Connection con;
    String url = "jdbc:oracle:thin:@localhost:1521:XE"; //system instead XE*, revisar si la conexion no funciona
    String user = "system";
    String pass = "pistolas";

    public Connection conectar() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
        }
        return con;
    }

}
