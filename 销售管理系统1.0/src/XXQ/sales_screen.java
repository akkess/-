package XXQ;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.KeyEvent;

public class sales_screen {

	// ����SWT�ؼ�
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JComboBox<String> comboBox;
	private JTable table_1;
	private DefaultTableModel model;
	private int m = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					sales_screen window = new sales_screen(); // �½����۴���
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public sales_screen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {
		frame = new JFrame("���۹���"); // newһ��fram����
		// �Լ������������۹������СΪ�û���Ļ��һ�룬��λ����Ļ����
		int screenWidth = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width*2/3;
		int screenHeight = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height*2/3;
		frame.setBounds(screenWidth/6, screenHeight/6, screenWidth, screenHeight);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Ʒ��");// ����Ʒ��label����
		lblNewLabel.setFont(new Font("����", Font.BOLD, 20));
		lblNewLabel.setBounds((screenWidth - 880) / 2, (screenHeight - 400) / 2+100, 50, 20);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("����");// ���õ�λ�۸�label����
		lblNewLabel_1.setFont(new Font("����", Font.BOLD, 20));
		lblNewLabel_1.setBounds((screenWidth - 880) / 2, (screenHeight - 400) / 2+140 , 50, 20);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("��λ�۸�");// ��������label����
		lblNewLabel_2.setFont(new Font("����", Font.BOLD, 20));
		lblNewLabel_2.setBounds((screenWidth - 880) / 2, (screenHeight - 400) / 2+180 , 50, 20);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setEditable(false);
		textField_3.setBounds((screenWidth - 880) / 2 + 170, (screenHeight - 400) / 2 + 140, 60, 20);;
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);		

		comboBox = new JComboBox<String>();// ����comboxƷ����������
		new OperationSqlData().getGoodsNameToCombox(comboBox);
		comboBox.setBounds((screenWidth - 880) / 2 + 55, (screenHeight - 400) / 2 + 100, 180, 20);
		frame.getContentPane().add(comboBox);
		comboBox.setSelectedItem(null);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String unit = new OperationSqlData().getUnitByGoodsName(comboBox.getSelectedItem().toString());
				String sales_price = new OperationSqlData().getSalesPriceByGoodsName(comboBox.getSelectedItem().toString());	
				textField.setText(sales_price);
				textField_3.setText(unit);
				textField_1.setText(null);
			}
		});

		textField_1 = new JTextField();
		textField_1.setBounds((screenWidth - 880) / 2 + 55, (screenHeight - 400) / 2 + 140, 110, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		textField = new JTextField();// ����(����)Field��������
		textField.setBounds((screenWidth - 880) / 2 + 55, (screenHeight - 400) / 2 + 180, 110, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnNewButton_2 = new JButton(">>");// ����>>button���Լ��¼�����
		btnNewButton_2.setFont(new Font("����", Font.BOLD, 16));
		btnNewButton_2.setBounds((screenWidth - 880) / 2 + 55, (screenHeight - 400) / 2 + 220, 60, 20);
		frame.getContentPane().add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			// ���û���������ݡ�д��Jtable�У������ܽ��
			public void actionPerformed(ActionEvent arg0) {
				m = model.getRowCount();
				for(int q=0; q<m; q++)
				{
					if(model.getValueAt(q, 0).toString().equals(comboBox.getSelectedItem().toString()))
					{
						JOptionPane.showMessageDialog(null, "�������ӹ��ﳵ��");
					     return;
					}
				}
				if (comboBox.getSelectedItem().toString().isEmpty()||textField.getText().isEmpty()||textField_1.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "������λ����Ϊ�գ�");
					return;
				}
				
				model.addRow(new Object[4]);
				model.setValueAt(comboBox.getSelectedItem(), m, 0);
				model.setValueAt(textField_1.getText(), m, 1);
				model.setValueAt(textField_3.getText(), m, 2);
				model.setValueAt(textField.getText(), m, 3);
				model.setValueAt((float)(Math.round(Float.parseFloat(textField.getText()) * Float.parseFloat(textField_1.getText())*100))/100, m, 4);
			}
		});		
		
		JButton btnNewButton = new JButton("�ύ");// �����ύbutton���Լ��¼�����
		btnNewButton.setFont(new Font("����", Font.BOLD, 16));
		btnNewButton.setMnemonic(KeyEvent.VK_ENTER);
		btnNewButton.setBounds((screenWidth - 880) / 2 + 450, (screenHeight - 400) / 2 + 390, 80, 20);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new OperationSqlData().CommitSalesToSql(model, table_1);
				int j = model.getRowCount();
				while(j>0)
				{
					model.removeRow(j-1);
					j--;
				}
			}
		});

		JButton btnNewButton_1 = new JButton("ɾ��");// ����ɾ��button���Լ��¼�����
		btnNewButton_1.setFont(new Font("����", Font.BOLD, 16));
		btnNewButton_1.setBounds((screenWidth - 880) / 2 + 580, (screenHeight - 400) / 2 + 390, 80, 20);
		frame.getContentPane().add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table_1.getSelectedRow();
				if (row >= 0) {
					model.removeRow(row);
				}
			}
		});

		// ����model����һ��Jtable
		String[] header = { "Ʒ��", "����", "��λ", "��λ�۸�", "�ܼ�" };// ���ñ�ͷ
		Object[][] item = new Object[0][5];// ���õ���
		model = new DefaultTableModel(item, header) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};// ����һ��model��������model���ݲ��ɱ༭
		table_1 = new JTable(model);// ����model����һ��Jtable
		table_1.setFocusable(false);// �ر����ѡ�е�������


		JScrollPane scrollPane = new JScrollPane(table_1);// ����JScrollPane�������ԣ���Jtable����JScrollPane�У�ʵ�����ݳ����������ҹ������Ĺ���
		scrollPane.setLocation((screenWidth - 880) / 2 + 250, (screenHeight - 440) / 2);
		scrollPane.setSize(600, 400);
		frame.getContentPane().add(scrollPane);
		
	}	
	
}