package practice.restquestions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
    
    @GET
    @Path("students")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getStudents() {
    	Students[] students = StudentReader.readStudentsFromFile("C:\\Users\\user\\Documents\\Students.txt");
    	return Response.ok(Arrays.toString(students)).build();
    }
    
    @GET
    @Path("writeToFile")
    @Produces(MediaType.TEXT_PLAIN)
    public int add(@QueryParam("a") int a,@QueryParam("b") int b) {
    	int sum = a + b;
    	writeToFile(a,b,sum);
    	return sum;
    }

	private void writeToFile(int a, int b, int sum) {
		String fileName = "C:\\Users\\user\\Downloads\\addition.txt";
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))){
			writer.write("Input" + a + "+" + b + "=" + sum + "\n" );
			writer.write("Output" + sum + "\n\n");
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@GET
	@Path("copyfile")
	@Produces(MediaType.TEXT_PLAIN)
	public static void copyFile(@QueryParam("source") String sourceFilePath, @QueryParam("dest")String destinationFilePath) {
        java.nio.file.Path sourcePath = Paths.get(sourceFilePath);
        java.nio.file.Path destinationPath = Paths.get(destinationFilePath);

        try {
            Files.copy(sourcePath, destinationPath);
            System.out.println("File copied successfully.");
        } catch (IOException e) {
            System.err.println("Failed to copy file: " + e.getMessage());
        }
    }
    
    
    
    
    
}
