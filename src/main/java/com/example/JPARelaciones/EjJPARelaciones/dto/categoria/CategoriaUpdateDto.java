package com.example.JPARelaciones.EjJPARelaciones.dto.categoria;

import jakarta.validation.constraints.NotBlank;

public class CategoriaUpdateDto {

	@NotBlank(message = "Debe ingresar una categoría.")
	private String descripcion;
	
	public CategoriaUpdateDto(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
