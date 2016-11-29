package org.arqrifa.servlets;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import org.arqrifa.datatypes.DTReunion;
import org.arqrifa.datatypes.DTUsuario;
import org.arqrifa.validador.Validador;
import org.arqrifa.viewmodels.VMReunion;
import org.arqrifa.viewmodels.VMReunionMantenimiento;

public class ControladorReuniones extends Controlador {

    public void ver_get() {
        VMReunion vm = new VMReunion();
        try {

            DTReunion r = cliente.buscarReunion(Validador.validarId(request.getParameter("id")));

            if (r == null) {
                throw new Exception("Reunión no encontrada");
            }

            if (r.getGeneracion() != ((DTUsuario) sesion.getAttribute("usuario")).getGeneracion()) {
                throw new Exception("La reunión no pertenece a su generación");
            }

            SimpleDateFormat sdfFecha = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm");

            vm.setId(String.valueOf(r.getId()));
            vm.setGeneracion(String.valueOf(r.getGeneracion()));
            vm.setTitulo(r.getTitulo());
            vm.setDescripcion(r.getDescripcion());
            vm.setFecha(sdfFecha.format(r.getFecha()));
            vm.setHora(sdfHora.format(r.getFecha()));
            vm.setObligatoria(r.isObligatoria());
            vm.setObservaciones(r.getObservaciones());
            vm.setEstado(r.getEstado());
            vm.setLugar(r.getLugar());
            vm.setTemas(r.getTemas());
            vm.setResoluciones(r.getResoluciones());

            // Si la reunión está en condiciones se habilita su inicio
            if ((r.getEstado().equals("Pendiente") && vm.getFecha().equals(sdfFecha.format(new Date())))|| r.getEstado().equals("Iniciada")) {
                vm.setHabilitarBotonEfectuar(r.getFecha().after(new Date()));
            }
        } catch (Exception e) {
            vm.setMensaje(e.getMessage());
        }
        mostrarVista("Vistas/Reunion/ver.jsp", vm);
    }

    public void agendar_get() {
        mostrarVista("Vistas/Encargado/agendar_reunion.jsp");
    }

    public void agendar_post() {
        VMReunionMantenimiento vm = (VMReunionMantenimiento) cargarModelo(new VMReunionMantenimiento());

        try {
            if (vm.getTitulo().isEmpty() || vm.getDescripcion().isEmpty() || vm.getLugar().isEmpty()) {
                throw new Exception("Debe completar todos los campos.");
            }
            if (vm.getTemas().isEmpty()) {
                throw new Exception("Ingrese los temas de se debatiran en la reunión.");
            }

            int duracion = Integer.parseInt(vm.getDuracion());

            Date fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(vm.getFecha() + " " + vm.getHora());

            DTReunion r = new DTReunion();

            r.setGeneracion(((DTUsuario) sesion.getAttribute("usuario")).getGeneracion());
            r.setTitulo(vm.getTitulo());
            r.setDescripcion(vm.getDescripcion());
            r.setFecha(fecha);
            r.setDuracion(duracion);
            r.setObligatoria(vm.isObligatoria());
            r.setLugar(vm.getLugar());

            String temas = request.getParameter("temas");

            StringTokenizer st = new StringTokenizer(temas, "\n", false);
            while (st.hasMoreTokens()) {
                r.getTemas().add(st.nextToken());
            }

            cliente.agendarReunion(r);
            vm = new VMReunionMantenimiento();
            vm.setMensaje("Reuníon agendada exitosamente.");

        } catch (NumberFormatException e) {
            vm.setMensaje("Ingrese una duración válida.");
        } catch (ParseException e) {
            vm.setMensaje("Ingrese una fecha y hora válida.");
        } catch (Exception e) {
            vm.setMensaje(e.getMessage());
        }

        mostrarVista("Vistas/Encargado/agendar_reunion.jsp", vm);
    }

    public void efectuar_post() {
        mostrarVista("Vistas/Encargado/efectuar_reunion.jsp", (VMReunion) cargarModelo(new VMReunion()));
    }

    public void iniciar_post() {
        VMReunionMantenimiento vm = (VMReunionMantenimiento) cargarModelo(new VMReunionMantenimiento());
        try {

            DTReunion r = cliente.buscarReunion(Validador.validarId(request.getParameter("id")));
            cliente.iniciarReunion(r);
            vm.setEstado("Iniciada");

            vm.setMensaje("Reunión iniciada exitosamente");
        } catch (Exception e) {
            vm.setMensaje(e.getMessage());
        }
        mostrarVista("Vistas/Encargado/efectuar_reunion.jsp", vm);
    }

    public void finalizar_post() {
        VMReunionMantenimiento vm = (VMReunionMantenimiento) cargarModelo(new VMReunionMantenimiento());

        try {
            if (vm.getResoluciones().isEmpty()) {
                throw new Exception("Debe ingresar las resoluciones de la reunión.");
            }

            DTReunion r = new DTReunion();
            r.setId(Integer.parseInt(vm.getId()));
            r.setObservaciones(vm.getObservaciones());

            StringTokenizer st = new StringTokenizer(vm.getResoluciones(), "\n", false);
            while (st.hasMoreTokens()) {
                r.getResoluciones().add(st.nextToken());
            }
            cliente.finalizarReunion(r);
            vm.setEstado("Finalizada");
            vm.setMensaje("reunión finalizada exitosamente");
        } catch (Exception e) {
            vm.setMensaje(e.getMessage());
        }
        mostrarVista("Vistas/Encargado/efectuar_reunion.jsp", vm);
    }
}
