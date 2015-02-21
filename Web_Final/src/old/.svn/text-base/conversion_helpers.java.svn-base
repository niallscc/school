package old;

/**
 * Helper class for miscellaneous operations that renderer must do that don't really fit in
 * anywhere else.
 * @author Ian Mallett
 */
public class conversion_helpers {
	private static int offset;
	
	private final static String regex_top = "((TOP)|(top)):\\s*";
	private final static String regex_z = "((Z-INDEX)|(z-index)):\\s*[0-9]+;";
	private final static String regex_height = "((HEIGHT)|(height)):\\s*";
	
	/** Resets the helpers.  Should be called every time before the "String .combine_template_and_page_blocks(...)" method is used. */
	public final static void reset_combine() {
		offset = 0;
	}
	/** Combines two components.  Primary purpose is to set up z-indexing and relative positioning. */
	public final static String combine_template_and_page_blocks(String c1, String c2, boolean template_has_menu) {
		final boolean relatively_positioned = true;
		if (relatively_positioned) {
			if (c1.equals("")) return c2;
			if (c2.equals("")) return c1;
			
			int height_c = Integer.parseInt( c1.split(regex_height)[1].split("px")[0] );
			int top_c1 = Integer.parseInt( c1.split(regex_top)[1].split("px;")[0] );
			int top_c2 = Integer.parseInt( c2.split(regex_top)[1].split("px;")[0] );
			//System.out.println(height_c1+" "+top_c2);
			c1 = c1.replaceFirst(regex_top,"top:"+(top_c1-offset)+"px;");
			offset += height_c;
			c2 = c2.replaceFirst(regex_top,"top:"+(top_c2-offset)+"px;");
		}
		else {
			if (c1.equals("")) return c2;
			if (c2.equals("")) return c1;
			
			//System.out.println(    );
			
			int height_c1 = Integer.parseInt( c1.split(regex_height)[1].split("px")[0] );
			int top_c2 = Integer.parseInt( c2.split(regex_top)[1].split("px;")[0] );
			System.out.println(height_c1+" "+top_c2);
			c2 = c2.replaceFirst(regex_top,"top:"+(top_c2-height_c1)+"px;");
		}
		
		c1 = c1.replaceFirst(regex_z,"z-index:0;");
		c2 = c2.replaceFirst(regex_z,"z-index:1;");
		
		System.out.println(c1+c2);
		return c1 + c2;
		
	}
	
	/**
	 * Moves a block with a tag that contains the in_block_tag string to the bottom, and adjusts its z-index
	 * @param data
	 * @param in_block_tag
	 * @return
	 */
	public final static String move_block_down(String data, final String in_block_tag) {
		StringBuilder b1 = new StringBuilder();
		StringBuilder b2 = new StringBuilder();
		StringBuilder b3 = new StringBuilder();
		int i,last=0,j,k=0;
		while (true) {
			i = data.indexOf("<div ",last);
			j = data.indexOf(">",i);
			if (data.substring(i,j).contains(in_block_tag)) {
				b1.append( data.substring(last,i) );
				
				char[] remaining = data.substring(i).toCharArray();
				int depth = 0;
				for (;;++k) {
					if (remaining[k]=='<') {
						if (remaining[k+1]=='/') --depth;
						else ++depth;
					}
					b2.append(remaining[k]);
					if (depth==0&&remaining[k]=='>') break;
				}
				for (++k;k<remaining.length;++k) {
					b3.append(remaining[k]);
				}
				
				break;
			}
			b1.append( data.substring(last,j) );
			last = j;
		}
		//System.out.println("BLOCK1:\n"+b1.toString());
		//System.out.println("BLOCK2:\n"+b2.toString());
		//System.out.println("BLOCK3:\n"+b3.toString());
		String BLOCK1 = b1.toString();
		String BLOCK2 = b2.toString();
		String BLOCK3 = b3.toString();
		BLOCK2 = BLOCK2.replaceFirst(regex_z,"z-index:2;");
		String result = BLOCK1 + BLOCK3;
		result = result.substring(0,result.length()-1-"</div>".length()) + BLOCK2 + "</div>";
		return result;
	}
	
