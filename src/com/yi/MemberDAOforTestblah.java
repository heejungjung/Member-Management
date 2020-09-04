package com.yi;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 
public class MemberDAOforTestblah {
   
    private static Connection conn;
   
    private PreparedStatement pstmt;
    private Statement stmt;
    //private CallableStatement cstmt; <- 얘는 프로시저용임..
    private ResultSet rs;
   
   
    //기본생성자
    public MemberDAOforTestblah() {
   
    }
       
    private void getConnection() throws ClassNotFoundException, SQLException{
        if(conn == null){ //dbConn이 null이면 Connection 객체 얻어오기..
            //접속정보
            String url = "jdbc:mysql://localhost/shop_member";
            String user = "root";
            String pw = "rootroot";
           
            //JDBC드라이버 로드
            Class.forName("com.mysql.jdbc.Driver");
           
            //오라클(DBMS)에 연결하여 Connection 객체 얻기.
            conn = DriverManager.getConnection(url,user,pw);
                               
        }
    }
   
       
    /**
    * 회원 등록하기
    */
    public int insertMember(MemberDTO dto){    
       
        int result=500; //내부 오류
       
        try {
            getConnection();
           
            String sql = "insert into shop_Member_info values (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1,dto.getNo());
            pstmt.setString(2,dto.getName());
            pstmt.setInt(3,dto.getAge());
            pstmt.setString(4,dto.getPhone()); 
            
            int count = pstmt.executeUpdate();
            if( count == 0 ){
                System.out.println("데이터 입력 실패");
            }
            else{
                System.out.println("데이터 입력 성공");
            }

        } catch (Exception e) {        
            System.out.println("예외발생:insertMember()=> "+e.getMessage());
        }finally{          
            dbClose();
        }      
        return result;
    }      
   
    /**
    * 회원번호에 해당하는 회원정보 보기
    */
    public MemberDTO getMember(String no){
        MemberDTO dto =null;
        try {
            getConnection();
           
            String sql = "select * from shop_Member_info where m_no='"+no+"'";
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sql);
           
            while(rs.next()) {
                String m_no = rs.getInt("m_no")+"";
                String m_name = rs.getString("m_name");
                String m_age = rs.getInt("m_age")+"";
                String m_phone = rs.getString("m_phone");
                dto = new MemberDTO(m_no,m_name, m_age, m_phone);
            }
        } catch (Exception e) {
            System.out.println("예외발생:deleteMember()=> "+e.getMessage());
        }finally{          
            dbClose();
        }      
       
        return dto;
    }
    public MemberDTO getMemberbyname(String name){
        MemberDTO dto =null;
        try {
            getConnection();

            String sql = "select * from shop_Member_info where m_name='"+name+"'";
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sql);
           
            while(rs.next()) {
                String m_no = rs.getInt("m_no")+"";
                String m_name = rs.getString("m_name");
                String m_age = rs.getInt("m_age")+"";
                String m_phone = rs.getString("m_phone");
                dto = new MemberDTO(m_no,m_name, m_age, m_phone);
            }
        } catch (Exception e) {
            System.out.println("예외발생:deleteMember()=> "+e.getMessage());
        }finally{          
            dbClose();
        }      
       
        return dto;
    }
 
    /**
    * 저장된 회원 목록 보기
    */
    public List<MemberDTO> getMemberList(){
        List<MemberDTO> list = new ArrayList<MemberDTO>();
       
        try {
            getConnection();
            String sql = "select * from shop_Member_info";
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sql);
           
            while(rs.next()) {
                String m_no = rs.getInt("m_no")+"";
                String m_name = rs.getString("m_name");
                String m_age = rs.getInt("m_age")+"";
                String m_phone = rs.getString("m_phone");
                list.add(new MemberDTO(m_no,m_name, m_age, m_phone));
            }
            
        } catch (Exception e) {
            System.out.println("예외발생:getMemberList()=> "+e.getMessage());
        }finally{          
            dbClose();
        }  
       
        return list;
    }
   
 
    /**
    * 회원 수정
    */
    public boolean updateMember(MemberDTO dto){
       
        boolean result = false;            
        try {
            getConnection();
           
            String sql = "update shop_Member_info set m_name=?, m_age=?, m_phone=? where m_no=?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,dto.getName());
            pstmt.setInt(2,dto.getAge());
            pstmt.setString(3,dto.getPhone()); 
            pstmt.setInt(4,dto.getNo()); 

            int count = pstmt.executeUpdate();
            if( count == 0 ){
                System.out.println("데이터 수정 실패");
            }
            else{
                System.out.println("데이터 수정 성공");
                result=true;
            }
        } catch (Exception e) {
            System.out.println("예외발생:updateMember()=> "+e.getMessage());
            e.printStackTrace();
        }finally{          
            dbClose();
        }      
        return result;
    }
   
   
    /**
    * 회원 삭제
    */
    public boolean deleteMember(String id){        
        boolean result = false;            
        try {
            getConnection();
           
            String sql = "delete from shop_Member_info where m_no=" + id;
            pstmt = conn.prepareStatement(sql);       

            int count = pstmt.executeUpdate();
            if( count == 0 ){
                System.out.println("데이터 삭제 실패");
            }
            else{
                System.out.println("데이터 삭제 성공");
                result=true;
            }
           
        } catch (Exception e) {
            System.out.println("예외발생:deleteMember()=> "+e.getMessage());
        }finally{          
            dbClose();
        }      
        return result;
    }//deleteMember()--------------
   
   
    /**DB연결 해제(닫기)*/
    public void dbClose(){      
     
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.out.println("예외:ResultSet객체 close():" + e.getMessage());
            }
        }
         
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                System.out.println("예외:PreparedStatement객체 close():" + e.getMessage());
            }
        }
/*   
        if (cstmt != null) {
            try {
                cstmt.close();
            } catch (SQLException e) {
                System.out.println("예외:CallableStatement객체 close():" + e.getMessage());
            }
        }      
*/           
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("예외:Connection객체 close():" + e.getMessage());
            }
        }    
        conn = null;        
    }//dbClose()---------
   
}
 