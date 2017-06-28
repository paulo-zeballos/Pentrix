package view.leaderboard;

import javax.swing.table.AbstractTableModel;

import model.score.TopTen;

public class TopTenTableModel extends AbstractTableModel {
	
	private TopTen topTenData = new TopTen();
	private Object [][] data = topTenData.getTopTenData();
	private String [] columnNames = {"USERNAME","SCORE"};
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
	public String getColumnName(int col) {
         return columnNames[col];
     }

	@Override
	public int getRowCount() {
		return data.length;
	}

	@Override
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}
	
	

}
