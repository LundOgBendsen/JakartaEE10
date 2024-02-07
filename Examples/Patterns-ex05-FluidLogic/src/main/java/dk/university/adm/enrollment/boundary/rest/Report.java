package dk.university.adm.enrollment.boundary.rest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import dk.university.adm.enrollment.boundary.control.EnrollmentDAO;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


//FluidLogic: SLSB er et oplagt valg
@Stateless 
@Path("report/")
public class Report {
	//FluidLogic: using JavaScript for scripting
	private static final String ENGINE_NAME = "JavaScript";
	private ScriptEngine scriptEngine = null;
	private final static String header = "<html><body><h1>Result</h1><p>";
	private final static String footer = "</p><p><a href=\"/Patterns-ex05-FluidLogic-1.0-SNAPSHOT/report.xhtml\">Back</a></p></body></html>";
	
	@Inject
	EnrollmentDAO dao;

	@PostConstruct
	public void initScripting() {
		//FluidLogic: create a JSR-223 ScriptEngine
		ScriptEngineManager engineManager = new ScriptEngineManager();
		this.scriptEngine = engineManager.getEngineByName(ENGINE_NAME);
	}

	//FluidLogic: modtag script fra klienten
	@POST 
	@Produces( MediaType.TEXT_HTML) 
	public String rapport(@FormParam("expression") String script) { 
		Object retVal = null; 
		try { 			
			//bind java objects, so they are accessible from script.
			Bindings binding = this.scriptEngine.createBindings(); 
			binding.put("PI", 3.14); 
			binding.put("E", 2.71828);		
			binding.put("students", dao.findAll());
			
			//intercept print outs from ScriptEngine
			MyWriter myWriter = new MyWriter();
			scriptEngine.getContext().setWriter(myWriter);

			//eval script.
			retVal = this.scriptEngine.eval( script, binding);
			if (retVal!=null) {
				//use return value
				return header + retVal.toString() + footer; 
			} else {
				//or use print()-statements' output.
				return header + new String(myWriter.baos.toByteArray()) + footer; 
			}
		} catch (Exception e) { 
			e.printStackTrace();
		} 
		return "N/A";	
	}
}

/*
 * a writer that write to a buffer where the result kan be taken from
 */
class MyWriter extends Writer {

	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	Writer w = new OutputStreamWriter(baos);	
	
	
	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		w.write(cbuf, off, len);
	}

	@Override
	public void flush() throws IOException {
		w.flush();
		
	}

	@Override
	public void close() throws IOException {
		w.close();
	}
}