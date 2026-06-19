package com.example.JPARelaciones.EjJPARelaciones.filter;

import java.io.IOException;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class VisitaFilter implements Filter {
	
	private static final Logger log = LoggerFactory.getLogger(VisitaFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getRequestURI();
        
        if (!uri.equals("/health")) {
            log.info("Visita: {} {} | IP: {} | Hora: {}",
                req.getMethod(),
                uri,
                req.getRemoteAddr(),
                LocalDateTime.now());
        }
        
        chain.doFilter(request, response);

	}

}
