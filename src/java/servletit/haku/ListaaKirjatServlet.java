package servletit.haku;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kirja.Kirja;
import kirja.Kirjailija;
import listat.KirjaLista;

/**
 * Käsittelee requesteja kirjojen hakemiseksi tietokannasta.
 *
 * @author hkskogbe
 */
public class ListaaKirjatServlet extends HttpServlet {

    private KirjaLista lista = new KirjaLista();

    /**
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nimi = request.getParameter("kirja");
        String kirjailija = request.getParameter("kirjailija");
        String aihe = request.getParameter("aihe");
        String avuosi = request.getParameter("avuosi");
        String lvuosi = request.getParameter("lvuosi");


        if (nimi == null) {
            nimi = "";
        }
        if (nimi.contains("'")) {
            nimi = "";
            request.getSession().setAttribute("virheSyotteessa", true);
        }
        if (kirjailija == null) {
            kirjailija = "";
        }
        if (kirjailija.contains("'")) {
            kirjailija = "";
            request.getSession().setAttribute("virheSyotteessa", true);
        }
        if (aihe == null) {
            aihe = "";
        }
        if (aihe.contains("'")) {
            aihe = "";
            request.getSession().setAttribute("virheSyotteessa", true);
        }

        if (avuosi == null) {
            avuosi = "1000";
        }
        if (lvuosi == null) {
            lvuosi = "2500";
        }
        int a;
        int l;
        boolean vuosihaku = false;
        try {
            a = Integer.parseInt(avuosi);
            vuosihaku = true;
        } catch (Exception e) {
            a = 1000;
        }
        try {
            l = Integer.parseInt(lvuosi);
            vuosihaku = true;
        } catch (Exception e) {
            l = 2500;
        }


        String tarkennettu = request.getParameter("tarkennettu");
        if (tarkennettu != null) {
            request.getSession().setAttribute("tarkennettuHaku", true);
        }

        List<Kirja> hakutulokset = null;

        if (nimi.isEmpty() && kirjailija.isEmpty() && aihe.isEmpty()) {
            if (!vuosihaku) {
                RequestDispatcher rd = request.getRequestDispatcher("hakusivu.jsp");
                rd.forward(request, response);
                return;
            }
        }

        if (!nimi.isEmpty()) {
            Kirja tulos;
            try {
                tulos = lista.getKirjaISBNlla(nimi);

                hakutulokset = new ArrayList<Kirja>();
                hakutulokset.add(tulos);

                request.getSession().setAttribute("hakutulokset", true);
                RequestDispatcher rd = request.getRequestDispatcher("hakusivu.jsp");
                rd.forward(request, response);
                return;
            } catch (Exception e) {
            }
        }
        hakutulokset = lista.getKirja(nimi, kirjailija, aihe, avuosi, lvuosi);

        request.getSession().setAttribute("lista", hakutulokset);
        request.setAttribute("lista", hakutulokset);

        request.getSession().setAttribute("hakutulokset", true);

        RequestDispatcher rd = request.getRequestDispatcher("hakusivu.jsp");

        rd.forward(request, response);
    }
//        try {
//            if (request.getRequestedSessionId().equals("tarkennettuHakuehto")) {
//                request.getSession().setAttribute("tarkennettuHaku", true);
//            }
//        } catch (Exception e) {
//        }
//
//
//        String nimi = request.getParameter("kirja");
//        String kirjailija = request.getParameter("kirjailija");
//        String aihe = request.getParameter("aihe");
//        String avuosi = request.getParameter("avuosi");
//        String lvuosi = request.getParameter("lvuosi");
//
//        if (avuosi == null) {
//            avuosi = "1000";
//        }
//        if (lvuosi == null) {
//            lvuosi = "2500";
//        }
//        if (nimi == null) {
//            nimi = "";
//        }
//        if (kirjailija == null) {
//            kirjailija = "";
//        }
//        if (aihe == null) {
//            aihe = "";
//        }
//
//        try {
//            if (Integer.parseInt(avuosi) > Integer.parseInt(lvuosi)) {
//                request.setAttribute("lvuosiEnnenAvuotta", true);
//            }
//        } catch (Exception e) {
//            if (avuosi.length() != 4 || lvuosi.length() != 4) {
//                request.setAttribute("virheellinenVuosi", true);
//            }
//        }
//
//        List<Kirja> hakutulokset;
//
////        try {
//        // Jos hakuehdot tyhjät
//        if (nimi.isEmpty() && kirjailija.isEmpty() && aihe.isEmpty()) {
//            try {
//                /*
//                 * 
//                 * WTF DOES THIS do
//                 * 
//                 */
//                int a = Integer.parseInt(avuosi);
//                int l = Integer.parseInt(lvuosi);
//                hakutulokset = lista.getKirjatValilta(a, l);
//            } catch (Exception e) {
//                hakutulokset = lista.getKirjat();
//            }
//
//            // Jos hakuehtona vain nimi
//        } else if (!nimi.isEmpty() && kirjailija.isEmpty() && aihe.isEmpty()) {
//
//            Kirja etsiISBN;
//
//            //Kokeillaan, löytyykö hakutuloksia ISBN:llä
//            try {
//                etsiISBN = lista.getKirjaISBNlla(nimi);
//
//                if (etsiISBN != null) {
//                    hakutulokset = new ArrayList<Kirja>();
//                    hakutulokset.add(etsiISBN);
//                } else {
//                    hakutulokset = lista.getKirjaNimella(nimi);
//                }
//            } catch (Exception e) {
//                // Kirjaa ei löytynyt ISBN:llä
//                hakutulokset = lista.getKirjaNimella(nimi);
//            }
//            // Jos hakuehtona yksi kirjailija 
//        } else if (nimi.isEmpty() && !kirjailija.isEmpty() && aihe.isEmpty() && !kirjailija.contains(";")) {
//
//            hakutulokset = lista.getKirjaKirjailijanNimella(kirjailija);
//
//            // Jos hakuehtona yksi aihe
//        } else if (nimi.isEmpty() && kirjailija.isEmpty() && !aihe.isEmpty() && !aihe.contains(",")) {
//
//            hakutulokset = lista.getKirjaAiheella(aihe);
//
//            // Jos listoiksi muodostettavat eivät tyhjiä
//        } else if (!kirjailija.isEmpty() || !aihe.isEmpty()) {
//
//            List<String> kirjailijat = new ArrayList<String>();
//
//            if (!kirjailijat.isEmpty()) {
//                kirjailijat = listaaStringista(kirjailija, ";");
//            }
//            List<String> aiheet = new ArrayList<String>();
//
//            if (!aihe.isEmpty()) {
//                aiheet = listaaStringista(aihe, ",");
//            }
//            hakutulokset = lista.getKirja(nimi, kirjailijat, aiheet, avuosi, lvuosi);
//
//        } else {
//            hakutulokset = lista.getKirjat();
//        }
//        request.getSession().setAttribute("hakutulokset", true);
//
////        } catch (Exception e) {
////            request.getSession().setAttribute("virheSyotteessa", true);
////            hakutulokset = new ArrayList<Kirja>();
////
////        }
//        request.getSession()
//                .setAttribute("lista", hakutulokset);
//        request.setAttribute(
//                "lista", hakutulokset);
//
//        RequestDispatcher rd = request.getRequestDispatcher("hakusivu.jsp");
//
//        rd.forward(request, response);
//    }
//
//    /**
//     * Jakaa string-muotoisen syötteen osiksi pilkun kohdalta.
//     *
//     * @param string
//     * @return
//     */
//    private List<String> listaaStringista(String string, String jakaja) {
//        List<String> k;
//        try {
//            k = new ArrayList<String>();
//
//            String[] s = string.split(jakaja);
//            for (String lisattava : s) {
//                k.add(lisattava.trim());
//            }
//        } catch (Exception e) {
//            k = new ArrayList<String>();
//        }
//
//        return k;
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
