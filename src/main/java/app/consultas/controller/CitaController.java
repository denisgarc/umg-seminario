/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.consultas.controller;

import app.consultas.dal.CitaFacade;
import app.consultas.entities.Cita;
import app.consultas.entities.Estado;
import app.consultas.entities.Hospital;
import app.consultas.entities.Paciente;
import app.consultas.entities.Persona;
import app.consultas.entities.TipoDocumento;
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
public class CitaController extends HttpServlet {

    @EJB
    private CitaFacade citaService;
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
                Long idCita = Long.parseLong(request.getParameter("idCita"));
                Date fechaCita = new DateHandler().getDateFromString(request.getParameter("fechaCita"));
                Date horaCita = new DateHandler().getDateFromString(request.getParameter("horaCita"),"HH:MM");
                Long idPaciente = Long.parseLong(request.getParameter("idPaciente"));
                Short idHospital = Short.parseShort(request.getParameter("idHospital"));
                Short idClinica = 1;
                Short idSala = 1;
                Short idEstado = Short.parseShort(request.getParameter("idEstado"));

                Cita modelo = new Cita();
                modelo.setIdCita(idCita);
                modelo.setFechaCita(fechaCita);
                modelo.setHoraCita(horaCita);
                modelo.setIdPaciente(new Paciente(idPaciente));
                modelo.setIdHospital(new Hospital(idHospital));
                modelo.setIdClinica(idClinica);
                modelo.setIdSala(idSala);
                modelo.setIdEstado(new Estado(idEstado));

                if (modelo.getIdCita() == 0) {
                    //modelo.setIdCita(citaService.generateNewId());
                    long newIdCita = citaService.createWithId(modelo);
                    modelo.setIdCita(newIdCita);
                } else {
                    citaService.edit(modelo);
                }

                JsonObject result = new JsonHandler().ToJson(modelo);
                out.write(result.toString());

            } else {
                Long codCita = 0L;
                if(request.getParameter("idCita") != null)
                    codCita = Long.parseLong(request.getParameter("idCita"));
                            
                if(codCita == 0){
                    List<Cita> listado = citaService.findAll();
                    JsonArray jarray = new JsonHandler().ToJsonArray(listado);
                    out.write(jarray.toString());
                } else {
                    Cita item = citaService.find(codCita);
                    JsonObject result = new JsonHandler().ToJson(item);
                    out.write(result.toString());
                }
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
