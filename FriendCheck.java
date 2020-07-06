package ljh.java.exam14;

public class FriendCheck {
	int[] jumin = new int[13];
	float temp = 0.f;
	float total = 0.f;
	float ct = 2.f;
	float hap = 0.f;

	public boolean juminCheck(int jumin[]) {
		// hap 계산
		for(int i=0; i<jumin.length-1; i++){
			if(ct == 10.f) {
				ct = 2.f;
			}
			hap += jumin[i] * ct;
			ct++;
		}

		temp = 11.f * (int)(hap/11.f) + 11.f - hap;
		total = temp - 10.f * (int)(temp/10.f);
		if((int)total != jumin[jumin.length-1]){
			return false;
		}else {
			return true;
		}
	}
	
	public int[] getIntJumin(String jumin1, String jumin2) {
		int jumin[] = new int[13];
		int f = Integer.parseInt(jumin1);
		int l = Integer.parseInt(jumin2);
		jumin[0] = f / 100000;
		f %= 100000;
		jumin[1] = f / 10000;
		f %= 10000;
		jumin[2] = f / 1000;
		f %= 1000;
		jumin[3] = f / 100;
		f %= 100;
		jumin[4] = f / 10;
		f %= 10;
		jumin[5] = f;
		
		jumin[6] = l / 1000000;
		l %= 1000000;
		jumin[7] = l / 100000;
		l %= 100000;
		jumin[8] = l / 10000;
		l %= 10000;
		jumin[9] = l / 1000;
		l %= 1000;
		jumin[10] = l / 100;
		l %= 100;
		jumin[11] = l / 10;
		l %= 10;
		jumin[12] = l;
	
		return jumin;
	}
}
