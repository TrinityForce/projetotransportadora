package br.com.bomtransporte.formulario;

import br.com.bomtransporte.dao.CargaDao;
import br.com.bomtransporte.util.JTFSomenteNumeros;
import br.com.bomtransporte.dao.ClienteDao;
import br.com.bomtransporte.dao.EnderecoDao;
import br.com.bomtransporte.dao.PedidoDao;
import br.com.bomtransporte.dao.PrecoDistanciaDao;
import br.com.bomtransporte.dao.PrecoPesoDao;
import br.com.bomtransporte.modelo.Caminhao;
import br.com.bomtransporte.modelo.Carga;
import br.com.bomtransporte.modelo.Cliente;
import br.com.bomtransporte.modelo.Endereco;
import br.com.bomtransporte.modelo.FuncionarioSingleton;
import br.com.bomtransporte.modelo.ModeloTabela;
import br.com.bomtransporte.modelo.Pedido;
import br.com.bomtransporte.modelo.PrecoDistancia;
import br.com.bomtransporte.modelo.PrecoPeso;
import br.com.bomtransporte.regrasnegocio.FuncionarioRN;
import br.com.bomtransporte.util.Calc;
import br.com.bomtransporte.util.Datas;
import br.com.bomtransporte.util.Tela;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Gustavo Carvalho <gustavo.carvalho.costa@outlook.com>
 */
public class FormCadastrarCarga extends javax.swing.JFrame {

    private ClienteDao clienteDao;
    private EnderecoDao enderecoDao;
    private Integer idEndereco, idPedido, idCliente, idPedido_CliSelecionado;
    private String[] idPrecoDistancia;
    private Tela t;
    private CargaDao cargaDao;
    private Carga cargaSelecionada;
    private PedidoDao pedidoDao;
    private Pedido pedido;
    private PrecoDistancia precoDistancia;

    /**
     * Creates new form FormCadCliente
     */
    public FormCadastrarCarga() {
        initComponents();
        idPedido = FormClientePedido.idPedido_CliSelecionado;
        idCliente = FormClientePedido.idCliente;
        idPedido_CliSelecionado = FormClientePedido.idPedido_CliSelecionado;
        preencherCampos();
        preencherComboPreco();
        preencherComboPeso();
        desabilitarBotao(jBT_AdicionarCarga);
        verificarAba();
        desabilitarCampos(jTF_Bairro, jTF_NomeCidade, jTF_Logradouro, jTF_Uf);
        jTB_Pedido.setEnabledAt(1, false);
        jLB_ErroCep.setVisible(false);
    }

    private void verificarAba() {

        if ((FormClientePedido.ativarAba != null)
                && (FormClientePedido.ativarAba == 2)) {
            jTB_Pedido.setEnabledAt(1, true);
            jTB_Pedido.setEnabledAt(0, false);
            jTB_Pedido.setSelectedIndex(1);

            desabilitarBotao(jBT_Salvar);
            habilitarBotao(jBT_AdicionarCarga);

            preencherTabela();
        }
    }

