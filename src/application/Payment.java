package application;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
public class Payment
{
	Rectangle2D size;
	Rectangle rect3,rect4;
	StackPane stack;
	Text title,pgname,type,food,amount,received;
	TextField tfood,ttype,tamount,treceived;
	ComboBox<String> name;
	Button save,exc;
	GridPane grid;
	Connection con;
	PreparedStatement pst,pst2;
	ResultSet rs,rs2;
	ImageView img1;
	Calendar cal;
	String str;
	int num;
	HBox hbox;
	
	public void form()
	{
		cal = Calendar.getInstance();
		str=new SimpleDateFormat("MM").format(cal.getTime());
		num=Integer.parseInt(str)-1;
		//stack---------------------------------------------
		stack=new StackPane();
		//text------------------------------------------
		title=new Text("Payment Collection");
		title.getStyleClass().add("head");
		pgname=new Text("PG Name");
		pgname.getStyleClass().add("para");
		type=new Text("Room Type");
		type.getStyleClass().add("para");
		food=new Text("Food Taken");
		food.getStyleClass().add("para");
		amount=new Text("Amount");
		amount.getStyleClass().add("para");
		received=new Text("Received");
		received.getStyleClass().add("para");
		//textField---------------------------------------
		ttype=new TextField();
		ttype.setEditable(false);
		ttype.setFont(Font.font(15));
		ttype.getStyleClass().add("tfield");
		ttype.setPrefHeight(30);
		tfood=new TextField();
		tfood.setEditable(false);
		tfood.setFont(Font.font(15));
		tfood.getStyleClass().add("tfield");
		tfood.setPrefHeight(30);
		tamount=new TextField();
		tamount.setFont(Font.font(15));
		tamount.getStyleClass().add("tfield");
		tamount.setPrefHeight(30);
		treceived=new TextField("0");
		treceived.setFont(Font.font(15));
		treceived.getStyleClass().add("tfield");
		treceived.setPrefHeight(30);
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
		//combo--------------------------------------
		name=new ComboBox<String>();
		name.setPrefSize(400, 30);
		name.getStyleClass().add("tfield");
		try
		{
			pst=con.prepareStatement("select pgname from pgs");
			rs=pst.executeQuery();
			while(rs.next())
			{
				name.getItems().add(rs.getString("pgname"));
			}
			rs.close();
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		//size----------------------------------------
		size=Screen.getPrimary().getVisualBounds();
		//rectangle------------------------------------
		rect3=new Rectangle(size.getWidth()-200, 395);
		rect3.getStyleClass().add("rect");
		rect4=new Rectangle(636,375);
		rect4.getStyleClass().add("rect");
		rect4.setTranslateX(-254);
		stack.getChildren().addAll(rect3,rect4);
		//button-------------------------------------
		save=new Button("Save");
		save.getStyleClass().add("btnn");
		exc=new Button("Export to Excel");
		exc.getStyleClass().add("btnn");
		hbox=new HBox(15,exc,save);
		//gridPane------------------------------------
		GridPane.setConstraints(title, 0, 0, 2, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(5,10,35,30));
		GridPane.setConstraints(pgname, 0, 1, 1, 1, HPos.RIGHT, VPos.CENTER, null, null, new Insets(20,20,0,10));
		GridPane.setConstraints(name, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(20,20,0,10));
		GridPane.setConstraints(type, 0, 2, 1, 1, HPos.RIGHT, VPos.CENTER, null, null, new Insets(20,20,0,10));
		GridPane.setConstraints(ttype, 1, 2, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(20,20,0,10));
		GridPane.setConstraints(food, 0, 3, 1, 1, HPos.RIGHT, VPos.CENTER, null, null, new Insets(20,20,0,10));
		GridPane.setConstraints(tfood, 1, 3, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(20,20,0,10));
		GridPane.setConstraints(amount, 0, 4, 1, 1, HPos.RIGHT, VPos.CENTER, null, null, new Insets(20,20,0,10));
		GridPane.setConstraints(tamount, 1, 4, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(20,20,0,10));
		GridPane.setConstraints(received, 0, 5, 1, 1, HPos.RIGHT, VPos.CENTER, null, null, new Insets(20,20,0,10));
		GridPane.setConstraints(treceived, 1, 5, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(20,20,0,10));
		GridPane.setConstraints(hbox, 1, 6, 1, 1, HPos.RIGHT, VPos.CENTER, null, null, new Insets(20,0,0,160));
		grid=new GridPane();
		grid.setVgap(5);
		grid.setHgap(5);
		//grid.setGridLinesVisible(true);
		grid.getChildren().addAll(title, pgname, name, type, ttype, food, tfood, amount, tamount,received,treceived,hbox);
		stack.getChildren().add(grid);
		grid.setAlignment(Pos.CENTER);
		grid.setTranslateX(-230);
		grid.setTranslateY(-45);
		//image-----------------------------------------
		try
		{
			img1=new ImageView(new Image(new FileInputStream("bin/application/bookmark.png")));
			stack.getChildren().add(img1);
			img1.setFitHeight(80);
			img1.setFitWidth(80);
			img1.setTranslateX(-540);
			img1.setTranslateY(-175);
		}
		catch(IOException exp)
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
	
	public Payment()
	{
		form();
		name.getSelectionModel().selectedItemProperty().addListener((property,oldValue,newValue)->{
			try
			{
				pst=con.prepareStatement("select food, rtype from pgs where pgname=?");
				pst.setString(1, name.getSelectionModel().getSelectedItem());
				rs=pst.executeQuery();
				rs.next();
				if(rs.getString("rtype").equals("AC"))
					ttype.setText("AC");
				else
					ttype.setText("Non-AC");
				tfood.setText(rs.getString("food"));
				rs.close();
				num++;
				pst=con.prepareStatement("select amount,received from payment where pgname=? and month(curDate)=?");
				pst.setString(1, name.getSelectionModel().getSelectedItem());
				pst.setString(2, ""+num);
				rs=pst.executeQuery();
				if(rs.next())
				{
					tamount.setText(rs.getString("amount"));
					treceived.setText(rs.getString("received"));
				}
				else
				{
					tamount.setText("0");
					treceived.setText("0");
				}
				num--;
				rs.close();
			}
			catch(Exception exp)
			{
				exp.printStackTrace();
			}
		});
		save.setOnAction(e->{
			if(name.getSelectionModel().isEmpty()||tamount.getText().equals(""))
				showMsg("Please, Fill the Required Fields");
			else
			{
				try
				{
					PreparedStatement pst1;
					pst=con.prepareStatement("select pgname, amount, received from payment where pgname=? and month(curDate)=?");
					pst.setString(1, name.getSelectionModel().getSelectedItem());
					pst.setString(2, ""+num);
					rs=pst.executeQuery();
					if(rs.next())
					{
						pst1=con.prepareStatement("update payment set amount=?, received=?, status=? where pgname=?");
						pst1.setString(1, tamount.getText());
						pst1.setString(2, Integer.toString(Integer.parseInt(rs.getString("received"))+Integer.parseInt(treceived.getText())));
						if(Integer.parseInt(rs.getString("received"))+Integer.parseInt(treceived.getText())-Integer.parseInt(tamount.getText())==0)
							pst1.setInt(3, 1);
						else
							pst1.setInt(3, 0);
						pst1.setString(4, rs.getString("pgname"));
						pst1.executeUpdate();
						pst1.close();
						rs.close();
						showMsg2("Successfully Updated");
					}
					else
					{
						String datestr = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
						DateFormat format=new SimpleDateFormat("dd/MM/yyyy");
						java.util.Date date=format.parse(datestr);
						Date sqlDate=new java.sql.Date(date.getTime());
						pst1=con.prepareStatement("insert into payment values(?,?,?,?,?)");
						pst1.setString(1, name.getSelectionModel().getSelectedItem());
						pst1.setString(2, tamount.getText());
						pst1.setString(3, treceived.getText());
						if(Integer.parseInt(treceived.getText())-Integer.parseInt(tamount.getText())==0)
							pst1.setInt(4, 1);
						else
							pst1.setInt(4, 0);
						pst1.setDate(5, sqlDate);
						pst1.executeUpdate();
						pst1.close();
						showMsg2("Successfully Saved");
					}
					tamount.clear();
					treceived.clear();
					name.getSelectionModel().clearSelection();
					tfood.clear();
					ttype.clear();
				}
				catch(Exception exp)
				{
					exp.printStackTrace();
				}
			}
		});
		exc.setOnAction(e->{
			try
			{
				PrintWriter pw=new PrintWriter(new File("payment.csv"));
				StringBuilder sb=new StringBuilder();
				sb.append("Name");
				sb.append(",");
				sb.append("Room No.");
				sb.append(",");
				sb.append("Fees Paid");
				sb.append("\n");
				Calendar cal = Calendar.getInstance();
				String str=new SimpleDateFormat("MM").format(cal.getTime());
				int num=Integer.parseInt(str);
				try
				{
					pst=con.prepareStatement("select pgname,rno from pgs");
					rs=pst.executeQuery();
					while(rs.next())
					{
						pst2=con.prepareStatement("select pgname from payment where pgname=? and month(curdate)=? and status=1");
						pst2.setString(1, rs.getString("pgname"));
						pst2.setString(2, ""+num);
						rs2=pst2.executeQuery();
						sb.append(rs.getString("pgname"));
						sb.append(",");
						sb.append(rs.getString("rno"));
						sb.append(",");
						if(rs2.next())
							sb.append("Yes");
						else
							sb.append("No");
						sb.append("\n");
						rs2.close();
					}
					rs.close();
				}
				catch (SQLException e1)
				{
					e1.printStackTrace();
				}
				pw.write(sb.toString());
		        pw.close();
		        showMsg2("Successfully Exported");
			}
			catch (FileNotFoundException e1)
			{
				e1.printStackTrace();
			}
			
		});
		stack.getStylesheets().add(getClass().getResource("Payment.css").toExternalForm());
	}
}