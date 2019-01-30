package pacote;

/**
 * @author Roberto S. Brasil
 * @since 07/12/2018
 * @version 1.6*/
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class JCalendar extends JDialog{
	private Dimension d;
	private Calendar c;
	private String dia, mes, ano, mesExtenso;
	private JPanel pnorth,pcenter;
	private String formato;
	private JComboBox comboAno, comboMes;
	private JLabel labelMes, labelAno;
	private Border linha;
	
	/**
	 * M�todo construtor sem par�metros. Cria o JDialog e o posiciona no centro da tela.
	 * @param Nenhum*/
	public JCalendar(){
		formato = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		dia = "";
		mes = "";
		ano = "";
		mesExtenso = getMesExtenso();
		d = Toolkit.getDefaultToolkit().getScreenSize();
		pcenter = new JPanel();
		pnorth = new JPanel();
		c = new GregorianCalendar();
		labelMes = new JLabel("M�S:");
		labelAno = new JLabel("ANO:");
		comboMes = new JComboBox(getMeses());
		comboAno = new JComboBox(getAnos());
		linha = BorderFactory.createLineBorder(Color.BLACK);
		
		pcenter.setBorder(linha);
		pnorth.setBorder(linha);
		this.setResizable(false);
		this.setModal(true);
		this.setUndecorated(true);
		this.setSize(d.width/5,d.height/5);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		comboMes.setSelectedItem(mesExtenso);
		
		comboMes.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				pcenter.removeAll();
				preenchePainel(comboMes.getSelectedItem().toString(),comboAno.getSelectedItem().toString());
				JCalendar.this.setVisible(true);
			}
		});
		
		pnorth.add(labelMes);
		pnorth.add(comboMes);
		pnorth.add(labelAno);
		pnorth.add(comboAno);
		this.add(pcenter, "Center");
		this.add(pnorth,"North");
		
		this.setVisible(true);
	}
	
	/**
	 * M�todo que adiciona os dias do m�s e ano selecionados no JDialog.
	 * Cada dia � um JButton equipado com ActionListener, quando clicado fecha o JDialog e salva o dia, m�s e ano.
	 * @param String - mes
	 * @param String - ano*/
	protected void preenchePainel(final String mes, final String ano) {
		c.set(Integer.parseInt(ano), getNumeroMes(mes), 1);
		int qtdDias = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		int i;
		for(i = 1; i <= qtdDias;i++){
			final JButton botao = new JButton(String.valueOf(i));
			pcenter.add(botao);
			botao.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					JCalendar.this.ano = ano;
					JCalendar.this.mes = String.valueOf(getNumeroMes(mes)+1);
					JCalendar.this.dia = botao.getText();
					if(Integer.parseInt(JCalendar.this.dia) < 10)
						JCalendar.this.dia = "0"+JCalendar.this.dia;
					if(Integer.parseInt(JCalendar.this.mes) < 10)
						JCalendar.this.mes = "0"+JCalendar.this.mes;
					JCalendar.this.setModal(false);
					JCalendar.this.dispose();
				}
			});
		}
		pcenter.setLayout(new GridLayout(7,5));
	}

	/**
	 * M�todo que cria e preenche um String[] com 50 posi��es, iniciando do ano atual at� o atual-50.
	 * @param Nenhum
	 * @return Retorna o array.*/
	private String[] getAnos() {
		int ano = Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date()));
		String[] anos = new String[50];
		for(int i = 0; i < anos.length;i++){
			anos[i] = String.valueOf(ano - i);
		}
		return anos;
	}

	/**
	 * M�todo que cria um String[] com os nomes dos meses do ano.
	 * @param Nenhum
	 * @return retorna o nome do m�s atual*/
	private String getMesExtenso() {
		String mes = formato.split("/")[1];
		String[] meses = new DateFormatSymbols(new Locale("pt","BR")).getMonths();
		return meses[Integer.parseInt(mes)-1];
	}
	
	/**
	 * M�todo que cria um String[] com os nomes dos meses do ano.
	 * @param String - m�s (o nome do m�s escolhido)
	 * @return o n�mero do m�s escolhido*/
	private int getNumeroMes(String mes) {
		String[] meses = new DateFormatSymbols(new Locale("pt","BR")).getMonths();
		for(int i = 0; i < meses.length;i++){
			if(meses[i].equals(mes))
				return i;
		}
		return 0;
	}
	
	/**
	 * M�todo que cria um String[] contendo os nomes dos meses do ano.
	 * @return retorna o array*/
	private String[] getMeses() {
		return new DateFormatSymbols(new Locale("pt","BR")).getMonths();
	}
	
	/**
	 * M�todo construtor com par�metros. Cria o JDialog e o posiciona nas coordenadas passadas.
	 * @param int - x
	 * @param int - y*/
	public JCalendar(int x, int y){
		formato = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		dia = "";
		mes = "";
		ano = "";
		mesExtenso = getMesExtenso();
		d = Toolkit.getDefaultToolkit().getScreenSize();
		pcenter = new JPanel();
		pnorth = new JPanel();
		c = Calendar.getInstance();
		labelMes = new JLabel("M�S:");
		labelAno = new JLabel("ANO:");
		comboMes = new JComboBox(getMeses());
		comboAno = new JComboBox(getAnos());
		linha = BorderFactory.createLineBorder(Color.BLACK);
		
		pcenter.setBorder(linha);
		pnorth.setBorder(linha);
		this.setResizable(false);
		this.setModal(true);
		this.setUndecorated(true);
		this.setSize(d.width/5,d.height/5);
		this.setLocation(x, y);
		this.setLayout(new BorderLayout());
		comboMes.setSelectedItem(mesExtenso);
		
		comboMes.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				pcenter.removeAll();
				preenchePainel(comboMes.getSelectedItem().toString(),comboAno.getSelectedItem().toString());
				JCalendar.this.setVisible(true);
			}
		});
		
		pnorth.add(labelMes);
		pnorth.add(comboMes);
		pnorth.add(labelAno);
		pnorth.add(comboAno);
		this.add(pcenter, "Center");
		this.add(pnorth,"North");
		
		this.setVisible(true);
	}
	
	/**
	 * Acessa o ano escolhido.
	 * @return retorna o ano*/
	public String getAno(){
		return this.ano;
	}
	
	/**
	 * Acessa o m�s escolhido.
	 * @return retorna o m�s*/
	public String getMes(){
		return this.mes;
	}
	
	/**
	 * Acessa o dia escolhido.
	 * @return retorna o dia*/
	public String getDia(){
		return this.dia;
	}
	
	/**
	 * Forma simplificada de chamar a classe.
	 * @return retorna a data completa formatada em dd/MM/yyyy*/
	public static String getData(){
		JCalendar call = new JCalendar();
		return call.getDia()+"/"+call.getMes()+"/"+call.getAno();
	}
}
