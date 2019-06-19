package com.webservice.springbootsoapservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.petclinic.xml.pets.GetPetsByOwnerRequest;
import com.petclinic.xml.pets.GetPetsByOwnerResponse;

@Endpoint
public class PetsEndpoint {
	private static final String NAMESPACE_URI = "http://www.petclinic.com/xml/pets";

	private PetsRepository petsRepository;

	@Autowired
	public PetsEndpoint(PetsRepository petsRepository) {
		this.petsRepository = petsRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetPetsByOwnerRequest")
	@ResponsePayload
	public GetPetsByOwnerResponse getPetsByOwner(@RequestPayload GetPetsByOwnerRequest request) {
		GetPetsByOwnerResponse response = new GetPetsByOwnerResponse();
		response.getPets().addAll(petsRepository.findPetsByOwner(request.getOwnerId()));

		return response;
	}
}