package servletit.kirjautuminen;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import kayttaja.Kayttaja;
import kayttaja.KayttajaTyyppi;
import listat.KayttajaLista;

/**
 * Käsittelee kirjautumistoiminnon.
 *
 * @author hkskogbe
 */
public class KirjautuminenServlet extends HttpServlet {

    KayttajaLista lista = new KayttajaLista();

    /**
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();

            String tunnus = request.getParameter("username");
            String salasana = request.getParameter("password");

            if (kirjautuminenOnnistuu(session, tunnus, salasana)) {
                session.setAttribute("kirjautunut", tunnus);
            } else {
                request.getSession().setAttribute("onnistuiko", false);
                request.getRequestDispatcher("Kirjautuminen.jsp").forward(request, response);
            }

            request.getRequestDispatcher("Etusivu").forward(request, response);
        } catch (Exception e) {
            request.getSession().setAttribute("onnistuiko", false);
            request.getRequestDispatcher("Kirjautuminen.jsp").forward(request, response);
        }
    }

    /**
     * Metodi tarkistaa, voiko parametreina annetulla tunnuksella ja salasanalla
     * kirjautua sisään.
     *
     * @param tunnus
     * @param salasana
     * @return
     */
    private boolean kirjautuminenOnnistuu(HttpSession session, String tunnus, String salasana) {
        if (tunnus.equals("h") && salasana.equals("h")) {
            session.setAttribute("yllapitaja", tunnus);
            return true;
        }

        Kayttaja k = lista.getKayttaja(tunnus);
        if (k != null) {
            if (salasana.equals(k.getSalasana())) {

                if (k.getTyyppi() == KayttajaTyyppi.YLLAPITAJA) {
                    session.setAttribute("yllapitaja", tunnus);
                }

                return true;
            }

        }

        return false;
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
