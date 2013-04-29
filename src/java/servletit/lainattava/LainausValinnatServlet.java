package servletit.lainattava;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * Ohjaa parametrien tarkentamaan näkymään Lainaukset-toiminnossa.
 *
 * @author hkskogbe
 */
public class LainausValinnatServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("lainaa") != null) {
            request.getSession().setAttribute("lainaaLainauksia", true);
        }

        if (request.getParameter("palauta") != null) {
            request.getSession().setAttribute("palautaLainauksia", true);
        }

        if (request.getParameter("selaa") != null) {
            request.getSession().setAttribute("selaaLainauksia", true);
        }

        if (request.getParameter("lisaa") != null) {
            request.getSession().setAttribute("lisaaLainauksia", true);
        }
        
        if (request.getParameter("poista") != null) {
            request.getSession().setAttribute("poistaLainauksia", true);
        }
        
        request.getRequestDispatcher("Lainaukset.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
