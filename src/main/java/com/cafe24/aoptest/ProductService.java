package com.cafe24.aoptest;

import org.springframework.stereotype.Service;

@Service
public class ProductService {
	public ProductVo find(String name) {
		System.out.println("finding......");
		
//		throw new RuntimeException("My Exception");
		
		return new ProductVo(name);
	}
}
