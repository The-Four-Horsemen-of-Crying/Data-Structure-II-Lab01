/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab01_enrique.miranda_alvaro.cabrera_dilan.triana;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Domain
 */
public class GUI_Tree extends JFrame {

    private JTree tree;
    private DefaultMutableTreeNode root;
    private JPanel panel;
    private int width, height;
    private JTextArea description;
    private JScrollPane treeBar;
    private JScrollPane descripcionBar;
    private JButton search;
    private JButton searchOwner;
    private JTextField searchLabel;
    private JComboBox nodoType;
    private JComboBox varType;

    public GUI_Tree(String title, Nodo n_root, int width) {
        super(title);
        this.width = width;
        this.height = width / 16 * 9;
        root = new DefaultMutableTreeNode(n_root);
        tree = new JTree(root);
        treeBar = new JScrollPane();
        description = new JTextArea();
        search = new JButton("Buscar");
        searchOwner = new JButton("Buscar Usuario");
        searchLabel = new JTextField();
        String nodoTypeOptions[] = {"Users", "Post", "Comment"};
        nodoType = new JComboBox(nodoTypeOptions);
        String varTypeOptions[] = {"Var1", "Var2", "Var3"};
        varType = new JComboBox(varTypeOptions);
        descripcionBar = new JScrollPane();
        panel = new JPanel();

        init();
    }

    private void init() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        description.setEditable(false);
        setSize(width, height);
        panel.setLayout(null);
        panel.setBackground(Color.GRAY);
        this.getContentPane().add(panel);

        descripcionBar.setBounds(width / 2 - 50, 60, width / 2 - 100, height / 2);
        treeBar.setBounds(5, 60, width / 2 - 100, height / 2);
        searchLabel.setBounds(10, 10, 80, 25);
        searchLabel.setBackground(Color.WHITE);
        nodoType.setBounds(110, 10, 80, 25);
        varType.setBounds(200, 10, 80, 25);
        search.setBounds(290, 10, 80, 25);
        searchOwner.setBounds(width / 2 - 50, 65 + height / 2, 80, 25);

        panel.add(descripcionBar);
        panel.add(treeBar);
        panel.add(searchLabel);
        panel.add(nodoType);
        panel.add(varType);
        panel.add(search);
        panel.add(searchOwner);

        searchOwner.setEnabled(false);
        descripcionBar.setViewportView(description);
        treeBar.setViewportView(tree);

        this.setResizable(false);
        this.setVisible(true);

        tree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {

                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

                if (node == null) {
                    return;
                }

                Nodo n = (Nodo) node.getUserObject();
                if (n instanceof Comment) {
                    searchOwner.setEnabled(true);
                } else {
                    searchOwner.setEnabled(false);
                }
                description.setLineWrap(true);
                description.setFont(new Font("Times New Roman", Font.PLAIN, 15));
                description.setWrapStyleWord(true);

                description.setText(n.printInfo());

            }
        });
    }

    public void add(NodoList ptr, DefaultMutableTreeNode root) {
        DefaultMutableTreeNode j_Nodo;
        NodoList p = ptr;
   
        while (p.link != null) {
            Nodo aux = p.getNodo();
            j_Nodo = new DefaultMutableTreeNode(aux);
            if(aux.getLinks()!=null)this.add(aux.getLinks(), j_Nodo);
            root.add(j_Nodo);
            p = p.link;
        }

    }

    public DefaultMutableTreeNode getRoot() {

        return root;
    }

}