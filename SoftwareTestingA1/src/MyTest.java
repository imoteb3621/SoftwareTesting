
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MyTest {
    private Student validStudent;
    private Student notValidStudent;
    private Book book;

    @Before
    public void setUp(){
        book = new Book();
        validStudent = mock(Student.class);
        notValidStudent = mock(Student.class);
        when(notValidStudent.isMember()).thenReturn(false);
        when(notValidStudent.hasNoPenalty()).thenReturn(false);

        when(validStudent.isMember()).thenReturn(true);
        when(validStudent.hasNoPenalty()).thenReturn(true);


        book.setBookName("Java Fundamentals");
        book.setIBSN("123456");

    }
    @Test(expected = IllegalArgumentException.class)
    public void testSetStudentNotValid(){
        book.setStudent(notValidStudent);
    }
    @Test
    public void testPenalty(){
        Student student = new Student(book);
        student.setPenalty(2);
        assertEquals(true, student.hasNoPenalty());
    }
    @Test
    public void testMember(){
        book.setStudent(validStudent);
        verify(validStudent).isMember();

    }
    @Test
    public void testWhoLoanedTheBook(){
        Book book = new Book();
        Student student = new Student(book);
        student.setStudent("Taner");
        student.setUniversity("Linneaus University");
        book.setStudent(student);

        assertEquals("Taner", book.loanedBy(book));
    }

}
