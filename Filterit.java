/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CountYourBalance;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 * @author Jaya kumari
 */
public class Filterit implements Filter {
  ServletConfig conf;
    private FilterConfig filterConfig = null;
    
    public Filterit() {
    } 
    
 private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        

    }    
    
    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
       

    }

   
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
       System.out.println("THis is filter");
        
        doBeforeProcessing(request, response);
        
        Throwable problem = null;
        try {
            chain.doFilter(request, response);
        } catch (Throwable t) {
	    // If an exception is thrown somewhere down the filter chain,
            // we still want to execute our after processing, and then
            // rethrow the problem after that.
            problem = t;
        }
        
        doAfterProcessing(request, response);

	// If there was a problem, we want to rethrow it if it is
        // a known type, otherwise log it.
        if (problem != null) {
            if (problem instanceof ServletException) {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) {
                throw (IOException) problem;
            }
            
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    
    
    @Override
    public void destroy() {        
    }

    /**
     * Init method for this filter
     */
    @Override
    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
           System.out.println("THis is filter");
        }
    }

    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("TrimFilter()");
        }
        StringBuffer sb = new StringBuffer("TrimFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
 
       
}
