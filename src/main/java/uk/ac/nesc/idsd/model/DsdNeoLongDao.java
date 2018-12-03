package uk.ac.nesc.idsd.model;

import java.util.List;

public interface DsdNeoLongDao {
	
	 void save(DsdNeoLong transientInstance);

		public  List<DsdNeoLong> findAll();

	    
	}


