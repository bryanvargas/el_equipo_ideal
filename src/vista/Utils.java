package vista;

import java.awt.GraphicsEnvironment;
import java.io.File;
import java.net.URL;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.ImageIcon;


public class Utils {
	
	    public final static String pdf = "pdf";
	    public final static String png = "png";
	    public final static String txt = "txt";
	    public final static String carp = "carp";
		
	
	  
	    public static Map<String, String> Temas(){
	    	Map<String, String> temas = new TreeMap<String, String>();
//	    	temas.put("Classy", "de.javasoft.plaf.synthetica.SyntheticaClassyLookAndFeel");
//	    	temas.put("AluOxide","de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel");
//	    	temas.put("BlueLight","de.javasoft.plaf.synthetica.SyntheticaBlueLightLookAndFeel");
    		temas.put("BlackEye","de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel");
//			temas.put("Simple2D","de.javasoft.plaf.synthetica.SyntheticaSimple2DLookAndFeel");
			temas.put("WhiteVision","de.javasoft.plaf.synthetica.SyntheticaWhiteVisionLookAndFeel");
			temas.put("SkyMetallic","de.javasoft.plaf.synthetica.SyntheticaSkyMetallicLookAndFeel");
			temas.put("MauveMetallic","de.javasoft.plaf.synthetica.SyntheticaMauveMetallicLookAndFeel");
			temas.put("BlackMoon","de.javasoft.plaf.synthetica.SyntheticaBlackMoonLookAndFeel");
			temas.put("BlackStar","de.javasoft.plaf.synthetica.SyntheticaBlackStarLookAndFeel");
			temas.put("BlueIce","de.javasoft.plaf.synthetica.SyntheticaBlueIceLookAndFeel");
			temas.put("BlueMoon","de.javasoft.plaf.synthetica.SyntheticaBlueMoonLookAndFeel");
			temas.put("BlueSteel","de.javasoft.plaf.synthetica.SyntheticaBlueSteelLookAndFeel");
			temas.put("GreenDream","de.javasoft.plaf.synthetica.SyntheticaGreenDreamLookAndFeel");
			temas.put("OrangeMetallic","de.javasoft.plaf.synthetica.SyntheticaOrangeMetallicLookAndFeel");
			temas.put("SilverMoon","de.javasoft.plaf.synthetica.SyntheticaSilverMoonLookAndFeel");
			
			temas.put("Metal","javax.swing.plaf.metal.MetalLookAndFeel");
			temas.put("Nimbus","javax.swing.plaf.nimbus.NimbusLookAndFeel");
			temas.put("Motif","com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			temas.put("Windows","com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			temas.put("WindowsClassic","com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
			return temas;
	    	
	    }
	 
	 
	 
	 static final String[] FONT_NAMES = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	    //arreglo con estilos de fuente
	  static final String[] FONT_STYLES = {
	        "Normal", "Bold", "Italic", "Bold Italic"
	 };
	    //arreglo con tamaños de fuente
	 static final String[] FONT_SIZES = {
	        "8", "9", "10", "11", "12", "13", "14", "16", "18", "20", "24", "28", "32", "48", "72"
	 };
	 
	 static final String[] PAISES = {
	        "Argentina", "Bolivia", "Brasil", "Chile","Colombia","Ecuador","Jamaica","Mexico","Paraguay","Peru","Uruguay","Venezuela"
	 };
	 

	
	 public static ImageIcon createIcon(String path) {
		URL url = System.class.getResource(path);
		ImageIcon icon = new ImageIcon(url);
		return icon;
	}
	 
	 public static String getExtension(File f) {
	        String ext = null;
	        String s = f.getName();
	        int i = s.lastIndexOf('.');

	        if (i > 0 &&  i < s.length() - 1) {
	            ext = s.substring(i+1).toLowerCase();
	        }
	        return ext;
	    }

	
	    protected static ImageIcon createImageIcon(String path) {
	        java.net.URL imgURL = Utils.class.getResource(path);	      
        	if (imgURL != null) 
		       return new ImageIcon(imgURL);	        
	      
            return null;
	       
	    }
	    
	    public static String getFileExtension(String name){
			int $pointIdex = name.lastIndexOf(".");
			
			if ($pointIdex == -1) {
				return null;
			}
			if ($pointIdex == name.length()-1) {
				return null;
			}
			return name.substring($pointIdex+1, name.length());
		}
}
