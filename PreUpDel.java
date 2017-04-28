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
public class PreUpDel extends HttpServlet {

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
            out.println("<table align=center id=kj>");
            out.println("<tr>");
            out.println("<td align=center id=backw>");
                    out.println("EXPENSE MANAGER");
                out.println("</td>");
            out.println("</tr>");
             out.println("<tr>");
                out.println("<td align=center id=jk>");
                out.println("<p align=right>");
                 out.println("<input type=submit name=b1 value=BACK id=bk>");
            out.println("<input type=submit name=b1 value=LOGOUT id=lou>");
            out.println("</p>");
            out.println("</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td align=center id=jk>");
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
    

            pr = con.prepareStatement("Select ITEM.SNO,ITEMNAME,QTY,COST,DATEOFITEM from ITEM,DATAITEM where ITEM.SNO=DATAITEM.SNO");
            rs = pr.executeQuery();
           
            
            while(rs.next())
            {
                out.println("<tr>");
                 int m= rs.getInt("SNO");
             out.println("<td><input type=checkbox name=chk value="+m+"></td>");
            
             
             out.println("<td>"+rs.getInt(1)+"</td>");
             out.println("<td>"+rs.getString(2)+"</td>");
             out.println("<td>"+rs.getInt(3)+"</td>");
             out.println("<td>"+rs.getInt(4)+"</td>");
             out.println("<td>"+rs.getString(5)+"</td>");
             out.println("</tr>");
            }
            out.println("<tr>");
out.println("<input id=teb type=submit name=b2 value=UPDATE>");
out.println("<input id=teb type=submit name=b2 value=DELETE></td>");
out.println("</tr>");
            
            out.println("</table>");
            out.println("<style>");
        out.println("#tesu {border-color: white; border-style: double; border-spacing:20px; color: white; font-size: large; font-weight: 200}");
        out.println("#teb{ background-image: url(logo2.jpg); width: 110px; height: 30px; color: white; font-size: large; font-weight: 200;}");
        out.println("</style>");
            

        } catch (NullPointerException|SQLException ex) {
           
               
                out.println("<br> <br ><br> <br >");
                out.println("<br> <br ><br> <br >");
                out.println("<table align=center id=kj>");
                out.println("<tr>");
                out.println("<td align=center id=jk>");
                out.println("Please Enter It Again");
                out.println("<a href=Start>Back</a>");
                out.println("</td>");
                out.println("</tr>");
                out.println("</table>");
                out.println("<style>");
                out.println("#kj{ background-image: url(log1.jpg); width: 450px; background-repeat: repeat; height: 250px}");
                out.println("#jk{color: seashell; font-style: italic; font-family: Georgia; font-size: x-large; font-weight: 600}");
                out.println("</style>");
           
        }

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
    }
int id;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
         String nameofbut= request.getParameter("b2");
         
         Connection con = null;
        PreparedStatement pr = null;
        ResultSet rs = null;
        
        
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:Xe", "HCL", "qwerty");
        } catch (ClassNotFoundException | SQLException ex) {
             out.println("ERROR:"+ex);
       }

       
        
        if(nameofbut.equals("UPDATE"))
        {
             
           response.sendRedirect("Update");
       }
        else
        {
            
           id= Integer.parseInt(request.getParameter("chk"));
            
           try{
   
    pr= con.prepareStatement("Delete from item where SNO ="+id);

            int i= pr.executeUpdate();
            if(i==1)
            {
                response.sendRedirect("TodayEntry");
            }
            else
            {
                out.println("ADD AGAIN");
            }
           }
            catch(SQLException ex)
                    {
                    out.println("ERROR: "+ex);
                    }
        }
    }

}
