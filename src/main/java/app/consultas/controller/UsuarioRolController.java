/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.consultas.controller;

import app.consultas.dal.UsuarioFacade;
import app.consultas.dal.UsuarioRolFacade;
import app.consultas.entities.Usuario;
import app.consultas.entities.UsuarioRol;
import app.consultas.util.JsonHandler;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DOxlaj
 */
public class UsuarioRolController extends HttpServlet {

    @EJB
    private UsuarioRolFacade usuarioRolService;
    
    @EJB
    private UsuarioFacade usuarioService;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if(request.getMethod() == "POST"){
                String action = request.getParameter("action");
                Long idUsuario = Long.parseLong(request.getParameter("idUsuario"));
                Short idRol = Short.parseShort(request.getParameter("idUsuario"));
                
                UsuarioRol modelo = new UsuarioRol();
                modelo.setIdUsuario(idUsuario);
                modelo.setIdRol(idRol);
                
                switch(action){
                    case "agregar":
                        usuarioRolService.create(modelo);
                        break;
                    case "eliminar":
                        usuarioRolService.remove(modelo);
                        break;
                }
                
                JsonObject result = new JsonHandler().ToJson(modelo);
                out.write(result.toString());
                
            } else {
                String usr = request.getParameter("usuario");
                Usuario usuario = usuarioService.GetUsuarioByCode(usr);
                
                JsonObject result = new JsonObject();
                result.addProperty("idUsuario", usuario.getIdUsuario());
                result.addProperty("usuario", usuario.getUsuario());
                result.addProperty("nombre", usuario.getIdPersona().getNomberCompleto());
                result.add("roles", new JsonHandler().ToJsonArray(usuario.getRolList()));
                out.write(result.toString());
            } 
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
