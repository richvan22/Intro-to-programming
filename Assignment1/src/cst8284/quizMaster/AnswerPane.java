package cst8284.quizMaster;



import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class AnswerPane {
	private VBox answerBox;
	private ToggleGroup group;
	private RadioButton[] rbAr;
	private String[] answers;

	public AnswerPane(String[] answers) {
		answerBox = new VBox();
		this.answers = answers;
		rbAr = new RadioButton[answers.length];
		group = new ToggleGroup();
		
		for (int i = 0; i < answers.length; i++) {
			rbAr[i] = new RadioButton(answers[i]);
			rbAr[i].setToggleGroup(group);
			answerBox.getChildren().add(rbAr[i]);
		}
		
	    
	}
	
	public VBox getAnswerPane() {
		
			return answerBox;
	}
	
	public int getButtonSelected() {
		for (int i = 0; i < rbAr.length; i++) {
			if(rbAr[i].isSelected()){
				return i+1;//
			}
		}
		return -1;
	}
}
