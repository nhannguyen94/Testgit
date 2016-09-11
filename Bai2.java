package CSDL;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bai2 {
	static Connection con;
	static Statement sm;

	public static void main(String[] args) {
		Ketnoi();
		Hienthi();
		List<String> sl = Loadfiletxt();
		UpdateDataBase(sl);
		Hienthi();
	}

	private static void UpdateDataBase(List<String> sl) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(".\\src\\CSDL\\error.txt"));

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			// Truy van
			sm.execute("Delete from Hocvien");
			for (int i = 0; i < sl.size(); i++) 
			try{
				String s = sl.get(i);
				String mhv = s.substring(0, 10);
				String name = s.substring(10, 60);
				java.util.Date d = df.parse(s.substring(60, 70));
				String sex = s.substring(70, 73);
				Double diem = Double.parseDouble(s.substring(73));
				String q = "Insert into Hocvien Values(" + "\'" + mhv + "\'," + "\'" + name + "\'," + "\'"
						+ df.format(d) + "\'," + "\'" + sex + "\'," + diem + ")";
				// System.out.println(q);
				sm.executeUpdate(q);
			} catch(Exception e){
				bw.write("Error at "+(i+1)+" line");
				bw.newLine();
				System.out.println("Error at "+(i+1)+" line");
			}
			bw.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Ket noi khong thanh cong");

		}

	}

	private static List<String> Loadfiletxt() {
		try {
			List<String> sl = new ArrayList<String>();
			BufferedReader br = new BufferedReader(new FileReader(".\\src\\CSDL\\input.txt"));
			
			while (true) {

				String s = br.readLine();
				if (s == null)
					break;
				sl.add(s);
				// System.out.println(s);
			}
			br.close();
			return sl;
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return null;

	}

	private static void Ketnoi() {
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			String url = "jdbc:odbc:Hocvien";
			String user = "";
			String pass = "";
			con = DriverManager.getConnection(url, user, pass);
			sm = con.createStatement();
		} catch (Exception e) {

		}
	}

	private static void Hienthi() {
		try {
			// Truy van
			ResultSet rs = sm.executeQuery("Select * from Hocvien");
			// Lay thong tin
			ResultSetMetaData rsm = rs.getMetaData();
			int cn = rsm.getColumnCount();
			for (int i = 1; i <= cn; i++)
				System.out.print(rsm.getColumnLabel(i) + "\t");
			System.out.println("");
			while (rs.next()) {
				for (int i = 1; i <= cn; i++)
					System.out.print(rs.getString(i) + "\t");
				System.out.println("");
			}
			// con.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Ket noi khong thanh cong");

		}

	}
}
