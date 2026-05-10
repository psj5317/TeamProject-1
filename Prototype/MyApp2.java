package Prototype;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * MyApp2 클래스의 설명을 작성하세요.
 *
 * @author (작성자 이름)
 * @version (버전 번호 또는 작성한 날짜)
 */
public class MyApp2
{
    public static void main(String[] args){
        //변수 선언
        Scanner scanner = new Scanner(System.in); 
        int mainMenu = 0;                           //기본화면
        int studentMenu = 0;                        //학생메뉴
        Student[] students = new Student[1000];     //학생정보(1000명 기준)
        int studentIndex = 0;                       //학생 저장 인덱스

        while (mainMenu != 4){
            switch (mainMenu){
                    case(0):
                    System.out.print("학생메뉴: 1, 과목메뉴: 2, 성적처리: 3, 프로그램 종료: 4>>");
                    try{
                        mainMenu = scanner.nextInt();    
                    }
                    catch (InputMismatchException e){
                        System.out.println("1,2,3,4 중 입력하세요.");
                        scanner.nextLine();
                        mainMenu = 0;
                    }
                    break;

                    //학생메뉴
                    case(1):
                    switch (studentMenu){
                            case(0):
                            //학생메뉴 홈 탭
                            System.out.println("");
                            System.out.println("-------------학생메뉴 탭입니다.-------------");
                            System.out.println("");
                            System.out.print("학생추가: 1, 학생조회: 2, 학생수정: 3, 기본메뉴로 돌아가기: 4>> ");

                            try{
                                studentMenu = scanner.nextInt();    
                            }
                            catch (InputMismatchException e){
                                System.out.println("1,2,3,4 중 입력하세요.");
                                scanner.nextLine();
                                studentMenu = 0;
                            }
                            break;

                            case(1):
                            //변수 초기화
                            String studentName ="";
                            int studentNum = 0;
                            int courseCount = 0;
                            String[] courseStudent;      

                            System.out.print("학생 이름>> ");
                            studentName = scanner.next();
                            System.out.print("학생 학번>> ");
                            studentNum = scanner.nextInt();
                            System.out.print("학생 수강과목 개수>> ");
                            try{
                                courseCount = scanner.nextInt();
                            }
                            catch (InputMismatchException e){
                                System.out.println("수강과목 개수는 정수로 입력해주세요 !");
                                scanner.nextLine();
                                break;
                            }
                            courseStudent = new String[courseCount];
                            for (int i = 0; i < courseCount ; i++){
                                System.out.print("수강과목 이름 입력>> ");
                                courseStudent[i] = scanner.next();                    
                            }

                            students[studentIndex] = new Student(studentName, studentNum, courseStudent);

                            //학생 신규 추가 후 다음 학생 정보 저장을 위해 인덱스로 넘어감
                            studentIndex++;

                            //기본 학생 메뉴로 돌아가기
                            studentMenu = 0;
                            break;

                        default:  
                            System.out.println("메뉴 탭 1,2,3,4 중 입력하세요.");
                            scanner.nextLine();
                            studentMenu = 0;
                    }
            }
        }
    }
}