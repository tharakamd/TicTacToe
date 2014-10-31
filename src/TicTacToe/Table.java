/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TicTacToe;

import java.util.ArrayList;

public class Table {

	ArrayList<Recode> tableTypes = new ArrayList<Recode>();
	
	public void addRecode(Recode recode){
		this.tableTypes.add(recode);
	}
	public ArrayList<Recode> getTable(){
		return this.tableTypes;
	}
}
