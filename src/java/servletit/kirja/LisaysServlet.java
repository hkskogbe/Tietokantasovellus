package servletit.kirja;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
            request.getRequestDispatcher("/LisaaKirja").forward(request, response);
            return;
        }

        if (aiheet == null) {
            aiheet = " ";
        }

        if (isbn.contains("'") ||kirjailija.contains("'") || aiheet.contains("'") || nimi.contains("'") || nimi.isEmpty() || isbn.isEmpty() || jvuosi.isEmpty() || kirjailija.isEmpty()) {
            request.getRequestDispatcher("/LisaaKirja").forward(request, response);
            return;
        }

        if (!jvuosi.matches("[0-9]+")) {
            request.getRequestDispatcher("/LisaaKirja").forward(request, response);
            return;
        } else {
            julkaisuvuosi = Integer.parseInt(jvuosi);
        }

        lista.lisaaKirja(new Kirja(isbn, nimi, julkaisuvuosi));
        Kirja k = null;
        try {
            k = lista.getKirjaISBNlla(isbn);
        } catch (Exception e) {
        }

        for (String kirjz : this.kirjailijatStringista(kirjailija)) {
            if (k != null) {
                kirjailijat.lisaaKirjailija(new Kirjailija(k, kirjz));
            }
        }
        for (String az : this.aiheetStringista(aiheet)) {
            if (k != null) {
                aihelista.lisaaAihe(new Aihe(k, az));
            }
        }

        request.getSession().setAttribute("lisattiinkirja", true);
        request.getRequestDispatcher("/LisaaKirja").forward(request, response);

    }

    /**
     * Erottaa stringistä kirjailijat.
     *
     * @param kirjailijoidenNimet
     * @param kirja
     */
    private List<String> kirjailijatStringista(String kirjailijoidenNimet) {

        ArrayList<String> palautus = new ArrayList<String>();

        if (kirjailijoidenNimet.contains(";")) {

            String[] nimet;
            nimet = kirjailijoidenNimet.split(";");

            for (String nimi : nimet) {
                palautus.add(nimi.trim());
            }
        } else {
            palautus.add(kirjailijoidenNimet);

        }
        return palautus;
    }

    /**
     * Erottaa stringistä aiheet.
     *
     * @param aiheet
     * @param kirja
     */
    private List<String> aiheetStringista(String aiheet) {

        ArrayList<String> palautus = new ArrayList<String>();

        if (aiheet.contains(",")) {

            String[] nimet;
            nimet = aiheet.split(",");

            for (String nimi : nimet) {
                palautus.add(nimi.trim());
            }
        } else {
            palautus.add(aiheet);

        }
        return palautus;
    }

//        if (lisataan) {
//
//            if (onJoTietokannassa(isbn)) {
//                // Jos teos on jo aiemmin lisätty, poistetaan aiempi versio ja
//                // lisätään sen jälkeen uudestaan päivitetyillä tiedoilla
//
//                /*
//                 *  ITSE ASIASSA
//                 *  INSTEAD: PÄIVITÄ JOKA IKINEN PARAMETRI
//                 *  PROBLEM WILL BE FIXED
//                 * 
//                 * käytä: http://www.objectdb.com/java/jpa/persistence/update
//                 * 
//                 */
//                Kirja poistettava = lista.getKirjaISBNlla(isbn);
//
//                lista.poistaKirja(poistettava);
//
//            }
//            // Luodaan tietokantaan lisättävä kirja
//            Kirja kirja = new Kirja(isbn, nimi, julkaisuvuosi);
//
//            lista.lisaaKirja(kirja);
//
//            // Lopuksi lisätään kirjailijat ja aiheet kirjalle
//            lisaaKirjailijat(kirjailija, kirja);
//            lisaaAiheet(aiheet, kirja);
//
//            request.getSession().setAttribute("lisattiinkirja", true);
//        }
//
//        if (!lisataan) {
//            request.getSession().setAttribute("virhelisattaessa", true);
//        }
//
//        request.getRequestDispatcher("/LisaaKirja").forward(request, response);
//    }
//
//
//    /**
//     * Tarkistaa, onko teos jo tietokannassa.
//     *
//     * @param isbn
//     * @return
//     */
//    private boolean onJoTietokannassa(String isbn) {
//        // Tarkistetaan, onko kirja jo lisattu
//        for (Kirja k : lista.getKirjat()) {
//            if (k.getISBN().equals(isbn)) {
//                return true;
//            }
//        }
//        return false;
//    }
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
