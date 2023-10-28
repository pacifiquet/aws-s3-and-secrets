package com.pacifique.book;

import com.pacifique.service.StorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@RestController
@RequestMapping("storage")
@Tag(name = "Storage Controller")
@AllArgsConstructor
public class StorageController {
    private final StorageService service;

    @PostMapping(value = "upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "file upload")
    ResponseEntity<String> fileUpload(@RequestPart final MultipartFile file){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.uploadFile(file));
    }


    @GetMapping("/download/{fileName}")
    @Operation(summary = "download file")
    ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) {
        byte[] data = service.downloadFile(fileName);
        ByteArrayResource resource = new ByteArrayResource(Objects.requireNonNull(data));
        return ResponseEntity.ok()
                .contentLength(data.length)
                .header("Content-type","application/octet-stream")
                .header("Content-disposition","attachment; filename=\""+fileName +"\"")
                .body(resource);
    }

    @DeleteMapping("/delete/{fileName}")
    @Operation(summary = "delete file")
    ResponseEntity<String> deleteFile(@PathVariable String fileName){
        return new ResponseEntity<>(service.deleteFile(fileName),HttpStatus.OK);
    }
}
