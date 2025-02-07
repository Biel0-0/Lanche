
import java.io.File;

public class Produto {
    private int codigo;
    private double preco;
    private String localImagem;

    public Produto(int codigo, double preco, String localImagem) {
        this.codigo = codigo;
        this.preco = preco;
        this.localImagem = localImagem;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getLocalImagem() {
        return localImagem;
    }

    public void setLocalImagem(String localImagem) {
        this.localImagem = localImagem;
    }
    
    public boolean localImagem(){
        File imagem = new File(localImagem); 
        return imagem.exists();
    }
}
