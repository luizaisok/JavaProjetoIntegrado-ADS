package model;

public class Conteudo {
    private int id;
    private String titulo;
    private String descricao;
    private String texto;
    private String data;

    public Conteudo(){}

    public Conteudo(int id, String titulo, String descricao, String texto, String data){
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.texto = texto;
        this.data = data;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTexto() {
        return this.texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String toStringSql(){
        return "INSERT INTO conteudo (id, titulo, descricao, texto, data) VALUES ('"+this.id+"', '"+this.titulo+"','"+this.descricao+"','"+this.texto+"','"+this.data+"')";
    }
}
