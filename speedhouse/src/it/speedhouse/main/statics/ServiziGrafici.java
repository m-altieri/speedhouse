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

	public static void creaIstogramma(String nomeGrafico, String sottotitolo, ArrayList<String> colonne, ArrayList<String[]> dati) {
		
		JFrame frame = new JFrame("speedhouse - " + nomeGrafico);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		DefaultCategoryDataset d = new DefaultCategoryDataset();
		
		for (int i = 0; i < dati.size(); i++) {
			for (int j = 1; j < colonne.size(); j++) {
				d.addValue(Integer.parseInt(dati.get(i)[j]), dati.get(i)[0], colonne.get(j));
			}
		}

		CategoryDataset dataset = d;

		JFreeChart chart = ChartFactory.createBarChart(nomeGrafico,
				"Sezioni" /* x-axis label */, "Voti" /* y-axis label */, dataset);
		chart.addSubtitle(new TextTitle(sottotitolo));
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
