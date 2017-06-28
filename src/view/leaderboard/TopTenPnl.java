package view.leaderboard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;

import view.listeners.AddListenerTopTenPnl;

public class TopTenPnl extends JPanel{
	private String session;
	private JTable topTenTbl;
	private JButton startGame;
	private JRadioButton defaultRBttn, wideRBttn, bigRBttn;
	private int boardRows, boardColumns;
	
	public TopTenPnl(String session){
		this.session=session;
		AddListenerTopTenPnl listener = new AddListenerTopTenPnl(this, session );
		//default board size
		boardRows = 20;
		boardColumns = 10;
		
		//setting the border for the table scroll pane
		TitledBorder tblBorder = getBorder("BEST 10 SCORES", 24);
		TitledBorder optionsBorder = getBorder("BOARD SIZE", 12);
		
		//setting the table up
		topTenTbl= new JTable(new TopTenTableModel());
		topTenTbl.setPreferredScrollableViewportSize(new Dimension(280, 250));
		topTenTbl.setCellSelectionEnabled(false);
		topTenTbl.setFont(new Font("Helvetica", Font.PLAIN, 18));
		topTenTbl.setShowGrid(false);
		topTenTbl.setRowHeight(25);
		
		topTenTbl.setBorder(BorderFactory.createEmptyBorder());
		
		topTenTbl.getTableHeader().setFont(new Font("Courier", Font.PLAIN, 18));
		topTenTbl.getTableHeader().setBackground(new Color(255,255,255));
		
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(JLabel.CENTER);
		renderer.setBackground(new Color(255,255,255));
		topTenTbl.setDefaultRenderer(Object.class, renderer);
		
		JScrollPane scrollPane = new JScrollPane(topTenTbl);
	    scrollPane.setBorder(tblBorder);
	    scrollPane.setBackground(new Color(255,255,255));
	    
	    //setting the start game button up and its JPanel
	    startGame = new JButton("Start game");
	    startGame.addActionListener(listener);
	    startGame.setActionCommand("start");
	    startGame.setVisible(true);
	    JPanel startPnl = new JPanel();
	    startPnl.setLayout(new BoxLayout(startPnl,BoxLayout.X_AXIS));
	    startPnl.add(startGame);
	    
	    //setting radio buttons for board size 
	    defaultRBttn = new JRadioButton("Default 20x10");
	    defaultRBttn.setActionCommand("default");
	    defaultRBttn.setSelected(true);

	    wideRBttn = new JRadioButton("Wide 20x20");
	    wideRBttn.setActionCommand("wide");

	    bigRBttn = new JRadioButton("Big 30x30");
	    bigRBttn.setActionCommand("big");

	    ButtonGroup boardOptions = new ButtonGroup();
	    boardOptions.add(defaultRBttn);
	    boardOptions.add(wideRBttn);
	    boardOptions.add(bigRBttn);
	    
	    defaultRBttn.addActionListener(listener);
	    wideRBttn.addActionListener(listener);
	    bigRBttn.addActionListener(listener);
	   
	    //setting up panel for the radio buttons
	    JPanel brdOptionsPnl = new JPanel();
	    brdOptionsPnl.setLayout(new BoxLayout(brdOptionsPnl,BoxLayout.Y_AXIS));
	    brdOptionsPnl.setBorder(optionsBorder);
	    brdOptionsPnl.add(defaultRBttn);
	    brdOptionsPnl.add(wideRBttn);
	    brdOptionsPnl.add(bigRBttn);
	    
	    JPanel bttmPnl = new JPanel();
	    bttmPnl.setLayout(new FlowLayout());
	    bttmPnl.add(startPnl);
	    bttmPnl.add(brdOptionsPnl);
	    
	    setLayout(new BorderLayout());
	    setBackground(new Color(255,255,255));
	    add(scrollPane,BorderLayout.CENTER);
	    add(bttmPnl, BorderLayout.PAGE_END);
	}


	
	// returns a TitleBorder given the title
	private TitledBorder getBorder(String title, int size) {
		return BorderFactory.createTitledBorder(
				BorderFactory.createEmptyBorder(), title, TitledBorder.CENTER,
				TitledBorder.ABOVE_TOP, new Font("Arial", Font.BOLD, size),
				new Color(0, 0, 0));
	}
}
