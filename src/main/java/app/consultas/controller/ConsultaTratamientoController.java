package app.consultas.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import app.consultas.dal.ConsultaTratamientoFacade;
import app.consultas.entities.Consulta;
import app.consultas.entities.ConsultaDiagnostico;
import app.consultas.entities.ConsultaTratamiento;
import app.consultas.entities.TipoDiagnostico;
import app.consultas.entities.TipoTratamiento;
import app.consultas.util.JsonHandler;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
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
public class ConsultaTratamientoController extends HttpServlet {

    @EJB
    private ConsultaTratamientoFacade tratamientoService;
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
                Long codigo = Long.parseLong(request.getParameter("idTratamiento"));
                Long idConsulta = Long.parseLong(request.getParameter("idConsulta"));
                Short idTipoTratamiento = Short.parseShort(request.getParameter("idTipoTratamiento[idTipoTratamiento]"));
                String descripcion = request.getParameter("descripcion");
                String activo = request.getParameter("activo");

                ConsultaTratamiento modelo = new ConsultaTratamiento();
                modelo.setIdTratamiento(codigo);
                modelo.setIdConsulta(new Consulta(idConsulta));
                modelo.setIdTipoTratamiento(new TipoTratamiento(idTipoTratamiento));
                modelo.setDescripcion(descripcion);
                modelo.setActivo(activo);

                if(codigo == 0){
                    tratamientoService.create(modelo);
                } else {
                    tratamientoService.edit(modelo);
                }

                JsonObject result = new JsonHandler().ToJson(modelo);
                out.write(result.toString());
            } else {
                Long idConsulta = 0L;
                if(request.getParameter("idConsulta") != null)
                    idConsulta = Long.parseLong(request.getParameter("idConsulta"));
                
                List<ConsultaTratamiento> listado = tratamientoService.findByIdConsulta(idConsulta);
                JsonArray jarray = new JsonHandler().ToJsonArray(listado);
                out.write(jarray.toString());
            }
        } catch (Exception ex) {
            Logger.getLogger(CitaController.class.getName()).log(Level.SEVERE, null, ex);
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
