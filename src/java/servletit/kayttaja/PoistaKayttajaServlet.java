/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servletit.kayttaja;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kayttaja.Kayttaja;
import listat.KayttajaLista;

/**
 * Poistaa annetun käyttäjän tietokannasta.
 *
 * @author hkskogbe
 */
public class PoistaKayttajaServlet extends HttpServlet {

    KayttajaLista lista = new KayttajaLista();

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


        String tunnus = request.getParameter("nimi");

        try {
            Kayttaja poistettava = lista.getKayttaja(tunnus);
            if (poistettava == null) {
                request.getSession().setAttribute("kayttajanPoistoEiOnnistunut", true);
                request.getRequestDispatcher("Kayttajat.jsp").forward(request, response);
                return;
            }
        } catch (Exception e) {
            request.getSession().setAttribute("kayttajanPoistoEiOnnistunut", true);
            request.getRequestDispatcher("Kayttajat.jsp").forward(request, response);
            return;
        }


        if (tunnus != null) {
            try {

                lista.poistaKayttaja(tunnus);
                request.getSession().setAttribute("kayttajanPoistoOnnistui", true);

            } catch (Exception e) {
                request.getSession().setAttribute("kayttajanPoistoEiOnnistunut", true);

            }
        } else {
            request.getSession().setAttribute("kayttajanPoistoEiOnnistunut", true);
        }
        request.getRequestDispatcher("Kayttajat.jsp").forward(request, response);
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
