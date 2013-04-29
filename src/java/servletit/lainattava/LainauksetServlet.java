package servletit.lainattava;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import kirja.Kirja;
import kirja.Lainattava;
import listat.KirjaLista;
import listat.LainattavaLista;

/**
 *
 * @author hkskogbe
 */
public class LainauksetServlet extends HttpServlet {

    LainattavaLista lista = new LainattavaLista();
    KirjaLista kirjalista = new KirjaLista();

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

        HttpSession session = request.getSession();



        /* Lainaus */
        if (request.getParameter("lainaa") != null) {

            String isbn = request.getParameter("isbn");
            String lainaaja = request.getParameter("lainaaja");
            String mihinasti = request.getParameter("mihinasti");
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            Date dateWithoutTime;
            try {

                dateWithoutTime = sdf.parse(sdf.format(new Date()));
            } catch (ParseException ex) {
                session.setAttribute("syotevirhe", true);
                request.getRequestDispatcher("Lainaukset.jsp").forward(request, response);
                return;
            }
            String pvm = dateWithoutTime.toString();

            if (isbn == null) {
                isbn = "";
            }
            if (lainaaja == null) {
                lainaaja = "";
            }
            if (mihinasti == null) {
                mihinasti = "";
            }


            if (isbn.isEmpty() || lainaaja.isEmpty() || mihinasti.isEmpty() || isbn.contains("'") || lainaaja.contains("'") || mihinasti.contains("'")) {
                session.setAttribute("syotevirhe", true);
                request.getRequestDispatcher("Lainaukset.jsp").forward(request, response);
                return;
            }

            List<Lainattava> lainattavat;

            lainattavat = lista.getLainaamatonLainausISBNllä(isbn);

            if (lainattavat == null) {
                session.setAttribute("eivarastossa", true);
                request.getRequestDispatcher("Lainaukset.jsp").forward(request, response);
                return;
            }
            if (lainattavat.isEmpty()) {
                session.setAttribute("eivarastossa", true);
                request.getRequestDispatcher("Lainaukset.jsp").forward(request, response);
                return;
            }

            Lainattava lainattava = lainattavat.get(0);

            lista.lainaaLainaus(lainattava, lainaaja, pvm, mihinasti);

            session.setAttribute("lainatapahtumaonnistui", true);
        } //
        //
        // Palautus
        else if (request.getParameter("palauta") != null) {

            String isbn = request.getParameter("isbn");
            String lainaaja = request.getParameter("lainaaja");
//            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
//            Date dateWithoutTime;
//            try {
//
//                dateWithoutTime = sdf.parse(sdf.format(new Date()));
//            } catch (ParseException ex) {
//                session.setAttribute("syotevirhe", true);
//                request.getRequestDispatcher("Lainaukset.jsp").forward(request, response);
//                return;
//            }
//            String pvm = dateWithoutTime.toString();

            if (isbn == null) {
                isbn = "";
            }
            if (lainaaja == null) {
                lainaaja = "";
            }

            if (isbn.isEmpty() || lainaaja.isEmpty() || isbn.contains("'") || lainaaja.contains("'")) {
                session.setAttribute("syotevirhe", true);
                request.getRequestDispatcher("Lainaukset.jsp").forward(request, response);
                return;
            }

            List<Lainattava> palautettavat;
            try {
                palautettavat = lista.getLainauksetISBNlla(isbn);
            } catch (Exception e) {
                session.setAttribute("syotevirhe", true);
                request.getRequestDispatcher("Lainaukset.jsp").forward(request, response);
                return;
            }
            Lainattava palautettava = this.kayttajanLainausListasta(lainaaja, palautettavat);

            if (palautettava == null) {
                session.setAttribute("syotevirhe", true);
            } else {

                lista.palautaLainaus(palautettava);
                session.setAttribute("lainatapahtumaonnistui", true);

            }
        } else if (request.getParameter("poista") != null) {

            String isbn = request.getParameter("isbn");
//            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
//            Date dateWithoutTime;
//            try {
//
//                dateWithoutTime = sdf.parse(sdf.format(new Date()));
//            } catch (ParseException ex) {
//                session.setAttribute("syotevirhe", true);
//                request.getRequestDispatcher("Lainaukset.jsp").forward(request, response);
//                return;
//            }
//            String pvm = dateWithoutTime.toString();

            if (isbn == null) {
                isbn = "";
            }

            if (isbn.isEmpty() || isbn.contains("'")) {
                session.setAttribute("syotevirhe", true);
                request.getRequestDispatcher("Lainaukset.jsp").forward(request, response);
                return;
            }

            List<Lainattava> poistettavat;
            poistettavat = lista.getLainauksetISBNlla(isbn);
            Lainattava poistettava = this.haeListastaPalautettuLainattava(poistettavat);

            if (poistettava == null) {
                session.setAttribute("eivarastossa", true);
            } else {

                lista.poistaLainattava(poistettava);

                session.setAttribute("lainatapahtumaonnistui", true);

            }


        } else if (request.getParameter("lisaa") != null) {

            String isbn = request.getParameter("isbn");

            if (isbn == null) {
                isbn = "";
            }

            if (isbn.isEmpty() || isbn.contains("'")) {
                session.setAttribute("syotevirhe", true);
                request.getRequestDispatcher("Lainaukset.jsp").forward(request, response);
                return;
            }

            Kirja lisattava;
            lisattava = kirjalista.getKirjaISBNlla(isbn);

            if (lisattava == null) {
                session.setAttribute("syotevirhe", true);
                request.getRequestDispatcher("Lainaukset.jsp").forward(request, response);
                return;
            }

            lista.lisaaLainaus(new Lainattava(lisattava));
            session.setAttribute("lainatapahtumaonnistui", true);
        }


        request.getRequestDispatcher("Lainaukset.jsp").forward(request, response);
    }

    /**
     * Palauttaa Lainattava-listasta ensimmäisen vastaantulevan Lainattavan,
     * joka ei ole lainassa.
     *
     * @param lainattavat
     * @return
     */
    private Lainattava haeListastaPalautettuLainattava(List<Lainattava> lainattavat) {
        for (Lainattava lainattava : lainattavat) {
            if (!lainattava.isLainassa()) {
                return lainattava;
            }
        }
        return null;
    }

    /**
     * Palauttaa Lainattava-listasta ensimmäisen vastaantulevan Lainattavan,
     * jonka lainaaja on parametrina annettu string.
     *
     * @param lainaaja
     * @param palautettavat
     * @return
     */
    private Lainattava kayttajanLainausListasta(String lainaaja, List<Lainattava> palautettavat) {

        for (Lainattava lainattava : palautettavat) {
            if (lainattava.getLainaaja().equals(lainaaja)) {
                return lainattava;
            }
        }


        return null;
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
