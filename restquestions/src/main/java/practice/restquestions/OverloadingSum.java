package practice.restquestions;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("sumService")
public class OverloadingSum {
	@GET
    @Path("sumTwoIntegers/{a}/{b}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response sum(
            @PathParam("a") int num1,
            @PathParam("b") int num2) {
        int result = sumTwoNumbers(num1, num2);
        return Response.ok("The sum of two integers is: " + result).build();
    }

    @GET
    @Path("sumTwoFloats/{a}/{b}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response sum(
    		@PathParam("a") float num1,
    		@PathParam("b") float num2) {
        float result = sumTwoNumbers(num1, num2);
        return Response.ok("The sum of two floats is: " + result).build();
    }

    @GET
    @Path("sumIntAndFloat/{a}/{b}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response sum(
    		@PathParam("a") int num1,
    		@PathParam("b") float num2) {
        float result = sumTwoNumbers(num1, num2);
        return Response.ok("The sum of one integer and one float is: " + result).build();
    }

    // Overloaded methods for summing two numbers
    private int sumTwoNumbers(int num1, int num2) {
        return num1 + num2;
    }

    private float sumTwoNumbers(float num1, float num2) {
        return num1 + num2;
    }

    private float sumTwoNumbers(int num1, float num2) {
        return num1 + num2;
    }
}
