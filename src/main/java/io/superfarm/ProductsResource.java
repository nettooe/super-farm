package io.superfarm;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductsResource {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/{codigo}")
    public ResponseEntity<Product> buscarPeloCodigo(@PathVariable Long codigo) {
        Optional<Product> produto = productRepository.findById(codigo);

        return produto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
