package com.recipes.batch.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.annotation.Resource;
import jakarta.batch.operations.JobOperator;
import jakarta.batch.operations.JobSecurityException;
import jakarta.batch.operations.JobStartException;
import jakarta.batch.runtime.BatchRuntime;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.UserTransaction;

@WebServlet(urlPatterns = { "/IngredientTxServlet" })
public class IngredientTxServlet extends HttpServlet
{
  @Inject
  Logger log;

  @Resource
  UserTransaction tx;

  protected void processRequest(@SuppressWarnings("unused") final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException
  {
    response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter())
    {
      out.println("<html>");
      out.println("<head>");
      out.println("<title>CSV-to-Database Ingredient Tx Job</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>CSV-to-Database Ingredient Batch Job</h1>");

      JobOperator jo = BatchRuntime.getJobOperator();
      try
      {
        tx.begin();
        long jid = jo.start("IngredientTxJob", new Properties());
        tx.commit();
        out.println("<p>Ingredient Tx Job was submitted just now: " + jid + "</p>");

      }
      catch (Exception e)
      {
        out.println("<p>Ingredient Tx Job could not be submitted</p>");

      }
      out.println("<p>Check server.log for output.</p>");
      out.println("<p>See <a href=\"./list-ingredients.jsf\">list-ingredient page</a></p>");

      out.println("</body>");
      out.println("</html>");
    }
    catch (JobStartException | JobSecurityException ex)
    {
      log.log(Level.SEVERE, null, ex);
    }
  }

  @Override
  protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException
  {
    processRequest(request, response);
  }

  @Override
  protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException
  {
    processRequest(request, response);
  }

  @Override
  public String getServletInfo()
  {
    return "Short description";
  }
}
