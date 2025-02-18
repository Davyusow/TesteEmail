package com.example.testeemail;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;  //aqui é só coisa do javafx porque o serviço de e-mail ta todo na outra classe

public class ConfirmacaoEmail extends Application {

    private Stage janela1;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage janela1) {
        this.janela1 = janela1;
        janela1.setTitle("Email Confirmation App");
        
        VBox layoutInicial = new VBox(10);
        Label labelEmail = new Label("Digite seu e-mail:");
        TextField textFieldEmail = new TextField();
        Button buttonEnviarEmail = new Button("Enviar E-mail");

        layoutInicial.getChildren().addAll(labelEmail, textFieldEmail, buttonEnviarEmail);

        Scene cena1 = new Scene(layoutInicial, 300, 200);
        janela1.setScene(cena1);
        janela1.show();

        
        buttonEnviarEmail.setOnAction(e -> { 
            String email = textFieldEmail.getText();
            if (verificaEmail(email)) {   //
                enviaConfirmacaoDoEmail(email);
                mostraTelaDeConfirmacao();
            } else {
                labelEmail.setText("E-mail inválido. Tente novamente.");
            }
        });
    }

    public boolean verificaEmail(String email){
        boolean resultado;
        int indicadorArroba = email.indexOf('@');
        int indicadorPonto = email.lastIndexOf('.');
        if(indicadorArroba > 0 && indicadorPonto > indicadorArroba && indicadorPonto < email.length() - 1) {
            resultado = true;
        }else{
            System.out.println("Email inválido");
            resultado = false;
        }
        return resultado;
    }
    
    private void enviaConfirmacaoDoEmail(String email) {
        ServicoEmail.enviaConfirmacaoDoEmail(email);
    }

    private void mostraTelaDeConfirmacao() {
        // Tela de confirmação
        VBox layoutConfirmacao = new VBox(10);
        Label labelConfirmacao = new Label("E-mail enviado. Verifique sua caixa de entrada.");
        Button buttonConfirmacao = new Button("Confirmar");

        layoutConfirmacao.getChildren().addAll(labelConfirmacao, buttonConfirmacao);

        Scene cena2 = new Scene(layoutConfirmacao, 300, 200);
        janela1.setScene(cena2);

        // Ação do botão "Confirmar"
        buttonConfirmacao.setOnAction(e -> {
            // Verificar se o usuário confirmou o e-mail
            if (ServicoEmail.isEmailConfirmed()) {
                mostraProximaTela();
            } else {
                labelConfirmacao.setText("Confirmação pendente. Tente novamente.");
            }
        });
    }

    private void mostraProximaTela() {
        // Próxima tela do programa
        VBox layoutProximaTela = new VBox(10);
        Label labelProximaTela = new Label("E-mail confirmado com sucesso!");

        layoutProximaTela.getChildren().add(labelProximaTela);

        Scene cena3 = new Scene(layoutProximaTela, 300, 200);
        janela1.setScene(cena3);
    }
}