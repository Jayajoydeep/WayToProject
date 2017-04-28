package CountYourBalance;


import java.util.Calendar;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Header extends HttpServlet {
LoginPage m= new LoginPage();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<form method=post>");
        out.println("<div>");
        out.println("<table align=center id=back>");
        out.println("<tr>");
        out.println("<td id=head>");
        out.println("<h2>EXPENSE MANAGER</h2>");
        out.println("</td>");
        out.println("</tr>");
        out.println("</table>");
        out.println("<style>");
        out.println("#back{background-image: url(look.jpg); background-repeat: no-repeat; width: 80%; height: 150px;}");
        out.println("#head{ text-align: center; font-family: Copperplate Gothic Bold,serif; font-size: xx-large; color: indigo; font-weight: 800;}"); 
        out.println("</style>");
        out.println("</div>");
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
        processRequest(request, response);
    }


}
