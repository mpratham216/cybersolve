package practice.restquestions;

public class Emp {
	protected int id;
	protected  String name;
	protected double sal;
	protected String company = "CyberSolve";
	
	public Emp(int id, String name, double sal) { 
		this.id = id;
		this.name = name;
		this.sal = sal;
	}
	
	public double annualSal() {	
		return this.sal * 12;
	}
	
	
}
