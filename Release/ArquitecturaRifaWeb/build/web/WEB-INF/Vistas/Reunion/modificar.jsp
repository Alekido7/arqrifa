<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Modificar reuni�n</h1>
        <form action="Reuniones" method="post">
            <table>
                <tr>
                    <td>ID: </td>
                    <td><input type="text" name="id" value="${modelo.id}" readonly /></td>
                </tr>
                <tr>
                    <td>T�tulo: </td>
                    <td><input type="text" name="titulo" value="${modelo.titulo}"></td>
                </tr>
                <tr>
                    <td>Descripci�n: </td>
                    <td><input type="text" name="descripcion" value="${modelo.descripcion}"></td>
                </tr>
                <tr>
                    <td>Fecha: </td>
                    <td><input type="date" name="fecha" value="${modelo.fecha}" /></td>
                </tr>
                <tr>
                    <td>Hora: </td>
                    <td><input type="time" name="hora" value="${modelo.hora}"></td>
                </tr>
                <tr>
                    <td>Lugar: </td>
                    <td><input type="text" name="lugar" value="${modelo.lugar}"></td>
                </tr>
                <tr>
                    <td>Duraci�n: </td>
                    <td><input type="number" name="duracion" value="${modelo.duracion}" ></td>
                </tr>
                <tr>
                    <td>Obligatoria: </td>
                    <td><input type="checkbox" name="obligatoria" ${modelo.obligatoria ? "checked='checked'" : ""} /></td>
                </tr>
            </table>
            <fieldset>
                <legend>Temas</legend>
                <div id="temas_wrapper">

                    <c:forEach var="tema" items="${modelo.temas}"  varStatus="contador">
                        <p id="${contador.index}">
                            <input type="text" name="temas" placeholder="Ingrese un tema aqu�" value="${tema}" required>
                            <input type="button" value="X" onclick="eliminarTema(${contador.index})" />
                        <p>
                    </c:forEach>

                </div>
                <input type="button" value="Nuevo tema" onclick="agregarTema()"/>
            </fieldset>
            <p align="center">
                <input type="submit" name="accion" value="Modificar"/>            
                <a href="Reuniones?accion=modificar&id=${modelo.id}">Cancelar</a>
            </p>
        </form>

        <p>${modelo.mensaje}</p>


        <script>
            function agregarTema() {
                var temasWrapper = document.getElementById('temas_wrapper');
                var temaContainer = document.createElement('p');

                temaContainer.id = temasWrapper.childElementCount;
                temaContainer.innerHTML = "<input type='text' name='temas' placeholder='Ingrese un tema aqu�' autofocus required />"
                        + "<input type='button' value='X' onclick='eliminarTema(" + temasWrapper.childElementCount + ")'/>";

                temasWrapper.appendChild(temaContainer);
            }

            function eliminarTema(indice) {
                var temasWrapper = document.getElementById('temas_wrapper');
                temasWrapper.removeChild(document.getElementById(indice));
            }
        </script>
    </body>
</html>