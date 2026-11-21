package com.application.dogpackage.controller;

import com.application.dogpackage.model.Encomenda;
import com.application.dogpackage.model.Status;
import com.application.dogpackage.repository.EncomendaRepositorio;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CadastroController {
    private static final DateTimeFormatter format =  DateTimeFormatter.ofPattern("dd/mm/yyyy");

    @FXML private TextField txtNomeProduto;
    @FXML private TextField txtDescricao;
    @FXML private TextField txtPreco;
    @FXML private DatePicker dpPedido;
    @FXML private DatePicker dpEntrega;
    @FXML private ComboBox<Status> cbStatus;
    @FXML private ImageView imgPreview;
    @FXML private Button btnSalvar;

    @FXML private TableView<Encomenda> tabela;
    @FXML private TableColumn<Encomenda, Boolean> colConcluida;
    @FXML private TableColumn<Encomenda, Integer> colId;
    @FXML private TableColumn<Encomenda, String> colNomeProduto;
    @FXML private TableColumn<Encomenda, String> colDescricao;
    @FXML private TableColumn<Encomenda, String> colPreco;
    @FXML private TableColumn<Encomenda, String> colPedido;
    @FXML private TableColumn<Encomenda, String> colEntrega;
    @FXML private TableColumn<Encomenda, Status> colStatus;

    private Encomenda emEdicao; //se for null é pq ta em modo cadastro
    private String imagemSelecionada; //url da imagem selecionada

    @FXML
    public void inicializar() {
        cbStatus.getItems().setAll(Status.values());
        cbStatus.setValue(Status.PENDENTE);
        dpPedido.setValue(LocalDate.now());

        //checkbox
        colConcluida.setCellValueFactory(c ->
        {
            Encomenda e = c.getValue();
            SimpleBooleanProperty p = new SimpleBooleanProperty(e.getStatus() == Status.ENTREGUE);
            p.addListener((obs, antigo, novo) -> {
                Status novoStatus = novo ? Status.ENTREGUE : Status.PENDENTE;
                e.setStatus(novoStatus);
                if (e == emEdicao) cbStatus.setValue(novoStatus);
                tabela.refresh();
            });
            return p;
        });
        colConcluida.setCellFactory(CheckBoxTableCell.forTableColumn(colConcluida));

        //recebendo valores
        colId.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getId()));
        colNomeProduto.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNome()));
        colDescricao.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDescricao()));
        colPreco.setCellValueFactory(c -> new SimpleStringProperty(String.format("R$ %.2f", c.getValue().getPreco())));
        colPedido.setCellValueFactory(c -> new SimpleStringProperty(formatar(c.getValue().getDataPedido())));
        colEntrega.setCellValueFactory(c -> new SimpleStringProperty(formatar(c.getValue().getDataEntrega())));
        colStatus.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getStatus()));

        tabela.setItems(EncomendaRepositorio.getList());

        // selecionar linha = carregar para edição
        tabela.getSelectionModel().selectedItemProperty().addListener((obs, antiga, sel) ->
        {
            if (sel != null) {
                carregarNoFormulario(sel);
            }
        });

        // duplo clique abre o visualizador
        tabela.setRowFactory(tv ->
        {
            TableRow<Encomenda> linha = new TableRow<>();
            linha.setOnMouseClicked(ev ->
            {
                if (ev.getClickCount() == 2 && !linha.isEmpty()) {
                    abrirVisualizador(linha.getItem().getURLImagem());
                }
            });
            return linha;
        });
    }

    @FXML
    private void escolherImagem(){
        FileChooser fc = new FileChooser();
        fc.setTitle("Escolha a imagem da encomenda");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("images", "*.png", "*.jpg", "*.gif", "*.bmp"));
        File arquivo = fc.showOpenDialog(txtNomeProduto.getScene().getWindow());
        if(arquivo != null){
            imagemSelecionada = arquivo.getAbsolutePath();
            mostrarPreview(imagemSelecionada);
        }
    }

    @FXML
    private  void ampliarImagem(){
        abrirVisualizador(imagemSelecionada);
    }

    @FXML
    private void salvar(){
        if(txtNomeProduto.getText().isBlank() || txtDescricao.getText().isBlank() || txtPreco.getText().isBlank() || dpEntrega.getValue() == null){
            alerta("Preencha o nome do cliente, descrição, preço e data de entrega");
        }

        double preco = 0;
        try {
            preco = Double.parseDouble(txtPreco.getText().trim().replace(",",".")); //converte
        }catch (NumberFormatException e){
            alerta("Preço inválido. Use um número, por exemplo 25,30.");
        }

        //se for null significa que está em modo cadastro, então cria uma nova encomenda, se for diferente de null está em modo edição
        if(emEdicao == null){
            Encomenda nova = new Encomenda(txtNomeProduto.getText().trim(), txtDescricao.getText().trim(), preco, dpPedido.getValue(), dpEntrega.getValue(), cbStatus.getValue());
            nova.setURLImagem(imagemSelecionada);
            EncomendaRepositorio.addLista(nova);
        }else{
            emEdicao.setNome(txtNomeProduto.getText().trim());
            emEdicao.setDescricao(txtDescricao.getText().trim());
            emEdicao.setPreco(preco);
            emEdicao.setURLImagem(imagemSelecionada);
            emEdicao.setDataEntrega(dpEntrega.getValue());
            emEdicao.setDataEntrega(dpPedido.getValue());
            emEdicao.setStatus(cbStatus.getValue());
            tabela.refresh();
        }

        limpar();
    }

    @FXML
    private void limpar(){
        tabela.getSelectionModel().clearSelection();
        emEdicao = null;
        imagemSelecionada = null;
        txtNomeProduto.clear();
        txtDescricao.clear();
        txtPreco.clear();
        dpPedido.setValue(LocalDate.now());
        dpEntrega.setValue(null);
        cbStatus.setValue(Status.PENDENTE);
        imgPreview.setImage(null);
        btnSalvar.setText("Cadastrar");
    }

    //carrega os dados do formulario clicado na parte de formulario
    private void carregarNoFormulario(Encomenda e){
        emEdicao = e;
        txtNomeProduto.setText(e.getNome());
        txtDescricao.setText(e.getDescricao());
        txtPreco.setText(String.valueOf(e.getPreco()).replace(".", ","));
        dpPedido.setValue(e.getDataPedido());
        dpEntrega.setValue(e.getDataEntrega());
        cbStatus.setValue(e.getStatus());
        imagemSelecionada = e.getURLImagem();
        mostrarPreview(imagemSelecionada);
        btnSalvar.setText("Salvar alterações");
    }

    private void mostrarPreview(String caminho){
        if(caminho == null || !new File(caminho).exists()){
            alerta("Esta encomenda não tem imagem(ou o arquivo foi movido");
            return;
        }
        imgPreview.setImage(new Image(new File(caminho).toURI().toString(),160, 160, true, true));
    }

    private void abrirVisualizador(String caminho){
        if(caminho == null || !new File(caminho).exists()){
            alerta("Esta encomenda não tem imagem(ou o arquivo foi movido");
            return;
        }

        Image img = new Image(new File(caminho).toURI().toString());
        ImageView iv = new ImageView(img);
        iv.setPreserveRatio(true);

        double inicial = Math.min(1.0, 800.0 /img.getWidth());
        double larguraInicial = img.getWidth() * inicial;
        iv.setFitWidth(img.getWidth() * inicial);

        ScrollPane scrollPane = new ScrollPane(iv);
        scrollPane.setPannable(true);

        iv.addEventFilter(ScrollEvent.SCROLL, event ->
        {
            double fator = event.getDeltaY() > 0 ? 1.1 : 0.9;
            double novoZoom = Math.max(larguraInicial, Math.min(img.getWidth() * 3.0, iv.getFitWidth() * fator));
            iv.setFitWidth(novoZoom);
            event.consume();
        });
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        VBox raiz = new VBox(scrollPane);
        raiz.setPadding(new Insets(12));

        Stage stage = new Stage();
        stage.setTitle("Visualizador de Imagem");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(raiz, 900, 650));
        stage.show();
    }

    private void alerta(String msg){
        new Alert(Alert.AlertType.WARNING, msg).show();
    }
    private String formatar(LocalDate data){
        return data == null ? "-" : data.format(format);
    }
}
