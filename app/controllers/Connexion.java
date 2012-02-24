package controllers;

import global.HashUtil;
import play.Play;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.*;

/**
 * Connexion à l'espace d'administration
 * 
 * @author julienlafont
 * 
 */
public class Connexion extends Controller {

	public static Result index() {
		return ok(login.render(form(Login.class)));
	}

	public static Result authentifier() {
		Form<Login> loginForm = form(Login.class).bindFromRequest();

		if (loginForm.hasErrors()) {
			return badRequest(login.render(loginForm));
		} else {
			session("logged", "yes");
			return redirect(routes.Configuration.index());
		}
	}

	public static Result logout() {
		session().clear();
		return redirect(routes.Connexion.index());
	}

	public static class Login {

		public String password;

		public String validate() {
			play.Configuration conf = Play.application().configuration();

			if (conf.getString("application.password") == null || !conf.getString("application.password").equals(HashUtil.sha256(password))) {
				return "Informations de connexion invalides";
			}

			return null;
		}

	}
}
