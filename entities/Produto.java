package exerciciosArrays_Vetores.SistemaEstoque.entities;

public class Produto {

    private String nome;
    private double preco;
    private int quantidade;

    // Construtores com sobrecarga e uso de this()
    public Produto(String nome, double preco) {
        this.nome = nome;
        setPreco(preco); // Aplica a validação de preço
    }

    public Produto(String nome, double preco, int quantidade) {
        this(nome, preco);
        setQuantidade(quantidade); // Aplica a validação de quantidade
    }

    // Getters e Setters com validações
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if (preco >= 0) {
            this.preco = preco;
        }
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade >= 0) {
            this.quantidade = quantidade;
        }
    }

    // Métodos solicitados
    public void adicionarEstoque(int quantidade) {
        if (quantidade > 0) {
            this.quantidade += quantidade;
        }
    }

    public void removerEstoque(int quantidade) {
        if (quantidade > 0 && quantidade <= this.quantidade) {
            this.quantidade -= quantidade;
        }
    }

    public double valorTotalEmEstoque() {
        return preco * quantidade;
    }

    @Override
    public String toString() {
        return "Produto: " + nome
                + ", Preço: $ " + String.format("%.2f", preco)
                + ", Quantidade: " + quantidade
                + ", Total: $ " + String.format("%.2f", valorTotalEmEstoque());
    }
}