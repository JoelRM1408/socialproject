package com.example.demo.DTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter 
@Setter
public class LoginDTO {
	private int idpersona;
	private String nombres;
	private String apellidos;
}
