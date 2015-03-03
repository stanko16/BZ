package structureDefinition;


public class Cell {

	//Some parameters regarding all the cells
	private static double k1 = 1; //Infected coefficient - Influence of infected cells (gray ones)
	private static double k2 = 1; //Ill coefficient - Influence of ill cells (black ones)
	private static double g = 7; //Speed coefficient How fast an infected becomes ill
	public static int n = 255; //the state value of an ill cell; I chose 255 because rgb
	private static int leftNeighbours=1, rightNeighbours=1, upNeighbours=1, downNeighbours=1;//neighbors analized
	
	//Parameters regarding this cell
	private  int state; //0 = healthy, 0 < state < n = infected, n = ill
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
					Cell x = Matrix.getCell(this.getColumn()+i, this.getRow()+j); //Get the neighbor cell
					cellStateSum+=x.getState();
					if (x.getState()>0 && x.getState()<n){//If the cell is infected
						numOfInf++; //increase counter of infected
					} 
					else if(x.getState()==n){// if it is an ill cell
							numOfIll++; //increase counter of ill
					}
				}
		  	}  
		}
		
		int calcState = (int)(numOfInf/k1 + numOfIll/k2);//divide the counters by the coefficients
		int curState = this.getState();
		if (curState==0){this.setNextState(calcState);}//If the current state is healthy, infect it's next state
		else if (curState==n){this.setNextState(0);} //If it is ill, make next state healthy
		else if (curState < n && curState > 0) { //if it is infected
			int state = (cellStateSum/(numOfInf + numOfIll + 1))+ (int) g; //do some more calcs with the coefficient g
			if (state>n){state-=n;} //if it goes out of the limit, we subtract n to that number and get an acceptable one
			this.setNextState(state);//We change next state
			}
	}
	

	public void setState(int state) {
		this.state = state;
	}
	

	public int getState(){
		return this.state;
	}
	

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}
	
	public int getNextState(){
		return this.nextState;
	}
	
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

	/**
	 * Setting the new speed coefficient. If it is not between 0 and n, the rest of the division per n is set as value
	 * @param g the new speed coefficient
	 */
	public static void setG(double g) {
		if(g<0) g=(g*-1)%n;
		if(g>n) g=g%n;
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
