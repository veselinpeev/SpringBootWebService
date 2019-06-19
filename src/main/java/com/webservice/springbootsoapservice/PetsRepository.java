package com.webservice.springbootsoapservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.petclinic.xml.pets.Pet;

@Component
public class PetsRepository {
	private static final Map<Integer, List<Pet>> pets = new HashMap<>();

	@PostConstruct
	public void initData() {
		for (int ownerId = 1; ownerId < 5; ownerId++) {
			List<Pet> ownerPets = new ArrayList<>();
			for (int i=1; i < 5; i++) {
				ownerPets.add(createPet(ownerId, "petName_" + ownerId + "_" +  i));
			}
			pets.put(ownerId, ownerPets);
		}
		
	}
	
	private Pet createPet(int ownerId, String petName) {
		Pet pet = new Pet();
		pet.setId((int) System.currentTimeMillis());
		pet.setName(petName);
		pet.setOwnerId(ownerId);
		return pet;	
	}
	
	public List<Pet> findPetsByOwner(int ownerId) {
		List<Pet> result = pets.get(ownerId);
		
		if (result == null) {
			result = new ArrayList<>();
		}
		return result;
	}

}
