/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxapplication1.controller;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafxapplication1.model.Produto;
import javafxapplication1.model.dao.ProdutoDAOImpl;

public class VendaProdutoController implements Initializable {

    private ProdutoDAOImpl produtoDAO = ProdutoDAOImpl.getInstance();

    private ObservableList<Produto> listaObservable;

    private List<Produto> lista;

    @FXML
    private Button btn_adicionar;

    @FXML
    private Button btn_cancelar;

    @FXML
    private Button btn_remover;

    @FXML
    private Button btn_pesquisar;

    @FXML
    private TextField textField_codigo;

    @FXML
    private TextField textField_nome;

    @FXML
    private TextField textField_preco;

    @FXML
    private TextField textField_qtd;

    @FXML
    private TextField textField_forn;

    @FXML
    private TextField textField_marca;

    @FXML
    private TextField textField_desc;

    @FXML
    private ComboBox comboBox_tam;

    @FXML
    private ComboBox comboBox_cat;

    @FXML
    private DatePicker datePicker_dataCad;

    @FXML
    private TableView<Produto> tabelaAdicionados;

    @FXML
    private TableColumn<Produto, String> colunaCodigo;

    @FXML
    private TableColumn<Produto, String> colunaNome;

    @FXML
    private TableColumn<Produto, Double> colunaPreco;

    @FXML
    private TableColumn<Produto, Integer> colunaQtd;

    private String estado = "inicial";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        atualizarEstado();
        configurarColunas();

