/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CountYourBalance;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jaya kumari
 */
public class SignUp extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       PrintWriter out = response.getWriter();
       out.println("<form method=post>"); 
               out.println(" <table align=center id=kj>");
            out.println("<tr>");
                out.println("<td align=center id=backw colspan=2>");
                    out.println("<h1>EXPENSE MANAGER</h1>");
                out.println("</td>");
            out.println("</tr>");
             out.println("<tr>");
                out.println("<td align=right id=jk>");
                    out.println("<p>Please sign up to use this Application-<br>");
                        out.println("Expense Manager</p>");
                out.println("</td>");
                out.println("<td align=center id=jk>");
                    out.println("<table>");
                        out.println("<tr>");
                            out.println("<th colspan=2>");
                        out.println("<h2>");
                            out.println("SIGN UP");
                        out.println("</h2>");
                            out.println("</th>");
                        out.println("</tr>");
                        out.println("<tr>");
                            out.println("<td>");
                   out.println("USERID:"); 
                out.println("</td>");
                out.println("<td align=center id=jk>");
                   out.println("<input type=text name=usid>"); 
                out.println("</td>");
            out.println("</tr>");
            out.println("<tr>");
                out.println("<td>");
                    out.println("PASSWORD:");
                out.println("</td>");
                out.println("<td align=center id=jk>");
                   out.println("<input type=password name=pass>"); 
                out.println("</td>");
            out.println("</tr>");
            out.println("<tr>");
                 out.println("<td>");
                    out.println("NAME:");
                out.println("</td>");
                out.println("<td align=center id=jk>");
                   out.println("<input type=text name=nam>"); 
                out.println("</td>");
            out.println("</tr>");
            out.println("<tr>");
               out.println("<td>");
               out.println("    ADDRESS:"); 
                out.println("</td>");
                out.println("<td align=center id=jk>");
                out.println("<input type=text name=address> ");
                out.println("</td>");
            out.println("</tr>");
                out.println("<tr align=center>");
                    out.println("<td align=center id=jk colspan=2>");
                   out.println("<input type=submit name=save value=SAVE id=pr>"); 
                out.println("</td>");
            out.println("</tr>");
                    out.println("</table>");
                out.println("</td>");
            out.println("</tr>");
           
        out.println("</table>");
        out.println("<style>");
            out.println("#backw{background-image: url(look.jpg); background-repeat: no-repeat; width: 80%; height: 150px;color: midnightblue; font-style: italic; font-family: Georgia; font-size: x-large; font-weight: 800}");
            out.println("#pr{ background-image: url(logo2.jpg); width: 200px; height: 50px; color: white; font-size: x-large; font-weight: 400; text-transform: uppercase;}");
            out.println("#kj{ background-image: url(log1.jpg); width: 80%; background-repeat: repeat; height: 600px}");
            out.println("#jk{color: white; font-style: italic; font-family: Georgia; font-size: 20px; font-weight: 900}");
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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Connection con = null;
        PreparedStatement pr = null;
        ResultSet rs = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:Xe", "HCL", "qwerty");
        } catch (ClassNotFoundException | SQLException ex) {
             out.println("<br> <br ><br> <br >");
                out.println("<br> <br ><br> <br >");
                out.println("<table border=1 align=center id=kj>");
                out.println("<tr>");
                out.println("<td align=center id=jk>");
                out.println("Error: "+ex+"<br>");
                out.println("<a href=Start>Back</a>");
                out.println("</td>");
                out.println("</tr>");
                out.println("</table>");
                out.println("<style>");
                out.println("#kj{ background-image: url(log1.jpg); width: 450px; background-repeat: repeat; height: 250px}");
                out.println("#jk{color: seashell; font-style: italic; font-family: Georgia; font-size: x-large; font-weight: 600}");
                out.println("</style>");
       }
   

String id= request.getParameter("usid");
String iname= request.getParameter("nam");
String password= request.getParameter("pass");
String add= request.getParameter("address");



            try{
                pr= con.prepareStatement("Insert into login values(?,?,?,?)");
                pr.setString(1, id);
                pr.setString(2,password);
                pr.setString(3, iname);
                pr.setString(4, add);
                
                int i= pr.executeUpdate();
                if(i==1)
                {
                    response.sendRedirect("LoginPage");
                }
                else
                {
                    
                    out.println("ADD AGAIN");
                }
                
            }catch(SQLException ex)
            {
                 
                out.println("Error: "+ex+"<br>");
               
            }
    }
   
}
