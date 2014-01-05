package gui;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class GUI extends JFrame {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 4314194701851509723L;
	private static final String	XLS				= ".xls";
	private static final String	GUTTER			= "-";
	private static final String	SPARE			= "/";
	private static final String	STRIKE			= "X";

	private JPanel				contentPane;
	private JPanel				optionPane		= new JPanel();
	private JPanel				panel;

	private JTextField			txt_w11;
	private JTextField			txt_w12;
	private JTextField			txt_w21;
	private JTextField			txt_w22;
	private JTextField			txt_w31;
	private JTextField			txt_w32;
	private JTextField			txt_w41;
	private JTextField			txt_w42;
	private JTextField			txt_w51;
	private JTextField			txt_w52;
	private JTextField			txt_w61;
	private JTextField			txt_w62;
	private JTextField			txt_w71;
	private JTextField			txt_w72;
	private JTextField			txt_w81;
	private JTextField			txt_w82;
	private JTextField			txt_w91;
	private JTextField			txt_w92;
	private JTextField			txt_w101;
	private JTextField			txt_w102;
	private JTextField			txt_w103;
	private JTextField			txt_Date;

	private JComboBox<String>	comboBox		= new JComboBox<String>();

	private JList<Score>		anz_scoreList	= new JList<Score>();
	private JList<String>		anz_nameList	= new JList<String>();
	private JLabel				lbl_Status		= new JLabel();
	private JLabel				lbl_Pfad		= new JLabel();

	private Score				sc				= new Score();
	private List<Score>			scoreList		= new ArrayList<Score>();
	private List<String>		nameList		= new ArrayList<String>();

	private File				excelFile;
	private JTextField			txt_name;
	private JButton				btnNameHinzufuegen;
	private JButton				btnHinzufuegen;
	private JButton				btnExportieren;
	private JButton				btnAuswaehlen;
	private JButton				btnAendern;
	private JButton				btnBearbeiten;
	private JButton				btnNamenLoeschen;
	private JButton				btnLoeschen;

	private JPanel				anz_panelNameList;

	// private String cfgPath = "C:\\Temp\\";
	private String				cfgPath			= System.getenv("appdata") + "\\bowling.cfg";
	private Config				config			= null;
	private File				flcfg			= new File(cfgPath);

	/**
	 * Create the frame.
	 * 
	 * @param cont
	 */
	public GUI() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				saveConfig();
			}
		});
		setTitle("Bowling");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 680);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 874, 589);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setLayout(null);

		comboBox.setBounds(20, 60, 200, 70);

		initializeTextFields();
		JScrollPane jScrollPane = new JScrollPane();
		jScrollPane.setBounds(4, 148, 855, 402);
		jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane.setViewportView(anz_scoreList);
		anz_scoreList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		optionPane.setBounds(10, 10, 688, 510);
		optionPane.setLayout(null);

		JPanel anz_panelFile = new JPanel();
		anz_panelFile.setBorder(new TitledBorder(null, "Excel Datei", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		anz_panelFile.setBounds(6, 11, 662, 53);
		anz_panelFile.setLayout(null);
		lbl_Pfad.setBounds(10, 16, 506, 30);

		lbl_Pfad.setBackground(Color.WHITE);
		lbl_Pfad.setOpaque(true);

		anz_panelNameList = new JPanel();
		anz_panelNameList.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Namens Liste", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		anz_panelNameList.setBounds(6, 75, 262, 198);
		anz_panelNameList.setLayout(null);

		txt_name = new JTextField();
		txt_name.setBounds(6, 16, 110, 30);
		txt_name.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 51, 250, 140);

		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		scrollPane.setViewportView(anz_nameList);

		initializeButtons();

		lbl_Status.setBounds(50, 600, 824, 30);

		JLabel lbl_anz_Status = new JLabel("Status");
		lbl_anz_Status.setBounds(0, 600, 50, 30);
		lbl_anz_Status.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(jScrollPane);
		panel.add(comboBox);
		panel.add(btnHinzufuegen);
		panel.add(btnAendern);
		panel.add(btnLoeschen);
		panel.add(btnExportieren);
		panel.add(txt_w11);
		panel.add(txt_w12);
		panel.add(txt_w21);
		panel.add(txt_w22);
		panel.add(txt_w31);
		panel.add(txt_w32);
		panel.add(txt_w41);
		panel.add(txt_w42);
		panel.add(txt_w51);
		panel.add(txt_w52);
		panel.add(txt_w61);
		panel.add(txt_w62);
		panel.add(txt_w71);
		panel.add(txt_w72);
		panel.add(txt_w81);
		panel.add(txt_w82);
		panel.add(txt_w91);
		panel.add(txt_w92);
		panel.add(txt_w101);
		panel.add(txt_w102);
		panel.add(txt_w103);
//		panel.add(txt_Date);

		anz_panelFile.add(btnAuswaehlen);
		anz_panelFile.add(lbl_Pfad);

		anz_panelNameList.add(txt_name);
		anz_panelNameList.add(btnNameHinzufuegen);
		anz_panelNameList.add(scrollPane);
		anz_panelNameList.add(btnBearbeiten);
		anz_panelNameList.add(btnNamenLoeschen);

		optionPane.add(anz_panelFile);
		optionPane.add(anz_panelNameList);

		tabbedPane.addTab("Allgemein", panel);
		tabbedPane.addTab("Optionen", optionPane);

		contentPane.add(tabbedPane);
		contentPane.add(lbl_Status);
		contentPane.add(lbl_anz_Status);

		loadConfig();
	}

	private void initializeButtons() {
		btnHinzufuegen = new JButton("Hinzufuegen");
		btnHinzufuegen.setBounds(255, 60, 120, 70);

		btnExportieren = new JButton("Exportieren");
		btnExportieren.setBounds(690, 60, 126, 70);

		btnAuswaehlen = new JButton("Auswaehlen");
		btnAuswaehlen.setBounds(526, 16, 130, 30);

		btnNameHinzufuegen = new JButton("");
		btnNameHinzufuegen.setIcon(null);
		btnNameHinzufuegen.setBounds(116, 16, 40, 30);

		btnAendern = new JButton("Aendern");
		btnAendern.setBounds(400, 60, 120, 70);

		btnBearbeiten = new JButton("");
		btnBearbeiten.setIcon(null);
		btnBearbeiten.setBounds(166, 16, 40, 30);

		btnNamenLoeschen = new JButton("");
		btnNamenLoeschen.setIcon(null);
		btnNamenLoeschen.setBounds(216, 16, 40, 30);

		btnLoeschen = new JButton("Loeschen");
		btnLoeschen.setBounds(545, 60, 120, 70);

		btnBearbeiten.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (anz_nameList.getSelectedValue() != null) {
					String selectedValue = anz_nameList.getSelectedValue();
					txt_name.setText(selectedValue);
					nameList.remove(selectedValue);
					anz_nameList.setListData(getStringArray(nameList));
					comboBox.removeItem(selectedValue);
				}
			}
		});

		btnNamenLoeschen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (anz_nameList.getSelectedValue() != null) {
					String selectedValue = anz_nameList.getSelectedValue();
					nameList.remove(selectedValue);
					anz_nameList.setListData(getStringArray(nameList));
					comboBox.removeItem(selectedValue);
				}
			}
		});

		btnAendern.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				aendern();
			}
		});

		btnExportieren.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (scoreList.size() > 0) {
					try {
						exportieren();
						updateStatus("Export erfolgreich!", null);
					} catch (IOException | WriteException e) {
						updateStatus("Excel Pfad nicht gefunden!", null);
					}
				}
			}
		});

		btnHinzufuegen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				hinzufuegen();

				for (JTextField txtField : getTextFelder()) {
					txtField.setText("");
					txtField.setEditable(true);
					txtField.setEnabled(true);
				}
