package Projeto;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import javax.swing.table.DefaultTableModel;
import java.util.Random;

public class GuiaComAbas extends JFrame{
	Random random = new Random();
    private int id;
	public String path;
    public GuiaComAbas(String path){
        super("Exemplo de Abas");
        this.path = path;
        // Cria o painel de abas
        JTabbedPane tabbedPane = new JTabbedPane();

        // Cria as abas
        JPanel listagemPanel = new JPanel();
        JPanel adicionarPanel = new JPanel();
        JPanel atualizarPanel = new JPanel();
        JPanel removerPanel = new JPanel();
        
        // Adiciona as abas no painel de abas
        tabbedPane.addTab("Listagem", listagemPanel);
        tabbedPane.addTab("Adicionar", adicionarPanel);
        tabbedPane.addTab("Atualizar", atualizarPanel);
        tabbedPane.addTab("Remover", removerPanel);

        // Adiciona o painel de abas no frame principal
        getContentPane().add(tabbedPane);

        // Define o tamanho e a visibilidade do frame
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        //Adicionar elemento no arquivo
        JPanel inputPanel = new JPanel();
        JTextField nomeTextField = new JTextField(10);
        JTextField macaTextField = new JTextField("0", 10);
        JTextField bananaTextField = new JTextField("0", 10);
        JTextField uvaTextField = new JTextField("0", 10);
        JButton confirmarButton = new JButton("Confirmar");

        //Atualizar elemento no arquivo
        JPanel attPanel = new JPanel();
        JTextField attIdTextField = new JTextField(10);
        JTextField attMacaTextField = new JTextField("0", 10);
        JTextField attBananaTextField = new JTextField("0", 10);
        JTextField attUvaTextField = new JTextField("0", 10);
        JButton atualizarButton = new JButton("Atualizar");
        
        JPanel remPanel = new JPanel();
        JTextField remIdTextField = new JTextField("0", 10);
        JButton removerButton = new JButton("Remover");
        
        inputPanel.add(new JLabel("Nome:"));
        inputPanel.add(nomeTextField);
        inputPanel.add(new JLabel("Maçã(kg):"));
        inputPanel.add(macaTextField);
        inputPanel.add(new JLabel("Banana(kg):"));
        inputPanel.add(bananaTextField);
        inputPanel.add(new JLabel("Uva(kg):"));
        inputPanel.add(uvaTextField);
        nomeTextField.setText("");
        macaTextField.setText("");
        bananaTextField.setText("");
        uvaTextField.setText("");

        attPanel.add(new JLabel("ID:"));
        attPanel.add(attIdTextField);
        attPanel.add(new JLabel("Maçã(kg):"));
        attPanel.add(attMacaTextField);
        attPanel.add(new JLabel("Banana(kg):"));
        attPanel.add(attBananaTextField);
        attPanel.add(new JLabel("Uva(kg):"));
        attPanel.add(attUvaTextField);
        attIdTextField.setText("");
        attMacaTextField.setText("");
        attBananaTextField.setText("");
        attUvaTextField.setText("");
        
        remPanel.add(new JLabel("ID:"));
        remPanel.add(remIdTextField);
        remIdTextField.setText("");
        
        adicionarPanel.setLayout(new BoxLayout(adicionarPanel, BoxLayout.Y_AXIS));
        adicionarPanel.add(inputPanel);
        adicionarPanel.add(confirmarButton);
        
        atualizarPanel.setLayout(new BoxLayout(atualizarPanel, BoxLayout.Y_AXIS));
        atualizarPanel.add(attPanel);
        atualizarPanel.add(atualizarButton);
        
        removerPanel.setLayout(new BoxLayout(removerPanel, BoxLayout.Y_AXIS));
        removerPanel.add(remPanel);
        removerPanel.add(removerButton);
        
        //Tabela
        JTable tabela = carregarDadosCSV();
        JScrollPane scrollPane = new JScrollPane(tabela);
        listagemPanel.add(scrollPane);
    
        //Botão de confirmar
        confirmarButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                String nome = nomeTextField.getText();
                String maca = macaTextField.getText();
                String banana = bananaTextField.getText();
                String uva = uvaTextField.getText();
                
                String caminhoArquivo = path + "projetoMC322/Projeto/src/Projeto/Nome.csv";
                
                try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
                    String linha;
                    while ((linha = reader.readLine()) != null) {
                        String[] dados = linha.split(",");
                        String nomeExistente = dados[1]; // Índice da coluna de nomes

                        if (nomeExistente.equals(nome)) {
                            throw new IOException("O nome já está sendo usado.");
                        }
                    }
                }catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "Nome duplicado", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                adicionarRegistroCSV(nome, maca, banana, uva);
                
                double macad = Double.parseDouble(maca)*TabelaValores.MACA.getValor();
                double bananad = Double.parseDouble(banana)*TabelaValores.BANANA.getValor(); 
                double uvad = Double.parseDouble(uva)*TabelaValores.UVA.getValor();
                
                // Limpar os campos de texto após adicionar o registro
                nomeTextField.setText("");
                macaTextField.setText("");
                bananaTextField.setText("");
                uvaTextField.setText("");

