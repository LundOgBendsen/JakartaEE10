package dk.lundogbendsen.service;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Employee {
    @NotNull(message = "Employee.id must not be null")
    private String id;
    @Size(min = 5, message = "Employee.name must be at least 5 characters")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
