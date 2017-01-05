package cst8284.quizMaster;

import javafx.application.Application;
import javafx.geometry.Pos;

import java.io.File;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class QuizMain extends Application {

	QA[] QAList;
	public final int MAX_QA_SIZE = 5;
	public static int counter = 0;

	@Override
	public void start(Stage primaryStage){	

		primaryStage.setTitle("Quiz Master");
		Scene scene =  new Scene(getSplashPane("Welcome to QuizMaster"), 1024, 512);

		primaryStage.setScene(scene);
		primaryStage.show();	

		File f = FileUtils.getFileHandle(primaryStage);
		String str = FileUtils.getFileName();

		
			QAList = FileUtils.getQAArray(FileUtils.getFileName(f), MAX_QA_SIZE);

			/*	for (QA currentQA: QAList){
				scene = new Scene(getCurrentQAPane(currentQA));
				primaryStage.setScene(scene);
				FileUtils.waitAndShow(FileUtils.ON);  
			} */

			displayQA(counter, QAList, primaryStage);

		if (counter == MAX_QA_SIZE) {
			scene = new Scene(resultsPane(QAList), 640, 480);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
	}
	private void displayQA(int i, QA[] QAList, Stage stage) {
		StackPane main = new StackPane();
		Button btnNext = new Button("Next Question");
		btnNext.setOnAction(event -> {
			counter++;
			while (counter <= MAX_QA_SIZE)
			{
				displayQA(counter, QAList, stage);
			}
		});

		
		main.getChildren().addAll(btnNext,getCurrentQAPane(QAList[i]));
		Scene scene = new Scene(main, 1024, 512);
		stage.setScene(scene);



	}
	private Pane getSplashPane(String str){
		Text text = new Text(str);
		text.setStyle("-fx-font: 40px Tahoma; -fx-stroke: black; -fx-stroke-width: 1;");
		BorderPane splashPane = new BorderPane();
		splashPane.setCenter(text);
		return splashPane;	
	}
	public VBox getCurrentQAPane(QA currentQA){
		VBox currentvbox = new VBox();
		AnswerPane ap = new AnswerPane(currentQA.getAnswers());

		Text qaQuestion = new Text(currentQA.getQuestion());

		Button btnOK = new Button("Check Answer");
		Text userExplanation = new Text("");

		btnOK.setOnAction(event ->{
			if(ap.getButtonSelected() == currentQA.getCorrectAnswerNumber()){
				currentQA.setResult(true);
				userExplanation.setText(currentQA.getCorrectAnswerNumber()+". "+currentQA.getExplanation());

			}else{
				currentQA.setResult(false);
				userExplanation.setText(currentQA.getCorrectAnswerNumber()+". "+currentQA.getExplanation());
			}

		});

		currentvbox.getChildren().add(qaQuestion);
		currentvbox.getChildren().add(ap.getAnswerPane());
		currentvbox.getChildren().add(btnOK);
		currentvbox.getChildren().add(userExplanation);



		return currentvbox;
	}
	public VBox resultsPane(QA[] list){
		String finalResults = "";
		int totalscore = 0;
		for (int i = 0; i < list.length; i++) {
			finalResults += ""+(i+1)+" ";
			if(list[i].isCorrect()){
				finalResults += " correct \n";
				totalscore++;
			}else{
				finalResults += " wrong \n";
			}
			if((i+1) == list.length){
				finalResults+= " Your score is "+totalscore+"/"+list.length;
			}
		}
		System.out.println(finalResults);
		Text newText = new Text(finalResults);
		VBox box = new VBox();
		box.getChildren().add(newText);
		return box;
	}
	public static void main(String[] args){
		Application.launch(args);
	}
}