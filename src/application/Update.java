package application;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class Update extends Application
{
	Text name,rno,floor,food;
	RadioButton yes,no;
	TextField fname,frno,ffloor,ffood;
	GridPane grid;
	Button update,delete;
	
	public void initial()
	{
		//text-------------------------------------
		name=new Text("Name");
		rno=new Text("Room No.");
		floor=new Text("Floor");
		food=new Text("Food");
		//radio-button-------------------------------
		yes=new RadioButton("Yes");
		no=new RadioButton("No");
		//text-field---------------------------------
		fname=new TextField();
		frno=new TextField();
		ffloor=new TextField();
		ffood=new TextField();
		//button--------------------------------------
	}
	
	public void start(Stage stage) throws Exception
	{
	    initial();
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}
}