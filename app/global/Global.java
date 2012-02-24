package global;

import java.util.List;
import java.util.Map;

import models.Config;

import com.avaje.ebean.Ebean;

import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.libs.Yaml;

public class Global extends GlobalSettings {

	public void onStart(Application app) {
		InitialData.insert(app);
	}

	static class InitialData {

		@SuppressWarnings("unchecked")
		public static void insert(Application app) {

			if (Ebean.find(Config.class).findRowCount() == 0) {

				Logger.debug("Chargement des données initiales");

				Map<String, List<Object>> all = (Map<String, List<Object>>) Yaml.load("default-config.yml");
				Ebean.save(all.get("configs"));

			}
		}

	}

}
