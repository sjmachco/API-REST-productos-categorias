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

import com.example.JPARelaciones.EjJPARelaciones.dto.categoria.CategoriaCreateDto;
import com.example.JPARelaciones.EjJPARelaciones.dto.categoria.CategoriaResponseDto;
import com.example.JPARelaciones.EjJPARelaciones.dto.categoria.CategoriaUpdateDto;
import com.example.JPARelaciones.EjJPARelaciones.service.CategoriaService;
import com.example.JPARelaciones.EjJPARelaciones.util.ApiResponse;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/categoria")

public class CategoriaController {
	
	private final CategoriaService categoriaService;
	
	public CategoriaController(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}
	
	@GetMapping("/lista")
	public ResponseEntity<ApiResponse<List<CategoriaResponseDto>>> getListCategoria(){
		return ResponseEntity.ok(ApiResponse.ok("Categorias obtenidas correctamente", 
				categoriaService.getListCategoria()));
	}
	
	@GetMapping("/obtenerCategoria/{id}")
	public ResponseEntity<ApiResponse<CategoriaResponseDto>> getCategoriaById(@Validated @Min(1) @PathVariable("id") Long id) {
		return ResponseEntity.ok(ApiResponse.ok("Categoria encontrada.", 
				categoriaService.getCategoriaId(id)));
	}
	
	@PostMapping("/crear")
	public ResponseEntity<ApiResponse<CategoriaResponseDto>> createCategoria(@Valid @RequestBody CategoriaCreateDto dto) {
		CategoriaResponseDto creado = categoriaService.createCategoria(dto);	
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse
				.ok("Categoria creada correctamente.", creado));		
	}
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<ApiResponse<CategoriaResponseDto>> updateCategoria(@Validated @Min(1) @PathVariable("id") Long id, 
			@Valid @RequestBody CategoriaUpdateDto dto) {
		return ResponseEntity.ok(ApiResponse.ok("Categoria actualizada correctamente.", 
				categoriaService.updateCategoria(id, dto)));
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<ApiResponse<CategoriaResponseDto>> deleteCategoria(@Validated @Min(1) @PathVariable("id") Long id) {
		categoriaService.deleteCategoria(id);
		return ResponseEntity.ok(ApiResponse.ok("Categoria eliminada correctamente.", null));
	}
}





