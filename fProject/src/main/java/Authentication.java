import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

@Path("/auth")
public class Authentication {
	
	@GET
	@Path("/hello")
	@Produces(MediaType.TEXT_PLAIN)
	public Response answer() {
		return Response
				.status(Response.Status.OK)
				.type(MediaType.TEXT_PLAIN)
				.entity("HELLO")
				.build();
	}

	@GET
	@Path("/hello/{name}/{surname}")
	public Response answerName(@PathParam("name") String name, @PathParam("surname") String surname) {

		if (name.equals("alessio") && surname.equals("dg")) {
			return Response
					.status(Response.Status.OK)
					.type(MediaType.TEXT_PLAIN)
					.entity(String.format("HELLO %s %s", name, surname))
					.build();
		} else {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}
	
	@GET
	@Path("/hellobis")
	public Response answerName2(@QueryParam("name") String name, @QueryParam("surname") String surname) {

		if (name.equals("Ale") && surname.equals("DG")) {
			return Response
					.status(Response.Status.OK)
					.type(MediaType.TEXT_PLAIN)
					.entity(String.format("HELLO %s %s", name, surname))
					.build();
		} else {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}

	@POST
	@Path("/login/post")
	public Response login(@Context HttpServletRequest request) {
		
		String body = getBody(request);
		JSONObject jsonObj = new JSONObject(body);
		String name = jsonObj.getString("name");
		String pw = jsonObj.getString("pw");
		
		System.out.println("Data: \"" + name + "\" and \"" + pw + "\"");
		if (name.equals("Ale") && pw.equals("DG")) {
			return Response
					.status(Response.Status.OK)
					.type(MediaType.TEXT_PLAIN)
					.entity("Alright, you can get in")
					.build();
		} else {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}
	

	
	private String getBody(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = request.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line).append('\n');
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(sb.toString());
		
		return sb.toString();
	}
}