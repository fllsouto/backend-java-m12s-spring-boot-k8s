package com.cdc.backend.shoppingClient.dto;

// import com.cdc.backend.productApi.model.Category;

// import jakarta.validation.constraints.NotNull;

public class CategoryDTO {

    // @NotNull
    private long id;
    private String nome;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
