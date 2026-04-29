package com.example.JPARelaciones.EjJPARelaciones.util;

import java.time.LocalDateTime;

public class ApiResponse<T> {

	private boolean success;
	private String mensaje;
	private T data;
	private LocalDateTime fechaHora;
	
	public ApiResponse(boolean success, String mensaje, T data) {
		this.success = success;
		this.mensaje = mensaje;
		this.data = data;
		this.fechaHora = LocalDateTime.now();
	}
	
	public static <T> ApiResponse<T> ok(String mensaje, T data){
		return new ApiResponse<>(true, mensaje, data);
	}
	
    public static <T> ApiResponse<T> error(String mensaje) {
        return new ApiResponse<>(false, mensaje, null);
    }

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}
}
