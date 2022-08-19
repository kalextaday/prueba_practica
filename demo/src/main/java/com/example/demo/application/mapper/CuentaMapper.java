package com.example.demo.application.mapper;

import com.example.demo.application.dto.CuentaDto;
import com.example.demo.application.dto.PersonaDto;
import com.example.demo.domain.entity.Cuenta;
import com.example.demo.domain.entity.Persona;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public abstract class CuentaMapper {

    public static final CuentaMapper INSTANCE = Mappers.getMapper(CuentaMapper.class);


    @Mapping(source = "cuenta.idCuenta",                    target = "idCuenta")
    @Mapping(source = "cuenta.idCliente",                    target = "idCliente")
    @Mapping(source = "cuenta.numeroCuenta",                    target = "numeroCuenta")
    @Mapping(source = "cuenta.tipoCuenta",                    target = "tipoCuenta")
    @Mapping(source = "cuenta.saldoInicial",                    target = "saldoInicial")
    @Mapping(source = "cuenta.estadoCuenta",                    target = "estadoCuenta")
    public abstract CuentaDto cuentaToCuentaDto(Cuenta cuenta);


    @Mapping(source = "cuentaDto.idCliente",                    target = "idCliente")
    @Mapping(source = "cuentaDto.numeroCuenta",                    target = "numeroCuenta")
    @Mapping(source = "cuentaDto.tipoCuenta",                    target = "tipoCuenta")
    @Mapping(source = "cuentaDto.saldoInicial",                    target = "saldoInicial")
    @Mapping(source = "cuentaDto.estadoCuenta",                    target = "estadoCuenta")
    @Mapping(source = "cuentaDto.idCuenta",                    target = "idCuenta")
    public abstract Cuenta cuentaDtoToCuenta(CuentaDto cuentaDto);

    public List<CuentaDto> cuentaListToCuentaDtoList(List<Cuenta> data) {
        return data.stream()
                .map(this::cuentaToCuentaDto)
                .collect(Collectors.toList());
    }
}
