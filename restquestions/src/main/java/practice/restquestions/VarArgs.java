package practice.restquestions;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("varargs")
public class VarArgs {
	
	private int[] createArrayFromVarargs(String... numbers) {
        int[] intArr = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            intArr[i] = Integer.parseInt(numbers[i].trim());
        }
        return intArr;
    }
	
	
	
	@GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getArray(@QueryParam("values") String values) {
        if (values == null || values.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("No values provided").build();
        }

        String[] strArr = values.split(",");
        int[] intArr = createArrayFromVarargs(strArr);
        return Response.ok(Arrays.toString(intArr)).build();
    }
	
	@GET
	@Path("sum")
    @Produces(MediaType.TEXT_PLAIN)
	//Jersey does not inherently support Varargs.
	//public int getSum(@QueryParam("numbers") Integer... numbers)
    public int getSum(@QueryParam("numbers") List<Integer>numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("No Values Provided");
        }
        
        Integer[] numbersArray = numbers.toArray(new Integer[0]);

        return sum(numbersArray);
    }
	
	private int sum(Integer... num) {
		int sum = 0;
		for(int nums: num) {
			sum+=nums;
		}
		return sum;
	}
	
	@GET
    @Path("nextinteger")
    public Integer getNextInteger(@QueryParam("decimal") Double decimal) {
        if (decimal == null) {
            throw new IllegalArgumentException("Decimal value is required");
        }
        return (int) Math.ceil(decimal);
    }

    @GET
    @Path("previousinteger")
    public Integer getPreviousInteger(@QueryParam("decimal") Double decimal) {
        if (decimal == null) {
            throw new IllegalArgumentException("Decimal value is required");
        }
        return (int) Math.floor(decimal);
    }

}
