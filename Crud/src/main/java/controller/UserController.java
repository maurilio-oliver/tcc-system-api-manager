package controller;

import java.math.BigInteger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import service.UserService;
import userEntity.UserEntity;

@RestController
@RequestMapping("/api/user")
@Slf4j

public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping(path = "", produces = "application/json")
	public ResponseEntity criar (@Valid @RequestBody UserEntity userEntity) {
		
		try {
			userService.criar(userEntity);
			return ResponseEntity.ok(userEntity);
			
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping (path = "/idUser", produces = "application/json")
	public ResponseEntity buscarUserPorId(@PathVariable("idUser") BigInteger idUser){
		
		try {
			UserEntity userEntity = userService.buscarPorId(idUser);
			return ResponseEntity.ok(userEntity);
		}catch (Exception e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping(path = "/idUser", produces = "application/json")
	public ResponseEntity deletarPorId(@PathVariable("idUser") BigInteger idUser) {
		
		try {
			userService.delete(idUser);
			return ResponseEntity.ok(null);
		}
		catch (Exception e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping (path = "", produces = "application/json")
	public ResponseEntity editarUser(@Valid @RequestBody UserEntity userEntity) {
		
		try {
			userService.editar(userEntity);
			return ResponseEntity.ok(userEntity);
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}