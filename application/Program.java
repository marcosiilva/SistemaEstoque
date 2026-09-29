package exerciciosArrays_Vetores.SistemaEstoque.application;

import exerciciosArrays_Vetores.SistemaEstoque.entities.Produto;

import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        Produto[] produtos = new Produto[5];
        int opcao = 0;

        while (opcao != 7) {

            System.out.println("\n===== SISTEMA DE ESTOQUE =====");
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Listar produtos");
            System.out.println("3 - Adicionar estoque");
            System.out.println("4 - Remover estoque");
            System.out.println("5 - Consultar produto");
            System.out.println("6 - Mostrar valor total do estoque");
            System.out.println("7 - Sair");
            System.out.print("Opção: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {

                case 1:
                    System.out.println("\n===== CADASTRAR PRODUTO =====");

                    int posicaoLivre = -1;

                    // Procura a primeira posição vazia
                    for (int i = 0; i < produtos.length; i++) {
                        if (produtos[i] == null) {
                            posicaoLivre = i;
                            break;
                        }
                    }

                    // Verifica se existe espaço
                    if (posicaoLivre == -1) {
                        System.out.println("Estoque cheio! Não é possível cadastrar mais de 5 produtos.");
                        break;
                    }

                    System.out.print("Nome: ");
                    String nome = sc.nextLine();

                    double preco;

                    do {
                        System.out.print("Preço (não pode ser negativo): ");
                        preco = sc.nextDouble();

                        if (preco < 0) {
                            System.out.println("Erro: Preço inválido!");
                        }

                    } while (preco < 0);

                    int quantidade;

                    do {
                        System.out.print("Quantidade (não pode ser negativa): ");
                        quantidade = sc.nextInt();

                        if (quantidade < 0) {
                            System.out.println("Erro: Quantidade inválida!");
                        }

                    } while (quantidade < 0);

                    sc.nextLine();

                    produtos[posicaoLivre] = new Produto(nome, preco, quantidade);

                    System.out.println("Produto cadastrado com sucesso!");

                    break;

                case 2:
                    System.out.println("\n===== LISTAR PRODUTOS =====");

                    boolean temProdutos = false;

                    for (int i = 0; i < produtos.length; i++) {

                        if (produtos[i] != null) {
                            System.out.println(produtos[i]);
                            temProdutos = true;
                        }
                    }

                    if (!temProdutos) {
                        System.out.println("Nenhum produto cadastrado no momento.");
                    }

                    break;

                case 3:
                    System.out.println("\n===== ADICIONAR ESTOQUE =====");

                    System.out.print("Digite o nome do produto: ");
                    String nomeAdd = sc.nextLine();

                    Produto prodAdd = buscarProduto(produtos, nomeAdd);

                    if (prodAdd != null) {

                        System.out.print("Digite a quantidade a adicionar: ");

                        int qtdAdd = sc.nextInt();
                        sc.nextLine();

                        if (qtdAdd > 0) {

                            prodAdd.adicionarEstoque(qtdAdd);

                            System.out.println("Estoque atualizado com sucesso!");

                        } else {

                            System.out.println("Erro: A quantidade deve ser maior que zero.");
                        }

                    } else {

                        System.out.println("Produto não encontrado.");
                    }

                    break;

                case 4:
                    System.out.println("\n===== REMOVER ESTOQUE =====");

                    System.out.print("Digite o nome do produto: ");
                    String nomeRem = sc.nextLine();

                    Produto prodRem = buscarProduto(produtos, nomeRem);

                    if (prodRem != null) {

                        System.out.print("Digite a quantidade a remover: ");

                        int qtdRem = sc.nextInt();
                        sc.nextLine();

                        if (qtdRem <= 0) {

                            System.out.println("Erro: A quantidade deve ser maior que zero.");

                        } else if (qtdRem > prodRem.getQuantidade()) {

                            System.out.println("Erro: Remoção maior que o estoque existente!");

                        } else {

                            prodRem.removerEstoque(qtdRem);

                            System.out.println("Estoque atualizado com sucesso!");
                        }

                    } else {

                        System.out.println("Produto não encontrado.");
                    }

                    break;

                case 5:
                    System.out.println("\n===== CONSULTAR PRODUTO =====");

                    System.out.print("Digite o nome do produto: ");
                    String nomeCons = sc.nextLine();

                    Produto prodCons = buscarProduto(produtos, nomeCons);

                    if (prodCons != null) {

                        System.out.println(prodCons);

                    } else {

                        System.out.println("Produto não encontrado.");
                    }

                    break;

                case 6:
                    System.out.println("\n===== VALOR TOTAL DO ESTOQUE =====");

                    double valorTotalGeral = 0.0;

                    for (int i = 0; i < produtos.length; i++) {

                        if (produtos[i] != null) {

                            valorTotalGeral += produtos[i].valorTotalEmEstoque();
                        }
                    }

                    System.out.printf("Valor total de todos os produtos no estoque: R$ %.2f%n", valorTotalGeral);

                    break;

                case 7:
                    System.out.println("Encerrando o sistema...");
                    break;

                default:
                    System.out.println("Opção inválida! Escolha entre 1 e 7.");
            }
        }

        sc.close();
    }

    // Metodo auxiliar para buscar produto por nome
    public static Produto buscarProduto(Produto[] vetor, String nome) {

        for (int i = 0; i < vetor.length; i++) {

            if (vetor[i] != null && vetor[i].getNome().equalsIgnoreCase(nome)) {
                return vetor[i];
            }
        }

        return null;
    }
}