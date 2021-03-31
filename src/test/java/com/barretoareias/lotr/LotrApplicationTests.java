package com.barretoareias.lotr;

import java.time.LocalDate;
import java.util.ArrayList;

import com.barretoareias.lotr.dto.CharDTO;
import com.barretoareias.lotr.entity.Culture;
import com.barretoareias.lotr.repository.CharRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@SpringBootTest
@AutoConfigureWebTestClient
class LotrApplicationTests {

	@Autowired
	WebTestClient webTestClient;

	@Autowired
	CharRepository charRepository;

	@Test
	void getOneCharById() {
		webTestClient.get().uri("/api/v1/char/id/{id}", 1)
		.exchange()
		.expectStatus()
		.isOk()
		.expectBody();
	}

	@Test 
	void getOneCharByName(){
		webTestClient.get().uri("/api/v1/char/name/{name}", "Bilbo Baggings")
		.exchange()
		.expectStatus()
		.isOk()
		.expectBody();
	}

	@Test
	void deleteCharById() {
		webTestClient.delete().uri("/api/v1/char/id/{id}", 1)
		.exchange()
		.expectStatus()
		.isNoContent()
		.expectBody();
	}

	@Test 
	void createChars(){
		var bilbo = CharDTO.builder()
				.id(null)
				.name("Bilbo Baggings")
				.birth(LocalDate.parse("2890-09-22"))
				.title("Barrel Rider")
				.culture(new Culture(1l,""))
				.build();

		var frodo = CharDTO.builder()
			.id(null)
			.name("Frodo Baggings")
			.birth(LocalDate.parse("2968-09-22"))
			.title("Bearer of the One Ring")
			.culture(new Culture(1l,""))
			.build();

		var gandalf = CharDTO.builder()
			.id(null)
			.name("Gandalf")
			.birth(LocalDate.parse("0001-01-01"))
			.title("The White")
			.culture(new Culture(1l,""))
			.build();

		var aragorn = CharDTO.builder()
			.id(null)
			.name("Aragorn")
			.birth(LocalDate.parse("2933-07-08"))
			.title("The King")
			.culture(new Culture(1l,""))
			.build();
		
		var charList = new ArrayList<CharDTO>();

		charList.add(bilbo);
		charList.add(gandalf);
		charList.add(frodo);
		charList.add(aragorn);

		charList.forEach(dto -> {
			webTestClient.post().uri("/api/v1/char")
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue(dto))
				.exchange()
				.expectStatus().isCreated();
		});

	}

}
