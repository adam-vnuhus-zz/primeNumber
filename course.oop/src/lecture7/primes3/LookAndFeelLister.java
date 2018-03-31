package lecture7.primes3;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 * @author LE-HONG Phuong
 * 
 *         Nov 6, 2012, 2:06:32 AM
 *         <p>
 *         List all Look and Feel supported by the system.
 */
public class LookAndFeelLister {
	public static void main(String[] args) {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				System.out.println(info.getClassName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
