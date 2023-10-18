/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package app.consultas.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Francisco Lopez
 */
public class ResultadosServlet extends HttpServlet {

     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Establece la conexión a la base de datos MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3289/DOXLAJ", "DOXLAJ", "DOXLAJ");

            // Prepara la consulta SQL
            String sql = "SELECT COUNT(T1.SEXO) AS TOTAL, " +
                         "SUM(CASE WHEN T1.SEXO = 'M' THEN 1 ELSE 0 END) AS HOMBRES, " +
                         "SUM(CASE WHEN T1.SEXO = 'F' THEN 1 ELSE 0 END) AS MUJERES " +
                         "FROM DOXLAJ.PACIENTE T0 INNER JOIN DOXLAJ.PERSONA T1 ON T0.ID_PERSONA = T1.ID_PERSONA";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            // Obtén los resultados
            if (rs.next()) {
                int total = rs.getInt("TOTAL");
                int hombres = rs.getInt("HOMBRES");
                int mujeres = rs.getInt("MUJERES");

                // Almacena los resultados como atributos de solicitud
                request.setAttribute("total", total);
                request.setAttribute("hombres", hombres);
                request.setAttribute("mujeres", mujeres);
            }

            // Redirige a la vista JSP
            request.getRequestDispatcher("\\reporte\\Resultados.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
