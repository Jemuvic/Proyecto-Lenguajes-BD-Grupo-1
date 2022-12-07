package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RutaDao {

    PreparedStatement ps;
    ResultSet rs;
    Conexion c = new Conexion();
    Connection con;

    public List listar() {
        List<Ruta> lista = new ArrayList<>();
        String sql = "select * from senderos";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Ruta p = new Ruta();
                p.setCodigo(rs.getString(1));
                p.setNombre(rs.getString(2));
                p.setDireccion(rs.getString(3));
                p.setDificultad(rs.getString(4));
                p.setClima(rs.getString(5));
                p.setHorario(rs.getString(6));
                p.setDuracion(rs.getString(7));
                lista.add(p);
            }
        } catch (Exception e) {
        }
        return lista;
    }

    public int agregar(Ruta p) {
        int r = 0;
        String sql = "insert into senderos(codigo, nombre, direccion, dificultad, clima, horario, duracion) values (?,?,?,?,?,?,?)";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getCodigo());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getDireccion());
            ps.setString(4, p.getDificultad());
            ps.setString(5, p.getClima());
            ps.setString(6, p.getHorario());
            ps.setString(7, p.getDuracion());
            r = ps.executeUpdate();
            if (r == 1) {
                r = 1;
            } else {
                r = 0;
            }
        } catch (Exception e) {
        }
        return r;
    }

    public Ruta listarCod(String cod) {
        String sql = "select * from senderos where codigo=" + cod;
        Ruta r = new Ruta();
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                r.setCodigo(rs.getString(1));
                r.setNombre(rs.getString(2));
                r.setDireccion(rs.getString(3));
                r.setDificultad(rs.getString(4));
                r.setClima(rs.getString(5));
                r.setHorario(rs.getString(6));
                r.setDuracion(rs.getString(7));
            }
        } catch (Exception e) {
        }
        return r;
    }
    
    public int actualizar (Ruta r){
        int p=0;
        String sql="update senderos set nombre=?, direccion=?, dificultad=?, clima=?, horario=?, duracion=? where id=?";
        try{
        con=c.conectar();
        ps=con.prepareStatement(sql);
        ps.setString(1, r.getNombre());
        ps.setString(2,r.getDireccion());
        ps.setString(3,r.getDificultad());
        ps.setString(4,r.getClima());
        ps.setString(5,r.getHorario());
        ps.setString(6,r.getDuracion());
        ps.setString(7,r.getCodigo());
        p=ps.executeUpdate();
        if(p==1){
            p=1;
        }else{
            p=0;
        }
        }catch(Exception e){
        }
        return p;
    }
    public void eliminar(String codigo){
        String sql="delete from senderos where codigo="+codigo;
        try{
            con=c.conectar();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        }catch (Exception e){
        }
    }
}
