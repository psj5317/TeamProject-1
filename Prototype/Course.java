package Prototype;
import java.util.Scanner;

/**
 * Course 과목 객체를 만드는 클래스
 *
 * @author (작성자 이름)
 * @version 2026-05-13
 */
public class Course {
    private String courseTitle;

    public Course(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("입력할 과목의 개수를 정수로 입력하세요: ");
        int count = scanner.nextInt();
        scanner.nextLine(); 

        Course[] courseArray = new Course[count];

        for (int i = 0; i < count; i++) {
            System.out.print((i + 1) + "번째 과목명: ");
            String inputTitle = scanner.nextLine();
            
            courseArray[i] = new Course(inputTitle);
        }

        System.out.println("\n--- 저장된 과목 목록 ---");
        for (Course c : courseArray) {
            System.out.println("과목명: " + c.getCourseTitle());
        }

        scanner.close();
    }
}