package application;
public class Pgs
{
	String name,city,type,room,bill;
	Pgs(){
	}
	public Pgs(String name, String city, String type, String room, String bill)
	{
		this.name = name;
		this.city = city;
		this.type = type;
		this.room = room;
		this.bill = bill;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getBill() {
		return bill;
	}
	public void setBill(String bill) {
		this.bill = bill;
	}
}