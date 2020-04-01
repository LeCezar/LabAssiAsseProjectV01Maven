package org.example;

import org.example.domain.Nota;
import org.example.domain.Student;
import org.example.domain.Tema;
import org.example.repository.NotaXMLRepository;
import org.example.repository.StudentXMLRepository;
import org.example.repository.TemaXMLRepository;
import org.example.service.Service;
import org.example.validation.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest {


    Validator<Student> studentValidator = new StudentValidator();
    Validator<Tema> temaValidator = new TemaValidator();
    Validator<Nota> notaValidator = new NotaValidator();

    StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
    TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
    NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

    Service service = new Service(fileRepository1, fileRepository2, fileRepository3);


    @Test
    public void testIdForNull() {
        String idStudent = null;
        int result = service.saveStudent(idStudent,"Nume",935);
        assertEquals("Null id test", 1, result);
    }

    @Test
    public void testStudentNameForNull() {
        String studentName = null;
        int result = service.saveStudent("1",studentName,935);
        assertEquals("Student name null", 1, result);
    }

    @Test
    public void testAddTemaIdNull(){
        String temaId = null;
        int result = service.saveTema(temaId,"sad",3,2);
        assertEquals("Id of Tema null",1,result);
    }

    @Test
    public void testAddTemaDeadline(){
        int deadline = 0;
        int result = service.saveTema("2","sad",deadline,2);
        assertEquals("Deadline invalid, should be between 1 and 14 and higher than startline",1,result);
    }
}
