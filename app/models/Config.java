package models;

import java.util.Map;
import java.util.concurrent.Callable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import play.cache.Cache;
import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity
public class Config extends Model {
	
	private static final String CLE_CACHE_CONFIG = "config";
	public static final long ID_MAIN_CONFIG = 1;
	
	@Id
	public Long id;
	
	@Constraints.Required
	public boolean inscriptionActive;
	
	@Constraints.Required
	public String inscriptionUrl;
	
	private static Finder<Long, Config> find = new Finder<Long, Config>(Long.class, Config.class);
	
	/**
	 * Retourne la configuration principale de l'application
	 * Configuration mise en cache ou rechargée depuis la bdd
	 * @return
	 */
	public static Config getMainConfig() {
		try {
			return (Config) Cache.getOrElse(CLE_CACHE_CONFIG, new Callable<Config>() {
				public Config call() throws Exception {
					return loadConfigFromDatabase(ID_MAIN_CONFIG);
				}
			}, 3600);
		} catch (Exception e) {
			return loadConfigFromDatabase(ID_MAIN_CONFIG);
		}
	}
	
	/**
	 * Chargement de la configuration depuis la base de données
	 * @param id
	 * @return
	 */
	private static Config loadConfigFromDatabase(Long id) {
		return find.byId(id);
	}
	
	/**
	 * Vider le cache lors des mises à jour
	 * @TODO Trouver une méthode plus générique
	 */
	@Override
	public void update(Object id) {
		Cache.set(CLE_CACHE_CONFIG, null);
		super.update(id);
	}

}
