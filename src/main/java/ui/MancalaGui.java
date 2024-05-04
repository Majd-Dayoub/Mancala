package ui;
import mancala.MancalaGame;
import mancala.Player;
import mancala.InvalidMoveException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MancalaGui extends JFrame {

    private MancalaGame mancala;
    private JButton[] pitButtons;
    private JLabel storeLabelPlayer1;
    private JLabel storeLabelPlayer2;
    private JLabel currentPlayerLabel;

    public MancalaGui(MancalaGame game) {
        this.mancala = game;
        this.pitButtons = new JButton[12];

        setUpPlayersAndRules();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Mancala Game");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (mancala.isGameOver() || confirmQuit()) {
                    System.exit(0);
                }
            }
        });

        setLayout(new BorderLayout(10, 10)); // Adding spacing between components

        // Create a panel for the Mancala board
        JPanel boardPanel = new JPanel(new GridLayout(2, 6, 5, 5)); // Adding spacing between buttons

        // Create buttons for each pit
        for (int i = 11; i >= 6; i--) {
            JButton pitButton = new JButton("Pit " + (i + 1));
            pitButton.addActionListener(this::pitButtonClicked);
            pitButtons[i] = pitButton;
            boardPanel.add(pitButton);
        }

        for (int i = 0; i < 6; i++) {
            JButton pitButton = new JButton("Pit " + (i + 1));
            pitButton.addActionListener(this::pitButtonClicked);
            pitButtons[i] = pitButton;
            boardPanel.add(pitButton);
        }

        add(boardPanel, BorderLayout.CENTER);

        // Create panels for player stores
        JPanel player1StorePanel = new JPanel(new BorderLayout());
        JPanel player2StorePanel = new JPanel(new BorderLayout());

        storeLabelPlayer1 = new JLabel("Store: 0", SwingConstants.CENTER);
        storeLabelPlayer2 = new JLabel("Store: 0", SwingConstants.CENTER);

        player1StorePanel.add(storeLabelPlayer1, BorderLayout.CENTER);
        player2StorePanel.add(storeLabelPlayer2, BorderLayout.CENTER);

        add(player1StorePanel, BorderLayout.EAST);
        add(player2StorePanel, BorderLayout.WEST);

        // Create a label for the current player
        currentPlayerLabel = new JLabel("Current Player: " + mancala.getCurrentPlayerNum(), SwingConstants.CENTER);
        add(currentPlayerLabel, BorderLayout.SOUTH); // Display at the bottom

        // Create a "New Game" button
        JButton newGameButton = new JButton("New Game");
        newGameButton.addActionListener(this::startNewGame);

        // Add the "New Game" button to the top of the frame
        add(newGameButton, BorderLayout.NORTH);

        pack();
        setLocationRelativeTo(null); // Center the frame
    }

    private boolean confirmQuit() {
        int result = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to exit? The current game will be lost.",
                "Confirm Quit",
                JOptionPane.YES_NO_OPTION);
        return result == JOptionPane.YES_OPTION;
    }

    private void startNewGame(ActionEvent e) {
        if (mancala.isGameOver() || confirmQuit()) {
            mancala.resetGame();
            updateBoard();
            updateCurrentPlayerLabel();
        }
    }

    private void pitButtonClicked(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        int pitIndex = getButtonIndex(clickedButton);

        try {
            mancala.move(pitIndex + 1); // Pit indices are 1-based
            updateBoard();
            updateCurrentPlayerLabel(); // Update the current player label after each move
        } catch (InvalidMoveException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Invalid Move", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int getButtonIndex(JButton button) {
        for (int i = 0; i < pitButtons.length; i++) {
            if (pitButtons[i] == button) {
                return i;
            }
        }
        return -1;
    }

    private void updateBoard() {
        storeLabelPlayer1.setText("Store: " + mancala.getStoreCount(1));
        storeLabelPlayer2.setText("Store: " + mancala.getStoreCount(2));

        for (int i = 1; i < 13; i++) {
            int stoneCount = mancala.getNumStones(i); // Pit indices are 1-based
            pitButtons[i-1].setText("Pit " + (i) + ": " + stoneCount + " stones");
        }
    }

    private void setUpPlayersAndRules() {
        Player playerA = new Player();
        Player playerB = new Player();

        String playerNameA = JOptionPane.showInputDialog("Enter Player A Name:");
        playerA.setName(playerNameA);

        String playerNameB = JOptionPane.showInputDialog("Enter Player B Name:");
        playerB.setName(playerNameB);

        String ruleInput = JOptionPane.showInputDialog("Enter 1 for Kalaha Rules, or 2 for Ayo Rules:");
        int gameRule = Integer.parseInt(ruleInput);
        mancala.setRule(gameRule);

        mancala.setPlayers(playerA, playerB);
    }

    public static void main(String[] args) {
        // Create a new game
        MancalaGame game = new MancalaGame();
        Player playerA = new Player("Player A");
        Player playerB = new Player("Player B");
        game.setPlayers(playerA, playerB);

        // Create and show the GUI
        SwingUtilities.invokeLater(() -> {
            MancalaGui gui = new MancalaGui(game);
            gui.setVisible(true);
        });
    }

    private void updateCurrentPlayerLabel() {
        currentPlayerLabel.setText("Current Player: " + mancala.getCurrentPlayerNum());
    }
}