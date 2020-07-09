package ljh.java.exam01;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IndetAddressEx {
	public static void main(String[] args) {
		// InetAddress는 ip정보와 host정보(이름)을 가지는 객체.
		// InetAddress객체는 생성자가 아닌 static 메서드를 이용해서 생성한다.
		try {
			// host이름에 해당하는 ip정보를 가진 InetAddress객체 얻기.
			InetAddress ipInfo1 = InetAddress.getByName("www.daum.net");
			// ip주소 얻기.
			String ip = ipInfo1.getHostAddress();
			System.out.println("ip주소 : " + ip);
			// host이름에 해당하는 ip정보를 가진 모든 InetAddress객체 얻기.
			InetAddress[] ipArray = InetAddress.getAllByName("www.daum.net");
			for (InetAddress tempip : ipArray) {
				System.out.println(tempip);
			}
			// 현재 컴퓨터의 ip정보를 가진 InetAddress객체 얻기.
			InetAddress myHost = InetAddress.getLocalHost();
			System.out.println("host : " + myHost.getHostName());
			System.out.println("host IP : " + myHost.getHostAddress());
		} catch (UnknownHostException e) {
			// host이름에 해당하는 host를 찾지 못했을 경우 예외처리.
			e.printStackTrace();
		}
	}
}
