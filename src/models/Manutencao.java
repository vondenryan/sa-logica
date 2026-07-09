package models;

import java.time.LocalDateTime;

public class Manutencao {
    private String codigo;
    private Equipamento equipamentoRelacionado;
    private Tecnico tecnicoResponsavel;
    private LocalDateTime dataAbertura;
    private LocalDateTime dataEncerramento;
    private String tipoManutencao; //Preventiva / Corretiva
    private String descricaoProblema;
    private String situacao; // Aberta / Em Andamento / Finalizada

    public Manutencao(String codigo, Equipamento equipamentoRelacionado, Tecnico tecnicoResponsavel,
            LocalDateTime dataAbertura, String tipoManutencao, String descricaoProblema, String situacao) {
        this.codigo = codigo;
        this.equipamentoRelacionado = equipamentoRelacionado;
        this.tecnicoResponsavel = tecnicoResponsavel;
        this.dataAbertura = dataAbertura;
        this.tipoManutencao = tipoManutencao;
        this.descricaoProblema = descricaoProblema;
        this.situacao = situacao;
    }

    public String toString() {
        return "Código: " + codigo
        + "\nCod. Equipamento: " + equipamentoRelacionado.getCodigo()
        + "\nCod. Técnico: " + tecnicoResponsavel.getCodigo()
        + "\nData Abertura: " + dataAbertura.toString()
        + "\nData Encerramento: " + (dataEncerramento == null ? "Não finalizada" : dataEncerramento.toString())
        + "\nTipo Manutenção: " + tipoManutencao
        + "\nDescrição: " + descricaoProblema
        + "\nSituação: " + situacao;
    }

    public String getCodigo() {
        return codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public Equipamento getEquipamentoRelacionado() {
        return equipamentoRelacionado;
    }

    public void setEquipamentoRelacionado(Equipamento equipamentoRelacionado) {
        this.equipamentoRelacionado = equipamentoRelacionado;
    }

    public Tecnico getTecnicoResponsavel() {
        return tecnicoResponsavel;
    }

    public void setTecnicoResponsavel(Tecnico tecnicoResponsavel) {
        this.tecnicoResponsavel = tecnicoResponsavel;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDateTime getDataEncerramento() {
        return dataEncerramento;
    }

    public void setDataEncerramento(LocalDateTime dataEncerramento) {
        this.dataEncerramento = dataEncerramento;
    }

    public String getTipoManutencao() {
        return tipoManutencao;
    }

    public void setTipoManutencao(String tipoManutencao) {
        this.tipoManutencao = tipoManutencao;
    }

    public String getDescricaoProblema() {
        return descricaoProblema;
    }

    public void setDescricaoProblema(String descricaoProblema) {
        this.descricaoProblema = descricaoProblema;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
}
