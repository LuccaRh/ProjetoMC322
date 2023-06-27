package Projeto;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import javax.swing.table.DefaultTableModel;

public class GuiaComAbas extends JFrame{
    private static int id = 0;
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
        JTextField attNomeTextField = new JTextField(10);
        JTextField attMacaTextField = new JTextField("0", 10);
        JTextField attBananaTextField = new JTextField("0", 10);
        JTextField attUvaTextField = new JTextField("0", 10);
        JButton atualizarButton = new JButton("Atualizar");
        
        inputPanel.add(new JLabel("Nome:"));
        inputPanel.add(nomeTextField);
        inputPanel.add(new JLabel("Maçã(kg):"));
        inputPanel.add(macaTextField);
        inputPanel.add(new JLabel("Banana(kg):"));
        inputPanel.add(bananaTextField);
        inputPanel.add(new JLabel("Uva(kg):"));
        inputPanel.add(uvaTextField);

        attPanel.add(new JLabel("ID:"));
        attPanel.add(attIdTextField);
        attPanel.add(new JLabel("Nome:"));
        attPanel.add(attNomeTextField);
        attPanel.add(new JLabel("Maçã(kg):"));
        attPanel.add(attMacaTextField);
        attPanel.add(new JLabel("Banana(kg):"));
        attPanel.add(attBananaTextField);
        attPanel.add(new JLabel("Uva(kg):"));
        attPanel.add(attUvaTextField);
        
        adicionarPanel.setLayout(new BoxLayout(adicionarPanel, BoxLayout.Y_AXIS));
        adicionarPanel.add(inputPanel);
        adicionarPanel.add(confirmarButton);
        
        atualizarPanel.setLayout(new BoxLayout(atualizarPanel, BoxLayout.Y_AXIS));
        atualizarPanel.add(attPanel);
        atualizarPanel.add(atualizarButton);
        
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

                adicionarRegistroCSV(nome, maca, banana, uva);

                // Limpar os campos de texto após adicionar o registro
                nomeTextField.setText("");
                macaTextField.setText("");
                bananaTextField.setText("");
                uvaTextField.setText("");

                // Atualizar a tabela após adicionar o registro
                DefaultTableModel tableModel = (DefaultTableModel) tabela.getModel();
                tableModel.addRow(new Object[]{nome, maca, banana, uva});
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
            String novoRegistro = id++ + "," 
                                + nome + "," 
                                + Double.parseDouble(maca)*TabelaValores.MACA.getValor() + "," 
                                + Double.parseDouble(banana)*TabelaValores.BANANA.getValor() + "," 
                                + Double.parseDouble(uva)*TabelaValores.UVA.getValor();
            writer.write(novoRegistro);
            writer.newLine();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
}

