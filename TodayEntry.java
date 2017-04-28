
package CountYourBalance;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jaya kumari
 */
public class TodayEntry extends HttpServlet {
int m;

     Calendar theCal = Calendar.getInstance();
  int currentYearInt  = theCal.get(Calendar.YEAR);
  int currentMonthInt = theCal.get(Calendar.MONTH)+1;
  int currentDayInt   = theCal.get(Calendar.DATE);
    private String String;
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
                out.println("<p align=right>");
                out.println(currentDayInt+"/"+currentMonthInt+"/"+currentYearInt+"\t");
                
                    out.println("<input type=submit name=b1 value=BACK id=bk>");
            out.println("<input type=submit name=b1 value=LOGOUT id=lou>");
            out.println("</p>");
            out.println("</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td align=center id=jk>");
            sqlgotit(request, response);
            
            
    }

 
 

  void footer(HttpServletRequest request, HttpServletResponse response) throws IOException
 {
             response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();   
        
           out.println("</td>");
             out.println("</tr>");
             out.println("<tr>");
            out.println("<td align=center id=jk>");
            out.println("<input type=submit name=b1 value=BALANCE id=lou>");
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
            
            
                out.println("Error: "+ex+"<br>");
                out.println("<a href=Start>Back</a>");
               
       }



out.println("<table id=tesu >");

out.println("<tr>");
out.println("<th>  </th>");
out.println("<th>Item ID</th>");
out.println("<th>Item Name</th>");
out.println("<th>Item Qty</th>");
out.println("<th>Item Cost</th>");
out.println("<th>DATE</th>");
out.println("</tr>");
 DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
 Date date= new Date();
String yourDate = currentDayInt+"/"+"0"+currentMonthInt+"/"+currentYearInt;
//out.println(yourDate);
try {
    

            pr = con.prepareStatement("Select ITEM.SNO,ITEMNAME,QTY,COST,DATEOFITEM from ITEM,DATAITEM where ITEM.SNO=DATAITEM.SNO and USID='"+sq+"' and DATAITEM.DATEOFITEM='"+yourDate+"'");
            rs = pr.executeQuery();
           while(rs.next())
            {
                out.println("<tr>");
                m= rs.getInt("SNO");
             out.println("<td><input type=checkbox name=chk value="+m+"></td>");
             
             out.println("<td>"+rs.getInt("SNO")+"</td>");
             out.println("<td>"+rs.getString("ITEMNAME")+"</td>");
             out.println("<td>"+rs.getInt("QTY")+"</td>");
             out.println("<td>"+rs.getInt("COST")+"</td>");
             out.println("<td>"+rs.getString("DATEOFITEM")+"</td>");
             out.println("</tr>");
            }
            out.println("<tr>");
out.println("<td colspan=5><input id=teb type=submit name=b1 value=ADDNEW>");
out.println("<input id=teb type=submit name=b1 value=UPDATE>");
out.println("<input id=teb type=submit name=b1 value=DELETE></td>");
out.println("</tr>");

            
            out.println("</table>");
            out.println("<style>");
        out.println("#tesu {border-color: white; border-style: double; border-spacing:20px; color: white; font-size: large; font-weight: 200}");
        out.println("#teb{ background-image: url(logo2.jpg); width: 110px; height: 30px; color: white; font-size: large; font-weight: 200;}");
        out.println("</style>");
            

        } catch (NullPointerException|SQLException ex) {
           
               
               
                out.println("Error1:"+ex);
                out.println("<a href=Start>Back</a>");
               
           
        }
 }

 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        footer(request, response);
    }

    
      
    int id;
    int n;
    int setr(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         Connection con = null;
        PreparedStatement pr = null;
        ResultSet rs = null;
       
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:Xe", "HCL", "qwerty");
        } catch (ClassNotFoundException | SQLException ex) {
             out.println("ERROR:"+ex);
       }
         try {
                pr = con.prepareStatement("Select * from ITEM order by SNO");
                rs = pr.executeQuery();
           while(rs.next())
            {
                out.println("<tr>");
                n= rs.getRow();
            }
            } catch (SQLException ex) {
                Logger.getLogger(TodayEntry.class.getName()).log(Level.SEVERE, null, ex);
            }
    return n;
    }
    String sq;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        
        processRequest(request,response);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
         String nameofbut= request.getParameter("b1");
         
         Connection con = null;
        PreparedStatement pr = null;
        ResultSet rs = null;
        
        
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:Xe", "HCL", "qwerty");
        } catch (ClassNotFoundException | SQLException ex) {
             out.println("ERROR:"+ex);
       }

        if(nameofbut.equals("BACK"))
        {
            response.sendRedirect("Start");
        }
        else  if(nameofbut.equals("BALANCE"))
        {
             HttpSession ss= request.getSession();
            String total= (String)ss.getAttribute("totalAmount");
            ss.setAttribute("total",total);
            response.sendRedirect("BalanceSheet");
        }
        else if(nameofbut.equals("LOGOUT"))
        {
            response.sendRedirect("LogOut");
        }
        else if(nameofbut.equals("ADDNEW"))
        {
            HttpSession ss= request.getSession();
            sq = (String)ss.getAttribute("s1");
            ss.setAttribute("s1", sq);
            response.sendRedirect("Addnew");
            
        }
        else if(nameofbut.equals("UPDATE"))
        {
             id= Integer.parseInt(request.getParameter("chk"));
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
        
    footer(request,response);
        
    }
 int product(HttpServletRequest request, HttpServletResponse response)
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
            
            
                out.println("Error: "+ex+"<br>");
                out.println("<a href=Start>Back</a>");
               
       }
        String yourDate = currentDayInt+"/"+"0"+currentMonthInt+"/"+currentYearInt;
        
        int w = 0;
        try{
      pr = con.prepareStatement("Select QTY,COST from ITEM,DATAITEM where ITEM.SNO=DATAITEM.SNO");
            rs = pr.executeQuery();
           while(rs.next())
           {
               int x= rs.getInt("QTY");
               int y= rs.getInt("COST");
               w= w+(x*y);
               
           }
           return w;
              } catch (NullPointerException|SQLException ex) {
           out.println("Error:"+ex);
                out.println("<a href=Start>Back</a>");
 }
        return w;
 }
   
    
}
