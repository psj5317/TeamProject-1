package Prototype;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * MyApp 클래스의 설명을 작성하세요.
 *
 * @author (작성자 이름)
 * @version (2026.05.13)
 */

public class MyApp{ 

    //과목정보 입력 메서드

    /**
     * 메소드 예제 - 사용자에 맞게 주석을 바꾸십시오.
     *
     * @param   scanner : 메인 메서드와 연동되어 입력받는 파라미터
     *          studentDB : 학생정보가 저장되어 있는 데이터베이스
     *          courseDB : 과목정보가 저장되어있는 데이터베이스
     *          totalCourse : 현재까지 입력받은 과목개수
     *          
     * @return  totalCourse : 과목정보가 입력되어 수정된 총 과목개수 값 반환
     */
    public static int addCourse(Scanner scanner,Student[] studentDB, Course[] courseDB, int totalCourse) {
        //과목 정보 입력
        System.out.print("과목명>> ");
        String courseTitle = scanner.next();

        System.out.print("과목학점>> ");
        int courseCredit = scanner.nextInt();

        // System.out.print("평가항목 개수>> ");
        // int evaluationItemCount = scanner.nextInt();
        // String[][] evaluationItems = new String[evaluationItemCount][2];

        // //평가항목마다 점수 차등 설정
        // for (int i = 0 ; i < evaluationItemCount ; i++){
        // System.out.print("평가항목 " + (i+1) +"번 이름 입력>> ");
        // evaluationItems[i][0] = scanner.next();
        // System.out.print(evaluationItems[i][0] + " 점수 설정>> ");
        // evaluationItems[i][1] = scanner.next();
        // System.out.println("");
        // }

        //개설강좌 확인
        System.out.println("");
        System.out.println("----------입력된 과목----------");
        System.out.println("과목명: " + courseTitle);
        System.out.println("학점: " + courseCredit);

        // System.out.println(courseTitle + " 평가 점수");
        // for (int i = 0; i < evaluationItemCount; i++) {
        // System.out.println(evaluationItems[i][0] + " : " + evaluationItems[i][1]);
        // }

        //과목DB에 과목 추가
        courseDB[totalCourse] = new Course(courseTitle, courseCredit);

        totalCourse++; //과목이 정상적으로 추가되었으므로 데이터베이스에 저장된 학생 수 +1        

        //추가된 총 과목 수 메인메서드로 리턴
        return totalCourse;
    }

    //과목조회 메서드
    /**
     * 메소드 예제 - 사용자에 맞게 주석을 바꾸십시오.
     *
     * @param   scanner : 메인 메서드와 연동되어 입력받는 파라미터
     *          courseDB : 과목정보가 저장되어있는 데이터베이스
     *          
     */
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

