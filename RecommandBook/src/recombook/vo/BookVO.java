package recombook.vo;

import java.util.Date;

public class BookVO implements VO{
	

	private int id;
	
	private int category_id;
	
	private String book_sn;
	
	private String book_ISBN;
	
	private String book_name;
	
	private String book_subheading;
	
	private String book_partNo;
	
	private String book_version;
	
	private String publish_place;
	
	private String publish_time;
	
	private String book_author;
	
	private int book_pagination;
	
	private String book_brief;
	
	private double unit_price;
	
	private Date add_time;
	
	private int recommand_times;
	
	
	private int is_new;
	
	private int book_status;
	
	private String img_url;
	
	private int bookseller_id;
	
	private int recommand_num_tercher;
	
	///Ôö¼ÓµÄcategory
	
	
	private int parent_id;
	
	private String category_key;
	
	private String category_value;
	
	private String category_brief;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getBook_sn() {
		return book_sn;
	}

	public void setBook_sn(String book_sn) {
		this.book_sn = book_sn;
	}

	public String getBook_ISBN() {
		return book_ISBN;
	}

	public void setBook_ISBN(String book_ISBN) {
		this.book_ISBN = book_ISBN;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getBook_subheading() {
		return book_subheading;
	}

	public void setBook_subheading(String book_subheading) {
		this.book_subheading = book_subheading;
	}

	public String getBook_partNo() {
		return book_partNo;
	}

	public void setBook_partNo(String book_partNo) {
		this.book_partNo = book_partNo;
	}

	public String getBook_version() {
		return book_version;
	}

	public void setBook_version(String book_version) {
		this.book_version = book_version;
	}

	public String getBook_author() {
		return book_author;
	}

	public void setBook_author(String book_author) {
		this.book_author = book_author;
	}

	public int getBook_pagination() {
		return book_pagination;
	}

	public void setBook_pagination(int book_pagination) {
		this.book_pagination = book_pagination;
	}

	public String getBook_brief() {
		return book_brief;
	}

	public void setBook_brief(String book_brief) {
		this.book_brief = book_brief;
	}

	public Date getAdd_time() {
		return add_time;
	}

	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}

	public int getBook_status() {
		return book_status;
	}

	public void setBook_status(int book_status) {
		this.book_status = book_status;
	}

	public int getBookseller_id() {
		return bookseller_id;
	}

	public void setBookseller_id(int bookseller_id) {
		this.bookseller_id = bookseller_id;
	}

	public String getPublish_place() {
		return publish_place;
	}

	public void setPublish_place(String publish_place) {
		this.publish_place = publish_place;
	}

	public String getPublish_time() {
		return publish_time;
	}

	public void setPublish_time(String publish_time) {
		this.publish_time = publish_time;
	}

	public double getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(double unit_price) {
		this.unit_price = unit_price;
	}

	public int getRecommand_times() {
		return recommand_times;
	}

	public void setRecommand_times(int recommand_times) {
		this.recommand_times = recommand_times;
	}

	public int getIs_new() {
		return is_new;
	}

	public void setIs_new(int is_new) {
		this.is_new = is_new;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	public int getRecommand_num_tercher() {
		return recommand_num_tercher;
	}

	public void setRecommand_num_tercher(int recommand_num_tercher) {
		this.recommand_num_tercher = recommand_num_tercher;
	}



	public int getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}

	public String getCategory_key() {
		return category_key;
	}

	public void setCategory_key(String category_key) {
		this.category_key = category_key;
	}

	public String getCategory_value() {
		return category_value;
	}

	public void setCategory_value(String category_value) {
		this.category_value = category_value;
	}

	public String getCategory_brief() {
		return category_brief;
	}

	public void setCategory_brief(String category_brief) {
		this.category_brief = category_brief;
	}
}
