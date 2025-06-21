package dao;

import java.util.ArrayList;
import java.util.List;
import model.Paciente;

public class PacienteDao {

    public void salvarPaciente(Paciente paciente) {
        if(paciente.getId() == 0){
            paciente.setId(FileHandler2.getNextId("Paciente"));
        }

        String insertSQL = String.format(
            "INSERT INTO Paciente (id, nome, email, senha, data_nascimento, nome_social, medicacao, doenca, tipo_sanguineo) VALUES (%d, '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');",
            paciente.getId(),
            paciente.getNome(),
            paciente.getDataNascimento(),
            paciente.getNomeSocial(),
            paciente.getMedicacao(),
            paciente.getDoenca(),
            paciente.getTipoSanguineo()
        );

        FileHandler2.writeInsertStatement(insertSQL);
    }

    public Paciente autenticarPaciente(String email, String senha){
        List<String> lines = FileHandler2.readLines();
        for(String line : lines){
            if(line.startsWith("INSERT INTO Paciente")){
                String storedEmail = FileHandler2.getValueFromInsert(line, "email");
                String storedSenha = FileHandler2.getValueFromInsert(line, "senha");

                if(email.equals(storedEmail) && senha.equals(storedSenha)){
                    int id = Integer.parseInt(FileHandler2.getValueFromInsert(line, "id"));
                    String nome = FileHandler2.getValueFromInsert(line, "nome");
                    return new Paciente(id, nome, storedEmail, storedSenha);
                }
            }
        }
        return null;
    }
    
    public Paciente buscarPorId(int id) {
        List<String> lines = FileHandler2.readLines();
        for (String line : lines) {
            if (line.startsWith("INSERT INTO Paciente")) {
                String idStr = FileHandler2.getValueFromInsert(line, "id");
                if (idStr != null && Integer.parseInt(idStr) == id) {
                    String nome = FileHandler2.getValueFromInsert(line, "nome");
                    String email = FileHandler2.getValueFromInsert(line, "email");
                    String senha = FileHandler2.getValueFromInsert(line, "senha"); 
                    return new Paciente(id, nome, email, senha);
                }
            }
        }
        return null;
    }

    public Paciente buscarPorEmail(String email) {
        List<String> linhas = FileHandler2.readLines();
        for (String linha : linhas) {
            if (linha.startsWith("INSERT INTO Paciente")) {
                String storedEmail = FileHandler2.getValueFromInsert(linha, "email");
                if (storedEmail != null && storedEmail.equals(email)) {
                    int id = Integer.parseInt(FileHandler2.getValueFromInsert(linha, "id"));
                    String nome = FileHandler2.getValueFromInsert(linha, "nome");
                    String senha = FileHandler2.getValueFromInsert(linha, "senha");
                    return new Paciente(id, nome, storedEmail, senha);
                }
            }
        }
        return null;
    }

    public List<Paciente> listarTodos() {
        List<String> linhas = FileHandler2.readLines();
        List<Paciente> pacientes = new ArrayList<>();

        for (String linha : linhas) {
            if (linha.startsWith("INSERT INTO Paciente")) {
                Paciente p = new Paciente();

                p.setId(Integer.parseInt(FileHandler2.getValueFromInsert(linha, "id")));
                p.setNome(FileHandler2.getValueFromInsert(linha, "nome"));
                p.setDataNascimento(FileHandler2.getValueFromInsert(linha, "data_nascimento"));
                p.setNomeSocial(FileHandler2.getValueFromInsert(linha, "nome_social"));
                p.setMedicacao(FileHandler2.getValueFromInsert(linha, "medicacao"));
                p.setDoenca(FileHandler2.getValueFromInsert(linha, "doenca"));
                p.setTipoSanguineo(FileHandler2.getValueFromInsert(linha, "tipo_sanguineo"));

                pacientes.add(p);
            }
        }

        return pacientes;
    }
}
