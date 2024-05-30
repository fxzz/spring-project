package xyz.chuup.springproject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.chuup.springproject.dto.PageRequestDTO;
import xyz.chuup.springproject.dto.PageResponseDTO;
import xyz.chuup.springproject.dto.ProductDTO;
import xyz.chuup.springproject.service.ProductService;
import xyz.chuup.springproject.util.CustomFileUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final CustomFileUtil fileUtil;

    private final ProductService productService;



    @GetMapping("/view/{fileName}")
    public ResponseEntity<Resource> viewFileGET(@PathVariable String fileName){
        return fileUtil.getFile(fileName);
    }


    @GetMapping("/list")
    public PageResponseDTO<ProductDTO> list(PageRequestDTO pageRequestDTO) {
        return productService.getList(pageRequestDTO);
    }

    @PostMapping("/")
    public Map<String, Long> register(ProductDTO productDTO) {

         List<MultipartFile> files = productDTO.getFiles();

         List<String> uploadFileNames = fileUtil.saveFiles(files);

         productDTO.setUploadFileNames(uploadFileNames);
         log.info(uploadFileNames);

         Long pno = productService.register(productDTO);

         return Map.of("result", pno);

    }
}
