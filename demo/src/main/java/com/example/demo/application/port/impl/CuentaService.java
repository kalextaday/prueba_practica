package com.example.demo.application.port.impl;

import com.example.demo.application.dto.CuentaDto;
import com.example.demo.application.mapper.CuentaMapper;
import com.example.demo.application.port.out.ICuentaPersistenciaPort;
import com.example.demo.domain.entity.Cuenta;
import com.example.demo.infraestructure.repository.CuentaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class CuentaService implements ICuentaPersistenciaPort {

    private final CuentaRepository cuentaRepository;

    public CuentaService(CuentaRepository cuentaRepository) {
        
        this.cuentaRepository = cuentaRepository;
    }

    @Override
    public CuentaDto agregarCuenta(CuentaDto cuentaDto) {
        Cuenta accountRecent = CuentaMapper.INSTANCE.cuentaDtoToCuenta(cuentaDto);
        Cuenta accountSaved = this.cuentaRepository.save(accountRecent);

        return CuentaMapper.INSTANCE.cuentaToCuentaDto(accountSaved);
    }

    @Override
    public CuentaDto editarCuenta(CuentaDto cuentaDto) {
        return this.agregarCuenta(cuentaDto);
    }

    @Override
    public boolean eliminarCuenta(CuentaDto cuentaDto) {
        try{
            Cuenta accountRecent = CuentaMapper.INSTANCE.cuentaDtoToCuenta(cuentaDto);

            if(Objects.nonNull(accountRecent)){
                this.cuentaRepository.delete(accountRecent);
                log.info("Cuenta eliminada correctamente");
                return true;
            }
            return false;
        }catch(Exception ex){
            log.error("Error al eliminar cuenta: {}", ex.getMessage());
            return false;
        }
    }

    @Override
    public CuentaDto obtenerCuentaPorNumeroCuenta(String numberAccount) {
        Cuenta accountRecover = this.cuentaRepository.findByNumberAccount(numberAccount);
        return CuentaMapper.INSTANCE.cuentaToCuentaDto(accountRecover);
    }

    @Override
    public CuentaDto obtenerCuentaPorId(Integer idCuenta) {
        Cuenta accountRecover = this.cuentaRepository.findById(idCuenta).orElse(null);
        if(accountRecover != null){
            return CuentaMapper.INSTANCE.cuentaToCuentaDto(accountRecover);
        }
        return null;
    }

    @Override
    public List<CuentaDto> obtenerTodos() {
        List<Cuenta> accountList = this.cuentaRepository.findAll();

        return CuentaMapper.INSTANCE.cuentaListToCuentaDtoList(accountList);
    }
}
