package models;

public class Equipamento {
    private String codigo;
    private String nome;
    private String categoria;
    private String fabricante;
    private String modelo;
    private String setor;
    private String dataInstalacao;
    private String status; // Operanco / Em manutenção / Inativo
    
    public Equipamento(String codigo, String nome, String categoria, String fabricante, String modelo, String setor,
            String dataInstalacao, String status) {
        this.codigo = codigo;
        this.nome = nome;
        this.categoria = categoria;
        this.fabricante = fabricante;
        this.modelo = modelo;
        this.setor = setor;
        this.dataInstalacao = dataInstalacao;
        this.status = status;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getDataInstalacao() {
        return dataInstalacao;
    }

    public void setDataInstalacao(String dataInstalacao) {
        this.dataInstalacao = dataInstalacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
