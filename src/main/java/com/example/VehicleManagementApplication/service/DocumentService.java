package com.example.VehicleManagementApplication.service;

import com.example.VehicleManagementApplication.DTO.DocumentResponseDTO;
import com.example.VehicleManagementApplication.model.Document;
import com.example.VehicleManagementApplication.model.Vehicle;
import com.example.VehicleManagementApplication.repository.DocumentRepository;
import com.example.VehicleManagementApplication.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    // Upload document (photo or file)
    public DocumentResponseDTO uploadDocument(Long vehicleId, MultipartFile file) throws IOException {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        Document document = new Document();
        document.setFileName(file.getOriginalFilename());
        document.setDocumentType(file.getContentType());
        document.setFileData(file.getBytes());
        document.setVehicle(vehicle);

        Document savedDocument = documentRepository.save(document);

        return convertToResponseDTO(savedDocument);
    }

    // Get all documents for a vehicle
    public List<DocumentResponseDTO> getDocumentsByVehicle(Long vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        List<Document> documents = documentRepository.findByVehicle(vehicle);
        return documents.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    // Helper method to convert Document entity to DocumentResponseDTO
    private DocumentResponseDTO convertToResponseDTO(Document document) {
        DocumentResponseDTO responseDTO = new DocumentResponseDTO();
        responseDTO.setId(document.getId());
        responseDTO.setFileName(document.getFileName());
        responseDTO.setDocumentType(document.getDocumentType());
        responseDTO.setFileUrl("/documents/" + document.getId());  // Assuming the URL to access the document
        return responseDTO;
    }


}
