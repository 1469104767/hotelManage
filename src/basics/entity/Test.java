package basics.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class Test {
	private Integer id;
	
	private String name;
	
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date time;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Test [id= "+this.getId()+ ", name=" + name + ", time=" + time + "]";
	}
	
}