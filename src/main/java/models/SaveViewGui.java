package models;

import controllers.ControllerBattle;
import models.datos.Save;
import view.battle.gui.BattlePokemonGUI;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class SaveViewGui {

    public static void save(ControllerBattle controller){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar objeto Persona (serializado)");

        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            // Añadir extensión .ser si no la tiene (convención para serializados)
            if (!file.getName().toLowerCase().endsWith(".ser")) {
                file = new File(file.getAbsolutePath() + ".ser");
            }

            // Confirmar sobrescritura
            if (file.exists()) {
                int respuesta = JOptionPane.showConfirmDialog(null,
                        "El archivo ya existe. ¿Quieres sobrescribirlo?",
                        "Confirmar sobrescritura",
                        JOptionPane.YES_NO_OPTION);

                if (respuesta != JOptionPane.YES_OPTION) {
                    System.out.println("Guardado cancelado.");
                    return;
                }
            }

            // Guardar objeto serializado
            try {
                controller.saveGame(file);
                JOptionPane.showMessageDialog(null, "Objeto guardado serializado correctamente.");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al guardar archivo: " + e.getMessage());
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }

    }

    public static boolean load(){
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int option = chooser.showOpenDialog(null);
        if(option == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            if (file.exists()) {
                try {
                    Save save = Save.loadSave(file);
                    ControllerBattle controller = save.getControllerBattle();
                    BattlePokemonGUI view = new BattlePokemonGUI(controller);
                    controller.setViewBattle(view);
                    controller.startBattle();
                    return true;
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        return false;
    }
}
