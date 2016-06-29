package com.solarvillage.permit;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.solarvillage.neworderpermitting.data_model.PermitRequest;
import com.solarvillage.neworderpermitting.data_model.PermitResponse;

@Path("/structural")
public class StructuralPermitService {

	private static Map<Integer, PermitResponse> map = new HashMap<Integer, PermitResponse>();

	public StructuralPermitService() {
	}

	@POST
	@Path("/request")
	public int request(PermitRequest request) {
		System.out
				.println("[REST Structural Permit] Requesting Structural Permit for: "
						+ request);
		PermitResponse response = new PermitResponse(request);
		response.setApproved(Math.random() < 0.8);
		map.put(response.getId(), response);
		return response.getId();
	}

	@GET
	@Path("/query/{id}")
	@Produces("application/json")
	public PermitResponse query(@PathParam("id") int id) {
		PermitResponse resp = map.get(id);
		System.out.println("[REST Structural Permit] Query for: " + resp);
		return resp;
	}

	@GET
	@Path("/cancel/{id}")
	public boolean cancel(@PathParam("id") int id) {
		boolean b = false;
		PermitResponse resp = map.get(id);
		if (resp != null) {
			map.remove(id);
			b = true;
		}
		System.out.println("[REST Structural Permit] Cancel for id: " + id
				+ " - result: " + b);
		return b;
	}

}
