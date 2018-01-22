package OOP;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import org.junit.experimental.theories.Theories;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextArea;
import javax.swing.JEditorPane;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import java.awt.Checkbox;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.chrono.MinguoDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Stack;
import java.util.concurrent.ExecutionException;

import javax.swing.JFormattedTextField;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.TextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;

public class GUI
{

	private JFrame frame;
	private static Stack<Total> st = new Stack<>();
	private static Stack<GUI_Filter> filters = new Stack<>();
	private static ArrayList<Reader> readers = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					GUI window = new GUI();
					window.frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI()
	{
		st.push(new Total());
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{

		frame = new JFrame();
		frame.setBounds(100, 100, 1568, 819);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 50, 100, 0, 0, 47, 55, 20, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 50, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0,
				Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);

		JLabel lblNewLabel = new JLabel("Add dir");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		frame.getContentPane().add(lblNewLabel, gbc_lblNewLabel);

		JFormattedTextField AddDir = new JFormattedTextField();
		GridBagConstraints gbc_AddDir = new GridBagConstraints();
		gbc_AddDir.fill = GridBagConstraints.HORIZONTAL;
		gbc_AddDir.insets = new Insets(0, 0, 5, 5);
		gbc_AddDir.gridx = 1;
		gbc_AddDir.gridy = 0;
		frame.getContentPane().add(AddDir, gbc_AddDir);

		JButton btnAdd = new JButton("Add");
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.insets = new Insets(0, 0, 5, 5);
		gbc_btnAdd.gridx = 2;
		gbc_btnAdd.gridy = 0;
		frame.getContentPane().add(btnAdd, gbc_btnAdd);

		JLabel lblFilters = new JLabel("Filters");
		GridBagConstraints gbc_lblFilters = new GridBagConstraints();
		gbc_lblFilters.insets = new Insets(0, 0, 5, 5);
		gbc_lblFilters.gridx = 3;
		gbc_lblFilters.gridy = 0;
		gbc_lblFilters.gridwidth = 3;
		frame.getContentPane().add(lblFilters, gbc_lblFilters);

		JLabel lblNot = new JLabel("Not");
		GridBagConstraints gbc_lblNot = new GridBagConstraints();
		gbc_lblNot.insets = new Insets(0, 0, 5, 5);
		gbc_lblNot.gridx = 7;
		gbc_lblNot.gridy = 0;
		frame.getContentPane().add(lblNot, gbc_lblNot);

		JLabel lblOr = new JLabel("OR");
		GridBagConstraints gbc_lblOr = new GridBagConstraints();
		gbc_lblOr.insets = new Insets(0, 0, 5, 5);
		gbc_lblOr.gridx = 8;
		gbc_lblOr.gridy = 0;
		frame.getContentPane().add(lblOr, gbc_lblOr);

		JLabel lblAnd = new JLabel("And");
		GridBagConstraints gbc_lblAnd = new GridBagConstraints();
		gbc_lblAnd.insets = new Insets(0, 0, 5, 5);
		gbc_lblAnd.gridx = 9;
		gbc_lblAnd.gridy = 0;
		frame.getContentPane().add(lblAnd, gbc_lblAnd);

		JLabel lblAlgorithms = new JLabel("Algorithms");
		GridBagConstraints gbc_lblAlgorithms = new GridBagConstraints();
		gbc_lblAlgorithms.insets = new Insets(0, 0, 5, 5);
		gbc_lblAlgorithms.gridx = 10;
		gbc_lblAlgorithms.gridy = 0;
		gbc_lblAlgorithms.gridwidth = 2;
		frame.getContentPane().add(lblAlgorithms, gbc_lblAlgorithms);

		JLabel lblMin = new JLabel("min");
		GridBagConstraints gbc_lblMin = new GridBagConstraints();
		gbc_lblMin.insets = new Insets(0, 0, 5, 5);
		gbc_lblMin.gridx = 4;
		gbc_lblMin.gridy = 1;
		frame.getContentPane().add(lblMin, gbc_lblMin);

		JLabel lblMax = new JLabel("max");
		GridBagConstraints gbc_lblMax = new GridBagConstraints();
		gbc_lblMax.insets = new Insets(0, 0, 5, 5);
		gbc_lblMax.gridx = 5;
		gbc_lblMax.gridy = 1;
		frame.getContentPane().add(lblMax, gbc_lblMax);

		JLabel lblAddCsv = new JLabel("Add CSV");
		GridBagConstraints gbc_lblAddCsv = new GridBagConstraints();
		gbc_lblAddCsv.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblAddCsv.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddCsv.gridx = 0;
		gbc_lblAddCsv.gridy = 2;
		frame.getContentPane().add(lblAddCsv, gbc_lblAddCsv);

		JFormattedTextField AddCSV = new JFormattedTextField();
		GridBagConstraints gbc_AddCSV = new GridBagConstraints();
		gbc_AddCSV.fill = GridBagConstraints.HORIZONTAL;
		gbc_AddCSV.insets = new Insets(0, 0, 5, 5);
		gbc_AddCSV.gridx = 1;
		gbc_AddCSV.gridy = 2;
		frame.getContentPane().add(AddCSV, gbc_AddCSV);

		JButton btnAdd_1 = new JButton("Add");
		btnAdd_1.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent arg0)
			{
			}
		});
		GridBagConstraints gbc_btnAdd_1 = new GridBagConstraints();
		gbc_btnAdd_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnAdd_1.gridx = 2;
		gbc_btnAdd_1.gridy = 2;
		frame.getContentPane().add(btnAdd_1, gbc_btnAdd_1);

		JLabel lblTime = new JLabel("Time");
		GridBagConstraints gbc_lblTime = new GridBagConstraints();
		gbc_lblTime.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTime.insets = new Insets(0, 0, 5, 5);
		gbc_lblTime.gridx = 3;
		gbc_lblTime.gridy = 2;
		frame.getContentPane().add(lblTime, gbc_lblTime);

		JFormattedTextField Filters_minTime = new JFormattedTextField();
		Filters_minTime.setText("min");
		GridBagConstraints gbc_Filters_minTime = new GridBagConstraints();
		gbc_Filters_minTime.fill = GridBagConstraints.HORIZONTAL;
		gbc_Filters_minTime.anchor = GridBagConstraints.WEST;
		gbc_Filters_minTime.insets = new Insets(0, 0, 5, 5);
		gbc_Filters_minTime.gridx = 4;
		gbc_Filters_minTime.gridy = 2;
		frame.getContentPane().add(Filters_minTime, gbc_Filters_minTime);

		JFormattedTextField Filters_maxTime = new JFormattedTextField();
		Filters_maxTime.setText("max");
		GridBagConstraints gbc_Filters_maxTime = new GridBagConstraints();
		gbc_Filters_maxTime.fill = GridBagConstraints.HORIZONTAL;
		gbc_Filters_maxTime.anchor = GridBagConstraints.WEST;
		gbc_Filters_maxTime.insets = new Insets(0, 0, 5, 5);
		gbc_Filters_maxTime.gridx = 5;
		gbc_Filters_maxTime.gridy = 2;
		frame.getContentPane().add(Filters_maxTime, gbc_Filters_maxTime);

		Checkbox CheckBox_Time = new Checkbox("");
		GridBagConstraints gbc_CheckBox_Time = new GridBagConstraints();
		gbc_CheckBox_Time.fill = GridBagConstraints.HORIZONTAL;
		gbc_CheckBox_Time.insets = new Insets(0, 0, 5, 5);
		gbc_CheckBox_Time.gridx = 6;
		gbc_CheckBox_Time.gridy = 2;
		frame.getContentPane().add(CheckBox_Time, gbc_CheckBox_Time);

		Checkbox NotTime = new Checkbox("");
		GridBagConstraints gbc_NotTime = new GridBagConstraints();
		gbc_NotTime.insets = new Insets(0, 0, 5, 5);
		gbc_NotTime.gridx = 7;
		gbc_NotTime.gridy = 2;
		frame.getContentPane().add(NotTime, gbc_NotTime);

		JFormattedTextField FirstAlgo_mac = new JFormattedTextField();
		FirstAlgo_mac.setText("mac");
		GridBagConstraints gbc_FirstAlgo_mac = new GridBagConstraints();
		gbc_FirstAlgo_mac.gridwidth = 2;
		gbc_FirstAlgo_mac.insets = new Insets(0, 0, 5, 5);
		gbc_FirstAlgo_mac.fill = GridBagConstraints.HORIZONTAL;
		gbc_FirstAlgo_mac.gridx = 10;
		gbc_FirstAlgo_mac.gridy = 2;
		frame.getContentPane().add(FirstAlgo_mac, gbc_FirstAlgo_mac);

		JButton FirstAlgoSubmit = new JButton("Submit");

		GridBagConstraints gbc_FirstAlgoSubmit = new GridBagConstraints();
		gbc_FirstAlgoSubmit.insets = new Insets(0, 0, 5, 0);
		gbc_FirstAlgoSubmit.gridx = 12;
		gbc_FirstAlgoSubmit.gridy = 2;
		frame.getContentPane().add(FirstAlgoSubmit, gbc_FirstAlgoSubmit);

		JLabel lblNewLabel_1 = new JLabel("Add Union CSV");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 3;
		frame.getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);

		JFormattedTextField AddUnionCSV = new JFormattedTextField();
		GridBagConstraints gbc_AddUnionCSV = new GridBagConstraints();
		gbc_AddUnionCSV.insets = new Insets(0, 0, 5, 5);
		gbc_AddUnionCSV.fill = GridBagConstraints.HORIZONTAL;
		gbc_AddUnionCSV.gridx = 1;
		gbc_AddUnionCSV.gridy = 3;
		frame.getContentPane().add(AddUnionCSV, gbc_AddUnionCSV);

		JButton Add_Union_CSV_Button = new JButton("Add");
		Add_Union_CSV_Button.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
			}
		});
		GridBagConstraints gbc_Add_Union_CSV_Button = new GridBagConstraints();
		gbc_Add_Union_CSV_Button.insets = new Insets(0, 0, 5, 5);
		gbc_Add_Union_CSV_Button.gridx = 2;
		gbc_Add_Union_CSV_Button.gridy = 3;
		frame.getContentPane().add(Add_Union_CSV_Button, gbc_Add_Union_CSV_Button);

		JRadioButton OR1 = new JRadioButton("");
		GridBagConstraints gbc_OR1 = new GridBagConstraints();
		gbc_OR1.insets = new Insets(0, 0, 5, 5);
		gbc_OR1.gridx = 8;
		gbc_OR1.gridy = 3;
		frame.getContentPane().add(OR1, gbc_OR1);

		JRadioButton And1 = new JRadioButton("");
		GridBagConstraints gbc_And1 = new GridBagConstraints();
		gbc_And1.insets = new Insets(0, 0, 5, 5);
		gbc_And1.gridx = 9;
		gbc_And1.gridy = 3;
		frame.getContentPane().add(And1, gbc_And1);

		JButton btnClearData = new JButton("Clear Data");
		GridBagConstraints gbc_btnClearData = new GridBagConstraints();
		gbc_btnClearData.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnClearData.insets = new Insets(0, 0, 5, 5);
		gbc_btnClearData.gridx = 0;
		gbc_btnClearData.gridy = 4;
		frame.getContentPane().add(btnClearData, gbc_btnClearData);

		JButton returnButton = new JButton("Return step");

		GridBagConstraints gbc_returnButton = new GridBagConstraints();
		gbc_returnButton.gridwidth = 2;
		gbc_returnButton.insets = new Insets(0, 0, 5, 5);
		gbc_returnButton.gridx = 1;
		gbc_returnButton.gridy = 4;
		frame.getContentPane().add(returnButton, gbc_returnButton);

		JLabel lblLat = new JLabel("Lat");
		GridBagConstraints gbc_lblLat = new GridBagConstraints();
		gbc_lblLat.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblLat.insets = new Insets(0, 0, 5, 5);
		gbc_lblLat.gridx = 3;
		gbc_lblLat.gridy = 4;
		frame.getContentPane().add(lblLat, gbc_lblLat);

		JFormattedTextField Filters_minLat = new JFormattedTextField();
		Filters_minLat.setText("0");
		GridBagConstraints gbc_Filters_minLat = new GridBagConstraints();
		gbc_Filters_minLat.insets = new Insets(0, 0, 5, 5);
		gbc_Filters_minLat.fill = GridBagConstraints.HORIZONTAL;
		gbc_Filters_minLat.gridx = 4;
		gbc_Filters_minLat.gridy = 4;
		frame.getContentPane().add(Filters_minLat, gbc_Filters_minLat);

		JFormattedTextField Filters_maxLat = new JFormattedTextField();
		Filters_maxLat.setText("0");
		GridBagConstraints gbc_Filters_maxLat = new GridBagConstraints();
		gbc_Filters_maxLat.insets = new Insets(0, 0, 5, 5);
		gbc_Filters_maxLat.fill = GridBagConstraints.HORIZONTAL;
		gbc_Filters_maxLat.gridx = 5;
		gbc_Filters_maxLat.gridy = 4;
		frame.getContentPane().add(Filters_maxLat, gbc_Filters_maxLat);

		Checkbox CheckBox_Location = new Checkbox("");
		GridBagConstraints gbc_CheckBox_Location = new GridBagConstraints();
		gbc_CheckBox_Location.fill = GridBagConstraints.HORIZONTAL;
		gbc_CheckBox_Location.gridheight = 3;
		gbc_CheckBox_Location.insets = new Insets(0, 0, 5, 5);
		gbc_CheckBox_Location.gridx = 6;
		gbc_CheckBox_Location.gridy = 4;
		frame.getContentPane().add(CheckBox_Location, gbc_CheckBox_Location);

		TextField AlgoOneOut = new TextField();
		AlgoOneOut.setEditable(false);
		GridBagConstraints gbc_AlgoOneOut = new GridBagConstraints();
		gbc_AlgoOneOut.gridwidth = 2;
		gbc_AlgoOneOut.fill = GridBagConstraints.HORIZONTAL;
		gbc_AlgoOneOut.insets = new Insets(0, 0, 5, 5);
		gbc_AlgoOneOut.gridx = 10;
		gbc_AlgoOneOut.gridy = 4;
		frame.getContentPane().add(AlgoOneOut, gbc_AlgoOneOut);

		JFormattedTextField filename = new JFormattedTextField();
		filename.setText("filename");
		GridBagConstraints gbc_filename = new GridBagConstraints();
		gbc_filename.insets = new Insets(0, 0, 5, 5);
		gbc_filename.fill = GridBagConstraints.HORIZONTAL;
		gbc_filename.gridx = 0;
		gbc_filename.gridy = 5;

		JTextPane txtpnData = new JTextPane();
		txtpnData.setEditable(false);
		txtpnData.setText("Data");
		GridBagConstraints gbc_txtpnData = new GridBagConstraints();
		gbc_txtpnData.gridheight = 3;
		gbc_txtpnData.gridwidth = 3;
		gbc_txtpnData.insets = new Insets(0, 0, 5, 5);
		gbc_txtpnData.fill = GridBagConstraints.BOTH;
		gbc_txtpnData.gridx = 0;
		gbc_txtpnData.gridy = 6;
		frame.getContentPane().add(txtpnData, gbc_txtpnData);

		JButton btnSaveCsv = new JButton("Save2 CSV");
		GridBagConstraints gbc_btnSaveCsv = new GridBagConstraints();
		gbc_btnSaveCsv.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSaveCsv.gridwidth = 2;
		gbc_btnSaveCsv.insets = new Insets(0, 0, 5, 5);
		gbc_btnSaveCsv.gridx = 1;
		gbc_btnSaveCsv.gridy = 5;

		frame.getContentPane().add(filename, gbc_filename);
		frame.getContentPane().add(btnSaveCsv, gbc_btnSaveCsv);
		JLabel lblLon = new JLabel("Lon");
		GridBagConstraints gbc_lblLon = new GridBagConstraints();
		gbc_lblLon.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblLon.insets = new Insets(0, 0, 5, 5);
		gbc_lblLon.gridx = 3;
		gbc_lblLon.gridy = 5;
		frame.getContentPane().add(lblLon, gbc_lblLon);

		JFormattedTextField Filters_minLon = new JFormattedTextField();
		Filters_minLon.setText("0");
		GridBagConstraints gbc_Filters_minLon = new GridBagConstraints();
		gbc_Filters_minLon.insets = new Insets(0, 0, 5, 5);
		gbc_Filters_minLon.fill = GridBagConstraints.HORIZONTAL;
		gbc_Filters_minLon.gridx = 4;
		gbc_Filters_minLon.gridy = 5;
		frame.getContentPane().add(Filters_minLon, gbc_Filters_minLon);

		JFormattedTextField Filters_maxLon = new JFormattedTextField();
		Filters_maxLon.setText("0");
		GridBagConstraints gbc_Filters_maxLon = new GridBagConstraints();
		gbc_Filters_maxLon.insets = new Insets(0, 0, 5, 5);
		gbc_Filters_maxLon.fill = GridBagConstraints.HORIZONTAL;
		gbc_Filters_maxLon.gridx = 5;
		gbc_Filters_maxLon.gridy = 5;
		frame.getContentPane().add(Filters_maxLon, gbc_Filters_maxLon);

		Checkbox notLocation = new Checkbox("");
		GridBagConstraints gbc_notLocation = new GridBagConstraints();
		gbc_notLocation.insets = new Insets(0, 0, 5, 5);
		gbc_notLocation.gridx = 7;
		gbc_notLocation.gridy = 5;
		frame.getContentPane().add(notLocation, gbc_notLocation);

		JFormattedTextField frmtdtxtfldMac_1 = new JFormattedTextField();
		frmtdtxtfldMac_1.setText("MAC1");
		GridBagConstraints gbc_frmtdtxtfldMac_1 = new GridBagConstraints();
		gbc_frmtdtxtfldMac_1.insets = new Insets(0, 0, 5, 5);
		gbc_frmtdtxtfldMac_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_frmtdtxtfldMac_1.gridx = 10;
		gbc_frmtdtxtfldMac_1.gridy = 5;
		frame.getContentPane().add(frmtdtxtfldMac_1, gbc_frmtdtxtfldMac_1);

		JFormattedTextField frmtdtxtfldSig = new JFormattedTextField();
		frmtdtxtfldSig.setText("Sig1");
		GridBagConstraints gbc_frmtdtxtfldSig = new GridBagConstraints();
		gbc_frmtdtxtfldSig.insets = new Insets(0, 0, 5, 5);
		gbc_frmtdtxtfldSig.fill = GridBagConstraints.HORIZONTAL;
		gbc_frmtdtxtfldSig.gridx = 11;
		gbc_frmtdtxtfldSig.gridy = 5;
		frame.getContentPane().add(frmtdtxtfldSig, gbc_frmtdtxtfldSig);

		JLabel lblAlt = new JLabel("Alt");
		GridBagConstraints gbc_lblAlt = new GridBagConstraints();
		gbc_lblAlt.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblAlt.insets = new Insets(0, 0, 5, 5);
		gbc_lblAlt.gridx = 3;
		gbc_lblAlt.gridy = 6;
		frame.getContentPane().add(lblAlt, gbc_lblAlt);

		JFormattedTextField Filters_minAlt = new JFormattedTextField();
		Filters_minAlt.setText("0");
		GridBagConstraints gbc_Filters_minAlt = new GridBagConstraints();
		gbc_Filters_minAlt.insets = new Insets(0, 0, 5, 5);
		gbc_Filters_minAlt.fill = GridBagConstraints.HORIZONTAL;
		gbc_Filters_minAlt.gridx = 4;
		gbc_Filters_minAlt.gridy = 6;
		frame.getContentPane().add(Filters_minAlt, gbc_Filters_minAlt);

		JFormattedTextField Filters_maxAlt = new JFormattedTextField();
		Filters_maxAlt.setText("0");
		GridBagConstraints gbc_Filters_maxAlt = new GridBagConstraints();
		gbc_Filters_maxAlt.insets = new Insets(0, 0, 5, 5);
		gbc_Filters_maxAlt.fill = GridBagConstraints.HORIZONTAL;
		gbc_Filters_maxAlt.gridx = 5;
		gbc_Filters_maxAlt.gridy = 6;
		frame.getContentPane().add(Filters_maxAlt, gbc_Filters_maxAlt);

		JFormattedTextField frmtdtxtfldMac_2 = new JFormattedTextField();
		frmtdtxtfldMac_2.setText("MAC2");
		GridBagConstraints gbc_frmtdtxtfldMac_2 = new GridBagConstraints();
		gbc_frmtdtxtfldMac_2.insets = new Insets(0, 0, 5, 5);
		gbc_frmtdtxtfldMac_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_frmtdtxtfldMac_2.gridx = 10;
		gbc_frmtdtxtfldMac_2.gridy = 6;
		frame.getContentPane().add(frmtdtxtfldMac_2, gbc_frmtdtxtfldMac_2);

		JFormattedTextField frmtdtxtfldSig_1 = new JFormattedTextField();
		frmtdtxtfldSig_1.setText("Sig2");
		GridBagConstraints gbc_frmtdtxtfldSig_1 = new GridBagConstraints();
		gbc_frmtdtxtfldSig_1.insets = new Insets(0, 0, 5, 5);
		gbc_frmtdtxtfldSig_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_frmtdtxtfldSig_1.gridx = 11;
		gbc_frmtdtxtfldSig_1.gridy = 6;
		frame.getContentPane().add(frmtdtxtfldSig_1, gbc_frmtdtxtfldSig_1);

		JButton btnSubmit_1 = new JButton("Submit");

		GridBagConstraints gbc_btnSubmit_1 = new GridBagConstraints();
		gbc_btnSubmit_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnSubmit_1.gridx = 12;
		gbc_btnSubmit_1.gridy = 6;
		frame.getContentPane().add(btnSubmit_1, gbc_btnSubmit_1);

		JRadioButton OR2 = new JRadioButton("");
		GridBagConstraints gbc_OR2 = new GridBagConstraints();
		gbc_OR2.insets = new Insets(0, 0, 5, 5);
		gbc_OR2.gridx = 8;
		gbc_OR2.gridy = 7;
		frame.getContentPane().add(OR2, gbc_OR2);

		JRadioButton And2 = new JRadioButton("");
		GridBagConstraints gbc_And2 = new GridBagConstraints();
		gbc_And2.insets = new Insets(0, 0, 5, 5);
		gbc_And2.gridx = 9;
		gbc_And2.gridy = 7;
		frame.getContentPane().add(And2, gbc_And2);

		JFormattedTextField frmtdtxtfldMac_3 = new JFormattedTextField();
		frmtdtxtfldMac_3.setText("MAC3");
		GridBagConstraints gbc_frmtdtxtfldMac_3 = new GridBagConstraints();
		gbc_frmtdtxtfldMac_3.insets = new Insets(0, 0, 5, 5);
		gbc_frmtdtxtfldMac_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_frmtdtxtfldMac_3.gridx = 10;
		gbc_frmtdtxtfldMac_3.gridy = 7;
		frame.getContentPane().add(frmtdtxtfldMac_3, gbc_frmtdtxtfldMac_3);

		JFormattedTextField frmtdtxtfldSig_2 = new JFormattedTextField();
		frmtdtxtfldSig_2.setText("Sig3");
		GridBagConstraints gbc_frmtdtxtfldSig_2 = new GridBagConstraints();
		gbc_frmtdtxtfldSig_2.insets = new Insets(0, 0, 5, 5);
		gbc_frmtdtxtfldSig_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_frmtdtxtfldSig_2.gridx = 11;
		gbc_frmtdtxtfldSig_2.gridy = 7;
		frame.getContentPane().add(frmtdtxtfldSig_2, gbc_frmtdtxtfldSig_2);

		JLabel lblId = new JLabel("ID");
		GridBagConstraints gbc_lblId = new GridBagConstraints();
		gbc_lblId.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblId.insets = new Insets(0, 0, 5, 5);
		gbc_lblId.gridx = 3;
		gbc_lblId.gridy = 8;
		frame.getContentPane().add(lblId, gbc_lblId);

		JFormattedTextField Filters_Name = new JFormattedTextField();
		Filters_Name.setText("Name");
		GridBagConstraints gbc_Filters_Name = new GridBagConstraints();
		gbc_Filters_Name.gridwidth = 2;
		gbc_Filters_Name.insets = new Insets(0, 0, 5, 5);
		gbc_Filters_Name.fill = GridBagConstraints.HORIZONTAL;
		gbc_Filters_Name.gridx = 4;
		gbc_Filters_Name.gridy = 8;
		frame.getContentPane().add(Filters_Name, gbc_Filters_Name);

		Checkbox CheckBox_name = new Checkbox("");
		GridBagConstraints gbc_CheckBox_name = new GridBagConstraints();
		gbc_CheckBox_name.fill = GridBagConstraints.HORIZONTAL;
		gbc_CheckBox_name.insets = new Insets(0, 0, 5, 5);
		gbc_CheckBox_name.gridx = 6;
		gbc_CheckBox_name.gridy = 8;
		frame.getContentPane().add(CheckBox_name, gbc_CheckBox_name);

		Checkbox not_name = new Checkbox("");
		GridBagConstraints gbc_not_name = new GridBagConstraints();
		gbc_not_name.insets = new Insets(0, 0, 5, 5);
		gbc_not_name.gridx = 7;
		gbc_not_name.gridy = 8;
		frame.getContentPane().add(not_name, gbc_not_name);

		JButton btnSaveToKml = new JButton("Save to KML");
		btnSaveToKml.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				newToKml.newKml(st.peek(), filename.getText());
			}
		});

		TextField AlgoResult = new TextField();
		AlgoResult.setEditable(false);
		GridBagConstraints gbc_AlgoResult = new GridBagConstraints();
		gbc_AlgoResult.gridheight = 2;
		gbc_AlgoResult.gridwidth = 3;
		gbc_AlgoResult.fill = GridBagConstraints.BOTH;
		gbc_AlgoResult.insets = new Insets(0, 0, 5, 0);
		gbc_AlgoResult.gridx = 10;
		gbc_AlgoResult.gridy = 8;
		frame.getContentPane().add(AlgoResult, gbc_AlgoResult);
		GridBagConstraints gbc_btnSaveToKml = new GridBagConstraints();
		gbc_btnSaveToKml.insets = new Insets(0, 0, 5, 5);
		gbc_btnSaveToKml.gridx = 0;
		gbc_btnSaveToKml.gridy = 9;
		frame.getContentPane().add(btnSaveToKml, gbc_btnSaveToKml);

		TextField FilterOperation = new TextField();
		GridBagConstraints gbc_FilterOperation = new GridBagConstraints();
		gbc_FilterOperation.fill = GridBagConstraints.BOTH;
		gbc_FilterOperation.gridwidth = 2;
		gbc_FilterOperation.insets = new Insets(0, 0, 5, 5);
		gbc_FilterOperation.gridx = 1;
		gbc_FilterOperation.gridy = 9;
		frame.getContentPane().add(FilterOperation, gbc_FilterOperation);

		JFormattedTextField FilterOutName = new JFormattedTextField();
		FilterOutName.setText("Filter Name");
		GridBagConstraints gbc_FilterOutName = new GridBagConstraints();
		gbc_FilterOutName.anchor = GridBagConstraints.NORTH;
		gbc_FilterOutName.gridwidth = 3;
		gbc_FilterOutName.insets = new Insets(0, 0, 5, 5);
		gbc_FilterOutName.fill = GridBagConstraints.HORIZONTAL;
		gbc_FilterOutName.gridx = 3;
		gbc_FilterOutName.gridy = 9;
		frame.getContentPane().add(FilterOutName, gbc_FilterOutName);
		GridBagConstraints gbc_btnFilter = new GridBagConstraints();
		gbc_btnFilter.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnFilter.gridwidth = 2;
		gbc_btnFilter.insets = new Insets(0, 0, 5, 5);
		gbc_btnFilter.gridx = 6;
		gbc_btnFilter.gridy = 9;

		JButton btnFilter = new JButton("Filter");
		frame.getContentPane().add(btnFilter, gbc_btnFilter);

		JButton returnFilter = new JButton("return filter");
		GridBagConstraints gbc_returnFilter = new GridBagConstraints();
		gbc_returnFilter.gridwidth = 2;
		gbc_returnFilter.insets = new Insets(0, 0, 5, 5);
		gbc_returnFilter.gridx = 8;
		gbc_returnFilter.gridy = 9;
		frame.getContentPane().add(returnFilter, gbc_returnFilter);

		JLabel lblOpenfilter = new JLabel("FilterName");
		GridBagConstraints gbc_lblOpenfilter = new GridBagConstraints();
		gbc_lblOpenfilter.insets = new Insets(0, 0, 5, 5);
		gbc_lblOpenfilter.gridx = 3;
		gbc_lblOpenfilter.gridy = 10;
		frame.getContentPane().add(lblOpenfilter, gbc_lblOpenfilter);

		TextField OpenBinaryFilter = new TextField();
		GridBagConstraints gbc_OpenBinaryFilter = new GridBagConstraints();
		gbc_OpenBinaryFilter.fill = GridBagConstraints.BOTH;
		gbc_OpenBinaryFilter.gridwidth = 4;
		gbc_OpenBinaryFilter.insets = new Insets(0, 0, 5, 5);
		gbc_OpenBinaryFilter.gridx = 4;
		gbc_OpenBinaryFilter.gridy = 10;
		frame.getContentPane().add(OpenBinaryFilter, gbc_OpenBinaryFilter);

		JButton btnUploadFilter = new JButton("Upload Filter");
		GridBagConstraints gbc_btnUploadFilter = new GridBagConstraints();
		gbc_btnUploadFilter.gridwidth = 2;
		gbc_btnUploadFilter.insets = new Insets(0, 0, 5, 5);
		gbc_btnUploadFilter.gridx = 8;
		gbc_btnUploadFilter.gridy = 10;
		frame.getContentPane().add(btnUploadFilter, gbc_btnUploadFilter);

		JButton btnSaveFilter = new JButton("Save Filter");
		btnSaveFilter.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				filters.peek().WriteGUI_Filter(OpenBinaryFilter.getText());
			}
		});
		GridBagConstraints gbc_btnSaveFilter = new GridBagConstraints();
		gbc_btnSaveFilter.anchor = GridBagConstraints.WEST;
		gbc_btnSaveFilter.insets = new Insets(0, 0, 5, 5);
		gbc_btnSaveFilter.gridx = 10;
		gbc_btnSaveFilter.gridy = 10;
		frame.getContentPane().add(btnSaveFilter, gbc_btnSaveFilter);

		TextArea FileOut = new TextArea();
		GridBagConstraints gbc_FileOut = new GridBagConstraints();
		gbc_FileOut.fill = GridBagConstraints.BOTH;
		gbc_FileOut.gridheight = 3;
		gbc_FileOut.gridwidth = 13;
		gbc_FileOut.gridx = 0;
		gbc_FileOut.gridy = 11;
		frame.getContentPane().add(FileOut, gbc_FileOut);

		btnAdd.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (st == null || st.empty() || st.peek() == null)
				{
					st = new Stack<>();
					st.push(new Total());
				}
				readers.add(new ReaDir(AddDir.getText(), st.peek()));
				Total newOne;
				try
				{
					newOne = GUI_Adapter.addFile(AddDir.getText(), st.peek());
					if (newOne != null)
					{
						st.push(newOne);
						txtpnData.setText(newOne.toString());
						FileOut.setText(newOne.printAll());
					}
				}
				catch (CloneNotSupportedException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnAdd_1.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					readers.add(new ReaderSingalCSV(AddCSV.getText() + ".csv", st.peek()));
					Total total = GUI_Adapter.addSignalFile(AddCSV.getText() + ".csv", st.peek());
					if (total != null)
					{
						st.push(total);
						txtpnData.setText(total.toString());
						FileOut.setText(total.printAll());
					}

				}
				catch (Exception e1)
				{
					System.err.println("GUI: " + e.toString());
				}
			}
		});
		btnClearData.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				readers.removeAll(readers);
				GUI.this.st = new Stack<>();
				txtpnData.setText("");
			}
		});
		btnSaveCsv.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				CreateCSV.createTotalbyGivenTotal(filename.getText() + ".csv", st.peek().printAll());
				txtpnData.setText(st.peek().toString());
				FileOut.setText(st.peek().printAll());
			}
		});
		btnFilter.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				String name = Filters_Name.getText();
				String minDate = Filters_minTime.getText();
				String maxDate = Filters_maxTime.getText();
				Date min = null;
				Date max = null;
				double minLat = 0;
				double maxLat = 0;
				double minLon = 0;
				double maxLon = 0;
				double minAlt = 0;
				double maxAlt = 0;
				double[] minL = new double[3];
				double[] maxL = new double[3];
				try
				{
					minLat = Double.parseDouble(Filters_minLat.getText());
					maxLat = Double.parseDouble(Filters_maxLat.getText());
					minLon = Double.parseDouble(Filters_minLon.getText());
					maxLon = Double.parseDouble(Filters_maxLon.getText());
					minAlt = Double.parseDouble(Filters_minAlt.getText());
					maxAlt = Double.parseDouble(Filters_maxAlt.getText());
					minL[0] = minLat;
					minL[1] = minLon;
					minL[2] = minAlt;
					maxL[0] = maxLat;
					maxL[1] = maxLon;
					maxL[2] = maxAlt;
					min = Total.covertStringToDate(minDate);
					max = Total.covertStringToDate(maxDate);
				}
				catch (Exception ex)
				{
					System.err.println(ex.toString());
				}
				finally
				{
					Total total = null;
					if (NotTime.getState() && CheckBox_Time.getState()
							|| CheckBox_Location.getState() && notLocation.getState()
							|| CheckBox_name.getState() && not_name.getState() || OR1.isSelected() && And1.isSelected()
							|| OR2.isSelected() && And2.isSelected())
					{
						FileOut.setText("Posotive and Negative Toghter is meaningless, please do not make this"
								+ System.lineSeparator() + "Also Or and And operation togher is meaningless");
					}
					else
					{
						GUI_Filter gFilter = new GUI_Filter(CheckBox_Time.getState(), CheckBox_Location.getState(),
								CheckBox_name.getState(), NotTime.getState(), notLocation.getState(),
								not_name.getState(), OR1.isSelected(), OR2.isSelected(), And1.isSelected(),
								And2.isSelected(), min, max, name, minL, maxL);
						filters.push(gFilter);
						total = gFilter.filter(st.peek());
						if (total != null)
						{
							FileOut.setText(total.printAll());
							txtpnData.setText(total.toString());
							st.push(total);
							System.out.println(total.toString());
							FilterOperation.setText(gFilter.toString());
						}

					}
				}
			}
		});

		btnSubmit_1.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent arg0)
			{
				ArrayList<Integer> inputs = new ArrayList<>();
				inputs.add(Integer.parseInt(frmtdtxtfldSig.getText()));
				inputs.add(Integer.parseInt(frmtdtxtfldSig_1.getText()));
				inputs.add(Integer.parseInt(frmtdtxtfldSig_2.getText()));
				double[] loc = Algorithm2.getUserLocation(inputs, st.peek().getTotal());
				AlgoResult.setText(Arrays.toString(loc));
			}
		});
		returnButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (st != null && !st.isEmpty())
				{
					GUI.this.st.pop();
					if (!st.isEmpty())
					{
						if (st.peek() != null)
						{
							txtpnData.setText(st.peek().toString());
							System.out.println(st.peek().toString());
							FileOut.setText(st.peek().printAll());
						}
					}
					else
					{
						txtpnData.setText("");
					}
				}
				else
				{
					st = new Stack<>();
					st.push(new Total());
				}

			}
		});

		Add_Union_CSV_Button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					String line = null;
					FileReader fileReader;
					fileReader = new FileReader(AddUnionCSV.getText() + ".csv");
					readers.add(new ReadUnionCSV(AddUnionCSV.getText() + ".csv", st.peek()));
					BufferedReader reader = new BufferedReader(fileReader);
					Total newTotal = st.peek().clone();
					while ((line = reader.readLine()) != null)
					{
						newTotal.insertTotalLineToSpecificTotal(line);
					}
					st.push(newTotal);
					txtpnData.setText(newTotal.toString());
					FileOut.setText(newTotal.printAll());

				}
				catch (CloneNotSupportedException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				catch (FileNotFoundException e1)
				{
					System.err.println(e1.toString());
				}
				catch (Exception e2)
				{
					System.err.println(e2.toString());
				}
			}
		});
		FirstAlgoSubmit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				double[] loc = Algorithm1.getMacLocation(FirstAlgo_mac.getText(), 10, st.peek().getTotal());
				AlgoOneOut.setText(Arrays.toString(loc));
			}
		});

		returnFilter.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if (filters == null || filters.isEmpty() || filters.empty())
				{
					FilterOperation.setText(FilterOperation.getText() + "\r\n There is no Filter to return to");
				}
				else
				{
					GUI_Filter guiFilter = filters.pop();
					Filters_minTime.setText(Total.dateToString(guiFilter.getMinDate()));
					Filters_maxTime.setText(Total.dateToString(guiFilter.getMaxDate()));

					Filters_maxAlt.setText(Double.toString(guiFilter.getMaxLoc()[2]));
					Filters_maxLat.setText(Double.toString(guiFilter.getMaxLoc()[0]));
					Filters_maxLon.setText(Double.toString(guiFilter.getMaxLoc()[1]));
					Filters_minAlt.setText(Double.toString(guiFilter.getMinLoc()[2]));
					Filters_minLat.setText(Double.toString(guiFilter.getMinLoc()[0]));
					Filters_minLon.setText(Double.toString(guiFilter.getMinLoc()[1]));

					Filters_Name.setText(guiFilter.getId());

					notLocation.setState(guiFilter.isNotLocOn());
					CheckBox_Location.setState((guiFilter.isLocOn()));

					Boolean notName = guiFilter.isNotIdOn();
					not_name.setState(notName);
					CheckBox_Location.setState(guiFilter.isLocOn());

					OR1.setSelected(guiFilter.isOrOneOn());
					OR2.setSelected(guiFilter.isOrTwoOn());

					And1.setSelected(guiFilter.isAndOneOn());
					And2.setSelected(guiFilter.isAndTwoOn());

				}
			}
		});

		btnUploadFilter.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				try
				{
					GUI_Filter filter = GUI_Filter.ReadFile(OpenBinaryFilter.getText());
					filters.push(filter);
					if (filters == null || filters.isEmpty() || filters.empty())
					{
						FilterOperation.setText(FilterOperation.getText() + "\r\n There is no Filter to return to");
					}
					else
					{
						GUI_Filter guiFilter = filters.peek();
						Filters_minTime.setText(Total.dateToString(guiFilter.getMinDate()));
						Filters_maxTime.setText(Total.dateToString(guiFilter.getMaxDate()));

						Filters_maxAlt.setText(Double.toString(guiFilter.getMaxLoc()[2]));
						Filters_maxLat.setText(Double.toString(guiFilter.getMaxLoc()[0]));
						Filters_maxLon.setText(Double.toString(guiFilter.getMaxLoc()[1]));
						Filters_minAlt.setText(Double.toString(guiFilter.getMinLoc()[2]));
						Filters_minLat.setText(Double.toString(guiFilter.getMinLoc()[0]));
						Filters_minLon.setText(Double.toString(guiFilter.getMinLoc()[1]));

						Filters_Name.setText(guiFilter.getId());

						notLocation.setState(guiFilter.isNotLocOn());
						CheckBox_Location.setState((guiFilter.isLocOn()));

						Boolean notName = guiFilter.isNotIdOn();
						not_name.setState(notName);
						CheckBox_Location.setState(guiFilter.isLocOn());

						OR1.setSelected(guiFilter.isOrOneOn());
						OR2.setSelected(guiFilter.isOrTwoOn());

						And1.setSelected(guiFilter.isAndOneOn());
						And2.setSelected(guiFilter.isAndTwoOn());

					}
				}
				catch (ClassNotFoundException | IOException e)
				{
					e.printStackTrace();
				}
			}
		});
		
		Thread t = new Thread(new Runnable()
		{
			
			@Override
			public void run()
			{
				while (true)
				{
					if (checkIsModified())
					{
						txtpnData.setText(st.peek().toString());
						FileOut.setText(st.peek().printAll());

					}
					else {
						try
						{
							Thread.sleep(5000);
						}
						catch (InterruptedException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
			}
		});
		t.start();
	}

	public static Date lastModified(String path)
	{
		File file = new File(path);
		return new Date(file.lastModified());
	}

	public static Boolean checkIsModified()
	{
		for (int i = 0; i < GUI.readers.size(); i++)
		{
			Reader reader = GUI.readers.get(i);
			Date last = lastModified(reader.getFileName());
			if (!reader.getLastModified().equals(last))
			{
				System.out.println("modified");
				reader.setLastModified(last);
				GUI.st.push(reader.Read());
				return true;
			}
		}
		return false;
	}
}
