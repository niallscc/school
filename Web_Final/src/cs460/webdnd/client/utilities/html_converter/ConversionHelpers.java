package cs460.webdnd.client.utilities.html_converter;

/**
 * Helper class for miscellaneous operations that renderer must do that don't
 * really fit in anywhere else.
 * 
 * @author Ian Mallett
 */
public class ConversionHelpers {
	private final static String regex_top = "((TOP)|(top)):\\s*;";
	// private final static String regex_width = "((WIDTH)|(width)):[0-9]+px;";
	private final static String regex_z = "((Z-INDEX)|(z-index)):\\s*[0-9]+;";
	private final static String regex_bg_null = "BACKGROUND-COLOR:null";
	private final static char[] backwards_two_close_div = ">vid/<>vid/<".toCharArray(); // "</div></div>" reversed
	// private final static String regex_height = "((HEIGHT)|(height)):\\s*";

	private static int z_index; // I wanted this to be "returned" by reference
								// in .set_z_index(...), but we're using Java,
								// so it must be a global variable.
	private static int offset;

	private final static String removeTwoClosingDivs(String block) {
		for (char c : backwards_two_close_div) {
			boolean continuing = true;
			while (continuing) {
				if (block.charAt(block.length() - 1) == c) {
					continuing = false;
				}
				block = block.substring(0,block.length() - 1);
			}
		}
		return block;
	}

	/** Resets the offset variable. Client should call this before every render. */
	public final static void reset() {
		offset = 0;
	}

	/**
	 * Extracts a single HTML element of data from the input, starting at the offset, which MUST correspond to an opening
	 * tag's "<".  Will fail or infinite loop if input is incorrect.
	 * @param data
	 * @param offset
	 * @param rel_depth the termination condition.  For example, "0" returns a block of data with balanced
	 * open/close tags.  "1" returns with one more open tag than close tag.  MUST be positive.
	 * @return
	 */
	private final static String extractElement(String data, int offset, int rel_depth) {
		// System.out.println("\".extract_element(...)\" got (offset "+offset+"): \""+data+"\"!");
		final String data2 = data.substring(offset);

		int i = 0;
		char[] block2_chars = data2.toCharArray();
		{
			boolean quoted = false;
			int depth = 0;
			for (;; ++i) {
				char c = block2_chars[i];

				if (c == '\"' || c == '\'') quoted = !quoted;
				else if (!quoted) {
					if (c == '<') depth += (block2_chars[i + 1] == '/')?-1:1;
					else if (c == '>' && depth == rel_depth) break;
				}
			}
		}
		return data2.substring(0,i + 1);
	}

	/**
	 * Sets the z-index of all the elements it finds at the top level of the
	 * hierarchy represented by the input in increasing order, except for
	 * menu-containing elements, whose z-index it sets to 1000. Will fail for
	 * broken HTML. Checks to be sure that such a parameter exists; if not,
	 * returns the unmodified block.
	 * 
	 * @param block
	 * @return
	 */
	private final static String setZIndex(String block) {
		
		//if (!block.contains(regex_z)) return block;
		if (!block.contains(regex_z)) {
			return "<div z-index:"+(z_index++)+">"+block+"</div>";
		}

		// System.out.println("Setting Z-index on \""+block+"\"!");
		String block_temp = new String(block);
		block = "";
		while (block_temp.length() != 0) {
			{
				int f = block_temp.indexOf("<");
				if (f == -1) {
					block += block_temp;
					break;
				}
				if (f != 0) {
					block += block_temp.substring(0,f);
					block_temp = block_temp.substring(f);
				}
			}

			System.out.println("block_temp (" + block_temp.length() + ") = \"" + block_temp + "\"");

			String element = extractElement(block_temp,0,0);
			block_temp = block_temp.substring(element.length());

			element = element.replaceFirst(regex_z,"z-index:" + (element.contains("<li>")?1000:z_index++) + ";");

			block += element;
		}
		return block;
	}

