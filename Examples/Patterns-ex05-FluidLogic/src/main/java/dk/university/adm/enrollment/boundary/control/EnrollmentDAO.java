/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dk.university.adm.enrollment.boundary.control;


import dk.university.adm.enrollment.boundary.model.Student;
import jakarta.ejb.Stateless;

import java.util.Random;

/**
 *
 * @author jakob
 * 
 * Shows a Control component.
 */
@Stateless
public class EnrollmentDAO extends AbstractDAOControl<Student> {
     
    public EnrollmentDAO() {
    	super(Student.class);
	}
    
    public Student enroll(Student student) {
    	if (student.getStudentnumber()!=null) {
			throw new IllegalArgumentException("Student already has a student number and cannot be enrolled!");
		}
		if (student.getId()!=null) {
			throw new IllegalArgumentException("Student already has an ID (" + student.getId() + ")");
		}
		student.setStudentnumber(getStudentnumber());
		this.create(student);
		
		return student;

	}
    
	//simulates creation of a student number with 10 digits, zero padded
	private String getStudentnumber() {
		return String.format("%010d", new Random().nextInt(1000000000));
	}

}
