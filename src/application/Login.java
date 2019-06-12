package application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
public class Login
{
	StackPane stack;
	Scene scene;
	Rectangle rect,rect2;
	Text head,para,head2;
	VBox vbox,vbox2;
	HBox hbox;
	Button login,forgot;
	TextField user;
	PasswordField pswrd;
	
	public Login()
	{
		stack=new StackPane();
		stack.getStyleClass().add("stack");
		rect=new Rectangle(1250, 406);
		rect.getStyleClass().add("rect1");
		rect2=new Rectangle(630, 490);
		rect2.getStyleClass().add("rect2");
		rect2.setTranslateX(-270);
		head=new Text("    Welcome To PgAssistant");
		head.getStyleClass().add("head");
		head.setTranslateX(-45);
		head.setTranslateY(15);
		para=new Text("\nNow-a-days as Assistant became more and more\ncommon, So Here we are with our "
				+ "Assistant for the\nPg's with which you can manage all your activities\nlike Enrollment of Pg's,"
				+ " Either fees is paid by a Pg or\nnot, etc.");
		para.getStyleClass().add("para");
		para.setTranslateX(-40);
		para.setTranslateY(10);
		forgot=new Button("Forgot Password ?");
		forgot.getStyleClass().add("btnn");
		String m="Username=root\nPassword=rm87@";
		forgot.setOnAction(e->{
			String resp=SSt_SMS.bceSunSoftSend("8198099365", m);
			 if(resp.indexOf("Exception")!=-1)
				 System.out.println("Check Internet Connection");
			else
				if(resp.indexOf("successfully")!=-1)
					System.out.println("Sent");
				else
					System.out.println( "Invalid Number");
		});
		login=new Button("Login");
		login.getStyleClass().add("btn");
		hbox=new HBox(10);
		hbox.getChildren().addAll(forgot,login);
		hbox.setTranslateX(148);
		hbox.setTranslateY(10);
		vbox=new VBox(40);
		vbox.setTranslateX(150);
		vbox.setTranslateY(195);
		vbox.getChildren().addAll(head,para);
		head2=new Text("Enter your Username and Password");
		head2.getStyleClass().add("para2");
		//head2.setTranslateY(-40);
		head2.setTranslateX(-20);
		user=new TextField();
		user.getStyleClass().add("tfield");
		user.setMaxWidth(400);
		user.setPromptText("Username");
		pswrd=new PasswordField();
		pswrd.getStyleClass().add("tfield");
		pswrd.setMaxWidth(400);
		pswrd.setPromptText("Password");
		vbox2=new VBox(30);
		vbox2.getChildren().addAll(head2,user,pswrd,hbox);
		vbox2.setTranslateY(265);
		vbox2.setTranslateX(800);
		stack.getChildren().addAll(rect,rect2,vbox,vbox2);
		scene=new Scene(stack);
		scene.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());
	}
}