package com.example;

import java.sql.SQLException;
import java.util.List;

import com.example.model.UserDTO;
import com.example.model.UsersDAO;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * Jakarta RESTのリソースクラスです。
 * 
 * @Path はこのクラス全体が扱うURLのパスで、
 * @ApplicationPath からの相対パスとなります。
 * パスの先頭の/と末尾の/はあってもなくても同じです。
 */
@RequestScoped
@NoArgsConstructor(force = true)
@RequiredArgsConstructor(onConstructor_ = @Inject)
@Path("/")
public class MyResources {
	private final UsersDAO usersDAO;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserDTO> getUsers() throws SQLException {
		return usersDAO.getAll();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{name}")
	public UserDTO getUser(@PathParam("name") String name) throws SQLException {
		var user = usersDAO.get(name);
		user.setPassword("");
		return user;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void postUser(UserDTO user) throws SQLException {
		usersDAO.create(user);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{name}")
	public void putUser(@PathParam("name") String name, UserDTO user) throws SQLException {
		usersDAO.update(user);
	}
	
	@DELETE
	@Path("{name}")
	public void deleteUser(@PathParam("name") String name) throws SQLException {
		usersDAO.delete(name);
	}
}
