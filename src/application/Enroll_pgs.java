package application;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
public class Enroll_pgs
{
	Stage window;
	GridPane grid1,grid2,grid3;
	Rectangle rect1,rect2,rect3,rect4,rect5,rect6;
	Rectangle2D size;
	StackPane stack1,stack2,stack3,stack;
	Text pname,mno,adr,city,inst,title1,title2,title3,parname,parmob,parem,rtpe,floor,food,rno,seat;
	TextField tpname,tmno,tcity,tparname,tparmob,tparem,tfloor,tseat;
	ComboBox<String> room;
	TextArea tadr,tinst;
	HBox hbox1,hbox2,user,butn;
	int i=1;
	String str;
	File file1,file2;
	Alert alert;
	RadioButton ac,nac,yes,no;
	ToggleGroup type,rf;
	Button next1,next2,search,finish,bk,cls;
	Image ppic;
	Circle pic;
	ImageView img1,img2,img3;
	ImagePattern pgpic;
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	public void form1()
	{
		//imageView---------------------------------------
		try
		{
			file1=new File("bin/application/user.png");
			ppic=new Image(new FileInputStream(file1));
			pgpic=new ImagePattern(ppic);
			str=file1.getPath();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		//stack------------------------------------------
		stack1=new StackPane();
		//stack1.getStyleClass().add("stack");
		//text-------------------------------------------
		title1=new Text("PG's Enrollment");
		title1.getStyleClass().add("head");
		pname=new Text("PG's Name*");
		pname.getStyleClass().add("para");
		mno=new Text("Mobile No.*");
		mno.getStyleClass().add("para");
		adr=new Text("Address");
		adr.getStyleClass().add("para");
		city=new Text("City*");
		city.getStyleClass().add("para");
		inst=new Text("Institute/Office Name");
		inst.getStyleClass().add("para");
		//textField----------------------------------------
		tpname=new TextField();
		tpname.setFont(Font.font(15));
		tpname.getStyleClass().add("tfield");
		tpname.setPrefHeight(30);
		tmno=new TextField();
		tmno.setFont(Font.font(15));
		tmno.getStyleClass().add("tfield");
		tmno.setPrefHeight(30);
		tmno.focusedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				String mobile="^[789]\\d{9}$";
				if(tmno.getText().matches(mobile))
					tmno.setStyle("-fx-border-color: black");
				else
					tmno.setStyle("-fx-border-color: red");
			}
		});
		tcity=new TextField();
		tcity.setFont(Font.font(15));
		tcity.getStyleClass().add("tfield");
		tcity.setPrefHeight(30);
		//textArea----------------------------------------
		tadr=new TextArea();
		tadr.setFont(Font.font(14));
		tadr.setPrefHeight(50);
		tadr.getStyleClass().add("tfield");
		tinst=new TextArea();
		tinst.setFont(Font.font(14));
		tinst.setPrefHeight(50);
		tinst.getStyleClass().add("tfield");
		//button-----------------------------------------
		next1=new Button("Next");
		next1.getStyleClass().add("btnnext");
		pic=new Circle(75);
		pic.setFill(pgpic);
		pic.setStroke(Color.BLACK);
		//rectangle------------------------------------------
		rect4=new Rectangle(size.getWidth()-100, 520);
		rect4.getStyleClass().add("rect");
		stack1.getChildren().add(rect4);
		rect1=new Rectangle(942, 505);
		rect1.setTranslateX(-154);
		stack1.getChildren().add(rect1);
		rect1.getStyleClass().add("rect");
		//setform1------------------------------------------
		GridPane.setConstraints(title1, 0, 0, 3, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(5,10,0,70));
		GridPane.setConstraints(pic, 0, 1, 1, 3, HPos.RIGHT, VPos.CENTER, null, null, new Insets(70,0,20,25));
		GridPane.setConstraints(pname, 1, 1, 1, 1, HPos.RIGHT, VPos.CENTER, null, null, new Insets(50,10,20,10));
		GridPane.setConstraints(tpname, 2, 1, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(50,14,20,10));
		GridPane.setConstraints(mno, 1, 2, 1, 1, HPos.RIGHT, VPos.CENTER, null, null, new Insets(20,10,20,10));
		GridPane.setConstraints(tmno, 2, 2, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(20,14,20,10));
		GridPane.setConstraints(adr, 1, 3, 1, 1, HPos.RIGHT, VPos.TOP, null, null, new Insets(20,10,20,10));
		GridPane.setConstraints(tadr, 2, 3, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(20,14,20,10));
		GridPane.setConstraints(city, 1, 4, 1, 1, HPos.RIGHT, VPos.CENTER, null, null, new Insets(20,10,20,10));
		GridPane.setConstraints(tcity, 2, 4, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(20,14,20,10));
		GridPane.setConstraints(next1, 2, 6, 1, 1, HPos.RIGHT, VPos.CENTER, null, null, new Insets(20,14,20,10));
		GridPane.setConstraints(tinst, 2, 5, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(20,14,20,10));
		GridPane.setConstraints(inst, 0, 5, 2, 1, HPos.RIGHT, VPos.TOP, null, null, new Insets(20,10,20,10));
		//grid---------------------------------------------
		grid1=new GridPane();
		grid1.setVgap(5);
		grid1.setHgap(5);
		pic.setFocusTraversable(false);
		//grid1.setGridLinesVisible(true);
		grid1.getChildren().addAll(title1, pic, pname, tpname, mno, tmno, adr, tadr, city, tcity, next1, inst, tinst);
		stack1.getChildren().add(grid1);
		grid1.setAlignment(Pos.CENTER);
		grid1.setTranslateY(-40);
		grid1.setTranslateX(-160);
		//image-----------------------------------------
		try
		{
			img1=new ImageView(new Image(new FileInputStream("bin/application/bookmark.png")));
			stack1.getChildren().add(img1);
			img1.setFitHeight(80);
			img1.setFitWidth(80);
			img1.setTranslateX(-590);
			img1.setTranslateY(-239);
		}
		catch(IOException exp)
		{
			exp.printStackTrace();
		}
	}
	
	public void form2()
	{
		//stack----------------------------------------------------
		stack2=new StackPane();
		//text-----------------------------------------------------
		title2=new Text("Great! Give Some Detail About Your Parent");
		title2.getStyleClass().add("head1");
		parname=new Text("Parent's Name*");
		parname.getStyleClass().add("para");
		parmob=new Text("Parent's Mobile No.*");
		parmob.getStyleClass().add("para");
		parem=new Text("Parent's email-id");
		parem.getStyleClass().add("para");
		//textField---------------------------------------------------
		tparname=new TextField();
		tparname.setFont(Font.font(16));
		tparname.getStyleClass().add("tfield");
		tparname.setPrefHeight(30);
		tparmob=new TextField();
		tparmob.setFont(Font.font(16));
		tparmob.getStyleClass().add("tfield");
		tparmob.setPrefHeight(30);
		tparmob.focusedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				String mobile="^[789]\\d{9}$";
				if(tparmob.getText().matches(mobile))
					tparmob.setStyle("-fx-border-color: black");
				else
					tparmob.setStyle("-fx-border-color: red");
			}
		});
		tparem=new TextField();
		tparem.setFont(Font.font(16));
		tparem.getStyleClass().add("tfield");
		tparem.setPrefHeight(30);
		//button-----------------------------------------
		next2=new Button("Next");
		next2.getStyleClass().add("btnnext");
		//rectangle-----------------------------------------
		rect5=new Rectangle(size.getWidth()-200, 355);
		stack2.getChildren().add(rect5);
		rect5.getStyleClass().add("rect");
		rect2=new Rectangle(700, 340);
		rect2.setTranslateX(-225);
		stack2.getChildren().add(rect2);
		rect2.getStyleClass().add("rect");
		//setform2------------------------------------------
		GridPane.setConstraints(title2, 0, 0, 2, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(5,10,70,10));
		GridPane.setConstraints(parname, 0, 1, 1, 1, HPos.RIGHT, VPos.CENTER, null, null, new Insets(20,20,20,10));
		GridPane.setConstraints(tparname, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(20,20,20,10));
		GridPane.setConstraints(parmob, 0, 2, 1, 1, HPos.RIGHT, VPos.CENTER, null, null, new Insets(20,20,20,10));
		GridPane.setConstraints(tparmob, 1, 2, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(20,20,20,10));
		GridPane.setConstraints(parem, 0, 3, 1, 1, HPos.RIGHT, VPos.TOP, null, null, new Insets(20,20,20,10));
		GridPane.setConstraints(tparem, 1, 3, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(20,20,20,10));
		GridPane.setConstraints(next2, 1, 4, 1, 1, HPos.RIGHT, VPos.CENTER, null, null, new Insets(20,20,10,10));
		//grid---------------------------------------------
		grid2=new GridPane();
		grid2.setVgap(5);
		grid2.setHgap(5);
		//grid2.setGridLinesVisible(true);
		grid2.getChildren().addAll(title2,parname,parmob,parem,tparname,tparmob,tparem,next2);
		stack2.getChildren().add(grid2);
		grid2.setAlignment(Pos.CENTER);
		grid2.setTranslateY(-60);
		grid2.setTranslateX(-185);
		//image-----------------------------------------
		try
		{
			img2=new ImageView(new Image(new FileInputStream("bin/application/bookmark.png")));
			stack2.getChildren().add(img2);
			img2.setFitHeight(80);
			img2.setFitWidth(80);
			img2.setTranslateX(-535);
			img2.setTranslateY(-155);
		}
		catch(IOException exp)
		{
			exp.printStackTrace();
		}
	}
	
	public void form3()
	{
		//stack--------------------------------------
		stack3=new StackPane();
		//text--------------------------------------
		title3=new Text("Select Your Filters");
		title3.getStyleClass().add("head");
		rtpe=new Text("Room Type");
		rtpe.getStyleClass().add("para");
		floor=new Text("Floor*");
		floor.getStyleClass().add("para");
		food=new Text("Would you like to take Food in PG?");
		food.getStyleClass().add("para");
		rno=new Text("Room No.");
		rno.getStyleClass().add("para");
		seat=new Text("Seats/Beds Available");
		seat.getStyleClass().add("para");
		//combo------------------------------------
		room=new ComboBox<String>();
		room.setPrefSize(320, 30);
		room.getStyleClass().add("tfield");
		//room.setEditable(true);
		room.setValue("Available Rooms");
		//textField-----------------------------------
		tfloor=new TextField();
		tfloor.setFont(Font.font(16));
		tfloor.getStyleClass().add("tfield");
		tfloor.setPrefSize(320, 30);
		tseat=new TextField();
		tseat.setFont(Font.font(16));
		tseat.getStyleClass().add("tfield");
		tseat.setPrefHeight(30);
		tseat.setEditable(false);
		//button--------------------------------------
		finish=new Button("Save");
		finish.getStyleClass().add("btnnext");
		search=new Button("Search");
		search.getStyleClass().add("btnnext");
		//toggleGroup----------------------------------
		type=new ToggleGroup();
		rf=new ToggleGroup();
		//radioButton-------------------------------------
		ac=new RadioButton("AC");
		nac=new RadioButton("Non-AC");
		yes=new RadioButton("Yes");
		no=new RadioButton("No");
		ac.setToggleGroup(type);
		nac.setToggleGroup(type);
		yes.setToggleGroup(rf);
		no.setToggleGroup(rf);
		//rectangle--------------------------------------
		rect6=new Rectangle(size.getWidth()-200, 435);
		rect6.getStyleClass().add("rect");
		rect3=new Rectangle(840, 420);
		rect3.getStyleClass().add("rect");
		rect3.setTranslateX(-155);
		//HBox-------------------------------------------
		hbox1=new HBox(30);
		hbox1.getChildren().addAll(ac,nac);
		hbox2=new HBox(30);
		hbox2.getChildren().addAll(yes,no);
		butn=new HBox(30);
		butn.getChildren().addAll(search, finish);
		//grid--------------------------------------
		GridPane.setConstraints(title3, 0, 0, 2, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(5,10,45,10));
		GridPane.setConstraints(rtpe, 0, 1, 1, 1, HPos.RIGHT, VPos.CENTER, null, null, new Insets(20,20,10,10));
		GridPane.setConstraints(hbox1, 1, 1, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(20,20,10,20));
		GridPane.setConstraints(floor, 0, 2, 1, 1, HPos.RIGHT, VPos.CENTER, null, null, new Insets(15,20,10,10));
		GridPane.setConstraints(tfloor, 1, 2, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(15,20,10,10));
		GridPane.setConstraints(food, 0, 3, 1, 1, HPos.RIGHT, VPos.CENTER, null, null, new Insets(15,20,10,10));
		GridPane.setConstraints(hbox2, 1, 3, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(15,20,10,20));
		GridPane.setConstraints(rno, 0, 4, 1, 1, HPos.RIGHT, VPos.CENTER, null, null, new Insets(15,20,10,10));
		GridPane.setConstraints(room, 1, 4, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(15,20,10,10));
		GridPane.setConstraints(seat, 0, 5, 1, 1, HPos.RIGHT, VPos.CENTER, null, null, new Insets(15,20,10,10));
		GridPane.setConstraints(tseat, 1, 5, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(15,20,10,10));
		GridPane.setConstraints(butn, 1, 6, 1, 1, HPos.RIGHT, VPos.CENTER, null, null, new Insets(35,20,20,150));
		grid3=new GridPane();
		grid3.setVgap(5);
		grid3.setHgap(5);
		//grid3.setGridLinesVisible(true);
		grid3.setTranslateX(-130);
		grid3.setTranslateY(-50);
		grid3.getChildren().addAll(title3, rtpe, hbox1, floor, tfloor, food, hbox2,rno, room, seat, tseat, butn);
		stack3.getChildren().addAll(rect6, rect3, grid3);
		grid3.setAlignment(Pos.CENTER);
		//image-----------------------------------------
		try
		{
			img3=new ImageView(new Image(new FileInputStream("bin/application/bookmark.png")));
			stack3.getChildren().add(img3);
			img3.setFitHeight(80);
			img3.setFitWidth(80);
			img3.setTranslateX(-535);
			img3.setTranslateY(-195);
		}
		catch(IOException exp)
		{
			exp.printStackTrace();
		}
	}
	
	public void page()
	{
		//stack---------------------------------------------
		stack=new StackPane();
		stack.getStyleClass().add("stack");
		//size----------------------------------------------
		size=Screen.getPrimary().getVisualBounds();
		//mysqlConnection--------------------------------------------
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/rahul","root","rahul4024");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void form()
	{
		//form---------------------------------------------
		form1();
		form2();
		form3();
		//pageSet-------------------------------------
		stack.getChildren().addAll(stack1,stack2,stack3);
		stack2.setVisible(false);
		stack3.setVisible(false);
	}
	
	public void showMsg(String s)
	{
		Alert alert=new Alert(AlertType.ERROR);
		alert.setHeaderText(s);
		alert.setTitle("Error");
		alert.show();
	}

	public void showMsg2(String s)
	{
		Alert alert=new Alert(AlertType.INFORMATION);
		alert.setHeaderText(s);
		alert.setTitle("Success");
		alert.show();
	}
	
	public void setNext()
	{
		if(tpname.getText().equals("")||tmno.getText().equals("")||tcity.getText().equals(""))
			showMsg("Please, Enter the Required Fields");
		else
		{
			try
			{
				pst=con.prepareStatement("select pgname from pgs");
				rs= pst.executeQuery();
				while(rs.next())
				{
					String p=rs.getString("pgname");
					if(p==tpname.getText())
					{
						showMsg("Already Registered, Please try with Another one");
						rs.close();
						tpname.clear();
						return;
					}
				}
				rs.close();
				stack1.setVisible(false);
				stack2.setVisible(true);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void doSave() throws Exception
	{
		String datestr = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
		DateFormat format=new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date date=format.parse(datestr);
		Date sqlDate=new java.sql.Date(date.getTime());
		pst=con.prepareStatement("insert into pgs values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
		pst.setString(1, str);
		pst.setString(2, tpname.getText());
		pst.setString(3, tmno.getText());
		if(tadr.getText().equals(null))
			pst.setString(4, "");
		else
			pst.setString(4, tadr.getText());
		pst.setString(5, tcity.getText());
		if(tinst.getText().equals(null))
			pst.setString(6, "");
		else
			pst.setString(6, tinst.getText());
		pst.setString(7, tparname.getText());
		pst.setString(8, tparmob.getText());
		if(tparem.getText().equals(null))
			pst.setString(9, "");
		else
			pst.setString(9, tparem.getText());
		if(ac.isSelected())
			pst.setString(10, "AC");
		else
			pst.setString(10, "NAC");
		pst.setString(11, room.getSelectionModel().getSelectedItem());
		if(yes.isSelected())
			pst.setString(12, "Yes");
		else
			pst.setString(12, "No");
		pst.setDate(13, sqlDate);
		pst.executeUpdate();
		pst.close();
		pst=con.prepareStatement("update room set booked=? where roomNo=?");
		pst.setString(2, room.getSelectionModel().getSelectedItem());
		pst.setInt(1, Integer.parseInt(tseat.getText())-1);
		pst.executeUpdate();
		pst.close();
		showMsg2("Successfully Saved");
	}
	
	public Enroll_pgs()
	{
		page();
		form();
		//buttonAction-----------------------------------------------
		next1.setOnAction(e->{setNext();i++;});
		next2.setOnAction(e->{
			if(tparname.getText().equals("")||tparmob.getText().equals(""))
				showMsg("Please, Enter the Required Fields");
			else
			{
				stack2.setVisible(false);
				stack3.setVisible(true);
				i++;
			}
		});
		search.setOnAction(e->{
			room.getItems().clear();
			try
			{
				pst=con.prepareStatement("select roomNo from room where floor=? and type=? and booked!=0");
				pst.setString(1, tfloor.getText());
				if(ac.isSelected())
					pst.setString(2, "AC");
				else
					pst.setString(2, "NAC");
				rs= pst.executeQuery();
				ArrayList<String>lst=new ArrayList<String>();
				while(rs.next())
				{
					String p=rs.getString("roomNo");
					lst.add(p);
				}
				room.getItems().addAll(lst);
				rs.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		});
		/*finish.setOnAction(e->{
			if(tfloor.getText().equals("")||room.getSelectionModel().isEmpty())
				showMsg("Please, Enter the Required Fields");
			else
			{
				try
				{
					doSave();
				}
				catch(Exception exp)
				{
					exp.printStackTrace();
				}
			}
		});*/
		room.getSelectionModel().selectedItemProperty().addListener((property,oldValue,newValue)->{
			try
			{
				pst=con.prepareStatement("Select booked from room where roomNo=?");
				pst.setString(1, room.getSelectionModel().getSelectedItem());
				rs=pst.executeQuery();
				rs.next();
				Integer a=new Integer(rs.getInt("booked"));
				tseat.setText(a.toString());
				rs.close();
			}
			catch(SQLException e1)
			{
				e1.printStackTrace();
			}
		});
		pic.setOnMouseClicked(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event){
				fileChoose();
			}
		});
		stack.setOnKeyReleased(new EventHandler<javafx.scene.input.KeyEvent>() {
			public void handle(javafx.scene.input.KeyEvent event) {
				if(event.getCode()==KeyCode.ALT)
					{
						if(i==3)
						{
							stack3.setVisible(false);
							stack2.setVisible(true);
							i--;
						}
						if(i==2)
						{
							stack2.setVisible(false);
							stack1.setVisible(true);
							i--;
						}
					}
			}
		});
		stack.getStylesheets().add(getClass().getResource("Enroll_pgs.css").toExternalForm());
	}
	
	public void fileChoose()
	{
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		File file2=fileChooser.showOpenDialog(window);
		if(file2==null)
		{
			str=file1.getPath();
			return;
		}
		str=file2.getPath();
		try
		{
			ppic=new Image(new FileInputStream(file2));
			pgpic=new ImagePattern(ppic);
			pic.setFill(pgpic);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}