    private void preencherCampos() {
        try {
            clienteDao = new ClienteDao();
            Cliente cliente = clienteDao.consultarPorId(idCliente);

            if (cliente != null) {
                jTF_IdCliente.setText(String.valueOf(cliente.getIdCliente()));
                jTF_Cpf.setText(cliente.getCpf());
                jTF_NomeCliente.setText(cliente.getNome());
                jTF_IdCliente1.setText(String.valueOf(cliente.getIdCliente()));
                jTF_Cpf1.setText(cliente.getCpf());
                jTF_NomeCliente1.setText(cliente.getNome());

            } else {
                System.out.println("cliente n encontrado");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void preencherComboPeso() {
        PrecoPesoDao pesoDao = new PrecoPesoDao();
        try {
            jCB_Peso.removeAllItems();
            for (PrecoPeso peso : pesoDao.listarTodosAtivados()) {
                String str = peso.getIdPrecoPeso() + " - " + peso.getPeso() + "Kg  -R$" + peso.getValor();
                jCB_Peso.addItem(str.replace("/", " ate "));
                //PrecoDistancia pd = (PrecoDistancia) preco;
                // jCB_Peso.addItem(peso.getIdPrecoPeso() + " " + peso.getPeso() + "Kg  -R$" + peso.getValor());
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro Inesperado. Por favor tente novamente: " + ex.getMessage(), "ERRO INESPERADO", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace(System.err);
        }
    }

    private void preencherComboPreco() {
        PrecoDistanciaDao pdd = new PrecoDistanciaDao();
        try {
            jCB_Rotas.removeAllItems();
//            pdd.listarTodosAtivados().forEach((PrecoDistancia preco) -> {
            for (PrecoDistancia preco : pdd.listarTodosAtivados()) {
                //PrecoDistancia pd = (PrecoDistancia) preco;
                jCB_Rotas.addItem(preco.getIdPrecoDistancia() + " " + preco.getOrigemDestinoUf() + "-R$" + preco.getValor());
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro Inesperado. Por favor tente novamente: " + ex.getMessage(), "ERRO INESPERADO", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace(System.err);
        }
    }

    private boolean verificarCampo(String campo) {
        return campo != null && campo.trim().length() > 0;
    }

    private Double calcularDimensaoTotal(){
        Double largura, altura, profundidade;
        largura = Double.parseDouble(jTF_Largura.getText());
        altura = Double.parseDouble(jTF_Altura.getText());
        profundidade = Double.parseDouble(jTF_Profundidade.getText());
        Double totalCubagem = Calc.calcDimenCubica(altura, largura, profundidade);
        
        if(altura >= Caminhao.getAltura()){
            JOptionPane.showMessageDialog(this, "ALTURA DA CARGA EXCEDEU O LIMITE.", "ATENÇÃO!", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        else if(largura >= Caminhao.getLargura()){
            JOptionPane.showMessageDialog(this, "LARGURA DA CARGA EXCEDEU O LIMITE.", "ATENÇÃO!", JOptionPane.WARNING_MESSAGE);
            return null;
        }else if(profundidade >= Caminhao.getProfundidade()){
            JOptionPane.showMessageDialog(this, "PROFUNDIDADE DA CARGA EXCEDEU O LIMITE.", "ATENÇÃO!", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        return totalCubagem;
    }

    private boolean verificarCampo(Integer campo) {
        return campo != null;
    }

    private boolean verificarRadio(JRadioButton rb1, JRadioButton rb2) {
        return rb1.isSelected() == true || rb2.isSelected() == true;
    }

    //verifica se todos os campos da lista estao preenchidos e retorna true
    private boolean verificarCampos(List<String> list) {
        Boolean vazio = true;
        if (list != null && !list.isEmpty()) {
            for (Iterator<String> it = list.iterator(); it.hasNext();) {
                String campo = it.next();
                if (campo == null || campo.trim().length() == 0) {
                    vazio = false;
                    return vazio;
                }
            }
        } else {
            vazio = false;
        }
        return vazio;
    }

    private void limparCampos() {
        jTF_Descricao.setText("");
        jTF_NomeCliente.setText("");
        jTF_Quantidade.setText("1");
    }

    private void habilitarCampos() {
        jTF_Descricao.setEnabled(true);
        jTF_NomeCliente.setEnabled(true);
        jTF_Quantidade.setEnabled(true);
        jTF_Bairro.setEnabled(true);
        jTF_Cep.setEnabled(true);
        jTF_Complemento.setEnabled(true);
        jTF_Logradouro.setEnabled(true);
        jTF_Numero.setEnabled(true);
        jTF_Uf.setEnabled(true);

    }

    private void habilitarBotao(JButton bt) {
        bt.setEnabled(true);
    }

    private void desabilitarBotao(JButton bt) {
        bt.setEnabled(false);
    }

    private void desabilitarCampos() {
        jTF_Descricao.setEnabled(false);
        jTF_NomeCliente.setEnabled(false);
        jTF_Quantidade.setEnabled(false);
        jTF_Bairro.setEnabled(false);
        jTF_Cep.setEnabled(false);
        jTF_Complemento.setEnabled(false);
        jTF_Logradouro.setEnabled(false);
        jTF_Numero.setEnabled(false);
        jTF_Uf.setEnabled(false);
        jBT_ProximaTela.setEnabled(false);

    }

    private void desabilitarCampos(JTextField... jTF) {
        for (JTextField jTF1 : jTF) {
            jTF1.setEnabled(false);
        }
    }

    private void limparCamposCep() {
        jTF_Bairro.setText("");
        jTF_Logradouro.setText("");
        jTF_Uf.setText("");
    }

    private boolean verificarCep() {
        String cep = jTF_Cep.getText();
        EnderecoDao enderecoDao = new EnderecoDao();
        try {
            Endereco endereco = enderecoDao.retornarEndereco(cep);
            if (endereco != null) {
                jLB_ErroCep.setVisible(false);
                jTF_Bairro.setText(endereco.getBairro());
                jTF_Logradouro.setText(endereco.getLogradouro());
                jTF_Uf.setText(endereco.getUf());
                jTF_NomeCidade.setText(endereco.getNomeCidade());
                idEndereco = endereco.getId();
                return true;
            } else {
                jLB_ErroCep.setText("CEP Não Encontrado.");
                jLB_ErroCep.setVisible(true);
                limparCamposCep();
                return false;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Um erro ocorreu ao verificar o cep: " + ex.getMessage(), "ERRO", JOptionPane.INFORMATION_MESSAGE);
        }
        return false;
    }

    private void preencherTabela() {
        ArrayList dados = new ArrayList();
        String[] colunas = new String[]{"ID", "DESCRIÇÃO", "PESO", "QUANTIDADE"};

        try {
            cargaDao = new CargaDao();
            final List<Object> listaCargas = cargaDao.listarCargas(idPedido_CliSelecionado);

            if (listaCargas != null && listaCargas.size() > 0) {
                for (Object cargaAtual : listaCargas) {
                    Carga carga = (Carga) cargaAtual;
                    dados.add(new Object[]{carga.getIdCarga(), carga.getDescricao(), carga.getPeso(), carga.getQuantidade()});
                }
            }

            ModeloTabela modTabela = new ModeloTabela(dados, colunas);
            jTB_Cargas.setModel(modTabela);
            jTB_Cargas.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTB_Cargas.getColumnModel().getColumn(0).setResizable(false);
            jTB_Cargas.getColumnModel().getColumn(1).setPreferredWidth(180);
            jTB_Cargas.getColumnModel().getColumn(1).setResizable(false);
            jTB_Cargas.getColumnModel().getColumn(2).setPreferredWidth(100);
            jTB_Cargas.getColumnModel().getColumn(2).setResizable(false);
            jTB_Cargas.getColumnModel().getColumn(3).setPreferredWidth(100);
            jTB_Cargas.getColumnModel().getColumn(3).setResizable(false);
            jTB_Cargas.getTableHeader().setReorderingAllowed(false);
            jTB_Cargas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            jTB_Cargas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            jTB_Cargas.addMouseListener(new MouseAdapter() {
            });
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            ex.printStackTrace(System.err);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTB_Pedido = new javax.swing.JTabbedPane();
        jPN_Pedido = new javax.swing.JPanel();
        jBT_ProximaTela = new javax.swing.JButton();
        jBT_Verificar = new javax.swing.JButton();
        jBT_AlterarPedido = new javax.swing.JButton();
        jCB_Rotas = new javax.swing.JComboBox();
        jTF_NomeCliente = new javax.swing.JTextField();
        jTF_Cep = new javax.swing.JTextField();
        try{ 
            javax.swing.text.MaskFormatter cep= new javax.swing.text.MaskFormatter("#####-###"); 
            jTF_Cep = new javax.swing.JFormattedTextField(cep); 
        } 
        catch (Exception e){ 
        }
        jTF_Logradouro = new javax.swing.JTextField();
        jTF_Bairro = new javax.swing.JTextField();
        jTF_Uf = new javax.swing.JTextField();
        jTF_Numero = new javax.swing.JTextField();
        jTF_Complemento = new javax.swing.JTextField();
        jTF_Cpf = new javax.swing.JTextField();
        jTF_IdCliente = new javax.swing.JTextField();
        jTF_NomeCidade = new javax.swing.JTextField();
        jTF_Desconto = new javax.swing.JTextField();
        jTF_Desconto = new JTFSomenteNumeros();
        jLB_Fechar = new javax.swing.JLabel();
        jLB_Descricao1 = new javax.swing.JLabel();
        jLB_CEP = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLB_ErroCep = new javax.swing.JLabel();
        jLB_Descricao2 = new javax.swing.JLabel();
        jLB_Descricao3 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLB_Obrigatorio7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPN_Carga = new javax.swing.JPanel();
        jSP_Cargas = new javax.swing.JScrollPane();
        jTB_Cargas = new javax.swing.JTable();
        jBT_Salvar = new javax.swing.JButton();
        jBT_AdicionarCarga = new javax.swing.JButton();
        jTF_Quantidade = new javax.swing.JTextField();
        jTF_IdCliente1 = new javax.swing.JTextField();
        jTF_NomeCliente1 = new javax.swing.JTextField();
        jTF_Cpf1 = new javax.swing.JTextField();
        jLB_Descricao4 = new javax.swing.JLabel();
        jLB_Quantidade1 = new javax.swing.JLabel();
        jLB_Peso1 = new javax.swing.JLabel();
        jLB_Descricao5 = new javax.swing.JLabel();
        jLB_Descricao6 = new javax.swing.JLabel();
        jLB_Descricao7 = new javax.swing.JLabel();
        jCB_Peso = new javax.swing.JComboBox();
        jTF_Profundidade = new javax.swing.JTextField();
        jTF_Descricao = new javax.swing.JTextField();
        jTF_Altura = new javax.swing.JTextField();
        jTF_Largura = new javax.swing.JTextField();
        jLB_Descricao8 = new javax.swing.JLabel();
        jLB_Descricao9 = new javax.swing.JLabel();
        jLB_Descricao10 = new javax.swing.JLabel();
        jLB_Descricao11 = new javax.swing.JLabel();
        jLB_Fechar4 = new javax.swing.JLabel();
        jLB_Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTB_Pedido.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jPN_Pedido.setPreferredSize(new java.awt.Dimension(800, 600));
        jPN_Pedido.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jBT_ProximaTela.setBackground(new java.awt.Color(0, 0, 0));
        jBT_ProximaTela.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_ProximaTela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/salvar-icon.png"))); // NOI18N
        jBT_ProximaTela.setText("Finalizar ");
        jBT_ProximaTela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_ProximaTelaActionPerformed(evt);
            }
        });
        jPN_Pedido.add(jBT_ProximaTela, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 390, 170, 70));

        jBT_Verificar.setBackground(new java.awt.Color(0, 0, 0));
        jBT_Verificar.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jBT_Verificar.setText("Verificar CEP");
        jBT_Verificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_VerificarActionPerformed(evt);
            }
        });
        jPN_Pedido.add(jBT_Verificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 180, 120, 30));

        jBT_AlterarPedido.setBackground(new java.awt.Color(0, 0, 0));
        jBT_AlterarPedido.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_AlterarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/alterar2-icon.png"))); // NOI18N
        jBT_AlterarPedido.setText("Alterar");
        jBT_AlterarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_AlterarPedidoActionPerformed(evt);
            }
        });
        jPN_Pedido.add(jBT_AlterarPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 390, 150, 70));

