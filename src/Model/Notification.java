package Model;

public class Notification {
    private int line;
    private String content;

    public Notification(int line, String content) {
        this.line = line;
        this.content = content;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
