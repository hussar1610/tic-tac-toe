package makselan.konrad;

import java.awt.event.ActionEvent;
import java.util.concurrent.TimeUnit;

class AppController {
	
	private AppView appFrame;
	private AppModel model;
	
	public AppController(AppView appFrame, AppModel model) {
		this.appFrame = appFrame;
		this.model = model;
		addActionListenersToGameFields();
	}
	
	private void addActionListenersToGameFields() {
		for(int i = 0; i < AppModel.NUMBER_OF_GAME_FIELDS; i++) {
			addActionListener(i);
		}
	}
	
	private void addActionListener(int indexOfField) {
		appFrame.getGameField(indexOfField).addActionListener((ActionEvent e) -> {
			if(model.getFieldsStates()[indexOfField] != FieldState.NOTHING) {
				appFrame.showChosenFieldMessage();
			}
			else {
				switch(model.getLastPlayer()) {
					case CROSS: 
						updateGameAfterPlayerMove(indexOfField, FieldState.CIRCLE);
						break;

					case CIRCLE:
						updateGameAfterPlayerMove(indexOfField, FieldState.CROSS);
						break;

					default:
						break;
				}
			}
		});
	}

	private void updateGameAfterPlayerMove(int indexOfChosenField, FieldState playerWhoMoved){
		appFrame.drawChosenField(indexOfChosenField, playerWhoMoved);
		model.updateFieldsStateAfterPlayerMove(indexOfChosenField);
		if(model.isThereWinner()) {
			appFrame.drawWonFields(playerWhoMoved, model.getWonFields());
			appFrame.showWonMessage(playerWhoMoved.toString());
			sleep(1);
			resetGame();
		} else {
			if(model.getNumberOfChosenFields() == 9) {
				appFrame.showDrawMessage();
				sleep(1);
				resetGame();
			}
		}
	}

	private void resetGame(){
		model.resetFields();
		appFrame.resetFields();
	}

	private void sleep(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

