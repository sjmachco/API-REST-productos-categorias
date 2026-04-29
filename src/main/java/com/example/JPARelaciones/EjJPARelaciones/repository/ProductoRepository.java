package com.example.JPARelaciones.EjJPARelaciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.JPARelaciones.EjJPARelaciones.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{
    @Modifying
    @Transactional
	@Query("DELETE FROM Producto p WHERE p.categoria.id = :categoriaId")
    void deleteByCategoriaId(@Param("categoriaId") Long categoriaId);
}
