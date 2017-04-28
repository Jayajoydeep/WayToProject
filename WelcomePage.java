package CountYourBalance;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jaya kumari
 */
public class WelcomePage extends HttpServlet {
    Connection con;
        PreparedStatement pr;
        ResultSet rs;
        String m;
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        RequestDispatcher rd=request.getRequestDispatcher("Header");
        rd.include(request, response);
        out.println("<form method=post");
        out.println("<div>");
        out.println("<table align=center id=backon>");
        out.println("<tr>");
        out.println("<td align=center>");
        out.println("<font size=3 color=white face=\"Comic Sans MS\"><b>WELCOME TO BALANCED ACT WEBSITE. HERE YOU CAN BALANCE YOUR MONTHLY, ANNUALLY OR EVEN DAYS EXPENDITURE.</b></font> ");
        out.println("<br><br><input id=log type=submit value=LOGIN name=n1>");
       
        out.println("<br><br>");
        out.println("<input id=sig type=submit name=n1 value=SIGNUP><br><br>");
        out.println("<font size=3 color=white face=\"Comic Sans MS\"><b>LETS MAKE OUR EVERY ACTION BALANCED AND ACCURATE<b></font>");
        out.println("</td>");
        out.println("</tr>");
        out.println("</table>");
        out.println("<style>");
        out.println("#backon { background-image: url(logo1.jpg); background-repeat: repeat; width: 80%; height:500px; }");
        out.println("#log, #sig{ background-image: url(logo2.jpg); width: 320px; height: 60px; color: white; font-size: x-large; font-weight: 400; text-transform: uppercase;}");
        out.println("</style>");
        out.println("</div>");
        out.println("</form>");
       
       
    }
String nq;
int i,j;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
       String s1,s2;
       
       
       s1= request.getParameter("n1");
       if(s1.equals("LOGIN"))
       {
           
          response.sendRedirect("LoginPage");
       }
       else
       {
           response.sendRedirect("SignUp");
       }
    }}