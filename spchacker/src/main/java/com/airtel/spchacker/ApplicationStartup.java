package com.airtel.spchacker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.airtel.spchacker.dao.DataBase;
import com.airtel.spchacker.dao.Dictionary;
import com.airtel.spchacker.dto.Bucket;

@Component
public class ApplicationStartup 
implements ApplicationListener<ApplicationReadyEvent> {

	@Value("${data}")
	 private String line;
	 
	/**
	 * This event is executed as late as conceivably possible to indicate that 
	 * the application is ready to service requests.
	 */
	@Override
	public void onApplicationEvent(final ApplicationReadyEvent event) {

		DataBase.array = new Bucket[DataBase.M];
		for (int i = 0; i < DataBase.M; i++) {
			DataBase.array[i] = new Bucket();
		}
		build("");
	}

	public void build(String filePath) {
        try {
           // BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String[] ip = line.split(" ");
            for(String st : ip) {
                add(st);
            }
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }

    }
 
 public void add(String key) {
	 key = key.toLowerCase();
	 DataBase.array[hash(key)].put(key);
    }
 private int hash(String key) {
        return (key.hashCode() & 0x7fffffff) % DataBase.M;
    }

}