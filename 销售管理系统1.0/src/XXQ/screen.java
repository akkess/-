package XXQ;

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;

public class screen {

	private JFrame frame;
	private JButton button_4;
	private JButton button_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {    //同意多次运行
			public void run() {
				try {
					screen window = new screen();  //new一个窗体（全屏）
					window.frame.setVisible(true);  //设置fram可见
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public screen() {
		initialize();  
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("销售管理系统");
		frame.getContentPane().setFocusTraversalPolicyProvider(true);
		int screenWidth = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width; //依据用户屏幕，自己主动调整屏幕元素位置
		int screenHeight = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;

		frame.setBounds(0, 0, screenWidth, screenHeight); //设置fram的位置和长宽
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //定义close动作
		frame.getContentPane().setLayout(null); 

		JLabel lblNewLabel = new JLabel("零售业管理系统 V1.0"); //设置系统标题
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("仿宋", Font.BOLD | Font.ITALIC, 36));
		lblNewLabel.setBounds((screenWidth-400)/2, (screenHeight-350)/2 - 80, 400, 50);
		frame.getContentPane().add(lblNewLabel);		
		
		JButton newButton = new JButton("进货");   //点击进货按钮。调用进货管理程序界面
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				stock_screen.main(null);
			}
		});
		newButton.setFont(new Font("仿宋", Font.BOLD, 20)); //设置按钮字体，大小，位置
		newButton.setBounds((screenWidth-400)/2, (screenHeight-350)/2, 400, 50);
		frame.getContentPane().add(newButton); //放入fram容器中
		
		JButton button = new JButton("定价");  //设置销售按钮
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pricing_screen.main(null);
			}
		});
		button.setFont(new Font("仿宋", Font.BOLD, 20));
		button.setBounds((screenWidth-400)/2, (screenHeight-350)/2+60*1, 400, 50);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("销售");  //设置报废按钮
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sales_screen.main(null);
			}
		});
		button_1.setFont(new Font("仿宋", Font.BOLD, 20));
		button_1.setBounds((screenWidth-400)/2, (screenHeight-350)/2+60*2, 400, 50);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("报废");  //设置库存按钮
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				waste_screen.main(null);
			}
		});
		button_2.setFont(new Font("仿宋", Font.BOLD, 20));
		button_2.setBounds((screenWidth-400)/2, (screenHeight-350)/2+60*3, 400, 50);
		frame.getContentPane().add(button_2);
		
		JButton button_3 = new JButton("报表"); //设置利润按钮
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				report_screen.main(null);
			}
		});
		button_3.setFont(new Font("仿宋", Font.BOLD, 20));
		button_3.setBounds((screenWidth-400)/2, (screenHeight-350)/2+60*4, 400, 50);
		frame.getContentPane().add(button_3);
		
		JButton btnNewButton = new JButton("其它");
		btnNewButton.setFont(new Font("仿宋", Font.BOLD, 20));
		btnNewButton.setFocusPainted(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		        JOptionPane.showMessageDialog(null, "功能暂未开放！");
			}
		});
		btnNewButton.setBounds((screenWidth-400)/2, (screenHeight-350)/2+60*5, 400, 50);
		frame.getContentPane().add(btnNewButton);

		//toolbar上添加两个按钮选项 数据管理和关于
		button_4 = new JButton("数据管理");
		button_4.addActionListener(new ActionListener() {
			//点击按钮事件
			public void actionPerformed(ActionEvent e) {
				JPopupMenu popupMenu = new JPopupMenu();//载入popupMenu选项
				JMenu menu = new JMenu("清空");//清理旧数据。优化数据库
				JMenuItem item_1 = new JMenuItem("1年前数据");
				JMenuItem item_2 = new JMenuItem("2年前数据");
				JMenuItem item_3 = new JMenuItem("3年前数据");
				JMenuItem item_4 = new JMenuItem("4年前数据");
				JMenuItem item_5 = new JMenuItem("5年前数据");
				menu.add(item_1);
				menu.add(item_2);
				menu.add(item_3);
				menu.add(item_4);
				menu.add(item_5);
				popupMenu.add(menu);
	
				popupMenu.show(button_4, 0, button_4.getY() + button_4.getHeight());
				//选择toolbar里item事件。运行旧数据清理
				item_1.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						GregorianCalendar gc=new GregorianCalendar(); //当前日期的前一年
						gc.setTime(new Date());
						gc.add(1, -1);	
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置指定格式的当前日期时间
						String date = df.format(gc.getTime());						
						new OperationSqlData().DeleteSqlDataByYear(date);
					}
				});
				
				item_2.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						GregorianCalendar gc=new GregorianCalendar(); //当前日期的前一年
						gc.setTime(new Date());
						gc.add(1, -2);	
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置指定格式的当前日期时间
						String date = df.format(gc.getTime());						
						new OperationSqlData().DeleteSqlDataByYear(date);
					}
				});
				
				item_3.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						GregorianCalendar gc=new GregorianCalendar(); //当前日期的前一年
						gc.setTime(new Date());
						gc.add(1, -3);	
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置指定格式的当前日期时间
						String date = df.format(gc.getTime());						
						new OperationSqlData().DeleteSqlDataByYear(date);
					}
				});
				
				item_4.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						GregorianCalendar gc=new GregorianCalendar(); //当前日期的前两年
						gc.setTime(new Date());
						gc.add(1, -4);	
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置指定格式的当前日期时间
						String date = df.format(gc.getTime());						
						new OperationSqlData().DeleteSqlDataByYear(date);
					}
				});
				
				item_5.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						GregorianCalendar gc=new GregorianCalendar(); //当前日期的前一年
						gc.setTime(new Date());
						gc.add(1, -5);	
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置指定格式的当前日期时间
						String date = df.format(gc.getTime());						
						new OperationSqlData().DeleteSqlDataByYear(date);
					}
				});
			}
		});
		button_4.setBorder(null);
		button_4.setFont(new Font("仿宋", Font.PLAIN, 14));
		JToolBar toolBar = new JToolBar();
		toolBar.setToolTipText("配置");
		toolBar.setRollover(true);
		toolBar.add(button_4);
		toolBar.setBounds(0, 0, screenWidth, 25);
		frame.getContentPane().add(toolBar);
		
		//分隔功能栏
		toolBar.addSeparator();

		//添加开发相关信息
		button_5 = new JButton("关于");
		button_5.setBorder(null);
		button_5.setFont(new Font("仿宋", Font.PLAIN, 14));
		toolBar.add(button_5);
		frame.getContentPane().add(toolBar);
		
		button_5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
              // about_screen.main(null);
			}
		});

	}
}