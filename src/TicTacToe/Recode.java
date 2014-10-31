/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TicTacToe;

public class Recode {
	private int type;
	private boolean valBoolean;
	private int valInt;
	private double valDouble;
	private String valString;
	private String column;
	
	
	public Recode(int type,String value,String column) {
		this.column = column;
		this.type = type;
		switch(type){
		case 0:
			this.addInt(value);break;
		case 1:
			this.addBoolean(value);break;
		case 2:
			this.addDouble(value);break;
			default:
				this.valString = value;
			
		}
		
		
	}
	
	
	public int getType() {
		return type;
	}


	public boolean isValBoolean() {
		return valBoolean;
	}


	public int getValInt() {
		return valInt;
	}


	public double getValDouble() {
		return valDouble;
	}


	public String getValString() {
		return valString;
	}


	public String getColumn() {
		return column;
	}


	private void addInt(String value){
		try{
			this.valInt = Integer.parseInt(value);
		}catch(Exception e){
			System.err.println("Invallid input in to the TbaleAdder");
		}
	}
	private void addBoolean(String value){
		try{
			this.valBoolean = Boolean.parseBoolean(value);
		}catch(Exception e){
			System.err.println("Invallid input in to the TbaleAdder");
		}
	}
	private void addDouble(String value){
		try{
			this.valDouble = Double.parseDouble(value);
		}catch(Exception e){
			System.err.println("Invallid input in to the TableAdder");
		}
	}
	
	
}
