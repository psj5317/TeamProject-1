package Prototype;

/**
 * Student 클래스의 설명을 작성하세요.
 *
 * @author (작성자 이름)
 * @version (2026.05.10)
 */

public class Student
{
    private String studentName;     //학생명
    private int studentID;         //학번
    private Course[] studentCourse; //수강과목

    public Student(String studentName, int studentID, Course[] studentCourse) {
        this.studentName = studentName;
        this.studentID = studentID;
        this.studentCourse = studentCourse;
    }

    //getStudentName : 이름 출력
    public String getStudentName() {
        return studentName;
    }

    //getStudentNum : 학번 출력
    public int getStudentID() {
        return studentID;
    }

    //getStudentCourse : 수강중인 과목 출력
    public Course[] getStudentCourse() {
        return studentCourse;
    }
}