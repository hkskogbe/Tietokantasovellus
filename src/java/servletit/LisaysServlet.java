package servletit;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kirja.Aihe;
import kirja.Kirja;
import kirja.Kirjailija;
import listat.AiheLista;
import listat.KirjaLista;
import listat.KirjailijaLista;

/**
 * Käsittelee kirjan lisäämisen tietokantaan.
 *
 * @author hkskogbe
 */
public class LisaysServlet extends HttpServlet {

    private KirjaLista lista = new KirjaLista();
    private KirjailijaLista kirjailijat = new KirjailijaLista();
    private AiheLista aihelista = new AiheLista();

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

        if (request.getSession().getAttribute("kirjautunut") == null) {
            request.getRequestDispatcher("/LisaaKirja").forward(request, response);
            return;
        }

        String nimi = request.getParameter("nimi");
        String isbn = request.getParameter("isbn");
        String jvuosi = request.getParameter("vuosi");
        String kirjailija = request.getParameter("kirjailija");
        String aiheet = request.getParameter("aiheet");
        int julkaisuvuosi = 0;


        if (nimi == null || isbn == null || jvuosi == null || kirjailija == null) {
            lisataan = false;
        }

        if (aiheet == null) {
            aiheet = " ";
        }

        if (lisataan) {
            if (!jvuosi.matches("[0-9]+")) {
                lisataan = false;
            } else {
                julkaisuvuosi = Integer.parseInt(jvuosi);
            }


        }

        if (lisataan) {

            if (onJoTietokannassa(isbn)) {
                // Jos teos on jo aiemmin lisätty, poistetaan aiempi versio ja
                // lisätään sen jälkeen uudestaan päivitetyillä tiedoilla

                /*
                 *  ITSE ASIASSA
                 *  INSTEAD: PÄIVITÄ JOKA IKINEN PARAMETRI
                 *  PROBLEM WILL BE FIXED
                 * 
                 * käytä: http://www.objectdb.com/java/jpa/persistence/update
                 * 
                 */
                Kirja poistettava = lista.getKirjaISBNlla(isbn);

                lista.poistaKirja(poistettava);

            }
            // Luodaan tietokantaan lisättävä kirja
            Kirja kirja = new Kirja(isbn, nimi, julkaisuvuosi);

            lista.lisaaKirja(kirja);

            // Lopuksi lisätään kirjailijat ja aiheet kirjalle
            lisaaKirjailijat(kirjailija, kirja);
            lisaaAiheet(aiheet, kirja);

            request.getSession().setAttribute("lisattiinkirja", true);
        }

        if (!lisataan) {
            request.getSession().setAttribute("virhelisattaessa", true);
        }

        request.getRequestDispatcher("/LisaaKirja").forward(request, response);
    }

    /**
     * Lisää teokseen liittyvät kirjailijat tietokantaan.
     *
     * @param kirjailijoidenNimet
     * @param kirja
     */
    private void lisaaKirjailijat(String kirjailijoidenNimet, Kirja kirja) {

        if (kirjailijoidenNimet.contains(";")) {


            String[] nimet;

            nimet = kirjailijoidenNimet.split(";");

            for (String nimi : nimet) {
                nimi = nimi.trim();
                Kirjailija lisattava = new Kirjailija(kirja, nimi);
                kirjailijat.lisaaKirjailija(lisattava);
            }
        } else {
            Kirjailija lisattava = new Kirjailija(kirja, kirjailijoidenNimet);
            kirjailijat.lisaaKirjailija(lisattava);

        }

    }

    /**
     * Lisää teokseen liittyvät aiheet tietokantaan.
     *
     * @param aiheetString
     * @param kirja
     */
    private void lisaaAiheet(String aiheetString, Kirja kirja) {
        if (aiheetString.contains(",")) {
            String[] a;

            a = aiheetString.split(",");

            for (String string : a) {
                string = string.trim();
                aihelista.lisaaAihe(new Aihe(kirja, string));
            }
        } else {
            aihelista.lisaaAihe(new Aihe(kirja, aiheetString));
        }

    }

    /**
     * Tarkistaa, onko teos jo tietokannassa.
     *
     * @param isbn
     * @return
     */
    private boolean onJoTietokannassa(String isbn) {
        boolean on = false;
        // Tarkistetaan, onko kirja jo lisattu
        for (Kirja k : lista.getKirjat()) {
            if (k.getISBN().equals(isbn)) {
                on = true;
            }
        }
        return on;
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
