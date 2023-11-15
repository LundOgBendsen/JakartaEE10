package dk.lundogbendsen.batch;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.annotation.Resource;
import jakarta.batch.operations.JobOperator;
import jakarta.batch.operations.JobSecurityException;
import jakarta.batch.operations.JobStartException;
import jakarta.batch.runtime.BatchRuntime;
import jakarta.batch.runtime.JobExecution;
import jakarta.batch.runtime.JobInstance;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.UserTransaction;

@WebServlet(urlPatterns = { "/JobsServlet" })
public class JobsServlet extends HttpServlet
{

  @PersistenceUnit
  EntityManagerFactory em;

  @Resource
  UserTransaction tx;

  protected void processRequest(@SuppressWarnings("unused") final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException
  {
    response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter())
    {
      out.println("<html>");
      out.println("<head>");
      out.println("<title>Job List</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>Job List</h1>");

      JobOperator jo = BatchRuntime.getJobOperator();

      out.println("<p>Jobs</p><table>");
      out.println("<tr>");
      out.println("<th> Instance Id </td>");
      out.println("<th> Job Name </td>");
      out.println("<th> Execution Id </td>");
      out.println("<th> Create Time </td>");
      out.println("<th> Start Time </td>");
      out.println("<th> End Time </td>");
      out.println("<th> Batch Status </td>");
      out.println("<th> Exit Status </td>");
      out.println("<th> Last Updated Time </td>");
      out.println("</tr>");

      List<JobInstance> jilist = jo.getJobInstances("stockTxJob", 0, 100);
      for (JobInstance ji : jilist)
      {
        List<JobExecution> jelist = jo.getJobExecutions(ji);
        for (JobExecution je : jelist)
        {
          out.println("<tr>");
          out.println("<td>" + ji.getInstanceId() + "</td>");
          out.println("<td>" + ji.getJobName() + "</td>");
          out.println("<td>" + je.getExecutionId() + "</td>");
          out.println("<td>" + je.getCreateTime() + "</td>");
          out.println("<td>" + je.getStartTime() + "</td>");
          out.println("<td>" + je.getEndTime() + "</td>");
          out.println("<td>" + je.getBatchStatus() + "</td>");
          out.println("<td>" + je.getExitStatus() + "</td>");
          out.println("<td>" + je.getLastUpdatedTime() + "</td>");
          out.println("</tr>");
        }
      }
      out.println("</table>");

      out.println("<br/><br/><br/>");

      out.println("<br><br>Check server.log for output<br>Look at \"stockTxJob.xml\" for Job XML.");
      out.println("</body>");
      out.println("</html>");
    }
    catch (JobStartException | JobSecurityException ex)
    {
      Logger.getLogger(StockTxServlet.class.getName()).log(Level.SEVERE, null, ex);
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
