package com.betacom.sb.services.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betacom.sb.dto.input.BicycleReq;
import com.betacom.sb.dto.output.BicycleDTO;

@Service
public interface IBicycleServices {
    BicycleDTO getById(Long id) throws Exception;
    List<BicycleDTO> list() throws Exception;
    void create(BicycleReq req) throws Exception;
    void update(BicycleReq req) throws Exception;
    void delete(Long id) throws Exception;
}