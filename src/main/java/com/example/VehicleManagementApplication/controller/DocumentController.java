package com.example.VehicleManagementApplication.controller;


import com.example.VehicleManagementApplication.DTO.DocumentResponseDTO;
import com.example.VehicleManagementApplication.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @PostMapping("/upload/{vehicleId}")
    public ResponseEntity<DocumentResponseDTO> uploadDocument(@PathVariable Long vehicleId,
                                                              @RequestParam("file") MultipartFile file) throws IOException {
        DocumentResponseDTO documentResponseDTO = documentService.uploadDocument(vehicleId, file);
        return ResponseEntity.ok(documentResponseDTO);
    }

    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<DocumentResponseDTO>> getDocumentsByVehicle(@PathVariable Long vehicleId) {
        List<DocumentResponseDTO> documentResponseDTOs = documentService.getDocumentsByVehicle(vehicleId);
        return ResponseEntity.ok(documentResponseDTOs);
    }

}
