
package CountYourBalance;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jaya kumari
 */
public class Home extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private int month;
  private int year;
  private int days[][];
  private int numberOfWeeks;
         Calendar theCal = Calendar.getInstance();
  int currentYearInt  = theCal.get(Calendar.YEAR);
  int currentMonthInt = theCal.get(Calendar.MONTH);
  int currentDayInt   = theCal.get(Calendar.DATE);
  String currentYearString  = Integer.toString(currentYearInt);
  String currentMonthString = Integer.toString(currentMonthInt);
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        year=currentYearInt;
        month=currentMonthInt;
        String monthNames[] = {"January",
                         "February",
                         "March",
                         "April",
                         "May",
                         "June",
                         "July",
                         "August",
                         "September",
                         "October",
                         "November",
                         "December" };
         Calendar c = Calendar.getInstance();
    c.setFirstDayOfWeek(1);
    c.set(year, month, 1);
    for (; c.get(2) == month; c.add(5,1))
    {
      int weekNumber = c.get(4) - 1;
      int dayOfWeek = calculateDay(c.get(7));
      days[weekNumber][dayOfWeek] = c.get(5);
      numberOfWeeks = weekNumber;
    }
     days = getDays();
     out.println("<table border=1>");
     out.println("<tr>");
     out.println("<td colspan=7>");
     out.println(monthNames[month]+","+year);
     out.println("</td>");
     out.println("</tr>");
     out.println("<tr>");
    out.println("<th width=14%>Sun</th>");
    out.println("<th width=14%>Mon</th>");
    out.println("<th width=14%>Tue</th>");
    out.println("<th width=14%>Wed</th>");
    out.println("<th width=14%>Thu</th>");
    out.println("<th width=15%>Fri</th>");
    out.println("<th width=15%>Sat</th>");
 out.println(" </tr>");
     out.println("<tr>");
  for( int i=0; i<getNumberOfWeeks(); i++ )
  {
    
    for( int j=0; j<7; j++ )
    {
      if( days[i][j] == 0 )
      {
          
        out.println("<td class=empty_day_cell>&nbsp;</td>");
      }
      else
      {
        // this is "today"
        if( currentDayInt == days[i][j] && currentMonthInt == getMonth() && currentYearInt == getYear() )
        {
          out.println("<td class=today_cell>"+days[i][j]+"</td>");
        }
        else
        {
          out.println("<td class=day_cell>"+days[i][j]+"</td>");
        }
      } // end outer if
    } // end for
    }
  out.println("</tr>");
  out.println("</table>");
        
    }
      
    public int getMonth()
  {
    return month;
  }
    
     public int[][] getDays()
  {
    return days;
  }
     
      public int getNumberOfWeeks()
  {
    return numberOfWeeks + 1;
  }

  public int getYear()
  {
    return year;
  }


    
    public int calculateDay(int day)
  {
    if (day == 1)
      return 0;
    if (day == 2)
      return 1;
    if (day == 3)
      return 2;
    if (day == 4)
      return 3;
    if (day == 5)
      return 4;
    if (day == 6)
      return 5;
    return day != 7 ? 7 : 6;
  }
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 processRequest(request, response);
      
    }
    
    
    
    
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
