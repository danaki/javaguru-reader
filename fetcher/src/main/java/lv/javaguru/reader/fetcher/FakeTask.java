package lv.javaguru.reader.fetcher;

import org.springframework.stereotype.Component;

@Component
public class FakeTask extends Thread {

	@Override
	public void run() {
        for (;;) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
	}

}