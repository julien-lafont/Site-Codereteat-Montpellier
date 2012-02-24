package controllers;

import global.HashUtil;
import models.Config;

import play.Play;
import play.cache.Cached;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

/**
 * Page d'accueil
 * @author julienlafont
 *
 */
public class Application extends Controller {

	@Cached(key="homepage")
	public static Result index() {
		return ok(index.render(
					Config.getMainConfig().inscriptionActive,
					Config.getMainConfig().inscriptionUrl
				));
	}

}