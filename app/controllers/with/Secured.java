package controllers.with;

import play.mvc.Http.Context;
import play.mvc.Security;

public class Secured extends Security.Authenticator {

	@Override
	public String getUsername(Context ctx) {
		return ctx.session().get("logged") != null ? "admin" : null;
	}

	public static boolean isLogged() {
		return Context.current().session().get("logged") != null;
	}

}