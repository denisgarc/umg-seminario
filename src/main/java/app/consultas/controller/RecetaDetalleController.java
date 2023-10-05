/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.consultas.controller;

import app.consultas.dal.RecetaDetalleFacade;
import app.consultas.entities.Consulta;
import app.consultas.entities.Medicamento;
import app.consultas.entities.Receta;
import app.consultas.entities.RecetaDetalle;
import app.consultas.entities.RecetaDetallePK;
import app.consultas.util.DateHandler;
import app.consultas.util.JsonHandler;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DOxlaj
 */
public class RecetaDetalleController extends HttpServlet {

    @EJB
    private RecetaDetalleFacade detalleService;
    
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
                Long codigo = Long.parseLong(request.getParameter("idReceta"));
                Integer idMedicamento = Integer.parseInt(request.getParameter("idMedicamento[idMedicamento]"));
                Short cantidad = Short.parseShort(request.getParameter("cantidad"));
                String indicaciones = request.getParameter("indicaciones");
                String durecion = request.getParameter("duracion");

                RecetaDetalle modelo = new RecetaDetalle();
                modelo.setRecetaDetallePK(new RecetaDetallePK(codigo, idMedicamento));
                //modelo.setReceta(new Receta(codigo));
                //modelo.setMedicamento(new Medicamento(idMedicamento));
                modelo.setCantidad(cantidad);
                modelo.setIndicaciones(indicaciones);
                modelo.setDuracion(durecion);

                detalleService.create(modelo);

                JsonObject result = new JsonHandler().ToJson(modelo);
                out.write(result.toString());
                
            } else {
                Long idReceta = 0L;
                if(request.getParameter("idReceta") != null)
                    idReceta = Long.parseLong(request.getParameter("idReceta"));
                
                if(idReceta == 0){
                    List<RecetaDetalle> listado = detalleService.findByIdReceta(idReceta);
                    JsonArray jarray = new JsonHandler().ToJsonArray(listado);
                    out.write(jarray.toString());
                } else {
                    List<RecetaDetalle> listado = detalleService.findAll();
                    JsonArray jarray = new JsonHandler().ToJsonArray(listado);
                    out.write(jarray.toString());
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(RecetaController.class.getName()).log(Level.SEVERE, null, ex);
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
