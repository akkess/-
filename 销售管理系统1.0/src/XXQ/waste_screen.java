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
					waste_screen window = new waste_screen();  //创建进货窗体界面
					window.frame.setVisible(true);  //设置窗体可见
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
		frame = new JFrame("商品报废");  //new一个Jfram容器
		int screenWidth = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width/2;  //依据用户屏幕，自己主动调整控件的显示位置
		int screenHeight = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height/2;		
		frame.setBounds(screenWidth/2, screenHeight/2, screenWidth, screenHeight);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("品名");  //设置label控件属性。位置
		lblNewLabel.setFont(new Font("仿宋", Font.BOLD, 20));
		lblNewLabel.setBounds((screenWidth-180)/2, (screenHeight-140)/2, 50, 20);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("数量");  //设置label控件属性，位置
		lblNewLabel_1.setFont(new Font("仿宋", Font.BOLD, 20));
		lblNewLabel_1.setBounds((screenWidth-180)/2, (screenHeight-140)/2+40*1, 50, 20);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("成本");  //设置label控件属性，位置
		lblNewLabel_2.setFont(new Font("仿宋", Font.BOLD, 20));
		lblNewLabel_2.setBounds((screenWidth-180)/2, (screenHeight-140)/2+40*2, 50, 20);
		frame.getContentPane().add(lblNewLabel_2);
		
		comboBox = new JComboBox<String>();  //设置comboBox控件属性，位置
		new OperationSqlData().getGoodsNameToCombox(comboBox);
		comboBox.setSelectedItem(null);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//每次选择商品，先清空输入框，再抓取商品单位
				textField_1.setText(null);
				textField_2.setText(null);;
				textField_3.setText(null);				
				String unit = new OperationSqlData().getUnitByGoodsName(comboBox.getSelectedItem().toString());
				textField_3.setText(unit);
			}
		});
		comboBox.setBounds((screenWidth-180)/2+60, (screenHeight-140)/2, 180, 20);
		frame.getContentPane().add(comboBox);
		
		textField_1 = new JTextField(); //设置输入Field控件属性，位置
		textField_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedIndex() >= 0)
				{
					//抓取报废商品的库存成本
					float price = new OperationSqlData().getStockCostByGoodsName(comboBox.getSelectedItem().toString());
					float t_price = Float.parseFloat(textField_1.getText())*price;
					//保留两位小数
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
		
		btnNewButton = new JButton("提交");  //设置提交button属性，位置和提交事件处理
		btnNewButton.setFont(new Font("仿宋", Font.BOLD, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField_1.getText().isEmpty()||comboBox.getSelectedIndex()<0) //检查商品名字段是否为空
				{
					   JOptionPane.showMessageDialog(null, "输入栏位不能为空！");
					   return;
				}
				
				if (textField_2.getText().isEmpty()) //检查商品名字段是否为空
				{
					   JOptionPane.showMessageDialog(null, "先按Enter查看报废成本。");
					   return;
				}				
				
				int ret =new OperationSqlData().StockIn(comboBox.getSelectedItem().toString(), "-"+textField_1.getText(), textField_3.getText(), "-"+textField_2.getText());
				if  (ret == 1)  //依据SQL返回值推断运行结果，并显示对话框
				{
					JOptionPane.showMessageDialog(null, "报废成功！");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "报废失败！");
				}
									
			}
 				
		});
		btnNewButton.setBounds((screenWidth-180)/2+60, (screenHeight-140)/2+40*3, 120, 20);
		frame.getContentPane().add(btnNewButton);
	}
}