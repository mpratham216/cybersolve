package practice.restquestions;
import java.util.List;
import java.util.ArrayList;

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

@Path("string")
public class StringManipulation {
 	@GET
    @Path("wordcount")
    @Produces(MediaType.TEXT_PLAIN)
    public Response countWords(@QueryParam("text") String text) {
        if (text == null || text.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("Text parameter is required.")
                           .build();
        }
        
        String[] words = text.trim().split("\\s+");
        int wordCount = words.length;
        
        return Response.ok(String.valueOf(wordCount)).build();
    }

    @GET
    @Path("vowelcount")
    @Produces(MediaType.TEXT_PLAIN)
    public Response countVowels(@QueryParam("text") String text) {
        if (text == null || text.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("Text parameter is required.")
                           .build();
        }
        
        int vowelCount = 0;
        text = text.toLowerCase(); // Convert to lowercase to simplify vowel check
        
        for (char c : text.toCharArray()) {
            if ("aeiou".indexOf(c) != -1) {
                vowelCount++;
            }
        }
        
        return Response.ok(String.valueOf(vowelCount)).build();
    }

    @GET
    @Path("replacevowels")
    @Produces(MediaType.TEXT_PLAIN)
    public Response replaceVowelsWithUpperCase(@QueryParam("text") String text) {
        if (text == null || text.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("Text parameter is required.")
                           .build();
        }
        
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if ("aeiouAEIOU".indexOf(c) != -1) {
                result.append(Character.toUpperCase(c));
            } else {
                result.append(c);
            }
        }
        
        return Response.ok(result.toString()).build();
    }

    
    
    @GET
    @Path("vowelindexes")
    @Produces(MediaType.TEXT_PLAIN)
    public Response findVowelIndexes(@QueryParam("text") String text) {
        if (text == null || text.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("Text parameter is required.")
                           .build();
        }
        
        List<Integer> indexes = new ArrayList<>();
        text = text.toLowerCase(); // Convert to lowercase to simplify vowel check
        
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if ("aeiou".indexOf(c) != -1) {
                indexes.add(i);
            }
        }
        
        return Response.ok(indexes.toString()).build();
    }

    @GET
    @Path("loadwords")
    @Produces(MediaType.TEXT_PLAIN)
    public Response loadWordsIntoArray(@QueryParam("text") String text) {
        if (text == null || text.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("Text parameter is required.")
                           .build();
        }
        
        String[] words = text.trim().split("\\s+");
         	
        // Join array elements into a comma-separated string
        String result = String.join(", ", words);
        
        return Response.ok(result).build();
    }
    
    @GET
    @Path("arrayManipulation/{a}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response stringToArray(@PathParam("a") String number) {
    	StringBuilder output = new StringBuilder();
    	
    	for(int i = 0; i<number.length(); i++) {
    		output.append(number.charAt(i));
    		if (i != number.length() - 1) {
                output.append(",");
            }
    	}
    	
    	return Response.ok(output.toString()).build();
    }
    
    
}
