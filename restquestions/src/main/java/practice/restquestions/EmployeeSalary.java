package practice.restquestions;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("practice")
public class EmployeeSalary {
	
	 private static final String AUTH_PIN = "1234";

	 public EmployeeSalary() {
	 }
	 
	 private boolean authenticate(String enteredPin) {
	      return AUTH_PIN.equals(enteredPin);
	 }
	
   // To get the employee Annual Salary. 
    @POST
    @Path("employeeSalary")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response calculateAnnualSalary(@FormParam("pin")String enteredPin, @FormParam("id")int id, @FormParam("name")String name,@FormParam("sal")double sal){
    	
    	 if (!authenticate(enteredPin)) {
             return Response.status(Response.Status.UNAUTHORIZED)
                     .entity("Unauthorized: Incorrect PIN")
                     .build();
         }
    	 
    	Emp emp = new Emp(id, name, sal);
    	double annualSalary = emp.annualSal();
    	return Response.ok("Your Annual Salary is: " + annualSalary).build();
    }
    
    @GET
    @Path("char/{a}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response charConverter(@PathParam("a") char character) {
    	int asciiVal = (int) character;
    	return Response.ok("The ascii is:" + asciiVal).build();
    }
    
}
