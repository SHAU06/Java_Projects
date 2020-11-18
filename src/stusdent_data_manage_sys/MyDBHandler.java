/**/

package Stusdent_data_manage_sys;

import java.util.*;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
class Students
{
	private int roll;
	private String name, branch,fees;
	private long mobile;
	private float perc;        
	public Students(int p1,String p2,String p3,long p4,float p5,String p6)
	{
		roll=p1;name=p2;branch=p3;mobile=p4;perc=p5;fees=p6;
	}
	public int getRoll(){return roll;}
	public String getName(){return name;}
	public String getBranch(){return branch;}
	public long getMobile(){return mobile;}
	public float getPerc(){return perc;}
        public String getFees(){return fees;}   
        
}
public class MyDBHandler implements ActionListener
{	Connection con=null;
	Statement stmt=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	String sql=null;      
           Statement st, st1;
           PreparedStatement pst;
           String ids;
           JTable table;
           DefaultTableModel model;
           JFrame frame1;
           JButton refresh;
           int rollno;
           String name;
           String branch;
           Long mobile;        
           float percentage;
           String fees;
	public void prepareConnection()
	{
		try
		{	System.out.println("Loading drivers...");
                        Class.forName("com.mysql.jdbc.Driver");
                        System.out.println("Connecting to database...");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/gpndb?zeroDateTimeBehavior=convertToNull","root","");
			System.out.println("Connected.....");
		}
		catch (SQLException | ClassNotFoundException se)
		{
			se.printStackTrace();
		}
	}
	public String insertRecords(int rollno,String name,String branch,long mobile,float percentage,String fees)
	{	try
		{	prepareConnection();
			String sql = "INSERT INTO student" + "(rollno, name, branch, mobile, percentage,fees) VALUES"+ "(?,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, rollno);
			pstmt.setString(2, name);
			pstmt.setString(3, branch);
			pstmt.setLong(4, mobile);
			pstmt.setFloat(5,percentage);
                        pstmt.setString(6,fees);
			int rows = pstmt.executeUpdate();
			String str=String.valueOf(rows);
			str += " Record Inserted.....";
			closeConn();
 			return str;                        
		}
		catch (SQLException se)
                {       se.printStackTrace();       }
		catch(Exception e)
		{       e.printStackTrace();        		}
		return "";
	}
	public String updateRecords(int roll,String name,String branch,long mobile,float percentage,String fees )
	{       try
		{
			prepareConnection();
			String sql = "update student "+ "set name=?, branch=?, mobile=?, percentage=? ,fees=?"+ "where rollno=?";
			pstmt=con.prepareStatement(sql);			
			pstmt.setString(1, name);
			pstmt.setString(2, branch);
			pstmt.setLong(3, mobile);
			pstmt.setFloat(4, percentage);
                        pstmt.setString(5, fees);
			pstmt.setInt(6, roll);
                        int rows = pstmt.executeUpdate();
			String str=String.valueOf(rows);
			str += " Record Updated.....";
			closeConn();
 			return str;
		}
		catch (SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "";
	}
	public String deleteRecords(int roll)
	{
		try
		{
			prepareConnection();
			String sql = "delete from student "+ "where rollno=?";
			pstmt=con.prepareStatement(sql);						
			pstmt.setInt(1, roll);
			int rows = pstmt.executeUpdate();
			String str=String.valueOf(rows);
			str += " Record Deleted.....";
 			return str;
		}
		catch (SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "";
	}

	public ArrayList<Students> selectRecords()
	{
		ArrayList<Students> records=new ArrayList<Students>();
		try
		{
			prepareConnection();
			String sql = "select * from student";
			
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				records.add(new Students(
					rs.getInt(1),rs.getString(2),rs.getString(3),
					rs.getLong(4),rs.getFloat(5),rs.getString(6)
				));
			}			
			closeConn();
 			return records;
		}
		catch (SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public void closeConn()
	{
		try
		{			
			con.close();
		}
		catch (SQLException se)
		{
			se.printStackTrace();
		}
	}
        
         public void showTableData() {           
    String[] columnNames = {"Rollno", "Name", "Branch", "Mobile","Percentage","Fee Status"};
    frame1 = new JFrame("Displaying The Data...");
    frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame1.setLayout(new FlowLayout(FlowLayout.CENTER));
        refresh=new JButton("Refresh Data");
        frame1.add(refresh);
         model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        table = new JTable();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(  JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);        
         refresh.addActionListener(this);        
        try {
                prepareConnection();
		String sql = "select * from student";			
		stmt=con.createStatement();
		rs=stmt.executeQuery(sql);
            int i = 0;
            while(rs.next()) 
            {               
                rollno=rs.getInt("rollno");                
                name = rs.getString("name");
                branch = rs.getString("branch");
                mobile = rs.getLong("mobile");
                percentage = rs.getFloat("percentage");
                fees=rs.getString("fees");
                model.addRow(new Object[]{rollno, name, branch, mobile,percentage,fees});
                i++;
            }
            if (i < 1) {
                JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);

            }
            if (i == 1) {

                System.out.println(i + " Record Found");

            } else {

                System.out.println(i + " Records Found");
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }
        frame1.add(scroll);
        frame1.setVisible(true);
        frame1.setSize(400, 300);

    }         
         public void actionPerformed(ActionEvent ae)
         {
                           if (ae.getSource().equals(refresh)){
        try {   model.setRowCount(0);
                prepareConnection();
		String sql = "select * from student";			
		stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery(sql);
            int i = 0;
            while(rs.next()) 
            {               
                rollno=rs.getInt("rollno");                
                name = rs.getString("name");
                branch = rs.getString("branch");
                mobile = rs.getLong("mobile");
                percentage = rs.getFloat("percentage");
                fees=rs.getString("fees");
                model.addRow(new Object[]{rollno, name, branch, mobile,percentage,fees});
                i++;
            }
            if (i < 1) {
                JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);

            }
            if (i == 1) {

                System.out.println(i + " Record Found");

            } else {

                System.out.println(i + " Records Found");
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }
         }
           }    
}


