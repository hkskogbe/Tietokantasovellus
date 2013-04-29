package servletit.haku;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import kirja.Aihe;
import kirja.Kirja;
import kirja.Kirjailija;
import listat.AiheLista;
import listat.KirjaLista;
import listat.KirjailijaLista;

/**
 * Käsittelee kirjan tietojen tarkemman näyttämisen.
 *
 * @author hkskogbe
 */
public class TarkemmatTiedot extends HttpServlet {

    private KirjaLista lista = new KirjaLista();
    private KirjailijaLista kirjailijalista = new KirjailijaLista();
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

        HttpSession session = request.getSession();

        session.setAttribute("tiedotNakyvissa", true);

        String isbn = request.getParameter("isbn");
        Kirja kirja;
        try {
            kirja = lista.getKirjaISBNlla(isbn);
        } catch (Exception e) {
            session.setAttribute("virheTarkentaessa", true);
            request.getRequestDispatcher("hakusivu.jsp").forward(request, response);
            return;
        }

        /*
         <c:out value="${teosNimi}"/>
         <c:out value="${teosISBN}"/>
         <c:out value="${teosJulkaisuvuosi}"/>
         <c:out value="${teosKirjailijat}"/>
         <c:out value="${teosAiheet}"/>
         <c:out value="${teosSaatavilla}"/>
         </div>
         */

        // Parametrit jsplle
        String teosNimi;
        String teosISBN;
        String teosJulkaisuvuosi;
        String teosKirjailijat;
        String teosAiheet;
        String teosSaatavilla;

        // Initialize

        teosNimi = kirja.getNimi();
        teosISBN = kirja.getISBN();
        teosJulkaisuvuosi = "" + kirja.getJulkaisuvuosi();

        teosKirjailijat = "  ";
        for (Kirjailija k : kirjailijalista.getKirjanKirjailijat(teosISBN)) {
            teosKirjailijat += k.getKirjailijanNimi() + "; ";
        }
        teosKirjailijat = teosKirjailijat.substring(0, teosKirjailijat.length() - 2).trim();

        teosAiheet = "  ";
        for (Aihe a : aihelista.getKirjaanLiittyvatAiheet(teosISBN)) {
            teosAiheet += a.getAihe() + ", ";
        }
        teosAiheet = teosAiheet.substring(0, teosAiheet.length() - 2).trim();

        if (kirja.isPoistettu()) {
            teosSaatavilla = "Teos on poistettu kokoelmasta";
        } else {
            teosSaatavilla = "Teos löytyy kokoelmasta";
        }










        session.setAttribute("teosNimi", teosNimi);
        session.setAttribute("teosISBN", teosISBN);
        session.setAttribute("teosJulkaisuvuosi", teosJulkaisuvuosi);
        session.setAttribute("teosKirjailijat", teosKirjailijat);
        session.setAttribute("teosAiheet", teosAiheet);
        session.setAttribute("teosSaatavilla", teosSaatavilla);

        request.getRequestDispatcher("hakusivu.jsp").forward(request, response);
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