	/**
	 * Miniature tests for the conversion helpers.
	 * @param args
	 */
	public static void main(String[] args) {
		/*String c1 = "<div id=\"isc_7W\" eventproxy=\"template_body\" class=\"normal\" style=\"POSITION:relative;LEFT:0px;TOP:10px;WIDTH:1345px;HEIGHT:629px;Z-INDEX:201980;BACKGROUND-COLOR:#800080;OVERFLOW:visible;\" onscroll return template_body.$nd()>\n" +
	    "  <div id=\"isc_7X\" eventproxy=\"template_body\" style=\"POSITION:relative;VISIBILITY:inherit;Z-INDEX:203240;CURSOR:default;\">\n" +
	    "    &nbsp;\n" +
	    "  </div>\n" +
	    "</div>\n";
	    String c2 = "<div id=\"isc_DG\" eventproxy=\"index_body\" class=\"normal\" style=\"POSITION:relative;LEFT:0px;TOP:10px;WIDTH:1345px;HEIGHT:538px;Z-INDEX:203240;OVERFLOW:visible;\" onscroll return index_body.$nd()>\n" +
	    "  <div id=\"isc_DH\" eventproxy=\"index_body\" style=\"POSITION:relative;VISIBILITY:inherit;Z-INDEX:201980;CURSOR:default;\">\n" +
	    "    &nbsp;\n" +
	    "    <div id=\"isc_DM\" eventproxy=\"isc_Canvas_17\" class=\"normal\" style=\"POSITION:absolute;LEFT:0px;TOP:0px;WIDTH:100px;HEIGHT:100px;Z-INDEX:203294;OVERFLOW:visible;\" onscroll return isc_canvas_17.$nd()>\n" +
	    "      <div id=\"isc_DN\" eventproxy=\"isc_Canvas_17\" style=\"POSITION:relative;VISIBILITY:inherit;Z-INDEX:203294;CURSOR:default;\">\n" +
	    "        &nbsp;Helllsdjkfhsdljghldfg\n" +
	    "      </div>\n" +
	    "    </div>\n" +
	    "  </div>\n" +
	    "</div>\n";
	    System.out.println(combine(c1,c2));*/
		
		String c = ""+
		"<div id=\"isc_HM\" eventproxy=\"isc_Canvas_59\" class=\"normal\" style=\"POSITION:relative;LEFT:0px;top:-80px;10px;WIDTH:918px;HEIGHT:478px;z-index:0;BACKGROUND-COLOR:#FFFF99;BACKGROUND-IMAGE:url(http://127.0.0.1:8888/images/blank.gif);BACKGROUND-REPEAT:repeat;BACKGROUND-POSITION:null;-moz-box-sizing:border-box;OVERFLOW:-moz-scrollbars-none;\" onscroll return isc_canvas_59.$nd()>\n"+
		"	<div id=\"isc_HN\" eventproxy=\"isc_Canvas_59\" style=\"POSITION:relative;VISIBILITY:inherit;Z-INDEX:204698;CURSOR:default;\"> &nbsp;\n"+
		"		<div id=\"isc_HO\" eventproxy=\"isc_Canvas_60\" class=\"normal\" style=\"POSITION:absolute;LEFT:0px;TOP:0px;WIDTH:918px;HEIGHT:100px;Z-INDEX:204716;BACKGROUND-COLOR:null;BACKGROUND-IMAGE:url(http://127.0.0.1:8888/images/null);BACKGROUND-REPEAT:repeat;BACKGROUND-POSITION:null;-moz-box-sizing:border-box;OVERFLOW:-moz-scrollbars-none;\" onscroll return isc_canvas_60.$nd()>\n"+
		"			<div id=\"isc_HP\" eventproxy=\"isc_Canvas_60\" style=\"POSITION:relative;VISIBILITY:inherit;Z-INDEX:204716;CURSOR:default;\"> &nbsp;\n"+
		"				<div id=\"isc_HQ\" eventproxy=\"isc_Canvas_61\" class=\"normal\" style=\"POSITION:absolute;LEFT:0px;TOP:0px;WIDTH:918px;HEIGHT:100px;Z-INDEX:204734;BACKGROUND-COLOR:null;BACKGROUND-IMAGE:url(http://127.0.0.1:8888/images/null);BACKGROUND-REPEAT:repeat;BACKGROUND-POSITION:null;-moz-box-sizing:border-box;OVERFLOW:-moz-scrollbars-none;\" onscroll return isc_canvas_61.$nd()>\n"+
		"					<div id=\"isc_HR\" eventproxy=\"isc_Canvas_61\" style=\"POSITION:relative;VISIBILITY:inherit;Z-INDEX:204734;CURSOR:default;\">\n"+
		"						<ul class semiopaquemenu>\n"+
		"							<li> <a href=\"http://127.0.0.1:8888/web_final/FileServlet?file=bbbb.html\"> bbbb </a> </li>\n"+
		"							<li> <a href=\"http://127.0.0.1:8888/web_final/FileServlet?file=aaaaa.html\"> aaaaa </a> </li>\n"+
		"							<li> <a href=\"http://127.0.0.1:8888/web_final/FileServlet?file=dddd.html\"> dddd </a> </li>\n"+
		"						</ul>\n"+
		"					</div>\n"+
		"				</div>\n"+
		"			</div>\n"+
		"		</div>\n"+
		"		<div id=\"isc_HS\" eventproxy=\"isc_Canvas_62\" class=\"normal\" style=\"POSITION:absolute;LEFT:165px;TOP:104px;WIDTH:308px;HEIGHT:43px;Z-INDEX:204752;BACKGROUND-COLOR:null;BACKGROUND-IMAGE:url(http://127.0.0.1:8888/images/null);BACKGROUND-REPEAT:repeat;BACKGROUND-POSITION:null;-moz-box-sizing:border-box;OVERFLOW:-moz-scrollbars-none;\" onscroll return isc_canvas_62.$nd()>\n"+
		"			<div id=\"isc_HT\" eventproxy=\"isc_Canvas_62\" style=\"POSITION:relative;VISIBILITY:inherit;Z-INDEX:204752;CURSOR:default;\"> <font size=\"4\"> &nbsp;aaadfadfasfdjasfhasfhafa </font> </div>\n"+
		"		</div>\n"+
		"	</div>\n"+
		"</div>\n"+
		"<div id=\"isc_HU\" eventproxy=\"isc_Canvas_63\" class=\"normal\" style=\"POSITION:relative;LEFT:0px;top:-558px;10px;WIDTH:918px;HEIGHT:470px;z-index:1;BACKGROUND-COLOR:null;BACKGROUND-IMAGE:url(http://127.0.0.1:8888/images/null);BACKGROUND-REPEAT:repeat;BACKGROUND-POSITION:null;-moz-box-sizing:border-box;OVERFLOW:-moz-scrollbars-none;\" onscroll return isc_canvas_63.$nd()>\n"+
		"	<div id=\"isc_HV\" eventproxy=\"isc_Canvas_63\" style=\"POSITION:relative;VISIBILITY:inherit;Z-INDEX:204770;CURSOR:default;\"> &nbsp; </div>\n"+
		"</div>";
		System.out.println("Got: \n"+move_block_down(c,"POSITION:absolute"));
	}
}