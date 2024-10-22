package com.example.demo.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

@Service
public class StockService {
    @CircuitBreaker(name = "stockService", fallbackMethod = "fallbackErrorStock")
    public String verificarStockDeAutoReposicion(Long autoId) {
        if (autoId > 8) {
            throw new RuntimeException("Simulando fallo para probar fallback");
        }
        return "El stock del auto: " + autoId + "  se encuentra disponible, no es necesario solicitar importación.";
    }
    public String fallbackErrorStock(Long autoId, Throwable t) {
        return "No se pudo verificar el inventario para el producto ID: "
                + autoId + ". verifique manualmente.";
    }
}
