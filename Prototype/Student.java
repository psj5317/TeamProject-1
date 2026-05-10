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
    private int studentNum;         //학번
    private String[] courseStudent;  //수강과목

    public Student(String studentName, int studentNum, String[] courseStudent) {
        this.studentName = studentName;
        this.studentNum = studentNum;
        this.courseStudent = courseStudent;
    }
    
    //setStudentName : 이름 입력
    public void setStudentName(String studentName){
        this.studentName = studentName;
    }
    
    //setStudentNum : 학번 입력
    public void setStudentNum(int studentNum){
        this.studentNum = studentNum;
    }
    
    //setCourseStudent : 수강중인 과목 입력
    public void setCourseStudent(String[] courseStudent){
        this.courseStudent = courseStudent;
    }
    
    //getStudentName : 이름 출력
    public String getStudentName() {
        return studentName;
    }
    
    //getStudentNum : 학번 출력
    public int getStudentNum() {
        return studentNum;
    }

    //getCourseStudent : 수강중인 과목 출력
    public String[] getCourseStudent() {
        return courseStudent;
    }
}