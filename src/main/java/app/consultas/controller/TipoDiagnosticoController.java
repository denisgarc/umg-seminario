/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.consultas.controller;

import app.consultas.dal.TipoDiagnosticoFacade;
import app.consultas.entities.TipoDiagnostico;
import app.consultas.util.JsonHandler;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DOxlaj
 */
public class TipoDiagnosticoController extends HttpServlet {

    @EJB
    private TipoDiagnosticoFacade tipoDiagnosticoService;
    
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
                Short codigo = Short.parseShort(request.getParameter("idTipoDiagnostico"));
                String descripcion = request.getParameter("descripcion");
                String activo = request.getParameter("activo");

                TipoDiagnostico modelo = new TipoDiagnostico();
                modelo.setIdTipoDiagnostico(codigo);
                modelo.setDescripcion(descripcion);
                modelo.setActivo(activo);

                if(codigo == 0){
                    tipoDiagnosticoService.create(modelo);
                } else {
                    tipoDiagnosticoService.edit(modelo);
                }

                JsonObject result = new JsonHandler().ToJson(modelo);
                out.write(result.toString());
                
            } else {
                
                List<TipoDiagnostico> listado = tipoDiagnosticoService.findAll();
                JsonArray jarray = new JsonHandler().ToJsonArray(listado);
                out.write(jarray.toString());
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
