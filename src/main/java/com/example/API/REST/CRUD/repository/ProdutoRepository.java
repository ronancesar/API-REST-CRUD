package com.example.API.REST.CRUD.repository;

import com.example.API.REST.CRUD.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Opcional, mas e uma boa pratica pra indicar que e um componente de acesso a dados
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    /* A "mágica" acontece aqui, não precisamos escrever nenhum metodo.
    * O JpaRepository já nos fornece todos os métodos CRUD básicos :
    * save(), findiById(), findAll(), deleteById(), etc */


}
