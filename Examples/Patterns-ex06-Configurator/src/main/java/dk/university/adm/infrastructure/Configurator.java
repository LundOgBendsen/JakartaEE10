package dk.university.adm.infrastructure;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.AnnotatedField;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.inject.Singleton;


@Dependent
public class Configurator {
	private static Logger log = Logger.getLogger(Configurator.class.getName());

	//Configurator: config is stored in a map (config key -> config value)
	private Map < String, String > configuration; 

	//Configurator: called when config is injected
	@Produces
	public String getString( InjectionPoint ip ) { 
		String configurableName = obtainConfigurableName(ip);
		//see if config key exists
		String configuration = this.configuration.get(configurableName);
		//if not...
		if (configuration==null) {
			//...then return default value from attribute
			Configurable configurable = getConfigurableAnnotation(ip); 
			configuration = configurable.defaultValue();
			log.info("No value found for " + configurableName + ", using annotation's default value ");
		}
		return configuration;
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
		Configurable configurable = field.getAnnotation(Configurable.class);
		return configurable;
	}
	
	/*
	 * Her bygges et HashMap med konfigurationsværdier. De kunne alternativt indlæses fra en fil.
	 */
	@PostConstruct 
	public void fetchConfiguration() { 
		this.configuration = new HashMap < String, String >() {
			{ 
				put("greetings", "greetings - value from Configurator ");
				put("dk.university.adm.enrollment.boundary.Enrollment.text", "a text value from Configurator");
			}
		};
	}
}








