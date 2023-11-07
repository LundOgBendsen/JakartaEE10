package dk.lundogbendsen.client;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.InvocationCallback;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Form;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import dk.lundogbendsen.service.Employee;

import static jakarta.ws.rs.client.Entity.*;

public class RestClient {
    private final static String BASE_URL = "http://localhost:8080/JAX-RS-Ex-ClientAPI-1.0-SNAPSHOT/resources";

    public static void main(String[] args) throws InterruptedException {
        ClientBuilder cb = ClientBuilder.newBuilder();
        Client client = cb.build();

        ex01(client);
        ex02(client);
        ex03(client);
        ex04(client);
        ex05(client);
        ex06(client);
        ex07(client);
        ex08(client);
        ex09(client);
        ex10(client);
    }

    private static void ex01(Client client) {
        System.out.println("ex01--------------");
        Response res = client
                .target(BASE_URL + "/employees/Roy-Fielding")
                .request("text/plain").get();
        System.out.println(res.readEntity(String.class));
        res.close();
    }

    private static void ex02(Client client) {
        System.out.println("ex02--------------");
        Response res = client
                .target(BASE_URL + "/employees/Roy-Fielding")
                .request(MediaType.TEXT_PLAIN).get();
        System.out.println(res.readEntity(String.class));
        res.close();
    }

    private static void ex03(Client client) {
        System.out.println("ex03--------------");
        Response res = client
                .target(BASE_URL + "/employees/Roy-Fielding")
                .request(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
                .get();
        System.out.println(res.readEntity(String.class));
        res.close();
    }

    private static void ex04(Client client) {
        System.out.println("ex04--------------");
        Response res = client
                .target(BASE_URL + "/resources/employees")
                .queryParam("find", "Roy*").request(MediaType.APPLICATION_JSON)
                .header("version", "1.0").get();
        System.out.println(res.readEntity(String.class));
        res.close();
    }

    private static void ex05(Client client) {
        System.out.println("ex05--------------");
        WebTarget base = client
                .target(BASE_URL);
        WebTarget employees = base.path("employees");
        WebTarget target = employees.path("{first}-{last}")
                .resolveTemplate("first", "Roy")
                .resolveTemplate("last", "Fielding");
        Response res = target.request().get();
        System.out.println(res.readEntity(String.class));
        res.close();
    }

    private static void ex06(Client client) {
        System.out.println("ex06--------------");
        Employee emp = client
                .target(BASE_URL + "/employees/42")
                .request(MediaType.APPLICATION_XML).get(Employee.class);
        System.out.println("read employee: " + emp.getId() + ", "
                + emp.getName());

        Response post = client
                .target(BASE_URL + "/employees/managers")
                .request().post(xml(emp));

        System.out.println("moved employee to managers: " + emp.getId() + ", "
                + emp.getName() + ". Status: " + post.getStatus());
    }

    private static void ex07(Client client) {
        Form form = new Form();
        form = form.param("tx_valIBAN_pi1[iban]", "DK7253030009685367")
                .param("tx_valIBAN_pi1[fi]", "fi").param("no_cache", "1")
                .param("Action", "validate IBAN");
        Response post = client
                .target("http://www.ibancalculator.com/iban_validieren.html")
                .request().post(form(form));
        String readEntity = post.readEntity(String.class);
        System.out.println(readEntity);
    }

    private static void ex08(Client client) {
        System.out.println("ex08--------------");
        Invocation invocation = client
                .target(BASE_URL + "/employees/42")
                .request(MediaType.APPLICATION_XML).buildGet();

        // this invocation is now ready for submission. We can send it somewhere
        // else to be submitted.
        Employee emp = doInvoke(invocation);

        System.out.println("read employee: " + emp.getId() + ", "
                + emp.getName());
    }

    private static Employee doInvoke(Invocation invocation) {
        return invocation.invoke(Employee.class);
    }

    private static void ex09(Client client) {
        System.out.println("ex09--------------");
        Future<Employee> future = client
                .target(BASE_URL + "/employees/42")
                .request(MediaType.APPLICATION_XML).async().get(Employee.class);

        Employee emp;
        try {
            emp = future.get(2000, TimeUnit.MILLISECONDS);
            System.out.println("read employee: " + emp.getId() + ", "
                    + emp.getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    //async client features
    private static void ex10(Client client) throws InterruptedException {
        System.out.println("ex10--------------");
        Future<Employee> future = client
                .target(BASE_URL + "/employees/42")
                .request(MediaType.APPLICATION_XML).async()
                .get(new InvocationCallback<Employee>() {
                    @Override
                    public void completed(Employee emp) {
                        System.out.println("read employee: " + emp.getId()
                                + ", " + emp.getName());
                    }

                    @Override
                    public void failed(Throwable throwable) {
                        throw new RuntimeException(throwable);
                    }
                });
        Thread.sleep(2000);
        // After waiting a while ...
        if (!future.isDone()) {
            future.cancel(true);
        }
        client.close();
    }
}
