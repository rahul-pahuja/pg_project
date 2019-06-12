package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main1 extends Application
{
	StackPane stack,stack2;
	VBox vbox;
	int i=0,feespaid,nfeespaid,total,fac,fnac,totalac,totalfood;
	Rectangle rectd,rect,rect2,rect3,rect4,rect5,rect6,rect7,rectf;
	Rectangle2D size;
	Button option,rm,ep,rou,pay,find,rs,menu,home,about,fp,fnd,intime,back,clear;
	HBox hbox1;
	NumberAxis xaxis,yaxis,yAxis;
	CategoryAxis xAxis;
	LineChart linechart;
	XYChart.Series data,data1;
	GridPane grid,grid1,grid2,grid3;
	Text title1,title2,tfp,tfnp,tac,tnac,which,tinstud,tinhow,which1;
	Text name1,born1,acad1,exp1,exp2,founder,name2,born2,acad2,name3,born3,acad3,exp3,exp4,name4,born4,acad4,dev,dev1;
	Text cont1,cont2,cont3,cont4,cont5,cont6,em1,em2,em3,em4,em5,text;
	Circle circle;
	ImagePattern img;
	Connection con;
	PreparedStatement pst,pst2;
	ResultSet rst,rst2;
	ImageView img1,img2,fb,insta;
	Scene scene;
	PieChart piechart;
	BarChart barChart;
	PieChart.Data slice1,slice2;
	TranslateTransition trans1;
	Room_Master rma;
	Enroll_pgs epg;
	Payment paym;
	Routine rout;
	Pg_finder pgf;
	Room_Status room_status;
	Hyperlink hyper,hyper1;
	Login login;
	Stage window;
	
	public void setForm()
	{
		//stack-----------------------------------
		stack=new StackPane();
		stack2=new StackPane();
		stack2.setStyle("-fx-background-color: white");
		//size------------------------------------
		size=Screen.getPrimary().getVisualBounds();
		//scene----------------------------------
		scene=new Scene(stack,size.getWidth(),size.getHeight());
		scene.getStylesheets().add(getClass().getResource("Main1.css").toExternalForm());
		//rectangle-------------------------------
		rectf=new Rectangle(size.getWidth(),size.getHeight()-100);
		stack2.getChildren().add(rectf);
		//rectf.setTranslateY(50);
		//rectf.setTranslateX(-size.getWidth()-90);
		rectf.getStyleClass().add("rect");
		//childForm--------------------------------
		rma=new Room_Master();
		rma.stack.setTranslateY(40);
		epg=new Enroll_pgs();
		epg.stack.setTranslateY(60);
		paym=new Payment();
		paym.stack.setTranslateY(30);
		rout=new Routine();
		rout.stack.setTranslateY(30);
		room_status=new Room_Status();
		room_status.stack.setTranslateY(30);
		pgf=new Pg_finder();
		pgf.stack.setTranslateY(20);
		stack2.getChildren().addAll(rma.stack, epg.stack, paym.stack, rout.stack, pgf.stack, room_status.stack);
		//imageButton--------------------------------
		try
		{
			img2=new ImageView(new Image(new FileInputStream("bin/application/back.png")));
			back=new Button("Back",img2);
			back.getStyleClass().add("back");
			back.setContentDisplay(ContentDisplay.LEFT);
			stack2.getChildren().add(back);
			back.setTranslateX(-630);
			back.setTranslateY(-320);
			img2.setTranslateX(5);
			img2.setFitHeight(25);
			img2.setFitWidth(25);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		//transition---------------------------------
		trans1=new TranslateTransition(new Duration(500), stack);
		//trans2=new TranslateTransition(new Duration(3000), stack2);
	}
	
	public void childForm()
	{
		rma.stack.setVisible(false);
		epg.stack.setVisible(false);
		paym.stack.setVisible(false);
		rout.stack.setVisible(false);
		pgf.stack.setVisible(false);
		room_status.stack.setVisible(false);
	}
	
	public void init1()
	{
		childForm();
		rm.setOnAction(e->{
			rma.stack.setVisible(true);
			rectf.setTranslateY(50);
			scene.setRoot(stack2);
			window.setTitle("Room Master-PgAssistant");
		});
		ep.setOnAction(e->{
			epg.stack.setVisible(true);
			rectf.setTranslateY(50);
			scene.setRoot(stack2);
			window.setTitle("Enroll Pg's-PgAssistant");
		});
		pay.setOnAction(e->{
			paym.stack.setVisible(true);
			rectf.setTranslateY(50);
			scene.setRoot(stack2);
			window.setTitle("Payment-PgAssistant");
		});
		rou.setOnAction(e->{
			rout.stack.setVisible(true);
			rectf.setTranslateY(50);
			scene.setRoot(stack2);
			window.setTitle("Routine-PgAssistant");
		});
		find.setOnAction(e->{
			pgf.stack.setVisible(true);
			rectf.setTranslateY(50);
			scene.setRoot(stack2);
			window.setTitle("Pg Finder-PgAssistant");
		});
		rs.setOnAction(e->{
			room_status.stack.setVisible(true);
			rectf.setTranslateY(50);
			scene.setRoot(stack2);
			window.setTitle("Remove Pgs-PgAssistant");
		});
		back.setOnAction(e->{
			childForm();
			scene.setRoot(stack);
			inTime();
			lineData();
			food();
			fpaid();
			fpaid1();
			window.setTitle("Home-PgAssistant");
		});
		scene.setOnKeyReleased(new EventHandler<javafx.scene.input.KeyEvent>() {
			public void handle(javafx.scene.input.KeyEvent event) {
				if(event.getCode()==KeyCode.ESCAPE)
					{
						childForm();
						scene.setRoot(stack);
						inTime();
						lineData();
						food();
						fpaid();
						fpaid1();
						window.setTitle("Home-PgAssistant");
					}
			}
		});
	}
	
	public void drawer()
	{
		//VBox-----------------------------------
		vbox=new VBox();
		//button---------------------------------
		option=new Button("");
		option.setPrefWidth(180);
		option.setPrefHeight(60);
		option.getStyleClass().add("home");
		rm=new Button("Room Master");
		rm.setPrefWidth(180);
		rm.setPrefHeight(60);
		rm.getStyleClass().add("btn");
		ep=new Button("Enroll PG's");
		ep.getStyleClass().add("btn");
		ep.setPrefWidth(180);
		ep.setPrefHeight(60);
		rou=new Button("Routine");
		rou.getStyleClass().add("btn");
		rou.setPrefWidth(180);
		rou.setPrefHeight(60);
		pay=new Button("Payment");
		pay.getStyleClass().add("btn");
		pay.setPrefWidth(180);
		pay.setPrefHeight(60);
		find=new Button("PG Finder");
		find.getStyleClass().add("btn");
		find.setPrefWidth(180);
		find.setPrefHeight(60);
		rs=new Button("Remove Pgs");
		rs.getStyleClass().add("btn");
		rs.setPrefWidth(180);
		rs.setPrefHeight(60);
		//rectangle---------------------------------
		rectd=new Rectangle(181,size.getHeight());
		rectd.getStyleClass().add("rectd");
		rectd.setTranslateX(-774);
		//form------------------------------------
		vbox.getChildren().addAll(option,rm,ep,rou,pay,find,rs);
		vbox.setTranslateX(-180);
		stack.getChildren().addAll(rectd,vbox);
	}
	
	public void form()
	{
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
		//image----------------------------------------
		try
		{
			img1=new ImageView(new Image(new FileInputStream("bin/application/menu.png")));
			img1.setFitHeight(20);
			img1.setFitWidth(25);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		//rectangle---------------------------------------
		rect=new Rectangle(size.getWidth(), size.getHeight()-180);
		stack.getChildren().add(rect);
		rect.setTranslateY(90);
		rect.getStyleClass().add("rect");
		rect2=new Rectangle(size.getWidth(), 180);
		stack.getChildren().add(rect2);
		rect2.setTranslateY(-281);
		rect2.getStyleClass().add("rect2");	
		rect3=new Rectangle(255, 160, Color.WHITE);
		rect3.setTranslateX(-408);
		rect3.setTranslateY(-28);
		stack.getChildren().add(rect3);
		rect4=new Rectangle(337, 160, Color.WHITE);
		rect4.setTranslateY(-5);
		//text------------------------------------------
		title1=new Text("An Application Designed For");
		title2=new Text("Paying Guest");
		title2.getStyleClass().add("head");
		tfp=new Text();
		tfp.getStyleClass().add("text1");
		tfnp=new Text();
		tfnp.getStyleClass().add("text1");
		tac=new Text();
		tac.setStyle("-fx-font-size:1.2em");
		tnac=new Text();
		tnac.setStyle("-fx-font-size:1.2em");
		which=new Text("Out of Which :-");
		which.setStyle("-fx-font-size:1.2em");
		tinstud=new Text();
		tinstud.setStyle("-fx-font-size:1.2em");
		tinhow=new Text();
		tinhow.setStyle("-fx-font-size:1.2em");
		which1=new Text("Out of Which :-");
		which1.setStyle("-fx-font-size:1.2em");
		//button-----------------------------------------
		menu=new Button();
		menu.setGraphic(img1);
		menu.getStyleClass().add("but");
		home=new Button("Home");
		home.getStyleClass().add("btn1");
		home.setPrefWidth(150);
		about=new Button("About Us");
		about.getStyleClass().add("btn1");
		about.setPrefWidth(150);
		//function-call----------------------------------
		fpaid();
		fpaid1();
		inTime();
		//button---------------------------------------
		fp=new Button("Fees Paid",tfp);
		fp.getStyleClass().add("btn2");
		fp.setContentDisplay(ContentDisplay.TOP);
		fp.setPrefWidth(110);
		fp.setStyle("-fx-background-color:white");
		fnd=new Button("Pending Fees",tfnp);
		fnd.getStyleClass().add("btn2");
		fnd.setContentDisplay(ContentDisplay.TOP);
		fnd.setPrefWidth(110);
		intime=new Button("InTime");
		intime.getStyleClass().add("btn3");
		//intime.setStyle("");
		intime.setPrefWidth(110);
		intime.setPrefHeight(70);
		//numberAxis------------------------------------
		xaxis=new NumberAxis();
		xaxis.setLabel("Floor Number");
		//xaxis.setAutoRanging(false);
		//xaxis.setMinorTickVisible(false);
		//xaxis.setTickUnit(1);
		yaxis=new NumberAxis();
		yaxis.setLabel("No. Of Seats/Beds Available");
		//yaxis.setAutoRanging(false);
		//yaxis.setMinorTickVisible(false);
		//yaxis.setTickUnit(2);
		//lineChart----------------------------------------
		linechart=new LineChart<>(xaxis, yaxis);
		linechart.setMinWidth(728);
		data=new XYChart.Series();
		data.setName("Stats of Month - "+getmonth());
		lineData();
		linechart.getData().add(data);
		linechart.getStyleClass().add("linechart");
		linechart.setTranslateX(-37);
		//pieChart---------------------------------------
		piechart=new PieChart();
		piechart.setTitle("Students in various Room Type");
		//piechart.setLegendSide(Side.RIGHT);
		//piechart.setClockwise(false);
		piechart.setLabelsVisible(false);
		slice1=new Data("AC", totalac);
		slice2=new Data("Non-AC", total-totalac);
		piechart.getData().add(slice1);
		piechart.getData().add(slice2);
		//BarChart--------------------------------------
		xAxis=new CategoryAxis();
		xAxis.setLabel("Who Takes Food");
		yAxis=new NumberAxis();
		yAxis.setLabel("No. Of Students");
		barChart = new BarChart(xAxis, yAxis);
		data1=new Series();
		data1.setName("Number Of Students who takes food");
		food();
		data1.getData().add(new XYChart.Data("With Food", totalfood));
		data1.getData().add(new XYChart.Data("Without Food", total-totalfood));
		barChart.getData().add(data1);
		//yAxis.setAutoRanging(false);
		//yAxis.setTickUnit(2);
		//yAxis.setUpperBound(10);
		barChart.setTranslateY(-10);
		//HBox-----------------------------------------
		hbox1=new HBox(120, home,about);
		//grid-------------------------------------------
		GridPane.setConstraints(menu, 0, 0, 1, 2, HPos.CENTER, VPos.CENTER, null, null, new Insets(40,40,0,25));
		GridPane.setConstraints(title1, 1, 0, 2, 1, HPos.LEFT, VPos.TOP, null, null, new Insets(25,0,0,0));
		GridPane.setConstraints(title2, 1, 1, 3, 1, HPos.LEFT, VPos.TOP, null, null, new Insets(0,0,0,35));
		GridPane.setConstraints(hbox1, 1, 2, 3, 1, HPos.LEFT, VPos.TOP, null, null, new Insets(34,0,15,35));
		GridPane.setConstraints(fp, 1, 3, 1, 1, HPos.LEFT, VPos.TOP, null, null, new Insets(0,20,0,35));
		GridPane.setConstraints(fnd, 2, 3, 1, 1, HPos.LEFT, VPos.TOP, null, null, new Insets(0,20,0,0));
		GridPane.setConstraints(which, 1, 4, 2, 1, HPos.LEFT, VPos.TOP, null, null, new Insets(20,0,10,45));
		GridPane.setConstraints(tac, 1, 5, 2, 1, HPos.LEFT, VPos.TOP, null, null, new Insets(10,0,10,45));
		GridPane.setConstraints(tnac, 1, 6, 2, 1, HPos.LEFT, VPos.TOP, null, null, new Insets(10,0,30,45));
		GridPane.setConstraints(linechart, 1, 7, 3, 1, HPos.LEFT, VPos.TOP, null, null, new Insets(0));
		GridPane.setConstraints(piechart, 4, 7, 1, 1, HPos.LEFT, VPos.TOP, null, null, new Insets(0));
		GridPane.setConstraints(barChart, 4, 3, 1, 4, HPos.LEFT, VPos.TOP, null, null, new Insets(0));
		GridPane.setConstraints(intime, 3, 3, 1, 1, HPos.LEFT, VPos.TOP, null, null, new Insets(0,0,0,35));
		GridPane.setConstraints(tinstud, 3, 6, 1, 1, HPos.LEFT, VPos.TOP, null, null, new Insets(10,0,0,68));
		GridPane.setConstraints(tinhow, 3, 4, 1, 1, HPos.LEFT, VPos.TOP, null, null, new Insets(15,0,0,45));
		GridPane.setConstraints(which1, 3, 5, 1, 1, HPos.LEFT, VPos.TOP, null, null, new Insets(10,0,0,45));
		GridPane.setConstraints(rect4, 3, 4, 1, 3, HPos.LEFT, VPos.TOP, null, null, new Insets(0,0,0,35));
		barChart.setPadding(new Insets(0));
		grid=new GridPane();
		grid.setVgap(5);
		grid.setHgap(5);
		//grid.setGridLinesVisible(true);
		grid.getChildren().addAll(rect4,menu,title1,title2,hbox1,intime,fp,fnd,which,tac,tnac,linechart,barChart,piechart,tinstud,tinhow,which1);
		stack.getChildren().add(grid);
	}
	
	public void formAbout()
	{
		//image---------------------------------
		try
		{
			img=new ImagePattern(new Image(new FileInputStream("bin/application/sir.jpg")));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		//circle------------------------------------
		circle=new Circle(90);
		circle.setFill(img);
		circle.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				circle.setEffect(new DropShadow(+20d, 0d, +2d, Color.DARKSEAGREEN));
			}
		});
		circle.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				circle.setEffect(null);
			}
		});
		//text-----------------------------------
		name1=new Text("Name :");
		name1.setStyle("-fx-font-size:1.35em");
		name2=new Text("Rajesh Bansal");
		name2.setStyle("-fx-font-size:1.35em");
		born1=new Text("Born :");
		born1.setStyle("-fx-font-size:1.35em");
		born2=new Text("22 December 1979");
		born2.setStyle("-fx-font-size:1.35em");
		acad1=new Text("Academic :");
		acad1.setStyle("-fx-font-size:1.35em");
		acad2=new Text("M.Sc(IT), MCA (Master Of Computer Application)");
		acad2.setStyle("-fx-font-size:1.35em");
		exp1=new Text("Expert in :");
		exp1.setStyle("-fx-font-size:1.35em");
		exp2=new Text("Core & Advance Java, Python, Android, Spring, \nHibernate, PHP, AngularJS, Node.js, C++, etc");
		exp2.setStyle("-fx-font-size:1.35em");
		founder=new Text("Founder and Director of Bangalore Computer Education");
		founder.setStyle("-fx-font-size:1.35em");
		name3=new Text("Name :");
		name3.setStyle("-fx-font-size:1.35em");
		name4=new Text("Rahul Pahuja");
		name4.setStyle("-fx-font-size:1.35em");
		born3=new Text("Born :");
		born3.setStyle("-fx-font-size:1.35em");
		born4=new Text("23 September 1997");
		born4.setStyle("-fx-font-size:1.35em");
		acad3=new Text("Academic :");
		acad3.setStyle("-fx-font-size:1.35em");
		acad4=new Text("Currently Doing B.Tech (Computer Science 3rd Year) in Punjabi Universty");
		acad4.setStyle("-fx-font-size:1.35em");
		exp3=new Text("Expert in :");
		exp3.setStyle("-fx-font-size:1.35em");
		exp4=new Text("Core Java, C, C++, HTML, CSS, MySQL");
		exp4.setStyle("-fx-font-size:1.35em");
		dev=new Text("Application Developed By :-");
		dev.setStyle("-fx-font-size:1.5em");
		dev.setTranslateY(-230);
		dev1=new Text("Application Developed Under the Guidance Of :-");
		dev1.setStyle("-fx-font-size:1.5em");
		dev1.setTranslateY(-260);
		cont1=new Text("Contact Us :-");
		cont1.setStyle("-fx-font-size:1.5em");
		cont1.setTranslateY(-268);
		cont2=new Text("Contact No. :-");
		cont2.setStyle("-fx-font-size:1.35em");
		cont3=new Text("Rahul :");
		cont3.setStyle("-fx-font-size:1.35em");
		cont4=new Text("Rajesh :");
		cont4.setStyle("-fx-font-size:1.35em");
		cont5=new Text("+918198099365");
		cont5.setStyle("-fx-font-size:1.35em");
		cont6=new Text("+919872246056");
		cont6.setStyle("-fx-font-size:1.35em");
		em1=new Text("email-id :-");
		em1.setStyle("-fx-font-size:1.45em");
		em2=new Text("Rahul :");
		em2.setStyle("-fx-font-size:1.35em");
		em3=new Text("Rajesh :");
		em3.setStyle("-fx-font-size:1.35em");
		em4=new Text("rpahuja1520@gmail.com");
		em4.setStyle("-fx-font-size:1.35em");
		em5=new Text("bcebti@gmail.com");
		em5.setStyle("-fx-font-size:1.35em");
		//rectangle------------------------------
		rect5=new Rectangle(780, 220, Color.WHITE);
		rect5.setTranslateX(7);
		//rect5.setStroke(Color.BLACK);
		rect6=new Rectangle(780, 170, Color.WHITE);
		rect6.setTranslateX(-10);
		rect6.setTranslateY(-20);
		//rect6.setStroke(Color.BLACK);
		rect7=new Rectangle(418, 250, Color.WHITE);
		rect7.setTranslateX(-10);
		rect7.setTranslateY(-20);
		//hyperLink-----------------------------
		hyper=new Hyperlink();
		hyper1=new Hyperlink();
		text=new Text("Connect on Social Sites");
		text.setStyle("-fx-font-size:1.5em");
		text.setTranslateX(350);
		text.setTranslateY(310);
		text.setVisible(false);
		try
		{
			fb=new ImageView(new Image(new FileInputStream("bin/application/fbrl.png")));
			insta=new ImageView(new Image(new FileInputStream("bin/application/insta.png")));
			insta.setFitWidth(32);
			insta.setFitHeight(32);
			fb.setFitWidth(32);
			fb.setFitHeight(32);
			hyper.setGraphic(fb);
			hyper1.setGraphic(insta);
			hyper1.setVisible(false);
			hyper.setVisible(false);
			hyper1.setTranslateX(485);
			hyper1.setTranslateY(310);
			hyper.setTranslateX(520);
			hyper.setTranslateY(310);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		//grid-----------------------------------
		GridPane.setConstraints(rect5, 0, 0, 3, 6, HPos.CENTER, VPos.CENTER, null, null, new Insets(0));
		GridPane.setConstraints(circle, 0, 0, 1, 6, HPos.CENTER, VPos.CENTER, null, null, new Insets(0,30,0,0));
		GridPane.setConstraints(name1, 1, 1, 1, 1, HPos.RIGHT, VPos.CENTER, null, null, new Insets(0));
		GridPane.setConstraints(name2, 2, 1, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(0));
		GridPane.setConstraints(born1, 1, 2, 1, 1, HPos.RIGHT, VPos.CENTER, null, null, new Insets(0));
		GridPane.setConstraints(born2, 2, 2, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(0));
		GridPane.setConstraints(acad1, 1, 3, 1, 1, HPos.RIGHT, VPos.CENTER, null, null, new Insets(0));
		GridPane.setConstraints(acad2, 2, 3, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(0));
		GridPane.setConstraints(exp1, 1, 4, 1, 1, HPos.RIGHT, VPos.TOP, null, null, new Insets(0));
		GridPane.setConstraints(exp2, 2, 4, 1, 1, HPos.LEFT, VPos.TOP, null, null, new Insets(0));
		GridPane.setConstraints(founder, 1, 5, 2, 1, HPos.CENTER, VPos.TOP, null, null, new Insets(0));
		GridPane.setConstraints(rect6, 0, 0, 2, 4, HPos.CENTER, VPos.CENTER, null, null, new Insets(0));
		GridPane.setConstraints(name3, 0, 0, 1, 1, HPos.RIGHT, VPos.CENTER, null, null, new Insets(0));
		GridPane.setConstraints(name4, 1, 0, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(0));
		GridPane.setConstraints(born3, 0, 1, 1, 1, HPos.RIGHT, VPos.CENTER, null, null, new Insets(0));
		GridPane.setConstraints(born4, 1, 1, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(0));
		GridPane.setConstraints(acad3, 0, 3, 1, 1, HPos.RIGHT, VPos.TOP, null, null, new Insets(0));
		GridPane.setConstraints(acad4, 1, 3, 1, 1, HPos.LEFT, VPos.TOP, null, null, new Insets(0));
		GridPane.setConstraints(exp3, 0, 2, 1, 1, HPos.RIGHT, VPos.CENTER, null, null, new Insets(0));
		GridPane.setConstraints(exp4, 1, 2, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(0));
		GridPane.setConstraints(dev, 0, 4, 2, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(0));
		GridPane.setConstraints(dev1, 0, 6, 3, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(0));
		GridPane.setConstraints(cont1, 0, 6, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(0));
		GridPane.setConstraints(cont2, 0, 0, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(0));
		GridPane.setConstraints(cont3, 0, 1, 1, 1, HPos.RIGHT, VPos.CENTER, null, null, new Insets(0));
		GridPane.setConstraints(cont4, 0, 2, 1, 1, HPos.RIGHT, VPos.CENTER, null, null, new Insets(0));
		GridPane.setConstraints(cont5, 1, 1, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(0));
		GridPane.setConstraints(cont6, 1, 2, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(0));
		GridPane.setConstraints(em1, 0, 3, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(0));
		GridPane.setConstraints(em2, 0, 4, 1, 1, HPos.RIGHT, VPos.CENTER, null, null, new Insets(0));
		GridPane.setConstraints(em3, 0, 5, 1, 1, HPos.RIGHT, VPos.CENTER, null, null, new Insets(0));
		GridPane.setConstraints(em4, 1, 4, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(0));
		GridPane.setConstraints(em5, 1, 5, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(0));
		GridPane.setConstraints(rect7, 0, 0, 2, 7, HPos.LEFT, VPos.TOP, null, null, new Insets(0));
		grid1=new GridPane();
		grid1.setVgap(15);
		grid1.setHgap(5);
		//grid1.setGridLinesVisible(true);
		grid1.getChildren().addAll(rect5,dev1,circle,name1,born1,acad1,exp1,exp2,founder,name2,born2,acad2);
		//grid1.setPadding(new Insets(70));
		grid2=new GridPane();
		grid2.setVgap(15);
		grid2.setHgap(5);
		//grid2.setGridLinesVisible(true);
		grid2.getChildren().addAll(rect6,dev,name3,born3,acad3,exp3,exp4,name4,born4,acad4);
		//grid2.setPadding(new Insets(70));
		grid3=new GridPane();
		grid3.setVgap(15);
		grid3.setHgap(5);
		//grid3.setGridLinesVisible(true);
		grid3.getChildren().addAll(rect7,cont1,cont2,cont3,cont4,cont5,cont6,em1,em2,em3,em4,em5);
		clear=new Button("Clear Data");
		clear.getStyleClass().add("btnm");
		clear.setVisible(false);
		clear.setTranslateX(590);
		clear.setTranslateY(310);
		clear.setTooltip(new Tooltip("clears data regarding payment untill now"));
		stack.getChildren().addAll(grid2,grid1,grid3,hyper,hyper1,text,clear);
		grid1.setTranslateY(480);
		grid1.setTranslateX(70);
		grid2.setTranslateY(275);
		grid2.setTranslateX(70);
		grid3.setTranslateY(275);
		grid3.setTranslateX(910);
		hyper.setOnAction(e->{
			getHostServices().showDocument("http://www.facebook.com/rahul4024");
		});
		hyper1.setOnAction(e->{
			getHostServices().showDocument("https://www.instagram.com/_rahulpahuja");
		});
	}
	
	public void inTime()
	{
		Calendar cal = Calendar.getInstance();
		String str=new SimpleDateFormat("MM").format(cal.getTime());
		int j=0,num=Integer.parseInt(str);
		try
		{
			pst=con.prepareStatement("select curDate from routine where month(curDate)=?");
			pst.setString(1, ""+num);
			rst=pst.executeQuery();
			while(rst.next())
			{
				j++;
			}
			rst.close();
			tinhow.setText("How many times intime is challenged : "+j);
			j=0;
			pst=con.prepareStatement("select distinct pgname from routine where month(curDate)=?");
			pst.setString(1, ""+num);
			rst=pst.executeQuery();
			while(rst.next())
			{
				j++;
			}
			rst.close();
			tinstud.setText("How many students are defaulters : "+j);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void home(boolean b)
	{
		fp.setVisible(b);
		fnd.setVisible(b);
		which.setVisible(b);
		tac.setVisible(b);
		tnac.setVisible(b);
		linechart.setVisible(b);
		barChart.setVisible(b);
		piechart.setVisible(b);
		intime.setVisible(b);
		tinhow.setVisible(b);
		tinstud.setVisible(b);
		which1.setVisible(b);
		rect4.setVisible(b);
		rect3.setVisible(b);
	}
	
 	public String getmonth()
	{
		Calendar cal = Calendar.getInstance();
		String month="wrong";
		String str=new SimpleDateFormat("MM").format(cal.getTime());
		int num=Integer.parseInt(str);
		DateFormatSymbols dfs = new DateFormatSymbols();
	    String[] months = dfs.getMonths();
	    if (num >= 1 && num <= 12 )
	    {
	    	month = months[num-1];
	    }
	    return(month);
	}
	
	public void lineData()
	{
		data.getData().removeAll();
		int j=0,seat;
		while(true)
		{
			try
			{
				seat=0;
				pst=con.prepareStatement("select booked from room where floor=?");
				pst.setString(1, Integer.toString(j));
				rst=pst.executeQuery();
				if(rst.next())
				{
					seat+=rst.getInt("booked");
					while(rst.next())
					{
						seat+=rst.getInt("booked");
					}
					data.getData().add(new XYChart.Data(j,seat));
					j++;
					rst.close();
				}
				else
				{
					rst.close();
					return;
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return;
			}
		}
	}
	
	public void food()
	{
		totalfood=0;
		try
		{
			pst=con.prepareStatement("select pgname from pgs where food=?");
			pst.setString(1, "Yes");
			rst=pst.executeQuery();
			while(rst.next())
			{
				totalfood++;
			}
			rst.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void fpaid()
	{
		feespaid=0;
		nfeespaid=0;total=0;
		Calendar cal = Calendar.getInstance();
		String str=new SimpleDateFormat("MM").format(cal.getTime());
		int num=Integer.parseInt(str);
		try
		{
			pst=con.prepareStatement("select pgname from pgs");
			rst=pst.executeQuery();
			while(rst.next())
			{
				total++;
				pst2=con.prepareStatement("select pgname,status from payment where pgname=? and month(curdate)=? and status=1");
				pst2.setString(1, rst.getString("pgname"));
				pst2.setString(2, ""+num);
				rst2=pst2.executeQuery();
				if(rst2.next())
					feespaid++;
				rst2.close();
			}
			rst.close();
			nfeespaid=total-feespaid;
			tfp.setText(""+feespaid);
			tfnp.setText(""+nfeespaid);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}		
	}
	
	public void fpaid1()
	{
		fac=0;fnac=0;totalac=0;
		Calendar cal = Calendar.getInstance();
		String str=new SimpleDateFormat("MM").format(cal.getTime());
		int num=Integer.parseInt(str);
		try
		{
			pst=con.prepareStatement("select pgname from pgs where rtype=?");
			pst.setString(1, "AC");
			rst=pst.executeQuery();
			while(rst.next())
			{
				totalac++;
				pst2=con.prepareStatement("select pgname,status from payment where pgname=? and month(curDate)=? and status=1");
				pst2.setString(1, rst.getString("pgname"));
				pst2.setString(2, ""+num);
				rst2=pst2.executeQuery();
				if(rst2.next())
					fac++;
				rst2.close();
			}
			rst.close();
			tac.setText("Students in AC Rooms : "+fac);
			fnac=feespaid-fac;
			tnac.setText("Students in Non-AC Rooms : "+fnac);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void start(Stage stage) throws Exception
	{
		setForm();
		drawer();
		form();
		formAbout();
		grid1.setVisible(false);
		grid2.setVisible(false);
		grid3.setVisible(false);
		menu.setOnAction(e->{
			if(i==0)
			{
				trans1.setToX(180);
				trans1.play();
				i++;
			}
			else
			{
				trans1.setToX(0);
				trans1.play();
				i=0;
			}
		});
		about.setOnAction(e->{
			home(false);
			grid1.setVisible(true);
			grid2.setVisible(true);
			grid3.setVisible(true);
			hyper.setVisible(true);
			hyper1.setVisible(true);
			text.setVisible(true);
			clear.setVisible(true);
		});
		home.setOnAction(e->{
			grid1.setVisible(false);
			grid2.setVisible(false);
			grid3.setVisible(false);
			hyper.setVisible(false);
			hyper1.setVisible(false);
			text.setVisible(false);
			clear.setVisible(false);
			home(true);
		});
		clear.setOnAction(e->{
			try
			{
				Calendar cal = Calendar.getInstance();
				String str=new SimpleDateFormat("MM").format(cal.getTime());
				int num=Integer.parseInt(str);
				pst=con.prepareStatement("delete from payment where month(curDate)!=?");
				pst.setString(1, ""+num);
				pst.executeUpdate();
			}
			catch(Exception exp)
			{
				exp.printStackTrace();
			}
		});
		init1();
		login=new Login();
		window=stage;
		//stage.setFullScreen(true);
		stage.setMaximized(true);
		stage.setScene(login.scene);
		login.login.setOnAction(e->{
			if(login.user.getText().equals("root")&&login.pswrd.getText().equals("rahul"))
				{
					stage.setScene(scene);
					stage.setTitle("Home-PgAssistant");
				}
			else
				showMsg("Please Enter valid Username and Password");
		});
		stage.setTitle("Connect to PgAssistant");
		stage.show();
	}
	
	public void showMsg(String s)
	{
		Alert alert=new Alert(AlertType.ERROR);
		alert.setHeaderText(s);
		alert.setTitle("Error");
		alert.show();
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}
}