package com.example.testeemail;

import javax.mail.*; //importa tudo da api java mail
import javax.mail.internet.InternetAddress; //meio que precisa pra inserir links no e-mail
import javax.mail.internet.MimeMessage; //necessaria pra editar a mensagem do e-mail
import java.util.Properties;    //necessaria pra a frescura das configurações iniciais

public class ServicoEmail {

    private static final String SMTP_HOST = "smtp.gmail.com";   //serviço de e-mail usado
    private static final String SMTP_PORT = "587";      //porta padrão do gmail
    private static final String EMAIL_FROM = "*seu email*"; // Email do remetente
    private static final String EMAIL_PASSWORD = "*sua senha de app*"; //se colocar a senha da pau, precisa ir no "https://myaccount.google.com/intro/profile?hl=pt-BR&pli=1" e "pesquisar senha de app" lá você coloca um código que funciona como senha

    public static void enviaConfirmacaoDoEmail(String toEmail) {  //toEmail é o e-mail do usuário
        Properties props = new Properties();    //objeto que recebe as configurações do servidor do google
        props.put("mail.smtp.auth", "true");    //habilita a autenticação
        props.put("mail.smtp.starttls.enable", "true"); //esse tls meio que criptografa a mensagem quando ela é enviada pra dar mais segurança
        props.put("mail.smtp.host", SMTP_HOST);     //define o host, no caso o google
        props.put("mail.smtp.port", SMTP_PORT);     //define a porta que também é do google

        Session session = Session.getInstance(props, new Authenticator() {  //isso tudo vai fazer a autenticação do e-mail do remetente antes de enviar o e-mail para o usuário
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_FROM, EMAIL_PASSWORD); //retona a autenticação do email e senha do remetente, que ficam na linha 12 e 13;
            }
        });

        try {
            Message mensagem = new MimeMessage(session); //cria uma nova mensagem
            mensagem.setFrom(new InternetAddress(EMAIL_FROM));   //define o remetente
            mensagem.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));    //define o destinatário que recebe a mensagem
            mensagem.setSubject("Confirmação de E-mail");    //título da mensagem
            mensagem.setText("Obrigado pela compra!\n" +
                    "name: \n" +
                    "location: \n" +
                    "description: \n" +
                    "price: \n" +
                    "capacity: \n"); //a mensagem dentro do e-mail

            Transport.send(mensagem);    //envia a mensagem;
            System.out.println("E-mail enviado com sucesso!");  //debug caso tenha sido enviado a mensagem
        } catch (MessagingException e) {
            throw new RuntimeException("Falha ao enviar e-mail", e);    //exceções caso tenha dado pau
        }
    }

    public static boolean isEmailConfirmed() {
        //aqui é como a verificação vai ser feita, ou seja balaban
        return true; //se der certo vem aqui
    }
}