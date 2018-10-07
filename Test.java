import java.util.ArrayList;
import java.util.Arrays;

public class Channel {
	
	private ArrayList<Wave> waves = new ArrayList<Wave>();
	
	public void addTime(double time) {
		for(Wave wave: waves) {
			wave.addTime(time);
		}
	}
	
	public int getMaxFreq() {
		double maxFreq = 0;
		for(Wave wave: waves) {
			if(wave.getFreq() > maxFreq) maxFreq = wave.getFreq();
		}
		return (int) maxFreq;
	}
	
	public ArrayList<Wave> getWaves() {
		return waves;
	}
	
	public void addWave(Wave wave) {
		waves.add(wave);
	}
	
	public void decAmps(double dec) {
		for(Wave wave: waves) {
			wave.decAmp(dec);
			if(wave.getAmp() < 0) {
				//waves.remove(wave);
			}
		}
	}
	
	public void resetTimes() {
		for(Wave wave: waves) {
			wave.setLocalTime(0);
		}
	}
	
}
