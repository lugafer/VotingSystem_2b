package es.uniovi.asw.parser;

import es.uniovi.asw.model.Candidate;
import es.uniovi.asw.reportwriter.WReportR;
import es.uniovi.asw.reportwriter.WriteReport;

public class CheckFailsCandidate {
	
	private static final WriteReport reporter = new WReportR();
	public static String file;

	public static boolean check(Candidate candidate){
		return comprobarFallosDni(candidate) && comprobarFallosNombre(candidate)
				&& comprobarFallosPartido(candidate);
	}
	
	public static boolean comprobarFallosNombre(Candidate candidate){
		if (candidate.getName() == null || candidate.getName().equals("")) {
			reporter.report(file + " Nombre vacío --- ---");
			return false;
		}
		return true;
	}
	
	public static boolean comprobarFallosPartido(Candidate candidate){
		if (candidate.getCandidature().getName() == null || candidate.getCandidature().getName().equals("")){
			reporter.report(file + " Partido vacío --- ---");
			return false;
		}
		return true;
	}
	
	public static boolean comprobarFallosDni(Candidate candidate){
		if (candidate.getDNI() == null || candidate.getDNI().equals("")) {
			reporter.report(file + " NIF vacío --- ---");
			return false;
		} else if ((!Character.isLetter(candidate.getDNI().charAt(candidate.getDNI().length() - 1))) 
				|| (!(candidate.getDNI().length() == 9))) {
			reporter.report(file + " NIF no válido --- " + candidate.getDNI() + " ---");
			return false;
		}
		return true;
	}
}
