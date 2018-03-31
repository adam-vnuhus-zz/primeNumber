package lecture7.primes3;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

/**
 * @author Phuong LE-HONG, phuonglh@gmail.com
 * <p>
 * <p>
 * Oct 31, 2012, 3:00:53 PM
 * <p>
 * A simple graphical frame for prime numbers.
 * @see PrimeNumbers
 */
public class PrimeNumberFrame extends JFrame {
    private JTextField nTextField;
    private JButton okButton;
    private PrimeNumbers pn;

    private JMenuItem saveMenuItem;
    private List<Integer> numbers;
    private JList primeList;

    /**
     * The default constructor which builds
     * the main frame of the application.
     */
    public PrimeNumberFrame() {
        setTitle("Prime Numbers");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initialize();
    }

    /**
     * Creates graphical components of the
     * application.
     */
    private void initialize() {
        pn = new PrimeNumbers();
        // prepare the input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setBorder(new TitledBorder("Input"));
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(new JLabel("Enter n = "), BorderLayout.WEST);
        nTextField = new JTextField();
        inputPanel.add(nTextField, BorderLayout.CENTER);
        okButton = new JButton("Ok");
        inputPanel.add(okButton, BorderLayout.EAST);
        // add the input panel to the frame
        getContentPane().add(inputPanel, BorderLayout.NORTH);

        // prepare the result panel
        JPanel resultPanel = new JPanel(new BorderLayout());
        resultPanel.setBorder(new TitledBorder("Result"));
        resultPanel.setPreferredSize(new Dimension(300, 400));
        primeList = new JList();
//		resultTextArea.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
        resultPanel.add(new JScrollPane(primeList), BorderLayout.CENTER);
        // add the output panel to the frame
        getContentPane().add(resultPanel, BorderLayout.CENTER);

        // add an action listener to the Ok button
        ActionListener listener = new EnumeratePrimeNumbersListener();
        okButton.addActionListener(listener);
        // add the same action listener to the text field
        nTextField.addActionListener(listener);

        // init menu bar
        initMenuBar();
    }

    private void initMenuBar() {
        // create a menu bar containing some menus
        JMenuBar menuBar = new JMenuBar();
        // File menu
        JMenu menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_F);

        // Save
        saveMenuItem = new JMenuItem("Save");
        saveMenuItem.setMnemonic(KeyEvent.VK_S);
        saveMenuItem.setEnabled(false);
        saveMenuItem.addActionListener(new SaveActionListener());
        menu.add(saveMenuItem);

        // Exit
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setMnemonic(KeyEvent.VK_X);
        exitMenuItem.addActionListener(new ExitActionListener());
        menu.add(exitMenuItem);

        menuBar.add(menu);

        // Look and Feel menu
        menu = new JMenu("Look and Feel");
        menu.setMnemonic(KeyEvent.VK_L);
        ButtonGroup group = new ButtonGroup();
        // Meta LnF
        JRadioButtonMenuItem menuItem = new JRadioButtonMenuItem("Meta");
        menuItem.setMnemonic(KeyEvent.VK_M);
        ActionListener lookAndFeelActionListener = new ChooseLookAndFeelActionListener("javax.swing.plaf.metal.MetalLookAndFeel");
        menuItem.addActionListener(lookAndFeelActionListener);
        group.add(menuItem);
        menu.add(menuItem);
        // Nimbus LnF (Java 6, update 10)
        menuItem = new JRadioButtonMenuItem("Nimbus");
        menuItem.setMnemonic(KeyEvent.VK_N);
        lookAndFeelActionListener = new ChooseLookAndFeelActionListener("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        menuItem.addActionListener(lookAndFeelActionListener);
        group.add(menuItem);
        menu.add(menuItem);
        // Motif LnF
        menuItem = new JRadioButtonMenuItem("Motif");
        menuItem.setMnemonic(KeyEvent.VK_T);
        lookAndFeelActionListener = new ChooseLookAndFeelActionListener("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        menuItem.addActionListener(lookAndFeelActionListener);
        group.add(menuItem);
        menu.add(menuItem);
        // GTK LnF
        menuItem = new JRadioButtonMenuItem("GTK");
        menuItem.setMnemonic(KeyEvent.VK_G);
        lookAndFeelActionListener = new ChooseLookAndFeelActionListener("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        menuItem.addActionListener(lookAndFeelActionListener);
        menu.add(menuItem);
        group.add(menuItem);
        // add a separator
        menu.add(new JSeparator());
        // System LnF
        menuItem = new JRadioButtonMenuItem("System");
        menuItem.setSelected(true);
        menuItem.setMnemonic(KeyEvent.VK_S);
        lookAndFeelActionListener = new ChooseLookAndFeelActionListener(UIManager.getSystemLookAndFeelClassName());
        menuItem.addActionListener(lookAndFeelActionListener);
        group.add(menuItem);
        menu.add(menuItem);

        menuBar.add(menu);
        // Help menu
        menu = new JMenu("Help");
        menu.setMnemonic(KeyEvent.VK_H);
        JMenuItem aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.setMnemonic(KeyEvent.VK_A);
        aboutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(PrimeNumberFrame.this, "A program for listing prime numbers", "About", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        menu.add(aboutMenuItem);
        menuBar.add(menu);

        // add the menu bar to the frame
        setJMenuBar(menuBar);
    }


    class ExitActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            System.exit(0);
        }
    }

    class ChooseLookAndFeelActionListener implements ActionListener {
        private String name;

        public ChooseLookAndFeelActionListener(String name) {
            this.name = name;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                UIManager.setLookAndFeel(name);
            } catch (ClassNotFoundException exp) {
                exp.printStackTrace();
            } catch (InstantiationException exp) {
                exp.printStackTrace();
            } catch (IllegalAccessException exp) {
                exp.printStackTrace();
            } catch (UnsupportedLookAndFeelException exp) {
                exp.printStackTrace();
            }
            SwingUtilities.updateComponentTreeUI(PrimeNumberFrame.this);
            PrimeNumberFrame.this.pack();
        }
    }

    class EnumeratePrimeNumbersListener implements ActionListener {
        /* (non-Javadoc)
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        @Override
        public void actionPerformed(ActionEvent event) {
            int n = 0;
            try {
                n = Integer.parseInt(nTextField.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(PrimeNumberFrame.this, "Number format error!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // update the prime list
            numbers = pn.computePrimeNumbers(n);
            DefaultListModel listModel = new DefaultListModel();
            for (Integer p : numbers) {
                listModel.addElement(p);
            }
            primeList.setModel(listModel);
            // enable the Save action
            if (numbers.size() > 0) {
                saveMenuItem.setEnabled(true);
            } else {
                saveMenuItem.setEnabled(false);
            }
        }
    }

    class SaveActionListener implements ActionListener {
        /* (non-Javadoc)
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        @Override
        public void actionPerformed(ActionEvent arg0) {
            // open a message dialog to accept a file name from user
            JFileChooser fileChooser = new JFileChooser();
            int value = fileChooser.showSaveDialog(PrimeNumberFrame.this);
            // if the file is approved, save the list to the selected file
            if (value == JFileChooser.APPROVE_OPTION) {
                String fileName = fileChooser.getSelectedFile().getAbsolutePath();
                if (numbers != null) {
                    PrimeNumberIO.writeList(numbers, fileName);
                }
            }
        }
    }
}