                // Atualizar a tabela após adicionar o registro
                DefaultTableModel tableModel = (DefaultTableModel) tabela.getModel();
                tableModel.addRow(new Object[]{id, nome, macad, bananad, uvad}); 
            }
        });
        atualizarButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String id = attIdTextField.getText();
                String maca = attMacaTextField.getText();
                String banana = attBananaTextField.getText();
                String uva = attUvaTextField.getText();
                String caminhoArquivo = path + "projetoMC322/Projeto/src/Projeto/Nome.csv";
                
                try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
                    String linha;
                    boolean idverificar = false;
                    while ((linha = reader.readLine()) != null) {
                        String[] dados = linha.split(",");
                        String idEncontrado = dados[0]; // Índice da coluna de nomes

                        if (idEncontrado.equals(id)) {
                            idverificar = true;
                        }
                    }
                    if (!idverificar) {
                    	throw new IOException("Id não encontrado.");
                    }
                }catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "ID não encontrado. Por favor, verifique o ID digitado.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                atualizarRegistroCSV(tabela, id, maca, banana, uva);
                
                // Limpar os campos de texto após adicionar o registro
                attIdTextField.setText("");
                attMacaTextField.setText("");
                attBananaTextField.setText("");
                attUvaTextField.setText("");
                
             
            }
        });
        
        removerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = remIdTextField.getText();
                
                String caminhoArquivo = path + "projetoMC322/Projeto/src/Projeto/Nome.csv";
                
                try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
                    String linha;
                    boolean idverificar = false;
                    while ((linha = reader.readLine()) != null) {
                        String[] dados = linha.split(",");
                        String idEncontrado = dados[0]; // Índice da coluna de nomes

                        if (idEncontrado.equals(id)) {
                            idverificar = true;
                        }
                    }
                    if (!idverificar) {
                    	throw new IOException("Id não encontrado.");
                    }
                }catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "ID não encontrado. Por favor, verifique o ID digitado.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                try {
                    removerRegistroCSV(tabela, id); // Chame o método para remover o registro da tabela
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao remover registro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                remIdTextField.setText(""); // Limpar o campo de texto do ID após a remoção
                
                // Atualizar a tabela após a remoção
                DefaultTableModel tableModel = (DefaultTableModel) tabela.getModel();
                tableModel.fireTableDataChanged(); // Notifique a tabela sobre a alteração nos dados
            }
        });
    }
    
    private JTable carregarDadosCSV(){
        String caminhoArquivo = this.path + "projetoMC322/Projeto/src/Projeto/Nome.csv";
        String[] colunas = {"ID", "Nome", "Maca", "Banana", "Uva"};
        DefaultTableModel tableModel = new DefaultTableModel(colunas, 0);
        
        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                tableModel.addRow(dados);
                id = Integer.parseInt(dados[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new JTable(tableModel);
    }
    
    private void adicionarRegistroCSV(String nome, String maca, String banana, String uva){
        String caminhoArquivo = this.path + "projetoMC322/Projeto/src/Projeto/Nome.csv";
        

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo, true))){
        	int numeroInteiro = random.nextInt(100000000);
        	id = numeroInteiro;
            String novoRegistro = id + "," 
                                + nome + "," 
                                + Double.parseDouble(maca)*TabelaValores.MACA.getValor() + "," 
                                + Double.parseDouble(banana)*TabelaValores.BANANA.getValor() + "," 
                                + Double.parseDouble(uva)*TabelaValores.UVA.getValor();
            writer.write(novoRegistro);
            writer.newLine();
            JOptionPane.showMessageDialog(null, "Usuário adicionado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }catch(IOException e){
            e.printStackTrace();
            return;
        }
    }

    private void atualizarRegistroCSV(JTable tabela, String stringId, String maca, String banana, String uva) {
        String caminhoArquivo = this.path + "projetoMC322/Projeto/src/Projeto/Nome.csv";
        double doubleMaca = 0.0, doubleBanana = 0.0, doubleUva = 0.0;
        boolean idEncontrado = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            int row = 0;
            
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (Objects.equals(stringId, dados[0])) { // Procura o ID
                  
                    if (!maca.isEmpty()) {
                        doubleMaca = Double.parseDouble(maca) * TabelaValores.MACA.getValor();
                        tabela.setValueAt(doubleMaca + "", row, 2); // Atualiza a maçã
                    }
                    if (!banana.isEmpty()) {
                        doubleBanana = Double.parseDouble(banana) * TabelaValores.BANANA.getValor();
                        tabela.setValueAt(doubleBanana + "", row, 3); // Atualiza a banana
                    }
                    if (!uva.isEmpty()) {
                        doubleUva = Double.parseDouble(uva) * TabelaValores.UVA.getValor();
                        tabela.setValueAt(doubleUva + "", row, 4); // Atualiza a uva
                    }
                    break;
                }
                row++;
            }
            JOptionPane.showMessageDialog(null, "Valores atualizados com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
      
    }
    private void removerRegistroCSV(JTable tabela, String id) throws IOException {
        String caminhoArquivo = this.path + "projetoMC322/Projeto/src/Projeto/Nome.csv";
        DefaultTableModel tableModel = (DefaultTableModel) tabela.getModel();
        int rowCount = tableModel.getRowCount();
        
        for (int i = 0; i < rowCount; i++) {
            String rowId = tableModel.getValueAt(i, 0).toString();
            
            if (rowId.equals(id)) {
                tableModel.removeRow(i);
                break;
            }
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo + ".tmp"))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    String[] dados = linha.split(",");
                    String linhaId = dados[0]; // Índice da coluna de ID

                    if (!linhaId.equals(id)) {
                        writer.write(linha);
                        writer.newLine();
                    }
                }
            }
        }
        
        // Remova o arquivo original e renomeie o arquivo temporário para o nome original
        File arquivoOriginal = new File(caminhoArquivo);
        File arquivoTemporario = new File(caminhoArquivo + ".tmp");
        if (arquivoOriginal.exists()) {
            arquivoOriginal.delete();
        }
        arquivoTemporario.renameTo(arquivoOriginal);
        
        JOptionPane.showMessageDialog(null, "Usuário removido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
}
