package servletit;

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
 *
 * @author hkskogbe
 */
public class ListaaKirjatServlet extends HttpServlet {

    private KirjaLista lista = new KirjaLista();

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




        String nimi = request.getParameter("kirja");
        String kirjailija = request.getParameter("kirjailija");
        String aihe = request.getParameter("aihe");
        String avuosi = request.getParameter("avuosi");
        String lvuosi = request.getParameter("lvuosi");

        try {
            if (Integer.parseInt(avuosi) > Integer.parseInt(lvuosi)) {
                request.setAttribute("lvuosiEnnenAvuotta", true);
            }
        } catch (Exception e) {
            if (avuosi.length() != 4 || lvuosi.length() != 4) {
                request.setAttribute("virheellinenVuosi", true);
            }
        }

        List<Kirja> hakutulokset = null;

        // Jos hakuehtona vain nimi
        if (!nimi.isEmpty() && kirjailija == null && aihe == null && avuosi == null && lvuosi == null) {
            hakutulokset = lista.getKirjaNimella(nimi);

        } // Jos hakuehdot tyhjät ja hakuehtona vain nimi
        else if (nimi.isEmpty() && kirjailija == null && aihe == null && avuosi == null && lvuosi == null) {
            hakutulokset = lista.getKirjat();

        } // Jos parametrit eivät tyhjiä
        else if (!nimi.isEmpty() || !kirjailija.isEmpty() || !aihe.isEmpty() || !avuosi.isEmpty() || !lvuosi.isEmpty()) {
            List<String> kirjailijat = listaaStringista(kirjailija);
            List<String> aiheet = listaaStringista(aihe);
            hakutulokset = lista.getKirja(nimi, kirjailijat, aiheet, avuosi, lvuosi);

        } else {
            hakutulokset = lista.getKirjat();

        }


        request.getSession().setAttribute("lista", hakutulokset);
        request.setAttribute("lista", hakutulokset);

        RequestDispatcher rd = request.getRequestDispatcher("hakusivu.jsp");
        rd.forward(request, response);
    }

    private List<String> listaaStringista(String string) {
        List<String> k = new ArrayList<String>();

        String[] s = string.split(",");
        for (String lisattava : s) {
            k.add(lisattava.trim());
        }
        return k;
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
