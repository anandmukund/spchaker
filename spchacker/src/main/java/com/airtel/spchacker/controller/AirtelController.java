package com.airtel.spchacker.controller;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.airtel.spchacker.dto.Response;
import com.airtel.spchacker.dto.UserDetails;
import com.airtel.spchacker.service.SpellCheck;

@RestController
@RequestMapping("/check")
public class AirtelController {

	@Autowired
	SpellCheck spellCheck;

	 @GetMapping("/word")
	public Response checkSpell(@RequestParam String[] inputList) throws Exception{

		return spellCheck.run(Arrays.asList(inputList));
	}

	 @GetMapping("/sentense")
	public String sentenseSpell(@RequestParam String inputList) throws Exception{

		return spellCheck.run(inputList);
	}

	 @PostMapping("/createuser")
		public Response  createUser(@RequestBody UserDetails userDetails , MultipartFile document) throws Exception{
		 
		 buildDocument(userDetails , document);
		 
		 Response resp = new Response();
		 
		 return resp;
		 
		 
	 }
	 
	private void  buildDocument(UserDetails userDetails , MultipartFile document){
		 
		if(!document.isEmpty()) {
			try {
				userDetails.setDocumentProff(document.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	 }
}
