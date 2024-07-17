package practice.restquestions;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("supersalary")
public class FullTimeEmployeeSalary extends Emp {
	protected String company = "Cybersolve";
	
	public FullTimeEmployeeSalary(@QueryParam("id") int id, 
            @QueryParam("name") String name, 
            @QueryParam("sal") double sal) {
		super(id,name,sal);
	}
	
	@GET
	@Path("anual")
	@Produces(MediaType.TEXT_PLAIN)
	public double annualSalaryEmp(
			@QueryParam("id") int id,
			@QueryParam("name")String name,
			@QueryParam("sal") double sal) {
		//updateEmpDetails(id,name,sal);
		return annualSal();
	} 
	
	public String accessBothVariables() {
        // Accessing the shared variable from the superclass using 'super'
        String superValue = super.company;
        // Accessing the shared variable from the subclass
        String subValue = this.company;
        return "SuperClass variable: " + superValue + ", SubClass variable: " + subValue;
    }
	
	//updating the details
//    private void updateEmpDetails(int id, String name, double sal) {
//        super.id = id;
//        super.name = name;
//        super.sal = sal;
//    }
	
	
	@GET
    @Path("/access")
    @Produces(MediaType.TEXT_PLAIN)
    public String getVariables() {
        return accessBothVariables();
    }
}
