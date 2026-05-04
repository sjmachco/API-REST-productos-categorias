package com.example.JPARelaciones.EjJPARelaciones.dto.producto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public class ProductoUpdateDto {
	
	@NotBlank(message = "Debe ingresar un nombre.")
	private String nombre;
	
	@NotNull(message = "No se permite valor nulo.")
	@Positive(message = "Debe ser un valor positivo.")
	private BigDecimal precio;
	
	@NotNull(message = "No se permite valor nulo.")
	private Long categoriaId;

	public ProductoUpdateDto() {
		
	}
	
	public ProductoUpdateDto(String nombre, BigDecimal precio, Long categoriaId) {
		this.nombre = nombre;
		this.precio = precio;
		this.categoriaId = categoriaId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public Long getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}
}
