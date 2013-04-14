package servletit;

import java.io.IOException;
import java.io.PrintWriter;
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

public class TarkemmatTiedot extends HttpServlet {

    private KirjaLista lista = new KirjaLista();
    private KirjailijaLista kirjailijalista = new KirjailijaLista();
//    private AiheLista aihelista = new AiheLista();

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



        request.getSession().setAttribute("tiedotNakyvissa", true);

        String isbn = request.getParameter("isbn");
        List<Kirja> listana = lista.getKirja(isbn);

        Kirja kirja;

        if (listana.isEmpty()) {
            kirja = new Kirja("Tapahtui virhe.", isbn, 0000);
        } else {
            kirja = listana.get(0);
        }

//        try {
//        } catch (Exception e) {
//            request.getRequestDispatcher("index.jsp").forward(request, response);
//            return;
//        }

        List<String> tiedot = new ArrayList<String>();
        tiedot.add(kirja.getNimi());
        tiedot.add(kirja.getISBN());
        tiedot.add("" + kirja.getJulkaisuvuosi());

        List<Kirjailija> kirjailijat = kirjailijalista.getKirjanKirjailijat(kirja);

        String kirjailijaString = "";

        for (Kirjailija kirjailija : kirjailijat) {
            kirjailijaString += kirjailija.getKirjailijanNimi() + "   ";
        }
        tiedot.add(kirjailijaString);

//        List<Aihe> aiheet = aihelista.getKirjaanLiittyvatAiheet(kirja);
//        
//        String aiheString = "";
//        for (Aihe aihe : aiheet) {
//            aiheString += aihe.getAihe() + ", ";
//        }
//        aiheString = aiheString.substring(0, aiheString.length() - 2);
//        tiedot.add(aiheString);

        request.setAttribute("tiedot", tiedot);

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
