package cst8284.quizMaster;

import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.swing.JOptionPane;

public class FileUtils {

	private static String QuizFileAbsolutePathAndName="";
	public final static boolean ON = true, OFF = false;

	public static File getFileHandle(Stage primaryStage){
		File quizFile;
		boolean quit = false;

		FileChooser fc = new FileChooser();
		do{fc.setTitle("Open Quiz File");
		fc.getExtensionFilters().addAll(
				new ExtensionFilter("Quiz Files", "*.quiz"),
				new ExtensionFilter("All Files", "*.*"));
		quizFile = fc.showOpenDialog(primaryStage);
		try{if(quizFile == null){
			int dialogResult = JOptionPane.showConfirmDialog (null, "No file entered. Do you wish exit(ok) or continue(cancel)","Warning",JOptionPane.OK_CANCEL_OPTION);
			if(dialogResult == JOptionPane.OK_OPTION){
				quit = true;
				Platform.exit();
				System.exit(0);
			}else{
				quit = false;
			}
		}
		}catch(Exception e){

		}
		}while(quizFile ==null && !quit);
		setFileName(quizFile);

		return(quizFile);
	}

	public static QA[] getQAArray(String absPath, int numObjects){
		QA[] arrayOfObjects = new QA[numObjects];
		try {
			FileInputStream fin=new FileInputStream(absPath);
			ObjectInputStream objectIn = new ObjectInputStream(fin);
			int numberRead = 0;
			while(numberRead < numObjects){
				arrayOfObjects[numberRead] = (QA) objectIn.readObject();
				numberRead++;	
			}
			objectIn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		// TODO: Open the file as a FileInputStream and load the first numObjects into a
		// new Array of type QA. Then return an array of QA objects to the caller

		return arrayOfObjects;
	}

	private static void setFileName(File f) {
		QuizFileAbsolutePathAndName = (FileExists(f))? f.getAbsolutePath():"";
	}

	public static String getFileName(){
		return QuizFileAbsolutePathAndName;
	}

	public static String getFileName(File f){
		return f.getAbsolutePath();
	}

	private static Boolean FileExists(File f){
		return (f!=null && f.exists() && f.isFile() && f.canRead());
	}

	public static void waitAndShow(boolean b){
		while(b){};  // loop until b is OFF
	}

}