	/**
	 * Combines two components. Primary purpose is to set up z-indexing and
	 * relative positioning.
	 */
	public final static String combineTemplateAndPageBlocks(String block_1, String block_2, int top_offset) {
		// System.out.println("Block 1 = \""+block_1+"\"");
		// System.out.println("Block 2 = \""+block_2+"\"");
		
		//If the width is 100px, then what was intended was 100%.
		block_1 = block_1.replace("100px","100%");
		block_2 = block_2.replace("100px","100%");

		//Prepare the blocks for concatenation by removing the first two <div ...> and the trailing <div></div>
		//of the second and first blocks.  Also determine whether to keep the <div ...><div ...> from the
		//template or from the body.  Will fail if input is incorrect.
		{
			final String temp1 = extractElement(block_1,0,2);
			final String temp2 = extractElement(block_2,0,2);
			block_1 = removeTwoClosingDivs(block_1);
			block_2 = block_2.substring(temp2.length());
			if (!temp2.contains(regex_bg_null)&&temp2.contains("BACKGROUND")) { //Use page's, not template's
				block_1 = temp2 + block_1.substring(temp1.length());
			}
		}
		
		// Set the width of the block to 100% instead of in pixels
		// block_1 = block_1.replaceFirst(regex_width, "width:100%;");

		// Concatenate. The blocks now form a unified hierarchy.
		String block = block_1 + block_2;

		// System.out.println("Block 1 = \""+block_1+"\"");
		// System.out.println("Block 2 = \""+block_2+"\"");

		System.out.println("BEGIN\n" + block + "\nEND");

		// Set z-index on all elements in the block. To do this, rip off the
		// first two
		// levels of hierarchy, do the operation, then tack them back on.
		{
			String temp1 = extractElement(block,0,2);
			String temp3 = "</div></div>";
			String temp2 = block.substring(temp1.length());
			temp2 = removeTwoClosingDivs(temp2);
			// System.out.print("###########T1:\n"+temp1+"\n\n"+"###########T2:\n"+temp2+"\n\n"+"###########T3:\n"+temp3+"\n");
			z_index = 0;
			temp2 = setZIndex(temp2);
			block = temp1 + temp2 + temp3;
		}

		// Set the offset
		offset += top_offset;
		block = block.replaceFirst(regex_top,"top:" + offset + ";");

		return block;
	}

