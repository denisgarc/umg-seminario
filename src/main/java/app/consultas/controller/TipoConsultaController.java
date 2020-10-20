package app.consultas.controller;

import app.consultas.dal.TipoConsultaFacade;
import app.consultas.entities.TipoConsulta;
import com.google.gson.GsonBuilder;
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
public class TipoConsultaController extends HttpServlet {

    @EJB
    private TipoConsultaFacade tipoConsultaService;
    
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
                Short codigo = Short.parseShort(request.getParameter("idTipoConsulta"));
                String descripcion = request.getParameter("descripcion");
                String activo = request.getParameter("activo");

                TipoConsulta modelo = new TipoConsulta();
                modelo.setIdTipoConsulta(codigo);
                modelo.setDescripcion(descripcion);
                modelo.setActivo(activo);

                if(codigo == 0){
                    tipoConsultaService.create(modelo);
                } else {
                    tipoConsultaService.edit(modelo);
                }

                JsonObject result = new GsonBuilder().create().toJsonTree(modelo).getAsJsonObject();
                out.write(result.toString());
                
            } else {
                
                List<TipoConsulta> listado = tipoConsultaService.findAll();
                JsonArray jarray = new GsonBuilder().create().toJsonTree(listado).getAsJsonArray();
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
