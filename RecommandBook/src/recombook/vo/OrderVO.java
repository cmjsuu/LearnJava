package recombook.vo;

import java.util.Date;

public class OrderVO implements VO{

	private int id;
	
	private int manager_id;
	
	private int consignee_id;
	
	private int bookseller_id;
	
	private String order_sn;
	
	private double order_price;
	
	private int order_status;
	
	private Date add_time;
	
	private Date ship_time;
	
	private Date receive_time;
	
	private String remark;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getManager_id() {
		return manager_id;
	}

	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}

	public int getConsignee_id() {
		return consignee_id;
	}

	public void setConsignee_id(int consignee_id) {
		this.consignee_id = consignee_id;
	}

	public int getBookseller_id() {
		return bookseller_id;
	}

	public void setBookseller_id(int bookseller_id) {
		this.bookseller_id = bookseller_id;
	}

	public String getOrder_sn() {
		return order_sn;
	}

	public void setOrder_sn(String order_sn) {
		this.order_sn = order_sn;
	}

	public double getOrder_price() {
		return order_price;
	}

	public void setOrder_price(double order_price) {
		this.order_price = order_price;
	}

	public int getOrder_status() {
		return order_status;
	}

	public void setOrder_status(int order_status) {
		this.order_status = order_status;
	}

	public Date getAdd_time() {
		return add_time;
	}

	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}

	public Date getShip_time() {
		return ship_time;
	}

	public void setShip_time(Date ship_time) {
		this.ship_time = ship_time;
	}

	public Date getReceive_time() {
		return receive_time;
	}

	public void setReceive_time(Date receive_time) {
		this.receive_time = receive_time;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
