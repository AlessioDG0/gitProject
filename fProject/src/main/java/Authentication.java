import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
			return Response.status(Response.Status.FORBIDDEN).build();
		}
	}

	@POST
	@Path("/login/post")
	public Response login(@QueryParam("name") String name, @QueryParam("pw") String pw) {
		System.out.println("Data: " + name + " " + pw);
		
		return Response
				.status(Response.Status.OK)
				.type(MediaType.TEXT_PLAIN)
				.entity("Alright, you can get in")
				.build();
	}
}