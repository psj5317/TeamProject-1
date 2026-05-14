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
    private String[] studentCourse; //수강과목
    private int score[];            //수강과목 총점 (과제, 출석, 시험점수 등을 합한 점수)
    private String grade[];         //수강과목 성적 (A,B,F 로 나뉘는 등급)

    public Student(String studentName, int studentNum, String[] studentCourse, int[] score, String[] grade) {
        this.studentName = studentName;
        this.studentNum = studentNum;
        this.studentCourse = studentCourse;
        this.score = score;
        this.grade = grade;
    }

    //getStudentName : 이름 출력
    public String getStudentName() {
        return studentName;
    }

    //getStudentNum : 학번 출력
    public int getStudentNum() {
        return studentNum;
    }

    //getStudentCourse : 수강중인 과목 출력
    public String[] getStudentCourse() {
        return studentCourse;
    }

    //getScore : 수강과목별 총점 출력
    public int[] getScore() {
        return score;
    }
    
    //getGrade : 수강과목별 성적 출력
    public String[] getGrade() {
        return grade;
    }
}