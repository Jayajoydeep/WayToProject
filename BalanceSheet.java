/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CountYourBalance;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jaya kumari
 */
public class BalanceSheet extends HttpServlet {

   Calendar theCal = Calendar.getInstance();
  int currentYearInt  = theCal.get(Calendar.YEAR);
  int currentMonthInt = theCal.get(Calendar.MONTH)+1;
  int currentDayInt   = theCal.get(Calendar.DATE);
    private String String;
  
    TodayEntry td= new TodayEntry();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ss= request.getSession();
        String ne= (String)ss.getAttribute("total");
        int n= Integer.parseInt(ne);
        int re=td.product(request, response);
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
                out.println(currentDayInt+"/"+currentMonthInt+"/"+currentYearInt+"");
                
                out.println("<input type=submit name=b1 value=BACK id=bk>");
            out.println("<input type=submit name=b1 value=LOGOUT id=lou>");
            out.println("</p>");
            out.println("</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td align=center id=jk>");
            out.println("Total Monthly Income:"+n+"<br>");
            out.println("Expense of today: "+td.product(request, response)+"<br>");
            int Balance= n-re;
             out.println("Balance: "+Balance);
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
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        PrintWriter out = response.getWriter();
         String nameofbut= request.getParameter("b1");
        if(nameofbut.equals("BACK"))
        {
            response.sendRedirect("Start");
        }else if(nameofbut.equals("LOGOUT"))
        {
            response.sendRedirect("LogOut");
        }
    }
  
}
