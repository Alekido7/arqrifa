<!-- Esta jsp deber�a mostrarse como cuadro de dialogo en detalle de reuni�n-->
<!DOCTYPE html>
<html>
    <head>
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Eliminar encuesta</h1>
        <form action="Encuesta" method="post">
            <p>�Est� segur@ que desea eliminar la encuesta  dela reuni�n ID: ${modelo.reunionId} ?</p>
            <input type="text" name="reunion_id" value="${modelo.reunionId}" hidden>
            <input type="submit" name="accion" value="Eliminar" />
        </form>
            ${modelo.mensaje}
    </body>
</html>
