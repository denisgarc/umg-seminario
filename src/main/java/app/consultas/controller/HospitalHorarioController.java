/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.consultas.controller;

import app.consultas.dal.HospitalHorarioFacade;
import app.consultas.entities.HospitalHorario;
import app.consultas.entities.HospitalHorarioPK;
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

public class HospitalHorarioController extends HttpServlet {

    @EJB
    private HospitalHorarioFacade horarioService;
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
                Short codigo = Short.parseShort(request.getParameter("idHospital"));
                Short idHorario = Short.parseShort(request.getParameter("idHorario"));
                Date horaInicio = new DateHandler().getDateFromString(request.getParameter("horaInicio"), "HH:mm");
                Date horaFin = new DateHandler().getDateFromString(request.getParameter("horaFin"), "HH:mm");
                String descripcion = request.getParameter("descripcion");
                String activo = request.getParameter("activo");

                HospitalHorario modelo = new HospitalHorario();
                modelo.setHospitalHorarioPK(new HospitalHorarioPK(codigo, idHorario));
                modelo.setDescripcion(descripcion);
                modelo.setHoraInicio(horaInicio);
                modelo.setHoraFin(horaFin);
                modelo.setActivo(activo);

                if(idHorario == 0){
                    horarioService.create(modelo);
                } else {
                    horarioService.edit(modelo);
                }

                JsonObject result = new JsonHandler().ToJson(modelo);
                out.write(result.toString());
                
            } else {
                Short codigo = 0;
                if(request.getParameter("idHospital") != null)
                    codigo = Short.parseShort(request.getParameter("idHospital"));
                
                List<HospitalHorario> listado = horarioService.findByIdHospital(codigo);
                JsonArray jarray = new JsonHandler().ToJsonArray(listado);
                out.write(jarray.toString());
            }
        } catch (ParseException ex) {
            Logger.getLogger(HospitalHorarioController.class.getName()).log(Level.SEVERE, null, ex);
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
