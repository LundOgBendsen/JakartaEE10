package dk.lundogbendsen.client;

import static jakarta.ws.rs.client.Entity.xml;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import dk.lundogbendsen.service.Employee;

public class RestClient {

	public static void main(String[] args) throws InterruptedException {
		ClientBuilder cb = ClientBuilder.newBuilder();
		Client client = cb.build();

		System.out.println("Bean Validation example client--------------");

		// a valid request
		Employee emp = client.target("http://localhost:8080/JAX-RS-Ex-BeanValidation-1.0-SNAPSHOT/resources/employees/42")
				.request(MediaType.APPLICATION_XML).get(Employee.class);
		System.out.println("1. (valid) get employee: " + emp.getId() + ", " + emp.getName());

		// invalid
		Response response = client.target("http://localhost:8080/JAX-RS-Ex-BeanValidation-1.0-SNAPSHOT/resources/employees/aa")
				.request(MediaType.APPLICATION_XML).get();
		System.out.println("2. (invalid) Response status: " + response.getStatus() + ", " + response.getStatusInfo().getReasonPhrase());

		//valid
		Response post = null;
		post = client.target("http://localhost:8080/JAX-RS-Ex-BeanValidation-1.0-SNAPSHOT/resources/employees/managers").request()
				.post(xml(emp));
		System.out.println("3. (valid) Response status: " + post.getStatus());

		//invalid
		post = null;
		emp.setId(null);
		post = client.target("http://localhost:8080/JAX-RS-Ex-BeanValidation-1.0-SNAPSHOT/resources/employees/managers").request()
				.post(xml(emp));
		System.out.println("4. (invalid) Response status: " + post.getStatus() + ", " + post.getStatusInfo().getReasonPhrase());

	}
}
