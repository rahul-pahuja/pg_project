package application;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
public class Pg_finder
{
	Text title,title2,name,city,type,room,bill;
	TextField tname;
	ComboBox<String> ccity,croom,cbill,cmonth,cyear;
	RadioButton ac,nac;
	RadioButton rname,rcity,rrtype,rrno,rpay;
	HBox hbox,hbox2;
	ToggleGroup group,group2;
	GridPane grid;
	Rectangle2D size;
	Rectangle rect3,rect4;
	StackPane stack;
	Connection con;
	PreparedStatement pst,pst2;
	ResultSet rs,rs2;
	ImageView img1;
	TableView<Pgs> table;
	ArrayList<String> arr1;
	
	public void form()
	{
		//stack------------------------------------------
		stack=new StackPane();
		stack.getStyleClass().add("stack");
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
		//comboBox---------------------------------------
		ccity=new ComboBox<>();
		ccity.setStyle("-fx-font-size:1.2em");
		ccity.setPrefSize(430, 30);
		ccity.setMaxWidth(350);
		ccity.getStyleClass().add("tfield");
		try
		{
			pst=con.prepareStatement("select distinct city from pgs");
			rs= pst.executeQuery();
			ArrayList<String>lst=new ArrayList<String>();
			while(rs.next())
			{
				String p=rs.getString("city");
				lst.add(p);
			}
			ccity.getItems().addAll(lst);
			rs.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		croom=new ComboBox<>();
		croom.setStyle("-fx-font-size:1.2em");
		croom.setPrefSize(430, 30);
		croom.setMaxWidth(300);
		croom.getStyleClass().add("tfield");
		try
		{
			pst=con.prepareStatement("select distinct rno from pgs");
			rs= pst.executeQuery();
			ArrayList<String>lst=new ArrayList<String>();
			while(rs.next())
			{
				String p=rs.getString("rno");
				lst.add(p);
			}
			croom.getItems().addAll(lst);
			rs.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		cmonth=new ComboBox<String>();
		cmonth.setStyle("-fx-font-size:1.2em");
		cmonth.setValue("Month");
		cmonth.getStyleClass().add("tfield");
		arr1=new ArrayList<String>();
		arr1.add("January");
		arr1.add("February");
		arr1.add("March");
		arr1.add("April");
		arr1.add("May");
		arr1.add("June");
		arr1.add("July");
		arr1.add("August");
		arr1.add("September");
		arr1.add("October");
		arr1.add("November");
		arr1.add("December");
		cmonth.getItems().addAll(arr1);
		cyear=new ComboBox<String>();
		cyear.setStyle("-fx-font-size:1.2em");
		cyear.setValue("Year");
		cyear.getStyleClass().add("tfield");
		for(Integer i=2017; i<=2025; i++)
		{
			cyear.getItems().add(i.toString());
		}
		cbill=new ComboBox<String>();
		cbill.setStyle("-fx-font-size:1.2em");
		cbill.getStyleClass().add("tfield");
		cbill.setValue("Bill Status");
		cbill.getItems().addAll("Paid","Not Paid");
		//text-------------------------------------------
		title=new Text("PG's Finder");
		title.getStyleClass().add("head");
		title2=new Text("Choose Your Filters :-");
		title2.getStyleClass().add("para");
		bill=new Text("Payments");
		bill.getStyleClass().add("para");
		name=new Text("PG's Name");
		name.getStyleClass().add("para");
		city=new Text("City");
		city.getStyleClass().add("para");
		type=new Text("Room Type");
		type.getStyleClass().add("para");
		room=new Text("Room No.");
		room.getStyleClass().add("para");
		//textField----------------------------------------
		tname=new TextField();
		tname.setFont(Font.font(15));
		tname.setMaxWidth(300);
		tname.getStyleClass().add("tfield");
		tname.setPrefHeight(30);
		//size----------------------------------------
		size=Screen.getPrimary().getVisualBounds();
		//rectangle------------------------------------
		rect3=new Rectangle(size.getWidth()-90, 316);
		rect3.getStyleClass().add("rect");
		stack.getChildren().addAll(rect3);
		rect4=new Rectangle(size.getWidth()-106, 300);
		rect4.getStyleClass().add("rect");
		stack.getChildren().add(rect4);
		//image-----------------------------------------
		try
		{
			img1=new ImageView(new Image(new FileInputStream("bin/application/bookmark.png")));
			stack.getChildren().add(img1);
			img1.setFitHeight(80);
			img1.setFitWidth(80);
			img1.setTranslateX(-600);
			img1.setTranslateY(-137);
		}
		catch(IOException exp)
		{
			exp.printStackTrace();
		}
		//radioButton----------------------------------------
		ac=new RadioButton("AC");
		ac.setStyle("-fx-font-size:1.5em");
		nac=new RadioButton("Non-AC");
		nac.setStyle("-fx-font-size:1.5em");
		//HBox---------------------------------------------
		hbox=new HBox(45);
		hbox.getChildren().addAll(ac,nac);
		//toggleGroup----------------------------------------
		group=new ToggleGroup();
		ac.setToggleGroup(group);
		nac.setToggleGroup(group);
		//radio--------------------------------------------
		rname=new RadioButton("Name");
		rname.setStyle("-fx-font-size:1.5em");
		rcity=new RadioButton("City");
		rcity.setStyle("-fx-font-size:1.5em");
		rrtype=new RadioButton("Room Type");
		rrtype.setStyle("-fx-font-size:1.5em");
		rrno=new RadioButton("Room No.");
		rrno.setStyle("-fx-font-size:1.5em");
		rpay=new RadioButton("Payments");
		rpay.setStyle("-fx-font-size:1.5em");
		//toggleGroup-------------------------------------
		group2=new ToggleGroup();
		rname.setToggleGroup(group2);
		rcity.setToggleGroup(group2);
		rrtype.setToggleGroup(group2);
		rrno.setToggleGroup(group2);
		rpay.setToggleGroup(group2);
		//Hbox---------------------------------------------
		hbox2=new HBox(10);
		hbox2.getChildren().addAll(bill,cbill,cmonth,cyear);
		//grid---------------------------------------------
		GridPane.setConstraints(title, 0, 0, 4, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(5,10,25,0));
		GridPane.setConstraints(title2, 0, 1, 3, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(20,20,0,0));
		GridPane.setConstraints(rname, 0, 2, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(20,20,0,30));
		GridPane.setConstraints(rrno, 1, 2, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(20,20,0,0));
		GridPane.setConstraints(rpay, 2, 2, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(20,20,0,0));
		GridPane.setConstraints(rcity, 0, 3, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(20,20,20,30));
		GridPane.setConstraints(rrtype, 1, 3, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(20,20,20,0));
		GridPane.setConstraints(name, 0, 4, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(20,20,0,0));
		GridPane.setConstraints(tname, 1, 4, 3, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(20,20,0,0));
		GridPane.setConstraints(city, 0, 4, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(20,20,0,20));
		GridPane.setConstraints(ccity, 1, 4, 3, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(20,20,0,-50));
		GridPane.setConstraints(type, 0, 4, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(20,20,0,0));
		GridPane.setConstraints(hbox, 1, 4, 3, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(27,20,0,15));
		GridPane.setConstraints(room, 0, 4, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(20,20,0,0));
		GridPane.setConstraints(croom, 1, 4, 3, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(20,20,0,0));
		GridPane.setConstraints(hbox2, 0, 4, 4, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(27,0,0,-25));
		grid=new GridPane();
		grid.getChildren().addAll(title,title2,rname,rrno,rpay,rcity,rrtype,
				name,type,hbox,room,city,tname,ccity,croom,hbox2);
		NShow();
		grid.setHgap(5);
		grid.setVgap(5);
		grid.setTranslateX(-330);
		grid.setTranslateY(-62);
		stack.getChildren().add(grid);
		//grid.setGridLinesVisible(true);
		grid.setAlignment(Pos.CENTER);
		Actions();
	}
	
	public void Actions()
	{
		rname.setOnAction(e->{
			NShow();
			name.setVisible(true);
			tname.setVisible(true);
		});
		rcity.setOnAction(e->{
			NShow();
			city.setVisible(true);
			ccity.setVisible(true);
		});
		rpay.setOnAction(e->{
			NShow();
			bill.setVisible(true);
			cbill.setVisible(true);
			cmonth.setVisible(true);
			cyear.setVisible(true);
		});
		rrno.setOnAction(e->{
			NShow();
			room.setVisible(true);
			croom.setVisible(true);
		});
		rrtype.setOnAction(e->{
			NShow();
			type.setVisible(true);
			ac.setVisible(true);
			nac.setVisible(true);
		});
	}
	
	public void setTable()
	{
		table=new TableView<>();
		
		TableColumn<Pgs, String>name=new TableColumn<>("PG Name");
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn<Pgs, String>city=new TableColumn<>("City");
		city.setCellValueFactory(new PropertyValueFactory<>("city"));
		
		TableColumn<Pgs, String>type=new TableColumn<>("Room Type");
		type.setCellValueFactory(new PropertyValueFactory<>("type"));
		
		TableColumn<Pgs, String>room=new TableColumn<>("Room No.");
		room.setCellValueFactory(new PropertyValueFactory<>("room"));
		
		TableColumn<Pgs, String>bill=new TableColumn<>("Bill Paid");
		bill.setCellValueFactory(new PropertyValueFactory<>("bill"));
		
		table.setFixedCellSize(40);
		table.getColumns().add(name);
		table.getColumns().add(city);
		table.getColumns().add(bill);
		table.getColumns().add(room);
		table.getColumns().add(type);
		
		stack.getChildren().add(table);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.setMaxWidth(710);
		table.setMaxHeight(300);
		table.setTranslateX(275);
	}
	
	public void comboCity()
	{
		ccity.getSelectionModel().selectedItemProperty().addListener((property,oldValue,newValue)->{
			try
			{
				ObservableList<Pgs>ary=FXCollections.observableArrayList();
				pst=con.prepareStatement("select pgname,city,rtype,rno from pgs where city=?");
				pst.setString(1, ccity.getSelectionModel().getSelectedItem());
				rs=pst.executeQuery();
				while(rs.next())
				{
					pst2=con.prepareStatement("select status from payment where pgname=?");
					pst2.setString(1, rs.getString("pgname"));
					rs2=pst2.executeQuery();
					if(rs2.next())
					{
						if(rs2.getInt("status")==1)
							ary.add(new Pgs(rs.getString("pgname"),rs.getString("city"),rs.getString("rtype"),rs.getString("rno"),"Yes"));
						else
							ary.add(new Pgs(rs.getString("pgname"),rs.getString("city"),rs.getString("rtype"),rs.getString("rno"),"No"));
					}
					else
						ary.add(new Pgs(rs.getString("pgname"),rs.getString("city"),rs.getString("rtype"),rs.getString("rno"),"No"));
					rs2.close();
				}
				rs.close();
				table.setItems(ary);
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		});
	}
	
	public void comboRoom()
	{
		croom.getSelectionModel().selectedItemProperty().addListener((property,oldValue,newValue)->{
			try
			{
				ObservableList<Pgs>ary=FXCollections.observableArrayList();
				pst=con.prepareStatement("select pgname,city,rtype,rno from pgs where rno=?");
				pst.setString(1, croom.getSelectionModel().getSelectedItem());
				rs=pst.executeQuery();
				while(rs.next())
				{
					pst2=con.prepareStatement("select status from payment where pgname=?");
					pst2.setString(1, rs.getString("pgname"));
					rs2=pst2.executeQuery();
					if(rs2.next())
					{
						if(rs2.getInt("status")==1)
							ary.add(new Pgs(rs.getString("pgname"),rs.getString("city"),rs.getString("rtype"),rs.getString("rno"),"Yes"));
						else
							ary.add(new Pgs(rs.getString("pgname"),rs.getString("city"),rs.getString("rtype"),rs.getString("rno"),"No"));
					}
					else
						ary.add(new Pgs(rs.getString("pgname"),rs.getString("city"),rs.getString("rtype"),rs.getString("rno"),"No"));
					rs2.close();
				}
				rs.close();
				table.setItems(ary);
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		});
	}
	
	public void comboYear()
	{
		cyear.getSelectionModel().selectedItemProperty().addListener((property,oldValue,newValue)->{
			try
			{
				ObservableList<Pgs>ary=FXCollections.observableArrayList();
				pst=con.prepareStatement("select pgname,city,rtype,rno from pgs");
				rs=pst.executeQuery();
				while(rs.next())
				{
					pst2=con.prepareStatement("select status from payment where pgname=? and mon=? and yr=?");
					pst2.setString(1, rs.getString("pgname"));
					pst2.setString(2, cmonth.getSelectionModel().getSelectedItem());
					pst2.setString(3, cyear.getSelectionModel().getSelectedItem());
					rs2=pst2.executeQuery();
					if(rs2.next())
						{
							if(rs2.getInt("status")==0)
							{
								ary.add(new Pgs(rs.getString("pgname"),rs.getString("city"),rs.getString("rtype"),rs.getString("rno"),"No"));
							}
							else
								ary.add(new Pgs(rs.getString("pgname"),rs.getString("city"),rs.getString("rtype"),rs.getString("rno"),"Yes"));
						}
					else
					{
						ary.add(new Pgs(rs.getString("pgname"),rs.getString("city"),rs.getString("rtype"),rs.getString("rno"),"No"));
					}
					rs2.close();
				}
				rs.close();
				table.setItems(ary);
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		});
	}
	
	public void NShow()
	{
		name.setVisible(false);
		tname.setVisible(false);
		type.setVisible(false);
		bill.setVisible(false);
		cbill.setVisible(false);
		cmonth.setVisible(false);
		cyear.setVisible(false);
		city.setVisible(false);
		ccity.setVisible(false);
		room.setVisible(false);
		ac.setVisible(false);
		nac.setVisible(false);
		room.setVisible(false);
		croom.setVisible(false);
	}
	
	public Pg_finder()
	{
		form();
		setTable();
		comboCity();
		comboRoom();
		comboYear();
		tname.setOnKeyReleased(new EventHandler<KeyEvent>(){
			public void handle(KeyEvent event)
			{
				try
				{
					ObservableList<Pgs>ary=FXCollections.observableArrayList();
					pst=con.prepareStatement("select pgname,city,rtype,rno from pgs where pgname like '%"+tname.getText()+"%'");
					rs=pst.executeQuery();
					while(rs.next())
					{
						pst2=con.prepareStatement("select status from payment where pgname=?");
						pst2.setString(1, rs.getString("pgname"));
						rs2=pst2.executeQuery();
						if(rs2.next())
						{
							if(rs2.getInt("status")==1)
								ary.add(new Pgs(rs.getString("pgname"),rs.getString("city"),rs.getString("rtype"),rs.getString("rno"),"Yes"));
							else
								ary.add(new Pgs(rs.getString("pgname"),rs.getString("city"),rs.getString("rtype"),rs.getString("rno"),"No"));
						}
						else
							ary.add(new Pgs(rs.getString("pgname"),rs.getString("city"),rs.getString("rtype"),rs.getString("rno"),"No"));
						rs2.close();
					}
					rs.close();
					table.setItems(ary);
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}	
			}
		});
		ac.setOnAction(e->doFill("AC"));
		nac.setOnAction(e->doFill("NAC"));
		stack.getStylesheets().add(getClass().getResource("Pg_finder.css").toExternalForm());
	}
	
	public void doFill(String s)
	{
		try
		{
			ObservableList<Pgs>ary=FXCollections.observableArrayList();
			pst=con.prepareStatement("select pgname,city,rtype,rno from pgs where rtype=?");
			pst.setString(1, s);
			rs=pst.executeQuery();
			while(rs.next())
			{
				pst2=con.prepareStatement("select status from payment where pgname=?");
				pst2.setString(1, rs.getString("pgname"));
				rs2=pst2.executeQuery();
				if(rs2.next())
				{
					if(rs2.getInt("status")==1)
						ary.add(new Pgs(rs.getString("pgname"),rs.getString("city"),rs.getString("rtype"),rs.getString("rno"),"Yes"));
					else
						ary.add(new Pgs(rs.getString("pgname"),rs.getString("city"),rs.getString("rtype"),rs.getString("rno"),"No"));
				}
				else
					ary.add(new Pgs(rs.getString("pgname"),rs.getString("city"),rs.getString("rtype"),rs.getString("rno"),"No"));
				rs2.close();
			}
			rs.close();
			table.setItems(ary);
		}
		catch(SQLException e1)
		{
			e1.printStackTrace();
		}
	}
}