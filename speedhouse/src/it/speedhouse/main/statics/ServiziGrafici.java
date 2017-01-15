package it.speedhouse.main.statics;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;

public abstract class ServiziGrafici {

	public static void creaIstogramma(String nomeGrafico, String sottotitolo, String[] colonne) {
		
		JFrame frame = new JFrame("Grafico referendum");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		DefaultCategoryDataset d = new DefaultCategoryDataset();
		
		ArrayList<String[]> dati = ServiziDB.selezionaColonne("nomeDb", "mkmk_referendum_mod", colonne); //modificare
		//TODO
		for (int i = 0; i < dati.size(); i++) {
			for (int j = 1; j < colonne.length; j++) {
				d.addValue(Integer.parseInt(dati.get(i)[j]), dati.get(i)[0], colonne[j]);

			}
		}

		CategoryDataset dataset = d;

		JFreeChart chart = ChartFactory.createBarChart("Analisi referendum",
				"Sezioni" /* x-axis label */, "Voti" /* y-axis label */, dataset);
		chart.addSubtitle(new TextTitle("Sezione - Maschi - Femmine"));
		chart.setBackgroundPaint(Color.GREEN);
		CategoryPlot plot = (CategoryPlot) chart.getPlot();

		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setDrawBarOutline(false);
		chart.getLegend().setFrame(BlockBorder.NONE);

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setFillZoomRectangle(true);
		chartPanel.setMouseWheelEnabled(true);
		chartPanel.setPreferredSize(new Dimension(500, 270));
		frame.setContentPane(chartPanel);

		frame.pack();
		RefineryUtilities.centerFrameOnScreen(frame);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
	}
}
