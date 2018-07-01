package services;

import model.User;
        import org.apache.commons.mail.DefaultAuthenticator;
        import org.apache.commons.mail.EmailException;
        import org.apache.commons.mail.SimpleEmail;

public class MailService {
    private static MailService instance = null;
    private SimpleEmail mail = new SimpleEmail();

    public MailService() throws EmailException {
        mailConfiguration();

    }

    public void sendMail(User clientdestination, String description) throws EmailException {
        mail.setSubject("Email de prueba");
        mail.setMsg("Probando:" + description);
        mail.addTo(clientdestination.getEmail());
        mail.send();
    }

    private void mailConfiguration() throws EmailException {
        mail.setHostName("smtp.gmail.com");
        mail.setSmtpPort(465);
        mail.setAuthenticator(new DefaultAuthenticator("carpnd.grupok@gmail.com", "facil666"));
        mail.setSSLOnConnect(true);
        mail.setFrom("carpnd.grupok@gmail.com");
    }

    public MailService(SimpleEmail email) throws EmailException {
        this.mail = email;
        mailConfiguration();
    }

    public MailService getInstance() throws EmailException {
        if (instance == null) {
            instance = new MailService();
        }
        return instance;
    }
}