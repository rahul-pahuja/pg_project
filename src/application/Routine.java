package application;
import java.io.FileInputStream;
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
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
public class Routine
{
	GridPane grid;
	Text title,reason,intime,hr,minute;
	Button filter,save,sms;
	TextArea treason;
	Rectangle rect3,rect4;
	Rectangle2D size;
	ListView<String> list;
	HBox hbox;
	ComboBox<String> hour,min;
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	StackPane stack;
	ImageView img1;
	
	public void form()
	{
		//text--------------------------------------
		title=new Text("InTime PG Routine");
		title.getStyleClass().add("head");
		reason=new Text("Reason");
		reason.getStyleClass().add("para");
		intime=new Text("Intime");
		intime.getStyleClass().add("para");
		hr=new Text("hour");
		hr.getStyleClass().add("para");
		minute=new Text("minutes");
		minute.getStyleClass().add("para");
		//textArea------------------------------------
		treason=new TextArea();
		treason.setPrefWidth(250);
		treason.setPrefHeight(120);
		treason.setFont(Font.font(14));
		treason.getStyleClass().add("tfield");
		//button--------------------------------------
		filter=new Button("Filter");
		filter.getStyleClass().add("btnn");
		save=new Button("Save");
		save.getStyleClass().add("btnn");
		sms=new Button("Send SMS");
		sms.getStyleClass().add("btnn");
		//stack---------------------------------------------
		stack=new StackPane();
		stack.getStyleClass().add("stack");
		//size----------------------------------------
		size=Screen.getPrimary().getVisualBounds();
		//rectangle------------------------------------
		rect3=new Rectangle(size.getWidth()-200, 378);
		rect3.getStyleClass().add("rect");
		rect4=new Rectangle(750,360);
		rect4.getStyleClass().add("rect");
		rect4.setTranslateX(-200);
		stack.getChildren().addAll(rect3,rect4);
		//list----------------------------------------
		list=new ListView<String>();
		list.setPrefSize(220, 320);
		list.setFocusTraversable(false);
		list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		//comboBox-------------------------------------
		hour=new ComboBox<String>();
		hour=new ComboBox<String>();
		hour.setPrefSize(75, 20);
		//hour.setEditable(true);
		hour.getStyleClass().add("tfield");
		for(Integer i=0; i<24; i++)
			hour.getItems().add(i.toString());
		min=new ComboBox<String>();
		min=new ComboBox<String>();
		min.setPrefSize(75, 20);
		//min.setEditable(true);
		min.getStyleClass().add("tfield");
		for(Integer i=0; i<=59; i++)
			min.getItems().add(i.toString());
		//HBox----------------------------------------
		hbox=new HBox(17);
		hbox.getChildren().addAll(save,sms);
		//grid---------------------------------------
		GridPane.setConstraints(title, 0, 0, 5, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(5,10,30,40));
		GridPane.setConstraints(list, 0, 1, 1, 4, HPos.CENTER, VPos.CENTER, null, null, new Insets(20,10,20,0));
		GridPane.setConstraints(filter, 5, 1, 1, 1, HPos.RIGHT, VPos.CENTER, null, null, new Insets(30,10,0,10));
		GridPane.setConstraints(intime, 1, 2, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(20,10,0,10));
		GridPane.setConstraints(hour, 2, 2, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(20,0,0,10));
		GridPane.setConstraints(hr, 3, 2, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(20,10,0,0));
		GridPane.setConstraints(min, 4, 2, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(20,0,0,0));
		GridPane.setConstraints(minute, 5, 2, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(20,10,0,0));
		GridPane.setConstraints(reason, 1, 3, 2, 1, HPos.LEFT, VPos.TOP, null, null, new Insets(20,10,0,10));
		GridPane.setConstraints(treason, 2, 3, 4, 1, HPos.LEFT, VPos.BOTTOM, null, null, new Insets(20,10,20,10));
		GridPane.setConstraints(hbox, 3, 4, 3, 1, HPos.RIGHT, VPos.CENTER, null, null, new Insets(10,10,0,35));
		grid=new GridPane();
		grid.setVgap(5);
		grid.setHgap(5);
		//grid.setGridLinesVisible(true);
		grid.getChildren().addAll(title, list, filter, intime, hour, hr, min, minute, reason, treason, hbox);
		stack.getChildren().add(grid);
		grid.setAlignment(Pos.CENTER);
		grid.setTranslateX(-175);
		grid.setTranslateY(-40);
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
		//image-----------------------------------------
		try
		{
			img1=new ImageView(new Image(new FileInputStream("bin/application/bookmark.png")));
			stack.getChildren().add(img1);
			img1.setFitHeight(80);
			img1.setFitWidth(80);
			img1.setTranslateX(-540);
			img1.setTranslateY(-168);
		}
		catch(IOException exp)
		{
			exp.printStackTrace();
		}
	}
	
	public void addList()
	{
		try
		{
			pst=con.prepareStatement("select pgname from pgs");
			rs=pst.executeQuery();
			ArrayList<String> arr=new ArrayList<String>();
			while(rs.next())
			{
				arr.add(rs.getString("pgname"));
			}
			list.getItems().addAll(arr);
			rs.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void doFilter()
	{
		ArrayList<String> lst=new ArrayList<String>();
		for(int i=0; i < list.getItems().size(); i++)
		{
			if(list.getSelectionModel().isSelected(i)==false)
				lst.add(list.getItems().get(i));
		}
		list.getItems().removeAll(lst);
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
	
	public Routine()
	{
		form();
		addList();
		filter.setOnAction(e->doFilter());
		list.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event)
			{
				if(event.getClickCount()==2)
				{
					hour.requestFocus();
				}
			}
		});
		save.setOnAction(e->{
			if(list.getSelectionModel().getSelectedItem()==null||hour.getSelectionModel().getSelectedItem()==null||min.getSelectionModel().getSelectedItem()==null)
				showMsg("Please, Select Required Fields");
			else
			{
				try
				{
					String datestr = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
					DateFormat format=new SimpleDateFormat("dd/MM/yyyy");
					java.util.Date date=format.parse(datestr);
					Date sqlDate=new java.sql.Date(date.getTime());
					pst=con.prepareStatement("insert into routine values(?,?,?,?)");
					pst.setString(1, list.getSelectionModel().getSelectedItem());
					pst.setString(2, hour.getSelectionModel().getSelectedItem()+":"+min.getSelectionModel().getSelectedItem());
					if(treason.getText().equals(""))
						pst.setString(3, "");
					else
						pst.setString(3, treason.getText());
					pst.setDate(4, sqlDate);
					pst.executeUpdate();
					pst.close();
					showMsg2("Successfully Saved");
				}
				catch(Exception exp)
				{
					exp.printStackTrace();
				}
			}
		});
		String m="Your ward is found guilty in breaking the rules and regulations"
				+ " of the PG and challenging the intime of Pg and came Pg at"
				+ hour.getSelectionModel().getSelectedItem()+":"+min.getSelectionModel().getSelectedItem()
				+" and the reason stated is "+treason.getText();
		sms.setOnAction(e->{
			String resp=SSt_SMS.bceSunSoftSend("8198099365", m);
			 if(resp.indexOf("Exception")!=-1)
				 System.out.println("Check Internet Connection");
			else
				if(resp.indexOf("successfully")!=-1)
					System.out.println("Sent");
				else
					System.out.println( "Invalid Number");
		});
		stack.getStylesheets().add(getClass().getResource("routine.css").toExternalForm());
	}
}