<!DOCTYPE html>
<html>
    <head>
        <title>JSP Page</title>
    </head>
    <body>
        <p>Esta p�gina podr�a ser un cuadro de di�logo en el calendario</p>
        <h3>Eliminar reuni�n</h3>
        <p>�Est� segur@ que desea eliminar la reuni�n con ID: ${modelo.id}?</p>
        <form action="Reuniones" method="post">
            <input type="text" name="id" value="${modelo.id}" hidden />
            <input type="submit" name="accion" value="Eliminar" />
        </form>
        ${modelo.mensaje}
    </body>
</html>
