package makselan.konrad;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppModelTest {

    private static AppModel appModel;

    @BeforeEach
    public void setUp(){
        appModel = new AppModel();
    }

    @Test
    public void givenNewAppModelWhenUpdateFieldsAfterPlayerMoveThenNumberOfChosenFieldsIncreases(){
        //given
        //when
        appModel.updateFieldsStateAfterPlayerMove(5);
        //then
        assertEquals(1, appModel.getNumberOfChosenFields());
    }

    @Test
    public void givenNewAppModelWhenCrossPlayerChoseFreeFieldThenProperStateOfChosenField(){
        //given
        //when
        appModel.updateFieldsStateAfterPlayerMove(7);
        //then
        assertEquals(FieldState.CROSS, appModel.getFieldsStates()[7]);
    }

    @Test
    public void givenCrossPlayerChoseFieldWhenCirclePlayerChooseFreeFieldThenProperStateOfChosenField(){
        //given
        appModel.updateFieldsStateAfterPlayerMove(2);
        //when
        appModel.updateFieldsStateAfterPlayerMove(3);
        //then
        assertEquals(FieldState.CIRCLE, appModel.getFieldsStates()[3]);
    }

    @Test
    public void givenPlayerChoseFieldWhenResetFieldsStateThenProperResetNumberOfChosenFields(){
        //given
        appModel.updateFieldsStateAfterPlayerMove(2);
        //when
        appModel.resetFields();
        //then
        assertEquals(0, appModel.getNumberOfChosenFields());
    }

    @Test
    public void givenNewAppModelWhenCrossPlayerChoseWinningSequenceOfFieldsThenProperIsAWinnerChecking(){
        //given
        //when
        appModel.updateFieldsStateAfterPlayerMove(0);
        appModel.updateFieldsStateAfterPlayerMove(2);
        appModel.updateFieldsStateAfterPlayerMove(4);
        appModel.updateFieldsStateAfterPlayerMove(1);
        appModel.updateFieldsStateAfterPlayerMove(8);
        //then
        assertEquals(true, appModel.isThereWinner());
    }

    @Test
    public void givenNewAppModelWhenCirclePlayerChoseWinningSequenceOfFieldsThenProperIsAWinnerChecking(){
        //given
        //when
        appModel.updateFieldsStateAfterPlayerMove(0);
        appModel.updateFieldsStateAfterPlayerMove(3);
        appModel.updateFieldsStateAfterPlayerMove(8);
        appModel.updateFieldsStateAfterPlayerMove(4);
        appModel.updateFieldsStateAfterPlayerMove(1);
        appModel.updateFieldsStateAfterPlayerMove(5);
        //then
        assertEquals(true, appModel.isThereWinner());
    }

    @Test
    public void givenNewAppModelWhenCrossPlayerChoseWinningSequenceOfFieldsThenProperSetOfWonFields(){
        //given
        //when
        appModel.updateFieldsStateAfterPlayerMove(0);
        appModel.updateFieldsStateAfterPlayerMove(5);
        appModel.updateFieldsStateAfterPlayerMove(1);
        appModel.updateFieldsStateAfterPlayerMove(8);
        appModel.updateFieldsStateAfterPlayerMove(2);
        appModel.isThereWinner();
        //then
        int[] winningSequenceOfFields = {0, 1, 2};
        assertArrayEquals(winningSequenceOfFields, appModel.getWonFields());
    }

}