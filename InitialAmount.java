package CountYourBalance;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class InitialAmount extends HttpServlet {

    LoginPage loginpa = new LoginPage();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         RequestDispatcher rd=request.getRequestDispatcher("Header");
        rd.include(request, response);
        RequestDispatcher rd1=request.getRequestDispatcher("Header2");
        rd1.include(request, response);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<form method=post>");
        out.println("<table border=1 align=center id=ko>");
        out.println("<tr>");
        out.println("<td align=center id=mk>");
        out.println("<br> <br> WELCOME: "+ loginpa.s1+"<br> <br>");
        out.println("<br><br><input id=vb type=submit name=cont value=NEXT>");
        out.println("</td>");
        out.println("</tr>");
        out.println("</table>");
        out.println("<style>");
        out.println("#ko{ background-image: url(butter.jpg); width: 80%; background-repeat: repeat; height: 450px}");
        out.println("#mk{color: black; font-style: italic; font-family: Georgia; font-size: x-large; font-weight: 600}");
        out.println("#vb{ background-image: url(logo2.jpg); width: 280px; height: 50px; color: white; font-size: x-large; font-weight: 400; text-transform: uppercase}");
        out.println("</style>");
        out.println("</form>");
    }
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
int a1;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("Start");
      
       
    }

}
