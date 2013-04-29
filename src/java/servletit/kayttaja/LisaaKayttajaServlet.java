package servletit.kayttaja;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kayttaja.Kayttaja;
import kayttaja.KayttajaTyyppi;
import listat.KayttajaLista;

/**
 * Käsittelee käyttäjän lisäämisen tietokantaan.
 *
 * @author hkskogbe
 */
public class LisaaKayttajaServlet extends HttpServlet {

    private KayttajaLista kayttajat = new KayttajaLista();

    /**
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        boolean lisataan = true;

        if (request.getSession().getAttribute("yllapitaja") == null) {
            request.getRequestDispatcher("/LisaaKayttaja.jsp").forward(request, response);
            return;
        }

        String lisaaNakyma = request.getParameter("lisaaNakyma");
        if (lisaaNakyma != null) {
            request.getSession().setAttribute("lisaysNakyma", true);
            request.getRequestDispatcher("/Kayttajat.jsp").forward(request, response);
            return;
        }

        String poistoNakyma = request.getParameter("poistoNakyma");
        if (poistoNakyma != null) {
            request.getSession().setAttribute("poistoNakyma", true);
            request.getRequestDispatcher("/Kayttajat.jsp").forward(request, response);
            return;
        }



        /*----------Varsinaiset toiminnot---------*/


        String tunnus = request.getParameter("nimi");
        String salasana = request.getParameter("salasana");


        if (tunnus != null && salasana != null) {

            if (tunnus.contains("'")) {
                request.getSession().setAttribute("virhelisattaessakayttajaa", true);
                request.getRequestDispatcher("/Kayttajat.jsp").forward(request, response);
                return;
            }

            try {

            String tyyppi = request.getParameter("tyyppi");
            if (tyyppi == null) {
                tyyppi = "kirjautunut";
            }

            if (tyyppi.equals("yllapitaja")) {
                kayttajat.lisaaKayttaja(new Kayttaja(tunnus, salasana, KayttajaTyyppi.YLLAPITAJA));
            } else {
                kayttajat.lisaaKayttaja(new Kayttaja(tunnus, salasana, KayttajaTyyppi.KIRJAUTUNUT));
            }

            request.getSession().setAttribute("lisattiinkayttaja", true);

            } catch (Exception e) {
                request.getSession().setAttribute("virhelisattaessakayttajaa", true);

            }
        } else {
            request.getSession().setAttribute("virhelisattaessakayttajaa", true);
        }


//        for (Kayttaja k : kayttajat.getKayttajat()) {
//            if (k.getNimi().equalsIgnoreCase(tunnus)) {
//                lisataan = false;
//            }
//        }

        request.getRequestDispatcher("/Kayttajat.jsp").forward(request, response);
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
