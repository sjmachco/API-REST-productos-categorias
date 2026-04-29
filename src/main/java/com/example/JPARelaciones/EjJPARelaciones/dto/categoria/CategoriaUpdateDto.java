package com.example.JPARelaciones.EjJPARelaciones.dto.categoria;

public class CategoriaUpdateDto {

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
