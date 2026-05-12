package Prototype;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * MyApp 클래스의 설명을 작성하세요.
 *
 * @author (작성자 이름)
 * @version (2026.05.10)
 */

public class MyApp
{   

    public static void main(String[] args){
        //변수 선언
        Scanner scanner = new Scanner(System.in);
        StudentManager studentManager = new StudentManager();
        CourseManager courseManager = new CourseManager();

        //메인화면 변수
        int mainMenu = 0;                           //기본화면
        int studentMenu = 0;                        //학생메뉴
        int courseMenu = 0;                         //과목메뉴
        int creditMenu = 0;                         //성적처리메뉴

        //과목관련 변수
        int evaluationItemCount;                    //평가항목 개수
        String[] evaluationItems;                   //평가항목 배열

        while (mainMenu != 4){
            switch (mainMenu){
                    //메인화면
                case 0:
                    studentMenu = 0;
                    courseMenu = 0;
                    System.out.println("");
                    System.out.println("-------------성적처리 홈화면입니다.-------------");
                    System.out.println("");
                    System.out.print("과목메뉴: 1, 학생메뉴: 2, 성적처리: 3, 프로그램 종료: 4>> ");

                    //예외처리
                    try{
                        mainMenu = scanner.nextInt();    
                    }
                    catch (InputMismatchException e){
                        System.out.println("1,2,3,4 중 입력하세요.");
                        scanner.nextLine();
                        mainMenu = 0;
                    }
                    break;

                    //과목메뉴
                case 1:
                    while(courseMenu != 3){
                        switch (courseMenu){
                            case 0:
                                //-------------------------------------------과목메뉴 홈 탭
                                System.out.println("");
                                System.out.println("-------------과목메뉴 탭입니다.-------------");
                                System.out.println("");
                                System.out.print("과목개설: 1, 과목조회: 2, 기본메뉴로 돌아가기: 3>> ");

                                //정수 외 입력 예외처리
                                try{
                                    courseMenu = scanner.nextInt();    
                                }
                                catch (InputMismatchException e){
                                    System.out.println("1,2,3 중 입력하세요.");
                                    scanner.nextLine();
                                    courseMenu = 0;
                                }
                                break;

                                //--------------------------------------------과목개설 탭 
                            case 1:
                                System.out.println("");
                                courseManager.addCourse(scanner);
                                courseMenu = 0;
                                break;

                            case 2:
                                System.out.println("");
                                courseManager.findCourse(scanner);
                                courseMenu = 0;
                                break;

                            case 3:
                                System.out.println("메인 탭으로 이동합니다");
                                break;

                                //과목 탭 정수 입력 예외처리
                            default:
                                System.out.println("메뉴 탭 1,2,3 중 입력하세요");
                                scanner.nextLine();
                                courseMenu = 0;
                        }
                    }
                    courseMenu = 0;
                    mainMenu = 0;
                    break;

                    //학생 메뉴
                case 2:
                    while(studentMenu != 3){
                        mainMenu = 0;
                        switch (studentMenu){
                            case 0:
                                //학생메뉴 홈 탭
                                System.out.println("");
                                System.out.println("-------------학생메뉴 탭입니다.-------------");
                                System.out.println("");
                                System.out.print("학생추가: 1, 학생조회: 2, 기본메뉴로 돌아가기: 3>> ");

                                //정수 외 입력 예외처리
                                try{
                                    studentMenu = scanner.nextInt();    
                                }
                                catch (InputMismatchException e){
                                    System.out.println("1,2,3 중 입력하세요.");
                                    scanner.nextLine();
                                    studentMenu = 0;
                                }
                                break;

                                //----------------------------------------학생 추가 탭
                            case 1:
                                studentManager.addStudent(scanner, courseManager);       
                                studentMenu = 0;
                                break;

                                //--------------------------------------------학생 조회 탭
                            case 2:
                                studentManager.findStudent(scanner);
                                studentMenu = 0;
                                break;

                            case 3:
                                mainMenu = 0;
                                break;

                                //학생 탭 정수 입력 예외처리
                            default:  
                                System.out.println("메뉴 탭 1,2,3 중 입력하세요");
                                scanner.nextLine();
                                studentMenu = 0;
                        }
                    }
                    break;

                    //성적처리 메뉴
                case 3:
                    while(creditMenu != 3){
                        mainMenu = 0;
                        switch (creditMenu){
                            case 0:
                                //성적처리메뉴 홈 탭
                                System.out.println("");
                                System.out.println("-------------성적처리 탭입니다.-------------");
                                System.out.println("");
                                System.out.print("과목별 성적조회: 1, 학생별 성적조회: 2, 기본메뉴로 돌아가기: 3>> ");

                                //정수 외 입력 예외처리
                                try{
                                    creditMenu = scanner.nextInt();    
                                }
                                catch (InputMismatchException e){
                                    System.out.println("1,2,3 중 입력하세요.");
                                    scanner.nextLine();
                                    studentMenu = 0;
                                }
                                break;

                                //----------------------------------------과목별 성적조회 탭
                            case 1:
                                creditManager.studentCredit(scanner);       
                                creditMenu = 0;
                                break;

                                //--------------------------------------------학생별 성적조회 탭
                            case 2:
                                creditManager.courseCredit(scanner);
                                creditMenu = 0;
                                break;

                            case 3:
                                mainMenu = 0;
                                break;

                                //성적처리 탭 정수 입력 예외처리
                            default:  
                                System.out.println("메뉴 탭 1,2,3 중 입력하세요");
                                scanner.nextLine();
                                studentMenu = 0;
                        }
                    }
                    break;

                    //프로그램 종료
                case 4:
                    break;

                    //메인메뉴 정수 예외처리
                default:
                    System.out.println("메뉴 탭 1,2,3,4 중 입력하세요.");
                    System.out.println("");
                    scanner.nextLine();
                    mainMenu = 0;  
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }
}