	/**
	 * Miniature tests for the conversion helpers.
	 * 
	 * @param args
	 */
	@SuppressWarnings("unused") public static void main(String[] args) {
		/*
		 * final String c1 =
		 * "<div id=\"isc_HK\" eventproxy=\"isc_Canvas_81\" class=\"normal\" style=\"POSITION:relative;LEFT:0px;TOP:0px;WIDTH:1343px;HEIGHT:117px;Z-INDEX:204572;BACKGROUND-COLOR:#99CC00;BACKGROUND-IMAGE:url(http://127.0.0.1:8888/images/blank.gif);BACKGROUND-REPEAT:repeat;BACKGROUND-POSITION:null;OVERFLOW:visible;\" onscroll=\"return isc_Canvas_81.$nd()\"><div id=\"isc_HL\" eventproxy=\"isc_Canvas_81\" style=\"POSITION:relative;VISIBILITY:inherit;Z-INDEX:204572;CURSOR:default;\">&nbsp;<div id=\"isc_HM\" eventproxy=\"isc_Canvas_82\" class=\"normal\" style=\"POSITION:absolute;LEFT:0px;TOP:0px;WIDTH:100px;HEIGHT:100px;Z-INDEX:204590;BACKGROUND-COLOR:null;BACKGROUND-IMAGE:url(http://127.0.0.1:8888/images/null);BACKGROUND-REPEAT:repeat;BACKGROUND-POSITION:null;OVERFLOW:visible;\" onscroll=\"return isc_Canvas_82.$nd()\"><div id=\"isc_HN\" eventproxy=\"isc_Canvas_82\" style=\"POSITION:relative;VISIBILITY:inherit;Z-INDEX:204590;CURSOR:default;\">&nbsp;template text header</div></div></div></div>"
		 * ; final String c2 =
		 * "<div id=\"isc_HO\" eventproxy=\"isc_Canvas_83\" class=\"normal\" style=\"POSITION:relative;LEFT:0px;TOP:0px;WIDTH:1343px;HEIGHT:117px;Z-INDEX:204608;BACKGROUND-COLOR:#800080;BACKGROUND-IMAGE:url(http://127.0.0.1:8888/images/blank.gif);BACKGROUND-REPEAT:repeat;BACKGROUND-POSITION:null;OVERFLOW:visible;\" onscroll=\"return isc_Canvas_83.$nd()\"><div id=\"isc_HP\" eventproxy=\"isc_Canvas_83\" style=\"POSITION:relative;VISIBILITY:inherit;Z-INDEX:204608;CURSOR:default;\">&nbsp;<div id=\"isc_HQ\" eventproxy=\"isc_Canvas_84\" class=\"normal\" style=\"POSITION:absolute;LEFT:0px;TOP:0px;WIDTH:100px;HEIGHT:100px;Z-INDEX:204626;BACKGROUND-COLOR:null;BACKGROUND-IMAGE:url(http://127.0.0.1:8888/images/null);BACKGROUND-REPEAT:repeat;BACKGROUND-POSITION:null;OVERFLOW:visible;\" onscroll=\"return isc_Canvas_84.$nd()\"><div id=\"isc_HR\" eventproxy=\"isc_Canvas_84\" style=\"POSITION:relative;VISIBILITY:inherit;Z-INDEX:204626;CURSOR:default;\">&nbsp;index text header</div></div></div></div>"
		 * ;
		 * 
		 * final String c3 =
		 * "<div id=\"isc_HS\" eventproxy=\"isc_Canvas_85\" class=\"normal\" style=\"POSITION:relative;LEFT:0px;TOP:10px;WIDTH:1343px;HEIGHT:622px;Z-INDEX:204644;BACKGROUND-COLOR:#6366FF;BACKGROUND-IMAGE:url(http://127.0.0.1:8888/images/blank.gif);BACKGROUND-REPEAT:repeat;BACKGROUND-POSITION:null;OVERFLOW:visible;\" onscroll=\"return isc_Canvas_85.$nd()\"><div id=\"isc_HT\" eventproxy=\"isc_Canvas_85\" style=\"POSITION:relative;VISIBILITY:inherit;Z-INDEX:204644;CURSOR:default;\">&nbsp;<div id=\"isc_HU\" eventproxy=\"isc_Canvas_86\" class=\"normal\" style=\"POSITION:absolute;LEFT:0px;TOP:0px;WIDTH:100px;HEIGHT:100px;Z-INDEX:204662;BACKGROUND-COLOR:null;BACKGROUND-IMAGE:url(http://127.0.0.1:8888/images/null);BACKGROUND-REPEAT:repeat;BACKGROUND-POSITION:null;OVERFLOW:visible;\" onscroll=\"return isc_Canvas_86.$nd()\"><div id=\"isc_HV\" eventproxy=\"isc_Canvas_86\" style=\"POSITION:relative;VISIBILITY:inherit;Z-INDEX:204662;CURSOR:default;\">&nbsp;template text body</div></div></div></div>"
		 * ; final String c4 =
		 * "<div id=\"isc_HW\" eventproxy=\"isc_Canvas_87\" class=\"normal\" style=\"POSITION:relative;LEFT:0px;TOP:10px;WIDTH:1343px;HEIGHT:622px;Z-INDEX:204680;BACKGROUND-COLOR:#FF9900;BACKGROUND-IMAGE:url(http://127.0.0.1:8888/images/blank.gif);BACKGROUND-REPEAT:repeat;BACKGROUND-POSITION:null;OVERFLOW:visible;\" onscroll=\"return isc_Canvas_87.$nd()\"><div id=\"isc_HX\" eventproxy=\"isc_Canvas_87\" style=\"POSITION:relative;VISIBILITY:inherit;Z-INDEX:204680;CURSOR:default;\">&nbsp;<div id=\"isc_HY\" eventproxy=\"isc_Canvas_88\" class=\"normal\" style=\"POSITION:absolute;LEFT:0px;TOP:0px;WIDTH:100px;HEIGHT:100px;Z-INDEX:204698;BACKGROUND-COLOR:null;BACKGROUND-IMAGE:url(http://127.0.0.1:8888/images/null);BACKGROUND-REPEAT:repeat;BACKGROUND-POSITION:null;OVERFLOW:visible;\" onscroll=\"return isc_Canvas_88.$nd()\"><div id=\"isc_HZ\" eventproxy=\"isc_Canvas_88\" style=\"POSITION:relative;VISIBILITY:inherit;Z-INDEX:204698;CURSOR:default;\">&nbsp;index text body</div></div></div></div>"
		 * ;
		 * 
		 * final String c5 =
		 * "<div id=\"isc_I0\" eventproxy=\"isc_Canvas_89\" class=\"normal\" style=\"position: relative; z-index: 204716; background-color: rgb(99, 99, 99); background-image: url(http://127.0.0.1:8888/images/blank.gif); overflow-x: visible; overflow-y: visible; left: 0px; top: 20px; width: 1343px; height: 100px; background-repeat: repeat repeat; \" onscroll=\"return isc_Canvas_89.$nd()\"><div id=\"isc_I1\" eventproxy=\"isc_Canvas_89\" style=\"POSITION:relative;VISIBILITY:inherit;Z-INDEX:204716;CURSOR:default;\">&nbsp;<div id=\"isc_I2\" eventproxy=\"isc_Canvas_90\" class=\"normal\" style=\"POSITION:absolute;LEFT:0px;TOP:0px;WIDTH:100px;HEIGHT:100px;Z-INDEX:204734;BACKGROUND-COLOR:null;BACKGROUND-IMAGE:url(http://127.0.0.1:8888/images/null);BACKGROUND-REPEAT:repeat;BACKGROUND-POSITION:null;OVERFLOW:visible;\" onscroll=\"return isc_Canvas_90.$nd()\"><div id=\"isc_I3\" eventproxy=\"isc_Canvas_90\" style=\"POSITION:relative;VISIBILITY:inherit;Z-INDEX:204734;CURSOR:default;\">&nbsp;template text footer</div></div></div></div>"
		 * ; final String c6 =
		 * "<div id=\"isc_I4\" eventproxy=\"isc_Canvas_91\" class=\"normal\" style=\"position: relative; z-index: 204752; background-color: rgb(128, 0, 0); background-image: url(http://127.0.0.1:8888/images/blank.gif); overflow-x: visible; overflow-y: visible; left: 0px; top: 20px; width: 1343px; height: 100px; background-repeat: repeat repeat; \" onscroll=\"return isc_Canvas_91.$nd()\"><div id=\"isc_I5\" eventproxy=\"isc_Canvas_91\" style=\"POSITION:relative;VISIBILITY:inherit;Z-INDEX:204752;CURSOR:default;\">&nbsp;<div id=\"isc_I6\" eventproxy=\"isc_Canvas_92\" class=\"normal\" style=\"POSITION:absolute;LEFT:0px;TOP:0px;WIDTH:100px;HEIGHT:100px;Z-INDEX:204770;BACKGROUND-COLOR:null;BACKGROUND-IMAGE:url(http://127.0.0.1:8888/images/null);BACKGROUND-REPEAT:repeat;BACKGROUND-POSITION:null;OVERFLOW:visible;\" onscroll=\"return isc_Canvas_92.$nd()\"><div id=\"isc_I7\" eventproxy=\"isc_Canvas_92\" style=\"POSITION:relative;VISIBILITY:inherit;Z-INDEX:204770;CURSOR:default;\">&nbsp;index text footer</div></div></div></div>"
		 * ;
		 * 
		 * 
		 * final String c7 =
		 * "<div id=\"isc_DZ\" eventproxy=\"isc_Canvas_39\" class=\"normal\" style=\"POSITION:relative;LEFT:0px;TOP:0px;WIDTH:1343px;HEIGHT:117px;Z-INDEX:203708;BACKGROUND-COLOR:#FF6600;BACKGROUND-IMAGE:url(http://127.0.0.1:8888/images/blank.gif);BACKGROUND-REPEAT:repeat;BACKGROUND-POSITION:null;OVERFLOW:visible;\" onscroll=\"return isc_Canvas_39.$nd()\"><div id=\"isc_E0\" eventproxy=\"isc_Canvas_39\" style=\"POSITION:relative;VISIBILITY:inherit;Z-INDEX:203708;CURSOR:default;\">&nbsp;<div id=\"isc_E1\" eventproxy=\"isc_Canvas_40\" class=\"normal\" style=\"POSITION:absolute;LEFT:0px;TOP:0px;WIDTH:100px;HEIGHT:100px;Z-INDEX:203726;BACKGROUND-COLOR:null;BACKGROUND-IMAGE:url(http://127.0.0.1:8888/images/null);BACKGROUND-REPEAT:repeat;BACKGROUND-POSITION:null;OVERFLOW:visible;\" onscroll=\"return isc_Canvas_40.$nd()\"><div id=\"isc_E2\" eventproxy=\"isc_Canvas_40\" style=\"POSITION:relative;VISIBILITY:inherit;Z-INDEX:203726;CURSOR:default;\">&nbsp;template text header</div></div></div></div>"
		 * ; final String c8 =
		 * "<div id=\"isc_E3\" eventproxy=\"isc_Canvas_41\" class=\"normal\" style=\"POSITION:relative;LEFT:0px;TOP:0px;WIDTH:1343px;HEIGHT:117px;Z-INDEX:203744;BACKGROUND-COLOR:null;BACKGROUND-IMAGE:url(http://127.0.0.1:8888/images/null);BACKGROUND-REPEAT:repeat;BACKGROUND-POSITION:null;OVERFLOW:visible;\" onscroll=\"return isc_Canvas_41.$nd()\"><div id=\"isc_E4\" eventproxy=\"isc_Canvas_41\" style=\"POSITION:relative;VISIBILITY:inherit;Z-INDEX:203744;CURSOR:default;\">&nbsp;<div id=\"isc_E5\" eventproxy=\"isc_Canvas_42\" class=\"normal\" style=\"POSITION:absolute;LEFT:0px;TOP:0px;WIDTH:100px;HEIGHT:100px;Z-INDEX:203762;BACKGROUND-COLOR:null;BACKGROUND-IMAGE:url(http://127.0.0.1:8888/images/null);BACKGROUND-REPEAT:repeat;BACKGROUND-POSITION:null;OVERFLOW:visible;\" onscroll=\"return isc_Canvas_42.$nd()\"><div id=\"isc_E6\" eventproxy=\"isc_Canvas_42\" style=\"POSITION:relative;VISIBILITY:inherit;Z-INDEX:203762;CURSOR:default;\">&nbsp;index text header</div></div></div></div>"
		 * ;
		 * 
		 * final String c9 =
		 * "<div id=\"isc_E7\" eventproxy=\"isc_Canvas_43\" class=\"normal\" style=\"position: relative; z-index: 203780; background-color: rgb(255, 0, 0); background-image: url(http://127.0.0.1:8888/images/blank.gif); overflow-x: visible; overflow-y: visible; left: 0px; top: 10px; width: 1463px; height: 622px; background-repeat: repeat repeat; \" onscroll=\"return isc_Canvas_43.$nd()\"><div id=\"isc_E8\" eventproxy=\"isc_Canvas_43\" style=\"POSITION:relative;VISIBILITY:inherit;Z-INDEX:203780;CURSOR:default;\">&nbsp;<div id=\"isc_E9\" eventproxy=\"isc_Canvas_44\" class=\"normal\" style=\"position: absolute; z-index: 203798; background-image: url(http://127.0.0.1:8888/images/null); overflow-x: visible; overflow-y: visible; left: 0px; top: 0px; width: 1463px; height: 100px; background-repeat: repeat repeat; \" onscroll=\"return isc_Canvas_44.$nd()\"><div id=\"isc_EA\" eventproxy=\"isc_Canvas_44\" style=\"POSITION:relative;VISIBILITY:inherit;Z-INDEX:203798;CURSOR:default;\">&nbsp;<div id=\"isc_EB\" eventproxy=\"isc_Canvas_45\" class=\"normal\" style=\"position: absolute; z-index: 203816; background-image: url(http://127.0.0.1:8888/images/null); overflow-x: visible; overflow-y: visible; left: 0px; top: 0px; width: 1423px; height: 100px; background-repeat: repeat repeat; \" onscroll=\"return isc_Canvas_45.$nd()\"><div id=\"isc_EC\" eventproxy=\"isc_Canvas_45\" style=\"POSITION:relative;VISIBILITY:inherit;Z-INDEX:203816;CURSOR:default;\"><ul class=\" underlinemenu \"><li><a href=\"http://127.0.0.1:8888/web_final/FileServlet?file=index.html\">index</a></li></ul></div></div></div></div><div id=\"isc_ED\" eventproxy=\"isc_Canvas_46\" class=\"normal\" style=\"POSITION:absolute;LEFT:0px;TOP:0px;WIDTH:100px;HEIGHT:100px;Z-INDEX:203834;BACKGROUND-COLOR:null;BACKGROUND-IMAGE:url(http://127.0.0.1:8888/images/null);BACKGROUND-REPEAT:repeat;BACKGROUND-POSITION:null;OVERFLOW:visible;\" onscroll=\"return isc_Canvas_46.$nd()\"><div id=\"isc_EE\" eventproxy=\"isc_Canvas_46\" style=\"POSITION:relative;VISIBILITY:inherit;Z-INDEX:203834;CURSOR:default;\">&nbsp;template text body</div></div></div></div>"
		 * ; final String c10 =
		 * "<div id=\"isc_EF\" eventproxy=\"isc_Canvas_47\" class=\"normal\" style=\"POSITION:relative;LEFT:0px;TOP:10px;WIDTH:1343px;HEIGHT:622px;Z-INDEX:203852;BACKGROUND-COLOR:null;BACKGROUND-IMAGE:url(http://127.0.0.1:8888/images/null);BACKGROUND-REPEAT:repeat;BACKGROUND-POSITION:null;OVERFLOW:visible;\" onscroll=\"return isc_Canvas_47.$nd()\"><div id=\"isc_EG\" eventproxy=\"isc_Canvas_47\" style=\"POSITION:relative;VISIBILITY:inherit;Z-INDEX:203852;CURSOR:default;\">&nbsp;<div id=\"isc_EH\" eventproxy=\"isc_Canvas_48\" class=\"normal\" style=\"POSITION:absolute;LEFT:0px;TOP:0px;WIDTH:100px;HEIGHT:100px;Z-INDEX:203870;BACKGROUND-COLOR:null;BACKGROUND-IMAGE:url(http://127.0.0.1:8888/images/null);BACKGROUND-REPEAT:repeat;BACKGROUND-POSITION:null;OVERFLOW:visible;\" onscroll=\"return isc_Canvas_48.$nd()\"><div id=\"isc_EI\" eventproxy=\"isc_Canvas_48\" style=\"POSITION:relative;VISIBILITY:inherit;Z-INDEX:203870;CURSOR:default;\">&nbsp;index text body</div></div></div></div>"
		 * ;
		 * 
		 * final String c11 =
		 * "<div id=\"isc_EJ\" eventproxy=\"isc_Canvas_49\" class=\"normal\" style=\"position: relative; z-index: 203888; background-color: rgb(255, 255, 0); background-image: url(http://127.0.0.1:8888/images/blank.gif); overflow-x: visible; overflow-y: visible; left: 0px; top: 20px; width: 1343px; height: 100px; background-repeat: repeat repeat; \" onscroll=\"return isc_Canvas_49.$nd()\"><div id=\"isc_EK\" eventproxy=\"isc_Canvas_49\" style=\"POSITION:relative;VISIBILITY:inherit;Z-INDEX:203888;CURSOR:default;\">&nbsp;<div id=\"isc_EL\" eventproxy=\"isc_Canvas_50\" class=\"normal\" style=\"POSITION:absolute;LEFT:0px;TOP:0px;WIDTH:100px;HEIGHT:100px;Z-INDEX:203906;BACKGROUND-COLOR:null;BACKGROUND-IMAGE:url(http://127.0.0.1:8888/images/null);BACKGROUND-REPEAT:repeat;BACKGROUND-POSITION:null;OVERFLOW:visible;\" onscroll=\"return isc_Canvas_50.$nd()\"><div id=\"isc_EM\" eventproxy=\"isc_Canvas_50\" style=\"POSITION:relative;VISIBILITY:inherit;Z-INDEX:203906;CURSOR:default;\">&nbsp;</div></div><div id=\"isc_EN\" eventproxy=\"isc_Canvas_51\" class=\"normal\" style=\"POSITION:absolute;LEFT:0px;TOP:0px;WIDTH:100px;HEIGHT:100px;Z-INDEX:203924;BACKGROUND-COLOR:null;BACKGROUND-IMAGE:url(http://127.0.0.1:8888/images/null);BACKGROUND-REPEAT:repeat;BACKGROUND-POSITION:null;OVERFLOW:visible;\" onscroll=\"return isc_Canvas_51.$nd()\"><div id=\"isc_EO\" eventproxy=\"isc_Canvas_51\" style=\"POSITION:relative;VISIBILITY:inherit;Z-INDEX:203924;CURSOR:default;\">&nbsp;template text footer</div></div></div></div>"
		 * ; final String c12 =
		 * "<div id=\"isc_EP\" eventproxy=\"isc_Canvas_52\" class=\"normal\" style=\"position: relative; z-index: 203942; background-image: url(http://127.0.0.1:8888/images/null); overflow-x: visible; overflow-y: visible; left: 0px; top: 20px; width: 1343px; height: 100px; background-repeat: repeat repeat; \" onscroll=\"return isc_Canvas_52.$nd()\"><div id=\"isc_EQ\" eventproxy=\"isc_Canvas_52\" style=\"POSITION:relative;VISIBILITY:inherit;Z-INDEX:203942;CURSOR:default;\">&nbsp;<div id=\"isc_ER\" eventproxy=\"isc_Canvas_53\" class=\"normal\" style=\"POSITION:absolute;LEFT:0px;TOP:0px;WIDTH:100px;HEIGHT:100px;Z-INDEX:203960;BACKGROUND-COLOR:null;BACKGROUND-IMAGE:url(http://127.0.0.1:8888/images/null);BACKGROUND-REPEAT:repeat;BACKGROUND-POSITION:null;OVERFLOW:visible;\" onscroll=\"return isc_Canvas_53.$nd()\"><div id=\"isc_ES\" eventproxy=\"isc_Canvas_53\" style=\"POSITION:relative;VISIBILITY:inherit;Z-INDEX:203960;CURSOR:default;\">&nbsp;index text footer</div></div></div></div>"
		 * ;
		 * 
		 * 
		 * System.out.println(combine_template_and_page_blocks(c7,c8,0));
		 * System.out.println(combine_template_and_page_blocks(c9,c10,0));
		 * System.out.println(combine_template_and_page_blocks(c11,c12,0));
		 */

		final String block1 = "<div id=\"isc_7M\" eventproxy=\"isc_Canvas_28\" class=\"normal\" style=\"POSITION:relative;LEFT:0px;TOP:0px;WIDTH:1343px;HEIGHT:117px;Z-INDEX:201908;BACKGROUND-COLOR:#ffffff;BACKGROUND-IMAGE:url(http://127.0.0.1:8888/images/null);BACKGROUND-REPEAT:repeat;BACKGROUND-POSITION:null;OVERFLOW:visible;\" onscroll=\"return isc_Canvas_28.$nd()\"><div id=\"isc_7N\" eventproxy=\"isc_Canvas_28\" style=\"POSITION:relative;VISIBILITY:inherit;Z-INDEX:201908;CURSOR:default;\">&nbsp;</div></div>";
		final String block2 = "<div id=\"isc_7O\" eventproxy=\"isc_Canvas_29\" class=\"normal\" style=\"POSITION:relative;LEFT:0px;TOP:0px;WIDTH:1343px;HEIGHT:117px;Z-INDEX:201926;BACKGROUND-COLOR:null;BACKGROUND-IMAGE:url(http://127.0.0.1:8888/images/null);BACKGROUND-REPEAT:repeat;BACKGROUND-POSITION:null;OVERFLOW:visible;\" onscroll=\"return isc_Canvas_29.$nd()\"><div id=\"isc_7P\" eventproxy=\"isc_Canvas_29\" style=\"POSITION:relative;VISIBILITY:inherit;Z-INDEX:201926;CURSOR:default;\">&nbsp;</div></div>";

		final String block3 = "<div id=\"isc_7M\" eventproxy=\"isc_Canvas_28\" class=\"normal\" style=\"POSITION:relative;LEFT:0px;TOP:0px;WIDTH:1343px;HEIGHT:117px;Z-INDEX:201908;BACKGROUND-COLOR:#ffffff;BACKGROUND-IMAGE:url(http://127.0.0.1:8888/images/null);BACKGROUND-REPEAT:repeat;BACKGROUND-POSITION:null;OVERFLOW:visible;\" onscroll=\"return isc_Canvas_28.$nd()\"><div id=\"isc_7N\" eventproxy=\"isc_Canvas_28\" style=\"POSITION:relative;VISIBILITY:inherit;Z-INDEX:201908;CURSOR:default;\">&nbsp;</div></div>fdsdfhgjkl;'jlhgfx";
		final String block4 = "<div id=\"isc_7O\" eventproxy=\"isc_Canvas_29\" class=\"normal\" style=\"POSITION:relative;LEFT:0px;TOP:0px;WIDTH:1343px;HEIGHT:117px;Z-INDEX:201926;BACKGROUND-COLOR:null;BACKGROUND-IMAGE:url(http://127.0.0.1:8888/images/null);BACKGROUND-REPEAT:repeat;BACKGROUND-POSITION:null;OVERFLOW:visible;\" onscroll=\"return isc_Canvas_29.$nd()\"><div id=\"isc_7P\" eventproxy=\"isc_Canvas_29\" style=\"POSITION:relative;VISIBILITY:inherit;Z-INDEX:201926;CURSOR:default;\">&nbsp;</div></div>";

		final String block5 = "<div id=\"isc_MY\" eventproxy=\"isc_Canvas_138\" class=\"normal\" style=\"POSITION:relative;LEFT:0px;TOP:20px;WIDTH:1294px;HEIGHT:38px;Z-INDEX:206066;BACKGROUND-COLOR:#808080;BACKGROUND-IMAGE:url(http://127.0.0.1:8888/images/blank.gif);BACKGROUND-REPEAT:repeat;BACKGROUND-POSITION:null;OVERFLOW:visible;\" onscroll=\"return isc_Canvas_138.$nd()\"><div id=\"isc_MZ\" eventproxy=\"isc_Canvas_138\" style=\"POSITION:relative;VISIBILITY:inherit;Z-INDEX:206066;CURSOR:default;\">&nbsp;</div></div>";
		final String block6 = "<div id=\"isc_N0\" eventproxy=\"isc_Canvas_139\" class=\"normal\" style=\"position: relative; z-index: 206084; background-image: url(http://127.0.0.1:8888/images/null); overflow-x: visible; overflow-y: visible; left: 0px; top: 0px; width: 1294px; height: 112px; background-repeat: repeat repeat; \" onscroll=\"return isc_Canvas_139.$nd()\"><div id=\"isc_N1\" eventproxy=\"isc_Canvas_139\" style=\"POSITION:relative;VISIBILITY:inherit;Z-INDEX:206084;CURSOR:default;\">&nbsp;sdftyugihjlk;,/.<div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div>sdgfsfgsd</div></div></div>";

		combineTemplateAndPageBlocks(block5,block6,10);
	}
}