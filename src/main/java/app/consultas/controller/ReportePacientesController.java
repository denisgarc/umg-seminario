/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package app.consultas.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Francisco Lopez
 */
@WebServlet(name = "ReportePacientesController", urlPatterns = {"/ReportePacientes"})
public class ReportePacientesController extends HttpServlet {

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
        
// Crear una instancia de EntityManagerFactory y EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
        EntityManager em = emf.createEntityManager();

        // Realizar la consulta SQL
        String sql = "SELECT"+
    "COUNT(T1.SEXO) AS TOTAL,"+
"sum(CASE WHEN T1.SEXO =  'M' THEN 1 ELSE 0 END) AS HOMBRES,"+
"sum(CASE WHEN T1.SEXO =  'F' THEN 1 ELSE 0 END) AS MUJERES"+
    "FROM DOXLAJ.PACIENTE T0 "+
"INNER JOIN DOXLAJ.PERSONA T1 ON T0.ID_PERSONA = T1.ID_PERSONA";


        Query query = em.createNativeQuery(sql);
        Object[] result = (Object[]) query.getSingleResult();
        
        int total = ((Number) result[0]).intValue();
        int hombres = ((Number) result[1]).intValue();
        int mujeres = ((Number) result[2]).intValue();

        // Almacenar los resultados como atributos en el objeto request
        request.setAttribute("total", total);
        request.setAttribute("hombres", hombres);
        request.setAttribute("mujeres", mujeres);

        // Redirigir a la vista JSP
        request.getRequestDispatcher("/ReportePacientes.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
