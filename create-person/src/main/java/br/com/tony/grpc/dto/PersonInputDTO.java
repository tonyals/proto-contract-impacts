package br.com.tony.grpc.dto;

public class PersonInputDTO {

    private String name;
    private String email;
    private String cpf;

    private PersonInputDTO() {
    }

    private PersonInputDTO(String name, String email, String cpf) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
    }

    public static PersonInputDTO builder() {
        return new PersonInputDTO();
    }

    public PersonInputDTO setName(String name) {
        this.name = name;
        return this;
    }

    public PersonInputDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public PersonInputDTO setCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public PersonInputDTO build() {
        return new PersonInputDTO(this.name, this.email, this.cpf);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }
}
