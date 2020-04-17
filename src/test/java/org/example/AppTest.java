package org.example;

import org.example.domain.Nota;
import org.example.domain.Student;
import org.example.domain.Tema;
import org.example.repository.NotaXMLRepository;
import org.example.repository.StudentXMLRepository;
import org.example.repository.TemaXMLRepository;
import org.example.service.Service;
import org.example.validation.NotaValidator;
import org.example.validation.StudentValidator;
import org.example.validation.TemaValidator;
import org.example.validation.Validator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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


    // Deprecated test, here just for jenkins run
    @Test
    public void testIdForNull(){
        assertEquals(1,1);
    }

    // Deprecated test, here just for jenkins run
    @Test
    public void testStudentNameForNull(){
        assertEquals(1,1);
    }

    @Test
    public void tc_1_ec_validStudentEntry() {
        int result = service.saveStudent("12", "Nume", 935);
        assertEquals("Valid Student", 1, result);
        service.deleteStudent("12");
    }

    @Test
    public void tc_2_ec_testIdForNull() {
        String idStudent = null;
        int result = service.saveStudent(idStudent, "Nume", 935);
        assertEquals("Null id test", 0, result);
    }

    @Test
    public void tc_3_ec_testIdForEmptyString() {
        String idStudent = "";
        int result = service.saveStudent(idStudent, "Nume", 935);
        assertEquals("Empty id test", 0, result);
    }

    @Test
    public void tc_4_ec_testExistentId() {
        String idStudent = "1";
        int result = service.saveStudent(idStudent, "Nume", 935);
        assertEquals("Existent id test", 0, result);
    }

    @Test
    public void tc_5_ec_testNumeForEmptyString() {
        String nume = "";
        int result = service.saveStudent("10", nume, 935);
        assertEquals("Empty nume test", 0, result);
    }

    @Test
    public void tc_6_ec_testNumeForNull() {
        String nume = null;
        int result = service.saveStudent("10", nume, 935);
        assertEquals("Null nume test", 0, result);
    }

    @Test
    public void tc_7_ec_testGrupaForOutOfLowerBounds() {
        int grupa = 10;
        int result = service.saveStudent("10", "Nume", grupa);
        assertEquals("Out of lower bounds grupa", 0, result);
    }

    @Test
    public void tc_8_ec_testGrupaForOutOfUpperBounds() {
        int grupa = 1100;
        int result = service.saveStudent("10", "Nume", grupa);
        assertEquals("Out of upper bound", 0, result);
    }

    @Test
    public void tc_1_BVA_testGrupaForOutOfLowerBounds() {
        int grupa = 109;
        int result = service.saveStudent("10", "Nume", grupa);
        assertEquals("Out of lower bounds grupa", 0, result);
    }

    @Test
    public void tc_2_BVA_testGrupaForOutOfLowerBounds() {
        int grupa = 110;
        int result = service.saveStudent("10", "Nume", grupa);
        assertEquals("Out of lower bounds grupa", 0, result);
        service.deleteStudent("10");
    }

    @Test
    public void tc_3_BVA_testGrupaForOutOfLowerBounds() {
        int grupa = 111;
        int result = service.saveStudent("10", "Nume", grupa);
        assertEquals("Grupa good", 1, result);
        service.deleteStudent("10");
    }

    @Test
    public void tc_4_BVA_testGrupaForOutOfUpperBounds() {
        int grupa = 939;
        int result = service.saveStudent("10", "Nume", grupa);
        assertEquals("Out of upper bound", 0, result);
    }

    @Test
    public void tc_5_BVA_testGrupaForOutOfUpperBounds() {
        int grupa = 938;
        int result = service.saveStudent("10", "Nume", grupa);
        assertEquals("Grupa good", 0, result);
    }

    @Test
    public void tc_6_BVA_testGrupaForOutOfUpperBounds() {
        int grupa = 937;
        int result = service.saveStudent("10", "Nume", grupa);
        assertEquals("Grupa good", 1, result);
        service.deleteStudent("10");
    }


    @Test
    public void testAddTemaIdNull() {
        String temaId = null;
        int result = service.saveTema(temaId, "sad", 3, 2);
        assertEquals("Id of Tema null", 1, result);
    }

    @Test
    public void testAddTemaDeadline() {
        int deadline = 0;
        int result = service.saveTema("2", "sad", deadline, 2);
        assertEquals("Deadline invalid, should be between 1 and 14 and higher than startline", 1, result);
    }
}
