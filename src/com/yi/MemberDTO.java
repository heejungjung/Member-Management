package com.yi;

import java.util.Formatter;

//회원 (한 명분의) 정보 저장용 자료형 클래스
public class MemberDTO {
	// 이름, 나이, 전화번호
		// 회원 한 명의 정보 중에서 복수개의 자료(->컬렉션)가 있는지 확인 필요
		private int m_no;
		private String m_name;
		private int m_age;
		private String m_phone;
		// source-generate getter,setter하면 한번에 생성됨
		public int getNo() {
			return m_no;
		}
		public void setNo(int no) {
			this.m_no = no;
		}
		public String getName() {
			return m_name;
		}
		public void setName(String name) {
			this.m_name = name;
		}
		public int getAge() {
			return m_age;
		}
		public void setAge(int age) {
			this.m_age = age;
		}
		public String getPhone() {
			return m_phone;
		}
		public void setPhone(String phone) {
			this.m_phone = phone;
		}
		
		MemberDTO(String m_name, String m_age, String m_phone){
			this.m_name = m_name;
			this.m_age = Integer.parseInt(m_age);
			this.m_phone = m_phone;
		};
		
		MemberDTO(String m_no, String m_name, String m_age, String m_phone){
			this.m_no = Integer.parseInt(m_no);
			this.m_name = m_name;
			this.m_age = Integer.parseInt(m_age);
			this.m_phone = m_phone;
		};

		@Override
		public String toString() {
			Formatter fm = new Formatter();
			String meminfo = fm.format("%4d\t  %-10s\t%-3d세\t%-12s", m_no,m_name, m_age,m_phone).toString();
			return meminfo;
		}
		
		public String getInfo() {
			StringBuffer sb = new StringBuffer();
			sb.append("\r\n");
			sb.append("[ "+m_no+ " ] 회원님의 정보====\n");
			sb.append("이    름 : "+m_name+"\n");
			sb.append("나    이 : "+m_age+"\n");
			sb.append("전화번호 : "+m_phone+"\n");
					
			return sb.toString();
		}
		
		public String test20() {
			StringBuffer sb = new StringBuffer();
			sb.append("\r\n");
			if(m_age<20) {
				sb.append("[ "+m_no+ " ] "+m_name+ " 님은 아직 어리시네요 ㅡ3ㅡ  \n");
			}else {
				sb.append("[ "+m_no+ " ] "+m_name+ " 어르신!!  \n");
			}
					
			return sb.toString();
		}
} //메인 클래스
