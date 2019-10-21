package XXQ;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.SwingConstants;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.awt.event.ActionEvent;

public class waste_screen {
	private JFrame frame;
	private JComboBox<String> comboBox;
	private JTextField textField_1;
	private JTextField textField_2;
	private JButton btnNewButton;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					waste_screen window = new waste_screen();  //���������������
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
	public waste_screen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("��Ʒ����");  //newһ��Jfram����
		int screenWidth = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width/2;  //�����û���Ļ���Լ����������ؼ�����ʾλ��
		int screenHeight = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height/2;		
		frame.setBounds(screenWidth/2, screenHeight/2, screenWidth, screenHeight);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ʒ��");  //����label�ؼ����ԡ�λ��
		lblNewLabel.setFont(new Font("����", Font.BOLD, 20));
		lblNewLabel.setBounds((screenWidth-180)/2, (screenHeight-140)/2, 50, 20);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("����");  //����label�ؼ����ԣ�λ��
		lblNewLabel_1.setFont(new Font("����", Font.BOLD, 20));
		lblNewLabel_1.setBounds((screenWidth-180)/2, (screenHeight-140)/2+40*1, 50, 20);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("�ɱ�");  //����label�ؼ����ԣ�λ��
		lblNewLabel_2.setFont(new Font("����", Font.BOLD, 20));
		lblNewLabel_2.setBounds((screenWidth-180)/2, (screenHeight-140)/2+40*2, 50, 20);
		frame.getContentPane().add(lblNewLabel_2);
		
		comboBox = new JComboBox<String>();  //����comboBox�ؼ����ԣ�λ��
		new OperationSqlData().getGoodsNameToCombox(comboBox);
		comboBox.setSelectedItem(null);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//ÿ��ѡ����Ʒ��������������ץȡ��Ʒ��λ
				textField_1.setText(null);
				textField_2.setText(null);;
				textField_3.setText(null);				
				String unit = new OperationSqlData().getUnitByGoodsName(comboBox.getSelectedItem().toString());
				textField_3.setText(unit);
			}
		});
		comboBox.setBounds((screenWidth-180)/2+60, (screenHeight-140)/2, 180, 20);
		frame.getContentPane().add(comboBox);
		
		textField_1 = new JTextField(); //��������Field�ؼ����ԣ�λ��
		textField_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedIndex() >= 0)
				{
					//ץȡ������Ʒ�Ŀ��ɱ�
					float price = new OperationSqlData().getStockCostByGoodsName(comboBox.getSelectedItem().toString());
					float t_price = Float.parseFloat(textField_1.getText())*price;
					//������λС��
					BigDecimal bd = new BigDecimal(t_price);
					bd = bd.setScale(2, RoundingMode.HALF_UP);
					textField_2.setText(bd.toString());
				}
			}
		});
		textField_1.setBounds((screenWidth-180)/2+60, (screenHeight-140)/2+40*1, 120, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds((screenWidth-180)/2+60, (screenHeight-140)/2+40*2, 120, 20);
		frame.getContentPane().add(textField_2);

		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setEditable(false);
		textField_3.setBounds((screenWidth-180)/2+190, (screenHeight-140)/2+40*1, 50, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);		
		
		btnNewButton = new JButton("�ύ");  //�����ύbutton���ԣ�λ�ú��ύ�¼�����
		btnNewButton.setFont(new Font("����", Font.BOLD, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField_1.getText().isEmpty()||comboBox.getSelectedIndex()<0) //�����Ʒ���ֶ��Ƿ�Ϊ��
				{
					   JOptionPane.showMessageDialog(null, "������λ����Ϊ�գ�");
					   return;
				}
				
				if (textField_2.getText().isEmpty()) //�����Ʒ���ֶ��Ƿ�Ϊ��
				{
					   JOptionPane.showMessageDialog(null, "�Ȱ�Enter�鿴���ϳɱ���");
					   return;
				}				
				
				int ret =new OperationSqlData().StockIn(comboBox.getSelectedItem().toString(), "-"+textField_1.getText(), textField_3.getText(), "-"+textField_2.getText());
				if  (ret == 1)  //����SQL����ֵ�ƶ����н��������ʾ�Ի���
				{
					JOptionPane.showMessageDialog(null, "���ϳɹ���");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "����ʧ�ܣ�");
				}
									
			}
 				
		});
		btnNewButton.setBounds((screenWidth-180)/2+60, (screenHeight-140)/2+40*3, 120, 20);
		frame.getContentPane().add(btnNewButton);
	}
}