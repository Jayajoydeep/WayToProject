package CountYourBalance;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginPage extends HttpServlet {
     public String s1;
  String s2;
  
   void initialPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
         RequestDispatcher rd=request.getRequestDispatcher("Header");
        rd.include(request, response);
        RequestDispatcher rd1 = request.getRequestDispatcher("Header1");
        rd1.include(request, response);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<form method=post>");
        out.println("<table border=1 align=center id=ko>");
        out.println("<tr>");
        out.println("<td align=center id=mk>");
        out.println("<br> <br> WELCOME: "+s1+"<br> <br>");
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
   
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        RequestDispatcher rd = request.getRequestDispatcher("Header");
        rd.include(request, response);
      
        out.println("<form method=post>");
        out.println("<div>");
        out.println("<table align=center id=t2>");
        out.println("<tr>");
        out.println("<td id=m2 align=center>");
        out.println("<table id=m3>");
        out.println("<tr>");
        out.println("<td colspan=2>");
        out.println("ENTER LOGIN DETAILS:");
        out.println("</td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td>");
        out.println("USER ID:");
        out.println("</td>");
        out.println("<td>");
        out.println("<input type=text name=usid>");
        out.println("</td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td>");
        out.println("PASSWORD:");
        out.println("</td>");
        out.println("<td>");
        out.println("<input type=password name=pass>");
        out.println("</td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td >");
        out.println("<input id=nm type=submit value=LOGIN>");
        out.println("</td>");
        out.println("</tr>");
//        out.println("<tr>");
//        out.println("<td color=white>");
//        out.println("<a href=Forget>Forget Password</a>");
//        out.println("</td>");
//        out.println("</tr>");
        out.println("</table>");
        out.println("</td>");
        out.println("</tr>");
        out.println("</table>");
        out.println("</div>");
        out.println("<style>");
        out.println("#t2{ background-image: url(log1.jpg); background-repeat: repeat; width: 80%; height:500px; }");
        out.println("#m2{ width: 320px; height: 90px; color: white; font-size: x-large; font-weight: 400; text-transform: uppercase; }");
        out.println("#m3{ border-color: white; border-style: double; border-spacing:20px; color: white; font-size: x-large; font-weight: 400}");
        out.println("#nm{ background-color: buttonface; color; font-weight: 200; width: 120px}");
        out.println("</style>");
        out.println("</form>");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    private String nq;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        sqlcheck(request, response);
        
    }

    

    void sqlcheck(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Connection con = null;
        PreparedStatement pr = null;
        ResultSet rs = null;
        String passcode = null;
        s1 = request.getParameter("usid");
        s2 = request.getParameter("pass");
//        out.println(s1+"........."+s2);
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
                out.println("<a href=LoginPage>Back</a>");
                out.println("</td>");
                out.println("</tr>");
                out.println("</table>");
                out.println("<style>");
                out.println("#kj{ background-image: url(log1.jpg); width: 450px; background-repeat: repeat; height: 250px}");
                out.println("#jk{color: seashell; font-style: italic; font-family: Georgia; font-size: x-large; font-weight: 600}");
                out.println("</style>");
       }
        
        
        try {

            pr = con.prepareStatement("Select PASSWORD from Login where USID=?");
            pr.setString(1, s1);
            rs = pr.executeQuery();
            while(rs.next())
            {
             passcode = rs.getString("PASSWORD");
             
            }
            
            if (passcode.equals(s2)) {
                HttpSession ss= request.getSession();
            ss.setAttribute("s1",s1);
            ss.setMaxInactiveInterval(60);
            response.sendRedirect("Start");
               
        } 
            

        } catch (NullPointerException|SQLException ex) {
           
            RequestDispatcher rd = request.getRequestDispatcher("Header");
        rd.include(request, response);
        
                out.println("<br> <br ><br> <br >");
                out.println("<br> <br ><br> <br >");
                out.println("<table align=center id=kj>");
                out.println("<tr>");
                out.println("<td align=center id=jk>");
                out.println("Please Enter It Again");
                out.println("<a href=LoginPage>Back</a>");
                out.println("</td>");
                out.println("</tr>");
                out.println("</table>");
                out.println("<style>");
                out.println("#kj{ background-image: url(log1.jpg); width: 450px; background-repeat: repeat; height: 250px}");
                out.println("#jk{color: seashell; font-style: italic; font-family: Georgia; font-size: x-large; font-weight: 600}");
                out.println("</style>");
           
        }
    }
    
   

}
