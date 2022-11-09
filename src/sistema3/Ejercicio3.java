
package sistema3;

import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.sql.*;


public final class Ejercicio3 extends javax.swing.JFrame {
static Connection cn;
 static Statement s;
 static ResultSet rs;
 int cantidadColumnas;
 int cantidadFilas;
 
    //Variables
int contadorDeRegistros=0;
int ubicacionDeRegistro=0;
int buscador=0;
boolean enter=false;
boolean codigoencontrado=false;
String codigo[]=new String[25];
String nombre[]=new String[50];
String direccion[]=new String[100];
String dificultad[]=new String[30];
String clima[]=new String[20];
String horario[]=new String[20];
String duracion[]=new String[5];
String codigo1="";


 public Ejercicio3() {

        
        initComponents();
        this.setLocationRelativeTo(null);
        consultaDeTabla();
        
        
    }
 
public void ingresoEnTabla() 
{
    try
    {
      conectar();
        String result="insert into SENDEROS values('"+codigo[contadorDeRegistros]+"','"
                    +nombre[contadorDeRegistros]
                    +"','"+direccion[contadorDeRegistros]+"','"+dificultad[contadorDeRegistros]
                    +"','"+clima[contadorDeRegistros]+"',"+horario[contadorDeRegistros]
                    +"',"+duracion[contadorDeRegistros]+"',"+
                (   contadorDeRegistros+1)+")";
          s.execute(result); 
            
    
       s.close();
    
            cn.close();       
    }
    catch(Exception ex)
    {
       
        JOptionPane.showMessageDialog(null,"Error ingresando datos: "+ex);
    }
    
}
public void borrarTabla(int i)
{
    try
    {
    conectar();
    String res="DELETE FROM SENDEROS WHERE id_sen='"+codigo[i]+"'";
    s.executeUpdate(res);

             s.close();
            cn.close(); 
            }
    catch(Exception e)
    {
         JOptionPane.showMessageDialog(null,"Error al borrar: "+e);
    }
}

public void actualizarTabla(int x)
{
    try
    {
       
        conectar();
            String orden="UPDATE SENDEROS SET NOMBRE='"+nombre[x]+"' ,"
                    + "DIRECCION='"+direccion[x]+"', DIFICULTAD='"+dificultad[x]+"',CLIMA='"+clima[x]+"'"
                    + ", HORARIO='"+horario[x]+"', DURACION='"+duracion[x]+"' "
                    + "WHERE ID_SEN='"+codigo[x]+"'";
           
            s.executeUpdate(orden);
             
            s.close();
            cn.close(); 
            
            
    }
    catch(Exception ex)
    {
        JOptionPane.showMessageDialog(null,"No se puede Actualizar: "+ex);
    }
}
public void conectar()
{
    try{
    String url = "jdbc:oracle:thin:@localhost:1521:XE";
    cn = DriverManager.getConnection(url, "sys","Oracle22");
    s = cn.createStatement(); 
  
    }
    catch(Exception e)
    {
       JOptionPane.showMessageDialog(null,"error: "+e);
    }
    
}

public void consultaDeTabla()
{
    int x=0;
    try {
            
           conectar();
           rs=s.executeQuery("SELECT count(*) FROM SENDEROS");
          
           if(rs.next()){
            
             cantidadFilas = rs.getInt("count(*)" ) ;
           }
           else
           {
               cantidadFilas=0;
           }
                              
           rs = s.executeQuery("SELECT * FROM SENDEROS");
            ResultSetMetaData rsMd = rs.getMetaData();
            cantidadColumnas = rsMd.getColumnCount();
            
            Object[] fila = new Object[cantidadColumnas]; 
            while (rs.next()) {
           
             for (int i = 0; i < cantidadColumnas; i++) {
               fila[i]=rs.getObject(i+1);
               
             }
             
             codigo[x]=fila[0].toString(); 
             nombre[x]=fila[1].toString(); 
             direccion[x]=fila[2].toString(); 
             dificultad[x]=fila[3].toString(); 
             clima[x]=fila[4].toString();
             horario[x]=fila[5].toString(); 
             duracion[x]=fila[6].toString(); 
             x++;
            
            }
            
            contadorDeRegistros=cantidadFilas;
            ubicacionDeRegistro=contadorDeRegistros;
            
            rs.close();
            s.close();
            cn.close();
            
            
            lblsenderos.setForeground(Color.WHITE);
       } catch (SQLException | NumberFormatException | HeadlessException ex) {
        JOptionPane.showMessageDialog(null,"Error al consultar \nError: "+ex);
       }
}

public void eliminar(int x)
{
    if(x!=contadorDeRegistros)
    {
     if(x>=0) 
     {
         borrarTabla(x);
    for(int i=x;i<contadorDeRegistros;i++)
    {
        codigo[i]=codigo[i+1];
        nombre[i]=nombre[i+1];
        direccion[i]=direccion[i+1];
        dificultad[i]=dificultad[i+1];
        clima[i]=clima[i+1];
        horario[i]=horario[i+1];
        duracion[i]=duracion[i+1];
        
    }
    contadorDeRegistros--;
     }
     else
     {
         JOptionPane.showMessageDialog(null,"Sin registros.");
     }
    }
    
}
//metodo de ingreso de datos
public  void ingresoDeDatos(String id_sen,String Nombre, String Direccion
                                    ,String dificultad,String clima, String horario,
                                    String duracion) 
{
  
    
    lblsenderos.setForeground(Color.WHITE);
    if(contadorDeRegistros<24){
    codigo[contadorDeRegistros]=id_sen.replaceAll("-","").replaceAll(" ","");
    nombre[contadorDeRegistros]=Nombre;
    direccion[contadorDeRegistros]=Direccion;
    dificultad[contadorDeRegistros]=Dificultad;
    clima[contadorDeRegistros]=Clima;
    horario[contadorDeRegistros]=Horario;
    duracion[contadorDeRegistros]=Duracion;
    
   
    
   ingresoEnTabla();
   
   
    
    }
    else
    {
        JOptionPane.showMessageDialog(null, "Lo sentimos pero no puede ingresar mas datos.");
    }
    
}


//metodo de modificacion de datos
public  void modificarDatos(String id_sen,String Nombre, String Direccion
                                    ,String Dificultad,String Clima,String Horario,
                                    String Duracion,int i)
{
        
    codigo[i]=id_sen.replaceAll("-", "").replaceAll(" ","");
    nombre[i]=Nombre;
    direccion[i]=Direccion;
    dificultad[i]=Dificultad;
    clima[i]=Clima;
    horario[i]=Horario;
    duracion[i]=Duracion;
    
    actualizarTabla(i);
    
}


//metodo para mostrar datos
public void mostrarDatos(int i)
{          
            txtcod.setText(codigo[i]);
            txtcod.setEnabled(true);
            txtnombre.setText(nombre[i]);
            txtnombre.setEnabled(false);
            txtdireccion.setText(direccion[i]);
            txtdireccion.setEnabled(false);
            txthorario.setText(horario[i]);
            txthorario.setEnabled(false);
            cbdificultad.setToolTipText(dificultad[i]); 
            cbdificultad.setEnabled(false);
            cbclima.setToolTipText(clima[i]); 
            cbclima.setEnabled(false);
            txthorario.setText(horario[i]); 
            txthorario.setEnabled(false);
            txtduracion.setText(duracion[i]); 
            txtduracion.setEnabled(false);
            txtcod.requestFocus();
}

//metodo para buscar el codigo en el arreglo
public int buscarcodigo(String DPI) throws ArrayIndexOutOfBoundsException
{   
    codigoencontrado=false;
    int regresar=10002;
    for(int i=0;i<contadorDeRegistros;i++)
    {
       if((DPI.replaceAll("-", "").replaceAll(" ", "")).equals(codigo[i]))
       {
          regresar=i; 
          codigoencontrado=true;
          break;
       } 
    }
    
    return regresar;
}

//metodo que mustra la info si ya existe
public void habilitarYMostrar(int i)
{
    lblsenderos.setForeground(Color.WHITE);
    txtcod.setEnabled(false);
            txtnombre.setText(nombre[i]);
            txtnombre.setEnabled(true);
            txtdireccion.setText(direccion[i]);
            txtdireccion.setEnabled(true);
            cbdificultad.setToolTipText(dificultad[i]);
            cbdificultad.setEnabled(true);
            cbclima.setToolTipText(clima[i]);
            cbclima.setEnabled(true);
            txthorario.setToolTipText(horario[i]);
            txthorario.setEnabled(true);
            txtduracion.setToolTipText(duracion[i]);
            txtduracion.setEnabled(true);
            btnsiguiente.setEnabled(false);
            btnprimero.setEnabled(false);
            btnultimo.setEnabled(false);
            btnanterior.setEnabled(false);
            btnguardar.setEnabled(true);
            btncancelar.setEnabled(true);
            btneliminar.setEnabled(true);
            codigoencontrado=true;
}

//metodo para habilitar ingreso de datos
public void habilitar()
{
    lblsenderos.setForeground(Color.WHITE);
    txtcod.setEnabled(false);
            txtnombre.setText("");
            txtnombre.setEnabled(true);
            txtdireccion.setText("");
            txtdireccion.setEnabled(true);
            cbdificultad.setToolTipText("");
            cbdificultad.setEnabled(true);
            cbclima.setToolTipText("");
            cbclima.setEnabled(true);
            txthorario.setText("");
            txthorario.setEnabled(true);
            txtduracion.setText("");
            txtduracion.setEnabled(true);
            btnsiguiente.setEnabled(false);
            btnprimero.setEnabled(false);
            btnultimo.setEnabled(false);
            btnanterior.setEnabled(false);
            btnguardar.setEnabled(true);
            btncancelar.setEnabled(true);
            btneliminar.setEnabled(true);
            codigoencontrado=false;
}

//metodo que deshabilita las casillas y muestra la info
public void deshabilitarYMostrar()
{
    txtcod.setEnabled(true);
            txtnombre.setText(nombre[buscador]);
            txtnombre.setEnabled(false);
            txtdireccion.setText(direccion[buscador]);
            txtdireccion.setEnabled(false);
            cbdificultad.setToolTipText(dificultad[buscador]);
            cbdificultad.setEnabled(false);
            cbclima.setToolTipText(clima[buscador]);
            cbclima.setEnabled(false);
            txthorario.setText(horario[buscador]);
            txthorario.setEnabled(false);
            txtduracion.setText(duracion[buscador]);
            txtduracion.setEnabled(false);
            btnsiguiente.setEnabled(true);
            btnprimero.setEnabled(true);
            btnultimo.setEnabled(true);
            btnanterior.setEnabled(true);
            btnguardar.setEnabled(false);
            btncancelar.setEnabled(false);
            
            codigoencontrado=false;
}

//metodo que deshabilita las casillas de edicion
public void deshabilitar()
{
    txtcod.setEnabled(true);
            txtnombre.setEnabled(false);
            txtdireccion.setEnabled(false);
            cbdificultad.setEnabled(false);
            cbclima.setEnabled(false);
            txthorario.setEnabled(false);
            txtduracion.setEnabled(false);
            btnsiguiente.setEnabled(true);
            btnprimero.setEnabled(true);
            btnultimo.setEnabled(true);
            btnanterior.setEnabled(true);
            btnguardar.setEnabled(false);
            btncancelar.setEnabled(false);
            
}

//metodo que limpia las casillas
public void limpiar()
{
            txtcod.setText("");
            txtcod.requestFocus();
            txtnombre.setText("");
            txtdireccion.setText("");
            txthorario.setText("");
            txtduracion.setText("");
    
}

public void borrar()
{
     try
        {
        codigo1=codigo1.substring(0,codigo1.length()-1);
        }
        catch(StringIndexOutOfBoundsException er)
        {
            JOptionPane.showMessageDialog(null,"no hay datos para borrar");
        }
}

//metodo que permite solo numeros y guiones en el codigo
public void errorDeFormato(String nombre)
{
    JOptionPane.showMessageDialog(null, "No se permiten "+nombre+" en esta seccion.");
    
}
  

   

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblEncabezado = new javax.swing.JLabel();
        lblsenderos = new javax.swing.JLabel();
        lblcod = new javax.swing.JLabel();
        lblnombre = new javax.swing.JLabel();
        lbldireccion = new javax.swing.JLabel();
        lbldificultad = new javax.swing.JLabel();
        lblclima = new javax.swing.JLabel();
        lblhorario = new javax.swing.JLabel();
        lblduracion = new javax.swing.JLabel();
        cbdificultad = new javax.swing.JComboBox<>();
        cbclima = new javax.swing.JComboBox<>();
        txtduracion = new javax.swing.JTextField();
        txtcod = new javax.swing.JTextField();
        txtnombre = new javax.swing.JTextField();
        txtdireccion = new javax.swing.JTextField();
        txthorario = new javax.swing.JTextField();
        btnguardar = new javax.swing.JButton();
        btnprimero = new javax.swing.JButton();
        btnanterior = new javax.swing.JButton();
        btnsiguiente = new javax.swing.JButton();
        btnultimo = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        lblNRegistro = new javax.swing.JLabel();
        btncancelar = new javax.swing.JButton();
        lblFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DATOS GENERALES");
        setBackground(new java.awt.Color(0, 51, 102));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblEncabezado.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        lblEncabezado.setForeground(new java.awt.Color(255, 255, 255));
        lblEncabezado.setText("SENDEROS");
        lblEncabezado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lblEncabezado, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, -1, 50));

        lblsenderos.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblsenderos.setForeground(new java.awt.Color(255, 255, 255));
        lblsenderos.setText("Senderos:");
        getContentPane().add(lblsenderos, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 120, -1));

        lblcod.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblcod.setForeground(new java.awt.Color(255, 255, 255));
        lblcod.setText("Cod:");
        getContentPane().add(lblcod, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 50, 25));

        lblnombre.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblnombre.setForeground(new java.awt.Color(255, 255, 255));
        lblnombre.setText("Nombre:");
        getContentPane().add(lblnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 96, 25));

        lbldireccion.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbldireccion.setForeground(new java.awt.Color(255, 255, 255));
        lbldireccion.setText("Direccion:");
        getContentPane().add(lbldireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 90, 25));

        lbldificultad.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbldificultad.setForeground(new java.awt.Color(255, 255, 255));
        lbldificultad.setText("Dificultad:");
        getContentPane().add(lbldificultad, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 109, 25));

        lblclima.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblclima.setForeground(new java.awt.Color(255, 255, 255));
        lblclima.setText("Clima:");
        getContentPane().add(lblclima, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, 109, 25));

        lblhorario.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblhorario.setForeground(new java.awt.Color(255, 255, 255));
        lblhorario.setText("Horario:");
        getContentPane().add(lblhorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 120, 40));

        lblduracion.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblduracion.setForeground(new java.awt.Color(255, 255, 255));
        lblduracion.setText("Duracion:");
        getContentPane().add(lblduracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 300, -1, -1));

        cbdificultad.setBackground(new java.awt.Color(242, 242, 242));
        cbdificultad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Baja", "Media", "Alta" }));
        getContentPane().add(cbdificultad, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 120, -1));

        cbclima.setBackground(new java.awt.Color(242, 242, 242));
        cbclima.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Soleado", "Lluvioso" }));
        getContentPane().add(cbclima, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 250, 120, -1));

        txtduracion.setBackground(new java.awt.Color(242, 242, 242));
        getContentPane().add(txtduracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 330, 120, -1));

        txtcod.setEditable(false);
        txtcod.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtcodFocusGained(evt);
            }
        });
        txtcod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcodKeyTyped(evt);
            }
        });
        getContentPane().add(txtcod, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 70, -1));

        txtnombre.setEnabled(false);
        txtnombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombreKeyTyped(evt);
            }
        });
        getContentPane().add(txtnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 270, 40));

        txtdireccion.setEnabled(false);
        txtdireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdireccionKeyTyped(evt);
            }
        });
        getContentPane().add(txtdireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 270, 60));

        txthorario.setEnabled(false);
        txthorario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txthorarioKeyTyped(evt);
            }
        });
        getContentPane().add(txthorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 120, -1));

        btnguardar.setText("Guardar");
        btnguardar.setEnabled(false);
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });
        getContentPane().add(btnguardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 320, 80, 31));

        btnprimero.setText("Primero");
        btnprimero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnprimeroActionPerformed(evt);
            }
        });
        getContentPane().add(btnprimero, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 240, 80, 31));

        btnanterior.setText("Anterior");
        btnanterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnanteriorActionPerformed(evt);
            }
        });
        getContentPane().add(btnanterior, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 280, -1, 31));

        btnsiguiente.setText("Siguiente");
        btnsiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsiguienteActionPerformed(evt);
            }
        });
        getContentPane().add(btnsiguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 280, -1, 31));

        btnultimo.setText("Ultimo");
        btnultimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnultimoActionPerformed(evt);
            }
        });
        getContentPane().add(btnultimo, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 240, -1, 31));

        btneliminar.setText("Eliminar");
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btneliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 320, -1, 31));
        getContentPane().add(lblNRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 36, -1, -1));

        btncancelar.setText("Cancelar");
        btncancelar.setEnabled(false);
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btncancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 380, -1, 31));

        lblFondo.setBackground(new java.awt.Color(0, 153, 0));
        lblFondo.setForeground(new java.awt.Color(0, 153, 0));
        lblFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistema3/Fondo.jpg"))); // NOI18N
        getContentPane().add(lblFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -10, 620, 470));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtcodKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcodKeyTyped
        char Tecla=(evt.getKeyChar());
        
        
        
        if(Tecla!=KeyEvent.VK_ENTER)
        {
          if(Tecla!='1' && Tecla!='2' && Tecla!='3' && Tecla!='4' && Tecla!='5' && 
                  Tecla!='6' && Tecla!='7' && Tecla!='8' && Tecla!='9' && 
                  Tecla!='0' && Tecla!='-' && Tecla!=KeyEvent.VK_BACK_SPACE 
                  && Tecla!=KeyEvent.VK_SPACE )
          {
              
              errorDeFormato("letras");
              txtcod.setText("");
              txtcod.setText(codigo1);
              
          }
          else
          {
              if(Tecla==KeyEvent.VK_BACK_SPACE)
              {
                  borrar();
                  txtcod.setText(codigo1);
                  txtcod.requestFocus();
                  
              }
              else
              {
              codigo1=codigo1+Tecla;
              txtcod.setText("");
              txtcod.setText(codigo1);
              }
          }
          
           enter=false;
        }
        else
        {
          codigo1="";  
          try
          {
            enter=true;
            buscador=buscarcodigo(txtcod.getText());
            
        if(codigoencontrado==true)
        {
          habilitarYMostrar(buscador); 
            
        }
        else
        {
           habilitar(); 
        }   
        }
          catch(ArrayIndexOutOfBoundsException error)
                {
                    JOptionPane.showMessageDialog(null,"Error en la busqueda");
                }
        }
        
        
    }//GEN-LAST:event_txtcodKeyTyped

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
       
        
        try
       {
        if(codigoencontrado==false)
       {
        ingresoDeDatos(txtcod.getText(),txtnombre.getText(),txtdireccion.getText()
       ,cbdificultad.getSelectedItem().toString(),cbclima.getSelectedItem().toString(),
       txthorario.getText(),txtduracion.getText());
        
       contadorDeRegistros++;
       ubicacionDeRegistro=contadorDeRegistros;
        deshabilitar();
            lblsenderos.setText(""+contadorDeRegistros);
            limpiar();
       }
       else
       {
          
           modificarDatos(txtcod.getText(),txtnombre.getText(),txtdireccion.getText()
       ,cbdificultad.getSelectedItem().toString(),cbclima.getSelectedItem().toString(),
       txthorario.getText(),txtduracion.getText(),buscador);
        
       
       ubicacionDeRegistro=contadorDeRegistros;
        deshabilitar();
            lblsenderos.setText(""+contadorDeRegistros);
            limpiar();
       }
       }
       catch(ArrayIndexOutOfBoundsException error)
                {
                    JOptionPane.showMessageDialog(null, "Error Guardando los datos");
                }
       
       
           // TODO add your handling code here:
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btnanteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnanteriorActionPerformed
     if(ubicacionDeRegistro!=0){
        ubicacionDeRegistro--;
        mostrarDatos(ubicacionDeRegistro);
        lblsenderos.setText(""+(ubicacionDeRegistro+1));
     }
     else
     {
        JOptionPane.showMessageDialog(null, "Usted ya se encuentra en el primer dato"); 
        txtcod.requestFocus();
     }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnanteriorActionPerformed

    private void btnprimeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnprimeroActionPerformed
       if(ubicacionDeRegistro!=0){
        ubicacionDeRegistro=0;
        mostrarDatos(ubicacionDeRegistro);
        lblsenderos.setText(""+(ubicacionDeRegistro+1));
     }
     else
     {
        JOptionPane.showMessageDialog(null, "Usted ya se encuentra en el primer dato"); 
        txtcod.requestFocus();
     }
       // TODO add your handling code here:
    }//GEN-LAST:event_btnprimeroActionPerformed

    private void txtnombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreKeyTyped
        char tecla=(evt.getKeyChar());
        if(tecla==KeyEvent.VK_ENTER)
        {
            txtdireccion.requestFocus();
            
        }
    }//GEN-LAST:event_txtnombreKeyTyped

    private void txtdireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdireccionKeyTyped
