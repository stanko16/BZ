package structureDefinition;


public abstract class Matrix {
	 private static Cell matrix[][];
	 private static int nRows;
	 private static int nColumns;
	 
	 
	public static void GenerateVoidMatrix(int nColumns, int nRows) {
		Matrix.initializeMatrix(nColumns, nRows);
		for (int j = 0; j < nColumns; j++) {
			for (int i = 0; i < nRows; i++) {
				Cell c = new Cell(0,j,i);
				matrix[j][i]=c;
			}
		}
	}
	
	public static void GenerateRandomMatrix(int nColumns, int nRows){
		Matrix.initializeMatrix(nColumns, nRows);
		for (int j = 0; j < nColumns; j++) {
			for (int i = 0; i < nRows; i++) {
				int b;
				if((Math.random()*100)<33){
					b = 0;
				}else if(Math.random()*100>=33 && Math.random()*100<66){
					b = (int) (Math.random()*Cell.n-1);
				} else{
					b=Cell.n;
				}
				Cell c = new Cell(b,j,i);
				matrix[j][i]=c;
			}
		}
	}
	
	/**
	 * 
	 */
	public static void initializeMatrix(int nColumns, int nRows){
		Matrix.setnColumns(nColumns);
		Matrix.setnRows(nRows);
		matrix = new Cell[nColumns][nRows];
	}
	
	/**
	 * Checks the status of each cell with respect to its neighbor cells
	 */
	public static void checkMatrix(){
		for (int i = 0; i < Matrix.getnColumns(); i++) {
			for (int j = 0; j < Matrix.getnRows(); j++) {
				matrix[i][j].check();
			}
		}
	}
	
	/**
	 * Updates the status of all cells
	 */
	public static void updateMatrix(){
		for (int i = 0; i < Matrix.getnColumns(); i++) {
			for (int j = 0; j < Matrix.getnRows(); j++) {
				matrix[i][j].update();
			}
		}
	}
		
	/**
	 * @return the nRows
	 */
	public static int getnRows() {
		return nRows;
	}
	/**
	 * @param nRows the nRows to set
	 */
	public static void setnRows(int nRows) {
		Matrix.nRows = nRows;
	}
	/**
	 * @return the nColumns
	 */
	public static  int getnColumns() {
		return nColumns;
	}
	/**
	 * @param nColumns the nColumns to set
	 */
	public static void setnColumns(int nColumns) {
		Matrix.nColumns = nColumns;
	}
	/**
	 * @return the matrix
	 */
	public static Cell[][] getMatrix() {
		return matrix;
	}
	 
	public static Cell getCell(int column, int row){
		if(column<0){
			column=nColumns-1;
		}
		if (column>nColumns-1){
			column=0;
		}
		if (row<0){
			row=nRows-1;
		}
		if (row>nRows-1){
			row=0;
		}
		return Matrix.getMatrix()[column][row];
	}
	
	public static void setCell(int column, int row){
		int b = Matrix.getMatrix()[column][row].getState();
		if(b>-1&&b<9){
			Matrix.getMatrix()[column][row].setState(b+1);
		} else{
			Matrix.getMatrix()[column][row].setState(0);
		}
		
		
	}
}
