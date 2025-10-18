package com.example.ordereventprocessor.controller;

import com.example.ordereventprocessor.dto.*;
import com.example.ordereventprocessor.model.*;
import com.example.ordereventprocessor.repository.OrderRepository;
import com.example.ordereventprocessor.repository.VehicleRepository;
import com.example.ordereventprocessor.service.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supabase")
public class VehicleController {
    private final VehicleService _vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this._vehicleService = vehicleService;
    }

    @PostMapping("/jpa/vehicles")
    public ResponseEntity<?> createVehicleJPA(@RequestBody VehicleDTO vehicleDTO) {
        
        VehicleEntity savedVehicle = _vehicleService.createVehicle(vehicleDTO);
        
        return ResponseEntity.ok(savedVehicle);
    }

}
