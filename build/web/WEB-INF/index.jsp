<%-- 
    Document   : index
    Created on : Nov 27, 2022, 1:43:16 PM
    Author     : Fidelitas22
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <center>
        <div>
            <h3>Senderos</h3>
            <form action="Controller" method="POST">
                <input type="submit" name="accion" value="Listar">
                <input type="submit" name="accion" value="Nuevo">
            </form>
        </div>
        <div>
            <table border="1">
                <thead>
                    <tr>
                        <th>Codigo</th>
                        <th>Nombre</th>
                        <th>Direccion</th>
                        <th>Dificultad</th>
                        <th>Clima</th>
                        <th>Horario</th>
                        <th>Duracion</th>
                        <th>Acciones</th> 
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dato" items="${datos}">
                        <tr>
                            <td>${dato.getCodigo()}</td>
                            <td>${dato.getNombre()}</td>
                            <td>${dato.getDireccion()}</td>
                            <td>${dato.getDificultad()}</td>
                            <td>${dato.getClima()}</td>
                            <td>${dato.Horario()}</td>
                            <td>${dato.Duracion()}</td>
                            <td>
                                <form action="Controller" method="POST">
                                    <input type="hidden" name="codigo" value="${dato.getCodigo()}">
                                    <input type="submit" name="action" value="Modificar">
                                    <input type="submit" name="action" value="Eliminar">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </center>
</body>
</html>
