package com.cdc.backend.shoppingClient.dto;

// import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.NotNull;

public class ProductDTO {

    // @NotBlank
    private String productIdentifier;
    // @NotBlank
    private String nome;
    // @NotBlank
    private String descricao;
    // @NotNull
    private Float preco;
    // @NotNull
    private CategoryDTO category;



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getProductIdentifier() {
        return productIdentifier;
    }

    public void setProductIdentifier(String productIdentifier) {
        this.productIdentifier = productIdentifier;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public CategoryDTO getCategory() {
        return this.category;
    }
}
