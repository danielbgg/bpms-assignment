package com.solarvillage.permit;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.solarvillage.neworderpermitting.data_model.PermitRequest;

@Path("/structuralByPost")
public class StructuralPermitServiceByPost {

	@POST
	@Path("/requestPermit")
	@Consumes("application/json")
	@Produces("application/json")
	public PermitRequest requestPermit(PermitRequest request) {
		request.setApproved(Math.random() < 0.8);
		System.out
				.println("[REST Web Service] Structural Permit Service Requested for "
						+ request + " - result: " + request.isApproved());
		return request;
	}

}
