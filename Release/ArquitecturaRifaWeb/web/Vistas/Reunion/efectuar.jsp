<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>${modelo.titulo}</h1>
        <h2>${modelo.descripcion}</h2>
        <form action="Reuniones"  method="post">
            <input type="text" name="id" value="${modelo.id}" readonly><br>
            <input type="text" name="titulo" value="${modelo.titulo}"readonly><br>
            <textarea name="descripcion"readonly>${modelo.descripcion}</textarea><br>
            <input type="date" name="fecha" value="${modelo.fecha}"readonly><br>
            <input type="time" name="hora" value="${modelo.hora}"readonly><br>
            <input type="checkbox" name="obligatoria" value="${modelo.obligatoria}"disabled=""><br>
            <input type="text" name="generacion" value="${modelo.generacion}"readonly><br>
            <input type="text" name="estado" value="${modelo.estado}"readonly><br>
            <input type="text" name="lugar" value="${modelo.lugar}"readonly><br>
            <input type="text" name="observaciones" value="${modelo.observaciones}"><br>
            
            <input type="text" id="resolucion"/>
            <input type="button" value="Agregar" onclick="agregarResolucion()">
            <textarea name="resoluciones" id="resoluciones" readonly>${modelo.resoluciones}</textarea>
            <c:if test="${modelo.estado == 'Pendiente'}">
                <input type="submit" name="accion" value="Iniciar">
            </c:if>
            <c:if test="${modelo.estado == 'Iniciada'}">
                <input type="submit" name="accion" value="Finalizar">
            </c:if>
        </form>
        <p>${modelo.mensaje}</p>
        <script>
            function agregarResolucion() {
                var input = document.getElementById("resolucion");
                var resolucion = input.value;

                if (resolucion !== "") {
                    var textarea = document.getElementById("resoluciones");
                    textarea.innerHTML += resolucion + "\n";
                    input.value = "";
                }
            }

        </script>
    </body>
</html>

