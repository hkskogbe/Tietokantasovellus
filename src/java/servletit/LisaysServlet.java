package servletit;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kirja.Kirja;
import kirja.Kirjailija;
import listat.KirjaLista;
import listat.KirjailijaLista;

/**
 *
 * @author hkskogbe
 */
public class LisaysServlet extends HttpServlet {

    private KirjaLista lista = new KirjaLista();
    private KirjailijaLista kirjailijat = new KirjailijaLista();

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


        boolean lisataan = true;

        String nimi = request.getParameter("nimi");
        String isbn = request.getParameter("isbn");
        String jvuosi = request.getParameter("vuosi");
        String kirjailija = request.getParameter("kirjailija");
        int julkaisuvuosi = 0;

        System.out.println(kirjailija);

        if (nimi == null || isbn == null || jvuosi == null || kirjailija == null) {
            lisataan = false;
        }

        if (lisataan) {
            if (!jvuosi.matches("[0-9]+")) {
                lisataan = false;
            } else {
                julkaisuvuosi = Integer.parseInt(jvuosi);
            }


        }

        // Tarkistetaan, onko kirja jo lisattu
        for (Kirja k : lista.getKirjat()) {
            if (k.getISBN().equals(isbn)) {
                lisataan = false;
            }
        }

        if (lisataan) {
            Kirja kirja = new Kirja(isbn, nimi, julkaisuvuosi);
            
            lista.lisaaKirja(kirja);
            
            if (!kirjailija.equals("")) {
                lisaaKirjailijat(request, kirjailija, kirja);
            } else {
                lisataan = false;
            }
        }
        request.getRequestDispatcher("/LisaaKirja").forward(request, response);
    }

    private void lisaaKirjailijat(HttpServletRequest request, String kirjailijoidenNimet, Kirja kirja) {

        String[] nimet;

        nimet = kirjailijoidenNimet.split(",");

        for (String nimi : nimet) {
            kirjailijat.lisaaKirjailija(new Kirjailija(kirja, nimi));
        }
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
