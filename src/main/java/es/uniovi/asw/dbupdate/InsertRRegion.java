package es.uniovi.asw.dbupdate;

import java.util.List;

import es.uniovi.asw.model.Region;

public class InsertRRegion implements InsertRegion{

	@Override
	public List<Region> insert(List<Region> regiones, String path) {
		return new InsertPRegion().insert(regiones, path);
	}

}
