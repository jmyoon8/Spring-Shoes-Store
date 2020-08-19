package mvc.stroe.springStore.dto;

import java.sql.Timestamp;

public class shoesInfo {
	private int shoesNumber;
	private String memId;
	private int pNum;
	private int cNum;
	private int cCount;
	private int oCount;
	private int rCount;
	private int pCount;
	private int oNum;
	private int dNum;
	private String pic;
	private String brand;
	private String modelName;
	private int price;
	private int stockCount;
	private int stockCount_extra;
	private int cnt;
	private String category;
	private Timestamp reg_date;

	
	public int getcCount() {
		return cCount;
	}
	public void setcCount(int cCount) {
		this.cCount = cCount;
	}
	public int getoCount() {
		return oCount;
	}
	public void setoCount(int oCount) {
		this.oCount = oCount;
	}
	public int getrCount() {
		return rCount;
	}
	public void setrCount(int rCount) {
		this.rCount = rCount;
	}
	public int getpCount() {
		return pCount;
	}
	public void setpCount(int pCount) {
		this.pCount = pCount;
	}
	public int getShoesNumber() {
		return shoesNumber;
	}
	public void setShoesNumber(int shoesNumber) {
		this.shoesNumber = shoesNumber;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public void setpNum(int pNum) {
		this.pNum = pNum;
	}
	public int getpNum() {
		return pNum;
	}
	public int getcNum() {
		return cNum;
	}
	public void setcNum(int cNum) {
		this.cNum = cNum;
	}
	public int getoNum() {
		return oNum;
	}
	public void setoNum(int oNum) {
		this.oNum = oNum;
	}
	public int getdNum() {
		return dNum;
	}
	public void setdNum(int dNum) {
		this.dNum = dNum;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getStockCount() {
		return stockCount;
	}
	public void setStockCount(int stockCount) {
		this.stockCount = stockCount;
	}
	public int getStockCount_extra() {
		return stockCount_extra;
	}
	public void setStockCount_extra(int stockCount_extra) {
		this.stockCount_extra = stockCount_extra;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	
	
}
