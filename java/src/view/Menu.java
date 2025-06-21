package view;

import dao.FileHandler;
import java.time.LocalDate;
import java.util.Scanner;
import model.Administrador;
import model.Conteudo;
import model.Paciente;

public class Menu {
    public void mostrarMenu(Scanner sc, FileHandler arquivo){
        String opcao;

        do {
            System.out.println("Se você deseja adicionar um conteúdo digite 'c', se deseja adicionar um usuário digite 'u', mas se deseja encerrar digite 'sair'");
            opcao = sc.nextLine();

            switch (opcao.toLowerCase()) {
                case "c":
                    Conteudo conteudo = new Conteudo();
                    System.out.println("Digite o título do conteúdo:");
                    conteudo.setTitulo(sc.nextLine());

                    System.out.println("Digite a descrição do conteúdo:");
                    conteudo.setDescricao(sc.nextLine());

                    System.out.println("Digite o texto do conteúdo:");
                    conteudo.setTexto(sc.nextLine());

                    conteudo.setData(LocalDate.now().toString());

                    arquivo.writeInsertStatement(conteudo.toStringSql());
                    System.out.println("Conteúdo criado com sucesso!");
                    break;
                case "u":
                    System.out.print("Digite um email: ");
                    String email = sc.nextLine();

                    System.out.print("Digite seu nome: ");
                    String nome = sc.nextLine();

                    System.out.print("Digite seu nome social ou 'N' caso não possua: ");
                    String nomeSocial = sc.nextLine();

                    System.out.print("Digite sua senha: ");
                    String senha = sc.nextLine();

                    System.out.print("Informe sua data de nascimento (AAAA-MM-DD): ");
                    String dataNascimento = sc.nextLine();

                    System.out.print("Informe seu gênero ('Feminino', 'Masculino' ou 'Outros'): ");
                    String genero = sc.nextLine();

                    if (!genero.equalsIgnoreCase("Feminino") &&
                        !genero.equalsIgnoreCase("Masculino") &&
                        !genero.equalsIgnoreCase("Outros")) {
                        System.err.println("Informação inválida de gênero. Pulando cadastro.");
                        continue;
                    }

                    System.out.print("Essa pessoa é um administrador ou um paciente? (a/p): ");
                    String resposta = sc.nextLine();

                    if (resposta.equalsIgnoreCase("a")) {
                        Administrador adm = new Administrador();
                        adm.setNome(nome);
                        adm.setEmail(email);
                        adm.setNomeSocial(nomeSocial);
                        adm.setSenha(senha);
                        adm.setDataNascimento(dataNascimento);
                        adm.setGenero(genero);

                        System.out.print("Digite o documento: ");
                        adm.setDocumento(sc.nextLine());

                        System.out.print("Digite a formação: ");
                        adm.setFormacao(sc.nextLine());

                        System.out.print("Digite a especialidade: ");
                        adm.setEspecialidade(sc.nextLine());

                        adm.registraLogin();
                        arquivo.writeInsertStatement(adm.toStringSql());
                    } else if(resposta.equalsIgnoreCase("p")){
                        Paciente pac = new Paciente();
                        pac.setNome(nome);
                        pac.setEmail(email);
                        pac.setNomeSocial(nomeSocial);
                        pac.setSenha(senha);
                        pac.setDataNascimento(dataNascimento);
                        pac.setGenero(genero);

                        System.out.println("Digite as medicações: ");
                        pac.setMedicacao(sc.nextLine());

                        System.out.println("Digite as doenças/condições/alergias: ");
                        pac.setDoenca(sc.nextLine());

                        System.out.println("Digite seu tipo sanguíneo: ");
                        pac.setTipoSanguineo(sc.nextLine());

                        arquivo.writeInsertStatement(pac.toStringSql());
                    }else{
                        System.err.println("Informe uma opção válida!");
                    }
                    break;
                case "sair":
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (!opcao.equalsIgnoreCase("sair"));
    }
}