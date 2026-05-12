// package Prototype;
// import java.util.Scanner;
// import Prototype.CourseManager;

// /**
 // * StudentManager 클래스의 설명을 작성하세요.
 // *
 // * @author (작성자 이름)
 // * @version (버전 번호 또는 작성한 날짜)
 // */

// public class StudentManager {
    // private Student[] students;                         //학생 저장 배열
    // private int studentIndex;                           //학생 저장 배열의 인덱스
    
    // public StudentManager() {
        // this.students = new Student[1000];
        // this.studentIndex = 0;
    // }
    
    // // 학생 추가
    // public void addStudent(Scanner scanner, CourseManager courseManager) {
        // System.out.print("학생 이름>> ");
        // String studentName = scanner.next();
        // System.out.print("학생 학번>> ");
        // int studentNum = scanner.nextInt();
        // System.out.print("학생 수강과목 개수>> ");
        // int courseCount = 0;
        // while (true) {
            // try {
                // courseCount = scanner.nextInt();
                // if (courseCount > courseManager.getCourseCount()) {
                    // System.out.println("개설된 과목 수는 " + courseManager.getCourseCount() + "개입니다. 다시 입력해주세요.");
                // } else {
                    // break;  // 유효한 값이면 탈출
                // }
            // } catch (java.util.InputMismatchException e) {
                // System.out.println("수강과목 개수는 정수로 입력해주세요!");
                // scanner.nextLine();
            // }
        // }
        
        // String[] courseStudent = new String[courseCount];
        // int i = 0;
        // while (i < courseCount) {
            // String inputCourse;
            // do {
                // System.out.print("수강과목 이름 입력>> ");
                // inputCourse = scanner.next();
                // if (!courseManager.isCourseExist(inputCourse)) {    // 없으면 재입력
                    // System.out.println("과목명을 확인해 주세요.");
                // }
            // } while (!courseManager.isCourseExist(inputCourse));
            // courseStudent[i] = inputCourse;                         // 개설된 과목이면 등록
            // i++;
        // }
        // students[studentIndex] = new Student(studentName, studentNum, courseStudent);
        // studentIndex++;
        // System.out.println(studentName + "학생이 추가되었습니다.");
    // }
    // // 학생 조회
    // public void findStudent(Scanner scanner) {
        // System.out.print("조회할 학생의 학번을 입력하세요>> ");
        // int findStudentNum = scanner.nextInt();
        // boolean found = false;
        // for (Student student : students) {
            // if (student != null && student.getStudentNum() == findStudentNum) {
                // found = true;
                // System.out.println("이름: " + student.getStudentName());
                // System.out.println("학번: " + student.getStudentNum());
                // for (String course : student.getCourseStudent()) {
                    // System.out.println("수강과목: " + course);
                // }
            // }
        // }
        // if (!found) {
            // System.out.println("해당 학번의 학생을 찾을 수 없습니다.");
        // }
    // }
// }