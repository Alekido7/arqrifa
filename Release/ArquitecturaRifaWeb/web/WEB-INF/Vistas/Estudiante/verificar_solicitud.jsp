<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Arquitectura Rifa | Verificar</title>
    </head>
    <body>
        <c:choose>
            <c:when test="${modelo.verificada}">
                <h1>�xito!</h1>
                <h2>Has verificado correctamente tu direcci�n de correo electr�nico.</h2>
                <h3>Debes esperar que se apruebe tu solicitud para iniciar sesi�n</h3>
            </c:when>
            <c:otherwise>
                <h1>No se pudo verificar la solicitud</h1>
                <h2>${modelo.mensaje}</h2>
            </c:otherwise>
        </c:choose>
    </body>
</html>
