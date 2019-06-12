package application;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

public class Room_Status
{
	Text name, roomno, title;
	StackPane stack;
	Rectangle2D size;
	Rectangle rect,rect2;
	Button remove;
	GridPane grid;
	ImageView img;
	Connection con;
	PreparedStatement pst;
	ComboBox<String> namefield;
	TextField roomfield;
	ResultSet rs;
	
	public void createLayout()
	{
		//pane-------------------------------------------
		stack=new StackPane();
		grid=new GridPane();
		grid.setVgap(5);
		grid.setHgap(5);
		//grid.setGridLinesVisible(true);
		grid.getStyleClass().add("grid");
		
		//text--------------------------------------------
		title=new Text("Remove Pgs");
		title.getStyleClass().add("head1");
		name = new Text("Name");
		name.getStyleClass().add("text1");
		roomno = new Text("Room No.");
		roomno.getStyleClass().add("text1");
		
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

		//textField------------------------------------------
		roomfield = new TextField();
		roomfield.setPrefWidth(330);
		roomfield.setFont(Font.font(16));
		roomfield.getStyleClass().add("field");
		roomfield.setEditable(false);
		
		//comboBox------------------------------------------
		namefield = new ComboBox<>();
		namefield.setPrefWidth(360);
		namefield.getStyleClass().add("field");
		populateName();
		
		//sizeScreen-----------------------------------------
		size=Screen.getPrimary().getVisualBounds();
		
		//button--------------------------------------------
		remove=new Button("REMOVE");
		
		//rectangle-------------------------------------------
		rect=new Rectangle(size.getWidth()-150, 418);
		rect.getStyleClass().add("rect");
		rect2=new Rectangle(626, 400);
		rect2.getStyleClass().add("rect");
		rect2.setTranslateX(-285);
		stack.getChildren().addAll(rect,rect2);
		
		//gridSet----------------------------------------------
		GridPane.setConstraints(title, 0, 0, 2, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(0,10,30,10));
		GridPane.setConstraints(name, 0, 1, 1, 1, HPos.RIGHT, VPos.CENTER, null, null, new Insets(40,20,20,10));
		GridPane.setConstraints(namefield, 1, 1, 1, 1, HPos.RIGHT, VPos.CENTER, null, null, new Insets(40,20,20,10));
		GridPane.setConstraints(roomno, 0, 2, 1, 1, HPos.RIGHT, VPos.CENTER, null, null, new Insets(20,20,20,10));
		GridPane.setConstraints(roomfield, 1, 2, 1, 1, HPos.RIGHT, VPos.CENTER, null, null, new Insets(20,20,20,10));
		GridPane.setConstraints(remove, 1, 3, 1, 1, HPos.RIGHT, VPos.CENTER, null, null, new Insets(70,20,20,10));
		grid.getChildren().addAll(title, name, namefield, roomno, roomfield, remove);
		stack.getChildren().add(grid);
		//grid.setGridLinesVisible(true);
		grid.setTranslateX(165);
		grid.setTranslateY(90);
		
		//image------------------------------------------
		try
		{
			img=new ImageView(new Image(new FileInputStream("bin/application/bookmark.png")));
			stack.getChildren().add(img);
			img.setFitWidth(80);
			img.setFitHeight(80);
			img.setTranslateX(-565);
			img.setTranslateY(-187);
		}
		catch(FileNotFoundException exp)
		{
			exp.printStackTrace();
		}
	}
	
	public void populateName() {
		namefield.getSelectionModel().clearSelection();
		roomfield.setText("");
		namefield.getItems().clear();
		try
		{
			pst=con.prepareStatement("select pgname from pgs");
			rs=pst.executeQuery();
			while(rs.next())
			{
				namefield.getItems().add(rs.getString("pgname"));
			}
			rs.close();
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
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
	
	public void setActions() {
		namefield.getSelectionModel().selectedItemProperty().addListener((property,oldValue,newValue)->{
			try
			{
				pst=con.prepareStatement("select rno from pgs where pgname=?");
				pst.setString(1, namefield.getSelectionModel().getSelectedItem());
				rs=pst.executeQuery();
				rs.next();
				roomfield.setText(rs.getString("rno"));
				rs.close();
			}
			catch(Exception exp)
			{
				exp.printStackTrace();
			}
		});
		remove.setOnAction(e->{
			PreparedStatement book;
			int booked;
			if(namefield.getSelectionModel().isEmpty())
				showMsg("Please, Fill the Required Fields");
			else
			{
				try
				{
					pst=con.prepareStatement("delete from pgs where pgname=?");
					pst.setString(1, namefield.getSelectionModel().getSelectedItem());
					int delete=pst.executeUpdate();
					if(delete>0) {
						showMsg2("Record Deleted");
						book=con.prepareStatement("select booked from room where roomNo=?");
						book.setString(1, roomfield.getText());
						rs=book.executeQuery();
						rs.next();
						booked=rs.getInt("booked")+1;
						rs.close();
						book=con.prepareStatement("update room set booked=? where roomNo=?");
						book.setInt(1, booked);
						book.setString(2, roomfield.getText());
						book.executeUpdate();
						book.close();
						populateName();
					}
					pst.close();
				}
				catch(Exception exp)
				{
					exp.printStackTrace();
				}
			}
		});
	}
	
	public Room_Status() {
		createLayout();
		setActions();
		stack.getStylesheets().add(getClass().getResource("Room_Status.css").toExternalForm());
	}
}