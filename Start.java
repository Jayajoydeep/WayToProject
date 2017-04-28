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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jaya kumari
 */
public class Start extends HttpServlet {
 BalanceSheet bal= new BalanceSheet();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * 
     */
    LoginPage loginpa = new LoginPage();
    Calendar theCal = Calendar.getInstance();
  int currentYearInt  = theCal.get(Calendar.YEAR);
  int currentMonthInt = theCal.get(Calendar.MONTH)+1;
  int currentDayInt   = theCal.get(Calendar.DATE);
  String sa;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession sq= request.getSession();
        sa= (String)sq.getAttribute("s1");
        out.println("<FORM METHOD=POST>");        
        out.println("<table align=center id=kj>");
             out.println("<tr>");
                 out.println("<td align=center id=backw>");
                     out.println("EXPENSE MANAGER");
                 out.println("</td>");
             out.println("</tr>");
             out.println("<tr>");
        out.println("<td align=center id=jk>");
        out.println("<br> <br> WELCOME: "+ sa+"<br> <br>");
        
        out.println("</td>");
        out.println("</tr>");
             out.println("<tr>");
              out.println("<td align=center id=jk>");
                     out.println("<input type=submit name=b1 value=PREVIOUS_RECORD id=pr>");
                 out.println("</td>");
               out.println("</tr>");
              out.println("<tr>");
              out.println("<td align=center id=jk>");
                     out.println("Total Income of "+currentMonthInt+"/"+currentYearInt+": <input type=text name=valu>");
                     String totalAmount= request.getParameter("valu");
                    
                 out.println("</td>");
               out.println("</tr>");
               out.println("<tr>");
                 out.println("<td align=center id=jk>");
                     out.println("<input type=submit name=b1 value=PREVIOUS_CHECK id=pr>");
                out.println(" </td>");
            out.println(" </tr>");
              out.println("<tr>");
                 out.println("<td align=center id=jk>");
                     out.println("<input type=submit name=b1 value=TODAYS_ENTRY id=td>");
                 out.println("</td>");
             out.println("</tr>");
             out.println(" <tr>");
                 out.println("<td align=right id=jk>");
                   out.println(" <input type=submit name=b1 value=LOGOUT id=lou> ");
                out.println(" </td>");
            out.println(" </tr>");
            out.println("<tr>");
                 out.println("<td align=center id=jk>");
                    
                
                }
    
    void footer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println(" </td>");
            out.println(" </tr>");
         out.println("</table>");
        out.println(" <style>");
             out.println("#backw{background-image: url(look.jpg); background-repeat: no-repeat; width: 80%; height: 150px;color: midnightblue; font-style: italic; font-family: Georgia; font-size: x-large; font-weight: 800}");
             out.println("#fu, #td, #pr { background-image: url(logo2.jpg); width: 320px; height: 60px; color: white; font-size: x-large; font-weight: 400; text-transform: uppercase;}");
             out.println("#kj{ background-image: url(log1.jpg); width: 80%; background-repeat: repeat; height: 600px}");
             out.println("#jk{color: seashell; font-style: italic; font-family: Georgia; font-size: 30px; font-weight: 200}");
              out.println("#pi { background-image: url(logo2.jpg); width: 120px; height: 20px; color: white; font-size: large; font-weight: 100; text-transform: uppercase;}");
             out.println("#lou{ background-image: url(logo2.jpg); width: 200px; height: 60px; color: white; font-size: x-large; font-weight: 400;}");
        out.println(" </style>");  
        out.println("</FORM>");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        footer(request, response);
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
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
         response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String ab= request.getParameter("b1");
        String totalAmount= request.getParameter("valu");
        HttpSession ss = request.getSession(false);
       if(ss!=null)
       {
           String uid = (String)ss.getAttribute("name");
        if(ab.equals("PREVIOUS_RECORD"))
        {
            gotit(request,response);
        }
        else if(ab.equals("PREVIOUS_CHECK"))
        {
            if(currentDayInt==1)
            {
                out.println("NO PREVIOUS DATA");
            }
            else{
                sqlgotit(request,response);
                }
        }
        
        else if(ab.equals("TODAYS_ENTRY"))
        {
            HttpSession sp= request.getSession();
            ss.setAttribute("totalAmount",totalAmount);
            String s12=(String)ss.getAttribute("s1");
            ss.setAttribute("s1", s12);
            response.sendRedirect("TodayEntry");
        }
        else if(ab.equals("LOGOUT"))
        {
            response.sendRedirect("LogOut");
        }
       }
        
        footer(request, response);
        
    }
