package com.example.JPARelaciones.EjJPARelaciones.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.JPARelaciones.EjJPARelaciones.dto.producto.ProductoCreateDto;
import com.example.JPARelaciones.EjJPARelaciones.dto.producto.ProductoResponseDto;
import com.example.JPARelaciones.EjJPARelaciones.dto.producto.ProductoUpdateDto;
import com.example.JPARelaciones.EjJPARelaciones.service.ProductoService;
import com.example.JPARelaciones.EjJPARelaciones.util.ApiResponse;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/producto")

public class ProductoController {
	
	private final ProductoService productoService;
	
	public ProductoController(ProductoService productoService) {
		this.productoService = productoService;
	}
	
	@GetMapping("/lista")
	public ResponseEntity<ApiResponse<List<ProductoResponseDto>>> getListProducto(){
		return ResponseEntity.ok(ApiResponse.ok("Productos obtenidos correctamente.", 
				productoService.getListProducto()));
	}
	
	@GetMapping("/obtenerProducto/{id}")
	public ResponseEntity<ApiResponse<ProductoResponseDto>> getProductoByid(@Validated @Min(1) @PathVariable("id") Long id) {
		return ResponseEntity.ok(ApiResponse.ok("Producto encontrado", 
				productoService.getProductoById(id)));
	}
	
	@PostMapping("/crear")
	public ResponseEntity<ApiResponse<ProductoResponseDto>> createProducto(@Valid @RequestBody ProductoCreateDto dto) {
		ProductoResponseDto creado = productoService.createProducto(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse
				.ok("Producto creado correctamente.", creado));
	}
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<ApiResponse<ProductoResponseDto>> updateProducto(@Validated @Min(1) @PathVariable("id") Long id, 
			@Valid @RequestBody ProductoUpdateDto dto) {
		return ResponseEntity.ok(ApiResponse.ok("Producto actualizado correctamente.", 
				productoService.updateProducto(id, dto)));
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<ApiResponse<ProductoResponseDto>> deleteProducto(@Validated @Min(1) @PathVariable("id") Long id) {
		productoService.deleteProducto(id);
		return ResponseEntity.ok(ApiResponse.ok("Producto eliminado correctamente.", null));
	}
}










