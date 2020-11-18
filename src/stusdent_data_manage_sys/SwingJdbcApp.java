/**/


package Stusdent_data_manage_sys;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
class GUIPanel extends JPanel implements ActionListener 
{
	int cnt = -1;
	JLabel title,l1,l2,l3,l4,l5,l6,msg;
	JTextField t1,t2,t3,t4,t5;
	JButton b1,b2,b3,b4,b5,b6,b7;
        JRadioButton btn1,btn2;
	MyDBHandler dbo;
	String str=null;
	ArrayList<Students> list = null;
        public GUIPanel() 
	{
		setSize(600,600);
                setLayout(null);
		dbo=new MyDBHandler();	                
               title=new JLabel("Student Data Management System");
                title.setBounds(580,50,200,40);
		l1=new JLabel("RollNo :");
                l1.setBounds(520,100,50,30);
		l2=new JLabel("Name :");
                l2.setBounds(520,140,50,30);
		l3=new JLabel("Branch :");
                l3.setBounds(520,180,50,30);
		l4=new JLabel("Mobile :");
                l4.setBounds(520,220,50,30);
		l5=new JLabel("Average :");
                l5.setBounds(520,260,80,30);
                l6=new JLabel("Fees Status :");
                l6.setBounds(520,300,100,30);                
                 btn1=new JRadioButton("Paid",false);
                btn1.setBounds(700,300,60,30);                
                btn2=new JRadioButton("Not Paid",false);
                btn2.setBounds(780,300,120,30);		
		t1=new JTextField(25);
                t1.setBounds(700,100,150,30);
		t2=new JTextField(25);
		t2.setBounds(700,140,150,30);
                t3=new JTextField(25);
		t3.setBounds(700,180,150,30);
		t4=new JTextField(25);
		t4.setBounds(700,220,150,30);
		t5=new JTextField(25);
		t5.setBounds(700,260,150,30);                
               	b1=new JButton("NEW RECORD");
                b1.setBounds(500,330,150,30);
		b2=new JButton("INSERT");
                b2.setBounds(700,330,150,30);
		b3=new JButton("DELETE");
		b3.setBounds(500,380,150,30);
                b4=new JButton("NEXT");
                b4.setBounds(700,380,150,30);
		b5=new JButton("PREV");
                b5.setBounds(500,430,150,30);
		b6=new JButton("UPDATE");
                b6.setBounds(700,430,150,30);
                b7=new JButton("DISPLAY DATA");
                b7.setBounds(500,480,150,30);                
                msg=new JLabel();
                msg.setBounds(710,480,170,30);                
                ButtonGroup bg=new ButtonGroup();
                bg.add(btn1);
                bg.add(btn2);     
                add(title);
                add(l1);add(t1);
		add(l2);add(t2);
		add(l3);add(t3);
		add(l4);add(t4);
		add(l5);add(t5);
                add(l6);add(btn1);add(btn2);
		add(b1);add(b2);add(b3);
		add(b6);add(b4);add(b5);add(b7);
		add(msg);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
                b7.addActionListener(this);
                btn1.addActionListener(this);
                btn2.addActionListener(this);
	}

