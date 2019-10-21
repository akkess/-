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
		EventQueue.invokeLater(new Runnable() {    //ͬ��������
			public void run() {
				try {
					screen window = new screen();  //newһ�����壨ȫ����
					window.frame.setVisible(true);  //����fram�ɼ�
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
		frame = new JFrame("���۹���ϵͳ");
		frame.getContentPane().setFocusTraversalPolicyProvider(true);
		int screenWidth = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width; //�����û���Ļ���Լ�����������ĻԪ��λ��
		int screenHeight = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;

		frame.setBounds(0, 0, screenWidth, screenHeight); //����fram��λ�úͳ���
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //����close����
		frame.getContentPane().setLayout(null); 

		JLabel lblNewLabel = new JLabel("����ҵ����ϵͳ V1.0"); //����ϵͳ����
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("����", Font.BOLD | Font.ITALIC, 36));
		lblNewLabel.setBounds((screenWidth-400)/2, (screenHeight-350)/2 - 80, 400, 50);
		frame.getContentPane().add(lblNewLabel);		
		
		JButton newButton = new JButton("����");   //���������ť�����ý�������������
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				stock_screen.main(null);
			}
		});
		newButton.setFont(new Font("����", Font.BOLD, 20)); //���ð�ť���壬��С��λ��
		newButton.setBounds((screenWidth-400)/2, (screenHeight-350)/2, 400, 50);
		frame.getContentPane().add(newButton); //����fram������
		
		JButton button = new JButton("����");  //�������۰�ť
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pricing_screen.main(null);
			}
		});
		button.setFont(new Font("����", Font.BOLD, 20));
		button.setBounds((screenWidth-400)/2, (screenHeight-350)/2+60*1, 400, 50);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("����");  //���ñ��ϰ�ť
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sales_screen.main(null);
			}
		});
		button_1.setFont(new Font("����", Font.BOLD, 20));
		button_1.setBounds((screenWidth-400)/2, (screenHeight-350)/2+60*2, 400, 50);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("����");  //���ÿ�水ť
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				waste_screen.main(null);
			}
		});
		button_2.setFont(new Font("����", Font.BOLD, 20));
		button_2.setBounds((screenWidth-400)/2, (screenHeight-350)/2+60*3, 400, 50);
		frame.getContentPane().add(button_2);
		
		JButton button_3 = new JButton("����"); //��������ť
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				report_screen.main(null);
			}
		});
		button_3.setFont(new Font("����", Font.BOLD, 20));
		button_3.setBounds((screenWidth-400)/2, (screenHeight-350)/2+60*4, 400, 50);
		frame.getContentPane().add(button_3);
		
		JButton btnNewButton = new JButton("����");
		btnNewButton.setFont(new Font("����", Font.BOLD, 20));
		btnNewButton.setFocusPainted(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		        JOptionPane.showMessageDialog(null, "������δ���ţ�");
			}
		});
		btnNewButton.setBounds((screenWidth-400)/2, (screenHeight-350)/2+60*5, 400, 50);
		frame.getContentPane().add(btnNewButton);

		//toolbar�����������ťѡ�� ���ݹ���͹���
		button_4 = new JButton("���ݹ���");
		button_4.addActionListener(new ActionListener() {
			//�����ť�¼�
			public void actionPerformed(ActionEvent e) {
				JPopupMenu popupMenu = new JPopupMenu();//����popupMenuѡ��
				JMenu menu = new JMenu("���");//��������ݡ��Ż����ݿ�
				JMenuItem item_1 = new JMenuItem("1��ǰ����");
				JMenuItem item_2 = new JMenuItem("2��ǰ����");
				JMenuItem item_3 = new JMenuItem("3��ǰ����");
				JMenuItem item_4 = new JMenuItem("4��ǰ����");
				JMenuItem item_5 = new JMenuItem("5��ǰ����");
				menu.add(item_1);
				menu.add(item_2);
				menu.add(item_3);
				menu.add(item_4);
				menu.add(item_5);
				popupMenu.add(menu);
	
				popupMenu.show(button_4, 0, button_4.getY() + button_4.getHeight());
				//ѡ��toolbar��item�¼������о���������
				item_1.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						GregorianCalendar gc=new GregorianCalendar(); //��ǰ���ڵ�ǰһ��
						gc.setTime(new Date());
						gc.add(1, -1);	
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// ����ָ����ʽ�ĵ�ǰ����ʱ��
						String date = df.format(gc.getTime());						
						new OperationSqlData().DeleteSqlDataByYear(date);
					}
				});
				
				item_2.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						GregorianCalendar gc=new GregorianCalendar(); //��ǰ���ڵ�ǰһ��
						gc.setTime(new Date());
						gc.add(1, -2);	
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// ����ָ����ʽ�ĵ�ǰ����ʱ��
						String date = df.format(gc.getTime());						
						new OperationSqlData().DeleteSqlDataByYear(date);
					}
				});
				
				item_3.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						GregorianCalendar gc=new GregorianCalendar(); //��ǰ���ڵ�ǰһ��
						gc.setTime(new Date());
						gc.add(1, -3);	
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// ����ָ����ʽ�ĵ�ǰ����ʱ��
						String date = df.format(gc.getTime());						
						new OperationSqlData().DeleteSqlDataByYear(date);
					}
				});
				
				item_4.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						GregorianCalendar gc=new GregorianCalendar(); //��ǰ���ڵ�ǰ����
						gc.setTime(new Date());
						gc.add(1, -4);	
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// ����ָ����ʽ�ĵ�ǰ����ʱ��
						String date = df.format(gc.getTime());						
						new OperationSqlData().DeleteSqlDataByYear(date);
					}
				});
				
				item_5.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						GregorianCalendar gc=new GregorianCalendar(); //��ǰ���ڵ�ǰһ��
						gc.setTime(new Date());
						gc.add(1, -5);	
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// ����ָ����ʽ�ĵ�ǰ����ʱ��
						String date = df.format(gc.getTime());						
						new OperationSqlData().DeleteSqlDataByYear(date);
					}
				});
			}
		});
		button_4.setBorder(null);
		button_4.setFont(new Font("����", Font.PLAIN, 14));
		JToolBar toolBar = new JToolBar();
		toolBar.setToolTipText("����");
		toolBar.setRollover(true);
		toolBar.add(button_4);
		toolBar.setBounds(0, 0, screenWidth, 25);
		frame.getContentPane().add(toolBar);
		
		//�ָ�������
		toolBar.addSeparator();

		//��ӿ��������Ϣ
		button_5 = new JButton("����");
		button_5.setBorder(null);
		button_5.setFont(new Font("����", Font.PLAIN, 14));
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