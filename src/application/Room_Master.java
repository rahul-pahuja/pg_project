package application;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
public class Room_Master
{
	Text rno,bed,type,flr,title;
	TextField trno,tbed,tflr;
	StackPane stack;
	RadioButton ac,nac;
	Rectangle2D size;
	Rectangle rect,rect2;
	ToggleGroup group;
	Button add,remove,update;
	GridPane grid;
	HBox hbox,hbox2;
	ImageView img;
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	public void initial()
	{
		//pane-------------------------------------------
		stack=new StackPane();
		grid=new GridPane();
		grid.setVgap(5);
		grid.setHgap(5);
		//grid.setGridLinesVisible(true);
		grid.getStyleClass().add("grid");
		//text--------------------------------------------
		title=new Text("Room Master");
		title.getStyleClass().add("head1");
		rno=new Text("Room");
		rno.getStyleClass().add("text1");
		bed=new Text("Bed");
		bed.getStyleClass().add("text1");
		type=new Text("Type");
		type.getStyleClass().add("text1");
		flr=new Text("Floor");
		flr.getStyleClass().add("text1");
		//textField------------------------------------------
		trno=new TextField();
		trno.setPrefWidth(330);
		trno.setFont(Font.font(16));
		trno.getStyleClass().add("field");
		tbed=new TextField();
		tbed.setFont(Font.font(16));
		tbed.getStyleClass().add("field");
		tflr=new TextField();
		tflr.setFont(Font.font(16));
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
		add=new Button("ADD");
		remove=new Button("REMOVE");
		update=new Button("UPDATE");
		//HBox----------------------------------------------
		hbox=new HBox();
		hbox.getChildren().addAll(ac,nac);
		hbox.setSpacing(45);
		hbox2=new HBox();
		hbox2.getChildren().addAll(add,update,remove);
		hbox2.setSpacing(20);
		//rectangle-------------------------------------------
		rect=new Rectangle(size.getWidth()-150, 418);
		rect.getStyleClass().add("rect");
		rect2=new Rectangle(626, 400);
		rect2.getStyleClass().add("rect");
		rect2.setTranslateX(-285);
		stack.getChildren().addAll(rect,rect2);
		//gridSet----------------------------------------------
		GridPane.setConstraints(title, 0, 0, 2, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(0,10,30,10));
		GridPane.setConstraints(rno, 0, 1, 1, 1, HPos.RIGHT, VPos.CENTER, null, null, new Insets(40,20,20,10));
		GridPane.setConstraints(trno, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(20,10,20,10));
		GridPane.setConstraints(bed, 0, 2, 1, 1, HPos.RIGHT, VPos.CENTER, null, null, new Insets(20,20,20,10));
		GridPane.setConstraints(tbed, 1, 2, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(20,10,20,10));
		GridPane.setConstraints(type, 0, 3, 1, 1, HPos.RIGHT, VPos.CENTER, null, null, new Insets(20,20,20,10));
		GridPane.setConstraints(hbox, 1, 3, 1, 1, HPos.CENTER, VPos.BASELINE, null, null, new Insets(20,10,20,10));
		GridPane.setConstraints(flr, 0, 4, 1, 1, HPos.RIGHT, VPos.CENTER, null, null, new Insets(20,20,20,10));
		GridPane.setConstraints(tflr, 1, 4, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(20,10,20,10));
		GridPane.setConstraints(hbox2, 1, 5, 1, 1, HPos.RIGHT, VPos.CENTER, null, null, new Insets(20,10,20,115));
		grid.getChildren().addAll(title,rno,trno,bed,tbed,type,hbox,flr,tflr,hbox2);
		stack.getChildren().add(grid);
		//pane.getChildren().add(grid);
		//grid.setGridLinesVisible(true);
		grid.setTranslateX(205);
		grid.setTranslateY(90);
		//image------------------------------------------
		try
		{
			img=new ImageView(new Image(new FileInputStream("bin/application/bookmark.png")));
			stack.getChildren().add(img);
			//pane.getChildren().add(img);
			img.setFitWidth(80);
			img.setFitHeight(80);
			img.setTranslateX(-565);
			img.setTranslateY(-187);
		}
		catch(FileNotFoundException exp)
		{
			exp.printStackTrace();
		}
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
	
	public void doSave()
	{
		if(trno.getText().equals("")||tbed.getText().equals("")||tflr.getText().equals(""))
			showMsg("Please, Enter the required fields");
		else
		{
			try
			{
				pst=con.prepareStatement("select roomNo from room where roomNo=?" );
				pst.setInt(1, Integer.parseInt(trno.getText()));
				rs= pst.executeQuery();
				while(rs.next())
				{
					int p=rs.getInt("roomNo");
					if(p==Integer.parseInt(trno.getText()))
					{
						showMsg("Already Registered");
						rs.close();
						trno.clear();
						tbed.clear();
						tflr.clear();
						return;
					}
				}
				rs.close();
				pst=con.prepareStatement("insert into room values(?,?,?,?,?)");
				pst.setInt(1, Integer.parseInt(trno.getText()));
				pst.setInt(2, Integer.parseInt(tbed.getText()));
				if(ac.isSelected())
					pst.setString(3, "AC");
				else
					pst.setString(3, "NAC");
				pst.setInt(4, Integer.parseInt(tflr.getText()));
				pst.setInt(5, Integer.parseInt(tbed.getText()));
				pst.executeUpdate();
				pst.close();
				showMsg2("Added Successfully");
				trno.clear();
				tbed.clear();
				tflr.clear();
			}
			catch(Exception exp)
			{
				exp.printStackTrace();
			}
		}
	}
	
	public void update()
	{
		if(trno.getText().equals("")||tbed.getText().equals("")||(ac.isSelected()&&nac.isSelected())||tflr.getText().equals(""))
			showMsg("Please, Enter the required fields");
		else
		{
			try
			{
				pst=con.prepareStatement("select roomNo, booked, bed from room where roomNo=?" );
				pst.setInt(1, Integer.parseInt(trno.getText()));
				rs= pst.executeQuery();
				while(rs.next())
				{
					int p=rs.getInt("roomNo");
					if(p==Integer.parseInt(trno.getText()))
					{
						pst=con.prepareStatement("update room set bed=?, type=?, floor=?, booked=? where roomNo=?");
						pst.setInt(1, Integer.parseInt(tbed.getText()));
						if(ac.isSelected())
							pst.setString(2, "AC");
						else
							pst.setString(2, "NAC");
						pst.setInt(3, Integer.parseInt(tflr.getText()));
						pst.setInt(4, rs.getInt("booked")-(rs.getInt("bed")-Integer.parseInt(tbed.getText())));
						pst.setInt(5, Integer.parseInt(trno.getText()));
						pst.executeUpdate();
						pst.close();
						showMsg2("Updated Successfully");
						trno.clear();
						tbed.clear();
						tflr.clear();
						return;
					}
				}
				showMsg("Not Registered");
			}
			catch(Exception exp)
			{
				exp.printStackTrace();
			}
			trno.clear();
			tbed.clear();
			tflr.clear();
		}
	}
	
	public void doRemove()
	{
		if(trno.getText().equals(""))
			{
				showMsg("Please, Enter the required fields");
				return;
			}
		else
		{
			try
			{
				pst=con.prepareStatement("delete from room where roomNo=?");
				pst.setInt(1, Integer.parseInt(trno.getText()));
				int x=pst.executeUpdate();
				//System.out.println(x);
				if(x==1)
					showMsg2("Record Deleted");
				else
					showMsg("Invalid Room No.");
				pst.close();
			}
			catch(Exception exp)
			{
				exp.printStackTrace();
			}
			trno.clear();
			tbed.clear();
			tflr.clear();
		}
	}
	
	public Room_Master()
	{
		initial();
		nac.setSelected(true);
		add.setOnAction(e->doSave());
		remove.setOnAction(e->doRemove());
		update.setOnAction(e->update());
		stack.getStylesheets().add(getClass().getResource("room_Master.css").toExternalForm());
	}
}