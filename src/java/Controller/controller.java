/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.Ruta;
import Model.RutaDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.util.List;

/**
 *
 * @author Fidelitas22
 */
public class controller extends HttpServlet {
    
    RutaDao dao=new RutaDao();
    Ruta r=new Ruta();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet controller</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet controller at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion=request.getParameter("accion");
        switch (accion){
            case "Listar":
                List<Ruta>datos=dao.listar();
                request.setAttribute("datos",datos);
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
            case "Nuevo":
                request.getRequestDispatcher("Agregar.jsp").forward(request, response);
                break;
            case "Guardar":
                String cod=request.getParameter("txtcod");
                String nom=request.getParameter("txtnom");
                String dir=request.getParameter("txtdir");
                String dif=request.getParameter("txtdif");
                String cli=request.getParameter("txtcli");
                String hor=request.getParameter("txthor");
                String dur=request.getParameter("txtdur");
                r.setCodigo(cod);
                r.setNombre(nom);
                r.setDireccion(dir);
                r.setDificultad(dif);
                r.setClima(cli);
                r.setHorario(hor);
                r.setDuracion(dur); 
                dao.agregar(r);
                request.getRequestDispatcher("Controller?accion=Listar").forward(request, response);
                break;
            case "Modificar":
                String ide=request.getParameter("codigo");
                Ruta ru=dao.listarCod(ide);
                request.setAttribute("ruta", ru);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;
            case "Actualizar":
                String cod1=request.getParameter("txtcod");
                String nom1=request.getParameter("txtnom");
                String dir1=request.getParameter("txtdir");
                String dif1=request.getParameter("txtdif");
                String cli1=request.getParameter("txtcli");
                String hor1=request.getParameter("txthor");
                String dur1=request.getParameter("txtdur");
                r.setCodigo(cod1);
                r.setNombre(nom1);
                r.setDireccion(dir1);
                r.setDificultad(dif1);
                r.setClima(cli1);
                r.setHorario(hor1);
                r.setDuracion(dur1);
                dao.actualizar(r);
                request.getRequestDispatcher("Controller?accion=Listar").forward(request, response);
                break;
            case "Eliminar":
                String cod2=request.getParameter("codigo");
                dao.eliminar(cod2);
                request.getRequestDispatcher("Controller?accion=Listar").forward(request, response);
                break;
            default:
                throw new AssertionError();
        
        } 
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
