/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema3;

import java.sql.Connection;
import java.sql.DriverManager;


public class Coneccion {

    private Connection conexion;

   public Coneccion() {
        Coneccion obconeccion = new Coneccion();
        obconeccion.Conectar();
    }

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

    public Connection Conectar() {
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

        return conexion;
    }
    
    public void close()
    {
        try {
        
             this.conexion.close();
        } catch (Exception e)
        {
       // this.conexion.abort(executor);
        }
        
       
    }

}
