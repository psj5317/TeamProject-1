package Prototype;
import java.util.Scanner;

/**
 * Course 과목 객체를 만드는 클래스
 *
 * @author (2023320010 박성준, 2023320012 강성하, 2023320006 정준영, 2023320029 정지후)
 * @version 2026-05-08
 */
public class Course {
    private String courseTitle;     //과목명
    private int courseCredit;       //학점
    private int score;            //수강과목 총점 (과제, 출석, 시험점수 등을 합한 점수)
    private String grade;         //수강과목 성적표 (Ex : A,B,F)

    public Course(String courseTitle, int courseCredit, int score, String grade) {
        this.courseTitle = courseTitle;
        this.courseCredit = courseCredit;
        this.score = score;
        this.grade = grade;
    }
    
    public Course(String courseTitle, int score, String grade){
        this.courseTitle = courseTitle;
        this.score = score;
        this.grade = grade;        
    }

    public String getCourseName() {
        return courseTitle;
    }

    public int getCourseCredit() {
        return courseCredit;
    }

    //getScore : 수강과목별 총점 출력
    public int getScore() {
        return score;
    }

    //getGrade : 수강과목별 성적 출력
    public String getGrade() {
        return grade;
    }
    
    //setGrade : 과목의 성적처리 완료시 성적표 변경
    public void setGrade(String grade){
        this.grade = grade;
    }
}
