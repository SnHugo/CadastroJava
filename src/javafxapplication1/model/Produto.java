
package javafxapplication1.model;

public class Produto {
    
    private int codigo;
    private String nome;
    private double preco;
    private int unidade;
    private String fornecedor;
    private String tamanho;
    private String categoria;
    private String marca;
    private String data_cadastro;
    private String desc;

    public Produto(int codigo, String nome, double preco, int unidade, String fornecedor, String tamanho, String categoria, String marca, String data_cadastro, String desc) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.unidade = unidade;
        this.fornecedor = fornecedor;
        this.tamanho = tamanho;
        this.categoria = categoria;
        this.marca = marca;
        this.data_cadastro = data_cadastro;
        this.desc = desc;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getUnidade() {
        return unidade;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public String getTamanho() {
        return tamanho;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getMarca() {
        return marca;
    }

    public String getData_cadastro() {
        return data_cadastro;
    }

    public String getDesc() {
        return desc;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setUnidade(int unidade) {
        this.unidade = unidade;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setData_cadastro(String data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Produto{" + "codigo=" + codigo + ", nome=" + nome + ", preco=" + preco + ", unidade=" + unidade + ", fornecedor=" + fornecedor + ", tamanho=" + tamanho + ", categoria=" + categoria + ", marca=" + marca + ", data_cadastro=" + data_cadastro + ", desc=" + desc + '}';
    }
    
    
    
    
    
}
