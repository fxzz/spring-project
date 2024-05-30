package xyz.chuup.springproject.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.chuup.springproject.dto.PageRequestDTO;
import xyz.chuup.springproject.dto.PageResponseDTO;
import xyz.chuup.springproject.dto.ProductDTO;

@SpringBootTest
@Log4j2
public class ProductServiceTests {

    @Autowired
    private ProductService productService;

    @Test
    public void testList() {
    PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();

        PageResponseDTO<ProductDTO> responseDTO = productService.getList(pageRequestDTO);

        log.info(responseDTO.getDtoList());
    }
}
