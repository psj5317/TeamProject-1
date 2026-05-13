package Prototype;
import java.util.Scanner;

/**
 * Course 과목 객체를 만드는 클래스
 *
 * @author (작성자 이름)
 * @version 2026-05-08
 */
public class Course {
    private String courseTitle;         //과목명
    private int courseCredit;           //학점
    // private String[][] evaluationItem;  //평가항목(Ex:시험, 과제, 출석) 및 각각 점수 분배

    public Course(String courseTitle, int courseCredit) {
        this.courseTitle = courseTitle;
        this.courseCredit = courseCredit;
        // this.evaluationItem = evaluationItem;
    }

    public String getCourseName() {
        return courseTitle;
    }

    public int getCourseCredit() {
        return courseCredit;
    }

    // public String[][] getEvaluationItem() {
        // return evaluationItem;
    // }
}
