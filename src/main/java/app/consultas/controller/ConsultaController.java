/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.consultas.controller;

import app.consultas.dal.CitaFacade;
import app.consultas.dal.ConsultaFacade;
import app.consultas.dal.EstadoFacade;
import app.consultas.entities.Cita;
import app.consultas.entities.Consulta;
import app.consultas.entities.Empleado;
import app.consultas.entities.Estado;
import app.consultas.entities.Hospital;
import app.consultas.entities.Paciente;
import app.consultas.entities.TipoConsulta;
import app.consultas.util.DateHandler;
import app.consultas.util.JsonHandler;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
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
public class ConsultaController extends HttpServlet {

    @EJB
    private ConsultaFacade consultaService;
    @EJB
    private CitaFacade citaService;
    @EJB
    private EstadoFacade estadoService;
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
            if (request.getMethod() == "POST") {
                Long idConsulta = Long.parseLong(request.getParameter("idConsulta"));
                Long idCita = Long.parseLong(request.getParameter("idCita"));
                Date fechaConsulta = new DateHandler().getDateFromString(request.getParameter("fechaConsulta"));
                Long idEmpleado = Long.parseLong(request.getParameter("idEmpleado"));
                Short idTipoConsulta = Short.parseShort(request.getParameter("idTipoConsulta"));
                String descripcion = request.getParameter("descripcion");
                String observaciones = request.getParameter("observaciones");
                String activo = request.getParameter("activo");

                Consulta modelo = new Consulta();
                modelo.setIdConsulta(idConsulta);
                modelo.setIdCita(new Cita(idCita));
                modelo.setFechaConsulta(fechaConsulta);
                modelo.setIdEmpleado(new Empleado(idEmpleado));
                modelo.setIdTipoConsulta(new TipoConsulta(idTipoConsulta));
                modelo.setDescripcion(descripcion);
                modelo.setObservaciones(observaciones);
                modelo.setActivo(activo);

                if (modelo.getIdConsulta()== 0) {
                    //modelo.setIdConsulta(consultaService.generateNewId());
                    long newIdConsulta = consultaService.createWithId(modelo);
                    modelo.setIdConsulta(newIdConsulta);
                } else {
                    consultaService.edit(modelo);
                }
                
                Cita citaActualizar = citaService.find(idCita);
                Estado estadoActualizar = estadoService.find(Short.parseShort("3"));
                citaActualizar.setIdEstado(estadoActualizar);
                citaService.edit(citaActualizar);

                JsonObject result = new JsonHandler().ToJson(modelo);
                out.write(result.toString());

            } else {
                Long codCita = 0L;
                if(request.getParameter("idCita") != null)
                    codCita = Long.parseLong(request.getParameter("idCita"));
                            
                List<Consulta> listado = consultaService.findAll();
                JsonArray jarray = new JsonHandler().ToJsonArray(listado);
                out.write(jarray.toString());
            }
        } catch (Exception ex) {
            Logger.getLogger(ConsultaController.class.getName()).log(Level.SEVERE, null, ex);
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