                    //평가항목별 점수표
                    // for (int i = 0 ; i < course.getEvaluationItem().length ; i++) {
                    // System.out.println(course.getEvaluationItem()[i][0] + " : " + course.getEvaluationItem()[i][1] + "점");
                    // }
                }
            }
        }

        //개설x 강의 안내
        if (!found) {
            System.out.println("해당 과목을 찾을 수 없습니다.");
        }
    }

    // 학생 정보 입력
    /**
     * 메소드 예제 - 사용자에 맞게 주석을 바꾸십시오.
     *
     * @param   scanner : 메인 메서드와 연동되어 입력받는 파라미터
     *          studentDB : 학생정보가 저장되어 있는 데이터베이스
     *          courseDB : 과목정보가 저장되어있는 데이터베이스
     *          totalStudent : 현재까지 입력받은 학생수
     *          totalCourse : 현재까지 입력받은 과목개수
     *          
     * @return  totalStudent : 학생정보가 입력되어 수정된 총학생수 값 반환
     */
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

        String[] studentCourse = new String[courseCount];      //입력받은 과목들을 저장할 배열 생성
        int[] score = new int[courseCount];                    //과목별 성적 입력받을 배열 생성
        // int[][] scores = new int[courseCount][];            //평가항목별 점수 저장할 배열 생성 [평가항목 이름][해당항목 점수]

        for (int i = 0 ; i < courseCount ; i++) {
            String inputCourse;     //과목이 데이터베이스에 입력되어 있는지 확인하기 위해 입력값을 저장해둘 변수
            System.out.println("");;
            System.out.print("수강과목명 입력>> ");
            inputCourse = scanner.next();

            //수강과목이 현재 과목DB에 입력되어 있는지 확인하고 학생정보 입력받기
            boolean found = false;  //입력받은 과목이 현재 데이터베이스에 있는지 구분하기 위한 변수

            //현재 과목DB에 저장되어있는 과목들을 검사하므로 입력된 과목개수만큼 반복
            for (int j = 0; j < totalCourse; j++) {
                //과목DB에서 현재 입력받은 과목명이 있는지 확인(equals()는 예제 3-6에서 나왔었음 !)
                if (courseDB[j].getCourseName().equals(inputCourse)) {
                    studentCourse[i] = inputCourse;
                    System.out.print(inputCourse + "의 총점을 입력해주세요. >>");
                    score[i] = scanner.nextInt(); 
                    found = true;

                    // //입력받은 과목의 평가항목별 점수 입력받기
                    // String[][] items = courseDB[j].getEvaluationItem(); //입력받은 과목의 평가항목 불러오기
                    // score[i] = new int[items.length];  //점수 저장할 변수에 평가항목 개수만큼 배열길이 설정

                    // for (int k = 0; k < items.length; k++) {
                    // int maxScore = Integer.parseInt(items[k][1]);   //현재 평가항목의 지정된 점수 Ex) 과제점수 20점 > 20 저장

                    // while (true) {
                    // System.out.print((k+1) + ". " + items[k][0] + " 점수 입력>> ");
                    // int inputScore = scanner.nextInt();

                    // //만약 입력받은 점수가 지정된 점수를 초과하면 다시 입력 (예를들어 과제 20점 만점인데 25점 입력시 재입력)
                    // if (inputScore > maxScore) {
                    // System.out.println(items[k][0] + "의 최대 점수는 " + maxScore + "점입니다.");
                    // }
                    // else {
                    // score[i][k] = inputScore;
                    // break;
                    // }
                    // }
                    // }

                    break; //검색한 과목 찾았으니 break
                }
            }

            // 없는 과목이면 다시 입력
            if(!found){
                System.out.println(inputCourse + "과목은 입력되지 않은 과목입니다.");
                i--;
            }
        }

        //DB에 학생 추가
        studentDB[totalStudent] = new Student(studentName, studentNum, studentCourse, score);
        System.out.println(studentName + "학생 정보가 입력되었습니다.");

        totalStudent++; //학생이 정상적으로 추가되었으므로 데이터베이스에 저장된 학생 수 +1

        //추가된 총 학생 수 메인메서드로 리턴
        return totalStudent;
    }

    // 학생 조회
    /**
     * 메소드 예제 - 사용자에 맞게 주석을 바꾸십시오.
     *
     * @param   scanner : 메인 메서드와 연동되어 입력받는 파라미터
     *          totalCourse : 현재까지 입력받은 과목개수
     *          studentDB : 학생정보가 저장되어 있는 데이터베이스
     *          courseDB : 과목정보가 저장되어있는 데이터베이스
     */
    public static void findStudent(Scanner scanner, int totalCourse, Student[] studentDB, Course[] courseDB) {
        System.out.print("조회할 학생의 학번을 입력하세요>> ");
        int findStudentNum = scanner.nextInt();

        //학생을 찾았는가 확인
        boolean found = false;

        //학생 DB에서 학번으로 학생 조회
        for (Student student : studentDB) {
            if (student != null && student.getStudentNum() == findStudentNum) {
                found = true;
                System.out.println("이름: " + student.getStudentName());
                System.out.println("학번: " + student.getStudentNum());

                for (int i = 0; i < student.getStudentCourse().length; i++) {   //수강중인 과목 개수만큼 반복
                    String courseName = student.getStudentCourse()[i];    //현재 출력중인 과목명
                    int[] courseScore = student.getScore();    //검색학생 점수 불러오기

                    // //해당 과목의 평가항목별 점수 출력
                    // for (int k = 0; k < totalCourse; k++) {
                    // if (courseDB[k].getCourseName().equals(courseName)) { //과목DB에서 출력중인 과목 데이터 찾기
                    // String[][] items = courseDB[k].getEvaluationItem(); //출력중인 과목의 평가항목 불러오기

                    // for (int j = 0; j < courseScores.length; j++) {
                    // System.out.print(items[j][0] + " 점수 : " + courseScores[j]);
                    // scoreSum += courseScores[j];
                    // }
                    // }
                    // }

                    //해당과목 총점 출력
                    System.out.println(courseName + "점수 : " + courseScore[i]);
                }
            }
        }

        //학생 DB에 해당 학생 없을경우 문구 출력
        if (!found) {
            System.out.println("");;
            System.out.println("해당 학번의 학생을 찾을 수 없습니다.");
        }
    }

    // //과목의 평가항목 총점 계산 메서드
    // public static int courseTotalScoreCalculate(Student student , String courseName){
    // String[] courses = student.getStudentCourse() ; //총점 계산할 학생의 수강과목 불러오기
    // int[] score = student.getScore();   //점수 불러오기
    // //
    // for (int i = 0; i < courses.length; i++) {
    // if (courses[i].equals(courseName)) {    //불러온 수강과목중 해당 과목만 뽑아내기
    // int sum = 0;
    // for (int j = 0 ; j < score[i].length ; j++) {  //받은 평가항목 점수의 총합 구하기
    // sum += score[i][j];
    // }
    // return sum;
    // }
    // }
    // return -1;
    // }

    //과목별 성적 계산
    /**
     * 메소드 예제 - 사용자에 맞게 주석을 바꾸십시오.
     *
     * @param   scanner : 메인 메서드와 연동되어 입력받는 파라미터
     *          courseDB : 과목정보가 저장되어있는 데이터베이스
     *          studentDB : 학생정보가 저장되어 있는 데이터베이스
     *          totalStudent : 현재까지 입력받은 학생수
     *          totalCourse : 현재까지 입력받은 과목개수
     */
    public static void courseCalculate(Scanner scanner , Course[] courseDB ,Student[] studentDB, int totalStudent, int totalCourse){
        System.out.print("성적조회할 과목명을 입력하세요 >> ");
        String findCourse = scanner.next(); //조회할 과목명

        Student[] rankStudents = new Student[totalStudent]; //수강학생 점수 순위를 넣을 배열
        int[] totals = new int[totalStudent];   //수강자의 점수를 저장할 배열
        int count = 0;  //수강자수

        // 해당 과목 수강 학생 찾기
        for(Student student : studentDB){
            if(student == null){
                continue;
            }

            String[] courses = student.getStudentCourse();  //학생의 수강과목 불러오기
            int[] scores = student.getScore();  //학생의 점수 불러오기

            for(int i = 0; i < courses.length; i++){
                // 해당과목 수강하는지 수강명으로 확인
                if(courses[i].equals(findCourse)){
                    rankStudents[count] = student;  //점수표에 학생 넣기
                    totals[count] = scores[i];  //점수 입력
                    count++;    //수강자 1명 추가
                    break;
                }
            }
        }

        // 수강 학생이 없는 경우
        if(count == 0){
            System.out.println("해당 과목 수강 학생이 없습니다.");
            return;
        }

        // 점수 내림차순 정렬
        for(int i = 0; i < count - 1; i++){ //정렬되지 않은 배열에서 앞 사람 인덱스를 뜻하는 i
            for(int j = i + 1; j < count; j++){ //정렬되지 않은 배열에서 뒷 사람의 인덱스를 뜻하는 j
                if(totals[i] < totals[j]){  //정렬되지 않은 배열에서 앞 사람 점수와 뒷사람 점수 비교

                    int tempScore = totals[i];  //정렬되지 않은 배열에서 앞 사람 점수를 임시저장
                    totals[i] = totals[j];  //배열의 앞 사람 자리에 뒷사람 점수를 저장
                    totals[j] = tempScore;  //배열의 뒷 사람 자리에 앞사람 점수를 저장

                    Student tempStudent = rankStudents[i];  //학생 정보도 위와 같은 방법으로 연동시킴
                    rankStudents[i] = rankStudents[j];      //점수와 학생배열의 인덱스가 같이 움직여야하기 때문에 우에 사용한 i와 j 인덱스를 그대로 사용
                    rankStudents[j] = tempStudent;
                }
            }
        }

        // 등급 나누기 전 순위표 출력
        System.out.println("");;
        System.out.println("[" + findCourse + " 성적현황]");

        for(int i = 0; i < count; i++){
            System.out.println(
                (i + 1) + "등 " + rankStudents[i].getStudentName() + " " + rankStudents[i].getStudentNum() + " " + totals[i] + "점");
        }

        // 등급컷 입력
        System.out.print("A 등급 최소 점수 >> ");
        int aCut = scanner.nextInt();

        System.out.print("B 등급 최소 점수 >> ");
        int bCut = scanner.nextInt();

        System.out.print("C 등급 최소 점수 >> ");
        int cCut = scanner.nextInt();

        //선문대학교의 학사정보에 의하면 소수점처리는 올림으로 한다
        //선문대학교 A이상 비율  
        double aValue = count * 0.35;
        int aLimit = (int)aValue;
        //소수점이 있다면 올림처리
        if(aValue > aLimit){
            aLimit++;
        }
        //선문대학교 B이상 비율
        double bValue = count * 0.70;
        int bLimit = (int)bValue;

        if(bValue > bLimit){
            bLimit++;
        }

        // 최종 성적 출력
        System.out.println("");;
        System.out.println("[" + findCourse + " 최종 성적]");

        for(int i = 0; i < count; i++){
            String grade;   //학점 저장
            //만약 35% 이상이 A를 받게된다면 자동으로 성적순으로 끊어 35%이후 학생은 B학점 부여
            if(i < aLimit && totals[i] >= aCut){
                grade = "A";
            }
            else if(i < bLimit && totals[i] >= bCut){
                grade = "B";
            }
            else if(totals[i] >= cCut){
                grade = "C";
            }
            else{
                grade = "F";
            }
            System.out.println((i + 1) + "등 " + rankStudents[i].getStudentName() + " " + totals[i] + "점 " + grade);
        }
        // // 해당 과목 수강 학생 저장
        // for (int i = 0; i < totalStudent; i++) {
        // Student student = studentDB[i];    //각 학생정보를 학생DB에서 참조하기
        // int[] total = student.getScore();   //해당학생 과목별 점수 모두 불러오기

        // // int total = courseTotalScoreCalculate(student, findCourse); //순위 계산을 위해 해당 학생의 총점을 따로 저장

        // for (int j = 0; j < totalCourse; j++) {
        // if (courseDB[j].equals(courseName)) {    //불러온 수강과목중 해당 과목만 뽑아내기                    
        // if (total != -1) {
        // rankStudents[count] = student;  //수강학생정보가 들어있는 데이터베이스(순위 계산을 위해 totlas와 같은 인덱스로 학생객체 참조)
        // totals[count] = total;  //총점이 입력된 데이터베이스 (순위 계산을 위해 rankStudents와 같은 인덱스 사용)
        // count++;    //수강자 구분 인덱스
        // }
        // }

        // // 총점 기준 내림차순 정렬
        // for (int k = 0; k < count - 1; k++) {
        // for (int l = k + 1; l < count; l++) {
        // if (totals[k] < totals[l]) {    //앞 사람과 뒷사람 총점 비교하며 순위 정렬 Ex)만약 이후에 입력된 사람이 더 높다면 서로 순위 바꾸기
        // int tempScore = totals[k];  
        // totals[k] = totals[l];
        // totals[l] = tempScore;
        // Student tempStudent = rankStudents[k];
        // rankStudents[k] = rankStudents[l];
        // rankStudents[l] = tempStudent;
        // }
        // }
        // }
        // System.out.println("");;

        // //학점 나누기 전 순위표 출력
        // System.out.println("[" + findCourse + " 순위표]");
        // for (int k = 0; k < count; k++) {
        // System.out.println((k + 1) + "등 " + rankStudents[k].getStudentName() + " " + rankStudents[k].getStudentNum() + " " + totals[k] + "점");
        // }

        // //등급 기준 입력
        // System.out.print("A 등급 최소 점수 >> ");
        // int aCut = scanner.nextInt();
        // System.out.print("B 등급 최소 점수 >> ");
        // int bCut = scanner.nextInt();
        // System.out.print("C 등급 최소 점수 >> ");
        // int cCut = scanner.nextInt();

        // //선문대학교 교칙에 따라 35%만 A 또는 A+ 가능
        // int aLimit = (int)Math.ceil(count * 0.35);
        // //선문대학교 교칙에 따라 70%만 B 또는 B+ 가능
        // int bLimit = (int)Math.ceil(count * 0.70);

        // // 최종 순위 출력
        // System.out.println("");;
        // System.out.println("[" + findCourse + " 성적 순위]");

        // System.out.println("");;
        // System.out.println("[" + findCourse + " 최종 성적]");

        // //학생들에게 기준에 맞추어 학점 부여
        // for (int k = 0; k < count; k++) {
        // String grade;
        // if (k < aLimit && totals[k] >= aCut) {
        // grade = "A";
        // }

        // else if (k < bLimit && totals[k] >= bCut) {
        // grade = "B";
        // }

        // else if (totals[k] >= cCut) {
        // grade = "C";
        // }

        // else {
        // grade = "F";
        // }

        // System.out.println((k + 1) + "등 " + rankStudents[k].getStudentName() + " " + rankStudents[k].getStudentNum() + " " + totals[k] + "점 " + grade);
        // }
        // }
        // }
    }

    //학생별 성적 계산
    /**
     * 메소드 예제 - 사용자에 맞게 주석을 바꾸십시오.
     *
     * @param  y  메소드의 샘플 파라미터
     * @return    x 와 y의 합
     */
    public static void studentScoreCalculate(Scanner scanner ,Student[] studentDB ,Course[] courseDB ,int totalStudent ,int totalCourse){
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
        // int evaluationItemCount = 0;                //평가항목 개수
        // String[] evaluationItems;                   //평가항목 배열
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
                                courseCalculate(scanner , courseDB , studentDB, totalStudent, totalCourse);
                                creditMenu = 0;
                                break;

                                //--------------------------------------------학생별 성적조회 탭
                            case 2:
                                studentScoreCalculate(scanner , studentDB , courseDB , totalStudent , totalCourse);
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