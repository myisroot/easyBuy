package  com.easybuynet.entity;

import java.io.Serializable;
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer u_id;
	private String u_loginname;
	private String u_pwd;
	private String u_email;
	private String u_phone;
	private Integer u_isadmin;
	private String u_sex;
	private String u_name;
	private String u_identitycods;
	private String u_createtime;
	private Integer u_isdelete;
	private Integer u_isadd;
	private Integer u_isupdate;
	private Integer u_islogin;

	public void setU_id(Integer u_id){
		this.u_id=u_id;
	}
	public Integer getU_id(){
		return u_id;
	}
	public void setU_loginname(String u_loginname){
		this.u_loginname=u_loginname;
	}
	public String getU_loginname(){
		return u_loginname;
	}
	public void setU_pwd(String u_pwd){
		this.u_pwd=u_pwd;
	}
	public String getU_pwd(){
		return u_pwd;
	}
	public void setU_email(String u_email){
		this.u_email=u_email;
	}
	public String getU_email(){
		return u_email;
	}
	public void setU_phone(String u_phone){
		this.u_phone=u_phone;
	}
	public String getU_phone(){
		return u_phone;
	}
	public void setU_isadmin(Integer u_isadmin){
		this.u_isadmin=u_isadmin;
	}
	public Integer getU_isadmin(){
		return u_isadmin;
	}
	public void setU_sex(String u_sex){
		this.u_sex=u_sex;
	}
	public String getU_sex(){
		return u_sex;
	}
	public void setU_name(String u_name){
		this.u_name=u_name;
	}
	public String getU_name(){
		return u_name;
	}
	public void setU_identitycods(String u_identitycods){
		this.u_identitycods=u_identitycods;
	}
	public String getU_identitycods(){
		return u_identitycods;
	}
	public void setU_createtime(String u_createtime){
		this.u_createtime=u_createtime;
	}
	public String getU_createtime(){
		return u_createtime;
	}
	public void setU_isdelete(Integer u_isdelete){
		this.u_isdelete=u_isdelete;
	}
	public Integer getU_isdelete(){
		return u_isdelete;
	}
	public void setU_isadd(Integer u_isadd){
		this.u_isadd=u_isadd;
	}
	public Integer getU_isadd(){
		return u_isadd;
	}
	public void setU_isupdate(Integer u_isupdate){
		this.u_isupdate=u_isupdate;
	}
	public Integer getU_isupdate(){
		return u_isupdate;
	}
	public void setU_islogin(Integer u_islogin){
		this.u_islogin=u_islogin;
	}
	public Integer getU_islogin(){
		return u_islogin;
	}

	@Override
	public String toString() {
		return "User{" +
				"u_id=" + u_id +
				", u_loginname='" + u_loginname + '\'' +
				", u_pwd='" + u_pwd + '\'' +
				", u_email='" + u_email + '\'' +
				", u_phone='" + u_phone + '\'' +
				", u_isadmin=" + u_isadmin +
				", u_sex='" + u_sex + '\'' +
				", u_name='" + u_name + '\'' +
				", u_identitycods='" + u_identitycods + '\'' +
				", u_createtime='" + u_createtime + '\'' +
				", u_isdelete=" + u_isdelete +
				", u_isadd=" + u_isadd +
				", u_isupdate=" + u_isupdate +
				", u_islogin=" + u_islogin +
				'}';
	}
}