        tabelaAdicionados.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    btn_remover.setDisable(newSelection == null);
                }
        );
    }

    private void configurarColunas() {
        colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("Codigo"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        colunaPreco.setCellValueFactory(new PropertyValueFactory<>("Preco"));
        colunaQtd.setCellValueFactory(new PropertyValueFactory<>("Qtd"));
    }

    @FXML
    private void handlePesquisar() {
        String codigo = textField_codigo.getText().trim();
        Produto produto = produtoDAO.buscarPorId(codigo);

        if (produto != null) {
            estado = "vendendo";
            atualizarEstado();
            preencherCampos(produto);
        } else {
            System.out.println("Produto não encontrado!");
        }
    }

    @FXML
    private void handleAdicionar() {
        String codigo = textField_codigo.getText().trim();
        Produto produto = produtoDAO.buscarPorId(codigo);

        if (produto != null) {
            try {
                int quantidadeDesejada = Integer.parseInt(textField_qtd.getText());

                if (quantidadeDesejada <= produto.getQtd()) {

                    Produto produtoParaVenda = new Produto(
                            produto.getCodigo(),
                            produto.getNome(),
                            produto.getPreco(),
                            quantidadeDesejada,
                            produto.getFornecedor(),
                            produto.getTamanho(),
                            produto.getCategoria(),
                            produto.getMarca(),
                            produto.getData_cadastro(),
                            produto.getDesc()
                    );

                    tabelaAdicionados.getItems().add(produtoParaVenda);

                    produto.setQtd(produto.getQtd() - quantidadeDesejada);

                    produtoDAO.atualizar(produto);
                    limparCampos();
                    estado = "inicial";
                    atualizarEstado();
                } else {
                    System.out.println("Quantidade em estoque insuficiente!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Digite uma quantidade válida!");
            }
        }
    }

    @FXML
    private void handleRemover() {
        Produto produtoSelecionado = tabelaAdicionados.getSelectionModel().getSelectedItem();

        if (produtoSelecionado != null) {
            Produto produtoOriginal = produtoDAO.buscarPorId(produtoSelecionado.getCodigo());

            if (produtoOriginal != null) {
                produtoOriginal.setQtd(produtoOriginal.getQtd() + produtoSelecionado.getQtd());

                produtoDAO.atualizar(produtoOriginal);

                tabelaAdicionados.getItems().remove(produtoSelecionado);

                tabelaAdicionados.getSelectionModel().clearSelection();

                if (tabelaAdicionados.getItems().isEmpty()) {
                    estado = "inicial";
                    atualizarEstado();
                    limparCampos();
                }
            }
        } else {
            mostrarAlerta("Remoção", "Selecione um produto para remover.");
        }
    }

    @FXML
    private void handleVender() {
        if (tabelaAdicionados.getItems().isEmpty()) {
            mostrarAlerta("Venda", "Não há produtos para vender!");
            return;
        }

        double valorTotal = tabelaAdicionados.getItems().stream()
                .mapToDouble(p -> p.getPreco() * p.getQtd())
                .sum();

        if (confirmarVenda(valorTotal)) {
            atualizarEstoque();

            tabelaAdicionados.getItems().clear();

            mostrarAlerta("Venda", "Venda realizada com sucesso!\nValor total: R$ " + String.format("%.2f", valorTotal));
        }
    }

    private void limparCampos() {
        textField_codigo.clear();
        textField_nome.clear();
        textField_preco.clear();
        textField_qtd.clear();
        textField_forn.clear();
        textField_marca.clear();
        textField_desc.clear();
        comboBox_tam.setValue(null);
        comboBox_cat.setValue(null);
        datePicker_dataCad.setValue(null);
    }

    private void atualizarEstoque() {
        for (Produto produtoVendido : tabelaAdicionados.getItems()) {
            Produto produtoOriginal = produtoDAO.buscarPorId(produtoVendido.getCodigo());

            if (produtoOriginal != null) {
                produtoDAO.atualizar(produtoOriginal);
            }
        }
    }

    @FXML
    private void handleCancelar() {
        if (tabelaAdicionados.getItems().isEmpty()) {
            mostrarAlerta("Cancelamento", "Não há produtos para cancelar.");
            return;
        }

        Alert confirmacaoAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacaoAlert.setTitle("Cancelar Venda");
        confirmacaoAlert.setHeaderText("Confirmar Cancelamento");
        confirmacaoAlert.setContentText("Tem certeza que deseja cancelar a venda? Todos os produtos serão devolvidos ao estoque.");

        Optional<ButtonType> resultado = confirmacaoAlert.showAndWait();

        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            for (Produto produtoVendido : tabelaAdicionados.getItems()) {
                Produto produtoOriginal = produtoDAO.buscarPorId(produtoVendido.getCodigo());

                if (produtoOriginal != null) {

                    produtoOriginal.setQtd(produtoOriginal.getQtd() + produtoVendido.getQtd());
                    produtoDAO.atualizar(produtoOriginal);
                }
            }
            tabelaAdicionados.getItems().clear();

            mostrarAlerta("Cancelamento", "Venda cancelada com sucesso. Todos os produtos foram devolvidos ao estoque.");

            estado = "inicial";
            atualizarEstado();
        }
    }

    private boolean confirmarVenda(double valorTotal) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação de Venda");
        alert.setHeaderText("Confirmar Venda");
        alert.setContentText("Valor total da venda: R$ " + String.format("%.2f", valorTotal)
                + "\n\nDeseja realmente finalizar a venda?");

        Optional<ButtonType> resultado = alert.showAndWait();
        return resultado.isPresent() && resultado.get() == ButtonType.OK;
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public void setProdutos(List<Produto> produtos) {
        this.lista = produtos;
    }

    public List<Produto> listarTodos() {
        return lista;
    }

    public Produto buscarPorId(String codigo) {
        for (Produto produto : lista) {
            if (produto.getCodigo().equals(codigo)) {
                return produto;
            }
        }
        return null;
    }

    public void preencherCampos(Produto produto) {
        textField_codigo.setText(produto.getCodigo());
        textField_nome.setText(produto.getNome());
        textField_preco.setText(String.valueOf(produto.getPreco()));
        textField_qtd.setText(String.valueOf(produto.getQtd()));
        textField_forn.setText(produto.getFornecedor());
        textField_marca.setText(produto.getMarca());
        textField_desc.setText(produto.getDesc());
        comboBox_tam.setValue(produto.getTamanho());
        comboBox_cat.setValue(produto.getCategoria());
        datePicker_dataCad.setValue(java.time.LocalDate.parse(produto.getData_cadastro()));
    }

    protected void atualizarEstado() {
        switch (estado) {
            case "inicial":
                textField_nome.setDisable(true);
                textField_preco.setDisable(true);
                textField_qtd.setDisable(true);
                textField_forn.setDisable(true);
                comboBox_tam.setDisable(true);
                comboBox_cat.setDisable(true);
                textField_marca.setDisable(true);
                datePicker_dataCad.setDisable(true);
                btn_adicionar.setDisable(true);
                btn_pesquisar.setDisable(false);
                btn_remover.setDisable(true);
                btn_cancelar.setDisable(true);
                break;
            case "vendendo":
                textField_nome.setDisable(true);
                textField_preco.setDisable(true);
                textField_qtd.setDisable(false);
                textField_forn.setDisable(true);
                comboBox_tam.setDisable(true);
                comboBox_cat.setDisable(true);
                textField_marca.setDisable(true);
                datePicker_dataCad.setDisable(true);
                btn_adicionar.setDisable(false);
                btn_pesquisar.setDisable(true);
                btn_remover.setDisable(true);
                btn_cancelar.setDisable(false);
                break;
        }
    }

}
