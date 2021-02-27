package makselan.konrad;

class AppModel {
	
	public static final int NUMBER_OF_GAME_FIELDS = 9;
	
	private FieldState lastPlayer;
	private FieldState[] fieldsStates;
	private int numberOfChosenFields = 0;
	private int[] wonFields;

	public AppModel() {
		lastPlayer = FieldState.CIRCLE;
		fieldsStates = new FieldState[NUMBER_OF_GAME_FIELDS];
		wonFields = new int[3];
		resetFields();
	}

	public int[] getWonFields() {
		return wonFields;
	}
	
	public  FieldState getLastPlayer() {
		return lastPlayer;
	}
	
	public  void updateFieldsStateAfterPlayerMove(int indexOfChosenField) {
		numberOfChosenFields++;
		if(lastPlayer == FieldState.CIRCLE) {
			lastPlayer = FieldState.CROSS;
			fieldsStates[indexOfChosenField] = FieldState.CROSS;
		} else {
			lastPlayer = FieldState.CIRCLE;
			fieldsStates[indexOfChosenField] = FieldState.CIRCLE;
		}
	}

	public void resetFields() {
		for(int i = 0; i < NUMBER_OF_GAME_FIELDS; i++) {
			fieldsStates[i] = FieldState.NOTHING;
			lastPlayer = FieldState.CIRCLE;
		}
		numberOfChosenFields = 0;
	}
	
	public FieldState[] getFieldsStates() {
		return this.fieldsStates;
	}
	
	public int getNumberOfChosenFields() {
		return this.numberOfChosenFields;
	}

	public boolean isThereWinner() {
		return(searchRowsForAWinner() || searchColumnsForAWinner() || searchDiagonalsForAWinner());
	}

	private boolean searchRowsForAWinner(){
		for(int i = 0; i < 7; i+= 3){
			if(fieldsStates[i] != FieldState.NOTHING && fieldsStates[i] == fieldsStates[i+1] && fieldsStates[i+1] == fieldsStates[i+2]){
				setWonFields(i, i+1, i+2);
				return true;
			}
		}
		return false;
	}

	private boolean searchColumnsForAWinner(){
		for(int i = 0; i < 3; i++){
			if(fieldsStates[i] != FieldState.NOTHING && fieldsStates[i] == fieldsStates[i+3] && fieldsStates[i+3] == fieldsStates[i+6]){
				setWonFields(i, i+3, i+6);
				return true;
			}
		}
		return false;
	}

	private boolean searchDiagonalsForAWinner(){
		if(fieldsStates[4] != FieldState.NOTHING) {
			if(fieldsStates[0] == fieldsStates[4] && fieldsStates[4] == fieldsStates[8]){
				setWonFields(0, 4, 8);
				return true;
			} else if(fieldsStates[2] == fieldsStates[4] && fieldsStates[4] == fieldsStates[6]){
				setWonFields(2, 4, 6);
				return true;
			}
		}
		return false;
	}

	private void setWonFields(int firstWonFieldIndex, int secondWonFieldIndex, int thirdWonFieldIndex){
		wonFields[0] = firstWonFieldIndex;
		wonFields[1] = secondWonFieldIndex;
		wonFields[2] = thirdWonFieldIndex;
	}

}
