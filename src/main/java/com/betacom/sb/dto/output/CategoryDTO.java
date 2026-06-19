package com.betacom.sb.dto.output;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CategoryDTO {

	private Long id;
	private String category;
}
