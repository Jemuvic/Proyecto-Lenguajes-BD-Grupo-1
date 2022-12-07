

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
            <h3>Actualizar Rutas</h3>
        </div>
        <div>
            <form action="Controlador" method="POST">
                Codigo:<br>
                <input type="text" name="txtcod" value="${ruta.getCodigo}"> <br>
                Nombre:<br>
                <input type="text" name="txtnom" value="${ruta.getNombre}"> <br>
                Direccion:<br>
                <input type="text" name="txtdir" value="${ruta.getDireccion}"> <br>
                Dificultad:<br>
                <input type="text" name="txtdif" value="${ruta.getDificultad}"> <br>
                Clima:<br>
                <input type="text" name="txtcli" value="${ruta.getClima}"> <br>
                Horario:<br>
                <input type="text" name="txthor" value="${ruta.getHorario}"> <br>
                Duracion:<br>
                <input type="text" name="txtdur" value="${ruta.getDuracion}"> <br>
                <input type="submit" name="accion" value="Actualizar">
            </form>
        </div>
    </center>
    </body>
</html>
