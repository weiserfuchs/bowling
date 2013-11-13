package gui;

import java.util.ArrayList;
import java.util.List;

public class Score {
	
	private String name;
	
	private String str11 = "";
	private String str12 = "";
	
	private String str21 = "";
	private String str22 = "";
	
	private String str31 = "";
	private String str32 = "";
	
	private String str41 = "";
	private String str42 = "";
	
	private String str51 = "";
	private String str52 = "";
	
	private String str61 = "";
	private String str62 = "";
	
	private String str71 = "";
	private String str72 = "";
	
	private String str81 = "";
	private String str82 = "";
	
	private String str91 = "";
	private String str92 = "";
	
	private String str101 = "";
	private String str102 = "";
	private String str103 = "";
	
	private String strGesamt = "";
	
	final String TRENNER = "|";
	
	final String KOMMMER = ",";
	
	public Score() {
		
	}
	
	@Override
	public String toString() {
		return  name + 
				TRENNER + str11 + KOMMMER + str12 + TRENNER + str21 + KOMMMER + str22 +
				TRENNER	+ str31 + KOMMMER + str32 + TRENNER + str41 + KOMMMER + str42 +
				TRENNER + str51 + KOMMMER + str52 + TRENNER + str61 + KOMMMER + str62 +
				TRENNER + str71 + KOMMMER + str72 + TRENNER + str81 + KOMMMER + str82 +
				TRENNER + str91	+ KOMMMER + str92 + TRENNER + str101 + KOMMMER + str102 +
				KOMMMER + str103 + TRENNER + strGesamt;
	}

	public String getName() {
		return name;
	}
	
	public List<String> getList() {
		List<String> list = new ArrayList<String>();
		
		list.add(str11);
		list.add(str12);
		list.add(str21);
		list.add(str22);
		list.add(str31);
		list.add(str32);
		list.add(str41);
		list.add(str42);
		list.add(str51);
		list.add(str52);
		list.add(str61);
		list.add(str62);
		list.add(str71);
		list.add(str72);
		list.add(str81);
		list.add(str82);
		list.add(str91);
		list.add(str92);
		list.add(str101);
		list.add(str102);
		list.add(str103);
		
		return list;
	}

	public String getStrGesamt() {
		return strGesamt;
	}

	public void setStrGesamt(String strGesamt) {
		this.strGesamt = strGesamt;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStr11() {
		return str11;
	}
	
	public void setStr11(String str11) {
		this.str11 = str11;
	}
	
	public String getStr12() {
		return str12;
	}
	
	public void setStr12(String str12) {
		this.str12 = str12;
	}
	
	public String getStr21() {
		return str21;
	}
	
	public void setStr21(String str21) {
		this.str21 = str21;
	}
	
	public String getStr22() {
		return str22;
	}
	
	public void setStr22(String str22) {
		this.str22 = str22;
	}
	
	public String getStr31() {
		return str31;
	}
	
	public void setStr31(String str31) {
		this.str31 = str31;
	}
	
	public String getStr32() {
		return str32;
	}
	
	public void setStr32(String str32) {
		this.str32 = str32;
	}
	
	public String getStr41() {
		return str41;
	}
	
	public void setStr41(String str41) {
		this.str41 = str41;
	}
	
	public String getStr42() {
		return str42;
	}
	
	public void setStr42(String str42) {
		this.str42 = str42;
	}
	
	public String getStr51() {
		return str51;
	}
	
	public void setStr51(String str51) {
		this.str51 = str51;
	}
	
	public String getStr52() {
		return str52;
	}
	
	public void setStr52(String str52) {
		this.str52 = str52;
	}
	
	public String getStr61() {
		return str61;
	}
	
	public void setStr61(String str61) {
		this.str61 = str61;
	}
	
	public String getStr62() {
		return str62;
	}
	
	public void setStr62(String str62) {
		this.str62 = str62;
	}
	
	public String getStr71() {
		return str71;
	}
	
	public void setStr71(String str71) {
		this.str71 = str71;
	}
	
	public String getStr72() {
		return str72;
	}
	
	public void setStr72(String str72) {
		this.str72 = str72;
	}
	
	public String getStr81() {
		return str81;
	}
	
	public void setStr81(String str81) {
		this.str81 = str81;
	}
	
	public String getStr82() {
		return str82;
	}
	
	public void setStr82(String str82) {
		this.str82 = str82;
	}
	
	public String getStr91() {
		return str91;
	}
	
	public void setStr91(String str91) {
		this.str91 = str91;
	}
	
	public String getStr92() {
		return str92;
	}
	
	public void setStr92(String str92) {
		this.str92 = str92;
	}
	
	public String getStr101() {
		return str101;
	}
	
	public void setStr101(String str101) {
		this.str101 = str101;
	}
	
	public String getStr102() {
		return str102;
	}
	
	public void setStr102(String str102) {
		this.str102 = str102;
	}
	
	public String getStr103() {
		return str103;
	}
	
	public void setStr103(String str103) {
		this.str103 = str103;
	}
}
