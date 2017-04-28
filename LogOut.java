/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CountYourBalance;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jaya kumari
 */
public class LogOut extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<form method=post>");
            out.println("<table align=center id=kj>");
            out.println("<tr>");
            out.println("<td align=center id=backw>");
                    out.println("EXPENSE MANAGER");
                out.println("</td>");
            out.println("</tr>");
            
            out.println("<tr>");
            out.println("<td align=center id=jk>");
           out.println("<p>Thank You To use This Application. May this App state usefull to u</p>");
           out.println("<input type=submit name=log value=LOGIN id=pr>");
            out.println("</td>"); 
             out.println("</tr>");
            
            out.println("</table>");
            out.println("<style>");
            out.println("#backw{background-image: url(look.jpg); background-repeat: no-repeat; width: 80%; height: 150px;color: midnightblue; font-style: italic; font-family: Georgia; font-size: x-large; font-weight: 800}");
            out.println("#pr{ background-image: url(logo2.jpg); width: 120px; height: 40px; color: white; font-size: x-large; font-weight: 400; text-transform: uppercase;}");
            out.println("#kj{ background-image: url(log1.jpg); width: 80%; background-repeat: repeat; height: 600px}");
            out.println("#jk{color: seashell; font-style: italic; font-family: Georgia; font-size: 28px; font-weight: 900}");
            out.println("#lou, #bk{ background-image: url(logo2.jpg); width: 150px; height: 40px; color: white; font-size: large; font-weight: 200;}");
        out.println("</style>");  
        out.println("</form>");
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("LoginPage");
        
    }

}
