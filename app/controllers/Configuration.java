package controllers;

import global.HashUtil;
import controllers.with.Secured;
import models.Config;
import play.Logger;
import play.Play;
import play.data.Form;
import play.mvc.*;
import views.html.*;

/**
 * Configuration du site web
 * 
 * @author julienlafont
 * 
 */
@Security.Authenticated(Secured.class)
public class Configuration extends Controller {

	public static Result index() {
		if (!Secured.isLogged()) return unauthorized();

		Form<Config> configForm = form(Config.class).fill(Config.getMainConfig());

		return ok(config.render(configForm));
	}

	public static Result enregistrer() {
		if (!Secured.isLogged()) return unauthorized();

		Form<Config> configForm = form(Config.class).bindFromRequest();

		if (configForm.hasErrors()) {
			return badRequest(config.render(configForm));
		} else {
			configForm.get().update(Config.ID_MAIN_CONFIG);
			flash("success", "Config mise à jour avec succès");
			return ok(config.render(configForm));
		}
	}

}
