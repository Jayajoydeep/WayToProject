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
public class Headerwow extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<div>");
        out.println("<table align=center id=e3>");
        out.println("<tr>");
        out.println("<td>");
        out.println("<a id=b0 name=ia href=>HOME</a>");  
        out.println("</td>");
        out.println("<td>");
        out.println("<a id=b1 name=ia href=>FILL TOTAL AMOUNT</a>");  
        out.println("</td>");
        out.println("<td>");
        out.println("<a id=b2 href=>ENTER TRANSACTION</a>");
        out.println("</td>");
        out.println("<td>");
        out.println("<a id=b3 href= >SHOW BALANCE</a>");
        out.println("</td>");
        out.println("<td>");
        out.println("<a id=b4 href= >PROFIT/LOSS</a>");
        out.println("</td>");
        out.println("</tr>");
        out.println("</table>");
        out.println("</div>");
        out.println("<style>");
        out.println("#e3{ background-image: url(Man.jpg); width: 80%; border-color: black; border-style: double;}");
        out.println("#b0, #b1, #b2, #b3, #b4{ color: black; font-size: 20px; font-weight: 400; text-transform: uppercase; }");
        out.println("</style>"); 
        }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
    }

}
