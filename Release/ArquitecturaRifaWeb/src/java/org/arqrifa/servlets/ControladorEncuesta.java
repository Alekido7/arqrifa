package org.arqrifa.servlets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.arqrifa.datatypes.DTEncuesta;
import org.arqrifa.datatypes.DTPropuesta;
import org.arqrifa.datatypes.DTRespuesta;
import org.arqrifa.datatypes.DTReunion;
import org.arqrifa.viewmodels.VMCrearEncuesta;
import org.arqrifa.viewmodels.VMEncuesta;

public class ControladorEncuesta extends Controlador {

    public void ver_get() {
        VMEncuesta vm = new VMEncuesta();
        try {            
            vm = new VMEncuesta(request.getParameter("reunion_id"), cliente.buscarReunion(Integer.parseInt(request.getParameter("reunion_id"))).getEncuesta());
        }
        catch(NumberFormatException e){
            vm.setMensaje("Ingrese un ID de reunión válido");
        }
        catch (Exception e) {
            vm.setMensaje(e.getMessage());
        }
        mostrarVista("Encuesta/ver.jsp", vm);
    }

    public void agregar_get() {
        VMCrearEncuesta vm = new VMCrearEncuesta();
        try {
            sesion.setAttribute("reunion", cliente.buscarReunion(Integer.parseInt(request.getParameter("id"))));
        } catch (Exception e) {
            vm.setMensaje(e.getMessage());
        }
        mostrarVista("Encuesta/agregar.jsp", vm);
    }

    public void agregar_propuesta_post() {
        VMCrearEncuesta vm = (VMCrearEncuesta) cargarModelo(new VMCrearEncuesta());
        try {
            if (request.getParameter("pregunta").isEmpty()) {
                throw new Exception("Ingrese la pregunta de la propuesta");
            }       
            if (request.getParameter("respuestas").isEmpty()) {
                throw new Exception("Ingrese las respuestas de la pregunta.");
            }

            List<DTRespuesta> respuestas = new ArrayList();
            for (String respuesta : Arrays.asList(request.getParameter("respuestas").split("\n"))) {
                respuestas.add(new DTRespuesta(0, respuesta));
            }
            
            DTReunion reunion = (DTReunion) sesion.getAttribute("reunion");
            if (reunion.getEncuesta() == null) {
                reunion.setEncuesta(new DTEncuesta());
            }
            DTEncuesta encuesta = ((DTReunion) sesion.getAttribute("reunion")).getEncuesta();
            reunion.getEncuesta().getPropuestas().add(new DTPropuesta(0, request.getParameter("pregunta"), respuestas));            
        } catch (Exception e) {
            vm.setMensaje(e.getMessage());
        }
        mostrarVista("Encuesta/agregar.jsp", vm);
    }

    public void agregar_encuesta_post() {
        VMCrearEncuesta vm = (VMCrearEncuesta) cargarModelo(new VMCrearEncuesta());
        try {
            DTReunion reunion = (DTReunion) sesion.getAttribute("reunion");

            reunion.getEncuesta().setTitulo(vm.getTitulo());
            reunion.getEncuesta().setDuracion(Integer.parseInt(vm.getDuracion()));

            cliente.crearEncuesta(reunion);
            vm = new VMCrearEncuesta();
            vm.setMensaje("Encuesta creada exitosamente.");
            sesion.setAttribute("Reunion", new DTReunion());
        } catch (Exception e) {
            vm.setMensaje(e.getMessage());
        }
        mostrarVista("Encuesta/agregar.jsp", vm);
    }

    public void iniciar_votacion_post() {
        VMEncuesta vm = new VMEncuesta();
        try {
            vm.setReunionId(request.getParameter("reunion_id"));
            DTReunion reunion = cliente.buscarReunion(Integer.parseInt(vm.getReunionId()));
            cliente.iniciarVotacion(reunion);
            vm.setEncuesta(reunion.getEncuesta());
            vm.setMensaje("Votación iniciada con éxito.");
        } catch (Exception e) {
            vm.setMensaje(e.getMessage());
        }
        mostrarVista("Encuesta/ver.jsp", vm);
    }
}
