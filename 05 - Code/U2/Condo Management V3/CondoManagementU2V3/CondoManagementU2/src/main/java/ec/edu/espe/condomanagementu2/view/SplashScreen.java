package ec.edu.espe.condomanagementu2.view;

import ec.edu.espe.condomanagementu2.model.User;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.net.URL;

public class SplashScreen extends JFrame {

    private final JFXPanel jfxPanel;
    private final User user; // Usuario recibido desde el login
    private JProgressBar progressBar;
    private JLabel percentageLabel;
    private MediaPlayer mediaPlayer; // Para controlar el video

    // Duración deseada para la animación (10 segundos)
    private final int totalDuration = 10000; // en milisegundos

    public SplashScreen(User user) {
        this.user = user;
        setTitle("Cargando...");
        setSize(800, 450);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Usamos un layout nulo para posicionar manualmente los componentes
        getContentPane().setLayout(null);

        // Creamos e inicializamos el JFXPanel (para reproducir el video)
        jfxPanel = new JFXPanel();
        jfxPanel.setBounds(0, 0, getWidth(), getHeight());
        getContentPane().add(jfxPanel);

        // Creamos la barra de progreso
        progressBar = new JProgressBar(0, 100);
        // La posicionamos más abajo, por ejemplo, a 40 píxeles del fondo
        progressBar.setBounds(100, getHeight() - 40, getWidth() - 200, 20);
        progressBar.setStringPainted(true);
        progressBar.setForeground(new Color(0, 51, 102));  // Color para el relleno y contorno
        progressBar.setBackground(new Color(220, 220, 220)); // Fondo de la barra
        progressBar.setOpaque(false);
        progressBar.setUI(new javax.swing.plaf.basic.BasicProgressBarUI() {
            @Override
            protected void paintDeterminate(Graphics g, JComponent c) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int barWidth = progressBar.getWidth();
                int barHeight = progressBar.getHeight();
                double percent = progressBar.getPercentComplete();
                int fillWidth = (int) (barWidth * percent);
                // Fondo redondeado
                Shape pill = new RoundRectangle2D.Double(0, 0, barWidth, barHeight, barHeight, barHeight);
                g2d.setColor(new Color(220, 220, 220));
                g2d.fill(pill);
                // Relleno redondeado
                Shape fillPill = new RoundRectangle2D.Double(0, 0, fillWidth, barHeight, barHeight, barHeight);
                g2d.setColor(progressBar.getForeground());
                g2d.fill(fillPill);
                // Contorno
                g2d.setColor(progressBar.getForeground());
                g2d.draw(pill);
                g2d.dispose();
            }
        });
        getLayeredPane().add(progressBar, JLayeredPane.PALETTE_LAYER);

        // Creamos la etiqueta del porcentaje y la posicionamos justo encima de la barra
        percentageLabel = new JLabel("0%", SwingConstants.CENTER);
        percentageLabel.setFont(new Font("Arial", Font.BOLD, 20));
        percentageLabel.setForeground(new Color(0, 51, 102));
        percentageLabel.setBounds(100, getHeight() - 70, getWidth() - 200, 20);
        getLayeredPane().add(percentageLabel, JLayeredPane.PALETTE_LAYER);

        // Aseguramos que se actualicen las capas
        getLayeredPane().revalidate();
        getLayeredPane().repaint();

        // Inicializamos el contenido de JavaFX
        initFX();
    }

    private void initFX() {
        Platform.runLater(() -> {
            try {
                // Cargar el video desde la carpeta /videos dentro de resources
                URL videoUrl = getClass().getResource("/videos/SplashScreen.mp4");
                if (videoUrl == null) {
                    System.err.println("No se encontró el video SplashScreen.mp4 en /videos");
                    Platform.exit();
                    return;
                }
                String videoPath = videoUrl.toExternalForm();

                // Crear Media y MediaPlayer
                Media media = new Media(videoPath);
                mediaPlayer = new MediaPlayer(media);

                // Crear MediaView para mostrar el video
                MediaView mediaView = new MediaView(mediaPlayer);
                mediaView.setFitWidth(getWidth());
                mediaView.setFitHeight(getHeight());

                // Crear la escena JavaFX y asignarla al JFXPanel
                Group root = new Group(mediaView);
                Scene scene = new Scene(root, getWidth(), getHeight());
                jfxPanel.setScene(scene);

                // Iniciamos el video
                mediaPlayer.play();

                // Creamos un Swing Timer para actualizar la barra de progreso de forma uniforme durante 10 segundos
                new Timer(100, e -> {
                    long elapsed = System.currentTimeMillis() % totalDuration; // Usamos el tiempo transcurrido (idealmente deberíamos medir desde un "startTime")
                    // Para hacerlo correctamente, se recomienda capturar el tiempo de inicio:
                }).start();

                // Mejor: Usar un Timer que capture el tiempo de inicio
                Timer progressTimer = new Timer(100, null);
                long startTime = System.currentTimeMillis();
                progressTimer.addActionListener(ev -> {
                    long elapsed = System.currentTimeMillis() - startTime;
                    int progress = (int) ((elapsed * 100) / totalDuration);
                    if (progress > 100) {
                        progress = 100;
                    }
                    progressBar.setValue(progress);
                    percentageLabel.setText(progress + "%");
                    if (elapsed >= totalDuration) {
                        ((Timer) ev.getSource()).stop();
                        Platform.runLater(() -> mediaPlayer.stop());
                        SwingUtilities.invokeLater(() -> {
                            openMainApplication();
                            dispose();
                        });
                    }
                });
                progressTimer.start();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    // Método que se ejecuta al finalizar la reproducción (o 10 segundos)
    private void openMainApplication() {
        if (user != null) {
            if (user.getType().equalsIgnoreCase("Administrador")) {
                FrmMenuAdmin menuAdmin = new FrmMenuAdmin();
                menuAdmin.setVisible(true);
            } else if (user.getType().equalsIgnoreCase("Residente")) {
                FrmMenuResident menuResident = new FrmMenuResident();
                menuResident.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Tipo de usuario no reconocido. Contacte al administrador del sistema.");
            }
        } else {
            System.out.println("Aplicación cargada. ¡Bienvenido!");
        }
    }

    // Método para mostrar el splash: la ventana se hace visible y el video se reproduce
    public void showSplashScreen() {
        setVisible(true);
    }

}