        jCB_Rotas.setFont(new java.awt.Font("Segoe WP SemiLight", 0, 18)); // NOI18N
        jCB_Rotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCB_RotasActionPerformed(evt);
            }
        });
        jPN_Pedido.add(jCB_Rotas, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 130, 170, -1));

        jTF_NomeCliente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTF_NomeCliente.setEnabled(false);
        jPN_Pedido.add(jTF_NomeCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 290, 30));

        jTF_Cep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTF_CepActionPerformed(evt);
            }
        });
        jPN_Pedido.add(jTF_Cep, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 210, 30));
        jPN_Pedido.add(jTF_Logradouro, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 300, 320, 30));
        jPN_Pedido.add(jTF_Bairro, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, 200, 30));
        jPN_Pedido.add(jTF_Uf, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 300, 130, 30));
        jPN_Pedido.add(jTF_Numero, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 350, 80, 30));
        jPN_Pedido.add(jTF_Complemento, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 350, 330, 30));

        jTF_Cpf.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTF_Cpf.setEnabled(false);
        jTF_Cpf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTF_CpfActionPerformed(evt);
            }
        });
        jPN_Pedido.add(jTF_Cpf, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 180, 30));

        jTF_IdCliente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTF_IdCliente.setEnabled(false);
        jPN_Pedido.add(jTF_IdCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 50, 30));
        jPN_Pedido.add(jTF_NomeCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 250, 180, 30));
        jPN_Pedido.add(jTF_Desconto, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, 180, 30));

        jLB_Fechar.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLB_Fechar.setForeground(new java.awt.Color(255, 255, 255));
        jLB_Fechar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLB_Fechar.setText("X");
        jLB_Fechar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLB_Fechar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLB_FecharMouseReleased(evt);
            }
        });
        jPN_Pedido.add(jLB_Fechar, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 0, 40, 40));

        jLB_Descricao1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_Descricao1.setText("Nome Cliente");
        jPN_Pedido.add(jLB_Descricao1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jLB_CEP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_CEP.setText("CEP *");
        jPN_Pedido.add(jLB_CEP, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Logradouro");
        jPN_Pedido.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Bairro");
        jPN_Pedido.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Destino *");
        jPN_Pedido.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 130, -1, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("Número *");
        jPN_Pedido.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("Complemento");
        jPN_Pedido.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 350, -1, -1));

        jLB_ErroCep.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLB_ErroCep.setForeground(new java.awt.Color(255, 0, 0));
        jLB_ErroCep.setText("ERROR PLACEHOLDER");
        jPN_Pedido.add(jLB_ErroCep, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, 160, 20));

        jLB_Descricao2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_Descricao2.setText("CPF");
        jPN_Pedido.add(jLB_Descricao2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, -1, -1));

        jLB_Descricao3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_Descricao3.setText("ID");
        jPN_Pedido.add(jLB_Descricao3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setText("Cidade");
        jPN_Pedido.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 250, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("UF");
        jPN_Pedido.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 300, -1, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel14.setText("Desconto *");
        jPN_Pedido.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        jLB_Obrigatorio7.setForeground(new java.awt.Color(153, 0, 0));
        jLB_Obrigatorio7.setText("* - Campos Obrigatórios");
        jPN_Pedido.add(jLB_Obrigatorio7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 530, 180, -1));

        jLabel2.setForeground(new java.awt.Color(255, 0, 51));
        jLabel2.setText("* - Campos Obrigatórios");
        jPN_Pedido.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jTB_Pedido.addTab("Cadastrar Pedido", jPN_Pedido);

        jPN_Carga.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTB_Cargas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jSP_Cargas.setViewportView(jTB_Cargas);

        jPN_Carga.add(jSP_Cargas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 660, 180));

        jBT_Salvar.setBackground(new java.awt.Color(0, 0, 0));
        jBT_Salvar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_Salvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/salvar-icon.png"))); // NOI18N
        jBT_Salvar.setText("Finalizar ");
        jBT_Salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_SalvarActionPerformed(evt);
            }
        });
        jPN_Carga.add(jBT_Salvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 110, 170, 70));

        jBT_AdicionarCarga.setBackground(new java.awt.Color(0, 0, 0));
        jBT_AdicionarCarga.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_AdicionarCarga.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/add-icon.png"))); // NOI18N
        jBT_AdicionarCarga.setText("<html>Adicionar<br/>Carga</html>");
        jBT_AdicionarCarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_AdicionarCargaActionPerformed(evt);
            }
        });
        jPN_Carga.add(jBT_AdicionarCarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 30, 170, 70));

        jTF_Quantidade.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTF_Quantidade.setText("1");
        jPN_Carga.add(jTF_Quantidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, 36, 30));

        jTF_IdCliente1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTF_IdCliente1.setEnabled(false);
        jPN_Carga.add(jTF_IdCliente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 50, 30));

        jTF_NomeCliente1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTF_NomeCliente1.setEnabled(false);
        jPN_Carga.add(jTF_NomeCliente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 320, 30));

        jTF_Cpf1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTF_Cpf1.setEnabled(false);
        jTF_Cpf1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTF_Cpf1ActionPerformed(evt);
            }
        });
        jPN_Carga.add(jTF_Cpf1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 160, 30));

        jLB_Descricao4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLB_Descricao4.setText("(valores em metro)");
        jPN_Carga.add(jLB_Descricao4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 270, -1, -1));

        jLB_Quantidade1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_Quantidade1.setText("Quantidade");
        jPN_Carga.add(jLB_Quantidade1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, -1));

        jLB_Peso1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_Peso1.setText("Peso");
        jPN_Carga.add(jLB_Peso1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        jLB_Descricao5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_Descricao5.setText("ID");
        jPN_Carga.add(jLB_Descricao5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLB_Descricao6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_Descricao6.setText("CPF");
        jPN_Carga.add(jLB_Descricao6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, -1, -1));

        jLB_Descricao7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_Descricao7.setText("Nome Cliente");
        jPN_Carga.add(jLB_Descricao7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        jCB_Peso.setFont(new java.awt.Font("Segoe WP SemiLight", 0, 18)); // NOI18N
        jCB_Peso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCB_PesoActionPerformed(evt);
            }
        });
        jPN_Carga.add(jCB_Peso, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, 320, -1));

        jTF_Profundidade.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPN_Carga.add(jTF_Profundidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 240, 50, 30));

        jTF_Descricao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPN_Carga.add(jTF_Descricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, 320, 30));

        jTF_Altura.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPN_Carga.add(jTF_Altura, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 240, 50, 30));

        jTF_Largura.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPN_Carga.add(jTF_Largura, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 240, 50, 30));

        jLB_Descricao8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_Descricao8.setText("Descricao");
        jPN_Carga.add(jLB_Descricao8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        jLB_Descricao9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_Descricao9.setText("Altura");
        jPN_Carga.add(jLB_Descricao9, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 210, -1, -1));

        jLB_Descricao10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_Descricao10.setText("Largura");
        jPN_Carga.add(jLB_Descricao10, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 210, -1, -1));

        jLB_Descricao11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_Descricao11.setText("Profundidade");
        jPN_Carga.add(jLB_Descricao11, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 210, -1, -1));

        jTB_Pedido.addTab("Cadastrar Carga", jPN_Carga);

        getContentPane().add(jTB_Pedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 700, 510));

        jLB_Fechar4.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLB_Fechar4.setForeground(new java.awt.Color(255, 255, 255));
        jLB_Fechar4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLB_Fechar4.setText("X");
        jLB_Fechar4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLB_Fechar4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLB_Fechar4MouseReleased(evt);
            }
        });
        getContentPane().add(jLB_Fechar4, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 0, 40, 40));

        jLB_Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/alterar-cliente-bg.png"))); // NOI18N
        getContentPane().add(jLB_Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 600));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLB_FecharMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLB_FecharMouseReleased
        System.exit(0);
    }//GEN-LAST:event_jLB_FecharMouseReleased

    private void jBT_ProximaTelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_ProximaTelaActionPerformed
        List<String> listCampos = new ArrayList<>();
        listCampos.add(jTF_Cep.getText());
        listCampos.add(jTF_Numero.getText());
        if (verificarCampos(listCampos) && (idCliente != null)
                && (idEndereco != null)) {

            if (verificarCep()) {

                pedidoDao = new PedidoDao();
                pedido = new Pedido();

                pedido.setComplemento(jTF_Complemento.getText());

                pedido.setDataVenda(Calendar.getInstance().getTime());

                if (jTF_Desconto.getText() != null && jTF_Desconto.getText().trim().length() > 0) {
                    pedido.setDesconto(Integer.valueOf(jTF_Desconto.getText()));
                }

                pedido.setIdEnderecoCorreios(idEndereco);
                pedido.setNumero(listCampos.get(1));
                pedido.setProtocolo(Datas.getCurrentDate() + idCliente);
                pedido.setStatusPedido("Aguardando");

                precoDistancia = new PrecoDistancia();
                precoDistancia.setIdPrecoDistancia(Integer.valueOf(idPrecoDistancia[0]));

                jTB_Pedido.setEnabledAt(1, true);
                jTB_Pedido.setEnabledAt(0, false);
                jTB_Pedido.setSelectedIndex(1);
            } else {
                JOptionPane.showMessageDialog(this,
                        "CEP invalido!", "Erro", JOptionPane.INFORMATION_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(this, "Campos necessários em branco.", "CAMPOS EM BRANCO", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jBT_ProximaTelaActionPerformed

    private void jTF_CepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTF_CepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTF_CepActionPerformed

    private void jBT_VerificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_VerificarActionPerformed
        verificarCep();
    }//GEN-LAST:event_jBT_VerificarActionPerformed

    private void jTF_CpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTF_CpfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTF_CpfActionPerformed

    private void jCB_RotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCB_RotasActionPerformed
        idPrecoDistancia = jCB_Rotas.getSelectedItem().toString().split(" ");
    }//GEN-LAST:event_jCB_RotasActionPerformed

    private void jTF_Cpf1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTF_Cpf1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTF_Cpf1ActionPerformed

    private void jBT_SalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_SalvarActionPerformed
        List<String> listCampos = new ArrayList<>();
        listCampos.add(jTF_Descricao.getText());
        listCampos.add(jTF_Quantidade.getText());
        listCampos.add(jTF_Altura.getText());
        listCampos.add(jTF_Largura.getText());
        listCampos.add(jTF_Profundidade.getText());

        try {
            //verifica se todos os valores estao preenchidos
            if (verificarCampos(listCampos)) {
                Double dimensaoCubica = calcularDimensaoTotal();
                if (dimensaoCubica != null) {
                    CargaDao cargaDao = new CargaDao();
                    Carga carga = new Carga();

                    pedido.setIdPedido(pedidoDao.insertGetKey(pedido));
                    idPedido_CliSelecionado = pedidoDao.inserirPedidoCli(idCliente, pedido.getIdPedido(),
                            precoDistancia.getIdPrecoDistancia());

                    carga.setIdPedido_Cli(idPedido_CliSelecionado);
                    carga.setDescricao(listCampos.get(0));
                    carga.setIdPrecoPeso(Integer.valueOf(String.valueOf(jCB_Peso.getSelectedItem()).substring(0, 1)));
                    carga.setDimensaoCubica(dimensaoCubica);
                    carga.setQuantidade(Integer.valueOf(listCampos.get(1)));
                    carga.setStatus("Aguardando");
                    cargaDao.insertGetKey(carga);
                    preencherTabela();

                    JOptionPane.showMessageDialog(this, "Produto incluido com sucesso!", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
                    Integer opt = JOptionPane.showConfirmDialog(this,
                            "Deseja adicionar uma nova carga a este pedido?", "ADICIONAR NOVA CARGA",
                            JOptionPane.YES_NO_OPTION);
                    if (opt == JOptionPane.YES_OPTION) {
                        desabilitarBotao(jBT_Salvar);
                        limparCampos();
                        habilitarBotao(jBT_AdicionarCarga);
                    } else {
                        FuncionarioRN.chamarTela(FuncionarioSingleton.getFuncionario().getUsuario().getIdPerfil(), this);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Campos necessários em branco.", "CAMPOS EM BRANCO", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro Inesperado. Por favor tente novamente" + ex.getMessage(), "ERRO INESPERADO", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace(System.err);
        }
    }//GEN-LAST:event_jBT_SalvarActionPerformed

    private void jBT_AdicionarCargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_AdicionarCargaActionPerformed
        List<String> listCampos = new ArrayList<>();
        listCampos.add(jTF_Descricao.getText());
        listCampos.add(jTF_Quantidade.getText());
        listCampos.add(jTF_Altura.getText());
        listCampos.add(jTF_Largura.getText());
        listCampos.add(jTF_Profundidade.getText());

        try {
            //verifica se todos os valores estao preenchidos
            if (verificarCampos(listCampos)) {
                Double dimensaoCubica = calcularDimensaoTotal();
                if (dimensaoCubica != null) {

                    CargaDao cargaDao = new CargaDao();
                    Carga carga = new Carga();
                    Double dimensaoCubicaDoPedido = 0.0;
                    Caminhao caminhao = new Caminhao();

                    final List<Object> listaCargas = cargaDao.listarCargas(idPedido_CliSelecionado);

                    if (listaCargas != null && listaCargas.size() > 0) {
                        for (Object cargaAtual : listaCargas) {
                            Carga c = (Carga) cargaAtual;
                            System.out.println("dimensao " + c.getDimensaoCubica());
                            dimensaoCubicaDoPedido += c.getDimensaoCubica();
                        }
                    }

                    carga.setIdPedido_Cli(idPedido_CliSelecionado);
                    carga.setDescricao(listCampos.get(0));
                    carga.setIdPrecoPeso(Integer.valueOf(String.valueOf(jCB_Peso.getSelectedItem()).substring(0, 1)));
                    carga.setDimensaoCubica(dimensaoCubica);
                    carga.setStatus("Aguardando");
                    System.err.println("idprecopeso " + carga.getIdPrecoPeso());
                    carga.setQuantidade(Integer.valueOf(listCampos.get(1)));
                    cargaDao.insertGetKey(carga);

                    preencherTabela();
                    JOptionPane.showMessageDialog(this, "Produto incluido com sucesso!", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
                    Integer opt = JOptionPane.showConfirmDialog(this,
                            "Deseja adicionar uma nova carga a este pedido?", "ADICIONAR NOVA CARGA",
                            JOptionPane.YES_NO_OPTION);
                    if (opt == JOptionPane.YES_OPTION) {
                        limparCampos();
                    } else {
                        FuncionarioRN.chamarTela(FuncionarioSingleton.getFuncionario().getUsuario().getIdPerfil(), this);
                    }

                }
            } else {
                JOptionPane.showMessageDialog(this, "Campos necessários em branco.", "CAMPOS EM BRANCO", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro Inesperado. Por favor tente novamente" + ex.getMessage(), "ERRO INESPERADO", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jBT_AdicionarCargaActionPerformed

    private void jBT_AlterarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_AlterarPedidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBT_AlterarPedidoActionPerformed

    private void jLB_Fechar4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLB_Fechar4MouseReleased
        FuncionarioRN.chamarTela(FuncionarioSingleton.getFuncionario().getUsuario().getIdPerfil(), this);
    }//GEN-LAST:event_jLB_Fechar4MouseReleased

    private void jCB_PesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCB_PesoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCB_PesoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormCadastrarCarga.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormCadastrarCarga.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormCadastrarCarga.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormCadastrarCarga.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FormCadastrarCarga().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBT_AdicionarCarga;
    private javax.swing.JButton jBT_AlterarPedido;
    private javax.swing.JButton jBT_ProximaTela;
    private javax.swing.JButton jBT_Salvar;
    private javax.swing.JButton jBT_Verificar;
    private javax.swing.JComboBox jCB_Peso;
    private javax.swing.JComboBox jCB_Rotas;
    private javax.swing.JLabel jLB_Background;
    private javax.swing.JLabel jLB_CEP;
    private javax.swing.JLabel jLB_Descricao1;
    private javax.swing.JLabel jLB_Descricao10;
    private javax.swing.JLabel jLB_Descricao11;
    private javax.swing.JLabel jLB_Descricao2;
    private javax.swing.JLabel jLB_Descricao3;
    private javax.swing.JLabel jLB_Descricao4;
    private javax.swing.JLabel jLB_Descricao5;
    private javax.swing.JLabel jLB_Descricao6;
    private javax.swing.JLabel jLB_Descricao7;
    private javax.swing.JLabel jLB_Descricao8;
    private javax.swing.JLabel jLB_Descricao9;
    private javax.swing.JLabel jLB_ErroCep;
    private javax.swing.JLabel jLB_Fechar;
    private javax.swing.JLabel jLB_Fechar4;
    private javax.swing.JLabel jLB_Obrigatorio7;
    private javax.swing.JLabel jLB_Peso1;
    private javax.swing.JLabel jLB_Quantidade1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPN_Carga;
    private javax.swing.JPanel jPN_Pedido;
    private javax.swing.JScrollPane jSP_Cargas;
    private javax.swing.JTable jTB_Cargas;
    private javax.swing.JTabbedPane jTB_Pedido;
    private javax.swing.JTextField jTF_Altura;
    private javax.swing.JTextField jTF_Bairro;
    private javax.swing.JTextField jTF_Cep;
    private javax.swing.JTextField jTF_Complemento;
    private javax.swing.JTextField jTF_Cpf;
    private javax.swing.JTextField jTF_Cpf1;
    private javax.swing.JTextField jTF_Desconto;
    private javax.swing.JTextField jTF_Descricao;
    private javax.swing.JTextField jTF_IdCliente;
    private javax.swing.JTextField jTF_IdCliente1;
    private javax.swing.JTextField jTF_Largura;
    private javax.swing.JTextField jTF_Logradouro;
    private javax.swing.JTextField jTF_NomeCidade;
    private javax.swing.JTextField jTF_NomeCliente;
    private javax.swing.JTextField jTF_NomeCliente1;
    private javax.swing.JTextField jTF_Numero;
    private javax.swing.JTextField jTF_Profundidade;
    private javax.swing.JTextField jTF_Quantidade;
    private javax.swing.JTextField jTF_Uf;
    // End of variables declaration//GEN-END:variables
}
