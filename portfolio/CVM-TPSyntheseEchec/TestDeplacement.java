public class TestDeplacement {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//creer pieces
		Reine chose = new Reine("reine", "noir");
		Tour tour = new Tour("tour", "noir");
		Fou fou = new Fou("fou", "noir");
		Pion pionNoir = new Pion("pion", "noir");
		Pion pionBlanc = new Pion("pion", "blanc");
		Roi roi = new Roi("roi", "noir");
		
		//Positions de la reine
		Position reineStart = new Position(3,7);
		Position reineWorking1 = new Position(3,0);
		Position reineWorking2 = new Position(7,3);
		Position reineWorking3 = new Position(0,4);
		Position reineNotWorking = new Position(0,0);
		
		//positions de la tour
		Position tourStart = new Position(3,7);
		Position tourWorking1 = new Position(3,0);
		Position tourWorking2 = new Position(7,7);
		Position tourNotWorking = new Position(0,4);
		
		//positions du fou
		Position fouStart = new Position(3,7);
		Position fouNotWorking = new Position(3,0);
		Position fouWorking2 = new Position(7,3);
		Position fouWorking1 = new Position(0,4);
		
		//positions du pion
		Position pionNoirStart = new Position(3,6);
		Position pionNotWorking = new Position(3,0);
		Position pionNotWorking2 = new Position(7,3);
		Position pionWorking1 = new Position(3,5);
		Position pionWorking2 = new Position(3,4);
		
		//positions du roi
		Position roiStart = new Position(4,7);
		Position roiWorking1 = new Position(4,6);
		Position roiWorking2 = new Position(5,6);
		Position roiWorking3 = new Position(5,7);
		Position roiNotWorking = new Position(4,5);
		
		
		//Print pour verifier ce qui marche (Verif pieces)
		System.out.println("Reine:");
		System.out.println(chose.estValide(reineStart, reineWorking1));
		System.out.println(chose.estValide(reineStart, reineWorking2));
		System.out.println(chose.estValide(reineStart, reineWorking3));
		System.out.println(chose.estValide(reineStart, reineNotWorking));
		System.out.println("\nTour:");
		System.out.println(tour.estValide(reineStart, tourWorking1));
		System.out.println(tour.estValide(reineStart, tourWorking2));
		System.out.println(tour.estValide(reineStart, tourNotWorking));
		System.out.println("\nFou:");
		System.out.println(fou.estValide(fouStart, fouWorking1));
		System.out.println(fou.estValide(fouStart, fouWorking2));
		System.out.println(fou.estValide(fouStart, fouNotWorking));
		System.out.println("\nPion Noir:");
		System.out.println(pionNoir.estValide(pionNoirStart, pionWorking1));
		System.out.println(pionNoir.estValide(pionNoirStart, pionWorking2));
		System.out.println(pionNoir.estValide(pionNoirStart, pionNotWorking));
		System.out.println(pionNoir.estValide(pionNoirStart, pionNotWorking2));
		System.out.println("\nPion Blanc:");
		System.out.println(pionBlanc.estValide(pionNoirStart, pionWorking1));
		System.out.println(pionBlanc.estValide(pionNoirStart, pionWorking2));
		System.out.println(pionBlanc.estValide(pionNoirStart, pionNotWorking));
		System.out.println(pionBlanc.estValide(pionNoirStart, pionNotWorking2));
		System.out.println("\nRoi:");
		System.out.println(roi.estValide(roiStart, roiWorking1));
		System.out.println(roi.estValide(roiStart, roiWorking2));
		System.out.println(roi.estValide(roiStart, roiWorking3));
		System.out.println(roi.estValide(roiStart, roiNotWorking));
		
	}

}
