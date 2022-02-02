package kaf22.codezilla.finapi.controllers;

import kaf22.codezilla.finapi.errors.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

@RestController
@RequestMapping("/api/file")
public class FileController {

    @GetMapping
    public ResponseEntity<?> getPhoto(@RequestParam String path) throws IOException {

        File imagePath = new File(path);

        if (!imagePath.exists()) {
            return new ResponseEntity<>(Map.of(
                    "code", ErrorCode.FILE_DOES_NOT_EXIST.getCode(),
                    "error", ErrorCode.FILE_DOES_NOT_EXIST.getMessage()
            ), HttpStatus.NOT_FOUND);
        }

        byte[] fileContent = Files.readAllBytes(imagePath.toPath());

        return new ResponseEntity<>(fileContent, HttpStatus.OK);
    }
}
