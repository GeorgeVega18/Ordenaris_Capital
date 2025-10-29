package com.ordenariscapital.riskengine;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ordenariscapital.riskengine.repository.EmpresaRepository; // ajusta si tu repo está en otro paquete
import com.ordenariscapital.riskengine.entity.EmpresaEntity; // ajusta si tu entidad está en otro paquete

@Component
public class VerificadorDatos {

    @Autowired
    private EmpresaRepository empresaRepository;

    @PostConstruct
    public void verificarDatos() {
        System.out.println("Empresas en base:");
        empresaRepository.findAll()
                         .forEach(e -> System.out.println(e.getEmpresaId() + " - " + e.getClaveEmpresa()));
    }
}
