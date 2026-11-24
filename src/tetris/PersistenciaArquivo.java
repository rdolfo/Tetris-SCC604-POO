/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tetris;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author profb
 */

// Caixa de Ferramenta para fazer a serialização de um tipo generico, basta eu passar o caminho

public class PersistenciaArquivo<T extends Serializable> {
    private final String caminhoArquivo;

    public PersistenciaArquivo(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public void salvar(T dados) {
        try (FileOutputStream fs = new FileOutputStream(caminhoArquivo);
             ObjectOutputStream os = new ObjectOutputStream(fs)) {
            os.writeObject(dados);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public T carregar() {
        try (FileInputStream fs = new FileInputStream(caminhoArquivo);
             ObjectInputStream os = new ObjectInputStream(fs)) {
            return (T) os.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
}
