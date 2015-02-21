package classes;

import java.io.File;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

import edu.unm.cs.cs351.tdrl.f10.p1.GraphStructureException;
/**
 * I know I know this class is the equivalent of me banging my elbows against the keyboard until code came out but its late and I dont have time
 * to make this pretty :( im sorry...
 *  but hey! it makes some awesome graphs :D
 * @author niallschavez
 *
 * @param <T>
 */
public class Analysis_Webalyzer<T> {

	MyCrawlState cs;
	Crawler c;
	ConcGraphAnalyzer<T> cga;
	@SuppressWarnings("rawtypes")
	static Analysis_Webalyzer aw;
	double[] resultsAPSP= new double[3];
	double[] resultsAPSPAverage= new double[3];
	double[] resultsCountSCCs= new double[3];
	double[] resultsInDegreeDist= new double[3];
	double[] resultsOutDegreeDist= new double[3];
	double[] resultsMaxOut= new double[3];
	double[] resultsMaxIn= new double[3];
	double[] resultsMinIn= new double[3];
	double[] resultsMinOut= new double[3];
	double[] resultsAvgOut= new double[3];
	double[] resultsAvgIn= new double[3];
	
	@SuppressWarnings({ "rawtypes" })
	public static void main(String[] args) throws GraphStructureException, IOException, ClassNotFoundException{
		aw = new Analysis_Webalyzer();
		//aw.empiricalTestAPSP();
		//aw.empiricalTestAPSPAverage();
		//aw.empiricalCountSCCS(); not working!!!!
		//aw.empiricalInDegreeDistribution();
		//aw.empiricalOutDegreeDistribution();
		//aw.empiricalMaxOut();
		//aw.empiricalMaxIn();
		//aw.empiricalMinIn();
		//aw.empiricalMinOut();
		//aw.empiricalAvgOut();
		//aw.empiricalAvgIn();
	}
	
