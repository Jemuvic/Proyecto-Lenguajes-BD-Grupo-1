
package sistema3;

import java.sql.Connection;
import java.sql.DriverManager;


public class Coneccion {

    private Connection conexion;

    public static void main(String[] args) {
        Coneccion obconeccion = new Coneccion();
        obconeccion.Conectar();
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public Coneccion Conectar() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            String BaseDeDatos = "jdbc:oracle:thin:@localhost:1521:XE";
            conexion = DriverManager.getConnection(BaseDeDatos, "system", "pistolas");
            if (conexion != null) {
                System.out.println("Conexion exitosa a registro");
            } else {
                System.out.println("Conexion fallida");
            }
        } catch (Exception e) {
            System.out.println("error" + e);
        }

        return this;
    }

}
