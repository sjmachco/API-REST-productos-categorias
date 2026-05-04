package com.example.JPARelaciones.EjJPARelaciones.dto.producto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

//@Schema(name = "ProductoCreateDto", description = "DTO para crear producto")
public class ProductoCreateDto {

	//private Long id;
	@NotBlank(message = "Debe ingresar un nombre.")
	private String nombre;
	
	@NotNull(message = "No se permite valor nulo.")
	@Positive(message = "Debe ser un valor positivo.")
	private BigDecimal precio;
	
	@NotNull(message = "No se permite valor nulo.")
	private Long categoriaId;
	
	public ProductoCreateDto() {
		
	}
	
	public ProductoCreateDto(String nombre, BigDecimal precio, Long categoriaId) {
		//this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.categoriaId = categoriaId;
	}

	/*public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}*/

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
