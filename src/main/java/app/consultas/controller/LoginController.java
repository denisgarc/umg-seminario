/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.consultas.controller;

import app.consultas.dal.UsuarioFacade;
import app.consultas.entities.Usuario;
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
//@WebServlet(name="", urlPatterns={""})
public class LoginController extends HttpServlet {
    @EJB
    private UsuarioFacade usrService;
    //private cUsuario usrService;
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
        PrintWriter out = response.getWriter();
        JsonObject result = new JsonObject();
        
        try {
            String usuario = request.getParameter("user");
            String password = request.getParameter("password");
            
            // Realizamos consulta a la DB
            Usuario usr = usrService.GetUsuarioByCode(usuario);
            
            // Validamos si se encontro el usuario
            if(usr != null){
                if(!usr.getContrasena().equalsIgnoreCase(password)){
                    result.addProperty("error", true);
                    result.addProperty("message", "Usuario o contraseña incorrectos.");
                } else {
                    result.addProperty("error", false);
                    request.getSession().setAttribute("session", usr);
                    request.getSession().setAttribute("usuario", usr.getUsuario());
                    request.getSession().setAttribute("nombreUsuario", usr.getIdPersona().getNomberCompleto());
                }
            } else {
                result.addProperty("error", true);
                result.addProperty("message", "Usuario no encontrado.");
            }
            
            out.write(result.toString());
        } 
        catch(Exception e) {
            result.addProperty("error", true);
            result.addProperty("message", e.getLocalizedMessage());
            out.write(result.toString());
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
