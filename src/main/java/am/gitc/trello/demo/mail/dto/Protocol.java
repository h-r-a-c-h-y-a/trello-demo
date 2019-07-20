package am.gitc.trello.demo.mail.dto;

public enum Protocol {

    POP3("pop3", "pop.gmail.com", 995),
    IMAP("imap", "imap.gmail.com", 993);

    private String protocol;
    private String host;
    private int port;

    public String getProtocol() {
        return protocol;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public static final String USER_NAME = "hro070890@gmail.com";
    public static final String PASSWORD = "password";

    Protocol(String protocol, String host, int port) {
        this.protocol = protocol;
        this.host = host;
        this.port = port;
    }
}
