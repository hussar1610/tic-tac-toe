package makselan.konrad;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

class AppView extends JFrame{
	
	private JPanel fieldsPanel;
	private ArrayList<JButton> gameFields;
	
	public AppView() {
		super("Tic Tac Toe");
		
		fieldsPanel = new JPanel();
		GridLayout gridLayout = new GridLayout(3,3);
		gridLayout.setHgap(20);
		gridLayout.setVgap(20);
		fieldsPanel.setLayout(gridLayout);

		gameFields = new ArrayList<>(AppModel.NUMBER_OF_GAME_FIELDS);
		for(int i = 0; i < AppModel.NUMBER_OF_GAME_FIELDS; i++) {
			JButton gameField = createGameField();
			gameFields.add(gameField);
			fieldsPanel.add(gameField);
		}
		
		this.add(fieldsPanel);
		this.pack();
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		JOptionPane.showMessageDialog(this, "Let's start! Cross player begins!");
	}

	public JButton getGameField(int index){
		return this.gameFields.get(index);
	}
	
	public void resetFields() {
		for(JButton button : gameFields) {
			button.setIcon(new ImageIcon(getClass().getResource("square.png")));
		}
	}

	public void drawChosenField(int indexOfChosenField, FieldState typeOfChosenField){
		if(typeOfChosenField == FieldState.CIRCLE){
			setCircle(indexOfChosenField);
		} else {
			setCross(indexOfChosenField);
		}
	}

	public void drawWonFields(FieldState typeOfWinner, int[] indexesOfFieldsToDraw){
		if(typeOfWinner == FieldState.CIRCLE){
			setRedCircle(indexesOfFieldsToDraw[0]);
			setRedCircle(indexesOfFieldsToDraw[1]);
			setRedCircle(indexesOfFieldsToDraw[2]);
		} else {
			setRedCross(indexesOfFieldsToDraw[0]);
			setRedCross(indexesOfFieldsToDraw[1]);
			setRedCross(indexesOfFieldsToDraw[2]);
		}
	}

	public void showChosenFieldMessage(){
		JOptionPane.showMessageDialog(this, "You cannot choose field that is already in used!");
	}

	public void showDrawMessage(){
		JOptionPane.showMessageDialog(this, "Draw! Let's play rematch!");
	}

	public void showWonMessage(String winner){
		JOptionPane.showMessageDialog(this, winner +" player won!");
	}

	private JButton createGameField() {
		JButton gameField = new JButton();
		gameField.setPreferredSize(new Dimension(130, 130));
		gameField.setIcon(new ImageIcon(getClass().getResource("square.png")));
		return gameField;
	}

	private void setCross(int indexOfChosenField) {
		gameFields.get(indexOfChosenField).setIcon(new ImageIcon(getClass().getResource("cross.png")));
	}

	private void setCircle(int indexOfChosenField) {
		gameFields.get(indexOfChosenField).setIcon(new ImageIcon(getClass().getResource("circle.png")));
	}

	private void setRedCross(int indexOfChosenField) {
		gameFields.get(indexOfChosenField).setIcon(new ImageIcon(getClass().getResource("redCross.png")));
	}

	private void setRedCircle(int indexOfChoosenField) {
		gameFields.get(indexOfChoosenField).setIcon(new ImageIcon(getClass().getResource("redCircle.png")));
	}
}
