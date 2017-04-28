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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jaya kumari
 */
public class Addnew extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
  
   // ****************************************************************************************************************************
TodayEntry td= new TodayEntry();
    int s=1,i;
    Date d= new Date();
    int id;
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    void baseEntry(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
 {
      response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        int l=td.setr(request, response);
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
                out.println(dateFormat.format(d));
                
                    out.println("<input type=submit name=b1 value=BACK id=adbk>");
            out.println("<input type=submit name=b1 value=LOGOUT id=adlou>");
            out.println("</p>");
            out.println("</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td align=center id=adjk>");
             out.println("<table id=adsu>");
         out.println("<tr>");
            out.println("<td colspan=5>");
            out.println("ITEM ID: <input type=text name=itemid value="+(++l)+"><br>");
            out.println("ITEM NAME: <input type=text name=itemname><br>");
            out.println("QUANTITY: <input type=text name=qty value=1><br>");
            out.println("COST per item: <input type=text name=cost><br>");
            out.println("<input type=submit name=b1 value=ADD>");
            out.println("</td>");
            out.println("</tr>");
        out.println("</table>");
        out.println("<style>");
        out.println("#adsu {border-color: white; border-spacing:20px; color: white; font-size: x-large; font-weight: 400}");
        out.println("</style>");
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

    int insertinto(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
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
                out.println("<table align=center id=kj>");
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
   

String iid= request.getParameter("itemid");
String iname= request.getParameter("itemname");
String iqty= request.getParameter("qty");
String icost= request.getParameter("cost");
id= Integer.parseInt(iid);
int qty= Integer.parseInt(iqty);
int cost= Integer.parseInt(icost);


            try{
                pr= con.prepareStatement("Insert into item values(?,?,?,?)");
                pr.setInt(1, id);
                pr.setString(2,iname);
                pr.setInt(3, qty);
                pr.setInt(4, cost);
                
                i= pr.executeUpdate();
                
                
            }catch(SQLException ex)
            {
                 
                out.println("Error: "+ex+"<br>");
               out.println("<a href=Start>Back</a>");
            }
       return i;     

 }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       baseEntry(request,response);
       //footer(request,response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
         String nameofbut= request.getParameter("b1");
          baseEntry(request,response);
            if(nameofbut.equals("BACK"))
        {
            response.sendRedirect("TodayEntry");
        }
        else if(nameofbut.equals("LOGOUT"))
        {
            response.sendRedirect("LogOut");
        }
        else if(nameofbut.equals("ADD")){
         int y=insertinto(request,response);
                  if(y==1)
         {
        DateEntry(request,response);
         }
          }
        //footer(request,response);
    }
void DateEntry(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
   //get current date time with Date()
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
         HttpSession ss= request.getSession();
            String usid=(String)ss.getAttribute("s1");
            
   Date date = new Date();
//   out.println(dateFormat.format(date)); //don't print it, but save it!
   String yourDate = dateFormat.format(date);

            
            try{
                 
                 pr= con.prepareStatement("Insert into dataitem values(?,?,?)");
                 pr.setString(1, yourDate);
                pr.setInt(2, id);
                pr.setString(3,usid);
                
                
                int i= pr.executeUpdate();
                if(i==1)
                {
                    response.sendRedirect("TodayEntry");
                }
                else
                {
                    baseEntry(request,response);
                    out.println("ADD DATE AGAIN");
                } 
                
            }catch(SQLException ex)
            {
              out.println("Error: "+ex+"<br>");
               out.println("<a href=Start>Back</a>");
            }
            
    }

    
}
