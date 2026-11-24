package tetris;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.KeyStroke;
import tetris.commands.*;

public class GameForm extends JFrame {
    
    private GameArea ga;
    private GameThread gt;

    public GameForm() {
        initComponents();
        
        ga = new GameArea(gameAreaPlaceholder, 10);
        this.add(ga);
        
        iniControles();
    } // Crio a classe GameForm que herda os métodos e rotinas de JFrame package
    
    public void startGame(){
        ga.iniBackground();
        gt = new GameThread(ga, this);
        gt.start();
    }
    
    
    
    // Fiz uma alteração aqui adicionando o Padrão de Projeto "COMMAND" pelo modo que a requisição é feita
    // GameForm se comunica diretamente com GameArea e gera muita repetição de codigo, vamos fazer com que os comandos
    // tornem-se classes para que eu possa passa-los como parametros
    
    private void registrarComando(String tecla, String nomeAcao, GameCommand comando) 
    {
    InputMap im = this.getRootPane().getInputMap();
    ActionMap am = this.getRootPane().getActionMap();

    im.put(KeyStroke.getKeyStroke(tecla), nomeAcao);
    am.put(nomeAcao, new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            comando.execute();
        }
    });
    }
    
    /*
    No GameForm, os comandos do teclado chamavam diretamente os métodos de movimento da GameArea dentro de várias AbstractAction parecidas. 
    O padrão Command criando a interface GameCommand e classes específicas para cada movimento, fazendo o formulário apenas disparar execute(). 
    Ttorna simples adicionar novas ações ou regras de entrada sem inflar o GameForm.
    */
    private void iniControles(){
        registrarComando("RIGHT", "right", new MoverDireitaCommand(ga));
        registrarComando("LEFT", "left", new MoverEsquerdaCommand(ga));
        registrarComando("UP", "up", new RotacionarCommand(ga));
        registrarComando("DOWN", "down", new DropCommand(ga));
    }
    

    
    public void atualizaPont(int pontuacao) // Numero de linhas limpas
    {
        labelPontuacao.setText("Pontuação: " + pontuacao);
    }
    
    public void atualizaNivel(int nivel)
    {
        labelNivel.setText("Nível: " + nivel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gameAreaPlaceholder = new javax.swing.JPanel();
        labelPontuacao = new javax.swing.JLabel();
        labelNivel = new javax.swing.JLabel();
        btnVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tetris");
        setResizable(false);

        gameAreaPlaceholder.setBackground(new java.awt.Color(238, 238, 238));
        gameAreaPlaceholder.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        gameAreaPlaceholder.setPreferredSize(new java.awt.Dimension(200, 300));

        javax.swing.GroupLayout gameAreaPlaceholderLayout = new javax.swing.GroupLayout(gameAreaPlaceholder);
        gameAreaPlaceholder.setLayout(gameAreaPlaceholderLayout);
        gameAreaPlaceholderLayout.setHorizontalGroup(
            gameAreaPlaceholderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 198, Short.MAX_VALUE)
        );
        gameAreaPlaceholderLayout.setVerticalGroup(
            gameAreaPlaceholderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 298, Short.MAX_VALUE)
        );

        labelPontuacao.setFont(new java.awt.Font("Lucida Console", 0, 18)); // NOI18N
        labelPontuacao.setText("Pontuação: 0");

        labelNivel.setFont(new java.awt.Font("Lucida Console", 0, 18)); // NOI18N
        labelNivel.setText("Nível: 1");

        btnVoltar.setFont(new java.awt.Font("Lucida Console", 0, 14)); // NOI18N
        btnVoltar.setText("Voltar");
        btnVoltar.setFocusable(false);
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnVoltar)
                .addGap(49, 49, 49)
                .addComponent(gameAreaPlaceholder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPontuacao, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelPontuacao, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(gameAreaPlaceholder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed

        gt.interrupt(); // Tem que tirar a parte que ele pega excecao aarr
        this.setVisible(false);
        Tetris.mostrarInicial();
    }//GEN-LAST:event_btnVoltarActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() { // Garante que a UI do prgrama seja rodada inteiramente numa threead diferente 
            public void run() {
                new GameForm().setVisible(true); // Crio um objetivo da classe GameForm que herda de JFrame
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVoltar;
    private javax.swing.JPanel gameAreaPlaceholder;
    private javax.swing.JLabel labelNivel;
    private javax.swing.JLabel labelPontuacao;
    // End of variables declaration//GEN-END:variables
}