void sqlgotit(HttpServletRequest request, HttpServletResponse response) throws IOException
 {
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


out.println("<form method=post>");
out.println("<table id=tesu >");
out.println("<tr>");
out.println("<th></th>");
out.println("<th>Item ID</th>");
out.println("<th>Item Name</th>");
out.println("<th>Item Qty</th>");
out.println("<th>Item Cost</th>");
out.println("<th>Date</th>");
out.println("</tr>");

try {
    

            pr = con.prepareStatement("Select ITEM.SNO,ITEMNAME,QTY,COST,DATEOFITEM from ITEM,DATAITEM where ITEM.SNO=DATAITEM.SNO and USID='"+sa+"'");
            rs = pr.executeQuery();
}catch (NullPointerException|SQLException ex) {
          out.println("Error1"+ex);
                out.println("<a href=Start>Back</a>");
               
           
        }     
        try{    
            while(rs.next())
            {
                out.println("<tr>");
                 int m= rs.getInt("SNO");
             out.println("<td><input type=checkbox name=chk value="+m+"></td>");
            
             
            out.println("<td>"+rs.getInt("SNO")+"</td>");
             out.println("<td>"+rs.getString("ITEMNAME")+"</td>");
             out.println("<td>"+rs.getInt("QTY")+"</td>");
             out.println("<td>"+rs.getInt("COST")+"</td>");
             out.println("<td>"+rs.getString("DATEOFITEM")+"</td>");
             out.println("</tr>");
            }
            out.println("<tr>");
out.println("<a href=PreUpDel>ACTION</a>");

out.println("</tr>");
            
            out.println("</table>");
            out.println("<style>");
        out.println("#tesu {border-color: white; border-style: double; border-spacing:20px; color: white; font-size: large; font-weight: 200}");
        out.println("#teb{ background-image: url(logo2.jpg); width: 110px; height: 30px; color: white; font-size: large; font-weight: 200;}");
        out.println("</style>");
        out.println("</form>");
} catch (NullPointerException|SQLException ex) {
          out.println("Error2"+ex);
                out.println("<a href=Start>Back</a>");
               
           
        }
       
 }
void gotit(HttpServletRequest request, HttpServletResponse response) throws IOException
 {
             response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Connection con = null;
        PreparedStatement pr = null;
        ResultSet rs = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:Xe", "HCL", "qwerty");
        } catch (ClassNotFoundException | SQLException ex) {
            
                out.println("Error: "+ex+"<br>");
                out.println("<a href=Start>Back</a>");
                
       }
out.println("<table id=tesu >");
out.println("<tr>");
out.println("<th>Item ID</th>");
out.println("<th>Item Name</th>");
out.println("<th>Item Qty</th>");
out.println("<th>Item Cost</th>");

out.println("</tr>");

try {
    

            pr = con.prepareStatement("Select ITEM.SNO,ITEMNAME,QTY,COST,DATEOFITEM from ITEM,DATAITEM where ITEM.SNO=DATAITEM.SNO and USID='"+sa+"'");
            rs = pr.executeQuery();
}catch (NullPointerException|SQLException ex) {
          out.println("Error1"+ex);
                out.println("<a href=Start>Back</a>");
               
           
        }     
        try{    
            while(rs.next())
            {
                out.println("<tr colspan=4>");
                 out.println("<td>"+rs.getString("DATEOFITEM")+"</td>");
                  out.println("</tr>");
                 int m= rs.getInt("SNO");
             //out.println("<td><input type=checkbox name=chk value="+m+"></td>");
             out.println("<tr>");
             
            out.println("<td>"+rs.getInt("SNO")+"</td>");
             out.println("<td>"+rs.getString("ITEMNAME")+"</td>");
             out.println("<td>"+rs.getInt("QTY")+"</td>");
             out.println("<td>"+rs.getInt("COST")+"</td>");
            
             out.println("</tr>");
            }
            out.println("<tr>");
out.println("<a href=PreUpDel>ACTION</a>");

out.println("</tr>");
            
            out.println("</table>");
            out.println("<style>");
        out.println("#tesu {border-color: white; border-style: double; border-spacing:20px; color: white; font-size: large; font-weight: 200}");
        out.println("#teb{ background-image: url(logo2.jpg); width: 110px; height: 30px; color: white; font-size: large; font-weight: 200;}");
        out.println("</style>");
        out.println("</form>");
} catch (NullPointerException|SQLException ex) {
          out.println("Error2"+ex);
                out.println("<a href=Start>Back</a>");
               
           
        }
       
 }

}
