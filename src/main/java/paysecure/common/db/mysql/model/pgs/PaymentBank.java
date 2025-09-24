package paysecure.common.db.mysql.model.pgs;



import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Valid 
public class PaymentBank {

	private long id;
	@Pattern(regexp = "^[a-zA-Z0-9.\\-\\/_ ]*$")
	@NotEmpty
	private String name;
	private String live_url;
	private String test_url;
	private String address;
	private String country;
	private String state;
	private String city;
	private String postal_code;
	@Email
	private String email_address;
	private String other_info;
	private String allowed_curr;
	private String allowed_card;
	private int max_refund_days;

	private String test_2D_Card;
	private String test_2D_Card_CVC;
	private String test_3D_Card_CVC;
	private String test_3D_Card;
	private String test_2D_curr;
	private String test_3D_curr;
	private String public_key;
	private boolean checkWhiteList;
	private boolean is_active = true;
	private int is3ds = 0;

	private String className;
	private int isNetwotkTokenAllowed = 0;
	
	private String sslfilename;
	private String sslpassword;

	public int getIs3ds() {
		return is3ds;
	}

	public void setIs3ds(int is3ds) {
		this.is3ds = is3ds;
	}

	public boolean isCheckWhiteList() {
		return checkWhiteList;
	}

	public void setCheckWhiteList(boolean checkWhiteList) {
		this.checkWhiteList = checkWhiteList;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLive_url() {
		return live_url;
	}

	public void setLive_url(String live_url) {
		this.live_url = live_url;
	}

	public String getTest_url() {
		return test_url;
	}

	public void setTest_url(String test_url) {
		this.test_url = test_url;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	public String getEmail_address() {
		return email_address;
	}

	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}

	public String getOther_info() {
		return other_info;
	}

	public void setOther_info(String other_info) {
		this.other_info = other_info;
	}

	public String getAllowed_curr() {
		return allowed_curr;
	}

	public void setAllowed_curr(String allowed_curr) {
		this.allowed_curr = allowed_curr;
	}

	public boolean getIs_active() {
		return is_active;
	}

	public int getIsNetwotkTokenAllowed() {
		return isNetwotkTokenAllowed;
	}

	public void setIsNetwotkTokenAllowed(int isNetwotkTokenAllowed) {
		this.isNetwotkTokenAllowed = isNetwotkTokenAllowed;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	public String getAllowed_card() {
		return allowed_card;
	}

	public void setAllowed_card(String allowed_card) {
		this.allowed_card = allowed_card;
	}

	public int getMax_refund_days() {
		return max_refund_days;
	}

	public void setMax_refund_days(int max_refund_days) {
		this.max_refund_days = max_refund_days;
	}

	public String getTest_2D_Card() {
		return test_2D_Card;
	}

	public void setTest_2D_Card(String test_2d_Card) {
		test_2D_Card = test_2d_Card;
	}

	public String getTest_2D_Card_CVC() {
		return test_2D_Card_CVC;
	}

	public void setTest_2D_Card_CVC(String test_2d_Card_CVC) {
		test_2D_Card_CVC = test_2d_Card_CVC;
	}

	public String getTest_3D_Card_CVC() {
		return test_3D_Card_CVC;
	}

	public void setTest_3D_Card_CVC(String test_3d_Card_CVC) {
		test_3D_Card_CVC = test_3d_Card_CVC;
	}

	public String getTest_3D_Card() {
		return test_3D_Card;
	}

	public void setTest_3D_Card(String test_3d_Card) {
		test_3D_Card = test_3d_Card;
	}

	public String getTest_2D_curr() {
		return test_2D_curr;
	}

	public void setTest_2D_curr(String test_2d_curr) {
		test_2D_curr = test_2d_curr;
	}

	public String getTest_3D_curr() {
		return test_3D_curr;
	}

	public void setTest_3D_curr(String test_3d_curr) {
		test_3D_curr = test_3d_curr;
	}

	public String getPublic_key() {
		return public_key;
	}

	public void setPublic_key(String public_key) {
		this.public_key = public_key;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Override
	public String toString() {
		return "PaymentBank [id=" + id + ", name=" + name + ", live_url=" + live_url + ", test_url=" + test_url
				+ ", address=" + address + ", country=" + country + ", state=" + state + ", city=" + city
				+ ", postal_code=" + postal_code + ", email_address=" + email_address + ", other_info=" + other_info
				+ ", allowed_curr=" + allowed_curr + ", allowed_card=" + allowed_card + ", max_refund_days="
				+ max_refund_days + ", test_2D_Card=" + test_2D_Card + ", test_2D_Card_CVC=" + test_2D_Card_CVC
				+ ", test_3D_Card_CVC=" + test_3D_Card_CVC + ", test_3D_Card=" + test_3D_Card + ", test_2D_curr="
				+ test_2D_curr + ", test_3D_curr=" + test_3D_curr + ", public_key=" + public_key + ", checkWhiteList="
				+ checkWhiteList + ", is_active=" + is_active + ", is3ds=" + is3ds + ", className=" + className
				+ ", isNetwotkTokenAllowed=" + isNetwotkTokenAllowed + "]";
	}

	public String getSslfilename() {
		return sslfilename;
	}

	public void setSslfilename(String sslfilename) {
		this.sslfilename = sslfilename;
	}

	public String getSslpassword() {
		return sslpassword;
	}

	public void setSslpassword(String sslpassword) {
		this.sslpassword = sslpassword;
	}

}

