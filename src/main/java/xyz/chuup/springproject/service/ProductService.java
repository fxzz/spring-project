package xyz.chuup.springproject.service;

import org.springframework.transaction.annotation.Transactional;
import xyz.chuup.springproject.domain.Product;
import xyz.chuup.springproject.dto.PageRequestDTO;
import xyz.chuup.springproject.dto.PageResponseDTO;
import xyz.chuup.springproject.dto.ProductDTO;

import java.util.List;

@Transactional
public interface ProductService {

    PageResponseDTO<ProductDTO> getList(PageRequestDTO pageRequestDTO);

    Long register(ProductDTO productDTO);

    default Product dtoToEntity(ProductDTO productDTO) {

        Product product = Product.builder()
                .pno(productDTO.getPno())
                .pname(productDTO.getPname())
                .pdesc(productDTO.getPdesc())
                .price(productDTO.getPrice())
                .build();

        List<String> uploadFileNames = productDTO.getUploadFileNames();
        if (uploadFileNames == null || uploadFileNames.size() ==0) {
            return product;
        }

        uploadFileNames.forEach(fileName -> {
            product.addImageString(fileName);
        });

        return product;
    }
}
