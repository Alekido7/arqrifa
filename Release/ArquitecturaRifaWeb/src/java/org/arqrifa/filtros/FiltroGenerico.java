package org.arqrifa.filtros;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.arqrifa.datatypes.DTUsuario;

@WebFilter(filterName = "filtroGenerico", urlPatterns = "/WEB-INF/Vistas/login.jsp")
public class FiltroGenerico implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //No necesarios ya que está todo en webinf
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest pedido = (HttpServletRequest) request;
        HttpServletResponse respuesta = (HttpServletResponse) response;
        HttpSession sesion = pedido.getSession();

        DTUsuario usuario = (DTUsuario) sesion.getAttribute("usuario");

        if (usuario != null) {
            System.out.println(pedido.getContextPath());
            //respuesta.sendRedirect(pedido.getContextPath() + "/Vistas/" + usuario.getRol() + "/index.jsp");
        } else {
            //respuesta.sendRedirect(pedido.getContextPath() + "/login.jsp");
        }
    }

    @Override
    public void destroy() {

    }

}
