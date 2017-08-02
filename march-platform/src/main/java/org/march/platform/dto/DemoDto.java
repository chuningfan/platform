package org.march.platform.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.march.platform.entity.Demo;

public class DemoDto {
	
	private Long id;
	
	private String demoName;
	
	private Date createDate;
	
	private BigDecimal amount;
	
	private List<Long> numbers = new ArrayList<>();;

	
	
	public DemoDto() {
	}

	public DemoDto(Demo demo) {
		if (demo == null) {
			return;
		}
		this.id = demo.getId();
		this.demoName = demo.getDemoName();
		this.amount = demo.getAmount();
		this.createDate = demo.getCreateDate();
		for (int i = 0; i < 5; i++) {
			numbers.add((i * id));
		}
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDemoName() {
		return demoName;
	}

	public void setDemoName(String demoName) {
		this.demoName = demoName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public List<Long> getNumbers() {
		return numbers;
	}

	public void setNumbers(List<Long> numbers) {
		this.numbers = numbers;
	}
	
}
