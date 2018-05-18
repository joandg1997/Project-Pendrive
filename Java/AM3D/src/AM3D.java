import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Canvas;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class AM3D extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	
	ArrayList<Treballdor> LlistaTreballadors = new ArrayList();
	ArrayList<Impresora> LlistaImpresores= new ArrayList();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
				
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AM3D window = new AM3D();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AM3D() {
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 837, 529);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/**
		 * Barra de menu
		 */
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		/**
		 * Menu
		 */
		JMenu mnArxiu = new JMenu("Arxiu");
		menuBar.add(mnArxiu);
		/**
		 * textArea per Treballadors
		 */
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("Consolas", Font.PLAIN, 12));
		textArea.setLineWrap(true);
		textArea.setBounds(25, 119, 769, 330);
		frame.getContentPane().add(textArea);
		
		/**
		 * textArea per Impresores
		 */
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setEditable(false);
		textArea_1.setFont(new Font("Consolas", Font.PLAIN, 12));
		textArea_1.setBounds(25, 119, 769, 330);
		textArea.setLineWrap(true);
		frame.getContentPane().add(textArea_1);
		
		/**
		 * label "Numero de maquines"
		 */
		JLabel lblNumeroDeMaquines = new JLabel("Numero de maquines:");
		lblNumeroDeMaquines.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumeroDeMaquines.setBounds(385, 3, 136, 14);
		frame.getContentPane().add(lblNumeroDeMaquines);
		
		/**
		 * label Resultat Num de maquines
		 */
		JLabel lblNumMaquines = new JLabel("");
		lblNumMaquines.setBounds(523, 3, 46, 14);
		frame.getContentPane().add(lblNumMaquines);
		
		/**
		 * label "Maquines connectades"
		 */
		JLabel lblMconnectades = new JLabel("Maquines connectades:");
		lblMconnectades.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMconnectades.setBounds(385, 19, 136, 14);
		frame.getContentPane().add(lblMconnectades);
		
		/**
		 * label Resultat Num de maquines connectades
		 */
		JLabel lblMaquinesConnectades = new JLabel("");
		lblMaquinesConnectades.setBounds(523, 19, 46, 14);
		frame.getContentPane().add(lblMaquinesConnectades);
		
		/**
		 * label "Maquines amb error"
		 */
		JLabel lblMAmbError = new JLabel("Maquines amb error:");
		lblMAmbError.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMAmbError.setBounds(385, 35, 136, 14);
		frame.getContentPane().add(lblMAmbError);
			
		/**
		 * label Resultat Maquines amb error
		 */
		JLabel lblMaquinesAmbError = new JLabel("");
		lblMaquinesAmbError.setBounds(523, 35, 46, 14);
		frame.getContentPane().add(lblMaquinesAmbError);
		
		/**
		 * label "Maquines imprimint"
		 */
		JLabel lblMImprimint = new JLabel("Maquines imprimint:");
		lblMImprimint.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMImprimint.setBounds(385, 51, 136, 14);
		frame.getContentPane().add(lblMImprimint);
			
		/**
		 * label Resultat Maquines imprimint
		 */
		JLabel lblMaquinesImprimint = new JLabel("");
		lblMaquinesImprimint.setBounds(523, 51, 46, 14);
		frame.getContentPane().add(lblMaquinesImprimint);
		
		/**
		 * label "Maquines finalitzades"
		 */
		JLabel lblMFinalitzdes = new JLabel("Maquines finalitzades:");
		lblMFinalitzdes.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMFinalitzdes.setBounds(385, 67, 136, 14);
		frame.getContentPane().add(lblMFinalitzdes);
		
		/**
		 * label Resultat Maquines finalitzades
		 */
		JLabel lblMaquinesFinalitzdes = new JLabel("");
		lblMaquinesFinalitzdes.setBounds(523, 67, 46, 14);
		frame.getContentPane().add(lblMaquinesFinalitzdes);
		
		/**
		 * button Amb la funcio de ordenar els treballadors segons el seu nom
		 */
		JButton btnOrdenarPerNomTreballador = new JButton("Ordenar per nom");
		btnOrdenarPerNomTreballador.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String contingut="";
				Collections.sort(LlistaTreballadors, new CompareNomTreballador());
				textArea.setText("");
				Iterator <Treballdor> itr;
				itr=LlistaTreballadors.iterator();
				while(itr.hasNext()){
					contingut = contingut + itr.next() + "\n";
				}
				textArea.setText(contingut);
				
			}
			
		});
		btnOrdenarPerNomTreballador.setBounds(25, 52, 162, 29);
		frame.getContentPane().add(btnOrdenarPerNomTreballador);
		
		/**
		 * button Amb la funcio de ordenar les impresores segons el seu nom
		 */
		JButton btnOrdenarPerNomImpresora = new JButton("Ordenar per nom");
		btnOrdenarPerNomImpresora.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
							
				String contingut ="";
				Collections.sort(LlistaImpresores, new CompareNomImpresores());
				textArea_1.setText("");
				Iterator <Impresora> itr;
				itr=LlistaImpresores.iterator();
				
				while(itr.hasNext()) {
					contingut= contingut + itr.next() + "\n";
				}
				textArea_1.setText(contingut);
			}	
		});
		btnOrdenarPerNomImpresora.setBounds(25, 52, 162, 29);
		frame.getContentPane().add(btnOrdenarPerNomImpresora);
		
		/**
		 * button Amb la funcio de ordenar les impresores segons el seu estat
		 */
		JButton btnOrdenarPerEstat = new JButton("Ordenar per Estat");
		btnOrdenarPerEstat.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
								
				String contingut ="";
				Collections.sort(LlistaImpresores, new CompareEstatImpresores());
				textArea_1.setText("");
				Iterator <Impresora> itr;
				itr=LlistaImpresores.iterator();
				
				while(itr.hasNext()) {
					contingut= contingut + itr.next() + "\n";
				}
				textArea_1.setText(contingut);
			}
		});
		btnOrdenarPerEstat.setBounds(197, 52, 153, 29);
		frame.getContentPane().add(btnOrdenarPerEstat);
		
		/**
		 * label per veure el ordre dels treballadors
		 */
		JLabel lblNewLabel = new JLabel("Dni | Nom | Cognom ");
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setBounds(30, 99, 291, 14);
		frame.getContentPane().add(lblNewLabel);
		
		/**
		 * label per veure el ordre de les impresores
		 */
		JLabel lblNewLabel_1 = new JLabel("Codi impresora | Nom assignat | Estat | Marca | Model | Bobina carregada");
		lblNewLabel_1.setBounds(30, 99, 427, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		/**
		 * label decoratiu
		 */
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(25, 92, 769, 29);
		frame.getContentPane().add(panel);
		
		/**
		 * amaga tots el elements innesesaris abans de cargar els arxius
		 */
		lblNumeroDeMaquines.show(false);
		lblMconnectades.show(false);
		lblMAmbError.show(false);
		lblMImprimint.show(false);
		lblMFinalitzdes.show(false);
		lblMaquinesConnectades.show(false);
		lblMaquinesAmbError.show(false);
		lblMaquinesImprimint.show(false);
		lblMaquinesFinalitzdes.show(false);
		lblNumMaquines.show(false);
		
		textArea.show(false);
		textArea_1.show(false);
		btnOrdenarPerNomTreballador.show(false);
		btnOrdenarPerNomImpresora.show(false);
		btnOrdenarPerEstat.show(false);
		lblNewLabel.show(false);
		lblNewLabel_1.show(false);
		panel.show(false);
		
		/**
		 * Carrega el arxiu de impresores i el mostra linea per linea al textArea_1
		 * Conta Conta els estats de les impresores per generar una llista amb quantes estan en cada estat
		 */
		JMenuItem mntmCargarImpresores = new JMenuItem("Cargar Impresores");
		mntmCargarImpresores.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				LlistaImpresores.clear();
				
				lblNumeroDeMaquines.show(true);
				lblMconnectades.show(true);
				lblMAmbError.show(true);
				lblMImprimint.show(true);
				lblMFinalitzdes.show(true);
				lblMaquinesConnectades.show(true);
				lblMaquinesAmbError.show(true);
				lblMaquinesImprimint.show(true);
				lblMaquinesFinalitzdes.show(true);
				lblNumMaquines.show(true);
				
				lblNewLabel.show(false);
				lblNewLabel_1.show(true);
				btnOrdenarPerNomImpresora.show(true);
				btnOrdenarPerEstat.show(true);
				btnOrdenarPerNomTreballador.show(false);
				textArea.show(false);
				textArea_1.show(true);
				panel.show(true);
							
				File ficher;
				String linia;
				String contingut="";
				
				ficher = openFile();

				textArea_1.setText("");
				try {
					FileReader fr = new FileReader(ficher);
					BufferedReader br = new BufferedReader(fr);
					int NumImpresores=0;
					while((linia=br.readLine())!=null){
						
						
						List <String> l=Arrays.asList(linia.split("\\|"));
						
						Impresora p = new Impresora(l.get(0),l.get(1),l.get(2),l.get(3),l.get(4),l.get(5));
						System.out.println(p);
						
						LlistaImpresores.add(p);
						NumImpresores=NumImpresores+1;
						lblNumMaquines.setText(Integer.toString(NumImpresores));
						
					}
					fr.close();
					br.close();
					Iterator <Impresora> itr;
					itr=LlistaImpresores.iterator();
					while(itr.hasNext()){
						contingut = contingut + itr.next() + "\n";
					}

					
				} catch (IOException o) {
										
					o.printStackTrace();
				}finally{
					textArea_1.setText("");
					textArea_1.setText(contingut);
					
					Iterator<Impresora> itr;
					itr = LlistaImpresores.iterator();
					int finished=0;
					int printing=0;
					int error=0;
					int connected=0;
					
					while (itr.hasNext()){
						String stat = itr.next().getEstat();
						if(stat.endsWith("CONNECTED")){
							connected = connected +1;
						}
						if(stat.endsWith("ERROR")){
							error = error + 1;
						}
						if(stat.endsWith("PRINTING")){
							printing = printing + 1;
						}
						if(stat.endsWith("FINISHED")){
							finished = finished + 1;
						}
					}
					lblMaquinesConnectades.setText(Integer.toString(connected));
					lblMaquinesAmbError.setText(Integer.toString(error));
					lblMaquinesImprimint.setText(Integer.toString(printing));
					lblMaquinesFinalitzdes.setText(Integer.toString(finished));
					
				}			
			}
			
		});
		mnArxiu.add(mntmCargarImpresores);
		
		
		/**
		 * Carrega el arxiu de treballadors i el mostra linea per linea al textArea
		 */
		JMenuItem mntmCargarTreballadors = new JMenuItem("Cargar Treballadors");
		mntmCargarTreballadors.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				LlistaTreballadors.clear();
				lblNumeroDeMaquines.show(false);
				lblMconnectades.show(false);
				lblMAmbError.show(false);
				lblMImprimint.show(false);
				lblMFinalitzdes.show(false);
				lblMaquinesConnectades.show(false);
				lblMaquinesAmbError.show(false);
				lblMaquinesImprimint.show(false);
				lblMaquinesFinalitzdes.show(false);
				lblNumMaquines.show(false);
				
				lblNewLabel.show(true);
				lblNewLabel_1.show(false);
				btnOrdenarPerNomImpresora.show(false);
				btnOrdenarPerNomTreballador.show(true);
				btnOrdenarPerEstat.show(false);
				textArea.show(true);
				textArea_1.show(false);
				panel.show(true);
				
				File ficher;
				String linia;
				String contingut="";
				
				ficher = openFile();

				textArea.setText("");
				try {
					FileReader fr = new FileReader(ficher);
					BufferedReader br = new BufferedReader(fr);
					while((linia=br.readLine())!=null){
						List <String> l=Arrays.asList(linia.split("\\|"));
						
						Treballdor p = new Treballdor(l.get(0),l.get(1),l.get(2));
						System.out.println(p);
						LlistaTreballadors.add(p);
			
					}
					fr.close();
					br.close();
					Iterator <Treballdor> itr;
					itr=LlistaTreballadors.iterator();
					while(itr.hasNext()){
						contingut = contingut + itr.next() + "\n";
					}		
				} catch (IOException o) {
					
					o.printStackTrace();
				}finally{
					textArea.setText("");
					textArea.setText(contingut);
				}
			}
			
		});
		mnArxiu.add(mntmCargarTreballadors);
		
		/**
		 * Tanca la aplicacio
		 */
		JMenuItem mntmSortir = new JMenuItem("Sortir");
		mntmSortir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnArxiu.add(mntmSortir);
				

		frame.getContentPane().setLayout(null);
		
		/**
		 * Mostra els traballadors idemendentment de si abans ja se l'hi ha carregat un arxiu i el mostra amb tot el seu contingut
		 */
		JButton btnTreballadors = new JButton("Treballadors");
		btnTreballadors.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				lblNumeroDeMaquines.show(false);
				lblMconnectades.show(false);
				lblMAmbError.show(false);
				lblMImprimint.show(false);
				lblMFinalitzdes.show(false);
				lblMaquinesConnectades.show(false);
				lblMaquinesAmbError.show(false);
				lblMaquinesImprimint.show(false);
				lblMaquinesFinalitzdes.show(false);
				lblNumMaquines.show(false);
				
				textArea.show(true);
				textArea_1.show(false);
				btnOrdenarPerNomTreballador.show(true);
				btnOrdenarPerNomImpresora.show(false);
				btnOrdenarPerEstat.show(false);
				lblNewLabel.show(true);
				lblNewLabel_1.show(false);
				panel.show(true);
				
			}
			
		});
		btnTreballadors.setBounds(0, 0, 107, 23);
		frame.getContentPane().add(btnTreballadors);
		
		
		/**
		 * 	Mostra les impresores idemendentment de si abans ja se l'hi ha carregat un arxiu i el mostra amb tot el seu contingut

		 */
		JButton btnImpresores = new JButton("Impresores");
		btnImpresores.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				lblNumeroDeMaquines.show(true);
				lblMconnectades.show(true);
				lblMAmbError.show(true);
				lblMImprimint.show(true);
				lblMFinalitzdes.show(true);
				lblMaquinesConnectades.show(true);
				lblMaquinesAmbError.show(true);
				lblMaquinesImprimint.show(true);
				lblMaquinesFinalitzdes.show(true);
				lblNumMaquines.show(true);
				
				textArea.show(false);
				textArea_1.show(true);
				btnOrdenarPerNomTreballador.show(false);
				btnOrdenarPerNomImpresora.show(true);
				btnOrdenarPerEstat.show(true);
				lblNewLabel.show(false);
				lblNewLabel_1.show(true);
				panel.show(true);
			}
			
		});
		btnImpresores.setBounds(105, 0, 107, 23);
		frame.getContentPane().add(btnImpresores);

	}
	
	/**
	 * Obre una finestra per carregar un arxiu
	 * @return el arxiu carregat
	 */
	public File openFile(){
		JFileChooser fileChooser=new JFileChooser();
		File file;
		fileChooser.showOpenDialog(this);
		file=fileChooser.getSelectedFile();
		return file;
		
	}
}