	public Analysis_Webalyzer(){
		cs=new MyCrawlState();
		cga= new ConcGraphAnalyzer();
		c= new Crawler();
		
	}
	public  void empiricalAvgIn() throws GraphStructureException, IOException, ClassNotFoundException{
		
		//The graph is initialzied here because if I have already compelted 
		//testing on addHref and we know that runs in the alotted time. 

		double totalStartTime= System.nanoTime();
	    tenFifty();
	    
	    double startTime= System.nanoTime();	
	    aw.cga.avgInDegree();
		double endTime=System.nanoTime();
		
		System.out.println("Time required to run APSPAverage(10, 50): "+(endTime-startTime)+"ns");
		resultsAvgIn[0]=(endTime-startTime);
	
		oneHundredTen();
	    startTime= System.nanoTime();
	    aw.cga.avgInDegree();
		endTime=System.nanoTime();
		
		System.out.println("Time required to run APSPAverage(100, 10): "+(endTime-startTime)+"ns");
		resultsAvgIn[1]=(endTime-startTime);
		

		tenOneHundred();
		
	    startTime= System.nanoTime();
	    aw.cga.avgOutDegree();
		endTime=System.nanoTime();
		
		System.out.println("Time required to run APSPAverage(10, 100): "+(endTime-startTime)+"ns");
		resultsAvgIn[2]=(endTime-startTime);
		
		double totalEndTime= System.nanoTime();
		
		double totalTime= totalEndTime-totalStartTime;
		
		
		DefaultPieDataset result = new DefaultPieDataset();
		
        result.setValue("10x50",  (resultsAvgIn[0]/totalTime)*100);
        result.setValue("10x100", (resultsAvgIn[1]/totalTime)*100);
        result.setValue("100x10", (resultsAvgIn[2]/totalTime)*100);
        
        JFreeChart chart = ChartFactory.createPieChart3D(
                "AvgDegreeIn Graph",// chart title
                result,                // data
                true,                  // include legend
                true,
                false
            );

            PiePlot3D plot = (PiePlot3D) chart.getPlot();
            plot.setStartAngle(290);
            plot.setDirection(Rotation.CLOCKWISE);
            plot.setForegroundAlpha(0.5f);
        
		ChartUtilities.saveChartAsJPEG(new File("AvgIn.jpg"), chart, 500, 300);	
	}
	public  void empiricalAvgOut() throws GraphStructureException, IOException, ClassNotFoundException{
		
		//The graph is initialzied here because if I have already compelted 
		//testing on addHref and we know that runs in the alotted time. 

		double totalStartTime= System.nanoTime();
	    tenFifty();
	    
	    double startTime= System.nanoTime();	
	    aw.cga.avgOutDegree();
		double endTime=System.nanoTime();
		
		System.out.println("Time required to run APSPAverage(10, 50): "+(endTime-startTime)+"ns");
		resultsAvgOut[0]=(endTime-startTime);
	
		oneHundredTen();
	    startTime= System.nanoTime();
	    aw.cga.avgOutDegree();
		endTime=System.nanoTime();
		
		System.out.println("Time required to run APSPAverage(100, 10): "+(endTime-startTime)+"ns");
		resultsAvgOut[1]=(endTime-startTime);
		

		tenOneHundred();
		
	    startTime= System.nanoTime();
	    aw.cga.avgOutDegree();
		endTime=System.nanoTime();
		
		System.out.println("Time required to run APSPAverage(10, 100): "+(endTime-startTime)+"ns");
		resultsAvgOut[2]=(endTime-startTime);
		
		double totalEndTime= System.nanoTime();
		
		double totalTime= totalEndTime-totalStartTime;
		
		
		DefaultPieDataset result = new DefaultPieDataset();
		
        result.setValue("10x50",  (resultsAvgOut[0]/totalTime)*100);
        result.setValue("10x100", (resultsAvgOut[1]/totalTime)*100);
        result.setValue("100x10", (resultsAvgOut[2]/totalTime)*100);
        
        JFreeChart chart = ChartFactory.createPieChart3D(
                "AvgDegreeOut Graph",// chart title
                result,                // data
                true,                  // include legend
                true,
                false
            );

            PiePlot3D plot = (PiePlot3D) chart.getPlot();
            plot.setStartAngle(290);
            plot.setDirection(Rotation.CLOCKWISE);
            plot.setForegroundAlpha(0.5f);
        
		ChartUtilities.saveChartAsJPEG(new File("AvgOut.jpg"), chart, 500, 300);	
	}
	public  void empiricalMinOut() throws GraphStructureException, IOException, ClassNotFoundException{
		
		//The graph is initialzied here because if I have already compelted 
		//testing on addHref and we know that runs in the alotted time. 

		double totalStartTime= System.nanoTime();
	    tenFifty();
	    
	    double startTime= System.nanoTime();	
	    aw.cga.minOutDegree();
		double endTime=System.nanoTime();
		
		System.out.println("Time required to run APSPAverage(10, 50): "+(endTime-startTime)+"ns");
		resultsMinOut[0]=(endTime-startTime);
	
		oneHundredTen();
	    startTime= System.nanoTime();
	    aw.cga.minOutDegree();
		endTime=System.nanoTime();
		
		System.out.println("Time required to run APSPAverage(100, 10): "+(endTime-startTime)+"ns");
		resultsMinOut[1]=(endTime-startTime);
		

		tenOneHundred();
		
	    startTime= System.nanoTime();
	    aw.cga.minOutDegree();
		endTime=System.nanoTime();
		
		System.out.println("Time required to run APSPAverage(10, 100): "+(endTime-startTime)+"ns");
		resultsMinOut[2]=(endTime-startTime);
		
		double totalEndTime= System.nanoTime();
		
		double totalTime= totalEndTime-totalStartTime;
		
		
		DefaultPieDataset result = new DefaultPieDataset();
		
        result.setValue("10x50",  (resultsMinOut[0]/totalTime)*100);
        result.setValue("10x100", (resultsMinOut[1]/totalTime)*100);
        result.setValue("100x10", (resultsMinOut[2]/totalTime)*100);
        
        JFreeChart chart = ChartFactory.createPieChart3D(
                "OutDegreeMin Graph",// chart title
                result,                // data
                true,                  // include legend
                true,
                false
            );

            PiePlot3D plot = (PiePlot3D) chart.getPlot();
            plot.setStartAngle(290);
            plot.setDirection(Rotation.CLOCKWISE);
            plot.setForegroundAlpha(0.5f);
        
		ChartUtilities.saveChartAsJPEG(new File("MinOut.jpg"), chart, 500, 300);	

	}
	public  void empiricalMinIn() throws GraphStructureException, IOException, ClassNotFoundException{
		
		//The graph is initialzied here because if I have already compelted 
		//testing on addHref and we know that runs in the alotted time. 

		double totalStartTime= System.nanoTime();
	    tenFifty();
	    
	    double startTime= System.nanoTime();	
	    aw.cga.minInDegree();
		double endTime=System.nanoTime();
		
		System.out.println("Time required to run APSPAverage(10, 50): "+(endTime-startTime)+"ns");
		resultsMinIn[0]=(endTime-startTime);
	
		oneHundredTen();
	    startTime= System.nanoTime();
	    aw.cga.minInDegree();
		endTime=System.nanoTime();
		
		System.out.println("Time required to run APSPAverage(100, 10): "+(endTime-startTime)+"ns");
		resultsMinIn[1]=(endTime-startTime);
		

		tenOneHundred();
		
	    startTime= System.nanoTime();
	    aw.cga.minInDegree();
		endTime=System.nanoTime();
		
		System.out.println("Time required to run APSPAverage(10, 100): "+(endTime-startTime)+"ns");
		resultsMinIn[2]=(endTime-startTime);
		
		double totalEndTime= System.nanoTime();
		
		double totalTime= totalEndTime-totalStartTime;
		
		
		DefaultPieDataset result = new DefaultPieDataset();
        result.setValue("10x50",  (resultsMinIn[0]/totalTime)*100);
        result.setValue("10x100", (resultsMinIn[1]/totalTime)*100);
        result.setValue("100x10", (resultsMinIn[2]/totalTime)*100);
        
        JFreeChart chart = ChartFactory.createPieChart3D(
                "inDegreeMin Graph",// chart title
                result,                // data
                true,                  // include legend
                true,
                false
            );

            PiePlot3D plot = (PiePlot3D) chart.getPlot();
            plot.setStartAngle(290);
            plot.setDirection(Rotation.CLOCKWISE);
            plot.setForegroundAlpha(0.5f);
        
		ChartUtilities.saveChartAsJPEG(new File("MinIn.jpg"), chart, 500, 300);	

	}
	public  void empiricalMaxIn() throws GraphStructureException, IOException, ClassNotFoundException{
		
		//The graph is initialzied here because if I have already compelted 
		//testing on addHref and we know that runs in the alotted time. 

		double totalStartTime= System.nanoTime();
	    tenFifty();
	    
	    double startTime= System.nanoTime();	
	    aw.cga.maxInDegree();
		double endTime=System.nanoTime();
		
		System.out.println("Time required to run APSPAverage(10, 50): "+(endTime-startTime)+"ns");
		resultsMaxIn[0]=(endTime-startTime);
	
		oneHundredTen();
	    startTime= System.nanoTime();
	    aw.cga.maxInDegree();
		endTime=System.nanoTime();
		
		System.out.println("Time required to run APSPAverage(100, 10): "+(endTime-startTime)+"ns");
		resultsMaxIn[1]=(endTime-startTime);
		

		tenOneHundred();
		
	    startTime= System.nanoTime();
	    aw.cga.maxInDegree();
		endTime=System.nanoTime();
		
		System.out.println("Time required to run APSPAverage(10, 100): "+(endTime-startTime)+"ns");
		resultsMaxIn[2]=(endTime-startTime);
		
		double totalEndTime= System.nanoTime();
		
		double totalTime= totalEndTime-totalStartTime;
		
		
		DefaultPieDataset result = new DefaultPieDataset();
        result.setValue("10x50",  (resultsMaxIn[0]/totalTime)*100);
        result.setValue("10x100", (resultsMaxIn[1]/totalTime)*100);
        result.setValue("100x10", (resultsMaxIn[2]/totalTime)*100);
        
        JFreeChart chart = ChartFactory.createPieChart3D(
                "inDegreeMax Graph",// chart title
                result,                // data
                true,                  // include legend
                true,
                false
            );

            PiePlot3D plot = (PiePlot3D) chart.getPlot();
            plot.setStartAngle(290);
            plot.setDirection(Rotation.CLOCKWISE);
            plot.setForegroundAlpha(0.5f);
        
		ChartUtilities.saveChartAsJPEG(new File("MaxIn.jpg"), chart, 500, 300);	

	}
	public  void empiricalMaxOut() throws GraphStructureException, IOException, ClassNotFoundException{
		
		//The graph is initialzied here because if I have already compelted 
		//testing on addHref and we know that runs in the alotted time. 

		double totalStartTime= System.nanoTime();
	    tenFifty();
	    
	    double startTime= System.nanoTime();	
	    aw.cga.maxOutDegree();
		double endTime=System.nanoTime();
		
		System.out.println("Time required to run APSPAverage(10, 50): "+(endTime-startTime)+"ns");
		resultsMaxOut[0]=(endTime-startTime);
	
		oneHundredTen();
	    startTime= System.nanoTime();
	    aw.cga.maxOutDegree();
		endTime=System.nanoTime();
		
		System.out.println("Time required to run APSPAverage(100, 10): "+(endTime-startTime)+"ns");
		resultsMaxOut[1]=(endTime-startTime);
		

		tenOneHundred();
		
	    startTime= System.nanoTime();
	    aw.cga.maxOutDegree();
		endTime=System.nanoTime();
		
		System.out.println("Time required to run APSPAverage(10, 100): "+(endTime-startTime)+"ns");
		resultsMaxOut[2]=(endTime-startTime);
		
		double totalEndTime= System.nanoTime();
		
		double totalTime= totalEndTime-totalStartTime;
		
		
		DefaultPieDataset result = new DefaultPieDataset();
        result.setValue("10x50",  (resultsMaxOut[0]/totalTime)*100);
        result.setValue("10x100", (resultsMaxOut[1]/totalTime)*100);
        result.setValue("100x10", (resultsMaxOut[2]/totalTime)*100);
        
        JFreeChart chart = ChartFactory.createPieChart3D(
                "outDegreeMax Graph",// chart title
                result,                // data
                true,                  // include legend
                true,
                false
            );

            PiePlot3D plot = (PiePlot3D) chart.getPlot();
            plot.setStartAngle(290);
            plot.setDirection(Rotation.CLOCKWISE);
            plot.setForegroundAlpha(0.5f);
        
		ChartUtilities.saveChartAsJPEG(new File("MaxOut.jpg"), chart, 500, 300);	

	}
	public  void empiricalTestAPSP() throws GraphStructureException, IOException, ClassNotFoundException{
		
		//The graph is initialzied here because if I have already compelted 
		//testing on addHref and we know that runs in the alotted time. 
		double totalStartTime= System.nanoTime();

	    tenFifty();
	    double startTime= System.nanoTime();	
		aw.cga.allPairsShortestPaths();
		double endTime=System.nanoTime();
		
		System.out.println("Time required to run APSP(10, 50): "+(endTime-startTime)+"ns");
		resultsAPSP[0]=(endTime-startTime);
		
		tenOneHundred();
	    startTime= System.nanoTime();
		aw.cga.allPairsShortestPaths();
		endTime=System.nanoTime();
		
		System.out.println("Time required to run APSP(10, 100): "+(endTime-startTime)+"ns");
		resultsAPSP[1]=(endTime-startTime);
		

		oneHundredTen();
		
	    startTime= System.nanoTime();
		aw.cga.allPairsShortestPaths();
		endTime=System.nanoTime();
		
		System.out.println("Time required to run APSP(100, 10): "+(endTime-startTime)+"ns");
		resultsAPSP[2]=(endTime-startTime);

		double totalEndTime= System.nanoTime();
		
		/*
		fiftyOneHundred();
	    startTime= System.nanoTime();
		aw.cga.allPairsShortestPaths();
		endTime=System.nanoTime();
		
		System.out.println("Time required to run APSP(50, 100): "+(endTime-startTime)+"ns");
		resultsAPSP[3]=(endTime-startTime);*/
		
		
		
		double totalTime= totalEndTime-totalStartTime;
		
		
		DefaultPieDataset result = new DefaultPieDataset();
        result.setValue("10x50", (resultsAPSP[0]/totalTime)*100);
        result.setValue("10x100", (resultsAPSP[1]/totalTime)*100);
        result.setValue("100x10", (resultsAPSP[2]/totalTime)*100);
        
        JFreeChart chart = ChartFactory.createPieChart3D(
                "APSP Graph",  		   // chart title
                result,                // data
                true,                  // include legend
                true,
                false
            );

            PiePlot3D plot = (PiePlot3D) chart.getPlot();
            plot.setStartAngle(290);
            plot.setDirection(Rotation.CLOCKWISE);
            plot.setForegroundAlpha(0.5f);
        
		ChartUtilities.saveChartAsJPEG(new File("APSP.jpg"), chart, 500, 300);	
	}
	public  void empiricalTestAPSPAverage() throws GraphStructureException, IOException, ClassNotFoundException{
		
		//The graph is initialzied here because if I have already compelted 
		//testing on addHref and we know that runs in the alotted time. 

		double totalStartTime= System.nanoTime();
	    tenFifty();
	    
	    double startTime= System.nanoTime();	
	    aw.cga.allPairsShortestPaths();
		aw.cga.avgShortestPathDistance();
		double endTime=System.nanoTime();
		
		System.out.println("Time required to run APSPAverage(10, 50): "+(endTime-startTime)+"ns");
		resultsAPSPAverage[0]=(endTime-startTime);
	
		oneHundredTen();
	    startTime= System.nanoTime();
	    aw.cga.allPairsShortestPaths();
		aw.cga.avgShortestPathDistance();
		endTime=System.nanoTime();
		
		System.out.println("Time required to run APSPAverage(100, 10): "+(endTime-startTime)+"ns");
		resultsAPSPAverage[1]=(endTime-startTime);
		

		tenOneHundred();
		
	    startTime= System.nanoTime();
	    aw.cga.allPairsShortestPaths();
		aw.cga.avgShortestPathDistance();
		endTime=System.nanoTime();
		
		System.out.println("Time required to run APSPAverage(10, 100): "+(endTime-startTime)+"ns");
		resultsAPSPAverage[2]=(endTime-startTime);
		
		double totalEndTime= System.nanoTime();
		
		double totalTime= totalEndTime-totalStartTime;
		
		
		DefaultPieDataset result = new DefaultPieDataset();
        result.setValue("10x50",  (resultsAPSPAverage[0]/totalTime)*100);
        result.setValue("10x100", (resultsAPSPAverage[1]/totalTime)*100);
        result.setValue("100x10", (resultsAPSPAverage[2]/totalTime)*100);
        
        JFreeChart chart = ChartFactory.createPieChart3D(
                "APSPAverage Graph",  		   // chart title
                result,                // data
                true,                  // include legend
                true,
                false
            );

            PiePlot3D plot = (PiePlot3D) chart.getPlot();
            plot.setStartAngle(290);
            plot.setDirection(Rotation.CLOCKWISE);
            plot.setForegroundAlpha(0.5f);
        
		ChartUtilities.saveChartAsJPEG(new File("APSPAverage.jpg"), chart, 500, 300);	

	}
	public  void empiricalCountSCCS() throws GraphStructureException, IOException, ClassNotFoundException{
		
		//The graph is initialzied here because if I have already compelted 
		//testing on addHref and we know that runs in the alotted time. 

		double totalStartTime= System.nanoTime();
	    tenFifty();
	    
	    double startTime= System.nanoTime();	
	    aw.cga.countSCCs();
		double endTime=System.nanoTime();
		
		System.out.println("Time required to run APSPAverage(10, 50): "+(endTime-startTime)+"ns");
		resultsCountSCCs[0]=(endTime-startTime);
	
		oneHundredTen();
	    startTime= System.nanoTime();
	    aw.cga.countSCCs();
		endTime=System.nanoTime();
		
		System.out.println("Time required to run APSPAverage(100, 10): "+(endTime-startTime)+"ns");
		resultsCountSCCs[1]=(endTime-startTime);
		

		tenOneHundred();
		
	    startTime= System.nanoTime();
	    aw.cga.countSCCs();
		endTime=System.nanoTime();
		
		System.out.println("Time required to run APSPAverage(10, 100): "+(endTime-startTime)+"ns");
		resultsCountSCCs[2]=(endTime-startTime);
		
		double totalEndTime= System.nanoTime();
		
		double totalTime= totalEndTime-totalStartTime;
		
		
		DefaultPieDataset result = new DefaultPieDataset();
        result.setValue("10x50",  (resultsAPSPAverage[0]/totalTime)*100);
        result.setValue("10x100", (resultsAPSPAverage[1]/totalTime)*100);
        result.setValue("100x10", (resultsAPSPAverage[2]/totalTime)*100);
        
        JFreeChart chart = ChartFactory.createPieChart3D(
                "CountSCCs Graph",  		   // chart title
                result,                // data
                true,                  // include legend
                true,
                false
            );

            PiePlot3D plot = (PiePlot3D) chart.getPlot();
            plot.setStartAngle(290);
            plot.setDirection(Rotation.CLOCKWISE);
            plot.setForegroundAlpha(0.5f);
        
		ChartUtilities.saveChartAsJPEG(new File("countSCCs.jpg"), chart, 500, 300);	

	}
	public  void empiricalInDegreeDistribution() throws GraphStructureException, IOException, ClassNotFoundException{
		
		//The graph is initialzied here because if I have already compelted 
		//testing on addHref and we know that runs in the alotted time. 

		double totalStartTime= System.nanoTime();
	    tenFifty();
	    
	    double startTime= System.nanoTime();	
	    aw.cga.outDegreeDistribution();
		double endTime=System.nanoTime();
		
		System.out.println("Time required to run APSPAverage(10, 50): "+(endTime-startTime)+"ns");
		resultsInDegreeDist[0]=(endTime-startTime);
	
		oneHundredTen();
	    startTime= System.nanoTime();
	    aw.cga.outDegreeDistribution();
		endTime=System.nanoTime();
		
		System.out.println("Time required to run APSPAverage(100, 10): "+(endTime-startTime)+"ns");
		resultsInDegreeDist[1]=(endTime-startTime);
		

		tenOneHundred();
		
	    startTime= System.nanoTime();
	    aw.cga.outDegreeDistribution();
		endTime=System.nanoTime();
		
		System.out.println("Time required to run APSPAverage(10, 100): "+(endTime-startTime)+"ns");
		resultsInDegreeDist[2]=(endTime-startTime);
		
		double totalEndTime= System.nanoTime();
		
		double totalTime= totalEndTime-totalStartTime;
		
		
		DefaultPieDataset result = new DefaultPieDataset();
        result.setValue("10x50",  (resultsInDegreeDist[0]/totalTime)*100);
        result.setValue("10x100", (resultsInDegreeDist[1]/totalTime)*100);
        result.setValue("100x10", (resultsInDegreeDist[2]/totalTime)*100);
        
        JFreeChart chart = ChartFactory.createPieChart3D(
                "inDegreeDistribution Graph",  		   // chart title
                result,                // data
                true,                  // include legend
                true,
                false
            );

            PiePlot3D plot = (PiePlot3D) chart.getPlot();
            plot.setStartAngle(290);
            plot.setDirection(Rotation.CLOCKWISE);
            plot.setForegroundAlpha(0.5f);
        
		ChartUtilities.saveChartAsJPEG(new File("inDegreeDist.jpg"), chart, 500, 300);	

	}
	public  void empiricalOutDegreeDistribution() throws GraphStructureException, IOException, ClassNotFoundException{
		
		//The graph is initialzied here because if I have already compelted 
		//testing on addHref and we know that runs in the alotted time. 

		double totalStartTime= System.nanoTime();
	    tenFifty();
	    
	    double startTime= System.nanoTime();	
	    aw.cga.outDegreeDistribution();
		double endTime=System.nanoTime();
		
		System.out.println("Time required to run APSPAverage(10, 50): "+(endTime-startTime)+"ns");
		resultsOutDegreeDist[0]=(endTime-startTime);
	
		oneHundredTen();
	    startTime= System.nanoTime();
	    aw.cga.outDegreeDistribution();
		endTime=System.nanoTime();
		
		System.out.println("Time required to run APSPAverage(100, 10): "+(endTime-startTime)+"ns");
		resultsOutDegreeDist[1]=(endTime-startTime);
		

		tenOneHundred();
		
	    startTime= System.nanoTime();
	    aw.cga.outDegreeDistribution();
		endTime=System.nanoTime();
		
		System.out.println("Time required to run APSPAverage(10, 100): "+(endTime-startTime)+"ns");
		resultsOutDegreeDist[2]=(endTime-startTime);
		
		double totalEndTime= System.nanoTime();
		
		double totalTime= totalEndTime-totalStartTime;
		
		
		DefaultPieDataset result = new DefaultPieDataset();
        result.setValue("10x50",  (resultsOutDegreeDist[0]/totalTime)*100);
        result.setValue("10x100", (resultsOutDegreeDist[1]/totalTime)*100);
        result.setValue("100x10", (resultsOutDegreeDist[2]/totalTime)*100);
        
        JFreeChart chart = ChartFactory.createPieChart3D(
                "outDegreeDistribution Graph",// chart title
                result,                // data
                true,                  // include legend
                true,
                false
            );

            PiePlot3D plot = (PiePlot3D) chart.getPlot();
            plot.setStartAngle(290);
            plot.setDirection(Rotation.CLOCKWISE);
            plot.setForegroundAlpha(0.5f);
        
		ChartUtilities.saveChartAsJPEG(new File("OutDegreeDist.jpg"), chart, 500, 300);	

	}

