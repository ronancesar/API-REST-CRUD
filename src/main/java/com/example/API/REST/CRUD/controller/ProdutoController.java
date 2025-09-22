package com.example.API.REST.CRUD.controller;

import com.example.API.REST.CRUD.entity.Produto;
import com.example.API.REST.CRUD.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    // 1. CREATE (Criar) - POST /produtos
    @PostMapping
    public Produto criarProduto(@RequestBody Produto produto) {
        // Salva o novo produto recebido no corpo da requisição e o retorna.
        return produtoRepository.save(produto);
    }

    // 2. READ (Ler Todos) - GET /produtos
    @GetMapping
    public List<Produto> listarTodosOsProdutos() {
        // Retorna a lista completa de todos os produtos no banco de dados.
        return produtoRepository.findAll();
    }

    // 3. READ (Ler por ID) - GET /produtos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable Long id) {
        // Busca um produto pelo ID.
        // Se encontrar, retorna o produto com status 200 OK.
        // Se não encontrar, retorna um status 404 Not Found.
        return produtoRepository.findById(id)
                .map(produto -> ResponseEntity.ok().body(produto))
                .orElse(ResponseEntity.notFound().build());
    }

    // 4. UPDATE (Atualizar) - PUT /produtos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @RequestBody Produto produtoDetalhes) {
        // Busca o produto existente pelo ID.
        return produtoRepository.findById(id)
                .map(produtoExistente -> {
                    // Se encontrar, atualiza os campos com os dados recebidos no corpo da requisição.
                    produtoExistente.setNome(produtoDetalhes.getNome());
                    produtoExistente.setPreco(produtoDetalhes.getPreco());
                    produtoExistente.setQuantidade(produtoDetalhes.getQuantidade());
                    // Salva o produto atualizado no banco.
                    Produto produtoAtualizado = produtoRepository.save(produtoExistente);
                    // Retorna o produto atualizado com status 200 OK.
                    return ResponseEntity.ok().body(produtoAtualizado);
                })
                // Se não encontrar o produto para atualizar, retorna 404 Not Found.
                .orElse(ResponseEntity.notFound().build());
    }

    // 5. DELETE (Deletar) - DELETE /produtos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarProduto(@PathVariable Long id) {
        // Busca o produto pelo ID.
        return produtoRepository.findById(id)
                .map(produto -> {
                    // Se encontrar, deleta o produto.
                    produtoRepository.deleteById(id);
                    // Retorna status 204 No Content, indicando sucesso sem corpo de resposta.
                    return ResponseEntity.noContent().build();
                })
                // Se não encontrar o produto para deletar, retorna 404 Not Found.
                .orElse(ResponseEntity.notFound().build());
    }
}