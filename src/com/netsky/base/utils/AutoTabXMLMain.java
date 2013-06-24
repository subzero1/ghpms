package com.netsky.base.utils;

import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;



/**
 * @description:生成对应表名的数据xml文件,点击运行,路径为/importConfig/
 * 
 * @class name:com.netsky.base.utils.AutoTabXMLMain
 * @author net Feb 25, 2013
 */
public class AutoTabXMLMain extends JFrame {
	private static final long serialVersionUID = -3652800940220556217L;
	private JTextField tableName;
	private JTextField pathField;
	private JLabel jl1;
	private JButton submitButton;
	private JButton clearButton;

	private JLabel tabNameLabel;
	private JLabel pathLabel;

	public AutoTabXMLMain() {
		this.setTitle("table xml自動生成");
		init();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);

		this.setBounds(0, 0, 355, 265);
		Image image = new ImageIcon("d:/xml.png").getImage();
		this.setIconImage(image);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void init() {
		Container con = this.getContentPane();
		jl1 = new JLabel();
		Image image1 = new ImageIcon("d:/balckground.jpg").getImage();
		jl1.setIcon(new ImageIcon(image1));
		jl1.setBounds(0, 0, 355, 265);
		tabNameLabel = new JLabel("表名:");
		tabNameLabel.setBounds(60, 70, 65, 20);
		tableName = new JTextField();
		tableName.setBounds(128, 70, 150, 25);
		pathLabel = new JLabel("路径:");
		pathLabel.setBounds(60, 110, 65, 20);
		pathField = new JTextField();
		pathField.setBounds(128, 110, 150, 25);

		submitButton = new JButton("提交");
		submitButton.setBounds(180, 180, 65, 20);

		clearButton = new JButton("清空");
		clearButton.setBounds(260, 180, 65, 20);

		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tabName = tableName.getText();
				XMLTableWrite xTableWrite = new XMLTableWrite();
				try {
					xTableWrite.autoGenerateTableXML(tabName);
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

		});

		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableName.setText("");
				pathField.setText("");
			}

		});

		jl1.add(submitButton);
		jl1.add(clearButton);
		con.add(jl1);
		con.add(tabNameLabel);
		con.add(tableName);
		con.add(pathLabel);
		con.add(pathField);

	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		AutoTabXMLMain qq = new AutoTabXMLMain();
	}

}