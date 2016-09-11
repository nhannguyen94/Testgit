/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author nhannguyen
 */
public class Tourdulich {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost/dbtourdulich_102120187";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";

    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
        //loadFileData1();
        //loadFileData2();
        int i=0;
        String data;
        File file = new File("error.txt");
                if (!file.exists()) {
                   file.createNewFile();
                }
                FileWriter fw = new FileWriter(file.getName());
                BufferedWriter bw = new BufferedWriter(fw);
        try{           
           Class.forName(JDBC_DRIVER);           
           System.out.println("Connecting to database...");
           Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
           System.out.println("OK");
           BufferedReader br = null, br2=null;
           String sCurrentLine;
  
            br = new BufferedReader(new FileReader("D:\\hoc tap\\ki8\\LapTrinhJava\\thicuoiky\\data1.txt"));
            br2 = new BufferedReader(new FileReader("D:\\hoc tap\\ki8\\LapTrinhJava\\thicuoiky\\data2.txt"));            
            while ((sCurrentLine = br.readLine()) != null) {
                String[] g = sCurrentLine.split(", ");                               
                    String mtv = g[0];
                    String ten = g[1];
                    String ngaysinh = g[2];
                    String que = g[3];
                    String email = g[4];
                    String sdt = g[5];
                    //System.out.println(mtv+" "+ten+" "+String.valueOf(ngaysinh)+" "+que+" "+email+" "+sdt);                                                
           String sql = "insert into thanhvien(MaThanhVien,TenThanhVien,NgaySinh,DiaChi,Email,SoDienThoai) values (?,?,?,?,?,?);";
           SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
           java.util.Date d = df.parse(ngaysinh);              
           java.sql.Date dt = new java.sql.Date(d.getTime());
           PreparedStatement pstm = conn.prepareStatement(sql);
           pstm.setString(1, mtv);
           pstm.setString(2, ten);
           pstm.setDate(3, dt);
           pstm.setString(4, que);
           pstm.setString(5, email);
           pstm.setString(6, sdt);
           pstm.executeUpdate();
            }
            int thuong=0;
            int chiphithuong;
            
            
            while ((sCurrentLine = br2.readLine()) != null) {
                i++;
                String[] h = sCurrentLine.split(", "); 
                chiphithuong = 0;
                    String mtv = h[0];                   
                    int diemthuong = Integer.parseInt(h[1]);
                    String level = h[2];                    ;
                    System.out.println(mtv+" "+diemthuong+" "+level);
                if (level.equalsIgnoreCase("VIP")) {
                    thuong = 50000;
                } else if(level.equalsIgnoreCase("NOR")){
                    thuong = 20000;
                } else {
                    data="Level khong phai la VIP hoac NOR";
                } 
                chiphithuong = diemthuong*thuong;
           String sql2 = "update thanhvien set ChiPhiNhan = ChiPhiNhan+"+(diemthuong*thuong)+" where MaThanhVien = '"+mtv+"'";
                                    
           PreparedStatement pstm = conn.prepareStatement(sql2);
           
           pstm.executeUpdate();                  
                
                
            }
           
        }catch(Exception e){
            System.out.println("Exception : " + e.getMessage());
            data = "Dong "+i+" co loi";
            bw.write(data+"\n");            
                bw.close();
        }       
        System.out.println("Goodbye!");
    }
    public static void loadFileData1() {
        BufferedReader br = null;
  
        try {
  
            String sCurrentLine;
  
            br = new BufferedReader(new FileReader("D:\\hoc tap\\ki8\\LapTrinhJava\\thicuoiky\\data1.txt"));
            
            while ((sCurrentLine = br.readLine()) != null) {
                String[] d = sCurrentLine.split(", ");                               
                    String mtv = d[0];
                    String ten = d[1];
                    String ngaysinh = (d[2]);
                    String que = d[3];
                    String email = d[4];
                    String sdt = d[5];
                    System.out.println(mtv+" "+ten+" "+String.valueOf(ngaysinh)+" "+que+" "+email+" "+sdt);                                    
            }  
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    public static void loadFileData2() {
        BufferedReader br = null;
  
        try {
  
            String sCurrentLine;
  
            br = new BufferedReader(new FileReader("D:\\hoc tap\\ki8\\LapTrinhJava\\thicuoiky\\data2.txt"));
            
            while ((sCurrentLine = br.readLine()) != null) {
                String[] d = sCurrentLine.split(", ");                               
                    String mtv = d[0];
                    int diemthuong = Integer.parseInt(d[1]);
                    String level = (d[2]);
                    
                    System.out.println(mtv+" "+String.valueOf(diemthuong)+" "+level);                                    
            }  
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
