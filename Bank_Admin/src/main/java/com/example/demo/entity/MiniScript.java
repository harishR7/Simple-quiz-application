package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MiniScript {

	@DateTimeFormat(iso = ISO.DATE_TIME)
	LocalDateTime transactionDateTime;
	double amount;
	String type;
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MiniScript other = (MiniScript) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (transactionDateTime == null) {
			if (other.transactionDateTime != null)
				return false;
		} else if (!transactionDateTime.equals(other.transactionDateTime))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((transactionDateTime == null) ? 0 : transactionDateTime.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	
	
	
}