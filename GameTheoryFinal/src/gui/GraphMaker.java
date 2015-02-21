package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.title.Title;
import org.jfree.data.category.DefaultCategoryDataset;


public class GraphMaker {

	/**
	 * @param args
	 */
	LinkedList<String> linesOfData= new LinkedList<String>();
	LinkedList<String[]> data= new LinkedList<String[]>();
	public GraphMaker(String fileName){
		try {
			read(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		splitter();
		for (String[] s: data){
			for( String i: s){
				System.out.print(i+" ");
			}
			System.out.println();
		}
		lineGraph();

	}
	private void splitter() {
		for (String line : linesOfData){
			data.add( line.split(" "));
		}
		
	}
	public void read(String fileName) throws IOException {
		
	    //StringBuilder text = new StringBuilder();
	    //String NL = System.getProperty("line.separator");
	    Scanner scanner = new Scanner(new FileInputStream (fileName));
	    try {
	      while (scanner.hasNextLine()){
	    	  linesOfData.add(scanner.nextLine()+"");
	    	 //text.append(scanner.nextLine() + NL);
	      }
	    }
	    finally{
	      scanner.close();
	    }
	    //System.out.println("Data: "+ text);
	  }
	
	private void  lineGraph(){
		String creature1= "Creature 1";
		String creature2= "Creature 2";
		
		double sumCreature1=0.0;
		double sumCreature2=0.0;
		
		double sumSquaredC1 = 0.0;
		double sumSquaredC2 = 0.0;
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		int counter=0;
		dataset.addValue(Integer.parseInt(data.peekFirst()[0]),creature1,counter+"");
		sumCreature1+=Integer.parseInt(data.peekFirst()[0]); 
		
		dataset.addValue(Integer.parseInt(data.peekFirst()[1]),creature2,counter+"");
		sumCreature2+=Integer.parseInt(data.peekFirst()[1]); 
		
		for (String[] s: data){
			
			sumCreature1+=Integer.parseInt(s[10]);
			sumCreature2+=Integer.parseInt(s[11]);

			
			dataset.addValue(Integer.parseInt(s[10]),creature1,counter+"");
			dataset.addValue(Integer.parseInt(s[11]),creature2,counter+"");
			
			counter++;
		}
		calcMeans(sumCreature1, sumCreature2, data.size());

		//Add the series to your data set
		//dataset.addSeries(creature1Series);
		//dataset.addSeries(creature2Series);
		//  Generate the graph
		
		
		JFreeChart chart= ChartFactory.createLineChart("Creature 1 Vs. Creature 2", "Generation Number", // x-axis Label
		        "Population Level", // y-axis Label
		        dataset, // Dataset
		        PlotOrientation.VERTICAL,true, // Show Legend
		        true, // Use tooltips
		        false); // Configure chart to generate URLs?
		/* 
		 * 
		 * 
		 * Mean Creature 1;
		 * 
		 * 
		 * 
		 */
		double meanC1= sumCreature1/data.size();
		long y=(long)meanC1*100;
        double meanCreature1= (double)y/100;
		/*
		 * 
		 * 
		 * Mean Creature 2
		 * 
		 * 
		 * 
		 */
        double meanC2= sumCreature2/data.size();
		long x=(long)meanC2*100;
        double meanCreature2= (double)x/100;
		
        
        /*
         * Variance 
         */
        sumSquaredC1+= (Integer.parseInt(data.peekFirst()[0]) - meanC1) * 
        			   (Integer.parseInt(data.peekFirst()[0]) - meanC1);
    	
    	sumSquaredC2+= (Integer.parseInt(data.peekFirst()[1]) - meanC2) * 
    			       (Integer.parseInt(data.peekFirst()[1]) - meanC2);
    	
        for (String[] s: data){
        	
        	sumSquaredC1+= (Integer.parseInt(s[10]) - meanC1) *
        				   (Integer.parseInt(s[10]) - meanC1);
        	
        	sumSquaredC2+= (Integer.parseInt(s[11]) - meanC2) *
        				   (Integer.parseInt(s[11]) - meanC2);
        }
        
        
        double varc1= Math.sqrt(sumSquaredC1/data.size()); 
        double varc2= Math.sqrt(sumSquaredC2/data.size());
        
		long var1=(long)varc1*100;
        varc1= (double)var1/100;
        
        long var2=(long)varc2*100;
        varc2= (double)var2/100;
        
        
        Title mean= new TextTitle(" Mean for Creature 1 is: "+meanCreature1+ "\nMean for Creature 2 is "+meanCreature2 );
		Title variance= new TextTitle(" Variance for Creature 1 is: "+varc1+ "\nVariance for Creature 2 is "+varc2 );
		
		
		chart.addSubtitle(0,mean);
		chart.addSubtitle(0,variance);
		
		final CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.WHITE);

        // customise the range axis...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setAutoRangeIncludesZero(true);
        
		final LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
		// renderer.setDrawShapes(true);
		renderer.setSeriesStroke(
          0, new BasicStroke(
              2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
              1.0f, new float[] {10.0f, 6.0f}, 0.0f
          )
      );
      renderer.setSeriesStroke(
          1, new BasicStroke(
              2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
              1.0f, new float[] {6.0f, 6.0f}, 0.0f
          )
      );
		            
        try {
            ChartUtilities.saveChartAsJPEG(new File("./LineChart.jpg"), chart, 500,300);
        } catch (IOException e) {
            System.err.println("Problem occurred creating chart.");
        }
		        
	}
	private void calcMeans(double sumCreature1, double sumCreature2, int size) {
		System.out.println("Mean for Creature1= "+ sumCreature1/size+ "\n Mean for Creature2= "+ sumCreature2/size+"");
		
	}
}

