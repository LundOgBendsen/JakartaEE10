package dk.universitet.adm.indskrivning.boundary.rest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

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

import dk.universitet.adm.indskrivning.control.IndskrivningDAO;

//FluidLogic: SLSB er et oplagt valg
@Stateless 
@Path("rapport/")
public class Rapport {
	//FluidLogic: vi scripter med JavaScript - det eneste sprog der skal være understøttet.
	private static final String ENGINE_NAME = "JavaScript";
	private ScriptEngine scriptEngine = null;
	private final static String header = "<html><body><h1>Resultat</h1><p>";
	private final static String footer = "</p><p><a href=\"/Patterns-ex05-FluidLogic/rapport.xhtml\">Tilbage</a></p></body></html>";
	
	@Inject
	IndskrivningDAO dao;

	@PostConstruct
	public void initScripting() {
		//FluidLogic: opret en JSR-223 ScriptEngine
		ScriptEngineManager engineManager = new ScriptEngineManager();
		this.scriptEngine = engineManager.getEngineByName(ENGINE_NAME);
	}

	//FluidLogic: modtag script fra klienten
	@POST 
	@Produces( MediaType.TEXT_HTML) 
	public String rapport(@FormParam("expression") String script) { 
		Object retVal = null; 
		try { 			
			//bind java-objekter, så de er tilgængelige for scriptet. 
			Bindings binding = this.scriptEngine.createBindings(); 
			binding.put("PI", 3.14); 
			binding.put("E", 2.71828);		
			binding.put("studenter", dao.findAll());
			
			//opfang print()-output fra ScriptEngine
			MyWriter myWriter = new MyWriter();
			scriptEngine.getContext().setWriter(myWriter);

			//evalu+er script. 
			retVal = this.scriptEngine.eval( script, binding);
			if (retVal!=null) {
				//Brug returværdi
				return header + retVal.toString() + footer; 
			} else {
				//ellers brug print()-statements' output.
				return header + new String(myWriter.baos.toByteArray()) + footer; 
			}
		} catch (Exception e) { 
			e.printStackTrace();
		} 
		return "N/A";	
	}
}

/*
 * En writer der skriver til en buffer hvorfra resultatet kan opsamles.
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