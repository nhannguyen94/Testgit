package CSDL;

import java.sql.*;
import java.util.Scanner;

public class CSDL {
	public static void main(String[] args) {
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			String url = "jdbc:odbc:PMTuan";
			String user = "";
			String pass = "";
			Connection con = DriverManager.getConnection(url, user, pass);
			System.out.println("Ket noi OK");
			Statement sm = con.createStatement();
			Scanner sc = new Scanner(System.in);
			lap: while (true) {
				// Truy van
				ResultSet rs = sm.executeQuery("Select * from Student");
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
				System.out.println("1. Them....2.Xoa....3.Sua....4.Ketthuc");
				int chon = sc.nextInt();
				int id;
				String name;
				int math, phys, chem;
				switch (chon) {
				case 1:
					System.out.print("Nhap ID:");
					id = sc.nextInt();
					System.out.print("Nhap ten:");
					name = sc.next();
					System.out.print("Nhap diem toan:");
					math = sc.nextInt();
					System.out.print("Nhap diem ly:");
					phys = sc.nextInt();
					System.out.print("Nhap diem hoa:");
					chem = sc.nextInt();
					try {
						System.out.println("Insert into Student values(null,\'"  + name + "\'," + math + "," + phys + "," + chem + ")");
						sm.execute("Insert into Student" +"(name,toan,ly,hoa)" +" values(\'"  + name + "\'," + math + "," + phys + "," + chem + ")");
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Error!");
					}
					break;
				case 2:
					System.out.print("Nhap ID:");
					id = sc.nextInt();
					try {
						sm.execute("Delete from Student where id=" + id);
					} catch (Exception e) {
						System.out.println("Error!");
					}
					break;
				case 3:
					System.out.print("Nhap ID:");
					id = sc.nextInt();
					System.out.print("Nhap ten:");
					name = "Tuan";//sc.next();
					System.out.print("Nhap diem toan:");
					math = 6;//sc.nextInt();
					System.out.print("Nhap diem ly:");
					phys = 7;//sc.nextInt();
					System.out.print("Nhap diem hoa:");
					chem = 8;//sc.nextInt();
					System.out.println("....");
					try {
						System.out.println("Update Student set Name = \'" + name + "\',toan = " + math + ",ly=" + phys + ",hoa=" + chem + " where id=" + id);
						sm.executeUpdate("Update Student set Name = \'" + name + "\',toan = " + math + ",ly=" + phys + ",hoa=" + chem + " where id=" + id);
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Error!");
					}
					break;
				case 4:
					break lap;
				default:
					break;
				}
			}

			con.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Ket noi khong thanh cong");

		}
	}
}
