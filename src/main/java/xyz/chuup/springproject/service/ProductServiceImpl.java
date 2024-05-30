package xyz.chuup.springproject.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import xyz.chuup.springproject.domain.Product;
import xyz.chuup.springproject.domain.ProductImage;
import xyz.chuup.springproject.dto.PageRequestDTO;
import xyz.chuup.springproject.dto.PageResponseDTO;
import xyz.chuup.springproject.dto.ProductDTO;
import xyz.chuup.springproject.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public PageResponseDTO<ProductDTO> getList(PageRequestDTO pageRequestDTO) {

        Pageable pageable = PageRequest.of(pageRequestDTO.getPage() -1,
                pageRequestDTO.getSize(),
                Sort.by("pno").descending());

        Page<Object[]> result = productRepository.selectList(pageable);

        //Object[] => 0 product, 1 productImage

        List<ProductDTO> dtoList = result.get().map(arr -> {
            ProductDTO productDTO = null;
            Product product = (Product) arr[0];
            ProductImage productImage = (ProductImage) arr[1];

            productDTO = productDTO.builder()
                    .pno(product.getPno())
                    .pname(product.getPname())
                    .pdesc(product.getPdesc())
                    .price(product.getPrice())
                    .build();

            String imageStr = productImage.getFileName();
            productDTO.setUploadFileNames(List.of(imageStr));

            return productDTO;
        }).collect(Collectors.toList());


       long totalCount = result.getTotalElements();

       return PageResponseDTO.<ProductDTO>withAll()
               .dtoList(dtoList)
               .totalCount(totalCount)
               .pageRequestDTO(pageRequestDTO)
               .build();
    }

    @Override
    public Long register(ProductDTO productDTO) {

        Product product = dtoToEntity(productDTO);

        log.info("----------------------");
        log.info(product);
        log.info(product.getImageList());
        Long pno = productRepository.save(product).getPno();

        return pno;
    }


}
