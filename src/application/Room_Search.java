package application;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
public class Room_Search extends Application
{
	Text title,type,flr;
	TextField tflr;
	StackPane stack;
	RadioButton ac,nac;
	Rectangle2D size;
	Rectangle rect;
	ToggleGroup group;
	Button search;
	GridPane grid;
	HBox hbox;
	ImageView img;
	
	public void initial()
	{
		//pane-------------------------------------------
		stack=new StackPane();
		stack.getStyleClass().add("stack");
		grid=new GridPane();
		grid.setVgap(35);
		grid.setHgap(5);
		//grid.setGridLinesVisible(true);
		grid.getStyleClass().add("grid");
		//image------------------------------------------
		try
		{
			img=new ImageView(new Image(new FileInputStream("bin/application/bookmark.png")));
		}
		catch(FileNotFoundException exp)
		{
			exp.printStackTrace();
		}
		//text--------------------------------------------
		title=new Text("Enter Your Filters");
		title.getStyleClass().add("text2");
		type=new Text("Type");
		type.getStyleClass().add("text1");
		flr=new Text("Floor");
		flr.getStyleClass().add("text1");
		//textField------------------------------------------
		tflr=new TextField();
		tflr.getStyleClass().add("field");
		//sizeScreen-----------------------------------------
		size=Screen.getPrimary().getVisualBounds();
		//radioButton----------------------------------------
		ac=new RadioButton("AC");
		nac=new RadioButton("Non-AC");
		//toggleGroup----------------------------------------
		group=new ToggleGroup();
		ac.setToggleGroup(group);
		nac.setToggleGroup(group);
		//button--------------------------------------------
		search=new Button("SEARCH");
		//hbox----------------------------------------------
		hbox=new HBox();
		hbox.getChildren().addAll(ac,nac);
		hbox.setSpacing(30);
		//rectangle-------------------------------------------
		rect=new Rectangle(850, 450);
		rect.getStyleClass().add("rect");
		stack.getChildren().add(rect);
	}
	
	public void start(Stage stage) throws Exception
	{
		initial();
		Rectangle rect2=new Rectangle(550, 440);
		rect2.getStyleClass().add("rect");
		stack.getChildren().add(rect2);
		rect2.setTranslateX(143);
		rect2.setTranslateY(0);
		GridPane.setConstraints(title, 0, 0, 2, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(50,5,25,55));
		GridPane.setConstraints(type, 0, 1, 1, 1, HPos.RIGHT, VPos.CENTER, null, null, new Insets(5,5,25,10));
		GridPane.setConstraints(hbox, 1, 1, 1, 1, HPos.CENTER, VPos.BASELINE, null, null, new Insets(5,25,25,15));
		GridPane.setConstraints(flr, 0, 2, 1, 1, HPos.RIGHT, VPos.CENTER, null, null, new Insets(5,5,25,10));
		GridPane.setConstraints(tflr, 1, 2, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(5,25,25,15));
		GridPane.setConstraints(search, 1, 3, 1, 1, HPos.RIGHT, VPos.CENTER, null, null, new Insets(25,25,0,300));
		grid.getChildren().addAll(title,type,hbox,flr,tflr,search);
		stack.getChildren().add(grid);
		grid.setTranslateX(465);
		grid.setTranslateY(150);
		stack.getChildren().add(img);
		img.setFitWidth(80);
		img.setFitHeight(80);
		img.setTranslateX(-90);
		img.setTranslateY(-205);
		Scene scene=new Scene(stack, 1100, size.getHeight());
		scene.getStylesheets().add(getClass().getResource("Room_Search.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}
}