

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
            <h3>Agregar Rutas</h3>
        </div>
        <div>
            <form action="Controlador" method="POST">
                Codigo:
                <input type="text" name="txtcod"> <br>
                Nombre:
                <input type="text" name="txtnom"> <br>
                Direccion:
                <input type="text" name="txtdir"> <br>
                Dificultad:
                <input type="text" name="txtdif"> <br>
                Clima:
                <input type="text" name="txtcli"> <br>
                Horario:
                <input type="text" name="txthor"> <br>
                Duracion:
                <input type="text" name="txtdur"> <br>
                <input type="submit" name="accion" value="Guardar">
            </form>
        </div>
    </center>
</body>
</html>
