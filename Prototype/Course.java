package Prototype;
import java.util.Scanner;

/**
 * Course 과목 객체를 만드는 클래스
 *
 * @author (작성자 이름)
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
