/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.consultas.controller;

import app.consultas.dal.EmpleadoFacade;
import app.consultas.entities.Empleado;
import app.consultas.entities.Especializacion;
import app.consultas.entities.Persona;
import app.consultas.entities.Puesto;
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

public class EmpleadoController extends HttpServlet {

    @EJB
    private EmpleadoFacade empleadoService;
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
                Long idEmpleado = Long.parseLong(request.getParameter("idEmpleado"));
                Long idPersona = Long.parseLong(request.getParameter("idPersona"));
                Short idPuesto = Short.parseShort(request.getParameter("idPuesto"));
                Short idEspecializacion = Short.parseShort(request.getParameter("idEspecializacion"));
                Date fecIngreso = new DateHandler().getDateFromString(request.getParameter("fecIngreso"));
                Date fecBaja = new DateHandler().getDateFromString(request.getParameter("fecBaja"));
                String activo = request.getParameter("activo");

                Empleado modelo = new Empleado();
                modelo.setIdEmpleado(idEmpleado);
                modelo.setIdPersona(new Persona(idPersona));
                modelo.setIdPuesto(new Puesto(idPuesto));
                modelo.setIdEspecializacion(new Especializacion(idEspecializacion));
                modelo.setFecIngreso(fecIngreso);
                modelo.setFecBaja(fecBaja);
                modelo.setActivo(activo);

                if (modelo.getIdEmpleado()== 0) {
                    empleadoService.create(modelo);
                } else {
                    empleadoService.edit(modelo);
                }

                JsonObject result = new JsonHandler().ToJson(modelo);
                out.write(result.toString());

            } else {
                List<Empleado> listado = empleadoService.findAll();
                JsonArray jarray = new JsonHandler().ToJsonArray(listado);
                out.write(jarray.toString());
            }
        } catch (Exception ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
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
