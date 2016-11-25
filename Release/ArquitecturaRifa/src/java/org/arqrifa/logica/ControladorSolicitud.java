package org.arqrifa.logica;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.mail.MessagingException;
import org.arqrifa.datatypes.DTMensaje;
import org.arqrifa.datatypes.DTSolicitud;
import org.arqrifa.excepciones.ArquitecturaRifaExcepcion;
import org.arqrifa.persistencia.FabricaPersistencia;

class ControladorSolicitud implements IControladorSolicitud {

    //<editor-fold defaultstate="collapsed" desc="Singleton">
    private static ControladorSolicitud instancia = null;

    public static ControladorSolicitud getInstancia() {
        if (instancia == null) {
            instancia = new ControladorSolicitud();
        }
        return instancia;
    }

    private ControladorSolicitud() {
    }
    //</editor-fold>

    @Override
    public void agregarSolicitud(DTSolicitud s) {
        String link = "http://localhost:8080/ArquitecturaRifaWeb/Usuarios?accion=verificar&codigo=";

        try {
            if (s == null) {
                throw new Exception("No se puede dar de alta una solicitud nula.");
            }
            if (s.getUsuario().getCi() < 4000000) {
                throw new Exception("Cédula inválida.");
            }

            s.setCodigo((int) (new Random().nextDouble() * 99999999));
            FabricaPersistencia.getPersistenciaSolicitud().agregar(s);

            String msj = s.getUsuario().getNombre() + " tu solicitud ha sido enviada exitosamente, ahora solo"
                    + " falta que verifiques tu dirección de correo electrónico haciendo clic en este enlace:\n "
                    + link + s.getCodigo();

            new Mensajeria(new DTMensaje(s.getUsuario().getEmail(), "Confirmar registro", msj)).enviar();
        } catch (MessagingException me) {
            System.out.println(me.getMessage());
        } catch (Exception e) {
            throw new ArquitecturaRifaExcepcion(e.getMessage());
        }
    }

    @Override
    public void verificarSolicitud(int codigo) {
        try {
            FabricaPersistencia.getPersistenciaSolicitud().verificar(codigo);
        } catch (Exception e) {
            throw new ArquitecturaRifaExcepcion(e.getMessage());
        }
    }

    @Override
    public void confirmarSolicitud(DTSolicitud s) {
        String link = "http://";
        try {
            if (s == null) {
                throw new Exception("No se puede confirmar una solicitud nula.");
            }
            if (!s.isVerificada()) {
                throw new Exception("No se puede confirmar una solicitud sin verificar");
            }

            String msj = "Tu solicitud ha sido confirmada, ya puedes iniciar sesión.\n Para descargar la app móvil: " + link;

            FabricaPersistencia.getPersistenciaSolicitud().confirmar(s);
            new Mensajeria(new DTMensaje(s.getUsuario().getEmail(), "Solicitud aceptada", msj)).enviar();
        } catch (Exception e) {
            throw new ArquitecturaRifaExcepcion(e.getMessage());
        }
    }

    @Override
    public void rechazarSolicitud(DTSolicitud solicitud) {
        try {
            if (solicitud == null) {
                throw new Exception("No se puede rechazar una solicitud nula.");
            }
            FabricaPersistencia.getPersistenciaSolicitud().rechazar(solicitud);
        } catch (Exception e) {
            throw new ArquitecturaRifaExcepcion(e.getMessage());
        }
    }

    @Override
    public DTSolicitud buscarSolicitud(int ci) {
        try {
            return FabricaPersistencia.getPersistenciaSolicitud().buscar(ci);
        } catch (Exception e) {
            throw new ArquitecturaRifaExcepcion(e.getMessage());
        }
    }

    @Override
    public List<DTSolicitud> listarSolicitudes(int generacion) {
        List<DTSolicitud> solicitudes = new ArrayList();
        try {
            
            solicitudes = FabricaPersistencia.getPersistenciaSolicitud().listar(generacion);
            
        } catch (Exception e) {
            throw new ArquitecturaRifaExcepcion(e.getMessage());
        }
        return solicitudes;
    }

}
