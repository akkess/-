package XXQ;

import java.awt.EventQueue;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.tree.DefaultTreeModel;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTable;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class report_screen {

	private JFrame frame;
	private JTable table;
	private JTree tree;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JSpinner spinner;
	private JSpinner spinner_1;	
	private JRadioButton checkBox;
	private String business;
	private DefaultTableModel model1;
	private DefaultTableModel model2;
	private DefaultTableModel model3;
	/**
	 * Launch the application.
	 */


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					report_screen window = new report_screen();
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
	public report_screen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {
		frame = new JFrame();
		int screenWidth = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;		
		frame.setBounds(0, 0, screenWidth, screenHeight);
		frame.getContentPane().setLayout(null);

		JLabel label = new JLabel("�_ʼ����");
		label.setFont(new Font("����", Font.BOLD, 16));
		label.setBounds(180, 20, 80, 20);
		frame.getContentPane().add(label);		

		JLabel label_1 = new JLabel("��������");
		label_1.setFont(new Font("����", Font.BOLD, 16));
		label_1.setBounds(390, 20, 80, 20);
		frame.getContentPane().add(label_1);	

		checkBox = new JRadioButton("��������");
		checkBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//���������ڲ��ɱ༭
				if(checkBox.isSelected() == true)
				{
					spinner.setEnabled(false);
					spinner_1.setEnabled(false);
				}
				else{
					spinner.setEnabled(true);
					spinner_1.setEnabled(true);
				}
			}
		});
		checkBox.setFont(new Font("����", Font.PLAIN, 16));
		checkBox.setBounds(600, 20, 100, 20);
		frame.getContentPane().add(checkBox);
		checkBox.setSelected(false);

		GregorianCalendar gc=new GregorianCalendar(); //��ǰ���ڵ�ǰһ����
		gc.setTime(new Date());
		gc.add(2, -1);		
		SpinnerModel dateModel=null;
		dateModel = new SpinnerDateModel(gc.getTime(), null, null,Calendar.DAY_OF_MONTH);
		spinner = new JSpinner();
		spinner.setFont(new Font("����", Font.BOLD, 14));
		spinner.setBounds(270, 20, 105, 20);
		frame.getContentPane().add(spinner);
		spinner.setModel(dateModel);  
		spinner.setEditor(new JSpinner.DateEditor(spinner,"yyyy-MM-dd"));		

		SpinnerModel dateModel_1=null;
		dateModel_1 = new SpinnerDateModel(new Date(), null, null,Calendar.DAY_OF_MONTH);
		spinner_1 = new JSpinner();
		spinner_1.setFont(new Font("����", Font.BOLD, 14));
		spinner_1.setBounds(480, 20, 105, 20);
		frame.getContentPane().add(spinner_1);
		spinner_1.setModel(dateModel_1);  
		spinner_1.setEditor(new JSpinner.DateEditor(spinner_1,"yyyy-MM-dd"));			

		//newһ����״������ѡ��ͬ�ı�������
		tree = new JTree();
		tree.setShowsRootHandles(true);
		tree.setModel(new DefaultTreeModel(
				new DefaultMutableTreeNode("����") {
					{
						DefaultMutableTreeNode node_1;
						node_1 = new DefaultMutableTreeNode("��¼");
						node_1.add(new DefaultMutableTreeNode("������¼"));					
						node_1.add(new DefaultMutableTreeNode("���ۼ�¼"));
						node_1.add(new DefaultMutableTreeNode("���ϼ�¼"));
						add(node_1);
						node_1 = new DefaultMutableTreeNode("���");
						node_1.add(new DefaultMutableTreeNode("��ǰ���"));
						add(node_1);
						node_1 = new DefaultMutableTreeNode("����");
						node_1.add(new DefaultMutableTreeNode("����ͳ��"));
						add(node_1);
					}
				}
				));
		tree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if(node == null)
					return;
				if (checkBox.isSelected()==false)  //����������ͬ�����б���
				{
					JOptionPane.showMessageDialog(null, "��������������");
					return;	
				}

				Object select_node = node.getUserObject();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// ����ָ����ʽ�ĵ�ǰʱ��

				switch (select_node.toString()) {
				case "������¼": //��������ץȡ������¼
					String[] header1 = { "����", "Ʒ��", "��������", "��λ", "������λ�۸�", "�����ܼ�"};// ���ñ�ͷ
					Object[][] item1 = new Object[0][6];// ���õ���
					model1 = new DefaultTableModel(item1, header1) {
						public boolean isCellEditable(int row, int column) {
							return false;
						}
					};// ����һ��model��������model���ݲ��ɱ༭
					table = new JTable(model1);// ����model����һ��Jtable
					table.setFocusable(false);// �ر����ѡ�е�������	
					scrollPane_1.setViewportView(table);

					int r1 = model1.getRowCount();
					while(r1>0)
					{
						model1.removeRow(r1-1);
						r1--;
					}

					Object[] obj1 = new Object[6];
					ArrayList<Object[]> list1 = new OperationSqlData().getStockRecordByDate(df.format(spinner.getValue())+" "+"00:00:00", df.format(spinner_1.getValue())+" "+"23:59:59",0 , false);
					int row1 = list1.size();
					float cost = 0;
					for(int i=0; i<row1; i++)
					{
						obj1 = list1.get(i);
						model1.addRow(new Object[6]);
						int j = model1.getRowCount();
						model1.setValueAt(obj1[0], j-1, 0);
						model1.setValueAt(obj1[1], j-1, 1);
						model1.setValueAt(obj1[2], j-1, 2);
						model1.setValueAt(obj1[3], j-1, 3);
						cost = Float.parseFloat(obj1[4].toString())/Float.parseFloat(obj1[2].toString());
						cost = (float)(Math.round(cost*100))/100;
						model1.setValueAt(cost, j-1, 4);
						model1.setValueAt(obj1[4], j-1, 5);
					}	
					business = "stock";
					reportMouseClick();
					break;
				case "���ۼ�¼"://��������ץȡ���ۼ�¼			
					String[] header2 = { "����", "Ʒ��", "��������", "��λ", "���۵�λ�۸�", "�����ܼ�"};// ���ñ�ͷ
					Object[][] item2 = new Object[0][6];// ���õ���
					model2 = new DefaultTableModel(item2, header2) {
						public boolean isCellEditable(int row, int column) {
							return false;
						}
					};// ����һ��model��������model���ݲ��ɱ༭
					table = new JTable(model2);// ����model����һ��Jtable
					table.setFocusable(false);// �ر����ѡ�е�������	
					scrollPane_1.setViewportView(table);

					int r2 = model2.getRowCount();
					while(r2>0)
					{
						model2.removeRow(r2-1);
						r2--;
					}

					Object[] obj2 = new Object[6];
					ArrayList<Object[]> list2 = new OperationSqlData().getSalesRecordByDate(df.format(spinner.getValue())+" "+"00:00:00", df.format(spinner_1.getValue())+" "+"23:59:59", false);
					int row2 = list2.size();
					for(int i=0; i<row2; i++)
					{
						obj2 = list2.get(i);
						model2.addRow(new Object[6]);
						int j = model2.getRowCount();
						model2.setValueAt(obj2[0], j-1, 0);
						model2.setValueAt(obj2[1], j-1, 1);
						model2.setValueAt(obj2[2], j-1, 2);
						model2.setValueAt(obj2[3], j-1, 3);
						model2.setValueAt(obj2[4], j-1, 4);
						model2.setValueAt(obj2[5], j-1, 5);
					}
					business = "sales";
					reportMouseClick();
					break;
				case "���ϼ�¼": //��������ץȡ���ϼ�¼
					String[] header3 = { "����", "Ʒ��", "��������", "��λ", "�ɱ���λ�۸�", "�����ܳɱ�"};// ���ñ�ͷ
					Object[][] item3 = new Object[0][6];// ���õ���
					model3 = new DefaultTableModel(item3, header3) {
						public boolean isCellEditable(int row, int column) {
							return false;
						}
					};// ����һ��model��������model���ݲ��ɱ༭
					table = new JTable(model3);// ����model����һ��Jtable
					table.setFocusable(false);// �ر����ѡ�е�������	
					scrollPane_1.setViewportView(table);

					int r3 = model3.getRowCount();
					while(r3>0)
					{
						model3.removeRow(r3-1);
						r3--;
					}

					Object[] obj3 = new Object[6];
					ArrayList<Object[]> list3 = new OperationSqlData().getStockRecordByDate(df.format(spinner.getValue())+" "+"00:00:00", df.format(spinner_1.getValue())+" "+"23:59:59",-1 , false);
					int row3 = list3.size();
					float cost1 = 0;
					for(int i=0; i<row3; i++)
					{
						obj3 = list3.get(i);
						model3.addRow(new Object[6]);
						int j = model3.getRowCount();
						model3.setValueAt(obj3[0], j-1, 0);
						model3.setValueAt(obj3[1], j-1, 1);
						model3.setValueAt(obj3[2], j-1, 2);
						model3.setValueAt(obj3[3], j-1, 3);
						cost1 = Float.parseFloat(obj3[4].toString())/Float.parseFloat(obj3[2].toString());
						model3.setValueAt(cost1, j-1, 4);
						model3.setValueAt(obj3[4], j-1, 5);
					}
					business = "stock_waste";
					reportMouseClick();
					break;
				case "��ǰ���":  //��������ץȡ�������Ϻ����ۼ�¼�����㵱ǰ���
					String[] header4 = {"Ʒ��", "�������", "��λ"};// ���ñ�ͷ
					Object[][] item4 = new Object[0][3];// ���õ���
					DefaultTableModel model4 = new DefaultTableModel(item4, header4) {
						public boolean isCellEditable(int row, int column) {
							return false;
						}
					};// ����һ��model��������model���ݲ��ɱ༭
					table = new JTable(model4);// ����model����һ��Jtable
					table.setFocusable(false);// �ر����ѡ�е�������	
					scrollPane_1.setViewportView(table);

					int r4 = model4.getRowCount();
					while(r4>0)
					{
						model4.removeRow(r4-1);
						r4--;
					}

					Object[] obj4 = new Object[6];
					Object[] obj4_sales = new Object[4];
					ArrayList<Object[]> list4 = new OperationSqlData().getStockRecordByDate(df.format(spinner.getValue())+" "+"00:00:00", df.format(spinner_1.getValue())+" "+"23:59:59",0 , true);
					ArrayList<Object[]> list4_sales = new OperationSqlData().getSalesRecordByDate("0000-00-00 00:00:00", "9999-99-99 99:99:99", true);
					int row4 = list4.size();
					int row4_sales = list4_sales.size();
					float stock_last = 0;
					for(int i=0; i<row4; i++)
					{
						model4.addRow(new Object[3]);
						int j = model4.getRowCount();
						obj4 = list4.get(i);

						for (int k=0; k<row4_sales; k++)
						{
							obj4_sales = list4_sales.get(k);
							if (obj4_sales[0].toString().equals(obj4[0].toString()))
							{
								stock_last = Float.parseFloat(obj4[1].toString()) - Float.parseFloat(obj4_sales[1].toString());
								model4.setValueAt(stock_last, j-1, 1);
								break;
							}
							else 
							{
								model4.setValueAt(obj4[1], j-1, 1);
							}	
						}

						if (row4_sales == 0)
							model4.setValueAt(obj4[1], j-1, 1);
						model4.setValueAt(obj4[0], j-1, 0);
						model4.setValueAt(obj4[2], j-1, 2);
					}						

					break;
				case "����ͳ��": //������������ ���ϳɱ� ͳ�����ھ�����
					String[] header5 = { "Ʒ��", "��������","��������","��λ", "ƽ�����۵�λ�۸�", "���ɱ���λ�۸�", "��������", "���ϳɱ�","������"};// ���ñ�ͷ
					Object[][] item5 = new Object[0][9];// ���õ���
					DefaultTableModel model5 = new DefaultTableModel(item5, header5) {
						public boolean isCellEditable(int row, int column) {
							return false;
						}
					};// ����һ��model��������model���ݲ��ɱ༭
					table = new JTable(model5);// ����model����һ��Jtable
					table.setFocusable(false);// �ر����ѡ�е�������	
					scrollPane_1.setViewportView(table);	
					int r5 = model5.getRowCount();
					while(r5>0)
					{
						model5.removeRow(r5-1);
						r5--;
					}

					Object[] obj5_sales = new Object[4];
					Object[] obj5_waste = new Object[9];
					Object[] obj5 = new Object[9];
					ArrayList<Object[]> list5_sales = new OperationSqlData().getSalesRecordByDate(df.format(spinner.getValue())+" "+"00:00:00", df.format(spinner_1.getValue())+" "+"23:59:59", true);
					ArrayList<Object[]> list5_waste = new OperationSqlData().getStockRecordByDate(df.format(spinner.getValue())+" "+"00:00:00", df.format(spinner_1.getValue())+" "+"23:59:59", -1, true);

					int row5_sales = list5_sales.size();
					int row5_waste = list5_waste.size();

					for (int i=0; i<row5_sales; i++)
					{
						obj5_sales = list5_sales.get(i);
						float price = new OperationSqlData().getStockCostByGoodsName(obj5_sales[0].toString());
						price =  (float)(Math.round(price*100))/100;
						obj5[0] = obj5_sales[0];
						obj5[1] = obj5_sales[1];
						obj5[2] = 0.00;
						obj5[3] = obj5_sales[2];
						obj5[4] =  (float)(Math.round((Float.parseFloat(obj5_sales[3].toString())/Float.parseFloat(obj5_sales[1].toString()))*100))/100;				
						obj5[5] = price;
						obj5[6] = new BigDecimal(Float.parseFloat(obj5[1].toString())*(Float.parseFloat(obj5[4].toString())-price)).setScale(2, RoundingMode.HALF_UP);
						obj5[7] = 0.00;
						obj5[8] = obj5[6];
						for (int j=0; j<row5_waste; j++)
						{
							obj5_waste = list5_waste.get(j);
							if (obj5_waste[0].equals(obj5_sales[0]))
							{
								obj5[2] = obj5_waste[1];
								obj5[7] = obj5_waste[3];
								obj5[8] =  new BigDecimal(Float.parseFloat(obj5[6].toString()) + Float.parseFloat(obj5[7].toString())).setScale(2, RoundingMode.HALF_UP);
								list5_waste.remove(j);
								row5_waste =  row5_waste - 1;
								break;
							}
						}	

						model5.addRow(new Object[9]);
						int m = model5.getRowCount();
						model5.setValueAt(obj5[0], m-1, 0);
						model5.setValueAt(obj5[1], m-1, 1);
						model5.setValueAt(obj5[2], m-1, 2);					
						model5.setValueAt(obj5[3], m-1, 3);
						model5.setValueAt(obj5[4], m-1, 4);
						model5.setValueAt(obj5[5], m-1, 5);	
						model5.setValueAt(obj5[6], m-1, 6);					
						model5.setValueAt(obj5[7], m-1, 7);
						model5.setValueAt(obj5[8], m-1, 8);							
					}

					if (row5_waste > 0)
					{
						for(int k=0; k<row5_waste; k++)
						{
							obj5_waste = list5_waste.get(k);	
							obj5[0] = obj5_waste[0];
							obj5[1] = 0.00;
							obj5[2] = obj5_waste[1];
							obj5[3] = obj5_waste[2];
							obj5[4] = 0.00;
							float price = new OperationSqlData().getStockCostByGoodsName(obj5_waste[0].toString());
							price = (float)(Math.round(price*100))/100;
							obj5[5] = price;
							obj5[6] = 0.0;
							obj5[7] = obj5_waste[3];
							obj5[8] = Float.parseFloat(obj5_waste[3].toString());

							model5.addRow(new Object[9]);
							int m = model5.getRowCount();
							model5.setValueAt(obj5[0], m-1, 0);
							model5.setValueAt(obj5[1], m-1, 1);
							model5.setValueAt(obj5[2], m-1, 2);					
							model5.setValueAt(obj5[3], m-1, 3);
							model5.setValueAt(obj5[4], m-1, 4);
							model5.setValueAt(obj5[5], m-1, 5);	
							model5.setValueAt(obj5[6], m-1, 6);					
							model5.setValueAt(obj5[7], m-1, 7);
							model5.setValueAt(obj5[8], m-1, 8);					
						}
					}
					//���һ�в��������ܼ�
					int row_last = model5.getRowCount();
					float sales_bft = 0;
					float stock_cost = 0;
					float last_bft = 0;
					for(int p=0; p<row_last; p++)
					{
						if (!(model5.getValueAt(p, 6)==null))
						{
							sales_bft =  sales_bft + Float.parseFloat(model5.getValueAt(p, 6).toString());
						}
						if (!(model5.getValueAt(p, 7)==null))
						{
							stock_cost = stock_cost + Float.parseFloat(model5.getValueAt(p, 7).toString());
						}
						if (!(model5.getValueAt(p, 8)==null))
						{
							last_bft = last_bft + Float.parseFloat(model5.getValueAt(p, 8).toString());
						}
					}
					sales_bft = (float)(Math.round(sales_bft*100))/100;
					stock_cost = (float)(Math.round(stock_cost*100))/100;
					last_bft = (float)(Math.round(last_bft*100))/100;


					model5.addRow(new Object[9]);
					model5.setValueAt("�ܼ�", row_last, 0);
					model5.setValueAt(sales_bft, row_last, 6);
					model5.setValueAt(stock_cost, row_last, 7);
					model5.setValueAt(last_bft, row_last, 8);
					break;					
				default:
					break;
				}
			}
		});

		scrollPane = new JScrollPane();
		scrollPane.setViewportView(tree);
		scrollPane.setBounds(10, 10, 160, screenHeight-10);
		frame.getContentPane().add(scrollPane);		

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(180, 50, screenWidth-200, screenHeight-50);
		frame.getContentPane().add(scrollPane_1);
	}

	class PopupActionListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			int answer = JOptionPane.showConfirmDialog(null,  "ɾ�����ݲ����ӱ�����㣬ȷ��Ҫɾ��ѡ�м�¼��", "�ύ��Ϣ",JOptionPane.YES_NO_OPTION);
			if (answer == 0)
			{
				int ret = 0;
				if (business.equals("stock"))
				{
					ret = new OperationSqlData().DeleteSqlDataByGoodsNameDate(model1.getValueAt(table.getSelectedRow(), 1).toString(), model1.getValueAt(table.getSelectedRow(), 0).toString(), "stock");
					model1.removeRow(table.getSelectedRow());
				}
				else if(business.equals("sales"))
				{
					ret = new OperationSqlData().DeleteSqlDataByGoodsNameDate(model2.getValueAt(table.getSelectedRow(), 1).toString(), model2.getValueAt(table.getSelectedRow(), 0).toString(), "sales");
					model2.removeRow(table.getSelectedRow());				
				}
				else if (business.equals("stock_waste"))
				{
					ret = new OperationSqlData().DeleteSqlDataByGoodsNameDate(model3.getValueAt(table.getSelectedRow(), 1).toString(), model3.getValueAt(table.getSelectedRow(), 0).toString(), "stock");	
					model3.removeRow(table.getSelectedRow());
				}

				if (ret == 1)
					JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
				else
					JOptionPane.showMessageDialog(null, "ɾ��ʧ��"); 
			}
		}
	}	

	ActionListener acitonListener = new PopupActionListener();   


	public void reportMouseClick()  //�Ҽ��ṩָ��������ɾ��ѡ�����ѡ���¼�
	{
		final JPopupMenu jp = new JPopupMenu();
		final JMenuItem item = jp.add("ɾ��������¼");
		item.addActionListener(acitonListener);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					// �����˵�
					if(table.getSelectedRow() > -1)
					{
						int y = table.getSelectedRow() * table.getRowHeight();
						if(e.getY() > y && e.getY() <= y+16)
						{
							jp.show(table, e.getX(), e.getY());
						}
					}
				}
			}
		});  
	}
}