package Prototype;
import java.util.Scanner;

public class StudentManager {
    private Student[] students;
    private int studentIndex;

    // 학생 추가
    public void addStudent(Scanner scanner, Student[] students) {
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
        for (int i = 0; i < courseCount; i++) {
            System.out.print("수강과목 이름 입력>> ");
            courseStudent[i] = scanner.next();
        }

        students[studentIndex] = new Student(studentName, studentNum, courseStudent);
        studentIndex++;
        System.out.println("학생이 추가되었습니다.");
    }

    // 학생 조회
    public void findStudent(Scanner scanner, Student[] students) {
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