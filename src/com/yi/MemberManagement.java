package com.yi;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MemberManagement {	
	
	public static void main(String[] args) {
		MemberProc mm = new MemberProc(); //MemberProc객체 생성
			
		while (true) {
			System.out.println();
			System.out.println("============== 멤버 관리 프로그램 ==============");
			System.out.println("1. 회원목록");			
			System.out.println("2. 회원등록   3. 회원삭제   4. 회원정보 수정  5. 회원검색   6. 회원나이 검사");
			System.out.println("7. 종료");
			System.out.println("============== aaaaaaaaaaaaaaaaaa ==============");
			System.out.print("메뉴를 입력하세요 : ");
			
			Scanner scn = new Scanner(System.in);
			int num=0;
			try {
				num = scn.nextInt();
				if(!(num>0 && num<8)){ //1~7외의 숫자가 입력되면 예외 강제 발생
					throw new InputMismatchException();
				}
			} catch (InputMismatchException e) {
				System.out.println("입력된 값이 잘못되었습니다. [1-7] 메뉴늘 선택해주세요!");
			}

			switch (num) {
			case 1:
				mm.showMemberList();//회원목록			
				break;
			case 2:
				mm.insertMember(); //회원 등록
				break;
			case 3:
				mm.deleteMember(); //회원 삭제				
				break;
			case 4:
				mm.updateMember(); //회원 수정
				break;
			case 5:
				mm.searchMember(); //회원 검색
				break;
			case 6:
				mm.underAged(); //나이 검사
				break;
			case 7:
				System.out.println("프로그램을 종료합니다.");
				System.exit(0); //프로그램 종료
			}//end switch()---------------
		}//while---------------------		
	}//main()--------------	
}//class MemberManagement