//				lbl_sumGesamt.setText("");

			}
		});

		btnAuswaehlen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				excelFile = getFile();
				if (excelFile != null) {
					lbl_Pfad.setText(excelFile.getAbsolutePath());
					updateStatus("Dateipfad erfolgreich ausgewählt", null);
				} else {
					lbl_Pfad.setText("");
					updateStatus("Falschen Dateipfad ausgewählt", null);
				}
			}
		});

		btnNameHinzufuegen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String text = txt_name.getText();
				if (text.length() > 0 && !text.equals(" ")) {
					String name = text;
					if (!nameList.contains(name)) {
						nameList.add(name);
						anz_nameList.setListData(getStringArray(nameList));
						comboBox.addItem(name);
						txt_name.setText("");
						updateStatus("Name Hinzugefügt!", null);
					} else {
						txt_name.setText("");
						updateStatus("Name schon vorhanden!", null);
					}
				}
			}
		});
		
		btnLoeschen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Score selectedValue = anz_scoreList.getSelectedValue();
				
				if(selectedValue != null){
					scoreList.remove(selectedValue);
					anz_scoreList.setListData(getScoreArray(scoreList));
				}
			}
		});

	}

	private void initializeTextFields() {
		txt_w11 = new JTextField();
		txt_w11.setBounds(5, 5, 30, 30);
		txt_w11.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				String text = txt_w11.getText();
				checkInput(text, txt_w11, true);
				if (text.contains(STRIKE)) {
					wurf2(txt_w12);
					sc.setStr12("");
				} else {
					txt_w12.setEditable(true);
				}
				sc.setStr11(text);
			}
		});
		txt_w11.setColumns(10);

		txt_w12 = new JTextField();
		txt_w12.setBounds(35, 5, 30, 30);
		txt_w12.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				String text = txt_w12.getText();
				checkInput(text, txt_w12, false);
				sc.setStr12(text);
			}
		});
		txt_w12.setColumns(10);

		txt_w21 = new JTextField();
		txt_w21.setBounds(70, 5, 30, 30);

		txt_w21.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String text = txt_w21.getText();
				checkInput(text, txt_w21, true);
				if (txt_w21.getText().contains(STRIKE)) {
					txt_w22.setEditable(false);
					txt_w22.setText("");
					sc.setStr22("");
				} else {
					txt_w22.setEditable(true);
				}
				sc.setStr21(text);
			}
		});
		txt_w21.setColumns(10);

		txt_w22 = new JTextField();
		txt_w22.setBounds(100, 5, 30, 30);
		txt_w22.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				String text = txt_w22.getText();
				checkInput(text, txt_w22, false);
				sc.setStr22(text);
			}
		});
		txt_w22.setColumns(10);

		txt_w31 = new JTextField();
		txt_w31.setBounds(135, 5, 30, 30);
		txt_w31.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String text = txt_w31.getText();
				checkInput(text, txt_w31, true);
				if (text.contains(STRIKE)) {
					wurf2(txt_w32);
					sc.setStr32("");
				} else {
					txt_w32.setEditable(true);
				}
				sc.setStr31(text);
			}
		});
		txt_w31.setColumns(10);

		txt_w32 = new JTextField();
		txt_w32.setBounds(165, 5, 30, 30);
		txt_w32.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				String text = txt_w32.getText();
				checkInput(text, txt_w32, false);
				sc.setStr32(text);
			}
		});
		txt_w32.setColumns(10);

		txt_w41 = new JTextField();
		txt_w41.setBounds(200, 5, 30, 30);
		txt_w41.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String text = txt_w41.getText();
				checkInput(text, txt_w41, true);
				if (text.contains(STRIKE)) {
					wurf2(txt_w42);
					sc.setStr42("");
				} else {
					txt_w42.setEditable(true);
				}
				sc.setStr41(text);
			}
		});
		txt_w41.setColumns(10);

		txt_w42 = new JTextField();
		txt_w42.setBounds(230, 5, 30, 30);
		txt_w42.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				String text = txt_w42.getText();
				checkInput(text, txt_w42, false);
				sc.setStr42(text);
			}
		});
		txt_w42.setColumns(10);

		txt_w51 = new JTextField();
		txt_w51.setBounds(265, 5, 30, 30);
		txt_w51.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String text = txt_w51.getText();
				checkInput(text, txt_w51, true);
				if (text.contains(STRIKE)) {
					wurf2(txt_w52);
					sc.setStr52("");
				} else {
					txt_w52.setEditable(true);
				}
				sc.setStr51(text);
			}
		});
		txt_w51.setColumns(10);

		txt_w52 = new JTextField();
		txt_w52.setBounds(295, 5, 30, 30);
		txt_w52.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				String text = txt_w52.getText();
				checkInput(text, txt_w52, false);
				sc.setStr52(text);
			}
		});
		txt_w52.setColumns(10);

		txt_w61 = new JTextField();
		txt_w61.setBounds(330, 5, 30, 30);
		txt_w61.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String text = txt_w61.getText();
				checkInput(text, txt_w61, true);
				if (text.contains(STRIKE)) {
					wurf2(txt_w62);
					sc.setStr62("");
				} else {
					txt_w62.setEditable(true);
				}
				sc.setStr61(text);
			}
		});
		txt_w61.setColumns(10);

		txt_w62 = new JTextField();
		txt_w62.setBounds(360, 5, 30, 30);
		txt_w62.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				String text = txt_w62.getText();
				checkInput(text, txt_w62, false);
				sc.setStr62(text);
			}
		});
		txt_w62.setColumns(10);

		txt_w71 = new JTextField();
		txt_w71.setBounds(395, 5, 30, 30);
		txt_w71.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String text = txt_w71.getText();
				checkInput(text, txt_w71, true);
				if (text.contains(STRIKE)) {
					txt_w72.setEditable(false);
					txt_w72.setText("");
					sc.setStr72("");
				} else {
					txt_w72.setEditable(true);
				}
				sc.setStr71(text);
			}
		});
		txt_w71.setColumns(10);

		txt_w72 = new JTextField();
		txt_w72.setBounds(425, 5, 30, 30);
		txt_w72.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				String text = txt_w72.getText();
				checkInput(text, txt_w72, false);
				sc.setStr72(text);
			}
		});
		txt_w72.setColumns(10);

		txt_w81 = new JTextField();
		txt_w81.setBounds(460, 5, 30, 30);
		txt_w81.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String text = txt_w81.getText();
				checkInput(text, txt_w81, true);
				if (text.contains(STRIKE)) {
					txt_w82.setEditable(false);
					txt_w82.setText("");
					sc.setStr82("");
				} else {
					txt_w82.setEditable(true);
				}
				sc.setStr81(text);
			}
		});
		txt_w81.setColumns(10);

		txt_w82 = new JTextField();
		txt_w82.setBounds(490, 5, 30, 30);
		txt_w82.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				String text = txt_w82.getText();
				checkInput(text, txt_w82, false);
				sc.setStr82(text);
			}
		});
		txt_w82.setColumns(10);

		txt_w91 = new JTextField();
		txt_w91.setBounds(525, 5, 30, 30);
		txt_w91.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String text = txt_w91.getText();
				checkInput(text, txt_w91, true);
				if (text.contains(STRIKE)) {
					txt_w92.setEditable(false);
					txt_w92.setText("");
					sc.setStr92("");
				} else {
					txt_w92.setEditable(true);
				}
				sc.setStr91(text);
			}
		});
		txt_w91.setColumns(10);

		txt_w92 = new JTextField();
		txt_w92.setBounds(555, 5, 30, 30);
		txt_w92.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				String text = txt_w92.getText();
				checkInput(text, txt_w92, false);
				sc.setStr92(text);
			}
		});
		txt_w92.setColumns(10);

		txt_w101 = new JTextField();
		txt_w101.setBounds(590, 5, 30, 30);
		txt_w101.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				String text = txt_w101.getText();
				checkInput(text, txt_w101, true);
				if (text.contains(STRIKE)) {
					txt_w103.setEditable(true);
				} else if (txt_w102.getText().contains(STRIKE)) {
					txt_w103.setEditable(true);
				} else {
					txt_w103.setEditable(false);
					txt_w103.setText("");
					sc.setStr103("");
				}

				sc.setStr101(text);
			}
		});
		txt_w101.setColumns(10);

		txt_w102 = new JTextField();
		txt_w102.setBounds(620, 5, 30, 30);
		txt_w102.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				String text = txt_w102.getText();
				checkInput(text, txt_w102, true);
				if (txt_w101.getText().contains(STRIKE)) {
					txt_w103.setEditable(true);
				} else if (text.contains(STRIKE)) {
					txt_w103.setEditable(true);
				} else {
					txt_w103.setEditable(false);
				}
				sc.setStr102(text);
			}
		});
		txt_w102.setColumns(10);

		txt_w103 = new JTextField();
		txt_w103.setBounds(650, 5, 30, 30);
		txt_w103.setEditable(false);
		txt_w103.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				String text = txt_w103.getText();
				checkInput(text, txt_w103, true);
				sc.setStr103(text);
			}
		});
		txt_w103.setColumns(10);
		
		txt_Date = new JTextField();
		txt_Date.setEnabled(false);
		txt_Date.setVisible(false);
		txt_Date.setBackground(new Color(255, 255, 255));
		txt_Date.setEditable(false);
		txt_Date.setBounds(690, 5, 126, 30);
		txt_Date.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