	public void actionPerformed(ActionEvent ae)
	{            String fees="";     
               
                if (ae.getSource().equals(b1))
		{
			t1.setText("");
			t2.setText("");
			t3.setText("");
			t4.setText("");
			t5.setText("");
                        btn1.setSelected(false);
                        btn2.setSelected(false);
			t1.requestFocus();
		}
		else if (ae.getSource().equals(b2))
		{
			int rollno=Integer.parseInt(t1.getText());
			String name=t2.getText();
			String branch=t3.getText();
			long mobile=Long.parseLong(t4.getText());
			float percentage=Float.parseFloat(t5.getText());   
                        
                        if(btn1.isSelected()){    fees="Paid";    }
                        if(btn2.isSelected()){    fees=" Not Paid";     }
                        
			str=dbo.insertRecords(rollno,name,branch,mobile,percentage,fees);                              
			msg.setText(str);
			list=null;
		}
		else if (ae.getSource().equals(b3))
		{
			int rollno=Integer.parseInt(t1.getText());
			str=dbo.deleteRecords(rollno);
			msg.setText(str);
			list=null;
		}
		else if (ae.getSource().equals(b4))
		{
			if(list == null)
			{
				list = dbo.selectRecords();
				cnt = -1;
			}
			if(cnt < list.size() - 1)
			{
				cnt++;
				Students record = list.get(cnt);
				t1.setText(String.valueOf(record.getRoll()));
				t2.setText(String.valueOf(record.getName()));
				t3.setText(String.valueOf(record.getBranch()));
				t4.setText(String.valueOf(record.getMobile()));
				t5.setText(String.valueOf(record.getPerc()));
                                String fstate=String.valueOf(record.getFees());
                                if(fstate.equals("Paid"))
                                {
                                    btn1.setSelected(true);
                                }
                                else
                                {
                                    btn2.setSelected(true);
                                }
			}			
			else
			{
				cnt = 0;
				Students record = list.get(cnt);
				t1.setText(String.valueOf(record.getRoll()));
				t2.setText(String.valueOf(record.getName()));
				t3.setText(String.valueOf(record.getBranch()));
				t4.setText(String.valueOf(record.getMobile()));
				t5.setText(String.valueOf(record.getPerc()));
                                String fstate=String.valueOf(record.getFees());
                                if(fstate.equals("Paid"))
                                {
                                    btn1.setSelected(true);
                                }
                                else
                                {
                                    btn2.setSelected(true);
                                }
			}
		}
		else if (ae.getSource().equals(b5))
		{
			if(list == null)
			{
				list = dbo.selectRecords();
				cnt = -1;
			}
			if(cnt > 0)
			{	
				cnt--;
				Students record = list.get(cnt);
				t1.setText(String.valueOf(record.getRoll()));
				t2.setText(String.valueOf(record.getName()));
				t3.setText(String.valueOf(record.getBranch()));
				t4.setText(String.valueOf(record.getMobile()));
				t5.setText(String.valueOf(record.getPerc()));
                                String fstate=String.valueOf(record.getFees());
                                if(fstate.equals("Paid"))
                                {
                                    btn1.setSelected(true);
                                }
                                else
                                {
                                    btn2.setSelected(true);
                                }
			}
			else
			{
				cnt = list.size() - 1;
				Students record = list.get(cnt);
				t1.setText(String.valueOf(record.getRoll()));
				t2.setText(String.valueOf(record.getName()));
				t3.setText(String.valueOf(record.getBranch()));
				t4.setText(String.valueOf(record.getMobile()));
				t5.setText(String.valueOf(record.getPerc()));
                                String fstate=String.valueOf(record.getFees());
                                if(fstate.equals("Paid"))
                                {
                                    btn1.setSelected(true);
                                }
                                else
                                {
                                    btn2.setSelected(true);
                                }
			}
		}
		else if (ae.getSource().equals(b6))
		{
			int rollno=Integer.parseInt(t1.getText());
			String name=t2.getText();
			String branch=t3.getText();
			long mobile=Long.parseLong(t4.getText());
			float avg=Float.parseFloat(t5.getText());                        
                        if(btn1.isSelected()){    fees="Paid";    }
                        if(btn2.isSelected()){    fees=" Not Paid";     }
                        
			str=dbo.updateRecords(rollno,name,branch,mobile,avg,fees);
			msg.setText(str);
			list=null;
		}
                else if(ae.getSource().equals(b7))
                {
                    dbo.showTableData();
                }                    
	}      
}
// Program for GUI based JDBC demo
public class SwingJdbcApp extends JFrame {
    GUIPanel myPanel;
	public SwingJdbcApp()
	{   makeGUI();      	}
	
	private void makeGUI() 
	{
		setTitle("My Swing App");
		setSize(300,400);
		setVisible(true);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myPanel=new GUIPanel();
		add(myPanel,BorderLayout.CENTER);
	}
        
         



	public static void main(String[] args) 
	{
		SwingJdbcApp newFrame=new SwingJdbcApp();
	}
}
