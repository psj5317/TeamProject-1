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
    //과목정보 입력 메서드
    public static int addCourse(Scanner scanner,Student[] studentDB, Course[] courseDB, int totalCourse) {
        //과목 정보 입력
        System.out.print("과목명>> ");
        String courseTitle = scanner.next();

        System.out.print("과목학점>> ");
        int courseCredit = scanner.nextInt();

        System.out.print("평가항목 개수>> ");
        int evaluationItemCount = scanner.nextInt();
        String[][] evaluationItems = new String[evaluationItemCount][2];

        //평가항목마다 점수 차등 설정
        for (int i = 0 ; i < evaluationItemCount ; i++){
            System.out.print("평가항목 " + (i+1) +"번 이름 입력>> ");
            evaluationItems[i][0] = scanner.next();
            System.out.print(evaluationItems[i][0] + " 점수 설정>> ");
            evaluationItems[i][1] = scanner.next();
            System.out.println("");
        }

        //개설강좌 확인
        System.out.println("");
        System.out.println("----------입력된 과목----------");
        System.out.println("과목명: " + courseTitle);
        System.out.println("학점: " + courseCredit);
        System.out.println(courseTitle + " 평가 점수");
        for (int i = 0; i < evaluationItemCount; i++) {
            System.out.println(evaluationItems[i][0] + " : " + evaluationItems[i][1]);
        }
        //과목DB에 과목 추가
        courseDB[totalCourse] = new Course(courseTitle, courseCredit, evaluationItems);

        totalCourse++; //과목이 정상적으로 추가되었으므로 데이터베이스에 저장된 학생 수 +1        

        //추가된 총 과목 수 메인메서드로 리턴
        return totalCourse++;
    }

    //과목조회 메서드
    public static void findCourse(Scanner scanner,Course[] courseDB){
        System.out.print("조회할 과목명을 입력하세요>> ");
        String findCourse = scanner.next();

        //해당 과목을 찾았는지 확인하기 위한 변수
        boolean found = false;

        for (Course course : courseDB) {
            //과목명 일치하는지 검사
            if (course != null) {
                String title = course.getCourseName();

                boolean same = true;

                //찾으려는 과목과 기존 과목 문자열 길이 다르면 넘어감
                if (title.length() != findCourse.length()) {
                    same = false;
                }
                //문자열 길이 같을경우 과목명 한 글자씩 비교
                else {

                    for (int i = 0; i < title.length(); i++) {
                        if (title.charAt(i) != findCourse.charAt(i)) {
                            same = false;
                            break;  
                        }
                    }
                }

                //일치할경우 조회된 과목정보 출력
                if (same) {
                    found = true;
                    System.out.println("과목명: " + course.getCourseName());

                    System.out.println("학점: " + course.getCourseCredit());

                    for (int i = 0 ; i < course.getEvaluationItem().length ; i++) {
                        System.out.println(course.getEvaluationItem()[i][0] + " : " + course.getEvaluationItem()[i][1] + "점");
                    }
                }
            }
        }

        //개설x 강의 안내
        if (!found) {
            System.out.println("해당 과목을 찾을 수 없습니다.");
        }
    }

    // 학생 정보 입력
    public static int addStudent(Scanner scanner,Student[] studentDB, Course[] courseDB, int totalStudent, int totalCourse) {      
        System.out.print("학생 이름>> ");
        String studentName = scanner.next();
        System.out.print("학생 학번>> ");
        int studentNum = scanner.nextInt();
        System.out.print("학생 수강과목 개수>> ");
        int courseCount = 0;

        //수강과목 수 입력받기
        while (true) {
            try {
                courseCount = scanner.nextInt();
                if (courseCount > totalCourse) {
                    System.out.println("현재까지 입력된 과목 수는 " + totalCourse + "개 입니다. 과목 정보를 먼저 입력한 뒤, 학생 정보를 입력해주세요.");
                    System.out.print("학생 수강과목 개수>> ");
                } else {
                    break;  // 유효한 값이면 끝내기
                }

            } catch (java.util.InputMismatchException e) {
                System.out.println("수강과목 개수는 정수로 입력해주세요!");
                scanner.nextLine();
            }
        }

        //수강과목이 현재 입력되어 있는지 확인하고 학생정보 입력받기
        String[] studentCourse = new String[courseCount];   //입력받은 과목들을 저장할 배열 생성
        int[][] scores = new int[courseCount][];            //평가항목별 점수 저장할 배열 생성 [평가항목 이름][해당항목 점수]

        for (int i = 0 ; i < courseCount ; i++) {
            String inputCourse;     //과목이 데이터베이스에 입력되어 있는지 확인하기 위해 입력값을 저장해둘 변수
            System.out.println();
            System.out.print("수강과목명 입력>> ");
            inputCourse = scanner.next();

            boolean found = false;  //입력받은 과목이 현재 데이터베이스에 있는지 구분하기 위한 변수

            //현재 과목DB에 저장되어있는 과목들을 검사하므로 입력된 과목개수만큼 반복
            for (int j = 0; j < totalCourse; j++) {
                //과목DB에서 현재 입력받은 과목명이 있는지 확인(equals()는 예제 3-6에서 나왔었음 !)
                if (courseDB[j].getCourseName().equals(inputCourse)) {
                    studentCourse[i] = inputCourse;
                    found = true;

                    //입력받은 과목의 평가항목별 점수 입력받기
                    String[][] items = courseDB[j].getEvaluationItem(); //입력받은 과목의 평가항목 불러오기
                    scores[i] = new int[items.length];  //점수 저장할 변수에 평가항목 개수만큼 배열길이 설정

                    for (int k = 0; k < items.length; k++) {
                        int maxScore = Integer.parseInt(items[k][1]);   //현재 평가항목의 지정된 점수 Ex) 과제점수 20점 > 20 저장

                        while (true) {
                            System.out.print((k+1) + ". " + items[k][0] + " 점수 입력>> ");
                            int inputScore = scanner.nextInt();

                            //만약 입력받은 점수가 지정된 점수를 초과하면 다시 입력 (예를들어 과제 20점 만점인데 25점 입력시 재입력)
                            if (inputScore > maxScore) {
                                System.out.println(items[k][0] + "의 최대 점수는 " + maxScore + "점입니다.");
                            }
                            else {
                                scores[i][k] = inputScore;
                                break;
                            }
                        }
                    }
                    break; //검색한 과목 찾았으니 break
                }
            }

            // 없는 과목이면 다시 입력
            if(!found){
                System.out.println("개설되지 않은 과목입니다.");
                i--;
            }
        }

        //DB에 학생 추가
        studentDB[totalStudent] = new Student(studentName, studentNum, studentCourse, scores);
        System.out.println(studentName + "학생 정보가 입력되었습니다.");

        totalStudent++; //학생이 정상적으로 추가되었으므로 데이터베이스에 저장된 학생 수 +1

        //추가된 총 학생 수 메인메서드로 리턴
        return totalStudent;
    }

    // 학생 조회
    public static void findStudent(Scanner scanner, int totalCourse, Student[] studentDB, Course[] courseDB) {
        System.out.print("조회할 학생의 학번을 입력하세요>> ");
        int findStudentNum = scanner.nextInt();

        boolean found = false;

        //학생 DB에서 학번으로 학생 조회
        for (Student student : studentDB) {
            if (student != null && student.getStudentNum() == findStudentNum) {
                found = true;
                System.out.println("이름: " + student.getStudentName());
                System.out.println("학번: " + student.getStudentNum());

                for (int i = 0; i < student.getCourseStudent().length; i++) {   //수강중인 과목 개수만큼 반복
                    String courseName = student.getCourseStudent()[i];    //현재 출력중인 과목명
                    System.out.println("수강과목: " + courseName);
                    int[] courseScores = student.getScores()[i];    //검색학생 점수 불러오기
                    int scoreSum = 0;
                    //해당 과목의 평가항목별 점수 출력
                    for (int k = 0; k < totalCourse; k++) {
                        if (courseDB[k].getCourseName().equals(courseName)) { //과목DB에서 출력중인 과목 데이터 찾기
                            String[][] items = courseDB[k].getEvaluationItem(); //출력중인 과목의 평가항목 불러오기

                            for (int j = 0; j < courseScores.length; j++) {
                                System.out.print(items[j][0] + " 점수 : " + courseScores[j]);
                                scoreSum += courseScores[j];
                            }
                        }
                    }

                    //해당과목 총점 출력
                    System.out.println(courseName + "과목 총 점수는 : " + scoreSum + "입니다.");
                }
            }
        }

        //학생 DB에 해당 학생 없을경우 문구 출력
        if (!found) {
            System.out.println("");;
            System.out.println("해당 학번의 학생을 찾을 수 없습니다.");
        }
    }

    //과목의 평가항목 총점 계산 메서드
    public static int courseTotalScoreCalculate(Student student , String courseName){
        String[] courses = student.getCourseStudent() ; //총점 계산할 학생의 수강과목 불러오기
        int[][] scores = student.getScores();   //점수 불러오기
        //
        for (int i = 0; i < courses.length; i++) {
            if (courses[i].equals(courseName)) {    //불러온 수강과목중 해당 과목만 뽑아내기
                int sum = 0;
                for (int j = 0 ; j < scores[i].length ; j++) {  //받은 평가항목 점수의 총합 구하기
                    sum += scores[i][j];
                }
                return sum;
            }
        }
        return -1;
    }

    //과목별 성적 계산
    public static void courseCalculate(Scanner scanner , Student[] studentsDB, int totalStudent){
        System.out.print("성적조회할 과목명을 입력하세요 >> ");
        String findCourse = scanner.next(); //조회할 과목명
        Student[] rankStudents = new Student[totalStudent]; //수강학생 점수 순위를 넣을 배열
        int[] totals = new int[totalStudent];   //
        int count = 0;

        // 해당 과목 수강 학생 저장
        for (int i = 0; i < totalStudent; i++) {
            Student student = studentsDB[i];    //학생DB에서 수강자만 뽑아오기
            int total = courseTotalScoreCalculate(student, findCourse); //순위 계산을 위해 해당 학생의 총점을 따로 저장

            if (total != -1) {
                rankStudents[count] = student;  //수강학생정보가 들어있는 데이터베이스(순위 계산을 위해 totlas와 같은 인덱스로 학생객체 참조)
                totals[count] = total;  //총점이 입력된 데이터베이스 (순위 계산을 위해 rankStudents와 같은 인덱스 사용)
                count++;    //수강자 구분 인덱스
            }
        }

        // 총점 기준 내림차순 정렬
        for (int i = 0; i < count - 1; i++) {
            for (int j = i + 1; j < count; j++) {
                if (totals[i] < totals[j]) {    //앞 사람과 뒷사람 총점 비교하며 순위 정렬 Ex)만약 이후에 입력된 사람이 더 높다면 서로 순위 바꾸기
                    int tempScore = totals[i];  
                    totals[i] = totals[j];
                    totals[j] = tempScore;
                    Student tempStudent = rankStudents[i];
                    rankStudents[i] = rankStudents[j];
                    rankStudents[j] = tempStudent;
                }
            }
        }
        System.out.println("");;
        
        //학점 나누기 전 순위표 출력
        System.out.println("[" + findCourse + " 순위표]");
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + "등 " + rankStudents[i].getStudentName() + " " + rankStudents[i].getStudentNum() + " " + totals[i] + "점");
        }
        
        //등급 기준 입력
        System.out.print("A 등급 최소 점수 >> ");
        int aCut = scanner.nextInt();
        System.out.print("B 등급 최소 점수 >> ");
        int bCut = scanner.nextInt();
        System.out.print("C 등급 최소 점수 >> ");
        int cCut = scanner.nextInt();

        //선문대학교 교칙에 따라 35%만 A 또는 A+ 가능
        int aLimit = (int)Math.ceil(count * 0.35);
        //선문대학교 교칙에 따라 70%만 B 또는 B+ 가능
        int bLimit = (int)Math.ceil(count * 0.70);

        // 최종 순위 출력
        System.out.println("");;
        System.out.println("[" + findCourse + " 성적 순위]");

        System.out.println("");;
        System.out.println("[" + findCourse + " 최종 성적]");
        
        //학생들에게 기준에 맞추어 학점 부여
        for (int i = 0; i < count; i++) {
            String grade;
            if (i < aLimit
            && totals[i] >= aCut) {
                grade = "A";
            }

            else if (i < bLimit
            && totals[i] >= bCut) {
                grade = "B";
            }

            else if (totals[i] >= cCut) {
                grade = "C";
            }

            else {
                grade = "F";
            }

            System.out.println((i + 1) + "등 " + rankStudents[i].getStudentName() + " " + rankStudents[i].getStudentNum() + " " + totals[i] + "점 " + grade);
        }
    }

    //메인 메서드
    public static void main(String[] args){
        //변수 선언
        Scanner scanner = new Scanner(System.in);
        Student[] studentDB = new Student[1000];     //학생정보를 저장할 데이터베이스
        Course[] courseDB = new Course[100];         //과목을 저장할 데이터베이스

        //메인화면 변수
        int mainMenu = 0;                           //기본화면
        int studentMenu = 0;                        //학생메뉴
        int courseMenu = 0;                         //과목메뉴
        int creditMenu = 0;                         //성적처리메뉴

        //학생관련 변수
        int totalStudent = 0;                        //입력받은 총 학생 수

        //과목관련 변수
        int evaluationItemCount = 0;                //평가항목 개수
        String[] evaluationItems;                   //평가항목 배열
        int totalCourse = 0;                        //입력받은 총 과목 수

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
                                System.out.print("과목명 입력: 1, 과목조회: 2, 기본메뉴로 돌아가기: 3>> ");

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
                                totalCourse = addCourse(scanner,studentDB,courseDB,totalCourse);
                                courseMenu = 0;
                                break;

                            case 2:
                                System.out.println("");
                                findCourse(scanner,courseDB);
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
                                System.out.print("학생정보 입력: 1, 학생조회: 2, 기본메뉴로 돌아가기: 3>> ");

                                //정수가 아닌 데이터타입이 입력시 재입력 안내
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
                                totalStudent = addStudent(scanner, studentDB, courseDB, totalStudent, totalCourse);       
                                studentMenu = 0;
                                break;

                                //--------------------------------------------학생 조회 탭
                            case 2:
                                findStudent(scanner, totalCourse, studentDB, courseDB);
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
                    studentMenu = 0;
                    mainMenu = 0;
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
                                courseCalculate(scanner , studentDB , totalStudent);
                                creditMenu = 0;
                                break;

                                //--------------------------------------------학생별 성적조회 탭
                                // case 2:
                                // studentScoreCalculate(scanner , studentDB , courseDB , totalStudent , totalCourse);
                                // creditMenu = 0;
                                // break;
                                //내일 여기서부터 하자잉 ㅇㅇ 거의 다 왔다 수고했어

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
                    creditMenu = 0;
                    mainMenu = 0;
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