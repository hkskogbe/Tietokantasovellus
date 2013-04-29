package servletit.kirja;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kirja.Kirja;
import kirja.Lainattava;
import listat.KirjaLista;
import listat.LainattavaLista;

/**
 * Käsittelee Kirjan asettamisen poistetuksi sekä poistettu-merkinnän
 * poistamisen.
 *
 * @author hkskogbe
 */
public class PoistoServlet extends HttpServlet {

    private KirjaLista lista = new KirjaLista();
    private LainattavaLista lainalista = new LainattavaLista();

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

        String isbn = request.getParameter("isbn");
        String poisto = request.getParameter("poistetaan");

        if (isbn != null) {
            try {
                if (poisto.equals("y")) {
                    List<Lainattava> lainassaOlevat;
                    try {
                        lainassaOlevat = lainalista.getLainauksetISBNlla(isbn);
                    } catch (Exception e) {
                        lainassaOlevat = new ArrayList<Lainattava>();
                    }

                    if (lainassaOlevat.isEmpty()) {
                        lista.poistoMerkinta(isbn, true);

                    } else {
                        request.getSession().setAttribute("virhepoistettaessa", true);
                        request.getRequestDispatcher("PoistaKirja.jsp").forward(request, response);
                        return;
                    }
                } else {
                    lista.poistoMerkinta(isbn, false);

                }
                request.getSession().setAttribute("poistomerkintaonnistui", true);

            } catch (Exception e) {
                request.getSession().setAttribute("virhepoistettaessa", true);
            }
        } else {
            request.getSession().setAttribute("virhepoistettaessa", true);
        }
        request.getRequestDispatcher("PoistaKirja.jsp").forward(request, response);
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
