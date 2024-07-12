package practice.restquestions;

import java.util.Arrays;

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

@Path("array")
public class ArrayManipulation {
	
	private static int[] arr = new int[10];
	
	@POST
	@Path("load")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response loadArray(@FormParam("array") String inputArray) {
		String[] strArr = inputArray.split(",");
		if(strArr.length > arr.length ) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		for(int i =0 ; i< strArr.length; i++) {
			arr[i] = Integer.parseInt(strArr[i].trim());
		}
		return Response.ok("Array loaded successfully").build();
	}
	
	@GET
	@Path("read")
	@Produces(MediaType.TEXT_PLAIN)
	public Response readArray() {
		String result = Arrays.toString(arr);
		return Response.ok(result).build();	
	}
	
	//http://localhost:8086/restquestions/webapi/array/lastchars/typo,in,the,world
	@GET
	@Path("lastchars/{strings}")
	@Produces(MediaType.TEXT_PLAIN)
    public Response getLastChars(@PathParam("strings") String stringParam) {
        if (stringParam == null || stringParam.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("Array of strings parameter is required.")
                           .build();
        }
        
        String[] strings = stringParam.split(",");
        StringBuilder result = new StringBuilder();
        
        for (String s : strings) {
            if (s != null && !s.isEmpty()) {
                result.append(s.charAt(s.length() - 1));
            }
        }

        return Response.ok(result.toString()).build();
    }
            
	@GET
	@Path("arrsize/{size}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getArray(@PathParam("size") int size) {
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = (i + 1) * 10;
        }
        return Response.ok(Arrays.toString(result)).build();
    }
}
