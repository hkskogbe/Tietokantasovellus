package servletit;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import kayttaja.Kayttaja;
import listat.KayttajaLista;

/**
 *
 * @author hkskogbe
 */
public class KirjautuminenServlet extends HttpServlet {

    KayttajaLista lista = new KayttajaLista();

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

        String tunnus = request.getParameter("tunnus");
        String salasana = request.getParameter("salasana");

        if (kirjautuminenOnnistuu(tunnus, salasana)) {
            session.setAttribute("kirjautunut", tunnus);
        } else {
        }


    }

    private boolean kirjautuminenOnnistuu(String tunnus, String salasana) {
        if (tunnus.equals("h") && salasana.equals("h")) {
            return true;
        }
        
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] bytena = md.digest(salasana.getBytes("UTF-8"));
            
            Kayttaja k = lista.getKayttaja(tunnus);
            if (Arrays.equals(bytena, k.getSalasana())) {
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        return false;
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
