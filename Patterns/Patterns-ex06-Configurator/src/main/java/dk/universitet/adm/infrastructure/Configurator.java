package dk.universitet.adm.infrastructure;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.AnnotatedField;
import jakarta.enterprise.inject.spi.InjectionPoint;


public class Configurator {
	private static Logger log = Logger.getLogger(Configurator.class.getName());
	//Configurator: vi bruger et Map til konfigurationen (konfigurationsnøgle-> konfigurationsværdi)
	private Map < String, String > configuration; 

	//Configurator: denne metode injecter konfiguration 
	@Produces 
	public String getString( InjectionPoint ip ) { 
		String configurableName = obtainConfigurableName(ip);
		//se om konfigurationsnøglen findes
		String konfiguration = configuration.get(configurableName);
		//hvis der ikke findes en konfigurationsværdi for konfigurationsnøglen?
		if (konfiguration==null) {
			//så returner defaultværdien fra annotationen
			Configurable configurable = getConfigurableAnnotation(ip); 
			konfiguration = configurable.defaultValue();
			log.info("INFO: ingen værdi fundet for " + configurableName + ", bruger defaultværdi fra annotation");
		}
		return konfiguration;
	}
	
	
	String obtainConfigurableName( InjectionPoint ip) { 
		Configurable configurable = getConfigurableAnnotation(ip); 
		if( configurable != null){ 
			return configurable.key(); 
		}else{ 
			String clazzName = ip.getMember().getDeclaringClass().getName(); 
			String memberName = ip.getMember(). getName(); 
			String fqn = clazzName + "." + memberName;
			return fqn;
		}
	}

	private Configurable getConfigurableAnnotation(InjectionPoint ip) {
		AnnotatedField field = (AnnotatedField) ip.getAnnotated(); 
		Configurable configurable = field.getAnnotation( Configurable.class);
		return configurable;
	}
	
	/*
	 * Her bygges et HashMap med konfigurationsværdier. De kunne alternativt indlæses fra en fil.
	 */
	@PostConstruct 
	public void fetchConfiguration() { 
		this.configuration = new HashMap < String, String >() {
			{ 
				put("greetings", "greetings - værdi fra Configurator "); 
				put("dk.universitet.adm.indskrivning.boundary.Indskrivning.tekst", "en tekst  - værdi fra Configurator");
			}
		};
	}
}








