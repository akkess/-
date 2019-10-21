package XXQ;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class stock_screen {
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					stock_screen window = new stock_screen();  //���������������
					window.frame.setVisible(true);  //���ô���ɼ�
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public stock_screen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("��������");  //newһ��Jfram����
		int screenWidth = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width/2;;  //�����û���Ļ���Լ����������ؼ�����ʾλ��
		int screenHeight = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height/2;;
		
		frame.setBounds(screenWidth/2, screenHeight/2, screenWidth, screenHeight);
		frame.getContentPane().setLayout(null);	
		
		JLabel lblNewLabel = new JLabel("Ʒ��");  //����label�ؼ����ԣ�λ��
		lblNewLabel.setFont(new Font("����", Font.BOLD, 20));
		lblNewLabel.setBounds((screenWidth-180)/2, (screenHeight-180)/2, 50, 20);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("����");  //����label�ؼ����ԣ�λ��
		lblNewLabel_1.setFont(new Font("����", Font.BOLD, 20));
		lblNewLabel_1.setBounds((screenWidth-180)/2, (screenHeight-180)/2+30*1, 50, 20);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("��λ");  //����label�ؼ����ԣ�λ��
		lblNewLabel_2.setFont(new Font("����", Font.BOLD, 20));
		lblNewLabel_2.setBounds((screenWidth-180)/2, (screenHeight-180)/2+30*2, 50, 20);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("�ܼ�");  //����label�ؼ����ԡ�λ��
		lblNewLabel_3.setFont(new Font("����", Font.BOLD, 20));
		lblNewLabel_3.setBounds((screenWidth-180)/2, (screenHeight-180)/2+30*3, 50, 20);
		frame.getContentPane().add(lblNewLabel_3);
		
		textField = new JTextField();  //��������Field�ؼ����ԣ�λ��
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_2.setText(new OperationSqlData().getUnitByGoodsName(textField.getText()));
			}
		});
		textField.setBounds((screenWidth-180)/2+60, (screenHeight-180)/2, 180, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField(); //��������Field�ؼ����ԡ�λ��
		textField_1.setBounds((screenWidth-180)/2+60, (screenHeight-180)/2+30*1, 120, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField(); //��������ComboBox�ؼ����ԣ�λ�� ��Ĭ������������ѡ��
		textField_2.setBounds((screenWidth-180)/2+60, (screenHeight-180)/2+30*2, 120, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setText(new OperationSqlData().getUnitByGoodsName(textField.getText()));
		
		textField_3 = new JTextField();  //��������Field�ؼ����ԣ�λ��
		textField_3.setBounds((screenWidth-180)/2+60, (screenHeight-180)/2+30*3, 120, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		btnNewButton = new JButton("�ύ");
		btnNewButton.setMnemonic(KeyEvent.VK_ENTER);
		btnNewButton.setFont(new Font("����", Font.BOLD, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().isEmpty()||textField_1.getText().isEmpty()||textField_3.getText().isEmpty()||textField_2.getText().isEmpty()) //�����Ʒ���ֶ��Ƿ�Ϊ��
				{
				   JOptionPane.showMessageDialog(null, "������λ����Ϊ�գ�");
				   return;
				}
					int ret = new OperationSqlData().StockIn(textField.getText(), textField_1.getText(), (String) textField_2.getText(), textField_3.getText());
					if  (ret == 1)  //����SQL����ֵ�ƶ����н��������ʾ�Ի���
					{
						JOptionPane.showMessageDialog(null, "�洢�ɹ�");
						textField.setText(null);
						textField_1.setText(null);
						textField_2.setText(null);;
						textField_3.setText(null);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "�洢ʧ�ܣ�");
					}			
			}
 				
		});
		btnNewButton.setBounds((screenWidth-180)/2+60, (screenHeight-180)/2+30*3+40, 120, 20);
		frame.getContentPane().add(btnNewButton);
	}
}