package app.consultas.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import app.consultas.dal.ConsultaFacade;
import app.consultas.dal.EmpleadoFacade;
import app.consultas.entities.Consulta;
import app.consultas.entities.ConsultaImpresion;
import app.consultas.entities.Empleado;
import app.consultas.entities.FichaMedica;
import app.consultas.entities.Paciente;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DOxlaj
 */
public class ConsultaImpresionController extends HttpServlet {

    @EJB
    private ConsultaFacade consultaService;
    
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
        try{
            String typeVisor = request.getParameter("type") == null ? "" : request.getParameter("type");
            response.setContentType("application/pdf;charset=UTF-8");
            if(typeVisor.equalsIgnoreCase("mobile")){
                response.addHeader("Content-Disposition", "attached; filename=" + "fichaMedica.pdf");
            } else {
                response.addHeader("Content-Disposition", "inline; filename=" + "fichaMedica.pdf");
            }
            
            ServletOutputStream out = response.getOutputStream();

            // Para obtener la Consulta
            Long idConsulta = 0L;
            if(request.getParameter("codigo") != null){
                idConsulta = Long.parseLong(request.getParameter("codigo"));
                Consulta consulta = consultaService.find(idConsulta);
                String path = getServletContext().getRealPath("/");
                
                if(consulta.getIdEmpleado().getIdPersona() == null) {
                    Empleado empleado = empleadoService.find(consulta.getIdEmpleado().getIdEmpleado());
                }

                ByteArrayOutputStream baos = ConsultaImpresion.getResume(consulta);
                baos.writeTo(out);
            } else {
                PrintWriter writer = response.getWriter();
                writer.write("Código inválido");
            }
        } catch(Exception ex){
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
