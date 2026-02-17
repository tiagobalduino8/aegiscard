package com.aegiscard.interfaces.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aegiscard.application.command.InsertCardCommand;
import com.aegiscard.application.command.InsertCardCommandHandler;
import com.aegiscard.application.query.FindCardQuery;
import com.aegiscard.application.query.FindCardQueryHandler;
import com.aegiscard.interfaces.dto.CardRequest;
import com.aegiscard.interfaces.dto.CardResponse;

@RestController
@RequestMapping("/cards")
public class CardController {
	private final InsertCardCommandHandler insertHandler;
	private final FindCardQueryHandler findHandler;

	public CardController(InsertCardCommandHandler insertHandler, FindCardQueryHandler findHandler) {
		this.insertHandler = insertHandler;
		this.findHandler = findHandler;
	}

	@PostMapping
	public ResponseEntity<CardResponse> insert(@RequestBody CardRequest request) {
		UUID id = insertHandler.handle(new InsertCardCommand(request.number()));
		return ResponseEntity.ok(new CardResponse(id));
	}

	@GetMapping("/{number}")
	public ResponseEntity<CardResponse> find(@PathVariable("number") String number) {
		Optional<UUID> id = findHandler.handle(new FindCardQuery(number));
		return id.map(uuid -> ResponseEntity.ok(new CardResponse(uuid))).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping("/upload")
	public ResponseEntity<List<CardResponse>> upload(@RequestParam("file") MultipartFile file) {
		List<CardResponse> responses = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
			String line;
			while ((line = reader.readLine()) != null) {
				UUID id = insertHandler.handle(new InsertCardCommand(line.trim()));
				responses.add(new CardResponse(id));
			}
		} catch (IOException e) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(responses);
	}

}
