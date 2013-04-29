package servletit.kirja;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import listat.AiheLista;
import listat.KirjaLista;
import listat.KirjailijaLista;

/**
 *
 * Poistetaan lopullisesti teos ja siihen liittvät oliot.
 *
 * @author hkskogbe
 */
public class PoistaLopullisestiServlet extends HttpServlet {

    KirjaLista lista = new KirjaLista();
    KirjailijaLista kirjailijat = new KirjailijaLista();
    AiheLista aiheet = new AiheLista();

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

        String isbn = request.getParameter("isbn");

        if (isbn == null) {
            session.setAttribute("virhepoistettaessalopullisesti", "kirjailija");
            request.getRequestDispatcher("PoistaLopullisesti.jsp").forward(request, response);
            return;
        }

        if (isbn.contains("'") || isbn.isEmpty()) {
            session.setAttribute("virhepoistettaessalopullisesti", "kirjailija");
            request.getRequestDispatcher("PoistaLopullisesti.jsp").forward(request, response);
            return;
        }



        // 1. Poistetaan kirjailijat joilla kirja
        try {
            kirjailijat.poistaKirjanISBNlla(isbn);
        } catch (Exception e) {
            session.setAttribute("virhepoistettaessalopullisesti", "kirjailija");
            request.getRequestDispatcher("PoistaLopullisesti.jsp").forward(request, response);
            return;
        }

        // 2. Poistetaan aiheet joilla kirja
        try {
            aiheet.poistaKirjanISBNlla(isbn);
        } catch (Exception e) {
            session.setAttribute("virhepoistettaessalopullisesti", "aihe");
            request.getRequestDispatcher("PoistaLopullisesti.jsp").forward(request, response);
            return;
        }

        // 3. Poistetaan itse kirja.
        try {
            lista.poistaKirja(isbn);
        } catch (Exception e) {
            session.setAttribute("virhepoistettaessalopullisesti", "kirja");
            request.getRequestDispatcher("PoistaLopullisesti.jsp").forward(request, response);
            return;
        }

        session.setAttribute("poistaminenonnistuilopullisesti", true);
        request.getRequestDispatcher("PoistaLopullisesti.jsp").forward(request, response);
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
