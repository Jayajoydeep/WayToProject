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
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jaya kumari
 */
public class Update extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
  
    //****************************************************************************************************************************
Calendar theCal = Calendar.getInstance();
  int currentYearInt  = theCal.get(Calendar.YEAR);
  int currentMonthInt = theCal.get(Calendar.MONTH)+1;
  int currentDayInt   = theCal.get(Calendar.DATE);
    int s=1;
    TodayEntry te= new TodayEntry();
    void baseEntry(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
 {
      response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<form method=post>");
out.println("<table align=center id=adkj>");
            out.println("<tr>");
            out.println("<td align=center id=adbackw>");
                    out.println("EXPENSE MANAGER");
                out.println("</td>");
            out.println("</tr>");
             out.println("<tr>");
                out.println("<td align=center id=adjk>");
                out.println("<p align=right>");
                out.println(currentDayInt+"/"+currentMonthInt+"/"+currentYearInt+"\t");
                
                    out.println("<input type=submit name=b1 value=BACK id=adbk>");
            out.println("<input type=submit name=b1 value=LOGOUT id=adlou>");
            out.println("</p>");
            out.println("</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td align=center id=adjk>");
updatedata(request,response);
out.println("</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td align=center id=adjk>");
            out.println("</td>");
            out.println("</tr>");
            out.println("</table>");
            out.println("<style>");
            out.println("#adbackw{background-image: url(look.jpg); background-repeat: no-repeat; width: 80%; height: 150px;color: midnightblue; font-style: italic; font-family: Georgia; font-size: x-large; font-weight: 800}");
            out.println("#adpr{ background-image: url(logo2.jpg); width: 120px; height: 40px; color: white; font-size: x-large; font-weight: 400; text-transform: uppercase;}");
            out.println("#adkj{ background-image: url(log1.jpg); width: 80%; background-repeat: repeat; height: 600px}");
            out.println("#adjk{color: seashell; font-style: italic; font-family: Georgia; font-size: 28px; font-weight: 900}");
            out.println("#adlou, #adbk{ background-image: url(logo2.jpg); width: 150px; height: 40px; color: white; font-size: large; font-weight: 200;}");
        out.println("</style>");  
        out.println("</form>");


 }
    
    void updatedata(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
 {
    response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<table id=upsu>");
         out.println("<tr>");
            out.println("<td colspan=5>");
            out.println("ID:<input type=text name=idw value="+te.id+"><br>");
           out.println("SELECT THING:<select id=uptm name=dropdown> <option value=ITEMNAME selected>Item</option> <option value=QTY>Quantity</option> <option value=COST>Cost</option> </select>");          
            out.println("<br>Enter new value <input type=text name=newval><br>");
            out.println("<input type=submit name=b1 value=UPDATE>");
            out.println("</td>");
            out.println("</tr>");
        out.println("</table>");
        out.println("<style>");
        out.println("#upsu {border-color: white; border-style: double; border-spacing:20px; color: white; font-size: x-large; font-weight: 400}");
        
        out.println("#uptm {color: black; font-size: large; font-weight: 200}");
        out.println("</style>");

 }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       baseEntry(request,response);
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
        String buto= request.getParameter("b1");
                String opt= request.getParameter("dropdown");
String value= request.getParameter("newval");
int idj= Integer.parseInt(request.getParameter("idw"));

if(buto.equals("BACK"))
{
   
            response.sendRedirect("TodayEntry");
}
else if(buto.equals("LOGOUT"))
{
     response.sendRedirect("LogOut");
}
else{
 try{
if(opt.equals("ITEMNAME"))
{
                pr= con.prepareStatement("Update item set "+opt+"='"+value+"' where SNO="+idj);
               
}    
else
{
   int newva= Integer.parseInt(value);
     pr= con.prepareStatement("Update item set "+opt+"="+newva+"where SNO="+idj);
}
                int i= pr.executeUpdate();
                if(i==1)
                {
                     response.sendRedirect("TodayEntry");
                }
                else
                {
                    out.println("ADD AGAIN");
                }

            }catch(SQLException ex)
            {
                out.println("Error: "+ex+"<br>");
                out.println("<a href=Start>Back</a>");
            
            }
}
    }

    
}