char tecla=(evt.getKeyChar());
        if(tecla==KeyEvent.VK_ENTER)
        {
            txtdireccion.requestFocus();
            
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtdireccionKeyTyped

    private void txthorarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txthorarioKeyTyped
char tecla=(evt.getKeyChar());
        if(tecla==KeyEvent.VK_ENTER)
        {
           
            
            if(codigoencontrado==false)
       {
        ingresoDeDatos(txtcod.getText(),txtnombre.getText(),txtdireccion.getText()
       ,cbdificultad.getSelectedItem().toString(),cbclima.getSelectedItem().toString(),
       txthorario.getText(),txtduracion.getText());
        
       contadorDeRegistros++;
       ubicacionDeRegistro=contadorDeRegistros;
        deshabilitar();
            lblsenderos.setText(""+contadorDeRegistros);
            limpiar();
       }
       else
       {
          
           modificarDatos(txtcod.getText(),txtnombre.getText(),txtdireccion.getText()
       ,cbdificultad.getSelectedItem().toString(),cbclima.getSelectedItem().toString(),
       txthorario.getText(),txtduracion.getText(),buscador);
        
       
       ubicacionDeRegistro=contadorDeRegistros;
        deshabilitar();
            lblsenderos.setText(""+contadorDeRegistros);
            limpiar();
       }
            
        } 
        
               // TODO add your handling code here:
    }//GEN-LAST:event_txthorarioKeyTyped

    private void btnsiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsiguienteActionPerformed
       
        if(contadorDeRegistros!=0)
       {
        if(ubicacionDeRegistro<(contadorDeRegistros-1)){
        ubicacionDeRegistro++;
        mostrarDatos(ubicacionDeRegistro);
        lblsenderos.setText(""+(ubicacionDeRegistro+1));
     }
     else
     {
        JOptionPane.showMessageDialog(null, "Usted ya se encuentra en el ultimo dato"); 
        txtcod.requestFocus();
     }
       }
       
        // TODO add your handling code here:
    }//GEN-LAST:event_btnsiguienteActionPerformed

    private void btnultimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnultimoActionPerformed
        if(ubicacionDeRegistro<(contadorDeRegistros-1)){
        ubicacionDeRegistro=contadorDeRegistros-1;
        mostrarDatos(ubicacionDeRegistro);
        lblsenderos.setText(""+(ubicacionDeRegistro+1));
     }
     else
     {
        JOptionPane.showMessageDialog(null, "Usted ya se encuentra en el ultimo dato"); 
        txtcod.requestFocus();
     }
                // TODO add your handling code here:
    }//GEN-LAST:event_btnultimoActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
       
            
           eliminar((ubicacionDeRegistro));
           
           
     if(codigo[0]!="")
     {
        
        ubicacionDeRegistro=0;
        mostrarDatos(ubicacionDeRegistro);
        lblsenderos.setText(""+(ubicacionDeRegistro+1));
     }
     else
     {
         limpiar();
         deshabilitar();
         lblsenderos.setText("");
     }
     
    }//GEN-LAST:event_btneliminarActionPerformed

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
        limpiar();
         deshabilitar();
         lblsenderos.setText(""+(contadorDeRegistros));
    }//GEN-LAST:event_btncancelarActionPerformed

    private void txtcodFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcodFocusGained
      
    }//GEN-LAST:event_txtcodFocusGained

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
      
        
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ejercicio3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ejercicio3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ejercicio3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ejercicio3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ejercicio3().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnanterior;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnprimero;
    private javax.swing.JButton btnsiguiente;
    private javax.swing.JButton btnultimo;
    private javax.swing.JComboBox<String> cbclima;
    private javax.swing.JComboBox<String> cbdificultad;
    private javax.swing.JLabel lblEncabezado;
    private javax.swing.JLabel lblFondo;
    private javax.swing.JLabel lblNRegistro;
    private javax.swing.JLabel lblclima;
    private javax.swing.JLabel lblcod;
    private javax.swing.JLabel lbldificultad;
    private javax.swing.JLabel lbldireccion;
    private javax.swing.JLabel lblduracion;
    private javax.swing.JLabel lblhorario;
    private javax.swing.JLabel lblnombre;
    private javax.swing.JLabel lblsenderos;
    private javax.swing.JTextField txtcod;
    private javax.swing.JTextField txtdireccion;
    private javax.swing.JTextField txtduracion;
    private javax.swing.JTextField txthorario;
    private javax.swing.JTextField txtnombre;
    // End of variables declaration//GEN-END:variables
}
