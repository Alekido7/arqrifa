<%-- 
    Document   : agregar_valores_textarea
    Created on : 25-nov-2016, 5:48:56
    Author     : Ale
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="name"%>
<%@attribute name="valores"%>

<input type="text" id="valor"/>
<input type="button" value="Agregar" onclick="agregar()"><br>
<textarea name="${name}" id="valores" readonly>${valores}</textarea>

<script>
    function agregar() {
        var txtValor = document.getElementById("valor");
        var valor = txtValor.value;

        if (valor !== "") {
            document.getElementById("valores").innerHTML += valor + "\n";
            txtValor.value = "";
        }
    }
</script>