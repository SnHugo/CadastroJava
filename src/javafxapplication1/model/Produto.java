
package javafxapplication1.model;


public class Produto {
    private int codigo;
    private String nome;
    private double preco;
    private int qtd;
    private String fornecedor;
    private String tamanho;
    private String categoria;
    private String marca;
    private String data_cadastro;
    private String desc;

    public Produto(int codigo, String nome, double preco, int qtd, String fornecedor, String tamanho, String categoria, String marca, String data_cadastro, String desc) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.qtd = qtd;
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

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(String data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Produto{" + "codigo=" + codigo + ", nome=" + nome + ", preco=" + preco + ", qtd=" + qtd + ", fornecedor=" + fornecedor + ", tamanho=" + tamanho + ", categoria=" + categoria + ", marca=" + marca + ", data_cadastro=" + data_cadastro + ", desc=" + desc + '}';
    }
    
    
}