//				txt_Date.setText(getStringDate());
				
			}
		});
		txt_Date.setColumns(10);
	}

//	private String getStringDate() {
//		DateChooser chooser = new DateChooser(this);
//		return chooser.select();

//		return DateFormat.getInstance().format(chooser.select()).substring(0, 8);
//	}

	private void aendern() {
		Score selectedScore = anz_scoreList.getSelectedValue();
		if (selectedScore != null) {

			String name = selectedScore.getName();
			if (!isNameinCBX(name)) {
				nameList.add(name);
				anz_nameList.setListData(getStringArray(nameList));
				comboBox.addItem(name);
			}
			comboBox.setSelectedItem(name);

			txt_w11.setText(selectedScore.getStr11());
			txt_w12.setText(selectedScore.getStr12());
			txt_w21.setText(selectedScore.getStr21());
			txt_w22.setText(selectedScore.getStr22());
			txt_w31.setText(selectedScore.getStr31());
			txt_w32.setText(selectedScore.getStr32());
			txt_w41.setText(selectedScore.getStr41());
			txt_w42.setText(selectedScore.getStr42());
			txt_w51.setText(selectedScore.getStr51());
			txt_w52.setText(selectedScore.getStr52());
			txt_w61.setText(selectedScore.getStr61());
			txt_w62.setText(selectedScore.getStr62());
			txt_w71.setText(selectedScore.getStr71());
			txt_w72.setText(selectedScore.getStr72());
			txt_w81.setText(selectedScore.getStr81());
			txt_w82.setText(selectedScore.getStr82());
			txt_w91.setText(selectedScore.getStr91());
			txt_w92.setText(selectedScore.getStr92());
			txt_w101.setText(selectedScore.getStr101());
			txt_w102.setText(selectedScore.getStr102());
			txt_w103.setText(selectedScore.getStr103());

			scoreList.remove(selectedScore);
			anz_scoreList.setListData(getScoreArray(scoreList));
		}

	}

	private void exportieren() throws IOException, RowsExceededException, WriteException {

		Map<String, Integer> map = new HashMap<String, Integer>();
		WritableWorkbook workbook = Workbook.createWorkbook(excelFile);
		WritableFont arial10font = new WritableFont(WritableFont.ARIAL, 10);
		WritableCellFormat arial10format = new WritableCellFormat(arial10font);
		arial10format.setWrap(true);

		for (Score sc : scoreList) {

			WritableSheet sheet = workbook.getSheet(sc.getName());

			if (sheet == null) {

				sheet = workbook.createSheet(sc.getName(), 0);
				map.put(sc.getName(), 0);

			} else {

				int value = map.get(sc.getName()) + 1;

				map.remove(sc.getName());
				map.put(sc.getName(), value);

			}

			List<String> labelList = sc.getList();
			int strCounter = 0;
			for (String str : labelList) {

				Label label = new Label(strCounter, map.get(sc.getName()), str, arial10format);
				sheet.addCell(label);
				strCounter++;
			}

			Label label = new Label(labelList.size(), map.get(sc.getName()), sc.getStrGesamt(), arial10format);
			sheet.addCell(label);
		}

		workbook.write();
		workbook.close();
	}

	private void hinzufuegen() {
		if (comboBox.getSelectedItem() != null) {
			Score score;
			if (sc.getStr61().length() > 0) {
				score = sc;
			} else {
				score = getScore();
			}

			sc.setName(comboBox.getSelectedItem().toString());
			Wurf w1 = wurfZusRechnen(score.getStr11(), score.getStr12(), "", "");
			Wurf w2 = wurfZusRechnen(score.getStr21(), score.getStr22(), score.getStr11(), score.getStr12());
			Wurf w3 = wurfZusRechnen(score.getStr31(), score.getStr32(), score.getStr21(), score.getStr22());
			Wurf w4 = wurfZusRechnen(score.getStr41(), score.getStr42(), score.getStr31(), score.getStr32());
			Wurf w5 = wurfZusRechnen(score.getStr51(), score.getStr52(), score.getStr41(), score.getStr42());
			Wurf w6 = wurfZusRechnen(score.getStr61(), score.getStr62(), score.getStr51(), score.getStr52());
			Wurf w7 = wurfZusRechnen(score.getStr71(), score.getStr72(), score.getStr61(), score.getStr62());
			Wurf w8 = wurfZusRechnen(score.getStr81(), score.getStr82(), score.getStr71(), score.getStr72());
			Wurf w9 = wurfZusRechnen(score.getStr91(), score.getStr92(), score.getStr81(), score.getStr82());
			Wurf w10 = wurfZusRechnenSpezial(score.getStr101(), score.getStr102(), score.getStr103(), score.getStr91(), score.getStr92());

			int summeGesamt = w1.getSumme() + w2.getSumme() + w3.getSumme() + w4.getSumme() + w5.getSumme() + w6.getSumme() + w7.getSumme() + w8.getSumme() + w9.getSumme() + w10.getSumme();

//			lbl_sumGesamt.setText(String.valueOf(summeGesamt));

			score.setStrGesamt(String.valueOf(summeGesamt));
			scoreList.add(score);
			anz_scoreList.setListData(getScoreArray(scoreList));
		}
	}

	private Wurf wurfZusRechnenSpezial(String wert1, String wert2, String wert3, String wertV1, String wertV2) {
		boolean strike = false;
		boolean spare = false;
		int summe = 0;

		if (wert1.length() == 0) {
			wert1 = "0";
		}
		if (wert2.length() == 0) {
			wert2 = "0";
		}
		if (wert3.length() == 0) {
			wert3 = "0";
		}

		if (wert1.contains(STRIKE)) {
			wert1 = "10";
			strike = true;
		}
		if (wert2.contains(STRIKE)) {
			wert2 = "10";
		}
		if (wert3.contains(STRIKE)) {
			wert3 = "10";
		}

		if (wert2.contains(SPARE)) {
			wert2 = (10 - Integer.parseInt(wert1)) + "";
			spare = true;
		}

		if (wertV2.contains(SPARE) || wertV1.contains(STRIKE)) {
			wert1 = (Integer.parseInt(wert1) * 2) + "";
		}

		if (strike || spare) {
			summe = Integer.parseInt(wert1) + Integer.parseInt(wert2) + Integer.parseInt(wert3);
		} else {
			summe = Integer.parseInt(wert1) + Integer.parseInt(wert2);
		}

		return new Wurf(strike, spare, summe);
	}

	private Wurf wurfZusRechnen(String wert1, String wert2, String wertV1, String wertV2) {
		boolean strike = false;
		boolean spare = false;
		int summe = 0;

		if (wert1.length() == 0) {
			wert1 = "0";
		}
		if (wert2.length() == 0) {
			wert2 = "0";
		}

		if (wert1.contains(STRIKE)) {
			wert1 = "10";
			strike = true;
		}
		if (wert2.contains(SPARE)) {
			wert2 = (10 - Integer.parseInt(wert1)) + "";
			spare = true;
		}

		if (wertV2.contains(SPARE) && !wertV1.contains(STRIKE)) {
			wert1 = (Integer.parseInt(wert1) * 2) + "";
		}

		summe = Integer.parseInt(wert1) + Integer.parseInt(wert2);

		if (wertV1.contains(STRIKE) && !wertV2.contains(SPARE)) {
			summe *= 2;
		}

		return new Wurf(strike, spare, summe);
	}

	private void wurf2(JTextField text_Field) {
		text_Field.setBackground(Color.WHITE);
		text_Field.setEditable(false);
		text_Field.setText("");
	}

	private void updateStatus(String status, JTextField textfield) {
		lbl_Status.setText(status);
		if (textfield != null) {
			textfield.setBackground(Color.RED);
		}
	}

	private void checkInput(String text, JTextField txt_Field, boolean ersterWurf) {
		if (text.length() > 1) {
			txt_Field.setText("");
			updateStatus("Error: Zu Viele Zeichen im rot hinterlegtem Feld", txt_Field);
		} else {
			txt_Field.setBackground(Color.WHITE);
		}

		boolean richtigeEingabe = false;
		;
		switch (text) {
		case GUTTER:
			richtigeEingabe = true;
			break;
		case "":
			richtigeEingabe = true;
		case "0":
			richtigeEingabe = true;
			break;
		case "1":
			richtigeEingabe = true;
			break;
		case "2":
			richtigeEingabe = true;
			break;
		case "3":
			richtigeEingabe = true;
			break;
		case "4":
			richtigeEingabe = true;
			break;
		case "5":
			richtigeEingabe = true;
			break;
		case "6":
			richtigeEingabe = true;
			break;
		case "7":
			richtigeEingabe = true;
			break;
		case "8":
			richtigeEingabe = true;
			break;
		case "9":
			richtigeEingabe = true;
			break;
		default:
			richtigeEingabe = false;
			break;
		}

		if (ersterWurf && !txt_Field.equals(txt_w102)) {
			if (text.equals(STRIKE)) {
				richtigeEingabe = true;
			} else if (text.equals(SPARE)) {
				richtigeEingabe = false;
			}
		} else if (!ersterWurf && !txt_Field.equals(txt_w102)) {
			if (text.equals(SPARE)) {
				richtigeEingabe = true;
			} else if (text.equals(STRIKE)) {
				richtigeEingabe = false;
			}
		} else {
			if (text.equals(SPARE)) {
				richtigeEingabe = true;
			} else if (text.equals(STRIKE)) {
				richtigeEingabe = true;
			}
		}

		if (richtigeEingabe) {
			txt_Field.setBackground(Color.WHITE);
		} else {
			txt_Field.setText("");
			updateStatus("Error: Falsche Eingabe im Roten Feld", txt_Field);
		}
	}

	private void importExcelDoc() {
		Workbook workbook = null;
		try {
			workbook = Workbook.getWorkbook(excelFile);
		} catch (BiffException | IOException e) {
			excelFile = null;
			lbl_Pfad.setText("");
			return;
		}

		List<Sheet> list = toList(workbook.getSheets());
		Score sc = new Score();
		List<Score> scores = new ArrayList<Score>();
		System.out.println("Try to Import.");

		for (Sheet s : list) {
			for (int i = 0; i < s.getRows(); i++) {
				sc.setName(s.getName());
				sc.setStr11(s.getRow(i)[0].getContents());
				sc.setStr12(s.getRow(i)[1].getContents());
				sc.setStr21(s.getRow(i)[2].getContents());
				sc.setStr22(s.getRow(i)[3].getContents());
				sc.setStr31(s.getRow(i)[4].getContents());
				sc.setStr32(s.getRow(i)[5].getContents());
				sc.setStr41(s.getRow(i)[6].getContents());
				sc.setStr42(s.getRow(i)[7].getContents());
				sc.setStr51(s.getRow(i)[8].getContents());
				sc.setStr52(s.getRow(i)[9].getContents());
				sc.setStr61(s.getRow(i)[10].getContents());
				sc.setStr62(s.getRow(i)[11].getContents());
				sc.setStr71(s.getRow(i)[12].getContents());
				sc.setStr72(s.getRow(i)[13].getContents());
				sc.setStr81(s.getRow(i)[14].getContents());
				sc.setStr82(s.getRow(i)[15].getContents());
				sc.setStr91(s.getRow(i)[16].getContents());
				sc.setStr92(s.getRow(i)[17].getContents());
				sc.setStr101(s.getRow(i)[18].getContents());
				sc.setStr102(s.getRow(i)[19].getContents());
				sc.setStr103(s.getRow(i)[20].getContents());
				sc.setStrGesamt(s.getRow(i)[21].getContents());

				System.out.println(sc.toString());

				scores.add(sc);
				scoreList.add(sc);
				sc = new Score();
			}
		}
		anz_scoreList.setListData(getScoreArray(scores));
	}

	private void loadConfig() {
		File file = new File(cfgPath);
		if (file.exists() && file.isFile()) {
			config = new Config(cfgPath);
			lbl_Pfad.setText(config.getProperty("ExcelPfad"));
			excelFile = new File(lbl_Pfad.getText());
			nameList.addAll(toList(config.getProperty("NamensListe")));
			if (nameList.size() > 0) {
				anz_nameList.setListData(getStringArray(nameList));
				for (String item : nameList) {
					comboBox.addItem(item);
				}
			}
		}
		if (excelFile.exists()) {
			importExcelDoc();
		}
	}

	private void saveConfig() {
		Writer fw;
		Writer bw = null;
		try {
			fw = new FileWriter(flcfg);
			bw = new BufferedWriter(fw);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String strConfig = "ExcelPfad=" + lbl_Pfad.getText().replace("\\", "\\\\") + "\n" + "NamensListe=" + getString(nameList);

		try {
			bw.write(strConfig);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.flush();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(strConfig);
	}

	private List<Sheet> toList(Sheet[] sheets) {
		List<Sheet> list = new ArrayList<Sheet>();

		for (int i = 0; i < sheets.length; i++) {
			list.add(sheets[i]);
		}
		return list;
	}

	private List<String> toList(String property) {
		List<String> strings = new ArrayList<String>();
		StringTokenizer tokenizer = new StringTokenizer(property, "|");

		while (tokenizer.hasMoreElements()) {
			strings.add((String) tokenizer.nextElement());
		}

		return strings;
	}

	private boolean isNameinCBX(String name) {
		boolean exist = false;
		for (int i = 0; i < comboBox.getItemCount(); i++) {
			if (comboBox.getItemAt(i).equals(name)) {
				exist = true;
			}
		}

		return exist;
	}

	private Score getScore() {
		Score sc = new Score();

		sc.setStr11(txt_w11.getText());
		sc.setStr12(txt_w12.getText());
		sc.setStr21(txt_w21.getText());
		sc.setStr22(txt_w22.getText());
		sc.setStr31(txt_w31.getText());
		sc.setStr32(txt_w32.getText());
		sc.setStr41(txt_w41.getText());
		sc.setStr42(txt_w42.getText());
		sc.setStr51(txt_w51.getText());
		sc.setStr52(txt_w52.getText());
		sc.setStr61(txt_w61.getText());
		sc.setStr62(txt_w62.getText());
		sc.setStr71(txt_w71.getText());
		sc.setStr72(txt_w72.getText());
		sc.setStr81(txt_w81.getText());
		sc.setStr82(txt_w82.getText());
		sc.setStr91(txt_w91.getText());
		sc.setStr92(txt_w92.getText());
		sc.setStr101(txt_w101.getText());
		sc.setStr102(txt_w102.getText());
		sc.setStr103(txt_w103.getText());

		sc.setName(comboBox.getSelectedItem().toString());

		return sc;
	}

	private Score[] getScoreArray(List<Score> scores2) {
		Score[] scs = new Score[scores2.size()];
		int counter = 0;
		for (Score s : scores2) {
			scs[counter] = s;
			counter++;
		}

		return scs;
	}

	private String[] getStringArray(List<String> strings) {
		String[] stringArray = new String[strings.size()];
		int counter = 0;
		for (String s : strings) {
			stringArray[counter] = s;
			counter++;
		}

		return stringArray;
	}

	private File getFile() {
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Datei auswählen");
		int indicator = fc.showOpenDialog(null);
		if (indicator == JFileChooser.CANCEL_OPTION || indicator == JFileChooser.ERROR_OPTION) {
			return null;
		} else if (!fc.getSelectedFile().getAbsolutePath().endsWith(XLS)) {
			return null;
		} else {
			return fc.getSelectedFile();
		}
	}

	private List<JTextField> getTextFelder() {
		List<JTextField> fields = new ArrayList<JTextField>();
		fields.add(txt_w11);
		fields.add(txt_w12);
		fields.add(txt_w21);
		fields.add(txt_w22);
		fields.add(txt_w31);
		fields.add(txt_w32);
		fields.add(txt_w41);
		fields.add(txt_w42);
		fields.add(txt_w51);
		fields.add(txt_w52);
		fields.add(txt_w61);
		fields.add(txt_w62);
		fields.add(txt_w71);
		fields.add(txt_w72);
		fields.add(txt_w81);
		fields.add(txt_w82);
		fields.add(txt_w91);
		fields.add(txt_w92);
		fields.add(txt_w101);
		fields.add(txt_w102);
		fields.add(txt_w103);

		return fields;
	}

	private String getString(List<String> nameList2) {
		String strList = "";

		for (String str : nameList2) {
			strList += str + "|";
		}

		if (strList.endsWith("|")) {
			strList = strList.substring(0, strList.length() - 1);
		}

		return strList;
	}
}