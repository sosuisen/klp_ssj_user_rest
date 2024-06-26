package com.example;

import java.sql.SQLException;
import java.util.logging.Level;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.java.Log;

@Provider
@Log
public class SQLExceptionMapper implements ExceptionMapper<SQLException> {
	@Override
	public Response toResponse(SQLException exception) {
		// ログにエラー内容を出力
		log.log(Level.SEVERE, "SQL Exception occurred", exception);
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity("500 Internal server error occurred in API server.")
				.build();
	}
}