	public void tenFifty() throws GraphStructureException, IOException, ClassNotFoundException{
		int j=0; 
		
		cs=new MyCrawlState();
		for(int i=0; i<10; i++){
			while( (j/50)!=i){
				aw.cs.addHref((""+i),(""+j));
				//System.out.println(i+"->"+j);
				j++;
			}
		}
		cs.saveYourself("crawl_dump.dat");
		cga.loader("crawl_dump.dat");

	}
	public void tenOneHundred() throws GraphStructureException, IOException, ClassNotFoundException{
		int j=0; 
		
		cs=new MyCrawlState();
		j=0; 
		for(int i=0; i<10; i++){
			while( (j/100)!=i){
				aw.cs.addHref((""+i),(""+j));

				j++;
			}
		}
		cs.saveYourself("crawl_dump.dat");
		cga.loader("crawl_dump.dat");
	}
	public void oneHundredTen() throws GraphStructureException, IOException, ClassNotFoundException{
		int j=0; 
		cs=new MyCrawlState();
		for(int i=0; i < 100; i++){
			while( (j / 10)!=i){
				aw.cs.addHref((""+i),(""+j));

				j++;
			}
		}
		cs.saveYourself("crawl_dump.dat");
		cga.loader("crawl_dump.dat");
	}
	
}
