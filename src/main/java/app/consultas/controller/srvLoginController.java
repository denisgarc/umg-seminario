/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.consultas.controller;

import app.consultas.dal.UsuarioFacade;
import app.consultas.entities.Usuario;
import app.consultas.model.cUsuario;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DOxlaj
 */
//@WebServlet(name="", urlPatterns={""})
public class srvLoginController extends HttpServlet {
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
        //response.setContentType("text/html;charset=UTF-8");
        //RequestDispatcher dispatcher;
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
                    //request.setAttribute("error", true);
                    //request.setAttribute("message", "Usuario o contraseña incorrectas");

                    //dispatcher = request.getRequestDispatcher("user/login.jsp");
                    //dispatcher.forward(request, response);
                    result.addProperty("error", true);
                    result.addProperty("message", "Usuario o contraseña incorrectos.");
                } else {
                    //request.getSession().setAttribute("usuario", usr);
                    result.addProperty("error", false);
                    //response.sendRedirect("index.jsp");
                    request.getSession().setAttribute("session", usr);
                    request.getSession().setAttribute("usuario", usr.getUsuario());
                    request.getSession().setAttribute("nombreUsuario", usr.getIdPersona().getNomberCompleto());
                }
            } else {
                //request.setAttribute("error", true);
                //request.setAttribute("message", "Usuario o contraseña incorrectas");
                //response.sendRedirect("user/login.jsp");
                //dispatcher = request.getRequestDispatcher("./user/login.jsp");
                //dispatcher.forward(request, response);
                
                result.addProperty("error", true);
                result.addProperty("message", "Usuario no encontrado.");
            }
            
            out.write(result.toString());
        } 
        catch(Exception e) {
            result.addProperty("error", true);
            result.addProperty("message", e.getLocalizedMessage());
            out.write(result.toString());
            //request.setAttribute("error", true);
            //request.setAttribute("message", e.getMessage());
            //dispatcher = request.getRequestDispatcher(request.getContextPath() + "user/login.jsp");
            //dispatcher.forward(request, response);
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
