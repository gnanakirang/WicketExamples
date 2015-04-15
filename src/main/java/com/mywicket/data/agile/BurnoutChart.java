package com.mywicket.data.agile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BurnoutChart implements Serializable {
		private static final long serialVersionUID = 1L;
		List<EffortData> effortDataList = new ArrayList<EffortData>();
		
		public List<EffortData> getEffortDataList(){
			return effortDataList;
		}

		@Override
		public String toString() {
			return "BurnoutChart [effortDataList=" + effortDataList.toString() + "]";
		}
		
		
}
