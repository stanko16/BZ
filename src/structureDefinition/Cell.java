package structureDefinition;

public class Cell {

	//Some parameters regarding all the cells
	private static double k1 = 1;
	private static double k2 = 1;
	private static double g = 7;
	public static int n = 255;
	private static int leftNeighbours=1, rightNeighbours=1, upNeighbours=1, downNeighbours=1;
	
	//Parameters regarding this cell
	private  int state;
	private int column;
	private int row;
	private int nextState;
	
	

	/**
	 * Creates a Cell object setting the basic parameters.
	 * 
	 * @param state The initial state of the cell
	 * @param column The column it belongs to in the matrix
	 * @param row The row it belongs to in the matrix
	 */
	public Cell(int state, int column, int row) {
		this.state = state;
		this.column = column;
		this.row = row;
	}
	
	/**
	 * Updates the status of this cell, putting the next status value into
	 * the current status value
	 */
	public void update(){
		this.setState(this.getNextState());
	}
	
	
	/**
	 * Checks the status of the neighbor cells and sets the future status
	 * of this cell
	 */
	public void check(){
		int numOfInf = 0; //counter of infected neighbors
		int numOfIll = 0; //counter of ill neighbors
		int cellStateSum = this.getState();
		for (int i = -1*leftNeighbours; i < rightNeighbours+1; i++) { //For each neighbor (left to right)
			for (int j = -1*upNeighbours; j < downNeighbours+1; j++) {//For each neighbor (top to bottom)
				if(!(i==0 && j==0)){
					try{
						Cell x = Matrix.getCell(this.getColumn()+i, this.getRow()+j);
						cellStateSum+=x.getState();
						if (x.getState()>0&&x.getState()<n){
							numOfInf++;
							} else if(x.getState()==n){
								numOfIll++;
							}
					}catch(Exception e){
					}
				}
		  	}  
		}
		
		int calcState = (int)(numOfInf/k1 + numOfIll/k2);
		int curState = this.getState();
		if (curState==0){this.setNextState(calcState);}
		else if (curState==n){this.setNextState(0);}
		else if (curState < n && curState > 0) {
			int state = (cellStateSum/(numOfInf + numOfIll + 1))+ (int) g;
			if (state>n){state-=n;}
			this.setNextState(state);
			}
	}
	
	/**
	 * @param state the state to set
	 */
	public void setState(int state) {
		this.state = state;
	}
	
	/**
	 * @return the state
	 */
	public int getState(){
		return this.state;
	}
	
	/**
	 * @return the column
	 */
	public int getColumn() {
		return column;
	}
	/**
	 * @param column the column to set
	 */
	public void setColumn(int column) {
		this.column = column;
	}
	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}
	/**
	 * @param row the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}
	/**
	 * @return the nextState
	 */
	public int getNextState(){
		return this.nextState;
	}
	
	/**
	 * @param nextState the nextState to set
	 */
	public void setNextState(int nextState) {
		this.nextState = nextState;
	}

	public static double getK1() {
		return k1;
	}

	public static void setK1(double d) {
		Cell.k1 = d;
	}

	public static double getK2() {
		return k2;
	}

	public static void setK2(double k2) {
		Cell.k2 = k2;
	}

	public static double getG() {
		return g;
	}

	public static void setG(double g) {
		while(g<0){
			g+=255;
			}
		while(g>255){
			g-=255;
			}
		Cell.g = g;
	}

	
	public static int getLeftNeighbours() {
		return leftNeighbours;
	}

	public static void setLeftNeighbours(int left) {
		leftNeighbours = left;
	}

	public static int getRightNeighbours() {
		return rightNeighbours;
	}

	public static void setRightNeighbours(int right) {
		rightNeighbours = right;
	}

	public static int getUpNeighbours() {
		return upNeighbours;
	}

	public static void setUpNeighbours(int up) {
		upNeighbours = up;
	}

	public static int getDownNeighbours() {
		return downNeighbours;
	}

	public static void setDownNeighbours(int down) {
		downNeighbours = down;
	}	
	
}
