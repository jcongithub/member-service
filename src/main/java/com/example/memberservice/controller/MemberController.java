package com.example.memberservice.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.memberservice.model.Member;
import com.example.memberservice.model.Visit;
import com.example.memberservice.service.MemberService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

/**
 * MemberService is a core service which handles patient and doctor personal information
 * @author jianfeicheng
 *
 */
@RestController
@RequestMapping(value = "/member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	private ObjectMapper objectMapper = new ObjectMapper();
	
	
	@GetMapping("/list")
	public List<Member> list() {
		return memberService.listAll();
	}

	@GetMapping("/visits")
	public List<Visit> listVisit(@RequestParam long patientId) {
		return memberService.listVisits(patientId);
	}

    @PostMapping(path="/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Member> createCustomer(@RequestBody Member member) {
        Member customerCreated = memberService.update(member);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                                  .path("/{id}")
                                                  .buildAndExpand(customerCreated.getId())
                                                  .toUri();
        return ResponseEntity.ok(customerCreated);
    }
	
	
	
//	@PatchMapping(path = "/update/{id}", consumes = "application/json-patch+json")
//	public ResponseEntity<Member> updateMember(@PathVariable long id, @RequestBody JsonPatch patch) {
//		try {
//			Member member = memberService.findByPatientId(id).orElse(new Member());
//			member = updateMember(patch, member);
//			return ResponseEntity.ok(memberService.update(null));
//		} catch (JsonProcessingException | JsonPatchException e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//		}		
//	}

	private Member updateMember(JsonPatch patch, Member member)
			throws JsonPatchException, JsonProcessingException {
		
		JsonNode patched = patch.apply(objectMapper.convertValue(member, JsonNode.class));
		return objectMapper.treeToValue(patched, Member.class);
	}

}
