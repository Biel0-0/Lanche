
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CadastrarProduto {

    public static void main(String[] args) {
        List<Produto> produtos = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        
        
        String desktopPath = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "imagens_salvas" + File.separator;

        
        File dir = new File(desktopPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        System.out.println("=== Cadastro de Produtos ===");
        while (true) {
            System.out.println("Digite o codigo do produto (ou 0 para sair):");
            int codigo = scanner.nextInt();
            if (codigo == 0) break;

            System.out.println("Digite o preco do produto:");
            double preco = scanner.nextDouble();

            System.out.println("Digite o caminho da imagem do produto:");
            String caminhoImagemOriginal = scanner.next();

           
            File imagemOriginal = new File(caminhoImagemOriginal);

            if (imagemOriginal.exists()) {
                
                String novoCaminhoImagem = desktopPath + imagemOriginal.getName();

                try {
                    
                    Files.move(Paths.get(caminhoImagemOriginal), Paths.get(novoCaminhoImagem), StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Imagem movida para: " + novoCaminhoImagem);

                    
                    Produto produto = new Produto(codigo, preco, novoCaminhoImagem);
                    produtos.add(produto);
                    System.out.println("Produto cadastrado com sucesso!");

                } catch (IOException e) {
                    System.out.println("Erro ao mover a imagem: " + e.getMessage());
                }
            } else {
                System.out.println("Imagem nao encontrada. Cadastro nao realizado.");
            }
        }

        
        System.out.println("\n=== Produtos cadastrados ===");
        for (Produto p : produtos) {
            System.out.println("Codigo: " + p.getCodigo() + ", Preco: R$ " + p.getPreco() + ", Imagem: " + p.getLocalImagem());
        }

       
        System.out.println("\n=== Realizar Pedido ===");
        System.out.println("Digite o codigo do produto e a quantidade:");

        int codigoPedido = scanner.nextInt();
        int quantidade = scanner.nextInt();

        
        Produto produtoSelecionado = null;
        for (Produto p : produtos) {
            if (p.getCodigo() == codigoPedido) {
                produtoSelecionado = p;
                break;
            }
        }

        if (produtoSelecionado != null) {
            double total = produtoSelecionado.getPreco() * quantidade;
            System.out.printf("Total: R$ %.2f\n", total);
        } else {
            System.out.println("Produto nao encontrado.");
        }

        scanner.close();
    }
}
