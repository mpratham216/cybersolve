package practice.restquestions;

public class Emp {
	private int id;
	private  String name;
	private double sal;
	
	public Emp(int id, String name, double sal) { 
		this.id = id;
		this.name = name;
		this.sal = sal;
	}
	
	public double annualSal() {	
		return this.sal * 12;
	}
	
	
}
