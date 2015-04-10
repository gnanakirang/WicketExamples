package com.mywicket.data.agile;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class BurnoutChart implements Serializable {
		private static final long serialVersionUID = 1L;
		Set<EffortCoordinate> effortSet = new HashSet<EffortCoordinate>();
		public Set<EffortCoordinate> getEffortSet() {
			return effortSet;
		}
		
		
}
