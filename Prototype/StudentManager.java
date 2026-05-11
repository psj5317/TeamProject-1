package Prototype;
import java.util.Scanner;

/**
 * StudentManager 클래스의 설명을 작성하세요.
 *
 * @author (작성자 이름)
 * @version (버전 번호 또는 작성한 날짜)
 */

public class StudentManager {
    private Student[] students;                         //학생 저장 배열
    private int studentIndex;                           //학생 저장 배열의 인덱스
    
    public StudentManager() {
        this.students = new Student[1000];
        this.studentIndex = 0;
    }
    
    
    // 학생 추가
    public void addStudent(Scanner scanner) {
        System.out.print("학생 이름>> ");
        String studentName = scanner.next();

        System.out.print("학생 학번>> ");
        int studentNum = scanner.nextInt();

        System.out.print("학생 수강과목 개수>> ");
        int courseCount;
        try {
            courseCount = scanner.nextInt();
        } catch (java.util.InputMismatchException e) {
            System.out.println("수강과목 개수는 정수로 입력해주세요!");
            scanner.nextLine();
            return;
        }
        
        String[] courseStudent = new String[courseCount];
        
        courseStudent = new String[courseCount];
        for (int i = 0; i < courseCount; i++) {
            System.out.print("수강과목 이름 입력>> ");
            courseStudent[i] = scanner.next();
        }

        students[studentIndex] = new Student(studentName, studentNum, courseStudent);
        studentIndex++;
        System.out.println(studentName + "학생이 추가되었습니다.");
    }

    
    // 학생 조회
    public void findStudent(Scanner scanner) {
        System.out.print("조회할 학생의 학번을 입력하세요>> ");
        int findStudentNum = scanner.nextInt();
        boolean found = false;

        for (Student student : students) {
            if (student != null && student.getStudentNum() == findStudentNum) {
                found = true;
                System.out.println("이름: " + student.getStudentName());
                System.out.println("학번: " + student.getStudentNum());
                for (String course : student.getCourseStudent()) {
                    System.out.println("수강과목: " + course);
                }
            }
        }

        if (!found) {
            System.out.println("해당 학번의 학생을 찾을 수 없습니다.");
        }
    }
}